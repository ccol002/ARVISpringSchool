package transactionsystem;

import java.util.ArrayList;

public class Verification {

	static public ArrayList<String> approvedAccounts;
	
	
	static public void assertion(boolean condition, String message)
	{
		if (!condition) System.out.println("*** "+message+" ***");
	}

	private Verification() 
	{ 
		initialiseVerification(); 
	}
	

	static public void initialiseVerification()
	{
		approvedAccounts = new ArrayList<String>();
	}

	
	

}
