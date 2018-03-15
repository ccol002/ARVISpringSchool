package re;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import re.automata.DFA;
import re.automata.NFA;

public class MainAutomata {
	
	
	public static void main(String [] args) 
	{
		String userPath = "/Users/christiancolombo/Dropbox/Gordon/Courses/RV summer school 2016/Code/As given to students/FiTS/";
		
		String coursePath = "";
		
		
		String topicPath = "05-REs";
		
		

		try {
			REScript script = new REScript(userPath + coursePath + topicPath
					+ "/src/script/properties.rs");
					
			try {
		          File file_java = new File(userPath + coursePath + topicPath
							+ "/src/transactionsystem/Verification.java");
		        		  
		          BufferedWriter output = new BufferedWriter(new FileWriter(file_java));
		          output.write(script.getAuxiliaryCode());
		          output.close();
		          
		          
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
