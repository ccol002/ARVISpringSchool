package re;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

public class REScript {

	// Data
	private ArrayList<RegExp> regExps;
	private String auxiliaryCode;
	private String prelude;
	
	// Constructors
	public REScript() 
	{
		regExps = new ArrayList<RegExp>();
		auxiliaryCode = "";
		prelude = "";
	}
	public REScript(String filename) throws Exception
	{
		String re_txt = "";
		String code_txt = "";
		String prelude_txt = "";
		Integer readMode = 0; 
		// 0=before VERIFICATIONCODE, 1=in VERIFICATIONCODE, 2=in PRELUDE, 3=in RULES
				
		BufferedReader br = new BufferedReader(new FileReader(filename));
	    try {
	        String line = br.readLine();

	        while (line != null) {
	        	line = line.trim();

	        	switch (readMode) {
	        	case 0:
	        		if (line.equals("VERIFICATIONCODE")) {
	        			readMode = 1;
	        		} else
	        		if (!(line.equals("")  || line.startsWith("//"))) { 
	        			throw (new Exception("Non-comment line before VERIFICATIONMODULE")); 
	        		}
	        		break;
	        	case 1:
	        		if (line.equals("PRELUDE")) {
	        			readMode = 2;
	        		} else {
	        			code_txt += line+"\n";
	        		}
	        		break;
	        	case 2:
	        		if (line.equals("REGULAREXPRESSIONS")) {
	        			readMode = 3;
	        		} else {
	        			prelude_txt += line+"\n";
	        		}
	        		break;
	        	case 3:
	        		if (!line.startsWith("//")) {
	        			re_txt += line+" ";
	        		}	        		
	        	}
	            line = br.readLine();
	        }
	    } finally {
	        br.close();
	    }
    	if (readMode < 3) 
    		throw (new Exception("Missing parts of REGULAREXPRESSIONS script")); 
	    
	    auxiliaryCode = code_txt;
	    prelude = prelude_txt;
	    regExps = REParser.parseRegExps(re_txt);
	}
	
	// Getters
	public ArrayList<RegExp> getRegExps() 
	{ 
		return regExps; 
	}
	public String getAuxiliaryCode() 
	{
		return auxiliaryCode;
	}
	public String getPrelude()
	{
		return prelude;
	}
	
	
	// Setters
	public void setAuxiliaryCode(String s)
	{ 
		auxiliaryCode = s;
	}
	public void addRegExp(RegExp regExp) 
	{
		regExps.add(regExp);
	}
	public void setPrelude(String s)
	{
		prelude = s;
	}
	
	// Pretty printing
	public String toString() 
	{
		String result = auxiliaryCode + "\n\nREGULAREXPRESSIONS\n\n";

		Iterator<RegExp> iterator = regExps.iterator();
		while (iterator.hasNext()) {
			result += iterator.next().toString()+";\n";
		}
		
		return result;
	}
	
	
	public String toAspectJAutomata() throws Exception
	{
		String result = 
				prelude+"\n\npublic aspect Properties {\n";
		
        for (RegExp a : regExps){
      	  result += a.toAspectJAutomata()+"\n\n";
        }
        
        
        result += "public static void initialise() {";
        for (RegExp a : regExps){
      	  result += a.getInit()+"\n";
        }
        
		result += "\n}\n}\n";
		
        return result;
	}

	public String toAspectJResiduals() throws Exception
	{
		String result = 
				prelude+"\n\npublic aspect Properties {\n";
		
        for (RegExp a : regExps){
      	  result += a.toAspectJResiduals()+"\n\n";
        }
        
        
        result += "public static void initialise() {";
        for (RegExp a : regExps){
      	  result += a.getInit()+"\n";
        }
        
		result += "\n}\n}\n";
        
	
		
        return result;
	}
}