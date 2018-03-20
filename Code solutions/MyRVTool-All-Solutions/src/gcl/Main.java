package gcl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
	public static void main(String [] args) 
	{
		String path = "/Users/christiancolombo/Uni Drive/repositories/ARVISpringSchool/Code solutions/03-GCL-Solution/src";
		try {
			GCLScript script = new GCLScript(path + "/script/properties.rs");
			try {
		          File file_java = new File(path + "/transactionsystem/Verification.java");
		      
		          BufferedWriter output = new BufferedWriter(new FileWriter(file_java));
		          output.write(script.getAuxiliaryCode());
		          output.close();

		          File file_aj = new File(path + "/transactionsystem/Properties.aj");
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
