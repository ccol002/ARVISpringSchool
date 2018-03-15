package automata;

import java.util.ArrayList;
import java.util.Iterator;

public class Transition {

	
	// The event part
		private String event_name;
		private ArrayList<String> event_parameter_vars;
		private ArrayList<String> event_parameter_types;
		private String target_type;
		private String target_var;
		
		private String condition;
		private String action;

		private String source_state;
		private String destination_state;
		private String destination_text;
		
		// Constructors
		// Rule with all values
		public Transition(
				String source_state,
				String destination_state,
				String event, 
				ArrayList<String> vars, ArrayList<String> types, 
				String target_var, String target_type,
				String condition, String action,
				String destination_text
		) 
		{
			this.source_state = source_state;
			this.destination_state = destination_state;
			this.event_name = event;
			this.event_parameter_types = types;
			this.event_parameter_vars = vars;
			this.target_type = target_type;
			this.target_var = target_var;
			this.condition = condition;
			this.action = action;
			this.destination_text = destination_text;
		}
		// Automaton with parameters but no target
		public Transition(
				String source_state,
				String destination_state,
				String event,
				ArrayList<String> vars, ArrayList<String> types,
				String condition, String action,
				String destination_text
		)
		{
			this(source_state, destination_state, event, vars, types, null, null, condition, action, destination_text);
		}
		
		// Automaton with no target and no parameters	
		public Transition(String source_state, String destination_state,
				String event, String condition, String action, 
				String destination_text) 
		{
			this(source_state, destination_state, event, new ArrayList<String> (), new ArrayList<String> (), null, null, condition, action, destination_text);
		}

		// Getters
		public String getSourceState()
		{
			return source_state;
		}
		public String getDestinationState()
		{
			return destination_state;
		}
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
			
			// Source state
			result = source_state + ">>>";
			
			// Event + parameters (or ..)
			result += event_name+listParametersWithTypes();
			// Target
			if (target_type != null) {
				result += " target ("+target_type+" "+target_var+")";
			}
			// Guard
			result += " | { "+condition+" }";
			// Action
			result += " -> { "+action+" }";

			// Destination state
			result += ">>>" + destination_state;
			
			return(result);
		}
		
		public String toAspectJ(String id, String foreach_type) throws Exception
		{
			
			String result = "\n\nbefore ";
			
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

			if (foreach_type==null)
			{
				result += 
					" {\n"+
					"    if ( checkMonitor"+id+"(\"" + this.source_state + "\")"
							+ " && "+getCondition()
							// inserted the change of state in the condition to avoid messing with the action
							+" && (setMonitor"+id+"(\"" + this.destination_state + "\"))) {";
				
			
			}
			else
			{
				
				if ((target_type == null && foreach_type != null) || !target_type.equals(foreach_type)) 
					throw new Exception("Target type mismatch! " + target_type + " <> " + foreach_type);
				result +=" {\n"+
						"    if ( checkMonitor"+id+"(" + getTargetVariable() + ", \""+ this.source_state + "\")"
						+ " && "+getCondition()
						// inserted the change of state in the condition to avoid messing with the action
						+" && (setMonitor"+id+"("+getTargetVariable()+", \"" + this.destination_state + "\"))) {";
				
			}
			
			//display text related to destination state
			if (destination_text.length()>0)
				result += "\n System.out.println(\""+destination_text+"\");";
			
			
			result += getAction()+ "}\n  }";
			return result;
		}

	
}





