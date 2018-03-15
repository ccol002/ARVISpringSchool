VERIFICATIONCODE

package transactionsystem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

public class Verification {


	

	static public void initialiseVerification()
	{
	// required to reset the automata to their initial state
	   Properties.initialise();
	   

	}
	
	static public void fail(String s) {
		System.out.println("ERROR: "+s);
	}
	
}

PRELUDE

package transactionsystem;

import java.util.HashMap;


AUTOMATA

//P1
property starting Start {
  Start >>> 
    *.makeGoldUser(..) target (UserInfo u) 
      |  !(u.getCountry().equals("Argentina")) 
    >>> Bad [P1 violated] }


// P5
property foreach target (UserInfo u) starting Enabled {
  Enabled >>> UserInfo.makeDisabled(..) target (UserInfo u)
          >>> Disabled 
  Disabled >>> UserInfo.makeActive(..) target (UserInfo u) 
           >>> Enabled
  Disabled >>> UserInfo.withdrawFrom(..) target (UserInfo u) 
           >>> Bad [P5 violated] 
  }