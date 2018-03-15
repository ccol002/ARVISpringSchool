package automata;

import java.util.Set;

public class ProductRho {

	private String setOfProps;
	private Set<String> nextState;
		
	public ProductRho(String setOfProps, Set<String> nextState) {
		this.setOfProps = setOfProps;
		this.nextState = nextState;
	}
	public String getSetOfProps() {
		return setOfProps;
	}
	public void setSetOfProps(String setOfProps) {
		this.setOfProps = setOfProps;
	}
	public Set<String> getNextState() {
		return nextState;
	}
	public void setNextState(Set<String> nextState) {
		this.nextState = nextState;
	}	
}
