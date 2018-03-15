package re.structure;

import java.util.HashSet;
import java.util.Set;

import re.automata.NFA;
import re.automata.State;
import re.automata.Transition;



public class Any extends RE{

	
	public Any()
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
		
		//create a transition for every variable
		for (String identifier: identifiers)
			fa.addTransition(new Transition(starting, identifier, finall));
		
		return fa;
	}
	
	public String toString()
	{
		return "?";
	}
	
	public boolean hasEmpty()
	{
		return false;
	}
	
	public boolean isNothing()
	{
		return false;
	}
	
	public RE clone()
	{
		return new Any();
	}
	
	public String getConstructor()
	{
		return "new Any()";
	}
	
	public RE residual(String input)
	{
		return new End();
	}
	
	public RE simplify()
	{
		return this;
	}
	
}