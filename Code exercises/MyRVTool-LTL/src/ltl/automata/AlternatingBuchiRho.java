package ltl.automata;
import java.util.HashSet;
import java.util.Set;

import ltlstructure.LTL;


public class AlternatingBuchiRho {

	private LTL state;
	private Set<LTL> setOfProps;
	private Set<Set<LTL>> nextState;
	
	public AlternatingBuchiRho(LTL state, Set<LTL> prop) {
		this.state = state;
		this.setOfProps = prop;
		nextState = new HashSet<Set<LTL>>();
		
		LTL tempNextState = state.alternatingRho(prop);
		tempNextState = tempNextState.negationNormalForm();
		tempNextState = tempNextState.moveAlternatingAndsInside();
		Set<LTL> tempOrs = new HashSet<LTL>(tempNextState.getSetFromAlternatingOrs());
		for(LTL ltl : new HashSet<LTL>(tempOrs)) {
			nextState.add(new HashSet<LTL>(ltl.getSetFromAlternatingAnds()));
		}
	}
	
	public LTL getState() {
		return state;
	}
	
	public Set<LTL> getSetOfProps() {
		return setOfProps;
	}
	
	public Set<Set<LTL>> getNextState() {
		return nextState;
	}
}
