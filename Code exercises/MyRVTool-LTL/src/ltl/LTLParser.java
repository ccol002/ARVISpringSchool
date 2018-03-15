package ltl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import ltlstructure.Globally;
import ltlstructure.And;
import ltlstructure.Equivalence;
import ltlstructure.Eventually;
import ltlstructure.Implies;
import ltlstructure.LTL;
import ltlstructure.Next;
import ltlstructure.Not;
import ltlstructure.Or;
import ltlstructure.Prop;
import ltlstructure.Until;
import ltl.LTLExp.Matching;



public class LTLParser {
	
	
	public static final String stops = " ()";
	
	static Set<String> identifiers;
	
	
	public static void registerIdentifier(String identifier)
	{
		identifiers.add(identifier);
	}
	
	
	public static void processBasic(ParsingHelper ph)throws Exception
	{
		if (ph.startsWith("not") || ph.startsWith("Not"))				
		{
			ph.consumeString(3);
			
			ph.setTemp(new Not(processLTL(ph)));
		}
		else if (ph.startsWith("next") || ph.startsWith("Next"))				
		{
			ph.consumeString(4);
			
			ph.setTemp(new Next(processLTL(ph)));
		}
		else if (ph.startsWith("globally") || ph.startsWith("Globally"))				
		{
			ph.consumeString(8);
			
			ph.setTemp(new Globally(processLTL(ph)));
		}
		else if (ph.startsWith("eventually") || ph.startsWith("Eventually"))				
		{
			ph.consumeString(10);
			
			ph.setTemp(new Eventually(processLTL(ph)));
		}
		else if (ph.startsWith("("))				
		{
			int end = ph.getBracketed();
			ParsingHelper ph2 = new ParsingHelper(ph.getString().substring(1,end-1).trim());
			ph.consumeString(end);
			ph.setTemp(processLTL(ph2));
		}
		else
		{
			ph.setTemp(new Prop(ph.getIdentifier(stops)));
		}
	}
	
	public static void processCompound(ParsingHelper ph)throws Exception
	{
		if (ph.startsWith("until") || ph.startsWith("Until"))				
		{
			ph.consumeString(5);
			LTL left = ph.getTemp();
			processBasic(ph);
			LTL right = ph.getTemp();
			ph.setTemp(new Until(left,right));
			
		}
		else if (ph.startsWith("and") || ph.startsWith("And"))				
		{
			ph.consumeString(3);
			LTL left = ph.getTemp();
			processBasic(ph);
			LTL right = ph.getTemp();
			ph.setTemp(new And(left,right));
		}
		else if (ph.startsWith("or") || ph.startsWith("Or"))				
		{
			ph.consumeString(2);
			LTL left = ph.getTemp();
			processBasic(ph);
			LTL right = ph.getTemp();
			ph.setTemp(new Or(left,right));
		}
		else if (ph.startsWith("=>"))				
		{
			ph.consumeString(2);
			LTL left = ph.getTemp();
			processBasic(ph);
			LTL right = ph.getTemp();
			ph.setTemp(new Implies(left,right));
		}
		else if (ph.startsWith("<=>"))				
		{
			ph.consumeString(3);
			LTL left = ph.getTemp();
			processBasic(ph);
			LTL right = ph.getTemp();
			ph.setTemp(new Equivalence(left,right));
		}
	}
	
	public static LTL processLTL(ParsingHelper ph)throws Exception
	{
		processBasic(ph);
		
		while (!ph.end())
		{	
			processCompound(ph);
		}
		return ph.getTemp();
	}
	
	// ltl parser
	static public ArrayList<LTLExp> parseLtlExps(String s) throws Exception 
	{
		ArrayList<LTLExp> ltlExps = new ArrayList<LTLExp>();
		s=s.trim();		
		
        //loop over formulae
		while (s.length()!=0) {
			
			System.out.println("Parsing LTL formula");
			
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

			Integer index_end_ltl = s.indexOf("}");
			if (index_end_ltl==-1) throw (new Exception("} not found"));

			String ltlString = s.substring(1,index_end_ltl).trim();

			s=s.substring(index_end_ltl+1).trim();//consume regexp and closing bracket

			
			identifiers = new HashSet<String>();//refresh the identifiers registry
			
			//two pass parsing
			processLTL(new ParsingHelper(ltlString));//first pass to gather identifiers
			//second pass
			LTL ltl = processLTL(new ParsingHelper(ltlString));
					
			System.out.println("Parsed: " + ltl);
			
			ltlExps.add(new LTLExp(ltl, matching, foreach_type, foreach_variable, identifiers));

		}
			
		return ltlExps;
	}
		
		
}

