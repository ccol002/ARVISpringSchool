
package transactionsystem;

import java.util.HashMap;




public aspect Properties {


// automaton starts here
static String monitorState401e9c3f = null;
static void init401e9c3f(){ monitorState401e9c3f = "Start"; }



static boolean checkMonitor401e9c3f(String state) { return state.equals(monitorState401e9c3f); }

static boolean setMonitor401e9c3f(String state) { 
  monitorState401e9c3f=state; 
  return true; }

before (UserInfo u): call(* *.makeGoldUser(..)) &&
    target(u) {
    if ( checkMonitor401e9c3f("Start") && !(u.getCountry().equals("Argentina")) && (setMonitor401e9c3f("Bad"))) {
 System.out.println("P1 violated");{}}
  }



// automaton starts here
static String monitorState19a40cfc = null;
static void init19a40cfc(){ monitorState19a40cfc = "Start"; }



static boolean checkMonitor19a40cfc(String state) { return state.equals(monitorState19a40cfc); }

static boolean setMonitor19a40cfc(String state) { 
  monitorState19a40cfc=state; 
  return true; }

before (): call(* *.initialise(..)) {
    if ( checkMonitor19a40cfc("Start") && true && (setMonitor19a40cfc("Initialised"))) {{}}
  }

before (): call(* UserInfo.openSession(..)) {
    if ( checkMonitor19a40cfc("Start") && true && (setMonitor19a40cfc("Bad"))) {
 System.out.println("P2 violated");{}}
  }



// automaton starts here
static String monitorState6150818a = null;
static void init6150818a(){ monitorState6150818a = "Start"; }



static boolean checkMonitor6150818a(String state) { return state.equals(monitorState6150818a); }

static boolean setMonitor6150818a(String state) { 
  monitorState6150818a=state; 
  return true; }

before (UserAccount a): call(* *.withdraw(..)) &&
    target(a) {
    if ( checkMonitor6150818a("Start") && a.getBalance() < 0 && (setMonitor6150818a("Bad"))) {
 System.out.println("P3 violated");{}}
  }

before (UserAccount a): call(* *.deposit(..)) &&
    target(a) {
    if ( checkMonitor6150818a("Start") && a.getBalance() < 0 && (setMonitor6150818a("Bad"))) {
 System.out.println("P3 violated");{}}
  }



// automaton starts here
static String monitorState6c68bcef = null;
static void init6c68bcef(){ monitorState6c68bcef = "Start"; }



static boolean checkMonitor6c68bcef(String state) { return state.equals(monitorState6c68bcef); }

static boolean setMonitor6c68bcef(String state) { 
  monitorState6c68bcef=state; 
  return true; }

before (Integer uid, String account_number): call(* *.ADMIN_approveOpenAccount(..)) &&
    args(uid, account_number) {
    if ( checkMonitor6c68bcef("Start") && true && (setMonitor6c68bcef("Start"))) {{ Verification.approvedAccounts.add(account_number); }}
  }

before (Integer uid, String account_number): call(* *.ADMIN_approveOpenAccount(..)) &&
    args(uid, account_number) {
    if ( checkMonitor6c68bcef("Start") && Verification.approvedAccounts.contains(account_number) && (setMonitor6c68bcef("Bad"))) {
 System.out.println("P4 violated");{}}
  }



// automaton starts here
static HashMap<UserInfo, String> monitors504c2683 = null;
static void init504c2683(){ monitors504c2683= new HashMap<UserInfo, String>(); }



static boolean checkMonitor504c2683(UserInfo target, String state) { 
 if (monitors504c2683.containsKey(target))
  return state.equals(monitors504c2683.get(target));
 else {
  monitors504c2683.put(target, state);
  return state.equals(state); }
 }

static boolean setMonitor504c2683(UserInfo target, String state) { 
 if (monitors504c2683.containsKey(target))
 { monitors504c2683.put(target,state);
   return true; 
 }
 else {
  return false; }
 }

before (UserInfo u): call(* UserInfo.makeDisabled(..)) &&
    target(u) {
    if ( checkMonitor504c2683(u, "Enabled") && true && (setMonitor504c2683(u, "Disabled"))) {{}}
  }

before (UserInfo u): call(* UserInfo.makeActive(..)) &&
    target(u) {
    if ( checkMonitor504c2683(u, "Disabled") && true && (setMonitor504c2683(u, "Enabled"))) {{}}
  }

before (UserInfo u): call(* UserInfo.withdrawFrom(..)) &&
    target(u) {
    if ( checkMonitor504c2683(u, "Disabled") && true && (setMonitor504c2683(u, "Bad"))) {
 System.out.println("P3 violated");{}}
  }



// automaton starts here
static HashMap<UserInfo, String> monitors37748ba4 = null;
static void init37748ba4(){ monitors37748ba4= new HashMap<UserInfo, String>(); }



static boolean checkMonitor37748ba4(UserInfo target, String state) { 
 if (monitors37748ba4.containsKey(target))
  return state.equals(monitors37748ba4.get(target));
 else {
  monitors37748ba4.put(target, state);
  return state.equals(state); }
 }

static boolean setMonitor37748ba4(UserInfo target, String state) { 
 if (monitors37748ba4.containsKey(target))
 { monitors37748ba4.put(target,state);
   return true; 
 }
 else {
  return false; }
 }

before (UserInfo u): call(* UserInfo.greylist(..)) &&
    target(u) {
    if ( checkMonitor37748ba4(u, "Start") && true && (setMonitor37748ba4(u, "Start"))) {{ Verification.countTransfers.put(u,0); }}
  }

before (UserInfo u): call(* UserInfo.depositTo(..)) &&
    target(u) {
    if ( checkMonitor37748ba4(u, "Start") && true && (setMonitor37748ba4(u, "Start"))) {{ if (!Verification.countTransfers.containsKey(u)) { Verification.countTransfers.put(u,0); } Verification.countTransfers.put(u,Verification.countTransfers.get(u)+1); }}
  }

before (UserInfo u): call(* UserInfo.whitelist(..)) &&
    target(u) {
    if ( checkMonitor37748ba4(u, "Start") && ( Verification.countTransfers.containsKey(u) && Verification.countTransfers.get(u) < 3 ) && (setMonitor37748ba4(u, "Bad"))) {
 System.out.println("P6 violated");{}}
  }



// automaton starts here
static HashMap<UserInfo, String> monitors447bd86d = null;
static void init447bd86d(){ monitors447bd86d= new HashMap<UserInfo, String>(); }



static boolean checkMonitor447bd86d(UserInfo target, String state) { 
 if (monitors447bd86d.containsKey(target))
  return state.equals(monitors447bd86d.get(target));
 else {
  monitors447bd86d.put(target, state);
  return state.equals(state); }
 }

static boolean setMonitor447bd86d(UserInfo target, String state) { 
 if (monitors447bd86d.containsKey(target))
 { monitors447bd86d.put(target,state);
   return true; 
 }
 else {
  return false; }
 }

before (UserInfo u, Integer sid): call(* UserInfo.createAccount(..)) &&
    args(sid) &&
    target(u) {
    if ( checkMonitor447bd86d(u, "Start") && true && (setMonitor447bd86d(u, "Start"))) {{ if (!Verification.countNewAccounts.containsKey(u)) { Verification.countNewAccounts.put(u,new HashMap<Integer, Integer>()); } if (!Verification.countNewAccounts.get(u).containsKey(sid)) { Verification.countNewAccounts.get(u).put(sid,0); } Verification.countNewAccounts.get(u).put(sid,1+Verification.countNewAccounts.get(u).get(sid)); if (Verification.countNewAccounts.get(u).get(sid) > 10) { Verification.fail("P7 violated"); } }}
  }



// automaton starts here
static String monitorState1ebdff3b = null;
static void init1ebdff3b(){ monitorState1ebdff3b = "Start"; }



static boolean checkMonitor1ebdff3b(String state) { return state.equals(monitorState1ebdff3b); }

static boolean setMonitor1ebdff3b(String state) { 
  monitorState1ebdff3b=state; 
  return true; }

before (Integer uid, Integer sid, String to_account_number, double amount): call(* *.USER_depositFromExternal(..)) &&
    args(uid, sid, to_account_number, amount) {
    if ( checkMonitor1ebdff3b("Start") && (Verification.countExternalTransferAttempts+1>1000 || Verification.totalExternalTransferAttempts+amount>1000000) && (setMonitor1ebdff3b("Bad"))) {
 System.out.println("P8 violated");{}}
  }

before (Integer uid, Integer sid, String to_account_number, double amount): call(* *.USER_depositFromExternal(..)) &&
    args(uid, sid, to_account_number, amount) {
    if ( checkMonitor1ebdff3b("Start") && true && (setMonitor1ebdff3b("Start"))) {{ Verification.countExternalTransferAttempts++; Verification.totalExternalTransferAttempts+=amount; }}
  }

before (Integer uid, Integer sid, String from_account_number, double amount): call(* *.USER_payToExternal(..)) &&
    args(uid, sid, from_account_number, amount) {
    if ( checkMonitor1ebdff3b("Start") && (Verification.countExternalTransferAttempts+1>1000 || Verification.totalExternalTransferAttempts+amount>1000000) && (setMonitor1ebdff3b("Bad"))) {
 System.out.println("P8 violated");{}}
  }

before (Integer uid, Integer sid, String from_account_number, double amount): call(* *.USER_payToExternal(..)) &&
    args(uid, sid, from_account_number, amount) {
    if ( checkMonitor1ebdff3b("Start") && true && (setMonitor1ebdff3b("Start"))) {{ Verification.countExternalTransferAttempts++; Verification.totalExternalTransferAttempts+=amount; }}
  }

before (): call(* *.ADMIN_reconcile(..)) {
    if ( checkMonitor1ebdff3b("Start") && true && (setMonitor1ebdff3b("Start"))) {{ Verification.countExternalTransferAttempts=0; Verification.totalExternalTransferAttempts=0; }}
  }



// automaton starts here
static HashMap<UserInfo, String> monitors4bbf8a41 = null;
static void init4bbf8a41(){ monitors4bbf8a41= new HashMap<UserInfo, String>(); }



static boolean checkMonitor4bbf8a41(UserInfo target, String state) { 
 if (monitors4bbf8a41.containsKey(target))
  return state.equals(monitors4bbf8a41.get(target));
 else {
  monitors4bbf8a41.put(target, state);
  return state.equals(state); }
 }

static boolean setMonitor4bbf8a41(UserInfo target, String state) { 
 if (monitors4bbf8a41.containsKey(target))
 { monitors4bbf8a41.put(target,state);
   return true; 
 }
 else {
  return false; }
 }

before (UserInfo u): call(* UserInfo.openSession(..)) &&
    target(u) {
    if ( checkMonitor4bbf8a41(u, "Start") && true && (setMonitor4bbf8a41(u, "Start"))) {{ if (!Verification.countSessions.containsKey(u)) { Verification.countSessions.put(u,0); } Verification.countSessions.put(u,Verification.countSessions.get(u)+1); if (Verification.countSessions.get(u) > 3) { Verification.fail("P9 violated"); } }}
  }

before (UserInfo u): call(* UserInfo.closeSession(..)) &&
    target(u) {
    if ( checkMonitor4bbf8a41(u, "Start") && true && (setMonitor4bbf8a41(u, "Start"))) {{ Verification.countSessions.put(u,Verification.countSessions.get(u)-1); }}
  }



// automaton starts here
static HashMap<UserSession, String> monitorsc0dd841 = null;
static void initc0dd841(){ monitorsc0dd841= new HashMap<UserSession, String>(); }



static boolean checkMonitorc0dd841(UserSession target, String state) { 
 if (monitorsc0dd841.containsKey(target))
  return state.equals(monitorsc0dd841.get(target));
 else {
  monitorsc0dd841.put(target, state);
  return state.equals(state); }
 }

static boolean setMonitorc0dd841(UserSession target, String state) { 
 if (monitorsc0dd841.containsKey(target))
 { monitorsc0dd841.put(target,state);
   return true; 
 }
 else {
  return false; }
 }

before (UserSession s): call(* UserSession.openSession(..)) &&
    target(s) {
    if ( checkMonitorc0dd841(s, "LoggedOut") && true && (setMonitorc0dd841(s, "LoggedIn"))) {{}}
  }

before (UserSession s): call(* UserSession.closeSession(..)) &&
    target(s) {
    if ( checkMonitorc0dd841(s, "LoggedIn") && true && (setMonitorc0dd841(s, "LoggedOut"))) {{}}
  }

before (UserSession s): call(* UserSession.log(..)) &&
    target(s) {
    if ( checkMonitorc0dd841(s, "LoggedOut") && true && (setMonitorc0dd841(s, "Bad"))) {
 System.out.println("P10 violated");{}}
  }

public static void initialise() {init401e9c3f(); 
init19a40cfc(); 
init6150818a(); 
init6c68bcef(); 
init504c2683(); 
init37748ba4(); 
init447bd86d(); 
init1ebdff3b(); 
init4bbf8a41(); 
initc0dd841(); 

}
}
