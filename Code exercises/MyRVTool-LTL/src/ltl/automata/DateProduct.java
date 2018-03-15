package ltl.automata;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DateProduct {

	private Map<String, Set<DateRho>> transitions;
	private Set<String> statesQ; //all states
	private Set<String> finalStatesF; //accepting states
	private String startStateQ0; //starting state
	private Set<String> badStates; //bad states
	private Set<String> normalStates; //normal states

	// -- takes a DFSA and determines the states and transitions for DATE --//
	public DateProduct(ProductAutomaton automaton) {

		transitions = new HashMap<>();
		statesQ = new HashSet<>();
		finalStatesF = new HashSet<>();
		badStates = new HashSet<>();
		normalStates = new HashSet<>();

		// -- name states --//
		Map<Set<String>, String> nameMap = new HashMap<>();
		int count = 0;

		for (Set<String> state : automaton.getStatesQ()) {
			nameMap.put(state, "S" + count);
			statesQ.add("S" + count);
			
//			if (state.toString().contains("[false]") && !state.toString().contains("[true]")) {
//				//formula violated
//				badStates.add("S" + count);
//			}

			count++;
		}

		// -- rename start state --//
		startStateQ0 = nameMap.get(automaton.getStartStateQ0());

		// -- rename final states --//
		for (Set<String> state : automaton.getFinalStatesF()) {
			finalStatesF.add(nameMap.get(state));
		}

		// -- rename bad states --//
		for (Set<String> state : automaton.getBadStates()) {
			badStates.add(nameMap.get(state));
		}
		
		// -- define normal states (not starting and not bad) --//
		for (Set<String> state : automaton.getStatesQ()) {
			if (!startStateQ0.equals(nameMap.get(state))
					&& !badStates.contains(nameMap.get(state))
					&& !finalStatesF.contains(nameMap.get(state))) {
				normalStates.add(nameMap.get(state));
			}
		}

		// -- rename states in transitions --//
		Iterator<Set<String>> iterator = automaton.getTransitions().keySet().iterator();

		while (iterator.hasNext()) {
			Set<String> state = iterator.next();
			Set<ProductRho> rhoSet = automaton.getTransitions().get(state);

			Set<DateRho> dateRhos = new HashSet<>();

			for (ProductRho productRho : rhoSet) {
				DateRho dateRho = new DateRho(productRho.getSetOfProps().toString(), nameMap.get(productRho
						.getNextState()));
				dateRhos.add(dateRho);
			}

			transitions.put(nameMap.get(state), dateRhos);
		}
	}

	public Map<String, Set<DateRho>> getTransitions() {
		return transitions;
	}

	public void setTransitions(Map<String, Set<DateRho>> transitions) {
		this.transitions = transitions;
	}

	public Set<String> getStatesQ() {
		return statesQ;
	}

	public void setStatesQ(Set<String> statesQ) {
		this.statesQ = statesQ;
	}

	public Set<String> getFinalStatesF() {
		return finalStatesF;
	}

	public void setFinalStatesF(Set<String> finalStatesF) {
		this.finalStatesF = finalStatesF;
	}

	public String getStartStateQ0() {
		return startStateQ0;
	}

	public void setStartStateQ0(String startStateQ0) {
		this.startStateQ0 = startStateQ0;
	}

	public Set<String> getNormalStates() {
		return normalStates;
	}

	public void setNormalStates(Set<String> normalStates) {
		this.normalStates = normalStates;
	}

	public Set<String> getBadStates() {
		return badStates;
	}

	public void setBadStates(Set<String> badStates) {
		this.badStates = badStates;
	}

}
