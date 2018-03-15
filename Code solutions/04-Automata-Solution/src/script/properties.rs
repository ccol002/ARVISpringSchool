VERIFICATIONCODE

package transactionsystem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

public class Verification {

    public static ArrayList<String> approvedAccounts = new ArrayList<String>();
    static public HashSet<UserInfo> disabledUsers;
	static public HashMap<UserInfo,Integer> countTransfers;
	static public HashMap<UserInfo,Integer> countSessions;
	static public HashMap<UserInfo,HashMap<Integer, Integer>> countNewAccounts;
	static public Integer countExternalTransferAttempts;
	static public double totalExternalTransferAttempts;
	

	static public void initialiseVerification()
	{
	// required to reset the automata to their initial state
	   Properties.initialise();
	   
	   approvedAccounts = new ArrayList<String>();
		disabledUsers = new HashSet<UserInfo>();
		countTransfers = new HashMap<UserInfo,Integer>();
		countSessions = new HashMap<UserInfo,Integer>();
		countNewAccounts = new HashMap<UserInfo,HashMap<Integer, Integer>>();
		countExternalTransferAttempts = 0;
		totalExternalTransferAttempts = 0;
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


// P2
// The transaction system must be initialised before any user logs in.
property starting Start {
  Start >>> *.initialise(..) >>> Initialised
  Start >>> UserInfo.openSession(..) >>> Bad[P2 violated] 
  }


// P3
property starting Start {
  Start >>> *.withdraw(..) target (UserAccount a) | a.getBalance() < 0 >>> Bad [P3 violated] 
  Start >>> *.deposit(..) target (UserAccount a) | a.getBalance() < 0 >>> Bad [P3 violated]
 }
  
  
// P4
// cannot make it cleaner as only "foreach target" is supported
property starting Start {
  Start >>> *.ADMIN_approveOpenAccount(Integer uid, String account_number) 
             | -> { Verification.approvedAccounts.add(account_number); } 
        >>> Start 
  Start >>> *.ADMIN_approveOpenAccount(Integer uid, String account_number)
             |  Verification.approvedAccounts.contains(account_number) 
        >>> Bad [P4 violated]
}

// P5
property foreach target (UserInfo u) starting Enabled {
  Enabled >>> UserInfo.makeDisabled(..) target (UserInfo u)
          >>> Disabled
  Disabled >>> UserInfo.makeActive(..) target (UserInfo u) 
           >>> Enabled
  Disabled >>> UserInfo.withdrawFrom(..) target (UserInfo u) 
           >>> Bad [P5 violated]

}

// P6
property foreach target (UserInfo u) starting Start {

   Start >>> UserInfo.greylist(..) target (UserInfo u) | -> { 
	            Verification.countTransfers.put(u,0);
	         } 
	     >>> Start

   Start >>> UserInfo.depositTo(..) target (UserInfo u) | -> { 
	            if (!Verification.countTransfers.containsKey(u)) {
		           Verification.countTransfers.put(u,0);
	            }
	            Verification.countTransfers.put(u,Verification.countTransfers.get(u)+1); 
             } 
         >>> Start
            
    Start >>> UserInfo.whitelist(..) target (UserInfo u) 
                 |  ( Verification.countTransfers.containsKey(u) && Verification.countTransfers.get(u) < 3 )
          >>> Bad[P6 violated]
}


// P7
// No user may not request more than 10 new accounts in a single session.
property foreach target (UserInfo u) starting Start {
 
   Start >>> UserInfo.createAccount(Integer sid) target (UserInfo u) | -> {
                if (!Verification.countNewAccounts.containsKey(u)) 
                   { Verification.countNewAccounts.put(u,new HashMap<Integer, Integer>()); }
                if (!Verification.countNewAccounts.get(u).containsKey(sid)) 
                   { Verification.countNewAccounts.get(u).put(sid,0); }
                Verification.countNewAccounts.get(u).put(sid,1+Verification.countNewAccounts.get(u).get(sid));
                if (Verification.countNewAccounts.get(u).get(sid) > 10) 
                   { Verification.fail("P7 violated"); }
                }
         >>> Start
}

// P8 
// The administrator must reconcile accounts every 1000 attempted external money transfers or an 
// aggregate total of one million dollars in attempted external transfers.
property starting Start {
    Start >>> *.USER_depositFromExternal(Integer uid, Integer sid, String to_account_number, double amount) 
              | (Verification.countExternalTransferAttempts+1>1000 || Verification.totalExternalTransferAttempts+amount>1000000)
          >>> Bad[P8 violated]
    
    Start >>> *.USER_depositFromExternal(Integer uid, Integer sid, String to_account_number, double amount) | -> { 
              Verification.countExternalTransferAttempts++; 
              Verification.totalExternalTransferAttempts+=amount; }
          >>> Start
          
    Start >>> *.USER_payToExternal(Integer uid, Integer sid, String from_account_number, double amount) 
              | (Verification.countExternalTransferAttempts+1>1000 || Verification.totalExternalTransferAttempts+amount>1000000)
          >>> Bad[P8 violated]
          
    Start >>> *.USER_payToExternal(Integer uid, Integer sid, String from_account_number, double amount) | -> {
              Verification.countExternalTransferAttempts++; 
              Verification.totalExternalTransferAttempts+=amount; }
          >>> Start
          
    Start >>> *.ADMIN_reconcile(..) | -> { 
                 Verification.countExternalTransferAttempts=0; 
                 Verification.totalExternalTransferAttempts=0; }
          >>> Start
}

          
// P9
// A user may not have more than 3 active sessions at once.
property foreach target (UserInfo u) starting Start {
   Start >>> UserInfo.openSession(..) target (UserInfo u) | -> {
             if (!Verification.countSessions.containsKey(u)) { Verification.countSessions.put(u,0); }
             Verification.countSessions.put(u,Verification.countSessions.get(u)+1); 
             if (Verification.countSessions.get(u) > 3) { Verification.fail("P9 violated"); }
             }
         >>> Start
         
   Start >>> UserInfo.closeSession(..) target (UserInfo u) | -> {
                Verification.countSessions.put(u,Verification.countSessions.get(u)-1); 
             }
         >>> Start
}


// P10
// Logging can only be made to an active session (i.e. between a login and a logout)
property foreach target (UserSession s) starting LoggedOut {
    LoggedOut >>> UserSession.openSession(..) target (UserSession s) 
              >>> LoggedIn       
                 
    LoggedIn >>> UserSession.closeSession(..) target (UserSession s) 
             >>> LoggedOut
          
    LoggedOut >>> UserSession.log(..) target (UserSession s)
              >>> Bad[P10 violated] 
}


















