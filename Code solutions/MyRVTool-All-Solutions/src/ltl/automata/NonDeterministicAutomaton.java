package ltl.automata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ltlstructure.LTL;
import algorithms.Helper;
import algorithms.Tarjan;

public class NonDeterministicAutomaton {

	private Set<LTL> startStateQ0;
	private Set<Set<LTL>> sigma;
	private Map<Set<LTL>, Set<NonDeterministicRho>> transitions;
	private Set<Set<LTL>> statesQ;
	private Set<Set<LTL>> finalStatesF;

	// -- creates deterministic automata from NFSA --//
	public NonDeterministicAutomaton(AlternatingBuchiAutomaton nfsa) {
		transitions = new HashMap<>();
		finalStatesF = new LinkedHashSet<>();

		// -- new sigma --//
		sigma = nfsa.getSigma();

		// -- new start states --//
		startStateQ0 = new HashSet<LTL>();
		startStateQ0.add(nfsa.getStartStateQ0());

		// -- new states --//
		statesQ = Helper.setOfAllSubsets(new ArrayList<LTL>(nfsa.getStatesQ()));

		// -- new final states --//
		for (Set<LTL> state : statesQ) {
			for (LTL s : state) {
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
		for (Set<LTL> setLtl : statesQ) {
			// -- only consider conjunction of states (other states already
			// handled above) --//
			if (setLtl.size() != 0) {
				Map<Set<LTL>, NonDeterministicRho> newTransitions = new HashMap<>();
				// -- consider each subset of the state --//
				for (LTL s : setLtl) {
					Set<AlternatingBuchiRho> oldTransitions = new LinkedHashSet<>(nfsa.getRho().get(s));
					for (AlternatingBuchiRho ndRho : oldTransitions) {
						NonDeterministicRho dRho = new NonDeterministicRho(setLtl, ndRho.getSetOfProps(), ndRho.getNextState());
						if (!newTransitions.containsKey(dRho.getSetOfProps())) {
							// -- create new next state --//
							newTransitions.put(dRho.getSetOfProps(), dRho);
						} else {
							// -- update previously defined next state --//
							NonDeterministicRho existingRho = newTransitions.get(dRho.getSetOfProps());
							Set<Set<LTL>> currNextState = new LinkedHashSet<>(existingRho.getNextState());
							for (Set<LTL> state : ndRho.getNextState()) {
								if (!currNextState.contains(state)) {
									currNextState.add(state);
								}
							}
							NonDeterministicRho newRho = new NonDeterministicRho(existingRho.getState(),
									existingRho.getSetOfProps(), currNextState);
							newTransitions.put(dRho.getSetOfProps(), newRho);
						}
					}
				}
				Set<NonDeterministicRho> vals = new LinkedHashSet<>(newTransitions.values());
				transitions.put(setLtl, vals);
			}
		}

		// -- optimization to remove nonreachable states --//
		Set<Set<LTL>> allStates = new LinkedHashSet<>(statesQ);
		for (Set<LTL> currentState : allStates) {
			boolean found = false;
			List<Set<LTL>> visitedStates = new ArrayList<>();
			if (startStateQ0.equals(currentState) || depthFirstSearch(currentState, startStateQ0, visitedStates)) {
				found = true;
			}
			if (!found) {
				// -- if path not found --//
				statesQ.remove(currentState);
			}
		}

		// -- for each state in original set --//
		for (Set<LTL> currentState : allStates) {
			// -- if it is not in current set --//
			if (!statesQ.contains(currentState)) {
				// -- remove its transitions --//
				transitions.remove(currentState);
				// -- remove from set of final states (if final state) --//
				finalStatesF.remove(currentState);
			}
		}
		


		// -- get graph to be used by Tarjan's algorithm --//
		Map<Set<LTL>, Set<Set<LTL>>> graph = createGraph();

		// -- get SCCs using Tarjan's --//
		Tarjan tarjan = new Tarjan(graph);
		tarjan.runAlgorithm();
		List<Set<Set<LTL>>> sccs = tarjan.getSccs();

		for (Set<LTL> q : statesQ) {
			boolean nonempty = false;

			// -- check each strongly connected component --//
			for (Set<Set<LTL>> scc : sccs) {

				// -- check if scc contains current state --//
				if (scc.contains(q)) {
					// -- check it contains an accepting state --//
					for (LTL acceptingState : nfsa.acceptingStates) {
						for (Set<LTL> s : scc) {
							if (s.contains(acceptingState)) {
								nonempty = true;
								break;
							}
						}
					}
					// -- if scc has already been found
					if (nonempty) {
						finalStatesF.add(q);
						break;
					}
				}
			}
		}
	}

	public Set<LTL> getStartStateQ0() {
		return startStateQ0;
	}

	public Set<Set<LTL>> getSigma() {
		return sigma;
	}

	public Set<Set<LTL>> getStatesQ() {
		return statesQ;
	}

	public Set<Set<LTL>> getFinalStatesF() {
		return finalStatesF;
	}

	public Map<Set<LTL>, Set<NonDeterministicRho>> getTransitions() {
		return transitions;
	}

	private boolean depthFirstSearch(Set<LTL> currentState, Set<LTL> visitedState, List<Set<LTL>> visitedStates) {
		boolean found = false;
		visitedStates.add(visitedState);
		for (NonDeterministicRho r : transitions.get(visitedState)) {
			for (Set<LTL> nextState : r.getNextState()) {
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

	private Map<Set<LTL>, Set<Set<LTL>>> createGraph() {
		Map<Set<LTL>, Set<Set<LTL>>> graph = new LinkedHashMap<>();
		for (Set<LTL> ltl : statesQ) {
			Set<NonDeterministicRho> transitions = this.transitions.get(ltl);
			for (NonDeterministicRho r : transitions) {
				// -- if no set for this state already exists --//
				if (!graph.containsKey(r.getState())) {
					// -- add new set --//
					graph.put(r.getState(), new HashSet<Set<LTL>>());
				}
				Set<Set<LTL>> values = r.getNextState();
				// -- add each possible value --//
				for (Set<LTL> v : values) {
					// -- if not already added --//
					if (!graph.get(r.getState()).contains(v)) {
						graph.get(r.getState()).add(v);
					}
				}
			}
		}

		return graph;
	}
}
