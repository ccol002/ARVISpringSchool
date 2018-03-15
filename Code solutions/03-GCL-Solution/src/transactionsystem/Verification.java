
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

