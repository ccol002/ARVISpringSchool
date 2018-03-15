package automata;

import java.util.ArrayList;

public class Automaton {
	
	private ArrayList<Transition> transitions;
	
	private String start_state;
	private String foreach_type;
	private String foreach_variable;
	
	
	public Automaton(String start_state,
			ArrayList<Transition> transitions,
			String foreach_type,
			String foreach_variable)
	{
		this.start_state = start_state;
		this.transitions = transitions;
		this.foreach_type = foreach_type;
		this.foreach_variable = foreach_variable;
	}
	
	
	public Automaton(String start_state,ArrayList<Transition> transitions)
	{
		this(start_state,transitions,null, null);
	}
	
	//getters
	public String getStartState()
	{
		return start_state;
	}
	
	public String getForeachType()
	{
		return foreach_type;
	}
	
	public String getForeachVariable()
	{
		return foreach_variable;
	}
	
	//tostring
	public String toString()
	{
		String result = "property ";
		
		if (foreach_type != null)
		{
			result += "foreach target (" + foreach_type + " " + foreach_variable + ") ";
		}
		
		result += "starting " + start_state + "{ ";
		
		for (Transition t : transitions)
			result += t.toString();
		
		result += "}";
		
		return result;
		
	}
	
	
	//toAJ
	public String toAspectJ() throws Exception
	{
		
		String result = "\n\n// automaton starts here";
		
		String id = super.toString().substring(19);
		
		//checkMonitor function
		if (foreach_type == null)
		{			
			//initial state
			result += "\nstatic String monitorState"+id+" = null;"
					+ "\nstatic void init" +id+ "(){ monitorState"+id+" = \""+start_state+"\"; }\n\n";
			
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
					+ "\n  monitors"+id+".put(target, state);"
					+ "\n  return state.equals(state); }"
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
		
		
		
		for (Transition t : transitions)
			result += t.toAspectJ(id, foreach_type);
		
		
		
		return result;
	}
	
	public String getInit()
	{
		return "init" +super.toString().substring(19)+"(); ";
	}
	
	
}
