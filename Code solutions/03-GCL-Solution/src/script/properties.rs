VERIFICATIONCODE

package transactionsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Verification {
	static public boolean initialised;
	static public ArrayList<String> approvedAccounts;
	static public HashSet<UserInfo> disabledUsers;
	static public HashMap<UserInfo,Integer> countTransfers;
	static public HashMap<UserInfo,Integer> countSessions;
	static public HashMap<UserInfo,HashMap<Integer, Integer>> countNewAccounts;
	static public Integer countExternalTransferAttempts;
	static public double totalExternalTransferAttempts;
	static public HashSet<UserSession> openSessions;

	static public void initialiseVerification()
	{
		initialised = false;
		approvedAccounts = new ArrayList<String>();
		disabledUsers = new HashSet<UserInfo>();
		countTransfers = new HashMap<UserInfo,Integer>();
		countSessions = new HashMap<UserInfo,Integer>();
		countNewAccounts = new HashMap<UserInfo,HashMap<Integer, Integer>>();
		openSessions = new HashSet<UserSession>();
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

RULES

// P1
*.makeGoldUser(..) target (UserInfo u) 
  |  !(u.getCountry().equals("Argentina")) 
  -> { Verification.fail("P1 violated"); }

// P2
*.initialise(..) | -> { Verification.initialised=true; }
UserInfo.openSession(..) | !Verification.initialised -> { Verification.fail("P2 violated"); }

// P3
*.withdraw(..) target (UserAccount a) | a.getBalance() < 0 -> { Verification.fail("P3 violated"); }
*.deposit(..) target (UserAccount a) | a.getBalance() < 0 -> { Verification.fail("P3 violated"); }

// P4
*.ADMIN_approveOpenAccount(Integer uid, String account_number) 
  | -> { Verification.approvedAccounts.add(account_number); }
*.ADMIN_approveOpenAccount(Integer uid, String account_number)
  |  Verification.approvedAccounts.contains(account_number) 
  -> { Verification.fail("P4 violated"); }

// P5
UserInfo.makeDisabled(..) target (UserInfo u) | -> { Verification.disabledUsers.add(u); }
UserInfo.makeActive(..) target (UserInfo u) | -> { Verification.disabledUsers.remove(u); }
UserInfo.withdrawFrom(..) target (UserInfo u) 
  |  (Verification.disabledUsers.contains(u)) 
  -> { Verification.fail("P3 violated"); }

// P6
UserInfo.greylist(..) target (UserInfo u) | -> { 
	Verification.countTransfers.put(u,0);
}
UserInfo.depositTo(..) target (UserInfo u) | -> { 
	if (!Verification.countTransfers.containsKey(u)) {
		Verification.countTransfers.put(u,0);
	}
	Verification.countTransfers.put(u,Verification.countTransfers.get(u)+1); 
}
UserInfo.whitelist(..) target (UserInfo u) 
  |  ( Verification.countTransfers.containsKey(u) && Verification.countTransfers.get(u) < 3 )
  -> { Verification.fail("P6 violated"); }

// P7
// No user may not request more than 10 new accounts in a single session.
UserInfo.createAccount(Integer sid) target (UserInfo u) | -> {
    if (!Verification.countNewAccounts.containsKey(u)) 
       { Verification.countNewAccounts.put(u,new HashMap<Integer, Integer>()); }
    if (!Verification.countNewAccounts.get(u).containsKey(sid)) 
       { Verification.countNewAccounts.get(u).put(sid,0); }
    Verification.countNewAccounts.get(u).put(sid,1+Verification.countNewAccounts.get(u).get(sid));
    if (Verification.countNewAccounts.get(u).get(sid) > 10) { Verification.fail("P7 violated"); }
}

// P8 
// The administrator must reconcile accounts every 1000 attempted external money transfers or an 
// aggregate total of one million dollars in attempted external transfers.
*.USER_depositFromExternal(Integer uid, Integer sid, String to_account_number, double amount) | -> { 
  Verification.countExternalTransferAttempts++; Verification.totalExternalTransferAttempts+=amount; 
  if (Verification.countExternalTransferAttempts>1000 || Verification.totalExternalTransferAttempts>1000000) {
     Verification.fail("P8 violated");
  }
}
*.USER_payToExternal(Integer uid, Integer sid, String from_account_number, double amount) | -> {
  Verification.countExternalTransferAttempts++; Verification.totalExternalTransferAttempts+=amount; 
  if (Verification.countExternalTransferAttempts>1000 || Verification.totalExternalTransferAttempts>1000000) {
     Verification.fail("P8 violated");
  }
}
*.ADMIN_reconcile(..) | -> { Verification.countExternalTransferAttempts=0; Verification.totalExternalTransferAttempts=0; }

// P9
// A user may not have more than 3 active sessions at once.
UserInfo.openSession(..) target (UserInfo u) | -> {
  if (!Verification.countSessions.containsKey(u)) { Verification.countSessions.put(u,0); }
  Verification.countSessions.put(u,Verification.countSessions.get(u)+1); 
  if (Verification.countSessions.get(u) > 3) { Verification.fail("P9 violated"); }
}
UserInfo.closeSession(..) target (UserInfo u) | -> {
  Verification.countSessions.put(u,Verification.countSessions.get(u)-1); 
}


// P10
// Logging can only be made to an active session (i.e. between a login and a logout)
UserSession.openSession(..) target (UserSession s) | -> { Verification.openSessions.add(s); }
UserSession.closeSession(..) target (UserSession s) | -> { Verification.openSessions.remove(s); }
UserSession.log(..) target (UserSession s)
  |  !Verification.openSessions.contains(s)
  -> { Verification.fail("P10 violated"); }






