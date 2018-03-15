package ltl.automata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ltlstructure.LTL;
import algorithms.Helper;

public class DeterministicAutomaton {

	private Set<LTL> startStateQ0;
	private Set<Set<LTL>> sigma;
	private Map<Set<Set<LTL>>, Set<DeterministicRho>> transitions;
	private Set<Set<Set<LTL>>> statesQ;
	private Set<Set<Set<LTL>>> finalStatesF;

	// -- creates deterministic automata from NFSA --//
	public DeterministicAutomaton(NonDeterministicAutomaton nfsa) {
		transitions = new HashMap<>();
		finalStatesF = new LinkedHashSet<>();

		// -- new sigma --//
		sigma = nfsa.getSigma();

		// -- new start states --//
		startStateQ0 = nfsa.getStartStateQ0();

		// -- new states --//
		List<Set<LTL>> tempList = new ArrayList<>(nfsa.getStatesQ());
		statesQ = Helper.setOfAllSets(tempList);

		// -- new final states --//
		for (Set<Set<LTL>> state : statesQ) {
			for (Set<LTL> s : state) {
				if (nfsa.getFinalStatesF().contains(s)) {
					// -- if not already added --//
					if (!finalStatesF.contains(state)) {
						// -- this state is final state in DFSA --//
						finalStatesF.add(state);
					}
					break;
				}
			}
		}

		// -- create new transitions for new states --//
		for (Set<Set<LTL>> setLtl : statesQ) {
			// -- only consider conjunction of states (other states already
			// handled above) --//
			if (setLtl.size() != 0) {
				Map<Set<LTL>, DeterministicRho> newTransitions = new HashMap<>();
				// -- consider each subset of the state --//
				for (Set<LTL> s : setLtl) {
					Set<NonDeterministicRho> oldTransitions = new LinkedHashSet<>(nfsa.getTransitions().get(s));
					for (NonDeterministicRho ndRho : oldTransitions) {
						DeterministicRho dRho = new DeterministicRho(s, ndRho.getSetOfProps(), ndRho.getNextState());
						if (!newTransitions.containsKey(dRho.getSetOfProps())) {
							// -- create new next state --//
							newTransitions.put(dRho.getSetOfProps(), dRho);
						} else {
							// -- update previously defined next state --//
							DeterministicRho existingRho = newTransitions.get(dRho.getSetOfProps());
							Set<Set<LTL>> currNextState = new LinkedHashSet<>(existingRho.getNextState());
							for (Set<LTL> state : ndRho.getNextState()) {
								if (!currNextState.contains(state)) {
									currNextState.add(state);
								}
							}
							DeterministicRho newRho = new DeterministicRho(existingRho.getState(),
									existingRho.getSetOfProps(), currNextState);
							newTransitions.put(dRho.getSetOfProps(), newRho);
						}
					}
				}
				Set<DeterministicRho> vals = new LinkedHashSet<>(newTransitions.values());
				transitions.put(setLtl, vals);
			}
		}

		// -- optimization to remove nonreachable states --//
		Set<Set<Set<LTL>>> allStates = new LinkedHashSet<>(statesQ);
		for (Set<Set<LTL>> currentState : allStates) {
			boolean found = false;
			Set<Set<LTL>> tempState = new LinkedHashSet<>();
			tempState.add(startStateQ0);
			List<Set<Set<LTL>>> visitedStates = new ArrayList<>();
			if (tempState.equals(currentState) || depthFirstSearch(currentState, tempState, visitedStates)) {
				found = true;
			}
			if (!found) {
				// -- if path not found --//
				statesQ.remove(currentState);
			}
		}

		// -- for each state in original set --//
		for (Set<Set<LTL>> currentState : allStates) {
			// -- if it is not in current set --//
			if (!statesQ.contains(currentState)) {
				// -- remove its transitions --//
				transitions.remove(currentState);
				// -- remove from set of final states (if final state) --//
				finalStatesF.remove(currentState);
			}
		}
	}

	public Set<LTL> getStartStateQ0() {
		return startStateQ0;
	}

	public Set<Set<LTL>> getSigma() {
		return sigma;
	}

	public Set<Set<Set<LTL>>> getStatesQ() {
		return statesQ;
	}

	public Set<Set<Set<LTL>>> getFinalStatesF() {
		return finalStatesF;
	}

	public Map<Set<Set<LTL>>, Set<DeterministicRho>> getTransitions() {
		return transitions;
	}

	private boolean depthFirstSearch(Set<Set<LTL>> currentState, Set<Set<LTL>> visitedState, List<Set<Set<LTL>>> visitedStates) {
		boolean found = false;
		visitedStates.add(visitedState);
		for (DeterministicRho r : transitions.get(visitedState)) {
			Set<Set<LTL>> nextState = r.getNextState();
			if (nextState.equals(currentState)) {
				// -- then state is reachable --//
				found = true;
			}
			if (found) {
				return true;
			} else {
				if (!visitedStates.contains(nextState)) {
					visitedState = nextState;
					// -- if not already visited --//
					if (depthFirstSearch(currentState, visitedState, visitedStates)) {
						return true;
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
