package re;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import re.RegExp.Matching;
import re.structure.Any;
import re.structure.End;
import re.structure.Not;
import re.structure.Nothing;
import re.structure.Or;
import re.structure.RE;
import re.structure.Seq;
import re.structure.Star;
import re.structure.Var;


public class REParser {
	
	
	public static final String stops = "; +()*";
	
	static Set<String> identifiers;
	
	
	public static void registerIdentifier(String identifier)
	{
		identifiers.add(identifier);
	}
	
	
	public static void processBasic(ParsingHelper ph)throws Exception
	{
		if (ph.startsWith("?"))				
		{
			ph.setTemp(new Any());
			ph.consumeString(1);
		}
		else if (ph.startsWith("1"))				
		{
			ph.setTemp(new End());
			ph.consumeString(1);
		}
		else if (ph.startsWith("0"))				
		{
			ph.setTemp(new Nothing());
			ph.consumeString(1);
		}
		else if (ph.startsWith("!"))				
		{
			ph.consumeString(1);
			
			ph.setTemp(new Not(new Var(ph.getIdentifier(stops))));
		}
		else if (ph.startsWith("("))				
		{
			int end = ph.getBracketed();
			ParsingHelper ph2 = new ParsingHelper(ph.getString().substring(1,end-1).trim());
			ph.consumeString(end);
			ph.setTemp(processRE(ph2));
		}
		else
		{
			ph.setTemp(new Var(ph.getIdentifier(stops)));
		}
		
		
		if (ph.startsWith("*"))
		{
			ph.consumeString(1);
			ph.setTemp(new Star(ph.getTemp()));
		}
	}
	
	public static void processCompound(ParsingHelper ph)throws Exception
	{
		if (ph.startsWith("+"))				
		{
			ph.consumeString(1);
			RE left = ph.getTemp();
			processBasic(ph);
			RE right = ph.getTemp();
			ph.setTemp(new Or(left,right));
			
		}
		else if (ph.startsWith(";"))				
		{
			ph.consumeString(1);
			RE left = ph.getTemp();
			processBasic(ph);
			RE right = ph.getTemp();
			ph.setTemp(new Seq(left,right));
		}
	}
	
	public static RE processRE(ParsingHelper ph)throws Exception
	{
		processBasic(ph);
		
		while (!ph.end())
		{	
			processCompound(ph);
		}
		return ph.getTemp();
	}
	
	// regexp parser
	static public ArrayList<RegExp> parseRegExps(String s) throws Exception 
	{
		ArrayList<RegExp> regExps = new ArrayList<RegExp>();
		s=s.trim();		
		
        //loop over automata
		while (s.length()!=0) {
			
			System.out.println("Parsing regular expression");
			
			//temporary variables
			Matching matching = null;
			String foreach_type = null;
			String foreach_variable = null;
			
			// Property
			Integer index_property = s.indexOf("property");
			if (index_property==-1) throw (new Exception("property keyword not found"));

			s = s.substring(index_property+8).trim();
			

			// Foreach (optional)
			if (s.startsWith("foreach")) //note that the foreach keyword is optional
			{
				s = s.substring(7).trim();
				
				//now expecting the kind of binding
				//currently only supporting "Target" binding
				Integer index_target = s.indexOf("target");
				if (index_target==-1) throw (new Exception("target keyword not found"));

				s = s.substring(index_target+6).trim();
				
				
				// Foreach type
				Integer index_opening_bracket = s.indexOf("(");
				if (index_opening_bracket==-1) throw (new Exception("Opening bracket not found"));
				
				s = s.substring(1).trim();
				
				Integer index_space_separator = s.indexOf(" ");
				if (index_space_separator==-1) throw (new Exception("Type or variable name not found"));
				
				foreach_type = s.substring(0,index_space_separator).trim();
				System.out.println("  - Foreach type is ["+foreach_type+"]");

				s = s.substring(index_space_separator+1).trim();
				
				
				// Foreach variable
				Integer index_closing_bracket = s.indexOf(")");
				if (index_closing_bracket==-1) throw (new Exception("Closing bracket not found"));
				
				foreach_variable = s.substring(0,index_closing_bracket).trim();
				System.out.println("  - Foreach variable is ["+foreach_variable+"]");

				s = s.substring(index_closing_bracket+1).trim();
				
			}	


			// starting state
			
			if (s.startsWith("not matching"))//NOT matching
			{
				matching = Matching.UNEXPECT;
				s = s.substring(12).trim();
			}
			else if (s.startsWith("matching"))//matching
			{
				matching = Matching.EXPECT;
				s = s.substring(8).trim();
			}
			else throw (new Exception("(not) matching keyword not found"));


			
			if (!s.startsWith("{")) throw (new Exception("{ not found"));

			Integer index_end_regexp = s.indexOf("}");
			if (index_end_regexp==-1) throw (new Exception("} not found"));

			String regexpString = s.substring(1,index_end_regexp).trim();

			s=s.substring(index_end_regexp+1).trim();//consume regexp and closing bracket

			
			identifiers = new HashSet<String>();//refresh the identifiers registry
			
			//two pass parsing
			processRE(new ParsingHelper(regexpString));//first pass to gather identifiers
			//second pass
			RE re = processRE(new ParsingHelper(regexpString));
					
			System.out.println("Parsed: " + re);
			
			regExps.add(new RegExp(re, matching, foreach_type, foreach_variable, identifiers));

		}
			
		return regExps;
	}
		
		
}

