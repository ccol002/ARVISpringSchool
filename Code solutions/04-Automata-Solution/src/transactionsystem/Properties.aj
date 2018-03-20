
package transactionsystem;

import java.util.HashMap;




public aspect Properties {


// automaton starts here
static String monitorState7852e922 = null;
static void init7852e922(){ monitorState7852e922 = "Start"; }



static boolean checkMonitor7852e922(String state) { return state.equals(monitorState7852e922); }

static boolean setMonitor7852e922(String state) { 
  monitorState7852e922=state; 
  return true; }

before (UserInfo u): call(* *.makeGoldUser(..)) &&
    target(u) {
    if ( checkMonitor7852e922("Start") && !(u.getCountry().equals("Argentina")) && (setMonitor7852e922("Bad"))) {
 System.out.println("P1 violated");{}}
  }



// automaton starts here
static String monitorState4e25154f = null;
static void init4e25154f(){ monitorState4e25154f = "Start"; }



static boolean checkMonitor4e25154f(String state) { return state.equals(monitorState4e25154f); }

static boolean setMonitor4e25154f(String state) { 
  monitorState4e25154f=state; 
  return true; }

before (): call(* *.initialise(..)) {
    if ( checkMonitor4e25154f("Start") && true && (setMonitor4e25154f("Initialised"))) {{}}
  }

before (): call(* UserInfo.openSession(..)) {
    if ( checkMonitor4e25154f("Start") && true && (setMonitor4e25154f("Bad"))) {
 System.out.println("P2 violated");{}}
  }



// automaton starts here
static String monitorState70dea4e = null;
static void init70dea4e(){ monitorState70dea4e = "Start"; }



static boolean checkMonitor70dea4e(String state) { return state.equals(monitorState70dea4e); }

static boolean setMonitor70dea4e(String state) { 
  monitorState70dea4e=state; 
  return true; }

before (UserAccount a): call(* *.withdraw(..)) &&
    target(a) {
    if ( checkMonitor70dea4e("Start") && a.getBalance() < 0 && (setMonitor70dea4e("Bad"))) {
 System.out.println("P3 violated");{}}
  }

before (UserAccount a): call(* *.deposit(..)) &&
    target(a) {
    if ( checkMonitor70dea4e("Start") && a.getBalance() < 0 && (setMonitor70dea4e("Bad"))) {
 System.out.println("P3 violated");{}}
  }



// automaton starts here
static String monitorState5c647e05 = null;
static void init5c647e05(){ monitorState5c647e05 = "Start"; }



static boolean checkMonitor5c647e05(String state) { return state.equals(monitorState5c647e05); }

static boolean setMonitor5c647e05(String state) { 
  monitorState5c647e05=state; 
  return true; }

before (Integer uid, String account_number): call(* *.ADMIN_approveOpenAccount(..)) &&
    args(uid, account_number) {
    if ( checkMonitor5c647e05("Start") && Verification.approvedAccounts.contains(account_number) && (setMonitor5c647e05("Bad"))) {
 System.out.println("P4 violated");{}}
  }

before (Integer uid, String account_number): call(* *.ADMIN_approveOpenAccount(..)) &&
    args(uid, account_number) {
    if ( checkMonitor5c647e05("Start") && true && (setMonitor5c647e05("Start"))) {{ Verification.approvedAccounts.add(account_number); }}
  }



// automaton starts here
static HashMap<UserInfo, String> monitors33909752 = null;
static void init33909752(){ monitors33909752= new HashMap<UserInfo, String>(); }



static boolean checkMonitor33909752(UserInfo target, String state) { 
 if (monitors33909752.containsKey(target))
  return state.equals(monitors33909752.get(target));
 else {
  monitors33909752.put(target, state);
  return state.equals(state); }
 }

static boolean setMonitor33909752(UserInfo target, String state) { 
 if (monitors33909752.containsKey(target))
 { monitors33909752.put(target,state);
   return true; 
 }
 else {
  return false; }
 }

before (UserInfo u): call(* UserInfo.makeDisabled(..)) &&
    target(u) {
    if ( checkMonitor33909752(u, "Enabled") && true && (setMonitor33909752(u, "Disabled"))) {{}}
  }

before (UserInfo u): call(* UserInfo.makeActive(..)) &&
    target(u) {
    if ( checkMonitor33909752(u, "Disabled") && true && (setMonitor33909752(u, "Enabled"))) {{}}
  }

before (UserInfo u): call(* UserInfo.withdrawFrom(..)) &&
    target(u) {
    if ( checkMonitor33909752(u, "Disabled") && true && (setMonitor33909752(u, "Bad"))) {
 System.out.println("P5 violated");{}}
  }



// automaton starts here
static HashMap<UserInfo, String> monitors55f96302 = null;
static void init55f96302(){ monitors55f96302= new HashMap<UserInfo, String>(); }



static boolean checkMonitor55f96302(UserInfo target, String state) { 
 if (monitors55f96302.containsKey(target))
  return state.equals(monitors55f96302.get(target));
 else {
  monitors55f96302.put(target, state);
  return state.equals(state); }
 }

static boolean setMonitor55f96302(UserInfo target, String state) { 
 if (monitors55f96302.containsKey(target))
 { monitors55f96302.put(target,state);
   return true; 
 }
 else {
  return false; }
 }

before (UserInfo u): call(* UserInfo.greylist(..)) &&
    target(u) {
    if ( checkMonitor55f96302(u, "Start") && true && (setMonitor55f96302(u, "Start"))) {{ Verification.countTransfers.put(u,0); }}
  }

before (UserInfo u): call(* UserInfo.depositTo(..)) &&
    target(u) {
    if ( checkMonitor55f96302(u, "Start") && true && (setMonitor55f96302(u, "Start"))) {{ if (!Verification.countTransfers.containsKey(u)) { Verification.countTransfers.put(u,0); } Verification.countTransfers.put(u,Verification.countTransfers.get(u)+1); }}
  }

before (UserInfo u): call(* UserInfo.whitelist(..)) &&
    target(u) {
    if ( checkMonitor55f96302(u, "Start") && ( Verification.countTransfers.containsKey(u) && Verification.countTransfers.get(u) < 3 ) && (setMonitor55f96302(u, "Bad"))) {
 System.out.println("P6 violated");{}}
  }



// automaton starts here
static HashMap<UserInfo, String> monitors3d4eac69 = null;
static void init3d4eac69(){ monitors3d4eac69= new HashMap<UserInfo, String>(); }



static boolean checkMonitor3d4eac69(UserInfo target, String state) { 
 if (monitors3d4eac69.containsKey(target))
  return state.equals(monitors3d4eac69.get(target));
 else {
  monitors3d4eac69.put(target, state);
  return state.equals(state); }
 }

static boolean setMonitor3d4eac69(UserInfo target, String state) { 
 if (monitors3d4eac69.containsKey(target))
 { monitors3d4eac69.put(target,state);
   return true; 
 }
 else {
  return false; }
 }

before (UserInfo u, Integer sid): call(* UserInfo.createAccount(..)) &&
    args(sid) &&
    target(u) {
    if ( checkMonitor3d4eac69(u, "Start") && true && (setMonitor3d4eac69(u, "Start"))) {{ if (!Verification.countNewAccounts.containsKey(u)) { Verification.countNewAccounts.put(u,new HashMap<Integer, Integer>()); } if (!Verification.countNewAccounts.get(u).containsKey(sid)) { Verification.countNewAccounts.get(u).put(sid,0); } Verification.countNewAccounts.get(u).put(sid,1+Verification.countNewAccounts.get(u).get(sid)); if (Verification.countNewAccounts.get(u).get(sid) > 10) { Verification.fail("P7 violated"); } }}
  }



// automaton starts here
static String monitorState42a57993 = null;
static void init42a57993(){ monitorState42a57993 = "Start"; }



static boolean checkMonitor42a57993(String state) { return state.equals(monitorState42a57993); }

static boolean setMonitor42a57993(String state) { 
  monitorState42a57993=state; 
  return true; }

before (Integer uid, Integer sid, String to_account_number, double amount): call(* *.USER_depositFromExternal(..)) &&
    args(uid, sid, to_account_number, amount) {
    if ( checkMonitor42a57993("Start") && (Verification.countExternalTransferAttempts+1>1000 || Verification.totalExternalTransferAttempts+amount>1000000) && (setMonitor42a57993("Bad"))) {
 System.out.println("P8 violated");{}}
  }

before (Integer uid, Integer sid, String to_account_number, double amount): call(* *.USER_depositFromExternal(..)) &&
    args(uid, sid, to_account_number, amount) {
    if ( checkMonitor42a57993("Start") && true && (setMonitor42a57993("Start"))) {{ Verification.countExternalTransferAttempts++; Verification.totalExternalTransferAttempts+=amount; }}
  }

before (Integer uid, Integer sid, String from_account_number, double amount): call(* *.USER_payToExternal(..)) &&
    args(uid, sid, from_account_number, amount) {
    if ( checkMonitor42a57993("Start") && (Verification.countExternalTransferAttempts+1>1000 || Verification.totalExternalTransferAttempts+amount>1000000) && (setMonitor42a57993("Bad"))) {
 System.out.println("P8 violated");{}}
  }

before (Integer uid, Integer sid, String from_account_number, double amount): call(* *.USER_payToExternal(..)) &&
    args(uid, sid, from_account_number, amount) {
    if ( checkMonitor42a57993("Start") && true && (setMonitor42a57993("Start"))) {{ Verification.countExternalTransferAttempts++; Verification.totalExternalTransferAttempts+=amount; }}
  }

before (): call(* *.ADMIN_reconcile(..)) {
    if ( checkMonitor42a57993("Start") && true && (setMonitor42a57993("Start"))) {{ Verification.countExternalTransferAttempts=0; Verification.totalExternalTransferAttempts=0; }}
  }



// automaton starts here
static HashMap<UserInfo, String> monitors75b84c92 = null;
static void init75b84c92(){ monitors75b84c92= new HashMap<UserInfo, String>(); }



static boolean checkMonitor75b84c92(UserInfo target, String state) { 
 if (monitors75b84c92.containsKey(target))
  return state.equals(monitors75b84c92.get(target));
 else {
  monitors75b84c92.put(target, state);
  return state.equals(state); }
 }

static boolean setMonitor75b84c92(UserInfo target, String state) { 
 if (monitors75b84c92.containsKey(target))
 { monitors75b84c92.put(target,state);
   return true; 
 }
 else {
  return false; }
 }

before (UserInfo u): call(* UserInfo.openSession(..)) &&
    target(u) {
    if ( checkMonitor75b84c92(u, "Start") && true && (setMonitor75b84c92(u, "Start"))) {{ if (!Verification.countSessions.containsKey(u)) { Verification.countSessions.put(u,0); } Verification.countSessions.put(u,Verification.countSessions.get(u)+1); if (Verification.countSessions.get(u) > 3) { Verification.fail("P9 violated"); } }}
  }

before (UserInfo u): call(* UserInfo.closeSession(..)) &&
    target(u) {
    if ( checkMonitor75b84c92(u, "Start") && true && (setMonitor75b84c92(u, "Start"))) {{ Verification.countSessions.put(u,Verification.countSessions.get(u)-1); }}
  }



// automaton starts here
static HashMap<UserSession, String> monitors6bc7c054 = null;
static void init6bc7c054(){ monitors6bc7c054= new HashMap<UserSession, String>(); }



static boolean checkMonitor6bc7c054(UserSession target, String state) { 
 if (monitors6bc7c054.containsKey(target))
  return state.equals(monitors6bc7c054.get(target));
 else {
  monitors6bc7c054.put(target, state);
  return state.equals(state); }
 }

static boolean setMonitor6bc7c054(UserSession target, String state) { 
 if (monitors6bc7c054.containsKey(target))
 { monitors6bc7c054.put(target,state);
   return true; 
 }
 else {
  return false; }
 }

before (UserSession s): call(* UserSession.openSession(..)) &&
    target(s) {
    if ( checkMonitor6bc7c054(s, "LoggedOut") && true && (setMonitor6bc7c054(s, "LoggedIn"))) {{}}
  }

before (UserSession s): call(* UserSession.closeSession(..)) &&
    target(s) {
    if ( checkMonitor6bc7c054(s, "LoggedIn") && true && (setMonitor6bc7c054(s, "LoggedOut"))) {{}}
  }

before (UserSession s): call(* UserSession.log(..)) &&
    target(s) {
    if ( checkMonitor6bc7c054(s, "LoggedOut") && true && (setMonitor6bc7c054(s, "Bad"))) {
 System.out.println("P10 violated");{}}
  }

public static void initialise() {init7852e922(); 
init4e25154f(); 
init70dea4e(); 
init5c647e05(); 
init33909752(); 
init55f96302(); 
init3d4eac69(); 
init42a57993(); 
init75b84c92(); 
init6bc7c054(); 

}
}
