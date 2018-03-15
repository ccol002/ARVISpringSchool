
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

