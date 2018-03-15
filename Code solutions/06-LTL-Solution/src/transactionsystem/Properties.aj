
package transactionsystem;

import java.util.HashMap;



public aspect Properties {


// the code for EXPECT: (not USER_login Until initialise)
static String monitorState30f39991 = null;
static void init30f39991(){ 
 monitorState30f39991 = "[[[(not USER_login Until initialise)]], [[(USER_login Release not initialise)]]']"; }



static boolean checkMonitor30f39991(String state) { return state.equals(monitorState30f39991); }

static boolean setMonitor30f39991(String state) { 
  monitorState30f39991=state; 
  return true; }

before (): call(* *.USER_login(..)){ 
 if (1==0) {}
   else if ( checkMonitor30f39991("[[[true]]']")
 && (setMonitor30f39991("[[[true]]']"))) {
 Verification.fail(" FAILED ON LTL EXPECT (not USER_login Until initialise)");}

   else if ( checkMonitor30f39991("[[[(not USER_login Until initialise)]], [[(USER_login Release not initialise)]]']")
 && (setMonitor30f39991("[[[true]]']"))) {
 Verification.fail(" FAILED ON LTL EXPECT (not USER_login Until initialise)");}

   else if ( checkMonitor30f39991("[[[true]]]")
 && (setMonitor30f39991("[[[true]]]"))) {}
}


before (): call(* *.initialise(..)){ 
 if (1==0) {}
   else if ( checkMonitor30f39991("[[[true]]']")
 && (setMonitor30f39991("[[[true]]']"))) {
 Verification.fail(" FAILED ON LTL EXPECT (not USER_login Until initialise)");}

   else if ( checkMonitor30f39991("[[[(not USER_login Until initialise)]], [[(USER_login Release not initialise)]]']")
 && (setMonitor30f39991("[[[true]]]"))) {}

   else if ( checkMonitor30f39991("[[[true]]]")
 && (setMonitor30f39991("[[[true]]]"))) {}
}


public static void init() {init30f39991(); 

}
}
