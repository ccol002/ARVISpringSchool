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

REGULAREXPRESSIONS

// P2
// The transaction system must be initialised before any user logs in.

property matching{
(!USER_login)* ; initialise ; 1*
  }
  
property not matching{  
!initialise*; USER_login
}

// P5
// Once a user is disabled, he or she may not withdraw from an account until 
// being made activate again.

property foreach target (UserInfo u)  {
+   (disabled >>> !withdraw* >>> activate >>> !disabled*)*
-   1* ; disabled ; !activate* ; withdraw
}

// P6 
// Once greylisted, a user must perform at least three incoming transfers 
// before being whitelisted.

property foreach target (UserInfo u) starting P6start {
  (greylisted >>> (!transfer* >>> transfer) >>> whitelist)*   
- 1* ; greylist; (!t* + !t*;t;!t* + !t*;t;!t*;t;!t*) ; whitelist
  
}

// P10
// Logging can only be made to an active session (i.e. between a login and a logout).

1*;(li;!lo*;lo)*;!li*;log

// Properties.js

before (TType Tvar, PType Pvar): 
	call (* method(..)) &&
	args(Pvar) &&
	target (Tvar) {
	if (RuntimeAutomaton.inState("S1") && condition) {
		action
		RuntimeAutomaton.changeState("S1", "S2");
	}
}

// Verification.java

...

// RuntimeAutomaton

package xxx;

import java.util.ArrayList;
import java.util.Iterator;

public class RuntimeAutomata {
	String p1;
	HashMap <UserInfo,String> p2;
	
	void step(String src, String dst)
	{
		if (p1==src) p1=dst; 
		p2.xxx

		if (dst.startsWith("BAD")) {
			System.out.println(dst.substring(4,dst.length()-1);
	}
	void initialise()
	{
		p1 = I1;
		
}

public class RuntimeAutomaton {
	static String current_state;
	
	static void initialise()
	{
		current_state = I1;
	}

	static void changeState(String src, String dst)
	{
		if (current_state==src) current_state=dst; 
		}
	} 
}


