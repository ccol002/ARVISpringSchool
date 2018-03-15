package automata;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
	
	
	public static void main(String [] args) 
	{
		String userPath = "/Users/christiancolombo/Dropbox/repositories/RV-course-repository/";
				
		//Gordon
		//	"/Users/gordonpace/Documents/Dropbox/Data/Current Work/> Papers/InProgress/Book-RV/";
		
		String coursePath = "Courses/02 UoM Full Course";
		
		//Gordon
		//  "Buenos-Aires-Course/";
		
		String topicPath = "/Code/Solutions/FinancialTransactions-04-Automata-Solution";
		
		//Gordon
		//  "/Code/FiTS/Code/FinancialTransactions-03-GCL-Solution";
		
		try {
			AutomataScript script = new AutomataScript(userPath + coursePath + topicPath
					+ "/src/script/properties.rs");
					
				//	"/Users/gordonpace/Documents/Dropbox"
				//	+ "/Data/Current Work/> Papers/InProgress/Book-RV/Code"
				//	+ "/FiTS/Code/FinancialTransactions-03-GCL-Solution/src/properties.rs");
			try {
		          File file_java = new File(userPath + coursePath + topicPath
							+ "/src/transactionsystem/Verification.java");
		        		  
		          BufferedWriter output = new BufferedWriter(new FileWriter(file_java));
		          output.write(script.getAuxiliaryCode());
		          output.close();

		          File file_aj = new File(userPath + coursePath + topicPath
							+ "/src/transactionsystem/Properties.aj");
		          output = new BufferedWriter(new FileWriter(file_aj));
  		  		  output.write(script.toAspectJ());
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
