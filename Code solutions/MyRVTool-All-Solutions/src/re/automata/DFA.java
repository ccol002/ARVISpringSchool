package re.automata;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class DFA extends FA{


	public State getStartState()
	{
		for (State s : getStates())
			if (s.isStarting())
				return s; 
		
		return null;
	}
	
	public boolean addTransition(Transition t)throws Exception
	{
		//label cannot be empty
		if (t.getLabel().equals(Label.empty))
			throw new Exception("Transition label cannot be empty in deterministic FA");
			
		//there cannot be 2 transitions from the same state with the same label
		for (Transition u: getTransitions())
			if (u.getSource().equals(t.getSource()) 
					&& u.getLabel().equals(t.getLabel()))
				throw new Exception("Non-deterministic transition in deterministic FA");
		
		//test for more than one start state
		State startState = getStartState();
		
		if (getStates().add(t.getSource()))
			if (startState != null && t.getSource().isStarting())
				throw new Exception("Only one starting state is allowed in deterministic FA");
			
		if (	getStates().add(t.getDestination()))
			if (startState != null && t.getDestination().isStarting())
				throw new Exception("Only one starting state is allowed in deterministic FA");
			
		getLabels().add(t.getLabel());
				
		return super.addTransition(t);
	}
	
	
	public DFA determinise()
	{
		return this;
	}
	
	//turns *this* automaton into a total automaton
	public DFA makeTotal(Set<String> identifiers) throws Exception
	{
		State sink = new State();
		
		ArrayList<Transition> transitions = new ArrayList<Transition>();
		
		//find the missing transitions for each state, for each label
		for (State s: this.getStates())
			for (String l : identifiers)
			{
				boolean found = false;
				for (Transition t: this.getTransitions())
					if (t.getSource().equals(s) && t.getLabel().equals(new Label(l)))
					{
						found = true;
						break;
					}

				if (!found)//add sink transition
					transitions.add(new Transition(s,l,sink));
			}

		
		//add the new transitions
		for (Transition t: transitions)
			addTransition(t);
		
		//if the sink state has been used, make it iterate over itself on any input
		if (transitions.size() > 0)
			for (Label l : this.getLabels())
				addTransition(new Transition(sink,l,sink));
		
		return this;
	}
	
	//returns true if a final state can be reached from state s
	public boolean canReachFinalState(State s, Set<State> alreadyProcessed)
	{
		if (s.isFinal())
			return true;
		else
			//go through all transitions recursively
			for (Transition t : getTransitions())
				if (t.getSource().equals(s))
					if (alreadyProcessed.add(t.getDestination()))
						if (canReachFinalState(t.getDestination(), alreadyProcessed))
							return true;
		
		return false;
	}
	
	//returns true if a final state can be reached from state s
	public boolean canReachFinalState(State s)
	{
		return canReachFinalState(s, new HashSet<State>());
	}
	
	//turns *this* automaton into its complement
	public DFA complement()
	{
		//toggle whether each state is final or not
		for (State s : this.getStates())
			if (s.isFinal())
				s.setTempFinal(State.NONFINAL);
			else // s is nonFinal
				//set to final (representing violation) only if there is no chance of salvation, 
				// i.e. no final state is reachable
				s.setTempFinal(!canReachFinalState(s));
	
		for (State s : this.getStates())
			s.applyTempFinal();
		
		return this;
	}
}
