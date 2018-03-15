package ltl;


import ltlstructure.LTL;

public class ParsingHelper {

	private String s;
	private LTL temp;
	

	public ParsingHelper(String s)
	{
		this.s = s;
	}
	
	public String consumeString(int len)
	{
		s=s.substring(len).trim();
		return s;
	}
	
	public boolean startsWith(String test)
	{
		return s.startsWith(test);
	}
	
	public boolean end()
	{
		return s.length()==0;
	}
	
	public String getString ()
	{
		return s;
	}
	
	public LTL getTemp()
	{
		return temp;
	}
	
	public void setTemp(LTL temp)
	{
		this.temp = temp;
	}
	
	public String getIdentifier(String stops)
	{
		int i = 0;
		while (i<s.length() && !stops.contains(Character.toString(s.charAt(i))))
		{ 
			i++;
		}
		
		String identifier = s.substring(0, i);
		consumeString(i);
		
		LTLParser.registerIdentifier(identifier);
		
		return identifier;
	}
	
	
	// The initial string enclosed between '(' and ')'.
		// Throws an exception if (i) the string does not start with '('; or (i) contains 
		// unbalanced or unclosed delimiters.
	 public Integer getBracketed() throws Exception
		{
			Integer balance = 0, i;

			if (s.charAt(0) != '(') throw (new Exception("Action not enclosed in brackets"));
			
			for (i=0; i<s.length(); i++) {
				switch (s.charAt(i)) {
				case '(': 
					balance++; break;
				case ')': 
					if (balance == 0) { throw (new Exception("Unbalanced '(' and ')' in automaton")); } 
					if (balance == 1) { return (i+1); }
					balance--; break;
				}

			}
			throw (new Exception("Unclosed '(' in regular expression"));
		}
	
	 public String toString()
	 {
		 return "Processing: "+ temp + " ... " + s;
	 }

	
}
