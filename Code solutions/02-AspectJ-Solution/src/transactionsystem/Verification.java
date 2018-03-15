package transactionsystem;

import java.util.ArrayList;
import totalhashmap.TotalHashMap;

import java.util.HashMap;

public class Verification {

	static public void assertion(boolean condition, String message)
	{
		if (!condition) System.out.println("*** "+message+" ***");
	}

	/*
	static private Verification the_instance = new Verification();
	static public Verification getInstance() { return the_instance; }
	*/
	private Verification() { initialiseVerification(); }

	// -----------------------------------------------------------------
	static private boolean wasInitialised;
	static public void event_initialise() 
	{
		wasInitialised = true;
	}
	static public void event_openSession() 
	{
		assertion(wasInitialised, "P2 violated");
	}
	
	static public ArrayList<String> approvedAccounts;
	
	static private ArrayList<Integer> disabledUsers;
	static void disableUser(Integer uid)
	{
		if (!disabledUsers.contains(uid)) disabledUsers.add(uid);
	}
	static void enableUser(Integer uid)
	{
		disabledUsers.remove(uid);
	}
	static boolean isDisabled(Integer uid)
	{
		return (disabledUsers.contains(uid));
	}

	static private ArrayList<Integer> greylistedUsers;
	static private HashMap<Integer,Integer> transfersSinceGreylist;
	static void greylist(Integer uid)
	{
		if (!greylistedUsers.contains(uid)) {
			greylistedUsers.add(uid);
			transfersSinceGreylist.put(uid,0);
		}
	}
	static void ungreylist(Integer uid)
	{
		greylistedUsers.remove(uid);
	}
	static void incomingTransfer(Integer uid) 
	{
		if (transfersSinceGreylist.containsKey(uid)) {
			transfersSinceGreylist.put(uid, transfersSinceGreylist.get(uid)+1);
		}
	}
	static Integer transferCount(Integer uid)
	{
		if (transfersSinceGreylist.containsKey(uid))
			return(transfersSinceGreylist.get(uid));
		return 0;
	}
	
	static private HashMap<Integer,HashMap<Integer,Integer>> countUserSessionCreateAccount;
	static void incCreateAccountCount(Integer uid, Integer sid)
	{
		if (!countUserSessionCreateAccount.containsKey(uid))
			countUserSessionCreateAccount.put(uid,new HashMap<Integer,Integer>());
		if (!countUserSessionCreateAccount.get(uid).containsKey(sid))
			countUserSessionCreateAccount.get(uid).put(sid,0);
		countUserSessionCreateAccount.get(uid).put
			(sid,countUserSessionCreateAccount.get(uid).get(sid)+1);
	}
	static Integer countCreateAccount(Integer uid, Integer sid)
	{
		return countUserSessionCreateAccount.get(uid).get(sid);
	}

	// === P9
	private static HashMap<UserInfo, Integer> activeSessionsPerUser;
	public static void openSession(UserInfo u)
	{
		if (!activeSessionsPerUser.containsKey(u)) activeSessionsPerUser.put(u,0);
		activeSessionsPerUser.put(u, activeSessionsPerUser.get(u)+1);
	}
	public static void closeSession(UserInfo u)
	{
		if (!activeSessionsPerUser.containsKey(u)) activeSessionsPerUser.put(u,0);
		activeSessionsPerUser.put(u, activeSessionsPerUser.get(u)-1);
	}
	public static Integer countOpenSessions(UserInfo u)
	{
		return activeSessionsPerUser.get(u);
	}

   // P8
	
	static Integer countExternalTransferAttempt;
	static double totalExternalTransferAttempt;
	static public void externalTransferAttempt(double amount) {
		countExternalTransferAttempt++;
		totalExternalTransferAttempt+=amount;
		assertion(countExternalTransferAttempt<=1000 && totalExternalTransferAttempt<=1000000,
			"P8 violated");
	}
	static public void resetExternalTransferAttempts() {
		countExternalTransferAttempt = 0;
		totalExternalTransferAttempt = 0;
	}
	
	
	// P10
	static private TotalHashMap<UserSession,Boolean> sessionLoggedIn;

	static Boolean session_isLogged(UserSession s)
	{
		return (sessionLoggedIn.get(s));
	}
	static void session_login(UserSession s)
	{
		sessionLoggedIn.put(s,true);
	}
	static void session_logout(UserSession s)
	{
		sessionLoggedIn.put(s,false);
	}
	
	static public void initialiseVerification()
	{
		wasInitialised = false;
		approvedAccounts = new ArrayList<String>();
		disabledUsers = new ArrayList<Integer>();
		greylistedUsers = new ArrayList<Integer>();
		transfersSinceGreylist = new HashMap<Integer,Integer>();
		countUserSessionCreateAccount = new HashMap<Integer,HashMap<Integer,Integer>>();
		countExternalTransferAttempt = 0;
		totalExternalTransferAttempt = 0;
		activeSessionsPerUser = new HashMap<UserInfo,Integer>();
		sessionLoggedIn = new TotalHashMap <UserSession,Boolean>(false);
	}
	
	

}
