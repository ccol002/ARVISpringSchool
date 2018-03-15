
package transactionsystem;

import java.util.HashMap;




public aspect Properties {


// automaton starts here
static String monitorState677327b6 = null;
static void init677327b6(){ monitorState677327b6 = "Start"; }



static boolean checkMonitor677327b6(String state) { return state.equals(monitorState677327b6); }

static boolean setMonitor677327b6(String state) { 
  monitorState677327b6=state; 
  return true; }

before (UserInfo u): call(* *.makeGoldUser(..)) &&
    target(u) {
    if ( checkMonitor677327b6("Start") && !(u.getCountry().equals("Argentina")) && (setMonitor677327b6("Bad"))) {
 System.out.println("P1 violated");{}}
  }



// automaton starts here
static HashMap<UserInfo, String> monitors14ae5a5 = null;
static void init14ae5a5(){ monitors14ae5a5= new HashMap<UserInfo, String>(); }



static boolean checkMonitor14ae5a5(UserInfo target, String state) { 
 if (monitors14ae5a5.containsKey(target))
  return state.equals(monitors14ae5a5.get(target));
 else {
  monitors14ae5a5.put(target, state);
  return state.equals(state); }
 }

static boolean setMonitor14ae5a5(UserInfo target, String state) { 
 if (monitors14ae5a5.containsKey(target))
 { monitors14ae5a5.put(target,state);
   return true; 
 }
 else {
  return false; }
 }

before (UserInfo u): call(* UserInfo.makeDisabled(..)) &&
    target(u) {
    if ( checkMonitor14ae5a5(u, "Enabled") && true && (setMonitor14ae5a5(u, "Disabled"))) {{}}
  }

before (UserInfo u): call(* UserInfo.makeActive(..)) &&
    target(u) {
    if ( checkMonitor14ae5a5(u, "Disabled") && true && (setMonitor14ae5a5(u, "Enabled"))) {{}}
  }

before (UserInfo u): call(* UserInfo.withdrawFrom(..)) &&
    target(u) {
    if ( checkMonitor14ae5a5(u, "Disabled") && true && (setMonitor14ae5a5(u, "Bad"))) {
 System.out.println("P5 violated");{}}
  }

public static void initialise() {init677327b6(); 
init14ae5a5(); 

}
}
