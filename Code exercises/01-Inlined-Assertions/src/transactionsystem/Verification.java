package transactionsystem;

public class Verification {

	
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
	}

}
