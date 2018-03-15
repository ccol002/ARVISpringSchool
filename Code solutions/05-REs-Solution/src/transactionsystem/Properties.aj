
package transactionsystem;

import re.structure.*;
import java.util.HashMap;




public aspect Properties {


// the code for EXPECT: (((!(USER_login))* ; ADMIN_initialise) ; (?)*)
static String monitorStateba5ba75 = null;
static void initba5ba75(){ 
 monitorStateba5ba75 = "S_{[246, 247, 248, 250, 252, 254, 259, 262]}"; }



static boolean checkMonitorba5ba75(String state) { return state.equals(monitorStateba5ba75); }

static boolean setMonitorba5ba75(String state) { 
  monitorStateba5ba75=state; 
  return true; }

before (): call(* *.ADMIN_initialise(..)){ 
 if (1==0) {}
   else if ( checkMonitorba5ba75("F_263")
 && (setMonitorba5ba75("F_263"))) {
 Verification.fail(" FAILED ON REGEXP EXPECT (((!(USER_login))* ; ADMIN_initialise) ; (?)*)");}

   else if ( checkMonitorba5ba75("S_{[246, 247, 248, 250, 252, 254, 259, 262]}")
 && (setMonitorba5ba75("N_{[247, 248, 249, 250, 251, 253, 254, 255, 256, 257, 260, 261]}"))) {}

   else if ( checkMonitorba5ba75("N_{[247, 248, 249, 250, 251, 253, 254, 255, 256, 257, 260, 261]}")
 && (setMonitorba5ba75("N_{[247, 248, 249, 250, 251, 253, 254, 255, 256, 257, 258, 260, 261]}"))) {}

   else if ( checkMonitorba5ba75("N_{[256, 257, 258, 260]}")
 && (setMonitorba5ba75("N_{[256, 257, 258, 260]}"))) {}

   else if ( checkMonitorba5ba75("N_{[247, 248, 249, 250, 251, 253, 254, 255, 256, 257, 258, 260, 261]}")
 && (setMonitorba5ba75("N_{[247, 248, 249, 250, 251, 253, 254, 255, 256, 257, 258, 260, 261]}"))) {}
}


before (): call(* *.USER_login(..)){ 
 if (1==0) {}
   else if ( checkMonitorba5ba75("S_{[246, 247, 248, 250, 252, 254, 259, 262]}")
 && (setMonitorba5ba75("F_263"))) {
 Verification.fail(" FAILED ON REGEXP EXPECT (((!(USER_login))* ; ADMIN_initialise) ; (?)*)");}

   else if ( checkMonitorba5ba75("F_263")
 && (setMonitorba5ba75("F_263"))) {
 Verification.fail(" FAILED ON REGEXP EXPECT (((!(USER_login))* ; ADMIN_initialise) ; (?)*)");}

   else if ( checkMonitorba5ba75("N_{[247, 248, 249, 250, 251, 253, 254, 255, 256, 257, 260, 261]}")
 && (setMonitorba5ba75("N_{[256, 257, 258, 260]}"))) {}

   else if ( checkMonitorba5ba75("N_{[256, 257, 258, 260]}")
 && (setMonitorba5ba75("N_{[256, 257, 258, 260]}"))) {}

   else if ( checkMonitorba5ba75("N_{[247, 248, 249, 250, 251, 253, 254, 255, 256, 257, 258, 260, 261]}")
 && (setMonitorba5ba75("N_{[256, 257, 258, 260]}"))) {}
}




// the code for UNEXPECT: ((!(ADMIN_initialise))* ; USER_login)
static String monitorStated748654 = null;
static void initd748654(){ 
 monitorStated748654 = "S_{[264, 265, 266, 268, 270, 272, 273]}"; }



static boolean checkMonitord748654(String state) { return state.equals(monitorStated748654); }

static boolean setMonitord748654(String state) { 
  monitorStated748654=state; 
  return true; }

before (): call(* *.ADMIN_initialise(..)){ 
 if (1==0) {}
   else if ( checkMonitord748654("N_274")
 && (setMonitord748654("N_274"))) {}

   else if ( checkMonitord748654("F_{[265, 266, 267, 268, 269, 271, 272]}")
 && (setMonitord748654("N_274"))) {}

   else if ( checkMonitord748654("S_{[264, 265, 266, 268, 270, 272, 273]}")
 && (setMonitord748654("N_274"))) {}
}


before (): call(* *.USER_login(..)){ 
 if (1==0) {}
   else if ( checkMonitord748654("N_274")
 && (setMonitord748654("N_274"))) {}

   else if ( checkMonitord748654("S_{[264, 265, 266, 268, 270, 272, 273]}")
 && (setMonitord748654("F_{[265, 266, 267, 268, 269, 271, 272]}"))) {
 Verification.fail(" FAILED ON REGEXP UNEXPECT ((!(ADMIN_initialise))* ; USER_login)");}

   else if ( checkMonitord748654("F_{[265, 266, 267, 268, 269, 271, 272]}")
 && (setMonitord748654("F_{[265, 266, 267, 268, 269, 271, 272]}"))) {
 Verification.fail(" FAILED ON REGEXP UNEXPECT ((!(ADMIN_initialise))* ; USER_login)");}
}




// the code for EXPECT: (((((!(makeDisabled))* ; makeDisabled) ; (!(withdrawFrom))*) ; makeActive))*
static HashMap<UserInfo, String> monitors1f24bb = null;
static void init1f24bb(){ monitors1f24bb= new HashMap<UserInfo, String>(); }



static boolean checkMonitor1f24bb(UserInfo target, String state) { 
 if (monitors1f24bb.containsKey(target))
  return state.equals(monitors1f24bb.get(target));
 else {
  monitors1f24bb.put(target, "S_{[275, 276, 277, 278, 279, 281, 283, 285, 290, 295, 298]}");
  return state.equals("S_{[275, 276, 277, 278, 279, 281, 283, 285, 290, 295, 298]}"); }
 }

static boolean setMonitor1f24bb(UserInfo target, String state) { 
 if (monitors1f24bb.containsKey(target))
 { monitors1f24bb.put(target,state);
   return true; 
 }
 else {
  return false; }
 }

before (UserInfo u): call(* *.withdrawFrom(..)) &&
    target(u){ 
 if (1==0) {}
   else if ( checkMonitor1f24bb(u, "F_299")
 && (setMonitor1f24bb(u, "F_299"))) {
 Verification.fail(" FAILED ON REGEXP EXPECT (((((!(makeDisabled))* ; makeDisabled) ; (!(withdrawFrom))*) ; makeActive))*");}

   else if ( checkMonitor1f24bb(u, "N_{[282, 284, 286, 287, 288, 289, 291, 292, 293, 297]}")
 && (setMonitor1f24bb(u, "F_299"))) {
 Verification.fail(" FAILED ON REGEXP EXPECT (((((!(makeDisabled))* ; makeDisabled) ; (!(withdrawFrom))*) ; makeActive))*");}

   else if ( checkMonitor1f24bb(u, "N_{[287, 288, 289, 291, 293, 297]}")
 && (setMonitor1f24bb(u, "F_299"))) {
 Verification.fail(" FAILED ON REGEXP EXPECT (((((!(makeDisabled))* ; makeDisabled) ; (!(withdrawFrom))*) ; makeActive))*");}

   else if ( checkMonitor1f24bb(u, "N_{[282, 284, 286, 287, 288, 291, 292, 293, 297]}")
 && (setMonitor1f24bb(u, "F_299"))) {
 Verification.fail(" FAILED ON REGEXP EXPECT (((((!(makeDisabled))* ; makeDisabled) ; (!(withdrawFrom))*) ; makeActive))*");}

   else if ( checkMonitor1f24bb(u, "S_{[275, 276, 277, 278, 279, 281, 283, 285, 290, 295, 298]}")
 && (setMonitor1f24bb(u, "N_{[278, 279, 280, 281, 285]}"))) {}

   else if ( checkMonitor1f24bb(u, "N_{[276, 277, 278, 279, 280, 281, 283, 285, 287, 288, 289, 290, 291, 293, 294, 295, 296, 297]}")
 && (setMonitor1f24bb(u, "N_{[278, 279, 280, 281, 285]}"))) {}

   else if ( checkMonitor1f24bb(u, "N_{[276, 277, 278, 279, 281, 283, 285, 287, 288, 289, 290, 291, 293, 294, 295, 296, 297]}")
 && (setMonitor1f24bb(u, "N_{[278, 279, 280, 281, 285]}"))) {}

   else if ( checkMonitor1f24bb(u, "N_{[278, 279, 280, 281, 285]}")
 && (setMonitor1f24bb(u, "N_{[278, 279, 280, 281, 285]}"))) {}
}


before (UserInfo u): call(* *.makeActive(..)) &&
    target(u){ 
 if (1==0) {}
   else if ( checkMonitor1f24bb(u, "F_299")
 && (setMonitor1f24bb(u, "F_299"))) {
 Verification.fail(" FAILED ON REGEXP EXPECT (((((!(makeDisabled))* ; makeDisabled) ; (!(withdrawFrom))*) ; makeActive))*");}

   else if ( checkMonitor1f24bb(u, "S_{[275, 276, 277, 278, 279, 281, 283, 285, 290, 295, 298]}")
 && (setMonitor1f24bb(u, "N_{[278, 279, 280, 281, 285]}"))) {}

   else if ( checkMonitor1f24bb(u, "N_{[282, 284, 286, 287, 288, 291, 292, 293, 297]}")
 && (setMonitor1f24bb(u, "N_{[276, 277, 278, 279, 281, 283, 285, 287, 288, 289, 290, 291, 293, 294, 295, 296, 297]}"))) {}

   else if ( checkMonitor1f24bb(u, "N_{[276, 277, 278, 279, 280, 281, 283, 285, 287, 288, 289, 290, 291, 293, 294, 295, 296, 297]}")
 && (setMonitor1f24bb(u, "N_{[276, 277, 278, 279, 280, 281, 283, 285, 287, 288, 289, 290, 291, 293, 294, 295, 296, 297]}"))) {}

   else if ( checkMonitor1f24bb(u, "N_{[282, 284, 286, 287, 288, 289, 291, 292, 293, 297]}")
 && (setMonitor1f24bb(u, "N_{[276, 277, 278, 279, 281, 283, 285, 287, 288, 289, 290, 291, 293, 294, 295, 296, 297]}"))) {}

   else if ( checkMonitor1f24bb(u, "N_{[276, 277, 278, 279, 281, 283, 285, 287, 288, 289, 290, 291, 293, 294, 295, 296, 297]}")
 && (setMonitor1f24bb(u, "N_{[276, 277, 278, 279, 280, 281, 283, 285, 287, 288, 289, 290, 291, 293, 294, 295, 296, 297]}"))) {}

   else if ( checkMonitor1f24bb(u, "N_{[287, 288, 289, 291, 293, 297]}")
 && (setMonitor1f24bb(u, "N_{[276, 277, 278, 279, 281, 283, 285, 287, 288, 289, 290, 291, 293, 294, 295, 296, 297]}"))) {}

   else if ( checkMonitor1f24bb(u, "N_{[278, 279, 280, 281, 285]}")
 && (setMonitor1f24bb(u, "N_{[278, 279, 280, 281, 285]}"))) {}
}


before (UserInfo u): call(* *.makeDisabled(..)) &&
    target(u){ 
 if (1==0) {}
   else if ( checkMonitor1f24bb(u, "S_{[275, 276, 277, 278, 279, 281, 283, 285, 290, 295, 298]}")
 && (setMonitor1f24bb(u, "N_{[282, 284, 286, 287, 288, 291, 292, 293, 297]}"))) {}

   else if ( checkMonitor1f24bb(u, "N_{[282, 284, 286, 287, 288, 291, 292, 293, 297]}")
 && (setMonitor1f24bb(u, "N_{[287, 288, 289, 291, 293, 297]}"))) {}

   else if ( checkMonitor1f24bb(u, "N_{[278, 279, 280, 281, 285]}")
 && (setMonitor1f24bb(u, "N_{[282, 284, 286, 287, 288, 291, 292, 293, 297]}"))) {}

   else if ( checkMonitor1f24bb(u, "N_{[276, 277, 278, 279, 280, 281, 283, 285, 287, 288, 289, 290, 291, 293, 294, 295, 296, 297]}")
 && (setMonitor1f24bb(u, "N_{[282, 284, 286, 287, 288, 289, 291, 292, 293, 297]}"))) {}

   else if ( checkMonitor1f24bb(u, "N_{[282, 284, 286, 287, 288, 289, 291, 292, 293, 297]}")
 && (setMonitor1f24bb(u, "N_{[287, 288, 289, 291, 293, 297]}"))) {}

   else if ( checkMonitor1f24bb(u, "N_{[276, 277, 278, 279, 281, 283, 285, 287, 288, 289, 290, 291, 293, 294, 295, 296, 297]}")
 && (setMonitor1f24bb(u, "N_{[282, 284, 286, 287, 288, 289, 291, 292, 293, 297]}"))) {}

   else if ( checkMonitor1f24bb(u, "N_{[287, 288, 289, 291, 293, 297]}")
 && (setMonitor1f24bb(u, "N_{[287, 288, 289, 291, 293, 297]}"))) {}

   else if ( checkMonitor1f24bb(u, "F_299")
 && (setMonitor1f24bb(u, "F_299"))) {
 Verification.fail(" FAILED ON REGEXP EXPECT (((((!(makeDisabled))* ; makeDisabled) ; (!(withdrawFrom))*) ; makeActive))*");}
}




// the code for UNEXPECT: ((((?)* ; makeDisabled) ; (!(makeActive))*) ; withdrawFrom)
static HashMap<UserInfo, String> monitors7a477b7 = null;
static void init7a477b7(){ monitors7a477b7= new HashMap<UserInfo, String>(); }



static boolean checkMonitor7a477b7(UserInfo target, String state) { 
 if (monitors7a477b7.containsKey(target))
  return state.equals(monitors7a477b7.get(target));
 else {
  monitors7a477b7.put(target, "S_{[300, 301, 302, 304, 306, 308, 313, 318, 321]}");
  return state.equals("S_{[300, 301, 302, 304, 306, 308, 313, 318, 321]}"); }
 }

static boolean setMonitor7a477b7(UserInfo target, String state) { 
 if (monitors7a477b7.containsKey(target))
 { monitors7a477b7.put(target,state);
   return true; 
 }
 else {
  return false; }
 }

before (UserInfo u): call(* *.withdrawFrom(..)) &&
    target(u){ 
 if (1==0) {}
   else if ( checkMonitor7a477b7(u, "S_{[300, 301, 302, 304, 306, 308, 313, 318, 321]}")
 && (setMonitor7a477b7(u, "N_{[301, 302, 303, 304, 308]}"))) {}

   else if ( checkMonitor7a477b7(u, "F_{[301, 302, 303, 304, 308, 310, 311, 312, 314, 316, 317, 319, 320]}")
 && (setMonitor7a477b7(u, "F_{[301, 302, 303, 304, 308, 310, 311, 312, 314, 316, 317, 319, 320]}"))) {
 Verification.fail(" FAILED ON REGEXP UNEXPECT ((((?)* ; makeDisabled) ; (!(makeActive))*) ; withdrawFrom)");}

   else if ( checkMonitor7a477b7(u, "N_{[301, 302, 303, 304, 305, 307, 308, 309, 310, 311, 312, 314, 315, 316, 320]}")
 && (setMonitor7a477b7(u, "F_{[301, 302, 303, 304, 308, 310, 311, 312, 314, 316, 317, 319, 320]}"))) {
 Verification.fail(" FAILED ON REGEXP UNEXPECT ((((?)* ; makeDisabled) ; (!(makeActive))*) ; withdrawFrom)");}

   else if ( checkMonitor7a477b7(u, "N_{[301, 302, 303, 304, 308]}")
 && (setMonitor7a477b7(u, "N_{[301, 302, 303, 304, 308]}"))) {}

   else if ( checkMonitor7a477b7(u, "N_{[301, 302, 303, 304, 305, 307, 308, 309, 310, 311, 314, 315, 316, 320]}")
 && (setMonitor7a477b7(u, "F_{[301, 302, 303, 304, 308, 310, 311, 312, 314, 316, 317, 319, 320]}"))) {
 Verification.fail(" FAILED ON REGEXP UNEXPECT ((((?)* ; makeDisabled) ; (!(makeActive))*) ; withdrawFrom)");}
}


before (UserInfo u): call(* *.makeActive(..)) &&
    target(u){ 
 if (1==0) {}
   else if ( checkMonitor7a477b7(u, "S_{[300, 301, 302, 304, 306, 308, 313, 318, 321]}")
 && (setMonitor7a477b7(u, "N_{[301, 302, 303, 304, 308]}"))) {}

   else if ( checkMonitor7a477b7(u, "F_{[301, 302, 303, 304, 308, 310, 311, 312, 314, 316, 317, 319, 320]}")
 && (setMonitor7a477b7(u, "N_{[301, 302, 303, 304, 308]}"))) {}

   else if ( checkMonitor7a477b7(u, "N_{[301, 302, 303, 304, 305, 307, 308, 309, 310, 311, 312, 314, 315, 316, 320]}")
 && (setMonitor7a477b7(u, "N_{[301, 302, 303, 304, 308]}"))) {}

   else if ( checkMonitor7a477b7(u, "N_{[301, 302, 303, 304, 308]}")
 && (setMonitor7a477b7(u, "N_{[301, 302, 303, 304, 308]}"))) {}

   else if ( checkMonitor7a477b7(u, "N_{[301, 302, 303, 304, 305, 307, 308, 309, 310, 311, 314, 315, 316, 320]}")
 && (setMonitor7a477b7(u, "N_{[301, 302, 303, 304, 308]}"))) {}
}


before (UserInfo u): call(* *.makeDisabled(..)) &&
    target(u){ 
 if (1==0) {}
   else if ( checkMonitor7a477b7(u, "S_{[300, 301, 302, 304, 306, 308, 313, 318, 321]}")
 && (setMonitor7a477b7(u, "N_{[301, 302, 303, 304, 305, 307, 308, 309, 310, 311, 314, 315, 316, 320]}"))) {}

   else if ( checkMonitor7a477b7(u, "F_{[301, 302, 303, 304, 308, 310, 311, 312, 314, 316, 317, 319, 320]}")
 && (setMonitor7a477b7(u, "N_{[301, 302, 303, 304, 305, 307, 308, 309, 310, 311, 312, 314, 315, 316, 320]}"))) {}

   else if ( checkMonitor7a477b7(u, "N_{[301, 302, 303, 304, 305, 307, 308, 309, 310, 311, 312, 314, 315, 316, 320]}")
 && (setMonitor7a477b7(u, "N_{[301, 302, 303, 304, 305, 307, 308, 309, 310, 311, 312, 314, 315, 316, 320]}"))) {}

   else if ( checkMonitor7a477b7(u, "N_{[301, 302, 303, 304, 308]}")
 && (setMonitor7a477b7(u, "N_{[301, 302, 303, 304, 305, 307, 308, 309, 310, 311, 314, 315, 316, 320]}"))) {}

   else if ( checkMonitor7a477b7(u, "N_{[301, 302, 303, 304, 305, 307, 308, 309, 310, 311, 314, 315, 316, 320]}")
 && (setMonitor7a477b7(u, "N_{[301, 302, 303, 304, 305, 307, 308, 309, 310, 311, 312, 314, 315, 316, 320]}"))) {}
}




// the code for EXPECT: (((((!(greylist))* ; greylist) ; (((((((!(whitelist))* ; depositTo) ; (!(whitelist))*) ; depositTo) ; (!(whitelist))*) ; depositTo) ; (!(whitelist))*)) ; whitelist))*
static HashMap<UserInfo, String> monitorseafccbe = null;
static void initeafccbe(){ monitorseafccbe= new HashMap<UserInfo, String>(); }



static boolean checkMonitoreafccbe(UserInfo target, String state) { 
 if (monitorseafccbe.containsKey(target))
  return state.equals(monitorseafccbe.get(target));
 else {
  monitorseafccbe.put(target, "S_{[323, 324, 325, 326, 327, 329, 331, 333, 374, 379, 382]}");
  return state.equals("S_{[323, 324, 325, 326, 327, 329, 331, 333, 374, 379, 382]}"); }
 }

static boolean setMonitoreafccbe(UserInfo target, String state) { 
 if (monitorseafccbe.containsKey(target))
 { monitorseafccbe.put(target,state);
   return true; 
 }
 else {
  return false; }
 }

before (UserInfo u): call(* *.depositTo(..)) &&
    target(u){ 
 if (1==0) {}
   else if ( checkMonitoreafccbe(u, "S_{[323, 324, 325, 326, 327, 329, 331, 333, 374, 379, 382]}")
 && (setMonitoreafccbe(u, "N_{[326, 327, 328, 329, 333]}"))) {}

   else if ( checkMonitoreafccbe(u, "N_{[330, 332, 334, 335, 336, 338, 340, 342, 347, 352, 359, 364, 371, 376]}")
 && (setMonitoreafccbe(u, "N_{[335, 336, 337, 338, 339, 341, 342, 343, 344, 345, 348, 349, 350, 354]}"))) {}

   else if ( checkMonitoreafccbe(u, "N_{[335, 336, 337, 338, 339, 341, 342, 343, 344, 345, 346, 348, 349, 350, 351, 353, 354, 355, 356, 357, 358, 360, 361, 362, 363, 365, 366, 367, 368, 369, 372, 373, 375, 377, 381]}")
 && (setMonitoreafccbe(u, "N_{[335, 336, 337, 338, 339, 341, 342, 343, 344, 345, 346, 348, 349, 350, 351, 353, 354, 355, 356, 357, 358, 360, 361, 362, 363, 365, 366, 367, 368, 369, 370, 372, 373, 375, 377, 381]}"))) {}

   else if ( checkMonitoreafccbe(u, "N_{[324, 325, 326, 327, 329, 331, 333, 374, 378, 379, 380]}")
 && (setMonitoreafccbe(u, "N_{[326, 327, 328, 329, 333]}"))) {}

   else if ( checkMonitoreafccbe(u, "N_{[335, 336, 337, 338, 342, 344, 345, 346, 348, 350, 354, 356, 357, 358, 360, 362, 366, 368, 369, 370, 372, 375, 377, 381]}")
 && (setMonitoreafccbe(u, "N_{[335, 336, 337, 338, 339, 341, 342, 343, 344, 345, 346, 348, 349, 350, 351, 353, 354, 355, 356, 357, 358, 360, 361, 362, 363, 365, 366, 367, 368, 369, 370, 372, 373, 375, 377, 381]}"))) {}

   else if ( checkMonitoreafccbe(u, "N_{[335, 336, 337, 338, 339, 341, 342, 343, 344, 345, 346, 348, 349, 350, 351, 353, 354, 355, 356, 357, 358, 360, 361, 362, 363, 365, 366, 367, 368, 369, 370, 372, 373, 375, 377, 381]}")
 && (setMonitoreafccbe(u, "N_{[335, 336, 337, 338, 339, 341, 342, 343, 344, 345, 346, 348, 349, 350, 351, 353, 354, 355, 356, 357, 358, 360, 361, 362, 363, 365, 366, 367, 368, 369, 370, 372, 373, 375, 377, 381]}"))) {}

   else if ( checkMonitoreafccbe(u, "N_{[335, 336, 337, 338, 342, 344, 345, 346, 348, 350, 354, 356, 357, 358, 360, 362, 366]}")
 && (setMonitoreafccbe(u, "N_{[335, 336, 337, 338, 339, 341, 342, 343, 344, 345, 346, 348, 349, 350, 351, 353, 354, 355, 356, 357, 358, 360, 361, 362, 363, 365, 366, 367, 368, 369, 372, 373, 375, 377, 381]}"))) {}

   else if ( checkMonitoreafccbe(u, "N_{[335, 336, 337, 338, 339, 341, 342, 343, 344, 345, 346, 348, 349, 350, 351, 353, 354, 355, 356, 357, 360, 361, 362, 366]}")
 && (setMonitoreafccbe(u, "N_{[335, 336, 337, 338, 339, 341, 342, 343, 344, 345, 346, 348, 349, 350, 351, 353, 354, 355, 356, 357, 358, 360, 361, 362, 363, 365, 366, 367, 368, 369, 372, 373, 375, 377, 381]}"))) {}

   else if ( checkMonitoreafccbe(u, "N_{[335, 336, 337, 338, 342, 344, 345, 346, 348, 350, 354]}")
 && (setMonitoreafccbe(u, "N_{[335, 336, 337, 338, 339, 341, 342, 343, 344, 345, 346, 348, 349, 350, 351, 353, 354, 355, 356, 357, 360, 361, 362, 366]}"))) {}

   else if ( checkMonitoreafccbe(u, "N_{[335, 336, 337, 338, 339, 341, 342, 343, 344, 345, 348, 349, 350, 354]}")
 && (setMonitoreafccbe(u, "N_{[335, 336, 337, 338, 339, 341, 342, 343, 344, 345, 346, 348, 349, 350, 351, 353, 354, 355, 356, 357, 360, 361, 362, 366]}"))) {}

   else if ( checkMonitoreafccbe(u, "N_{[335, 336, 337, 338, 342]}")
 && (setMonitoreafccbe(u, "N_{[335, 336, 337, 338, 339, 341, 342, 343, 344, 345, 348, 349, 350, 354]}"))) {}

   else if ( checkMonitoreafccbe(u, "N_{[326, 327, 328, 329, 333]}")
 && (setMonitoreafccbe(u, "N_{[326, 327, 328, 329, 333]}"))) {}

   else if ( checkMonitoreafccbe(u, "F_383")
 && (setMonitoreafccbe(u, "F_383"))) {
 Verification.fail(" FAILED ON REGEXP EXPECT (((((!(greylist))* ; greylist) ; (((((((!(whitelist))* ; depositTo) ; (!(whitelist))*) ; depositTo) ; (!(whitelist))*) ; depositTo) ; (!(whitelist))*)) ; whitelist))*");}
}


before (UserInfo u): call(* *.greylist(..)) &&
    target(u){ 
 if (1==0) {}
   else if ( checkMonitoreafccbe(u, "S_{[323, 324, 325, 326, 327, 329, 331, 333, 374, 379, 382]}")
 && (setMonitoreafccbe(u, "N_{[330, 332, 334, 335, 336, 338, 340, 342, 347, 352, 359, 364, 371, 376]}"))) {}

   else if ( checkMonitoreafccbe(u, "N_{[330, 332, 334, 335, 336, 338, 340, 342, 347, 352, 359, 364, 371, 376]}")
 && (setMonitoreafccbe(u, "N_{[335, 336, 337, 338, 342]}"))) {}

   else if ( checkMonitoreafccbe(u, "N_{[335, 336, 337, 338, 339, 341, 342, 343, 344, 345, 346, 348, 349, 350, 351, 353, 354, 355, 356, 357, 358, 360, 361, 362, 363, 365, 366, 367, 368, 369, 372, 373, 375, 377, 381]}")
 && (setMonitoreafccbe(u, "N_{[335, 336, 337, 338, 342, 344, 345, 346, 348, 350, 354, 356, 357, 358, 360, 362, 366, 368, 369, 370, 372, 375, 377, 381]}"))) {}

   else if ( checkMonitoreafccbe(u, "N_{[324, 325, 326, 327, 329, 331, 333, 374, 378, 379, 380]}")
 && (setMonitoreafccbe(u, "N_{[330, 332, 334, 335, 336, 338, 340, 342, 347, 352, 359, 364, 371, 376]}"))) {}

   else if ( checkMonitoreafccbe(u, "N_{[335, 336, 337, 338, 342, 344, 345, 346, 348, 350, 354, 356, 357, 358, 360, 362, 366, 368, 369, 370, 372, 375, 377, 381]}")
 && (setMonitoreafccbe(u, "N_{[335, 336, 337, 338, 342, 344, 345, 346, 348, 350, 354, 356, 357, 358, 360, 362, 366, 368, 369, 370, 372, 375, 377, 381]}"))) {}

   else if ( checkMonitoreafccbe(u, "N_{[335, 336, 337, 338, 339, 341, 342, 343, 344, 345, 346, 348, 349, 350, 351, 353, 354, 355, 356, 357, 358, 360, 361, 362, 363, 365, 366, 367, 368, 369, 370, 372, 373, 375, 377, 381]}")
 && (setMonitoreafccbe(u, "N_{[335, 336, 337, 338, 342, 344, 345, 346, 348, 350, 354, 356, 357, 358, 360, 362, 366, 368, 369, 370, 372, 375, 377, 381]}"))) {}

   else if ( checkMonitoreafccbe(u, "N_{[335, 336, 337, 338, 342, 344, 345, 346, 348, 350, 354, 356, 357, 358, 360, 362, 366]}")
 && (setMonitoreafccbe(u, "N_{[335, 336, 337, 338, 342, 344, 345, 346, 348, 350, 354, 356, 357, 358, 360, 362, 366]}"))) {}

   else if ( checkMonitoreafccbe(u, "N_{[335, 336, 337, 338, 339, 341, 342, 343, 344, 345, 346, 348, 349, 350, 351, 353, 354, 355, 356, 357, 360, 361, 362, 366]}")
 && (setMonitoreafccbe(u, "N_{[335, 336, 337, 338, 342, 344, 345, 346, 348, 350, 354, 356, 357, 358, 360, 362, 366]}"))) {}

   else if ( checkMonitoreafccbe(u, "N_{[335, 336, 337, 338, 342, 344, 345, 346, 348, 350, 354]}")
 && (setMonitoreafccbe(u, "N_{[335, 336, 337, 338, 342, 344, 345, 346, 348, 350, 354]}"))) {}

   else if ( checkMonitoreafccbe(u, "N_{[335, 336, 337, 338, 339, 341, 342, 343, 344, 345, 348, 349, 350, 354]}")
 && (setMonitoreafccbe(u, "N_{[335, 336, 337, 338, 342, 344, 345, 346, 348, 350, 354]}"))) {}

   else if ( checkMonitoreafccbe(u, "N_{[335, 336, 337, 338, 342]}")
 && (setMonitoreafccbe(u, "N_{[335, 336, 337, 338, 342]}"))) {}

   else if ( checkMonitoreafccbe(u, "N_{[326, 327, 328, 329, 333]}")
 && (setMonitoreafccbe(u, "N_{[330, 332, 334, 335, 336, 338, 340, 342, 347, 352, 359, 364, 371, 376]}"))) {}

   else if ( checkMonitoreafccbe(u, "F_383")
 && (setMonitoreafccbe(u, "F_383"))) {
 Verification.fail(" FAILED ON REGEXP EXPECT (((((!(greylist))* ; greylist) ; (((((((!(whitelist))* ; depositTo) ; (!(whitelist))*) ; depositTo) ; (!(whitelist))*) ; depositTo) ; (!(whitelist))*)) ; whitelist))*");}
}


before (UserInfo u): call(* *.whitelist(..)) &&
    target(u){ 
 if (1==0) {}
   else if ( checkMonitoreafccbe(u, "N_{[330, 332, 334, 335, 336, 338, 340, 342, 347, 352, 359, 364, 371, 376]}")
 && (setMonitoreafccbe(u, "F_383"))) {
 Verification.fail(" FAILED ON REGEXP EXPECT (((((!(greylist))* ; greylist) ; (((((((!(whitelist))* ; depositTo) ; (!(whitelist))*) ; depositTo) ; (!(whitelist))*) ; depositTo) ; (!(whitelist))*)) ; whitelist))*");}

   else if ( checkMonitoreafccbe(u, "N_{[335, 336, 337, 338, 342, 344, 345, 346, 348, 350, 354, 356, 357, 358, 360, 362, 366]}")
 && (setMonitoreafccbe(u, "F_383"))) {
 Verification.fail(" FAILED ON REGEXP EXPECT (((((!(greylist))* ; greylist) ; (((((((!(whitelist))* ; depositTo) ; (!(whitelist))*) ; depositTo) ; (!(whitelist))*) ; depositTo) ; (!(whitelist))*)) ; whitelist))*");}

   else if ( checkMonitoreafccbe(u, "N_{[335, 336, 337, 338, 339, 341, 342, 343, 344, 345, 346, 348, 349, 350, 351, 353, 354, 355, 356, 357, 360, 361, 362, 366]}")
 && (setMonitoreafccbe(u, "F_383"))) {
 Verification.fail(" FAILED ON REGEXP EXPECT (((((!(greylist))* ; greylist) ; (((((((!(whitelist))* ; depositTo) ; (!(whitelist))*) ; depositTo) ; (!(whitelist))*) ; depositTo) ; (!(whitelist))*)) ; whitelist))*");}

   else if ( checkMonitoreafccbe(u, "N_{[335, 336, 337, 338, 342, 344, 345, 346, 348, 350, 354]}")
 && (setMonitoreafccbe(u, "F_383"))) {
 Verification.fail(" FAILED ON REGEXP EXPECT (((((!(greylist))* ; greylist) ; (((((((!(whitelist))* ; depositTo) ; (!(whitelist))*) ; depositTo) ; (!(whitelist))*) ; depositTo) ; (!(whitelist))*)) ; whitelist))*");}

   else if ( checkMonitoreafccbe(u, "N_{[335, 336, 337, 338, 339, 341, 342, 343, 344, 345, 348, 349, 350, 354]}")
 && (setMonitoreafccbe(u, "F_383"))) {
 Verification.fail(" FAILED ON REGEXP EXPECT (((((!(greylist))* ; greylist) ; (((((((!(whitelist))* ; depositTo) ; (!(whitelist))*) ; depositTo) ; (!(whitelist))*) ; depositTo) ; (!(whitelist))*)) ; whitelist))*");}

   else if ( checkMonitoreafccbe(u, "N_{[335, 336, 337, 338, 342]}")
 && (setMonitoreafccbe(u, "F_383"))) {
 Verification.fail(" FAILED ON REGEXP EXPECT (((((!(greylist))* ; greylist) ; (((((((!(whitelist))* ; depositTo) ; (!(whitelist))*) ; depositTo) ; (!(whitelist))*) ; depositTo) ; (!(whitelist))*)) ; whitelist))*");}

   else if ( checkMonitoreafccbe(u, "S_{[323, 324, 325, 326, 327, 329, 331, 333, 374, 379, 382]}")
 && (setMonitoreafccbe(u, "N_{[326, 327, 328, 329, 333]}"))) {}

   else if ( checkMonitoreafccbe(u, "N_{[326, 327, 328, 329, 333]}")
 && (setMonitoreafccbe(u, "N_{[326, 327, 328, 329, 333]}"))) {}

   else if ( checkMonitoreafccbe(u, "N_{[335, 336, 337, 338, 339, 341, 342, 343, 344, 345, 346, 348, 349, 350, 351, 353, 354, 355, 356, 357, 358, 360, 361, 362, 363, 365, 366, 367, 368, 369, 372, 373, 375, 377, 381]}")
 && (setMonitoreafccbe(u, "N_{[324, 325, 326, 327, 329, 331, 333, 374, 378, 379, 380]}"))) {}

   else if ( checkMonitoreafccbe(u, "N_{[324, 325, 326, 327, 329, 331, 333, 374, 378, 379, 380]}")
 && (setMonitoreafccbe(u, "N_{[326, 327, 328, 329, 333]}"))) {}

   else if ( checkMonitoreafccbe(u, "N_{[335, 336, 337, 338, 342, 344, 345, 346, 348, 350, 354, 356, 357, 358, 360, 362, 366, 368, 369, 370, 372, 375, 377, 381]}")
 && (setMonitoreafccbe(u, "N_{[324, 325, 326, 327, 329, 331, 333, 374, 378, 379, 380]}"))) {}

   else if ( checkMonitoreafccbe(u, "N_{[335, 336, 337, 338, 339, 341, 342, 343, 344, 345, 346, 348, 349, 350, 351, 353, 354, 355, 356, 357, 358, 360, 361, 362, 363, 365, 366, 367, 368, 369, 370, 372, 373, 375, 377, 381]}")
 && (setMonitoreafccbe(u, "N_{[324, 325, 326, 327, 329, 331, 333, 374, 378, 379, 380]}"))) {}

   else if ( checkMonitoreafccbe(u, "F_383")
 && (setMonitoreafccbe(u, "F_383"))) {
 Verification.fail(" FAILED ON REGEXP EXPECT (((((!(greylist))* ; greylist) ; (((((((!(whitelist))* ; depositTo) ; (!(whitelist))*) ; depositTo) ; (!(whitelist))*) ; depositTo) ; (!(whitelist))*)) ; whitelist))*");}
}




// the code for UNEXPECT: ((((?)* ; greylist) ; (((((((((!(depositTo))* + (!(depositTo))*) ; depositTo) ; (!(depositTo))*) + (!(depositTo))*) ; depositTo) ; (!(depositTo))*) ; depositTo) ; (!(depositTo))*)) ; whitelist)
static HashMap<UserInfo, String> monitors7aaf64d = null;
static void init7aaf64d(){ monitors7aaf64d= new HashMap<UserInfo, String>(); }



static boolean checkMonitor7aaf64d(UserInfo target, String state) { 
 if (monitors7aaf64d.containsKey(target))
  return state.equals(monitors7aaf64d.get(target));
 else {
  monitors7aaf64d.put(target, "S_{[384, 385, 386, 388, 390, 392, 445, 450, 453]}");
  return state.equals("S_{[384, 385, 386, 388, 390, 392, 445, 450, 453]}"); }
 }

static boolean setMonitor7aaf64d(UserInfo target, String state) { 
 if (monitors7aaf64d.containsKey(target))
 { monitors7aaf64d.put(target,state);
   return true; 
 }
 else {
  return false; }
 }

before (UserInfo u): call(* *.depositTo(..)) &&
    target(u){ 
 if (1==0) {}
   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 405, 407, 412, 415, 416, 417, 418, 419, 420, 421, 423, 425, 430, 435, 439, 440, 441, 442, 443, 446, 447, 448, 452]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 404, 406, 408, 409, 410, 413, 414, 420, 421, 422, 424, 425, 426, 427, 428, 431, 432, 433, 437]}"))) {}

   else if ( checkMonitor7aaf64d(u, "F_{[385, 386, 387, 388, 392, 394, 395, 396, 398, 399, 400, 402, 403, 407, 409, 410, 411, 413, 416, 417, 418, 420, 421, 425, 427, 428, 429, 431, 433, 437, 439, 440, 441, 443, 446, 448, 449, 451, 452]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 404, 406, 408, 409, 410, 413, 414, 420, 421, 422, 424, 425, 426, 427, 428, 431, 432, 433, 434, 436, 437, 438, 439, 440, 443, 444, 446, 448, 452]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 405, 407, 409, 410, 411, 412, 413, 415, 416, 417, 418, 419, 420, 421, 423, 425, 427, 428, 429, 430, 431, 433, 435, 437, 439, 440, 441, 442, 443, 446, 447, 448, 452]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 404, 406, 408, 409, 410, 413, 414, 420, 421, 422, 424, 425, 426, 427, 428, 431, 432, 433, 434, 436, 437, 438, 439, 440, 443, 444, 446, 448, 452]}"))) {}

   else if ( checkMonitor7aaf64d(u, "F_{[385, 386, 387, 388, 392, 439, 440, 441, 443, 446, 448, 449, 451, 452]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 397, 398, 399, 401, 402, 403, 405, 407, 412, 415, 416, 417, 419, 420, 421, 423, 425, 430, 435, 439, 440, 441, 442, 443, 446, 447, 448, 452]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 404, 406, 408, 409, 410, 413, 414, 420, 421, 422, 424, 425, 426, 427, 428, 431, 432, 433, 437]}"))) {}

   else if ( checkMonitor7aaf64d(u, "F_{[385, 386, 387, 388, 392, 394, 395, 396, 398, 399, 400, 402, 403, 407, 416, 417, 418, 420, 421, 425, 427, 428, 429, 431, 433, 437, 439, 440, 441, 443, 446, 448, 449, 451, 452]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 404, 406, 408, 409, 410, 413, 414, 420, 421, 422, 424, 425, 426, 427, 428, 431, 432, 433, 434, 436, 437, 438, 439, 440, 443, 444, 446, 448, 452]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 405, 407, 412, 415, 416, 417, 418, 419, 420, 421, 423, 425, 427, 428, 429, 430, 431, 433, 435, 437, 439, 440, 441, 442, 443, 446, 447, 448, 452]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 404, 406, 408, 409, 410, 413, 414, 420, 421, 422, 424, 425, 426, 427, 428, 431, 432, 433, 434, 436, 437, 438, 439, 440, 443, 444, 446, 448, 452]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 397, 398, 399, 401, 402, 403, 405, 407, 409, 410, 411, 412, 413, 415, 416, 417, 419, 420, 421, 423, 425, 427, 428, 429, 430, 431, 433, 435, 437, 439, 440, 441, 442, 443, 446, 447, 448, 452]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 404, 406, 408, 409, 410, 413, 414, 420, 421, 422, 424, 425, 426, 427, 428, 431, 432, 433, 434, 436, 437, 438, 439, 440, 443, 444, 446, 448, 452]}"))) {}

   else if ( checkMonitor7aaf64d(u, "F_{[385, 386, 387, 388, 392, 409, 410, 411, 413, 420, 421, 425, 427, 428, 429, 431, 433, 437, 439, 440, 441, 443, 446, 448, 449, 451, 452]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 422, 424, 426, 427, 428, 431, 432, 433, 434, 436, 437, 438, 439, 440, 443, 444, 446, 448, 452]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 404, 406, 408, 409, 410, 413, 414, 420, 421, 422, 424, 425, 426, 427, 428, 431, 432, 433, 437]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 422, 424, 426, 427, 428, 431, 432, 433, 434, 436, 437, 438, 439, 440, 443, 444, 446, 448, 452]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 409, 410, 411, 413, 420, 421, 425, 427, 428, 429, 431, 433, 437]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 422, 424, 426, 427, 428, 431, 432, 433, 434, 436, 437, 438, 439, 440, 443, 444, 446, 448, 452]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 397, 398, 399, 401, 402, 403, 405, 407, 409, 410, 411, 412, 413, 415, 416, 417, 419, 420, 421, 423, 425, 427, 428, 429, 430, 431, 433, 435, 437, 442, 447]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 404, 406, 408, 409, 410, 413, 414, 420, 421, 422, 424, 425, 426, 427, 428, 431, 432, 433, 434, 436, 437, 438, 439, 440, 443, 444, 446, 448, 452]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 422, 424, 426, 427, 428, 431, 432, 433, 434, 436, 437, 438, 439, 440, 443, 444, 446, 448, 452]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 434, 436, 438, 439, 440, 443, 444, 446, 448, 452]}"))) {}

   else if ( checkMonitor7aaf64d(u, "F_{[385, 386, 387, 388, 392, 394, 395, 396, 398, 399, 400, 402, 403, 407, 416, 417, 418, 420, 421, 425, 439, 440, 441, 443, 446, 448, 449, 451, 452]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 404, 406, 408, 409, 410, 413, 414, 420, 421, 422, 424, 425, 426, 427, 428, 431, 432, 433, 437]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 434, 436, 438, 439, 440, 443, 444, 446, 448, 452]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 397, 398, 399, 401, 402, 403, 405, 407, 412, 415, 416, 417, 419, 420, 421, 423, 425, 427, 428, 429, 430, 431, 433, 435, 437, 439, 440, 441, 442, 443, 446, 447, 448, 452]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 404, 406, 408, 409, 410, 413, 414, 420, 421, 422, 424, 425, 426, 427, 428, 431, 432, 433, 434, 436, 437, 438, 439, 440, 443, 444, 446, 448, 452]}"))) {}

   else if ( checkMonitor7aaf64d(u, "F_{[385, 386, 387, 388, 392, 427, 428, 429, 431, 433, 437, 439, 440, 441, 443, 446, 448, 449, 451, 452]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 434, 436, 438, 439, 440, 443, 444, 446, 448, 452]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 405, 407, 412, 415, 416, 417, 418, 419, 420, 421, 423, 425, 430, 435, 442, 447]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 404, 406, 408, 409, 410, 413, 414, 420, 421, 422, 424, 425, 426, 427, 428, 431, 432, 433, 437]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 394, 395, 396, 398, 399, 400, 402, 403, 407, 416, 417, 418, 420, 421, 425]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 404, 406, 408, 409, 410, 413, 414, 420, 421, 422, 424, 425, 426, 427, 428, 431, 432, 433, 437]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 397, 398, 399, 401, 402, 403, 405, 407, 412, 415, 416, 417, 419, 420, 421, 423, 425, 430, 435, 442, 447]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 404, 406, 408, 409, 410, 413, 414, 420, 421, 422, 424, 425, 426, 427, 428, 431, 432, 433, 437]}"))) {}

   else if ( checkMonitor7aaf64d(u, "S_{[384, 385, 386, 388, 390, 392, 445, 450, 453]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 394, 395, 396, 398, 399, 400, 402, 403, 407, 409, 410, 411, 413, 416, 417, 418, 420, 421, 425, 427, 428, 429, 431, 433, 437]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 404, 406, 408, 409, 410, 413, 414, 420, 421, 422, 424, 425, 426, 427, 428, 431, 432, 433, 434, 436, 437, 438, 439, 440, 443, 444, 446, 448, 452]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 405, 407, 409, 410, 411, 412, 413, 415, 416, 417, 418, 419, 420, 421, 423, 425, 427, 428, 429, 430, 431, 433, 435, 437, 442, 447]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 404, 406, 408, 409, 410, 413, 414, 420, 421, 422, 424, 425, 426, 427, 428, 431, 432, 433, 434, 436, 437, 438, 439, 440, 443, 444, 446, 448, 452]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 404, 406, 408, 409, 410, 413, 414, 420, 421, 422, 424, 425, 426, 427, 428, 431, 432, 433, 434, 436, 437, 438, 439, 440, 443, 444, 446, 448, 452]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 422, 424, 426, 427, 428, 431, 432, 433, 434, 436, 437, 438, 439, 440, 443, 444, 446, 448, 452]}"))) {}
}


before (UserInfo u): call(* *.greylist(..)) &&
    target(u){ 
 if (1==0) {}
   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 405, 407, 412, 415, 416, 417, 418, 419, 420, 421, 423, 425, 430, 435, 439, 440, 441, 442, 443, 446, 447, 448, 452]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 405, 407, 412, 415, 416, 417, 418, 419, 420, 421, 423, 425, 430, 435, 439, 440, 441, 442, 443, 446, 447, 448, 452]}"))) {}

   else if ( checkMonitor7aaf64d(u, "F_{[385, 386, 387, 388, 392, 394, 395, 396, 398, 399, 400, 402, 403, 407, 409, 410, 411, 413, 416, 417, 418, 420, 421, 425, 427, 428, 429, 431, 433, 437, 439, 440, 441, 443, 446, 448, 449, 451, 452]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 405, 407, 409, 410, 411, 412, 413, 415, 416, 417, 418, 419, 420, 421, 423, 425, 427, 428, 429, 430, 431, 433, 435, 437, 439, 440, 441, 442, 443, 446, 447, 448, 452]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 405, 407, 409, 410, 411, 412, 413, 415, 416, 417, 418, 419, 420, 421, 423, 425, 427, 428, 429, 430, 431, 433, 435, 437, 439, 440, 441, 442, 443, 446, 447, 448, 452]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 405, 407, 409, 410, 411, 412, 413, 415, 416, 417, 418, 419, 420, 421, 423, 425, 427, 428, 429, 430, 431, 433, 435, 437, 439, 440, 441, 442, 443, 446, 447, 448, 452]}"))) {}

   else if ( checkMonitor7aaf64d(u, "F_{[385, 386, 387, 388, 392, 439, 440, 441, 443, 446, 448, 449, 451, 452]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 397, 398, 399, 401, 402, 403, 405, 407, 412, 415, 416, 417, 419, 420, 421, 423, 425, 430, 435, 439, 440, 441, 442, 443, 446, 447, 448, 452]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 397, 398, 399, 401, 402, 403, 405, 407, 412, 415, 416, 417, 419, 420, 421, 423, 425, 430, 435, 439, 440, 441, 442, 443, 446, 447, 448, 452]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 405, 407, 412, 415, 416, 417, 418, 419, 420, 421, 423, 425, 430, 435, 439, 440, 441, 442, 443, 446, 447, 448, 452]}"))) {}

   else if ( checkMonitor7aaf64d(u, "F_{[385, 386, 387, 388, 392, 394, 395, 396, 398, 399, 400, 402, 403, 407, 416, 417, 418, 420, 421, 425, 427, 428, 429, 431, 433, 437, 439, 440, 441, 443, 446, 448, 449, 451, 452]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 405, 407, 412, 415, 416, 417, 418, 419, 420, 421, 423, 425, 427, 428, 429, 430, 431, 433, 435, 437, 439, 440, 441, 442, 443, 446, 447, 448, 452]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 405, 407, 412, 415, 416, 417, 418, 419, 420, 421, 423, 425, 427, 428, 429, 430, 431, 433, 435, 437, 439, 440, 441, 442, 443, 446, 447, 448, 452]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 405, 407, 412, 415, 416, 417, 418, 419, 420, 421, 423, 425, 427, 428, 429, 430, 431, 433, 435, 437, 439, 440, 441, 442, 443, 446, 447, 448, 452]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 397, 398, 399, 401, 402, 403, 405, 407, 409, 410, 411, 412, 413, 415, 416, 417, 419, 420, 421, 423, 425, 427, 428, 429, 430, 431, 433, 435, 437, 439, 440, 441, 442, 443, 446, 447, 448, 452]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 405, 407, 409, 410, 411, 412, 413, 415, 416, 417, 418, 419, 420, 421, 423, 425, 427, 428, 429, 430, 431, 433, 435, 437, 439, 440, 441, 442, 443, 446, 447, 448, 452]}"))) {}

   else if ( checkMonitor7aaf64d(u, "F_{[385, 386, 387, 388, 392, 409, 410, 411, 413, 420, 421, 425, 427, 428, 429, 431, 433, 437, 439, 440, 441, 443, 446, 448, 449, 451, 452]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 397, 398, 399, 401, 402, 403, 405, 407, 409, 410, 411, 412, 413, 415, 416, 417, 419, 420, 421, 423, 425, 427, 428, 429, 430, 431, 433, 435, 437, 439, 440, 441, 442, 443, 446, 447, 448, 452]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 404, 406, 408, 409, 410, 413, 414, 420, 421, 422, 424, 425, 426, 427, 428, 431, 432, 433, 437]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 397, 398, 399, 401, 402, 403, 405, 407, 409, 410, 411, 412, 413, 415, 416, 417, 419, 420, 421, 423, 425, 427, 428, 429, 430, 431, 433, 435, 437, 442, 447]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 409, 410, 411, 413, 420, 421, 425, 427, 428, 429, 431, 433, 437]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 397, 398, 399, 401, 402, 403, 405, 407, 409, 410, 411, 412, 413, 415, 416, 417, 419, 420, 421, 423, 425, 427, 428, 429, 430, 431, 433, 435, 437, 442, 447]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 397, 398, 399, 401, 402, 403, 405, 407, 409, 410, 411, 412, 413, 415, 416, 417, 419, 420, 421, 423, 425, 427, 428, 429, 430, 431, 433, 435, 437, 442, 447]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 405, 407, 409, 410, 411, 412, 413, 415, 416, 417, 418, 419, 420, 421, 423, 425, 427, 428, 429, 430, 431, 433, 435, 437, 442, 447]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 422, 424, 426, 427, 428, 431, 432, 433, 434, 436, 437, 438, 439, 440, 443, 444, 446, 448, 452]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 397, 398, 399, 401, 402, 403, 405, 407, 412, 415, 416, 417, 419, 420, 421, 423, 425, 427, 428, 429, 430, 431, 433, 435, 437, 439, 440, 441, 442, 443, 446, 447, 448, 452]}"))) {}

   else if ( checkMonitor7aaf64d(u, "F_{[385, 386, 387, 388, 392, 394, 395, 396, 398, 399, 400, 402, 403, 407, 416, 417, 418, 420, 421, 425, 439, 440, 441, 443, 446, 448, 449, 451, 452]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 405, 407, 412, 415, 416, 417, 418, 419, 420, 421, 423, 425, 430, 435, 439, 440, 441, 442, 443, 446, 447, 448, 452]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 434, 436, 438, 439, 440, 443, 444, 446, 448, 452]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 397, 398, 399, 401, 402, 403, 405, 407, 412, 415, 416, 417, 419, 420, 421, 423, 425, 430, 435, 439, 440, 441, 442, 443, 446, 447, 448, 452]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 397, 398, 399, 401, 402, 403, 405, 407, 412, 415, 416, 417, 419, 420, 421, 423, 425, 427, 428, 429, 430, 431, 433, 435, 437, 439, 440, 441, 442, 443, 446, 447, 448, 452]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 405, 407, 412, 415, 416, 417, 418, 419, 420, 421, 423, 425, 427, 428, 429, 430, 431, 433, 435, 437, 439, 440, 441, 442, 443, 446, 447, 448, 452]}"))) {}

   else if ( checkMonitor7aaf64d(u, "F_{[385, 386, 387, 388, 392, 427, 428, 429, 431, 433, 437, 439, 440, 441, 443, 446, 448, 449, 451, 452]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 397, 398, 399, 401, 402, 403, 405, 407, 412, 415, 416, 417, 419, 420, 421, 423, 425, 427, 428, 429, 430, 431, 433, 435, 437, 439, 440, 441, 442, 443, 446, 447, 448, 452]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 405, 407, 412, 415, 416, 417, 418, 419, 420, 421, 423, 425, 430, 435, 442, 447]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 405, 407, 412, 415, 416, 417, 418, 419, 420, 421, 423, 425, 430, 435, 442, 447]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 394, 395, 396, 398, 399, 400, 402, 403, 407, 416, 417, 418, 420, 421, 425]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 405, 407, 412, 415, 416, 417, 418, 419, 420, 421, 423, 425, 430, 435, 442, 447]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 397, 398, 399, 401, 402, 403, 405, 407, 412, 415, 416, 417, 419, 420, 421, 423, 425, 430, 435, 442, 447]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 397, 398, 399, 401, 402, 403, 405, 407, 412, 415, 416, 417, 419, 420, 421, 423, 425, 430, 435, 442, 447]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 405, 407, 412, 415, 416, 417, 418, 419, 420, 421, 423, 425, 430, 435, 442, 447]}"))) {}

   else if ( checkMonitor7aaf64d(u, "S_{[384, 385, 386, 388, 390, 392, 445, 450, 453]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 397, 398, 399, 401, 402, 403, 405, 407, 412, 415, 416, 417, 419, 420, 421, 423, 425, 430, 435, 442, 447]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 394, 395, 396, 398, 399, 400, 402, 403, 407, 409, 410, 411, 413, 416, 417, 418, 420, 421, 425, 427, 428, 429, 431, 433, 437]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 405, 407, 409, 410, 411, 412, 413, 415, 416, 417, 418, 419, 420, 421, 423, 425, 427, 428, 429, 430, 431, 433, 435, 437, 442, 447]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 405, 407, 409, 410, 411, 412, 413, 415, 416, 417, 418, 419, 420, 421, 423, 425, 427, 428, 429, 430, 431, 433, 435, 437, 442, 447]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 405, 407, 409, 410, 411, 412, 413, 415, 416, 417, 418, 419, 420, 421, 423, 425, 427, 428, 429, 430, 431, 433, 435, 437, 442, 447]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 404, 406, 408, 409, 410, 413, 414, 420, 421, 422, 424, 425, 426, 427, 428, 431, 432, 433, 434, 436, 437, 438, 439, 440, 443, 444, 446, 448, 452]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 397, 398, 399, 401, 402, 403, 405, 407, 409, 410, 411, 412, 413, 415, 416, 417, 419, 420, 421, 423, 425, 427, 428, 429, 430, 431, 433, 435, 437, 439, 440, 441, 442, 443, 446, 447, 448, 452]}"))) {}
}


before (UserInfo u): call(* *.whitelist(..)) &&
    target(u){ 
 if (1==0) {}
   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 405, 407, 412, 415, 416, 417, 418, 419, 420, 421, 423, 425, 430, 435, 439, 440, 441, 442, 443, 446, 447, 448, 452]}")
 && (setMonitor7aaf64d(u, "F_{[385, 386, 387, 388, 392, 394, 395, 396, 398, 399, 400, 402, 403, 407, 416, 417, 418, 420, 421, 425, 439, 440, 441, 443, 446, 448, 449, 451, 452]}"))) {
 Verification.fail(" FAILED ON REGEXP UNEXPECT ((((?)* ; greylist) ; (((((((((!(depositTo))* + (!(depositTo))*) ; depositTo) ; (!(depositTo))*) + (!(depositTo))*) ; depositTo) ; (!(depositTo))*) ; depositTo) ; (!(depositTo))*)) ; whitelist)");}

   else if ( checkMonitor7aaf64d(u, "F_{[385, 386, 387, 388, 392, 394, 395, 396, 398, 399, 400, 402, 403, 407, 409, 410, 411, 413, 416, 417, 418, 420, 421, 425, 427, 428, 429, 431, 433, 437, 439, 440, 441, 443, 446, 448, 449, 451, 452]}")
 && (setMonitor7aaf64d(u, "F_{[385, 386, 387, 388, 392, 394, 395, 396, 398, 399, 400, 402, 403, 407, 409, 410, 411, 413, 416, 417, 418, 420, 421, 425, 427, 428, 429, 431, 433, 437, 439, 440, 441, 443, 446, 448, 449, 451, 452]}"))) {
 Verification.fail(" FAILED ON REGEXP UNEXPECT ((((?)* ; greylist) ; (((((((((!(depositTo))* + (!(depositTo))*) ; depositTo) ; (!(depositTo))*) + (!(depositTo))*) ; depositTo) ; (!(depositTo))*) ; depositTo) ; (!(depositTo))*)) ; whitelist)");}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 405, 407, 409, 410, 411, 412, 413, 415, 416, 417, 418, 419, 420, 421, 423, 425, 427, 428, 429, 430, 431, 433, 435, 437, 439, 440, 441, 442, 443, 446, 447, 448, 452]}")
 && (setMonitor7aaf64d(u, "F_{[385, 386, 387, 388, 392, 394, 395, 396, 398, 399, 400, 402, 403, 407, 409, 410, 411, 413, 416, 417, 418, 420, 421, 425, 427, 428, 429, 431, 433, 437, 439, 440, 441, 443, 446, 448, 449, 451, 452]}"))) {
 Verification.fail(" FAILED ON REGEXP UNEXPECT ((((?)* ; greylist) ; (((((((((!(depositTo))* + (!(depositTo))*) ; depositTo) ; (!(depositTo))*) + (!(depositTo))*) ; depositTo) ; (!(depositTo))*) ; depositTo) ; (!(depositTo))*)) ; whitelist)");}

   else if ( checkMonitor7aaf64d(u, "F_{[385, 386, 387, 388, 392, 439, 440, 441, 443, 446, 448, 449, 451, 452]}")
 && (setMonitor7aaf64d(u, "F_{[385, 386, 387, 388, 392, 439, 440, 441, 443, 446, 448, 449, 451, 452]}"))) {
 Verification.fail(" FAILED ON REGEXP UNEXPECT ((((?)* ; greylist) ; (((((((((!(depositTo))* + (!(depositTo))*) ; depositTo) ; (!(depositTo))*) + (!(depositTo))*) ; depositTo) ; (!(depositTo))*) ; depositTo) ; (!(depositTo))*)) ; whitelist)");}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 397, 398, 399, 401, 402, 403, 405, 407, 412, 415, 416, 417, 419, 420, 421, 423, 425, 430, 435, 439, 440, 441, 442, 443, 446, 447, 448, 452]}")
 && (setMonitor7aaf64d(u, "F_{[385, 386, 387, 388, 392, 394, 395, 396, 398, 399, 400, 402, 403, 407, 416, 417, 418, 420, 421, 425, 439, 440, 441, 443, 446, 448, 449, 451, 452]}"))) {
 Verification.fail(" FAILED ON REGEXP UNEXPECT ((((?)* ; greylist) ; (((((((((!(depositTo))* + (!(depositTo))*) ; depositTo) ; (!(depositTo))*) + (!(depositTo))*) ; depositTo) ; (!(depositTo))*) ; depositTo) ; (!(depositTo))*)) ; whitelist)");}

   else if ( checkMonitor7aaf64d(u, "F_{[385, 386, 387, 388, 392, 394, 395, 396, 398, 399, 400, 402, 403, 407, 416, 417, 418, 420, 421, 425, 427, 428, 429, 431, 433, 437, 439, 440, 441, 443, 446, 448, 449, 451, 452]}")
 && (setMonitor7aaf64d(u, "F_{[385, 386, 387, 388, 392, 394, 395, 396, 398, 399, 400, 402, 403, 407, 416, 417, 418, 420, 421, 425, 427, 428, 429, 431, 433, 437, 439, 440, 441, 443, 446, 448, 449, 451, 452]}"))) {
 Verification.fail(" FAILED ON REGEXP UNEXPECT ((((?)* ; greylist) ; (((((((((!(depositTo))* + (!(depositTo))*) ; depositTo) ; (!(depositTo))*) + (!(depositTo))*) ; depositTo) ; (!(depositTo))*) ; depositTo) ; (!(depositTo))*)) ; whitelist)");}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 405, 407, 412, 415, 416, 417, 418, 419, 420, 421, 423, 425, 427, 428, 429, 430, 431, 433, 435, 437, 439, 440, 441, 442, 443, 446, 447, 448, 452]}")
 && (setMonitor7aaf64d(u, "F_{[385, 386, 387, 388, 392, 394, 395, 396, 398, 399, 400, 402, 403, 407, 416, 417, 418, 420, 421, 425, 427, 428, 429, 431, 433, 437, 439, 440, 441, 443, 446, 448, 449, 451, 452]}"))) {
 Verification.fail(" FAILED ON REGEXP UNEXPECT ((((?)* ; greylist) ; (((((((((!(depositTo))* + (!(depositTo))*) ; depositTo) ; (!(depositTo))*) + (!(depositTo))*) ; depositTo) ; (!(depositTo))*) ; depositTo) ; (!(depositTo))*)) ; whitelist)");}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 397, 398, 399, 401, 402, 403, 405, 407, 409, 410, 411, 412, 413, 415, 416, 417, 419, 420, 421, 423, 425, 427, 428, 429, 430, 431, 433, 435, 437, 439, 440, 441, 442, 443, 446, 447, 448, 452]}")
 && (setMonitor7aaf64d(u, "F_{[385, 386, 387, 388, 392, 394, 395, 396, 398, 399, 400, 402, 403, 407, 409, 410, 411, 413, 416, 417, 418, 420, 421, 425, 427, 428, 429, 431, 433, 437, 439, 440, 441, 443, 446, 448, 449, 451, 452]}"))) {
 Verification.fail(" FAILED ON REGEXP UNEXPECT ((((?)* ; greylist) ; (((((((((!(depositTo))* + (!(depositTo))*) ; depositTo) ; (!(depositTo))*) + (!(depositTo))*) ; depositTo) ; (!(depositTo))*) ; depositTo) ; (!(depositTo))*)) ; whitelist)");}

   else if ( checkMonitor7aaf64d(u, "F_{[385, 386, 387, 388, 392, 409, 410, 411, 413, 420, 421, 425, 427, 428, 429, 431, 433, 437, 439, 440, 441, 443, 446, 448, 449, 451, 452]}")
 && (setMonitor7aaf64d(u, "F_{[385, 386, 387, 388, 392, 409, 410, 411, 413, 420, 421, 425, 427, 428, 429, 431, 433, 437, 439, 440, 441, 443, 446, 448, 449, 451, 452]}"))) {
 Verification.fail(" FAILED ON REGEXP UNEXPECT ((((?)* ; greylist) ; (((((((((!(depositTo))* + (!(depositTo))*) ; depositTo) ; (!(depositTo))*) + (!(depositTo))*) ; depositTo) ; (!(depositTo))*) ; depositTo) ; (!(depositTo))*)) ; whitelist)");}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 404, 406, 408, 409, 410, 413, 414, 420, 421, 422, 424, 425, 426, 427, 428, 431, 432, 433, 437]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 409, 410, 411, 413, 420, 421, 425, 427, 428, 429, 431, 433, 437]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 409, 410, 411, 413, 420, 421, 425, 427, 428, 429, 431, 433, 437]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 409, 410, 411, 413, 420, 421, 425, 427, 428, 429, 431, 433, 437]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 397, 398, 399, 401, 402, 403, 405, 407, 409, 410, 411, 412, 413, 415, 416, 417, 419, 420, 421, 423, 425, 427, 428, 429, 430, 431, 433, 435, 437, 442, 447]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 394, 395, 396, 398, 399, 400, 402, 403, 407, 409, 410, 411, 413, 416, 417, 418, 420, 421, 425, 427, 428, 429, 431, 433, 437]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 422, 424, 426, 427, 428, 431, 432, 433, 434, 436, 437, 438, 439, 440, 443, 444, 446, 448, 452]}")
 && (setMonitor7aaf64d(u, "F_{[385, 386, 387, 388, 392, 427, 428, 429, 431, 433, 437, 439, 440, 441, 443, 446, 448, 449, 451, 452]}"))) {
 Verification.fail(" FAILED ON REGEXP UNEXPECT ((((?)* ; greylist) ; (((((((((!(depositTo))* + (!(depositTo))*) ; depositTo) ; (!(depositTo))*) + (!(depositTo))*) ; depositTo) ; (!(depositTo))*) ; depositTo) ; (!(depositTo))*)) ; whitelist)");}

   else if ( checkMonitor7aaf64d(u, "F_{[385, 386, 387, 388, 392, 394, 395, 396, 398, 399, 400, 402, 403, 407, 416, 417, 418, 420, 421, 425, 439, 440, 441, 443, 446, 448, 449, 451, 452]}")
 && (setMonitor7aaf64d(u, "F_{[385, 386, 387, 388, 392, 394, 395, 396, 398, 399, 400, 402, 403, 407, 416, 417, 418, 420, 421, 425, 439, 440, 441, 443, 446, 448, 449, 451, 452]}"))) {
 Verification.fail(" FAILED ON REGEXP UNEXPECT ((((?)* ; greylist) ; (((((((((!(depositTo))* + (!(depositTo))*) ; depositTo) ; (!(depositTo))*) + (!(depositTo))*) ; depositTo) ; (!(depositTo))*) ; depositTo) ; (!(depositTo))*)) ; whitelist)");}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 434, 436, 438, 439, 440, 443, 444, 446, 448, 452]}")
 && (setMonitor7aaf64d(u, "F_{[385, 386, 387, 388, 392, 439, 440, 441, 443, 446, 448, 449, 451, 452]}"))) {
 Verification.fail(" FAILED ON REGEXP UNEXPECT ((((?)* ; greylist) ; (((((((((!(depositTo))* + (!(depositTo))*) ; depositTo) ; (!(depositTo))*) + (!(depositTo))*) ; depositTo) ; (!(depositTo))*) ; depositTo) ; (!(depositTo))*)) ; whitelist)");}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 397, 398, 399, 401, 402, 403, 405, 407, 412, 415, 416, 417, 419, 420, 421, 423, 425, 427, 428, 429, 430, 431, 433, 435, 437, 439, 440, 441, 442, 443, 446, 447, 448, 452]}")
 && (setMonitor7aaf64d(u, "F_{[385, 386, 387, 388, 392, 394, 395, 396, 398, 399, 400, 402, 403, 407, 416, 417, 418, 420, 421, 425, 427, 428, 429, 431, 433, 437, 439, 440, 441, 443, 446, 448, 449, 451, 452]}"))) {
 Verification.fail(" FAILED ON REGEXP UNEXPECT ((((?)* ; greylist) ; (((((((((!(depositTo))* + (!(depositTo))*) ; depositTo) ; (!(depositTo))*) + (!(depositTo))*) ; depositTo) ; (!(depositTo))*) ; depositTo) ; (!(depositTo))*)) ; whitelist)");}

   else if ( checkMonitor7aaf64d(u, "F_{[385, 386, 387, 388, 392, 427, 428, 429, 431, 433, 437, 439, 440, 441, 443, 446, 448, 449, 451, 452]}")
 && (setMonitor7aaf64d(u, "F_{[385, 386, 387, 388, 392, 427, 428, 429, 431, 433, 437, 439, 440, 441, 443, 446, 448, 449, 451, 452]}"))) {
 Verification.fail(" FAILED ON REGEXP UNEXPECT ((((?)* ; greylist) ; (((((((((!(depositTo))* + (!(depositTo))*) ; depositTo) ; (!(depositTo))*) + (!(depositTo))*) ; depositTo) ; (!(depositTo))*) ; depositTo) ; (!(depositTo))*)) ; whitelist)");}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 405, 407, 412, 415, 416, 417, 418, 419, 420, 421, 423, 425, 430, 435, 442, 447]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 394, 395, 396, 398, 399, 400, 402, 403, 407, 416, 417, 418, 420, 421, 425]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 394, 395, 396, 398, 399, 400, 402, 403, 407, 416, 417, 418, 420, 421, 425]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 394, 395, 396, 398, 399, 400, 402, 403, 407, 416, 417, 418, 420, 421, 425]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 397, 398, 399, 401, 402, 403, 405, 407, 412, 415, 416, 417, 419, 420, 421, 423, 425, 430, 435, 442, 447]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 394, 395, 396, 398, 399, 400, 402, 403, 407, 416, 417, 418, 420, 421, 425]}"))) {}

   else if ( checkMonitor7aaf64d(u, "S_{[384, 385, 386, 388, 390, 392, 445, 450, 453]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 394, 395, 396, 398, 399, 400, 402, 403, 407, 409, 410, 411, 413, 416, 417, 418, 420, 421, 425, 427, 428, 429, 431, 433, 437]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 394, 395, 396, 398, 399, 400, 402, 403, 407, 409, 410, 411, 413, 416, 417, 418, 420, 421, 425, 427, 428, 429, 431, 433, 437]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 389, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 405, 407, 409, 410, 411, 412, 413, 415, 416, 417, 418, 419, 420, 421, 423, 425, 427, 428, 429, 430, 431, 433, 435, 437, 442, 447]}")
 && (setMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 394, 395, 396, 398, 399, 400, 402, 403, 407, 409, 410, 411, 413, 416, 417, 418, 420, 421, 425, 427, 428, 429, 431, 433, 437]}"))) {}

   else if ( checkMonitor7aaf64d(u, "N_{[385, 386, 387, 388, 392, 404, 406, 408, 409, 410, 413, 414, 420, 421, 422, 424, 425, 426, 427, 428, 431, 432, 433, 434, 436, 437, 438, 439, 440, 443, 444, 446, 448, 452]}")
 && (setMonitor7aaf64d(u, "F_{[385, 386, 387, 388, 392, 409, 410, 411, 413, 420, 421, 425, 427, 428, 429, 431, 433, 437, 439, 440, 441, 443, 446, 448, 449, 451, 452]}"))) {
 Verification.fail(" FAILED ON REGEXP UNEXPECT ((((?)* ; greylist) ; (((((((((!(depositTo))* + (!(depositTo))*) ; depositTo) ; (!(depositTo))*) + (!(depositTo))*) ; depositTo) ; (!(depositTo))*) ; depositTo) ; (!(depositTo))*)) ; whitelist)");}
}




// the code for UNEXPECT: ((((!(openSession))* ; (((openSession ; (!(closeSession))*) ; closeSession))*) ; (!(openSession))*) ; log)
static HashMap<UserSession, String> monitors8dd7404 = null;
static void init8dd7404(){ monitors8dd7404= new HashMap<UserSession, String>(); }



static boolean checkMonitor8dd7404(UserSession target, String state) { 
 if (monitors8dd7404.containsKey(target))
  return state.equals(monitors8dd7404.get(target));
 else {
  monitors8dd7404.put(target, "S_{[455, 456, 457, 459, 460, 461, 467, 472, 475, 476, 477, 478, 479, 480, 482, 483, 484, 485, 487, 489, 490]}");
  return state.equals("S_{[455, 456, 457, 459, 460, 461, 467, 472, 475, 476, 477, 478, 479, 480, 482, 483, 484, 485, 487, 489, 490]}"); }
 }

static boolean setMonitor8dd7404(UserSession target, String state) { 
 if (monitors8dd7404.containsKey(target))
 { monitors8dd7404.put(target,state);
   return true; 
 }
 else {
  return false; }
 }

before (UserSession u): call(* *.openSession(..)) &&
    target(u){ 
 if (1==0) {}
   else if ( checkMonitor8dd7404(u, "S_{[455, 456, 457, 459, 460, 461, 467, 472, 475, 476, 477, 478, 479, 480, 482, 483, 484, 485, 487, 489, 490]}")
 && (setMonitor8dd7404(u, "N_{[462, 463, 464, 465, 468, 469, 470, 474]}"))) {}

   else if ( checkMonitor8dd7404(u, "F_{[456, 457, 458, 459, 460, 461, 467, 472, 476, 477, 478, 479, 480, 481, 483, 484, 485, 486, 488, 489]}")
 && (setMonitor8dd7404(u, "N_{[462, 463, 464, 465, 468, 469, 470, 474]}"))) {}

   else if ( checkMonitor8dd7404(u, "N_{[456, 457, 458, 459, 460, 461, 467, 472, 476, 477, 478, 479, 480, 481, 483, 484, 485, 489]}")
 && (setMonitor8dd7404(u, "N_{[462, 463, 464, 465, 468, 469, 470, 474]}"))) {}

   else if ( checkMonitor8dd7404(u, "N_{[464, 465, 466, 468, 470, 474]}")
 && (setMonitor8dd7404(u, "N_{[464, 465, 466, 468, 470, 474]}"))) {}

   else if ( checkMonitor8dd7404(u, "N_{[460, 461, 467, 471, 472, 473, 476, 478, 479, 480, 483, 484, 485, 489]}")
 && (setMonitor8dd7404(u, "N_{[462, 463, 464, 465, 468, 469, 470, 474]}"))) {}

   else if ( checkMonitor8dd7404(u, "N_{[462, 463, 464, 465, 468, 469, 470, 474]}")
 && (setMonitor8dd7404(u, "N_{[464, 465, 466, 468, 470, 474]}"))) {}

   else if ( checkMonitor8dd7404(u, "N_{[479, 480, 481, 483, 485, 489]}")
 && (setMonitor8dd7404(u, "N_491"))) {}

   else if ( checkMonitor8dd7404(u, "F_{[479, 480, 481, 483, 485, 486, 488, 489]}")
 && (setMonitor8dd7404(u, "N_491"))) {}

   else if ( checkMonitor8dd7404(u, "N_491")
 && (setMonitor8dd7404(u, "N_491"))) {}
}


before (UserSession u): call(* *.closeSession(..)) &&
    target(u){ 
 if (1==0) {}
   else if ( checkMonitor8dd7404(u, "S_{[455, 456, 457, 459, 460, 461, 467, 472, 475, 476, 477, 478, 479, 480, 482, 483, 484, 485, 487, 489, 490]}")
 && (setMonitor8dd7404(u, "N_{[456, 457, 458, 459, 460, 461, 467, 472, 476, 477, 478, 479, 480, 481, 483, 484, 485, 489]}"))) {}

   else if ( checkMonitor8dd7404(u, "F_{[456, 457, 458, 459, 460, 461, 467, 472, 476, 477, 478, 479, 480, 481, 483, 484, 485, 486, 488, 489]}")
 && (setMonitor8dd7404(u, "N_{[456, 457, 458, 459, 460, 461, 467, 472, 476, 477, 478, 479, 480, 481, 483, 484, 485, 489]}"))) {}

   else if ( checkMonitor8dd7404(u, "N_{[456, 457, 458, 459, 460, 461, 467, 472, 476, 477, 478, 479, 480, 481, 483, 484, 485, 489]}")
 && (setMonitor8dd7404(u, "N_{[456, 457, 458, 459, 460, 461, 467, 472, 476, 477, 478, 479, 480, 481, 483, 484, 485, 489]}"))) {}

   else if ( checkMonitor8dd7404(u, "N_{[479, 480, 481, 483, 485, 489]}")
 && (setMonitor8dd7404(u, "N_{[479, 480, 481, 483, 485, 489]}"))) {}

   else if ( checkMonitor8dd7404(u, "F_{[479, 480, 481, 483, 485, 486, 488, 489]}")
 && (setMonitor8dd7404(u, "N_{[479, 480, 481, 483, 485, 489]}"))) {}

   else if ( checkMonitor8dd7404(u, "N_{[464, 465, 466, 468, 470, 474]}")
 && (setMonitor8dd7404(u, "N_{[460, 461, 467, 471, 472, 473, 476, 478, 479, 480, 483, 484, 485, 489]}"))) {}

   else if ( checkMonitor8dd7404(u, "N_{[460, 461, 467, 471, 472, 473, 476, 478, 479, 480, 483, 484, 485, 489]}")
 && (setMonitor8dd7404(u, "N_{[479, 480, 481, 483, 485, 489]}"))) {}

   else if ( checkMonitor8dd7404(u, "N_{[462, 463, 464, 465, 468, 469, 470, 474]}")
 && (setMonitor8dd7404(u, "N_{[460, 461, 467, 471, 472, 473, 476, 478, 479, 480, 483, 484, 485, 489]}"))) {}

   else if ( checkMonitor8dd7404(u, "N_491")
 && (setMonitor8dd7404(u, "N_491"))) {}
}


before (UserSession u): call(* *.log(..)) &&
    target(u){ 
 if (1==0) {}
   else if ( checkMonitor8dd7404(u, "S_{[455, 456, 457, 459, 460, 461, 467, 472, 475, 476, 477, 478, 479, 480, 482, 483, 484, 485, 487, 489, 490]}")
 && (setMonitor8dd7404(u, "F_{[456, 457, 458, 459, 460, 461, 467, 472, 476, 477, 478, 479, 480, 481, 483, 484, 485, 486, 488, 489]}"))) {
 Verification.fail(" FAILED ON REGEXP UNEXPECT ((((!(openSession))* ; (((openSession ; (!(closeSession))*) ; closeSession))*) ; (!(openSession))*) ; log)");}

   else if ( checkMonitor8dd7404(u, "F_{[456, 457, 458, 459, 460, 461, 467, 472, 476, 477, 478, 479, 480, 481, 483, 484, 485, 486, 488, 489]}")
 && (setMonitor8dd7404(u, "F_{[456, 457, 458, 459, 460, 461, 467, 472, 476, 477, 478, 479, 480, 481, 483, 484, 485, 486, 488, 489]}"))) {
 Verification.fail(" FAILED ON REGEXP UNEXPECT ((((!(openSession))* ; (((openSession ; (!(closeSession))*) ; closeSession))*) ; (!(openSession))*) ; log)");}

   else if ( checkMonitor8dd7404(u, "N_{[456, 457, 458, 459, 460, 461, 467, 472, 476, 477, 478, 479, 480, 481, 483, 484, 485, 489]}")
 && (setMonitor8dd7404(u, "F_{[456, 457, 458, 459, 460, 461, 467, 472, 476, 477, 478, 479, 480, 481, 483, 484, 485, 486, 488, 489]}"))) {
 Verification.fail(" FAILED ON REGEXP UNEXPECT ((((!(openSession))* ; (((openSession ; (!(closeSession))*) ; closeSession))*) ; (!(openSession))*) ; log)");}

   else if ( checkMonitor8dd7404(u, "N_{[462, 463, 464, 465, 468, 469, 470, 474]}")
 && (setMonitor8dd7404(u, "N_{[464, 465, 466, 468, 470, 474]}"))) {}

   else if ( checkMonitor8dd7404(u, "N_{[479, 480, 481, 483, 485, 489]}")
 && (setMonitor8dd7404(u, "F_{[479, 480, 481, 483, 485, 486, 488, 489]}"))) {
 Verification.fail(" FAILED ON REGEXP UNEXPECT ((((!(openSession))* ; (((openSession ; (!(closeSession))*) ; closeSession))*) ; (!(openSession))*) ; log)");}

   else if ( checkMonitor8dd7404(u, "F_{[479, 480, 481, 483, 485, 486, 488, 489]}")
 && (setMonitor8dd7404(u, "F_{[479, 480, 481, 483, 485, 486, 488, 489]}"))) {
 Verification.fail(" FAILED ON REGEXP UNEXPECT ((((!(openSession))* ; (((openSession ; (!(closeSession))*) ; closeSession))*) ; (!(openSession))*) ; log)");}

   else if ( checkMonitor8dd7404(u, "N_{[464, 465, 466, 468, 470, 474]}")
 && (setMonitor8dd7404(u, "N_{[464, 465, 466, 468, 470, 474]}"))) {}

   else if ( checkMonitor8dd7404(u, "N_{[460, 461, 467, 471, 472, 473, 476, 478, 479, 480, 483, 484, 485, 489]}")
 && (setMonitor8dd7404(u, "F_{[479, 480, 481, 483, 485, 486, 488, 489]}"))) {
 Verification.fail(" FAILED ON REGEXP UNEXPECT ((((!(openSession))* ; (((openSession ; (!(closeSession))*) ; closeSession))*) ; (!(openSession))*) ; log)");}

   else if ( checkMonitor8dd7404(u, "N_491")
 && (setMonitor8dd7404(u, "N_491"))) {}
}


public static void initialise() {initba5ba75(); 
initd748654(); 
init1f24bb(); 
init7a477b7(); 
initeafccbe(); 
init7aaf64d(); 
init8dd7404(); 

}
}
