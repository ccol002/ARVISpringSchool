package gcl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
	public static void main(String [] args) 
	{
		try {
			GCLScript script = new GCLScript("/Users/christiancolombo/Dropbox/Gordon/Courses/RV summer school 2016/Code/As given to students/FiTS/03-GCL/src/properties.rules");
			try {
		          File file_java = new File("/Users/christiancolombo/Dropbox/Gordon/Courses/RV summer school 2016/Code/As given to students/FiTS/03-GCL/src/transactionsystem/Verification.java");;
		      
		          BufferedWriter output = new BufferedWriter(new FileWriter(file_java));
		          output.write(script.getAuxiliaryCode());
		          output.close();

		          File file_aj = new File("/Users/christiancolombo/Dropbox/Gordon/Courses/RV summer school 2016/Code/As given to students/FiTS/03-GCL/src/transactionsystem/Properties.aj");
		          output = new BufferedWriter(new FileWriter(file_aj));
  		  		  output.write(script.toAspectJ());
		          output.close();

			} catch ( IOException e ) {
		           e.printStackTrace();
		        }
		} catch (Exception msg) {
			System.out.println("Error: "+msg);
		}
	}
	
}
