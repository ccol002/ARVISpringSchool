package re.structure;

import java.util.Set;

import re.automata.Label;
import re.automata.NFA;
import re.automata.State;
import re.automata.Transition;


public class Star extends RE{

	private RE op;
	
	public Star(RE op)
	{
		this.op = op;
	}	
	
	public RE get()
	{
		return op;
	}
	
	public Set<Var> getVars()
	{
		return op.getVars();
	}
	
	public NFA toAutomaton(Set<String> identifiers) throws Exception
	{
		NFA fa = new NFA();
		
		//create a starting and a final state
		State starting = new State(State.STARTING, State.NONFINAL);
		State finall = new State(State.NONSTARTING, State.FINAL);
		
		//a transition from start to end
		fa.addTransition(new Transition(starting, Label.empty, finall));
		
		NFA sub = op.toAutomaton(identifiers);
		
		for (State s: sub.getStates())
		{
			if (s.isStarting())
			{
				s.setStarting(State.NONSTARTING);
				
				//from the new start state to the old starting
				fa.addTransition(new Transition(starting, Label.empty, s));
				
				//create a loop by connecting the new final state to the old starting
				fa.addTransition(new Transition(finall, Label.empty, s));
			}
			
			if (s.isFinal())
			{
				s.setFinal(State.NONFINAL);
				
				//connect the old final to the new
				fa.addTransition(new Transition(s, Label.empty, finall));
			}
		}
		
		//copy the existing transitions
		fa.addTransitionSet(sub.getTransitions());
		
		
		return fa;
	}
	
	public String toString()
	{
		return "(" + op + ")*";
	}
	
	public boolean hasEmpty()
	{
		return true;
	}
	
	public boolean isNothing()
	{
		return op.isNothing();
	}
	
	public RE clone()
	{
		return new Star(op.clone());
	}
	
	public String getConstructor()
	{
		return "new Star("+ op.getConstructor() + ")";
	}
	
	public RE residual(String input)
	{
		return new Seq(op.residual(input),this.clone());
	}
	
	public RE simplify()
	{	
		op = op.simplify();
		return this;
	}
	
}
