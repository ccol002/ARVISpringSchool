VERIFICATIONCODE

package transactionsystem;

public class Verification {
	static public void initialiseVerification()
	{
	}
	
	static public void fail(String s) {
		System.out.println("ERROR: "+s);
	}
	
}

PRELUDE

package transactionsystem;

RULES

// P1
*.makeGoldUser(..) target (UserInfo u) 
  |  !(u.getCountry().equals("Argentina")) 
  -> { Verification.fail("P1 violated"); }
  

 
 
 
  
  