
package transactionsystem;
import java.util.HashMap;



public aspect Properties {
before (UserInfo u): call(* *.makeGoldUser(..)) &&
    target(u) {
    if (!(u.getCountry().equals("Argentina"))) { Verification.fail("P1 violated"); }
  }

before (): call(* *.initialise(..)){ Verification.initialised=true; }

before (): call(* UserInfo.openSession(..)) {
    if (!Verification.initialised) { Verification.fail("P2 violated"); }
  }

before (UserAccount a): call(* *.withdraw(..)) &&
    target(a) {
    if (a.getBalance() < 0) { Verification.fail("P3 violated"); }
  }

before (UserAccount a): call(* *.deposit(..)) &&
    target(a) {
    if (a.getBalance() < 0) { Verification.fail("P3 violated"); }
  }

before (Integer uid, String account_number): call(* *.ADMIN_approveOpenAccount(..)) &&
    args(uid, account_number){ Verification.approvedAccounts.add(account_number); }

before (Integer uid, String account_number): call(* *.ADMIN_approveOpenAccount(..)) &&
    args(uid, account_number) {
    if (Verification.approvedAccounts.contains(account_number)) { Verification.fail("P4 violated"); }
  }

before (UserInfo u): call(* UserInfo.makeDisabled(..)) &&
    target(u){ Verification.disabledUsers.add(u); }

before (UserInfo u): call(* UserInfo.makeActive(..)) &&
    target(u){ Verification.disabledUsers.remove(u); }

before (UserInfo u): call(* UserInfo.withdrawFrom(..)) &&
    target(u) {
    if ((Verification.disabledUsers.contains(u))) { Verification.fail("P3 violated"); }
  }

before (UserInfo u): call(* UserInfo.greylist(..)) &&
    target(u){ Verification.countTransfers.put(u,0); }

before (UserInfo u): call(* UserInfo.depositTo(..)) &&
    target(u){ if (!Verification.countTransfers.containsKey(u)) { Verification.countTransfers.put(u,0); } Verification.countTransfers.put(u,Verification.countTransfers.get(u)+1); }

before (UserInfo u): call(* UserInfo.whitelist(..)) &&
    target(u) {
    if (( Verification.countTransfers.containsKey(u) && Verification.countTransfers.get(u) < 3 )) { Verification.fail("P6 violated"); }
  }

before (UserInfo u, Integer sid): call(* UserInfo.createAccount(..)) &&
    args(sid) &&
    target(u){ if (!Verification.countNewAccounts.containsKey(u)) { Verification.countNewAccounts.put(u,new HashMap<Integer, Integer>()); } if (!Verification.countNewAccounts.get(u).containsKey(sid)) { Verification.countNewAccounts.get(u).put(sid,0); } Verification.countNewAccounts.get(u).put(sid,1+Verification.countNewAccounts.get(u).get(sid)); if (Verification.countNewAccounts.get(u).get(sid) > 10) { Verification.fail("P7 violated"); } }

before (Integer uid, Integer sid, String to_account_number, double amount): call(* *.USER_depositFromExternal(..)) &&
    args(uid, sid, to_account_number, amount){ Verification.countExternalTransferAttempts++; Verification.totalExternalTransferAttempts+=amount; if (Verification.countExternalTransferAttempts>1000 || Verification.totalExternalTransferAttempts>1000000) { Verification.fail("P8 violated"); } }

before (Integer uid, Integer sid, String from_account_number, double amount): call(* *.USER_payToExternal(..)) &&
    args(uid, sid, from_account_number, amount){ Verification.countExternalTransferAttempts++; Verification.totalExternalTransferAttempts+=amount; if (Verification.countExternalTransferAttempts>1000 || Verification.totalExternalTransferAttempts>1000000) { Verification.fail("P8 violated"); } }

before (): call(* *.ADMIN_reconcile(..)){ Verification.countExternalTransferAttempts=0; Verification.totalExternalTransferAttempts=0; }

before (UserInfo u): call(* UserInfo.openSession(..)) &&
    target(u){ if (!Verification.countSessions.containsKey(u)) { Verification.countSessions.put(u,0); } Verification.countSessions.put(u,Verification.countSessions.get(u)+1); if (Verification.countSessions.get(u) > 3) { Verification.fail("P9 violated"); } }

before (UserInfo u): call(* UserInfo.closeSession(..)) &&
    target(u){ Verification.countSessions.put(u,Verification.countSessions.get(u)-1); }

before (UserSession s): call(* UserSession.openSession(..)) &&
    target(s){ Verification.openSessions.add(s); }

before (UserSession s): call(* UserSession.closeSession(..)) &&
    target(s){ Verification.openSessions.remove(s); }

before (UserSession s): call(* UserSession.log(..)) &&
    target(s) {
    if (!Verification.openSessions.contains(s)) { Verification.fail("P10 violated"); }
  }


}
