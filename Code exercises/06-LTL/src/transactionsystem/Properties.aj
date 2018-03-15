
package transactionsystem;



public aspect Properties {


// the code for EXPECT: (!USER_loginUinitialise)
static String monitorState10f0f6ac = null;
static void init10f0f6ac(){ 
 monitorState10f0f6ac = "[[[(USER_loginR!initialise)]]', [[(!USER_loginUinitialise)]]]"; }



static boolean checkMonitor10f0f6ac(String state) { return state.equals(monitorState10f0f6ac); }

static boolean setMonitor10f0f6ac(String state) { 
  monitorState10f0f6ac=state; 
  return true; }

before (): call(* *.initialise(..)){ 
 if (1==0) {}
   else if ( checkMonitor10f0f6ac("[[[true]]]")
 && (setMonitor10f0f6ac("[[[true]]]"))) {}

   else if ( checkMonitor10f0f6ac("[[[(USER_loginR!initialise)]]', [[(!USER_loginUinitialise)]]]")
 && (setMonitor10f0f6ac("[[[true]]]"))) {}

   else if ( checkMonitor10f0f6ac("[[[true]]']")
 && (setMonitor10f0f6ac("[[[true]]']"))) {
 Verification.fail(" FAILED ON LTL EXPECT (!USER_loginUinitialise)");}
}


before (): call(* *.USER_login(..)){ 
 if (1==0) {}
   else if ( checkMonitor10f0f6ac("[[[true]]]")
 && (setMonitor10f0f6ac("[[[true]]]"))) {}

   else if ( checkMonitor10f0f6ac("[[[(USER_loginR!initialise)]]', [[(!USER_loginUinitialise)]]]")
 && (setMonitor10f0f6ac("[[[true]]']"))) {
 Verification.fail(" FAILED ON LTL EXPECT (!USER_loginUinitialise)");}

   else if ( checkMonitor10f0f6ac("[[[true]]']")
 && (setMonitor10f0f6ac("[[[true]]']"))) {
 Verification.fail(" FAILED ON LTL EXPECT (!USER_loginUinitialise)");}
}


public static void init() {init10f0f6ac(); 

}
}
