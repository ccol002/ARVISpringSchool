package ltl.automata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ltlstructure.LTL;
import algorithms.Helper;

//-- unused class, might require changes if to be used --//
public class ProductAutomaton {

	private Set<Set<LTL>> sigma;
	private Map<Set<String>, Set<ProductRho>> transitions;
	private Set<Set<String>> statesQ; // all states
	private Set<Set<String>> finalStatesF; // accepting states
	private Set<Set<String>> badStates; // bad states
	private Set<String> startStateQ0; // starting state

	public ProductAutomaton(DeterministicAutomaton dfsa1, DeterministicAutomaton dfsa2) {
		transitions = new HashMap<>();
		statesQ = new HashSet<>();
		finalStatesF = new HashSet<>();
		badStates = new HashSet<>();

		// -- sigma is the same in both automaton --//
		sigma = dfsa1.getSigma();

		// -- rename states --//
		Map<Set<Set<LTL>>, String> nameMap1 = new HashMap<>();
		Map<Set<Set<LTL>>, String> nameMap2 = new HashMap<>();
		Map<String, Set<Set<LTL>>> allStates = new HashMap<>();

		for (Set<Set<LTL>> state : dfsa1.getStatesQ()) {
				nameMap1.put(state, state.toString());
				allStates.put(state.toString(), state);
		}

		for (Set<Set<LTL>> state : dfsa2.getStatesQ()) {
				nameMap2.put(state, state.toString() + "'");
				allStates.put(state.toString() + "'", state);
		}

		// -- all states --//
		statesQ = Helper.setOfAllStrings(new ArrayList<>(allStates.keySet()));

		Set<Set<LTL>> tempStartState1 = new HashSet<>();
		tempStartState1.add(dfsa1.getStartStateQ0());
		Set<Set<LTL>> tempStartState2 = new HashSet<>();
		tempStartState2.add(dfsa2.getStartStateQ0());
		// -- define start state --//
		for (Set<String> state : statesQ) {
			// -- if state contains the two start states --//
			if ((state.size() == 2) && state.contains(nameMap1.get(tempStartState1))
					&& state.contains(nameMap2.get(tempStartState2))) {
				startStateQ0 = state;
			}
		}

		// -- create new transitions for new states --//
		for (Set<String> setStringState : statesQ) {
			if (setStringState.size() != 0) {
				Map<String, ProductRho> newTransitions = new HashMap<>();
				// -- consider each subset of the state --//
				for (String stringState : setStringState) {
					Set<DeterministicRho> oldTransitions1 = new HashSet<>();
					Set<DeterministicRho> oldTransitions2 = new HashSet<>();
					if (nameMap1.containsValue(stringState)) { // state in 1st dfsa
						oldTransitions1 = new HashSet<>(dfsa1.getTransitions().get(allStates.get(stringState)));
					}
					if (nameMap2.containsValue(stringState)){ // state in 2nd dfsa
						oldTransitions2 = new HashSet<>(dfsa2.getTransitions().get(allStates.get(stringState)));
					}

					addTransitions(newTransitions, nameMap1, oldTransitions1);
					addTransitions(newTransitions, nameMap2, oldTransitions2);
				}
				Set<ProductRho> vals = new HashSet<>(newTransitions.values());
				transitions.put(setStringState, vals);
			}
		}

		// -- optimization to remove nonreachable states --//
		Set<Set<String>> tempStatesQ = new HashSet<>(statesQ);
		for (Set<String> currentState : tempStatesQ) {
			boolean found = false;
			List<Set<String>> visitedStates = new ArrayList<>();
			if (startStateQ0.equals(currentState) || depthFirstSearch(currentState, startStateQ0, visitedStates)) {
				found = true;
			}
			if (!found) {
				// -- if path not found --//
				statesQ.remove(currentState);
			}
		}

		// -- for each state in original set --//
		for (Set<String> currentState : tempStatesQ) {
			// -- if it is not in current set --//
			if (!statesQ.contains(currentState)) {
				// -- remove its transitions --//
				transitions.remove(currentState);
			}
		}

		for (Set<String> statesSet : statesQ) {
			boolean isFinalState = true;
			boolean isBadState = true;
			for (String state: statesSet) {
				Set<Set<LTL>> q = allStates.get(state);

				//-- Check: ��� if q ����� F������ --//
				if (nameMap2.containsValue(state) && dfsa2.getFinalStatesF().contains(q)) {
					isFinalState = false;
				}

				//-- Check: ��� if q ����� F���� --//
				if (nameMap1.containsValue(state) && dfsa1.getStatesQ().contains(q)) {
					isBadState = false;
				}
			}

			if (isFinalState && !isBadState) {
				finalStatesF.add(statesSet);
			}

			if (isBadState && !isFinalState) {
				badStates.add(statesSet);
			}
		}
	}

	public Map<Set<String>, Set<ProductRho>> getTransitions() {
		return transitions;
	}

	public void setTransitions(Map<Set<String>, Set<ProductRho>> transitions) {
		this.transitions = transitions;
	}

	public Set<Set<String>> getStatesQ() {
		return statesQ;
	}

	public void setStatesQ(Set<Set<String>> statesQ) {
		this.statesQ = statesQ;
	}

	public Set<Set<String>> getFinalStatesF() {
		return finalStatesF;
	}

	public void setFinalStatesF(Set<Set<String>> finalStatesF) {
		this.finalStatesF = finalStatesF;
	}

	public Set<Set<String>> getBadStates() {
		return badStates;
	}

	public void setBadStates(Set<Set<String>> badStates) {
		this.badStates = badStates;
	}

	public Set<String> getStartStateQ0() {
		return startStateQ0;
	}

	public void setStartStateQ0(Set<String> startStateQ0) {
		this.startStateQ0 = startStateQ0;
	}

	private void addTransitions(Map<String, ProductRho> newTransitions, Map<Set<Set<LTL>>, String> nameMap, Set<DeterministicRho> oldTransitions) {
		for (DeterministicRho dRho : oldTransitions) {
			Set<String> nextStates = new HashSet<>();
			String nextState = nameMap.get(dRho.getNextState());
			nextStates.add(nextState);
			ProductRho productRho = new ProductRho(dRho.getSetOfProps().toString(), nextStates);
			if (!newTransitions.containsKey(productRho.getSetOfProps())) {
				// -- create new next state --//
				newTransitions.put(productRho.getSetOfProps(), productRho);
			} else {
				// -- update previously defined next state --//
				ProductRho existingRho = newTransitions.get(productRho.getSetOfProps());
				Set<String> currNextState = new HashSet<>(existingRho.getNextState());
				Set<Set<LTL>> state = dRho.getNextState();
				String newState = nameMap.get(state);
				if (!currNextState.contains(newState)) {
					currNextState.add(newState);
				}
				ProductRho newRho = new ProductRho(existingRho.getSetOfProps(), currNextState);
				newTransitions.put(productRho.getSetOfProps(), newRho);
			}
		}
	}

	private boolean depthFirstSearch(Set<String> currentState, Set<String> visitedState, List<Set<String>> visitedStates) {
		boolean found = false;
		visitedStates.add(visitedState);
		for (ProductRho r : transitions.get(visitedState)) {
			Set<String> nextState = r.getNextState();
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

	public Set<Set<LTL>> getSigma() {
		return sigma;
	}

	public void setSigma(Set<Set<LTL>> sigma) {
		this.sigma = sigma;
	}
}
