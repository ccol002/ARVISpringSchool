
package transactionsystem;

import re.structure.*;
import java.util.HashMap;




public aspect Properties {


// the code for EXPECT: (((!(USER_login))* ; ADMIN_initialise) ; (?)*)
static String monitorState77327b6 = null;
static void init77327b6(){ 
 monitorState77327b6 = "S_{[0, 1, 13, 16, 2, 4, 6, 8]}"; }



static boolean checkMonitor77327b6(String state) { return state.equals(monitorState77327b6); }

static boolean setMonitor77327b6(String state) { 
  monitorState77327b6=state; 
  return true; }

before (): call(* *.USER_login(..)){ 
 if (1==0) {}
   else if ( checkMonitor77327b6("N_{[1, 10, 11, 14, 15, 2, 3, 4, 5, 7, 8, 9]}")
 && (setMonitor77327b6("N_{[10, 11, 12, 14]}"))) {}

   else if ( checkMonitor77327b6("N_{[10, 11, 12, 14]}")
 && (setMonitor77327b6("N_{[10, 11, 12, 14]}"))) {}

   else if ( checkMonitor77327b6("N_{[1, 10, 11, 12, 14, 15, 2, 3, 4, 5, 7, 8, 9]}")
 && (setMonitor77327b6("N_{[10, 11, 12, 14]}"))) {}

   else if ( checkMonitor77327b6("F_17")
 && (setMonitor77327b6("F_17"))) {
 Verification.fail(" FAILED ON REGEXP EXPECT (((!(USER_login))* ; ADMIN_initialise) ; (?)*)");}

   else if ( checkMonitor77327b6("S_{[0, 1, 13, 16, 2, 4, 6, 8]}")
 && (setMonitor77327b6("F_17"))) {
 Verification.fail(" FAILED ON REGEXP EXPECT (((!(USER_login))* ; ADMIN_initialise) ; (?)*)");}
}


before (): call(* *.ADMIN_initialise(..)){ 
 if (1==0) {}
   else if ( checkMonitor77327b6("S_{[0, 1, 13, 16, 2, 4, 6, 8]}")
 && (setMonitor77327b6("N_{[1, 10, 11, 14, 15, 2, 3, 4, 5, 7, 8, 9]}"))) {}

   else if ( checkMonitor77327b6("N_{[1, 10, 11, 14, 15, 2, 3, 4, 5, 7, 8, 9]}")
 && (setMonitor77327b6("N_{[1, 10, 11, 12, 14, 15, 2, 3, 4, 5, 7, 8, 9]}"))) {}

   else if ( checkMonitor77327b6("N_{[10, 11, 12, 14]}")
 && (setMonitor77327b6("N_{[10, 11, 12, 14]}"))) {}

   else if ( checkMonitor77327b6("N_{[1, 10, 11, 12, 14, 15, 2, 3, 4, 5, 7, 8, 9]}")
 && (setMonitor77327b6("N_{[1, 10, 11, 12, 14, 15, 2, 3, 4, 5, 7, 8, 9]}"))) {}

   else if ( checkMonitor77327b6("F_17")
 && (setMonitor77327b6("F_17"))) {
 Verification.fail(" FAILED ON REGEXP EXPECT (((!(USER_login))* ; ADMIN_initialise) ; (?)*)");}
}




// the code for UNEXPECT: ((!(ADMIN_initialise))* ; USER_login)
static String monitorState4ae5a5 = null;
static void init4ae5a5(){ 
 monitorState4ae5a5 = "S_{[18, 19, 20, 22, 24, 26, 27]}"; }



static boolean checkMonitor4ae5a5(String state) { return state.equals(monitorState4ae5a5); }

static boolean setMonitor4ae5a5(String state) { 
  monitorState4ae5a5=state; 
  return true; }

before (): call(* *.USER_login(..)){ 
 if (1==0) {}
   else if ( checkMonitor4ae5a5("S_{[18, 19, 20, 22, 24, 26, 27]}")
 && (setMonitor4ae5a5("F_{[19, 20, 21, 22, 23, 25, 26]}"))) {
 Verification.fail(" FAILED ON REGEXP UNEXPECT ((!(ADMIN_initialise))* ; USER_login)");}

   else if ( checkMonitor4ae5a5("F_{[19, 20, 21, 22, 23, 25, 26]}")
 && (setMonitor4ae5a5("F_{[19, 20, 21, 22, 23, 25, 26]}"))) {
 Verification.fail(" FAILED ON REGEXP UNEXPECT ((!(ADMIN_initialise))* ; USER_login)");}

   else if ( checkMonitor4ae5a5("N_28")
 && (setMonitor4ae5a5("N_28"))) {}
}


before (): call(* *.ADMIN_initialise(..)){ 
 if (1==0) {}
   else if ( checkMonitor4ae5a5("S_{[18, 19, 20, 22, 24, 26, 27]}")
 && (setMonitor4ae5a5("N_28"))) {}

   else if ( checkMonitor4ae5a5("F_{[19, 20, 21, 22, 23, 25, 26]}")
 && (setMonitor4ae5a5("N_28"))) {}

   else if ( checkMonitor4ae5a5("N_28")
 && (setMonitor4ae5a5("N_28"))) {}
}




// the code for EXPECT: (((((!(makeDisabled))* ; makeDisabled) ; (!(withdrawFrom))*) ; makeActive))*
static HashMap<UserInfo, String> monitorsf31245a = null;
static void initf31245a(){ monitorsf31245a= new HashMap<UserInfo, String>(); }



static boolean checkMonitorf31245a(UserInfo target, String state) { 
 if (monitorsf31245a.containsKey(target))
  return state.equals(monitorsf31245a.get(target));
 else {
  monitorsf31245a.put(target, "S_{[29, 30, 31, 32, 33, 35, 37, 39, 44, 49, 52]}");
  return state.equals("S_{[29, 30, 31, 32, 33, 35, 37, 39, 44, 49, 52]}"); }
 }

static boolean setMonitorf31245a(UserInfo target, String state) { 
 if (monitorsf31245a.containsKey(target))
 { monitorsf31245a.put(target,state);
   return true; 
 }
 else {
  return false; }
 }

before (UserInfo u): call(* *.withdrawFrom(..)) &&
    target(u){ 
 if (1==0) {}
   else if ( checkMonitorf31245a(u, "S_{[29, 30, 31, 32, 33, 35, 37, 39, 44, 49, 52]}")
 && (setMonitorf31245a(u, "N_{[32, 33, 34, 35, 39]}"))) {}

   else if ( checkMonitorf31245a(u, "N_{[32, 33, 34, 35, 39]}")
 && (setMonitorf31245a(u, "N_{[32, 33, 34, 35, 39]}"))) {}

   else if ( checkMonitorf31245a(u, "N_{[30, 31, 32, 33, 35, 37, 39, 41, 42, 43, 44, 45, 47, 48, 49, 50, 51]}")
 && (setMonitorf31245a(u, "N_{[32, 33, 34, 35, 39]}"))) {}

   else if ( checkMonitorf31245a(u, "N_{[30, 31, 32, 33, 34, 35, 37, 39, 41, 42, 43, 44, 45, 47, 48, 49, 50, 51]}")
 && (setMonitorf31245a(u, "N_{[32, 33, 34, 35, 39]}"))) {}

   else if ( checkMonitorf31245a(u, "N_{[36, 38, 40, 41, 42, 45, 46, 47, 51]}")
 && (setMonitorf31245a(u, "F_53"))) {
 Verification.fail(" FAILED ON REGEXP EXPECT (((((!(makeDisabled))* ; makeDisabled) ; (!(withdrawFrom))*) ; makeActive))*");}

   else if ( checkMonitorf31245a(u, "N_{[41, 42, 43, 45, 47, 51]}")
 && (setMonitorf31245a(u, "F_53"))) {
 Verification.fail(" FAILED ON REGEXP EXPECT (((((!(makeDisabled))* ; makeDisabled) ; (!(withdrawFrom))*) ; makeActive))*");}

   else if ( checkMonitorf31245a(u, "N_{[36, 38, 40, 41, 42, 43, 45, 46, 47, 51]}")
 && (setMonitorf31245a(u, "F_53"))) {
 Verification.fail(" FAILED ON REGEXP EXPECT (((((!(makeDisabled))* ; makeDisabled) ; (!(withdrawFrom))*) ; makeActive))*");}

   else if ( checkMonitorf31245a(u, "F_53")
 && (setMonitorf31245a(u, "F_53"))) {
 Verification.fail(" FAILED ON REGEXP EXPECT (((((!(makeDisabled))* ; makeDisabled) ; (!(withdrawFrom))*) ; makeActive))*");}
}


before (UserInfo u): call(* *.makeDisabled(..)) &&
    target(u){ 
 if (1==0) {}
   else if ( checkMonitorf31245a(u, "S_{[29, 30, 31, 32, 33, 35, 37, 39, 44, 49, 52]}")
 && (setMonitorf31245a(u, "N_{[36, 38, 40, 41, 42, 45, 46, 47, 51]}"))) {}

   else if ( checkMonitorf31245a(u, "N_{[32, 33, 34, 35, 39]}")
 && (setMonitorf31245a(u, "N_{[36, 38, 40, 41, 42, 45, 46, 47, 51]}"))) {}

   else if ( checkMonitorf31245a(u, "N_{[36, 38, 40, 41, 42, 45, 46, 47, 51]}")
 && (setMonitorf31245a(u, "N_{[41, 42, 43, 45, 47, 51]}"))) {}

   else if ( checkMonitorf31245a(u, "N_{[41, 42, 43, 45, 47, 51]}")
 && (setMonitorf31245a(u, "N_{[41, 42, 43, 45, 47, 51]}"))) {}

   else if ( checkMonitorf31245a(u, "N_{[30, 31, 32, 33, 35, 37, 39, 41, 42, 43, 44, 45, 47, 48, 49, 50, 51]}")
 && (setMonitorf31245a(u, "N_{[36, 38, 40, 41, 42, 43, 45, 46, 47, 51]}"))) {}

   else if ( checkMonitorf31245a(u, "N_{[36, 38, 40, 41, 42, 43, 45, 46, 47, 51]}")
 && (setMonitorf31245a(u, "N_{[41, 42, 43, 45, 47, 51]}"))) {}

   else if ( checkMonitorf31245a(u, "N_{[30, 31, 32, 33, 34, 35, 37, 39, 41, 42, 43, 44, 45, 47, 48, 49, 50, 51]}")
 && (setMonitorf31245a(u, "N_{[36, 38, 40, 41, 42, 43, 45, 46, 47, 51]}"))) {}

   else if ( checkMonitorf31245a(u, "F_53")
 && (setMonitorf31245a(u, "F_53"))) {
 Verification.fail(" FAILED ON REGEXP EXPECT (((((!(makeDisabled))* ; makeDisabled) ; (!(withdrawFrom))*) ; makeActive))*");}
}


before (UserInfo u): call(* *.makeActive(..)) &&
    target(u){ 
 if (1==0) {}
   else if ( checkMonitorf31245a(u, "F_53")
 && (setMonitorf31245a(u, "F_53"))) {
 Verification.fail(" FAILED ON REGEXP EXPECT (((((!(makeDisabled))* ; makeDisabled) ; (!(withdrawFrom))*) ; makeActive))*");}

   else if ( checkMonitorf31245a(u, "S_{[29, 30, 31, 32, 33, 35, 37, 39, 44, 49, 52]}")
 && (setMonitorf31245a(u, "N_{[32, 33, 34, 35, 39]}"))) {}

   else if ( checkMonitorf31245a(u, "N_{[32, 33, 34, 35, 39]}")
 && (setMonitorf31245a(u, "N_{[32, 33, 34, 35, 39]}"))) {}

   else if ( checkMonitorf31245a(u, "N_{[36, 38, 40, 41, 42, 45, 46, 47, 51]}")
 && (setMonitorf31245a(u, "N_{[30, 31, 32, 33, 35, 37, 39, 41, 42, 43, 44, 45, 47, 48, 49, 50, 51]}"))) {}

   else if ( checkMonitorf31245a(u, "N_{[41, 42, 43, 45, 47, 51]}")
 && (setMonitorf31245a(u, "N_{[30, 31, 32, 33, 35, 37, 39, 41, 42, 43, 44, 45, 47, 48, 49, 50, 51]}"))) {}

   else if ( checkMonitorf31245a(u, "N_{[30, 31, 32, 33, 35, 37, 39, 41, 42, 43, 44, 45, 47, 48, 49, 50, 51]}")
 && (setMonitorf31245a(u, "N_{[30, 31, 32, 33, 34, 35, 37, 39, 41, 42, 43, 44, 45, 47, 48, 49, 50, 51]}"))) {}

   else if ( checkMonitorf31245a(u, "N_{[36, 38, 40, 41, 42, 43, 45, 46, 47, 51]}")
 && (setMonitorf31245a(u, "N_{[30, 31, 32, 33, 35, 37, 39, 41, 42, 43, 44, 45, 47, 48, 49, 50, 51]}"))) {}

   else if ( checkMonitorf31245a(u, "N_{[30, 31, 32, 33, 34, 35, 37, 39, 41, 42, 43, 44, 45, 47, 48, 49, 50, 51]}")
 && (setMonitorf31245a(u, "N_{[30, 31, 32, 33, 34, 35, 37, 39, 41, 42, 43, 44, 45, 47, 48, 49, 50, 51]}"))) {}
}




// the code for UNEXPECT: ((((?)* ; makeDisabled) ; (!(makeActive))*) ; withdrawFrom)
static HashMap<UserInfo, String> monitorsd6f6e28 = null;
static void initd6f6e28(){ monitorsd6f6e28= new HashMap<UserInfo, String>(); }



static boolean checkMonitord6f6e28(UserInfo target, String state) { 
 if (monitorsd6f6e28.containsKey(target))
  return state.equals(monitorsd6f6e28.get(target));
 else {
  monitorsd6f6e28.put(target, "S_{[54, 55, 56, 58, 60, 62, 67, 72, 75]}");
  return state.equals("S_{[54, 55, 56, 58, 60, 62, 67, 72, 75]}"); }
 }

static boolean setMonitord6f6e28(UserInfo target, String state) { 
 if (monitorsd6f6e28.containsKey(target))
 { monitorsd6f6e28.put(target,state);
   return true; 
 }
 else {
  return false; }
 }

before (UserInfo u): call(* *.withdrawFrom(..)) &&
    target(u){ 
 if (1==0) {}
   else if ( checkMonitord6f6e28(u, "S_{[54, 55, 56, 58, 60, 62, 67, 72, 75]}")
 && (setMonitord6f6e28(u, "N_{[55, 56, 57, 58, 62]}"))) {}

   else if ( checkMonitord6f6e28(u, "N_{[55, 56, 57, 58, 62]}")
 && (setMonitord6f6e28(u, "N_{[55, 56, 57, 58, 62]}"))) {}

   else if ( checkMonitord6f6e28(u, "N_{[55, 56, 57, 58, 59, 61, 62, 63, 64, 65, 68, 69, 70, 74]}")
 && (setMonitord6f6e28(u, "F_{[55, 56, 57, 58, 62, 64, 65, 66, 68, 70, 71, 73, 74]}"))) {
 Verification.fail(" FAILED ON REGEXP UNEXPECT ((((?)* ; makeDisabled) ; (!(makeActive))*) ; withdrawFrom)");}

   else if ( checkMonitord6f6e28(u, "F_{[55, 56, 57, 58, 62, 64, 65, 66, 68, 70, 71, 73, 74]}")
 && (setMonitord6f6e28(u, "F_{[55, 56, 57, 58, 62, 64, 65, 66, 68, 70, 71, 73, 74]}"))) {
 Verification.fail(" FAILED ON REGEXP UNEXPECT ((((?)* ; makeDisabled) ; (!(makeActive))*) ; withdrawFrom)");}

   else if ( checkMonitord6f6e28(u, "N_{[55, 56, 57, 58, 59, 61, 62, 63, 64, 65, 66, 68, 69, 70, 74]}")
 && (setMonitord6f6e28(u, "F_{[55, 56, 57, 58, 62, 64, 65, 66, 68, 70, 71, 73, 74]}"))) {
 Verification.fail(" FAILED ON REGEXP UNEXPECT ((((?)* ; makeDisabled) ; (!(makeActive))*) ; withdrawFrom)");}
}


before (UserInfo u): call(* *.makeDisabled(..)) &&
    target(u){ 
 if (1==0) {}
   else if ( checkMonitord6f6e28(u, "S_{[54, 55, 56, 58, 60, 62, 67, 72, 75]}")
 && (setMonitord6f6e28(u, "N_{[55, 56, 57, 58, 59, 61, 62, 63, 64, 65, 68, 69, 70, 74]}"))) {}

   else if ( checkMonitord6f6e28(u, "N_{[55, 56, 57, 58, 62]}")
 && (setMonitord6f6e28(u, "N_{[55, 56, 57, 58, 59, 61, 62, 63, 64, 65, 68, 69, 70, 74]}"))) {}

   else if ( checkMonitord6f6e28(u, "N_{[55, 56, 57, 58, 59, 61, 62, 63, 64, 65, 68, 69, 70, 74]}")
 && (setMonitord6f6e28(u, "N_{[55, 56, 57, 58, 59, 61, 62, 63, 64, 65, 66, 68, 69, 70, 74]}"))) {}

   else if ( checkMonitord6f6e28(u, "F_{[55, 56, 57, 58, 62, 64, 65, 66, 68, 70, 71, 73, 74]}")
 && (setMonitord6f6e28(u, "N_{[55, 56, 57, 58, 59, 61, 62, 63, 64, 65, 66, 68, 69, 70, 74]}"))) {}

   else if ( checkMonitord6f6e28(u, "N_{[55, 56, 57, 58, 59, 61, 62, 63, 64, 65, 66, 68, 69, 70, 74]}")
 && (setMonitord6f6e28(u, "N_{[55, 56, 57, 58, 59, 61, 62, 63, 64, 65, 66, 68, 69, 70, 74]}"))) {}
}


before (UserInfo u): call(* *.makeActive(..)) &&
    target(u){ 
 if (1==0) {}
   else if ( checkMonitord6f6e28(u, "S_{[54, 55, 56, 58, 60, 62, 67, 72, 75]}")
 && (setMonitord6f6e28(u, "N_{[55, 56, 57, 58, 62]}"))) {}

   else if ( checkMonitord6f6e28(u, "N_{[55, 56, 57, 58, 62]}")
 && (setMonitord6f6e28(u, "N_{[55, 56, 57, 58, 62]}"))) {}

   else if ( checkMonitord6f6e28(u, "N_{[55, 56, 57, 58, 59, 61, 62, 63, 64, 65, 68, 69, 70, 74]}")
 && (setMonitord6f6e28(u, "N_{[55, 56, 57, 58, 62]}"))) {}

   else if ( checkMonitord6f6e28(u, "F_{[55, 56, 57, 58, 62, 64, 65, 66, 68, 70, 71, 73, 74]}")
 && (setMonitord6f6e28(u, "N_{[55, 56, 57, 58, 62]}"))) {}

   else if ( checkMonitord6f6e28(u, "N_{[55, 56, 57, 58, 59, 61, 62, 63, 64, 65, 66, 68, 69, 70, 74]}")
 && (setMonitord6f6e28(u, "N_{[55, 56, 57, 58, 62]}"))) {}
}


public static void initialise() {init77327b6(); 
init4ae5a5(); 
initf31245a(); 
initd6f6e28(); 

}
}
