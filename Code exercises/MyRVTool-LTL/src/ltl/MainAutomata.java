package ltl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import ltlstructure.LTL;
import ltlstructure.Not;
import ltl.automata.AlternatingBuchiAutomaton;
import ltl.automata.DeterministicAutomaton;
import ltl.automata.NonDeterministicAutomaton;
import ltl.automata.ProductAutomaton;


public class MainAutomata {
	
	
	public static void main(String [] args) 
	{

		String userPath = "/Users/christiancolombo/Dropbox/Gordon/Courses/RV summer school 2016/Code/Solutions/";
		
		String coursePath = "";
		
		String topicPath = "06-LTL-Solution";
		

		try {
			LTLScript script = new LTLScript(userPath + coursePath + topicPath
					+ "/src/script/properties.rs");
					
				
			try {
		          File file_java = new File(userPath + coursePath + topicPath
							+ "/src/transactionsystem/Verification.java");
		        		  
		          BufferedWriter output = new BufferedWriter(new FileWriter(file_java));
		          output.write(script.getAuxiliaryCode());
		          output.close();

		          //this is purely for display purposes
		          for (LTLExp ltl : script.getLTLExps())
		          {
		        	    System.out.println("\n");
		        	  	
		        	    LTL formula = ltl.getLTL().rewrite().negationNormalForm();
		        	    
		        	    
		        	    System.out.println("LTL formula: " + ltl.getMatching() +" " + formula);
		        	  	
		        	    
		        	    AlternatingBuchiAutomaton aba = new AlternatingBuchiAutomaton(formula);
		        	    NonDeterministicAutomaton nda = new NonDeterministicAutomaton(aba);
		        	    DeterministicAutomaton da = new DeterministicAutomaton(nda);
		        	 
		        	    
		        	  	
		        	    ProductAutomaton pa = new ProductAutomaton(da, 
		        	    		/*TODO add reference to deterministic automaton representing negated formula*/);
		        	   
		        	    
		        	  	System.out.println("Automaton: " + pa);
		        	  	
		        	  	System.out.println("\n");
		          }
		          
		          
		          File file_aj = new File(userPath + coursePath + topicPath
							+ "/src/transactionsystem/Properties.aj");
		          output = new BufferedWriter(new FileWriter(file_aj));
  		  		  output.write(script.toAspectJAutomata());
		          output.close();
		          

			} catch ( IOException e ) {
		           e.printStackTrace();
		        }
		} catch (Exception msg) {
			System.out.println("Error: ");
			msg.printStackTrace();
		}
	}
	
}
