package automata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import algorithms.Helper;
import ltlstructure.Bool;
import ltlstructure.LTL;

public class AlternatingBuchiAutomaton {

    private LTL formula;
    private LTL startStateQ0;
    private Set<Set<LTL>> sigma;
    private HashMap<LTL, Set<AlternatingBuchiRho>> alternatingBuchiRho;
    private Set<LTL> statesQ;
    private Set<LTL> finalStatesF;
    Set<LTL> acceptingStates;

    // accepts an LTL formula and creates a nondeterministic automata from it
    // --//
    public AlternatingBuchiAutomaton(LTL formula) {
        this.formula = formula;
        // -- still to be determined --//
        sigma = new HashSet<>();
        alternatingBuchiRho = new HashMap<>();
        statesQ = new HashSet<>();
        finalStatesF = new HashSet<>();

        createAutomatonFromLTL();
    }

    public LTL getFormula() {
        return formula;
    }

    public LTL getStartStateQ0() {
        return startStateQ0;
    }

    public Set<Set<LTL>> getSigma() {
        return sigma;
    }

    public Set<LTL> getStatesQ() {
        return statesQ;
    }

    public Set<LTL> getFinalStatesF() {
        return finalStatesF;
    }

    public HashMap<LTL, Set<AlternatingBuchiRho>> getRho() {
        return alternatingBuchiRho;
    }

    private void createAutomatonFromLTL() {
        // -- prepare formula --//
        formula = formula.rewrite();
        formula = formula.negationNormalForm();

        // -- set start states --//
        startStateQ0 = formula;

        // -- define sigma from list of props in formula --//
        Set<LTL> props = formula.props();
        //sigma = Helper.powerSet(props);

        for (LTL prop : props) {
            Set<LTL> setOfProp = new HashSet<>();
            setOfProp.add(prop);
            sigma.add(setOfProp);
        }

        // -- get all possible combinations of the props --//
        // Set<Set<LTL>> setOfProps = Helper.setOfAllSubsets(sigma); //unused
        // due to optimization

        // -- determine states Q of automaton --//
        List<LTL> closures = new ArrayList<>(formula.closure());
        for (LTL state : closures) {
            LTL newState = state.negationNormalForm();
            statesQ.add(newState);
        }
        statesQ.add(new Bool(true));
        //statesQ.add(new Bool(false));

        Set<LTL> falseState = new HashSet<>();
        falseState.add(new Bool(false));

        // -- determine set of transitions --//
        for (LTL ltl : statesQ) {
            for (Set<LTL> s : sigma) {
                Set<AlternatingBuchiRho> tempTrans;
                if (!alternatingBuchiRho.containsKey(ltl)) {
                    tempTrans = new HashSet<>();
                } else {
                    tempTrans = alternatingBuchiRho.get(ltl);
                }

                AlternatingBuchiRho r = new AlternatingBuchiRho(ltl, s);
                //-- Only include accepting states --//
                if (!r.getNextState().contains(falseState)) {
                    tempTrans.add(r);
                }

                alternatingBuchiRho.put(ltl, tempTrans);
            }
        }

        Set<LTL> tempAllStates = new HashSet<>(statesQ);
        // -- optimization to remove nonreachable states --//
        for (LTL currentState : tempAllStates) {
            boolean found = false;
            List<LTL> visitedStates = new ArrayList<>();
            if (currentState.equals(startStateQ0) || depthFirstSearch(currentState, startStateQ0, visitedStates)) {
                found = true;
            }
            if (!found) {
                // -- if path not found --//
                statesQ.remove(currentState);
            }
        }

        // -- for each state in original set --//
        for (LTL currentState : tempAllStates) {
            // -- if it is not in current set --//
            if (!statesQ.contains(currentState)) {
                // -- remove its transitions --//
                alternatingBuchiRho.remove(currentState);
            }
        }

        // -- determine accepting states --//
        acceptingStates = new HashSet<>();
        for (LTL q : statesQ) {
            if (q.isFinalState()) {
                acceptingStates.add(q);
            }
        }
    }

    private boolean depthFirstSearch(LTL currentState, LTL visitedState, List<LTL> visitedStates) {
        boolean found = false;
        visitedStates.add(visitedState);
        // -- for each transition --//
        for (AlternatingBuchiRho r : alternatingBuchiRho.get(visitedState)) {
            // -- check with each possible next state --//
            for (Set<LTL> nextState : r.getNextState()) {
                for (LTL nextS : nextState) {
                    if (nextS.equals(currentState)) {
                        // -- then state is reachable --//
                        found = true;
                    }

                    if (found) {
                        return true;
                    } else {
                        if (!visitedStates.contains(nextS)) {
                            visitedState = nextS;
                            // -- if not already visited --//
                            if (depthFirstSearch(currentState, visitedState, visitedStates)) {
                                return true;
                            }
                        }
                    }
                }
            }
            if (found) {
                return true;
            }
        }
        if (found) {
            return true;
        }
        return false;
    }
}
