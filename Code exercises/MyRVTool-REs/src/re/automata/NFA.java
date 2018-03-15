package re.automata;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;



public class NFA extends FA{


	//compose states reachable from a state on empty transitions
		private Set<String> getCompositeState(State s, Set<String> compositeState)
		{
			compositeState.add(s.getLabel().toString());
			
			for (Transition t : this.getTransitions())
				if (t.getSource().equals(s) && t.getLabel().equals(Label.empty) && !compositeState.contains(t.getDestination()))
					getCompositeState(t.getDestination(), compositeState);
			
			return compositeState;
		}
		
		private Set<String> getCompositeState(State s)
		{
			return getCompositeState(s, new HashSet<String>());
		}
		
		//assumes s is composite
		private State expandCompositeState(State s)
		{
			Set<String> labels = ((CompositeLabel)s.getLabel()).getSubLabels();
			
			Set<String> toAdd = new HashSet<String>();
			
			for (String l : labels)
				toAdd.addAll(getCompositeState(new State(l)));
		
			labels.addAll(toAdd);
			
			return s;
		}
		
		//assumes s is composite
		//if one of s substates is final, s is set to final
		private void checkAndSetFinal(State compositeState) throws Exception
		{
			Set<String> labels = ((CompositeLabel)compositeState.getLabel()).getSubLabels();
			
			for (State s: this.getStates())
				if (s.isFinal())
					if (labels.contains(s.getLabel().toString()))
					{
						compositeState.setFinal(true);
						break;
					}
		}
		
		//build the deterministic automaton
		private DFA buildDFA (Map<State,Map<Label, State>> transitions)
		{
			DFA dfa = new DFA();
			
			for (State s : transitions.keySet())
			{
				for (Label l : transitions.get(s).keySet())
				{
					State d = transitions.get(s).get(l);
					
					try{
					
					//set final
						checkAndSetFinal(s);
						checkAndSetFinal(d);
					
					//add transition
						dfa.addTransition(new Transition(s,l,d));
					
					}catch(Exception ex)
					{
						ex.printStackTrace();
					}
				}
			}
			
			return dfa;
		}

		
		
		public Map<State,Map<Label, State>> generateTransitions(Set<State> allCompositeStates, Set<State> compositeStatesToProcess, Map<State,Map<Label, State>> transitions)
		{
	 		
	 		Set<State> newCompositeStates = new HashSet<State>();
	 		
	 		//for each COMPOSITE state generate transition and (composite) destination
	 		for (State state: compositeStatesToProcess)
	 		{	
	 			if (allCompositeStates.contains(state))
	 				continue;
	 			else
	 				allCompositeStates.add(state);
	 			
	 			Map<Label, State> transitionsPerState = new HashMap<Label, State>();
	 			
	 			for (Transition t : this.getTransitions())
	 				if (!t.getLabel().equals(Label.empty))
	 			{
	 				CompositeLabel compositeSourceLabel = (CompositeLabel)state.getLabel();
	 				
	 				if (compositeSourceLabel.getSubLabels().contains(t.getSource().getLabel().toString()))
	 				{
	 					State compositeDestinationState = null;
	 					if (transitionsPerState.containsKey(t.getLabel()))
	 						compositeDestinationState = transitionsPerState.get(t.getLabel());
	 					else
	 					{
	 						compositeDestinationState = new State(new CompositeLabel());
	 						transitionsPerState.put(t.getLabel(), compositeDestinationState);
	 					}
	 					
	 					CompositeLabel compositeDestinationLabel = (CompositeLabel)compositeDestinationState.getLabel();
	 					compositeDestinationLabel.addLabel(t.getDestination().getLabel().toString());
	 				}
	 			}
	 			
	 			for (State s : transitionsPerState.values())
	 				expandCompositeState(s);
	 			
	 			//find new states
	 			newCompositeStates.addAll(transitionsPerState.values());
	 			
	 			transitions.put(state, transitionsPerState);
			}
	 		
	 		if (newCompositeStates.size()>0)
	 			return generateTransitions(allCompositeStates, newCompositeStates, transitions);
	 		else 
	 			return transitions;
		}
		
		public Map<State,Map<Label, State>> generateTransitions(Set<State> compositeStatesToProcess, Map<State,Map<Label, State>> transitions)
		{
			return generateTransitions(new HashSet<State>(),compositeStatesToProcess,transitions);
		}
		

		public DFA determinise()
		{		
			//ensure there is exactly one starting
			State starting = new State();
			starting.setStarting(State.STARTING);
			this.getStates().add(starting);
			
			for (State s: this.getStates())
				if (s.isStarting() && !s.equals(starting))
				{
					s.setStarting(State.NONSTARTING);
					try{
						this.addTransition(new Transition(starting, Label.empty,s));
					}catch(Exception ex)
					{
						ex.printStackTrace();
					}
					
				}
			
			Set<State> compositeStates = new HashSet<State>();
			
			State startingCompositeState = new State(new CompositeLabel(starting.getLabel().toString()));
			startingCompositeState.setStarting(State.STARTING);
			
			compositeStates.add(expandCompositeState(startingCompositeState));
			
			
	 		//generate transitions
	 		Map<State,Map<Label, State>> transitions = generateTransitions(compositeStates, new HashMap<State, Map<Label,State>>());
	 		
				
	 		//generate DFA
			DFA dfa = buildDFA(transitions);
			
			return dfa;
		}
	
		//note fa1 and fa2 will be modified!
		//pass cloned FAs if you don't want this to happen!
		public static NFA sequence(FA fa1, FA fa2)throws Exception
		{
			//TODO: fill in (see examples from other similar methods)
		}
	
	
	//note fa1 and fa2 will be modified!
		//pass cloned FAs if you don't want this to happen!
		public static NFA choice(FA fa1, FA fa2)throws Exception
		{
			NFA nfa = new NFA();
			
			//create a starting and a final state
			State starting = new State(State.STARTING, State.NONFINAL);
			State finall = new State(State.NONSTARTING, State.FINAL);
			
			//first automaton
			for (State s : fa1.getStates())
			{
				//if source state
				if (s.isStarting())
				{
					s.setStarting(State.NONSTARTING);		
					nfa.addTransition(new Transition(starting, Label.empty, s));
				}
				
				//if final state
				if (s.isFinal())
				{
					s.setFinal(State.NONFINAL);		
					nfa.addTransition(new Transition(s, Label.empty, finall));
				}
			}	
			
			//second automaton
			for (State s : fa2.getStates())
			{
				//if source state
				if (s.isStarting())
				{
					s.setStarting(State.NONSTARTING);		
					nfa.addTransition(new Transition(starting, Label.empty, s));
				}
				
				//if final state
				if (s.isFinal())
				{
					s.setFinal(State.NONFINAL);		
					nfa.addTransition(new Transition(s, Label.empty, finall));
				}
			}
				
			//copy the existing transitions
			nfa.addTransitionSet(fa1.getTransitions());
			nfa.addTransitionSet(fa2.getTransitions());
			
			return nfa;
		}
	
}
