package transactionsystem;

import java.util.ArrayList;
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
	static private boolean tsHasBeenInitialised;	
	static public void tsInitialised() { tsHasBeenInitialised = true; }
	static public boolean get_tsHasBeenInitialised() { return tsHasBeenInitialised; }

	static private ArrayList<Integer> disabledUsers;
	static void disable(Integer uid) 
	{
		if (!disabledUsers.contains(uid)) disabledUsers.add(uid);
	}
	static void enable(Integer uid)
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
	
	private static Integer transfersSinceReconciliation;
	private static double amountTransferedSinceReconciliation;
	public static void addExternalTransfer(double amount)
	{
		transfersSinceReconciliation++;
		amountTransferedSinceReconciliation+=amount;
	}
	public static void reconciliation()
	{
		transfersSinceReconciliation=0;
		amountTransferedSinceReconciliation = 0;
	}
	public static boolean reconciliationNeeded()
	{
		return transfersSinceReconciliation > 1000 || amountTransferedSinceReconciliation > 1000000;
	}
	
	private static HashMap<Integer, Integer> activeSessionsPerUser;
	public static void openSession(Integer uid)
	{
		if (!activeSessionsPerUser.containsKey(uid)) activeSessionsPerUser.put(uid,0);
		activeSessionsPerUser.put(uid, activeSessionsPerUser.get(uid)+1);
	}
	public static void closeSession(Integer uid)
	{
		if (!activeSessionsPerUser.containsKey(uid)) activeSessionsPerUser.put(uid,0);
		activeSessionsPerUser.put(uid, activeSessionsPerUser.get(uid)-1);
	}
	public static Integer countOpenSessions(Integer uid)
	{
		return activeSessionsPerUser.get(uid);
	}

	private static HashMap<Integer,ArrayList<Integer>> openSessionsPerUser;
	public static void openSessionP10(Integer uid, Integer sid) 
	{
		if (!openSessionsPerUser.containsKey(uid)) openSessionsPerUser.put(uid,new ArrayList<Integer>());
		openSessionsPerUser.get(uid).add(sid);
	}
	public static void closeSessionP10(Integer uid, Integer sid) 
	{
		if (!openSessionsPerUser.containsKey(uid)) openSessionsPerUser.put(uid,new ArrayList<Integer>());
		openSessionsPerUser.remove(uid).add(sid);
	}
	public static boolean isOpenP10(Integer uid,Integer sid)
	{
		return openSessionsPerUser.containsKey(uid) && openSessionsPerUser.get(uid).contains(sid);
	}
	
	static public void initialiseVerification()
	{
		tsHasBeenInitialised = false;
		disabledUsers = new ArrayList<Integer>();
		transfersSinceGreylist = new HashMap<Integer,Integer>();
		greylistedUsers = new ArrayList<Integer>();
		countUserSessionCreateAccount = new HashMap<Integer,HashMap<Integer,Integer>>();
		transfersSinceReconciliation = 0;
		amountTransferedSinceReconciliation = 0;
		activeSessionsPerUser = new HashMap<Integer, Integer>();
		openSessionsPerUser = new HashMap<Integer, ArrayList<Integer>>();
	}

}
