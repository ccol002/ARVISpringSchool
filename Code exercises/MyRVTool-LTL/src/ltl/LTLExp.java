package ltl;

import java.util.Set;

import ltl.automata.AlternatingBuchiAutomaton;
import ltl.automata.DeterministicAutomaton;
import ltl.automata.NonDeterministicAutomaton;
import ltl.automata.ProductAutomaton;
import ltl.automata.ProductRho;
import ltlstructure.LTL;
import ltlstructure.Not;

public class LTLExp {
	
	public enum Matching {EXPECT, UNEXPECT};
	
	private LTL ltl;	
	private Matching matching;
	
	private String foreach_type;
	private String foreach_variable;
	
	private Set<String> identifiers;
	
	public LTLExp(LTL ltl,
			Matching matching,
			String foreach_type,
			String foreach_variable,
			Set<String> identifiers)
	{
		this.ltl = ltl;
		this.matching = matching;
		this.foreach_type = foreach_type;
		this.foreach_variable = foreach_variable;
		this.identifiers = identifiers;
	}
	
	
	public LTLExp(LTL ltl, Matching matching,
			Set<String> identifiers)
	{
		this(ltl, matching, null, null,identifiers);
	}
	
	//getters

	public LTL getLTL()
	{
		return ltl;
	}
	
	
	public Matching getMatching()
	{
		return matching;
	}
	
	public String getForeachType()
	{
		return foreach_type;
	}
	
	public String getForeachVariable()
	{
		return foreach_variable;
	}
	
	public Set<String> getIdentifiers()
	{
		return identifiers;
	}
	
	//tostring
	public String toString()
	{
		String result = "property ";
		
		if (foreach_type != null)
		{
			result += "foreach target (" + foreach_type + " " + foreach_variable + ") ";
		}
		
		result += "{ "+ matching + " " + ltl + "}";
		
		return result;
		
	}
	
	
	//toAJ
	public String toAspectJAutomata() throws Exception
	{
		
		String result = "\n\n// the code for "+matching+": " + ltl.toString();
		
		//obtain a unique number
		String id = super.toString().substring(11);
		
		//obtain automaton
		
		LTL formula = ltl.negationNormalForm();
	    
	    AlternatingBuchiAutomaton aba = new AlternatingBuchiAutomaton(formula);
	    NonDeterministicAutomaton nda = new NonDeterministicAutomaton(aba);
	    DeterministicAutomaton da = new DeterministicAutomaton(nda);
	    
	    LTL _formula = new Not(ltl).negationNormalForm();
	    
	    AlternatingBuchiAutomaton _aba = new AlternatingBuchiAutomaton(_formula);
	    NonDeterministicAutomaton _nda = new NonDeterministicAutomaton(_aba);
	    DeterministicAutomaton _da = new DeterministicAutomaton(_nda);
	  	
	    ProductAutomaton pa = new ProductAutomaton(da, _da);
	   
	    
		if (!matching.equals(Matching.EXPECT))
			throw new Exception("Only Matching expressions are supported for the time being");
		
		//checkMonitor function
		if (foreach_type == null)
		{			
			//initial state
			result += "\nstatic String monitorState"+id+" = null;"
					+ "\nstatic void init" +id+ "(){ "
					+ "\n monitorState"+id+" = \""+pa.getStartStateQ0().toString()+"\"; "
				//	+ "\n System.out.println(\" INITIALISED: "+matching+" "+re+"\"); "
							+ "}\n\n";
			
			result += "\n\nstatic boolean checkMonitor"+id+"(String state) { return state.equals(monitorState"+id+"); }";
		}
		else
		{   
			//Hashmap
			result += "\nstatic HashMap<"+foreach_type+", String> monitors"+id+" = null;";
			
			result +=  "\nstatic void init" +id+ "(){ monitors"+id+ "= new HashMap<"+foreach_type+", String>(); }\n\n";
			
			result += "\n\nstatic boolean checkMonitor"+id+"("+foreach_type+" target, String state) { "
					+ "\n if (monitors"+id+".containsKey(target))"
					+ "\n  return state.equals(monitors"+id+".get(target));"
					+ "\n else {"
					+ "\n  monitors"+id+".put(target, \""+pa.getStartStateQ0().toString()+"\");"
					+ "\n  return state.equals(\""+pa.getStartStateQ0().toString()+"\"); }"
					+ "\n }";
		}
		
		
		//setMonitor function
		if (foreach_type == null)
		{			
			result += "\n\nstatic boolean setMonitor"+id+"(String state) { "
					+ "\n  monitorState"+id+"=state; "
					+ "\n  return true; }";
			
			
		}
		else
		{   
			result += "\n\nstatic boolean setMonitor"+id+"("+foreach_type+" target, String state) { "
					+ "\n if (monitors"+id+".containsKey(target))"
					+ "\n { monitors"+id+".put(target,state);"
					+ "\n   return true; "
					+ "\n }"
					+ "\n else {"
					+ "\n  return false; }"
					+ "\n }";
		}
		
		
		
		for (String l : this.identifiers)
		{
			
			result += "\n\nbefore ";
			
			if (foreach_variable == null)
				result += "()";
		    else
				result += "("+foreach_type+" "+foreach_variable+")";
			
			result += ": call(* *."+l+"(..))";
			
			if (foreach_variable != null) {
				result += " &&\n    target("+foreach_variable+")";
			}

			result +=  "{ \n if (1==0) {}";			
			
			for (Set<String> state_names : pa.getTransitions().keySet())
				for (ProductRho pr : pa.getTransitions().get(state_names))
					if (pr.getSetOfProps().contains(l))
					{
				
						if (foreach_type==null)
					
							result += 
						
							"\n   else if ( checkMonitor"+id+"(\"" + state_names + "\")"
								// inserted the change of state in the condition to avoid messing with the action
								+"\n && (setMonitor"+id+"(\"" + pr.getNextState() + "\"))) {";
					

				else
					
					result +=
							"\n   else if ( checkMonitor"+id+"(" + foreach_variable + ", \""+ state_names + "\")"
							// inserted the change of state in the condition to avoid messing with the action
							+"\n && (setMonitor"+id+"("+foreach_variable+", \"" + pr.getNextState() + "\"))) {";
					

				
				//display text related to destination state
			//	result += "\n System.out.println(\""+matching+" "+re+" REACHED STATE: "+t.getDestination()+"\");";
				
				//fail on bad states
				if (pa.getBadStates().contains(pr.getNextState()))
					result += "\n Verification.fail(\" FAILED ON LTL "+matching+" "+ltl+"\");";
				
				result += "}\n";
			}
			result += "}\n";
		}
		return result;
	
	}
	

	//return a unique string for this object
	public String getInit()
	{
		return "init" +super.toString().substring(11)+"(); ";
	}
	
	
}
