VERIFICATIONCODE

package transactionsystem;

public class Verification {
	static public void initialiseVerification()
	{
		Properties.init();
	}
	
	static public void fail(String s) {
		System.out.println("ERROR: "+s);
	}
	
}

PRELUDE

package transactionsystem;

import java.util.HashMap;

LTL

// P2
// The transaction system must be initialised before any user logs in.

property matching{
(not USER_login) Until initialise
  }
  
  
// P5
// Once a user is disabled, he or she may not withdraw from an account until 
// being made activate again.
  
property foreach target (UserInfo u) matching {
((not withdrawFrom) Until makeActive) and (Globally (makeDisabled => Next((not withdrawFrom) Until makeActive)))
}



  
// P10
// Logging can only be made to an active session (i.e. between openSession and closeSession).
  
  
property foreach target (UserSession u) matching{

((not log) Until openSession) and (Globally (closeSession => Next((not log) Until openSession)))

}

  
  
  
  
  
  

