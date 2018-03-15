package re.test;

import org.junit.Before;
import org.junit.Test;

import re.automata.CompositeLabel;
import re.automata.DFA;
import re.automata.Label;
import re.automata.NFA;
import re.automata.State;
import re.automata.Transition;

public class Determinism {
	
	@Before
	public void resetIdGenerator()
	{
		State.resetIdGenerator();
	}

	@Test
	public void basicTest()throws Exception {
		
		System.out.println("Testing a basic determinisation ");
		
		NFA nfa= new NFA();
		
		State starting = new State(State.STARTING, State.NONFINAL);
		
		State state1 = new State();
		State state2 = new State();
		
		Label l = new Label("a");
		
		nfa.addTransition(new Transition(starting, l, state1));
		nfa.addTransition(new Transition(starting, l, state2));
		
		DFA dfa = nfa.determinise();
		
		System.out.println("Non-deterministic automaton: "+nfa);
		System.out.println("The equivalent deterministic automaton: "+dfa);
		
		assert(dfa.getTransitions().size()==1);
		assert(dfa.getStates().size()==2);
	}
	
	@Test
	public void finalStateTest()throws Exception {
		
		System.out.println("Testing a determinisation involving a final state");
		
		NFA nfa= new NFA();
		
		State starting = new State(State.STARTING, State.NONFINAL);
		
		State state1 = new State(State.NONSTARTING, State.FINAL);
		State state2 = new State();
		
		Label l = new Label("a");
		
		nfa.addTransition(new Transition(starting, l, state1));
		nfa.addTransition(new Transition(starting, l, state2));
		
		DFA dfa = nfa.determinise();
		
		System.out.println("Non-deterministic automaton: "+nfa);
		System.out.println("The equivalent deterministic automaton: "+dfa);
		
		assert(dfa.getTransitions().size()==1);
		assert(dfa.getStates().size()==2);
		
		
		int cnt = 0;
		for (State s : dfa.getStates())
			if (s.equals(new State(new CompositeLabel("1","2"))))
				if (s.isFinal())
					cnt++;
		assert(cnt==1);
	}
	
	@Test
	public void startStateTest()throws Exception {
		
		System.out.println("Testing a determinisation involving multiple start states");
		
		NFA nfa= new NFA();
		
		State starting = new State(State.STARTING, State.NONFINAL);
		
		State state1 = new State(State.STARTING, State.FINAL);
		State state2 = new State();
		
		Label l = new Label("a");
		
		nfa.addTransition(new Transition(starting, l, state1));
		nfa.addTransition(new Transition(starting, l, state2));
		
		DFA dfa = nfa.determinise();
		
		System.out.println("Non-deterministic automaton: "+nfa);
		System.out.println("The equivalent deterministic automaton: "+dfa);
		
		assert(dfa.getTransitions().size()==1);
		assert(dfa.getStates().size()==2);
		
		
		//both should be final
		for (State s : dfa.getStates())
			assert(s.isFinal());
	}

}
