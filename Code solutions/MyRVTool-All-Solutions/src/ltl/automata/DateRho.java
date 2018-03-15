package ltl.automata;


public class DateRho {

	private String setOfProps;
	private String nextState;
		
	public DateRho(String setOfProps, String nextState) {
		this.setOfProps = setOfProps;
		this.nextState = nextState;
	}
	public String getSetOfProps() {
		return setOfProps;
	}
	public void setSetOfProps(String setOfProps) {
		this.setOfProps = setOfProps;
	}
	public String getNextState() {
		return nextState;
	}
	public void setNextState(String nextState) {
		this.nextState = nextState;
	}	
	
}
