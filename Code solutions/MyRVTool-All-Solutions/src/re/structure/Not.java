package re.structure;

import java.util.HashSet;
import java.util.Set;

import re.automata.NFA;
import re.automata.State;
import re.automata.Transition;


public class Not extends RE{

	private Var var;
	
	public Not(Var var)
	{
		this.var = var;
	}	
	
	public Var get()
	{
		return var;
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
		for (String s: identifiers)
			if (!var.getName().equals(s))
				fa.addTransition(new Transition(starting, s, finall));
		
		return fa;
	}
	
	public String toString()
	{
		return "!(" + var + ")";
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
		return new Not((Var)var.clone());
	}
	
	public String getConstructor()
	{
		return "new Not(" + var.getConstructor()+")";
	}
	
	public RE residual(String input)
	{
		if (var.getName().equals(input))
			return new Nothing();
		else
			return new End();
	}
	
	public RE simplify()
	{	
		return this;
	}
	
}
