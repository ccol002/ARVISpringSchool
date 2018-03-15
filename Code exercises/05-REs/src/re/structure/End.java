package re.structure;

import java.util.HashSet;
import java.util.Set;

import re.automata.Label;
import re.automata.NFA;
import re.automata.State;
import re.automata.Transition;



public class End extends RE{

	
	public End()
	{
	}
	
	public Set<Var> getVars()
	{
		return new HashSet<Var>();
	}
	
	public NFA toAutomaton(Set<String> identifiers) throws Exception
	{
		NFA fa = new NFA();
		
		//create a starting and a final state
		State starting = new State(State.STARTING, State.NONFINAL);
		State finall = new State(State.NONSTARTING, State.FINAL);
		
		fa.addTransition(new Transition(starting, Label.empty, finall));
		
		return fa;
	}
	
	public String toString()
	{
		return "1";
	}
	
	public boolean hasEmpty()
	{
		return true;
	}
	
	public boolean isNothing()
	{
		return false;
	}
	
	public RE clone()
	{
		return new End();
	}
	
	public String getConstructor()
	{
		return "new End()";
	}
	
	public RE residual(String input)
	{
		return new Nothing();
	}
	
	public RE simplify()
	{
		return this;
	}
	
}