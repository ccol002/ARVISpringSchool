VERIFICATIONCODE

package transactionsystem;

import java.util.Set;
import java.util.HashSet;


public class Verification {

static Set<String> msgs;

static public void initialiseVerification()
{
	System.out.println("\n\n");
	msgs = new HashSet<String>();
	Properties.initialise();
}
	
	static public void fail(String s) {
		
		if (msgs.add(s))
			System.out.println("ERROR: "+s);
	}
	
}

PRELUDE

package transactionsystem;

import re.structure.*;
import java.util.HashMap;


REGULAREXPRESSIONS


// P2
// The transaction system must be initialised before any user logs in.

property matching{
(!USER_login)* ; ADMIN_initialise ; ?*
  }
  
property not matching{  
(!ADMIN_initialise)*; USER_login
}




// P5
// Once a user is disabled, he or she may not withdraw from an account until 
// being made activate again.

property foreach target (UserInfo u) matching {
 ((!makeDisabled)* ; makeDisabled ; (!withdrawFrom)* ; makeActive )*
}

property foreach target (UserInfo u) not matching {
(?)* ; makeDisabled ; (!makeActive)* ; withdrawFrom
}


// P6 
// Once greylisted, a user must perform at least three incoming transfers 
// before being whitelisted.

property foreach target (UserInfo u) matching{
((!greylist)* ; greylist ; ((!whitelist)* ; depositTo ; (!whitelist)* ; depositTo ; (!whitelist)* ; depositTo ; (!whitelist)*) ; whitelist )*
}


property foreach target (UserInfo u) not matching{
(?)* ; greylist; ((!depositTo)* + (!depositTo)*;depositTo;(!depositTo)* + (!depositTo)*;depositTo;(!depositTo)*;depositTo;(!depositTo)*) ; whitelist
}


// P10
// Logging can only be made to an active session (i.e. between a login and a logout).
property foreach target (UserSession u) not matching{
(!openSession)*;(openSession;(!closeSession)*;closeSession)*;(!openSession)*;log
}
