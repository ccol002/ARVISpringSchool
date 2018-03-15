package gcl;

import java.util.ArrayList;
import java.util.Iterator;

public class Rule {
	// The event part
	private String event_name;
	private ArrayList<String> event_parameter_vars;
	private ArrayList<String> event_parameter_types;
	private String target_type;
	private String target_var;
	
	private String condition;
	private String action;

	// Constructors
	// Rule with all values
	public Rule(
			String event, 
			ArrayList<String> vars, ArrayList<String> types, 
			String target_var, String target_type,
			String condition, String action
	) 
	{
		this.event_name = event;
		this.event_parameter_types = types;
		this.event_parameter_vars = vars;
		this.target_type = target_type;
		this.target_var = target_var;
		this.condition = condition;
		this.action = action;
	}
	// Rule with parameters but no target
	public Rule(
			String event,
			ArrayList<String> vars, ArrayList<String> types,
			String condition, String action
	)
	{
		this(event, vars, types, null, null, condition, action);
	}
	// Rule with no target and no parameters	
	public Rule(String event, String condition, String action) 
	{
		this(event, new ArrayList<String> (), new ArrayList<String> (), null, null, condition, action);
	}

	// Getters
	public ArrayList<String> getParameterVariables() 
	{ 
		return event_parameter_vars;
	}
	public ArrayList<String> getParameterTypes()
	{
		return event_parameter_types;
	}
	public String getTargetType()
	{
		return target_type;
	}
	public String getTargetVariable()
	{
		return target_var;
	}
	public String getEvent() 
	{ 
		return event_name; 
	}
	public String getCondition() 
	{ 
		return condition; 
	}
	public String getAction() 
	{ 
		return action; 
	}

	// To string
	private String listTargetAndParametersWithTypes()
	{
		if (event_parameter_types==null) return "(..)";

		String result = "("+target_type+" "+target_var; 

		Iterator<String> iteratorTypes = event_parameter_types.iterator();
		Iterator<String> iteratorVars  = event_parameter_vars.iterator();
		while (iteratorVars.hasNext()) {
		    result+=", " + iteratorTypes.next() + " " + iteratorVars.next();
		}
		result+=")";
		return result;		

	}
	private String listParametersWithoutTypes()
	{
		if (event_parameter_types==null) return "(..)";

		String result = "("; Boolean first = true;
		Iterator<String> iteratorVars  = event_parameter_vars.iterator();
		while (iteratorVars.hasNext()) {
			if (!first) { result+=", "; } else { first=false; }
		    result += iteratorVars.next();
		}
		result+=")";
		return result;
	}
	private String listParametersWithTypes()
	{
		if (event_parameter_types==null) return "(..)";

		String result = "("; Boolean first = true;
		Iterator<String> iteratorTypes = event_parameter_types.iterator();
		Iterator<String> iteratorVars  = event_parameter_vars.iterator();
		while (iteratorVars.hasNext()) {
		    if (!first) { result+=", "; } else { first=false; }
		    result += iteratorTypes.next() + " " + iteratorVars.next();
		}
		result+=")";
		return result;		
	}
	public String toString() {
		String result;
		
		// Event + parameters (or ..)
		result = event_name+listParametersWithTypes();
		// Target
		if (target_type != null) {
			result += " target ("+target_type+" "+target_var+")";
		}
		// Guard
		result += " | { "+condition+" }";
		// Action
		result += " -> { "+action+" }";

		return(result);
	}
	public String toAspectJ()
	{
		String result = "before ";
		
		if (getTargetVariable() == null && getParameterVariables() == null) {
			result += "()";
		} else
		if (getTargetVariable() == null) {
			result += listParametersWithTypes();
		} else 
		if  (getParameterVariables() == null) {
			result += "("+getTargetType()+" "+getTargetVariable()+")";
		} else {
			result += listTargetAndParametersWithTypes();
		}
		result += ": call(* "+getEvent()+"(..))";
		
		if (getParameterVariables() != null) {
			result += " &&\n    args"+listParametersWithoutTypes();
		}
		if (getTargetVariable() != null) {
			result += " &&\n    target("+getTargetVariable()+")";
		}

		//TODO add condition and action
	}

}
