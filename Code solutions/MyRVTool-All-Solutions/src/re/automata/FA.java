package re.automata;

import java.util.HashSet;
import java.util.Set;

public abstract class FA {
	
	private Set<State> states = new HashSet<State>();
	private Set<Label> labels = new HashSet<Label>();
	private Set<Transition> transitions = new HashSet<Transition>();
	
	//returns true is t does not already exist in transitions
	//note this is overridden in DFAs to check to non-determinism
	public boolean addTransition(Transition t)throws Exception
	{
		State source=null;
		State destination=null;
		
		//ensure that the actual object states referred to by the transition are actually pointed to by the Set of states
		//otherwise when complementing some states will not be affected
		if (!states.add(t.getSource()))
		{
			for (State s: states)
				if (s.equals(t.getSource()))
				{
					source = s;
					break;
				}
		}		
		else
			source = t.getSource();
		
		if (!states.add(t.getDestination()))
		{
			for (State s: states)
				if (s.equals(t.getDestination()))
				{
					destination = s;
					break;
				}
		}else
			destination = t.getDestination();
		
		
		labels.add(t.getLabel());
		
		return transitions.add(new Transition(source,t.getLabel(),destination));
	}
	
	public void addTransitionSet(Set<Transition> transitions) throws Exception
	{
		for (Transition t : transitions)
			addTransition(t);
	}
	
	public Set<State> getStates()
	{
		return states;
	}
	
	public Set<Label> getLabels()
	{
		return labels;
	}
	
	public Set<Transition> getTransitions()
	{
		return transitions;
	}
	
	public abstract DFA determinise();
	
	public String toString()
	{
		return transitions.toString();
	}
	
}
