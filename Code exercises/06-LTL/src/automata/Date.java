package automata;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import ltlstructure.LTL;

public class Date {

	private Map<String, Set<DateRho>> transitions;
	private Set<String> statesQ; //all states
	private Set<String> finalStatesF; //accepting states
	private String startStateQ0; //starting state
	private Set<String> badStates; //bad states
	private Set<String> normalStates; //normal states

	// -- takes a DFSA and determines the states and transitions for DATE --//
	public Date(DeterministicAutomaton automaton) {

		transitions = new HashMap<>();
		statesQ = new LinkedHashSet<>();
		finalStatesF = new LinkedHashSet<>();
		badStates = new LinkedHashSet<>();
		normalStates = new LinkedHashSet<>();

		// -- name states --//
		Map<Set<Set<LTL>>, String> nameMap = new HashMap<>();
		int count = 0;

		for (Set<Set<LTL>> state : automaton.getStatesQ()) {
			nameMap.put(state, "S" + count);
			statesQ.add("S" + count);
			
			boolean t = false;
			boolean f = false;
			for (Set<LTL> s : state) {
				if (s.toString().equals("[true]")) {
					t = true;
				} else if (s.toString().equals("[false]")) {
					f = true;
				}
			}
			
			if (state.toString().equals("[[false]]")
					|| (f && !t)
					) {
				//formula violated
				badStates.add("S" + count);
			} else if (state.toString().equals("[[true]]")
					|| (t && !f)
					)	{
				finalStatesF.add("S" + count);
			}
			
			count++;
		}

		// -- rename start state --//
		Set<Set<LTL>> startStateSet = new HashSet<>();
		startStateSet.add(automaton.getStartStateQ0());
		startStateQ0 = nameMap.get(startStateSet);

		// -- rename final states --//
		//for (Set<Set<LTL>> state : automaton.getFinalStatesF()) {
			//finalStatesF.add(nameMap.get(state));
		//}
		
		// -- define normal states (not starting and not bad) --//
		for (Set<Set<LTL>> state : automaton.getStatesQ()) {
			if (!startStateQ0.equals(nameMap.get(state)) && !badStates.contains(nameMap.get(state))) {
				normalStates.add(nameMap.get(state));
			}
		}

		// -- rename states in transitions --//
		Iterator<Set<Set<LTL>>> iterator = automaton.getTransitions().keySet().iterator();

		while (iterator.hasNext()) {
			Set<Set<LTL>> state = iterator.next();
			Set<DeterministicRho> rhoSet = automaton.getTransitions().get(state);

			Set<DateRho> dateRhos = new LinkedHashSet<>();

			for (DeterministicRho deterministicRho : rhoSet) {
				DateRho dateRho = new DateRho(deterministicRho.getSetOfProps().toString(), nameMap.get(deterministicRho
						.getNextState()));
				dateRhos.add(dateRho);
			}

			transitions.put(nameMap.get(state), dateRhos);
		}
	}
	
	public boolean isFinalState(String state) {
		for (String finalState : finalStatesF) {
			if (state.equals(finalState)) {
				return true;
			}
		}
		return false;
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
