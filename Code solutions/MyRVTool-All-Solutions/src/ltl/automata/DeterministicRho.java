package ltl.automata;

import java.util.Set;

import ltlstructure.LTL;

public class DeterministicRho {

	private Set<LTL> state;
	private Set<LTL> setOfProps;
	private Set<Set<LTL>> nextState;

	public DeterministicRho(Set<LTL> state, Set<LTL> setOfProps, Set<Set<LTL>> nextState) {
		this.state = state;
		this.setOfProps = setOfProps;
		this.nextState = nextState;
	}
	
	public Set<LTL> getState() {
		return state;
	}
	
	public Set<LTL> getSetOfProps() {
		return setOfProps;
	}
	
	public Set<Set<LTL>> getNextState() {
		return nextState;
	}
	
	public void setNextState(Set<Set<LTL>> nextState) {
		this.nextState = nextState;
	}
}
