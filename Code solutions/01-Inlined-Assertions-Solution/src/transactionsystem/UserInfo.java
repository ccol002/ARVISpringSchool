package transactionsystem;

import java.util.ArrayList;
import java.util.Iterator;

import transactionsystem.UserSession;

public class UserInfo {
	protected enum UserMode {
		ACTIVE, DISABLED, FROZEN;
	}

	public enum UserStatus {
		WHITELISTED, GREYLISTED, BLACKLISTED;
	}

	public enum UserType {
		GOLD, SILVER, NORMAL
	}
	
	protected Integer uid;
	protected String name;
	protected UserMode mode;
	protected UserStatus status;
	protected UserType type;
	protected ArrayList<UserSession> sessions;
	protected ArrayList<UserAccount> accounts;
	protected Integer next_session_id;
	protected Integer next_account;
	protected String country;
	
	public UserInfo(Integer uid, String name, String country) {
		this.uid = uid;
		this.name = name;
		
		makeDisabled();
		whitelist();
		makeNormalUser();
		
		sessions = new ArrayList<UserSession>();
		accounts = new ArrayList<UserAccount>();
		
		next_session_id = 0;
		next_account = 1;
		
		this.country = country;
	}
	
	// Basic information
	public Integer getId() 
	{
		return uid;
	}
	public String getName()
	{
		return name;
	}
	public String getCountry()
	{
		return country;
	}
	public ArrayList<UserAccount> getAccounts()
	{
		return accounts;
	}
	public ArrayList<UserSession> getSessions()
	{
		return sessions;
	}
	
	// User type
	public boolean isGoldUser() { return (type==UserType.GOLD); }
	public boolean isSilverUser() { return (type==UserType.SILVER); }
	public boolean isNormalUser() { return (type==UserType.NORMAL); }

	public void makeGoldUser() { 
		// ===BEGIN Verification code [P1] Only users based in Argentina can be Gold users.
		Verification.assertion(getCountry()=="Argentina", "P1 violated"); 
		// ===END
		type = UserType.GOLD; 
	}
	public void makeSilverUser() { type = UserType.SILVER; }
	public void makeNormalUser() { type = UserType.NORMAL; }	
	
	// Status
	public boolean isWhitelisted() { return (status==UserStatus.WHITELISTED); }
	public boolean isGreylisted() { return (status==UserStatus.GREYLISTED); }
	public boolean isBlacklisted() { return (status==UserStatus.BLACKLISTED); }
	
	public void blacklist() 
	{ 
		Verification.ungreylist(uid);
		status=UserStatus.BLACKLISTED; 
	}
	public void greylist() 
	{
		Verification.greylist(uid);
		status=UserStatus.GREYLISTED; 
	}
	public void whitelist()
	{ 
		if (isGreylisted()) 
			Verification.assertion(Verification.transferCount(uid) >= 3, "P6 violated");

		Verification.ungreylist(uid);
		status=UserStatus.WHITELISTED; 
	}

	// Mode
	public boolean isActive() { return (mode==UserMode.ACTIVE); }
	public boolean isFrozen() { return (mode==UserMode.FROZEN); }
	public boolean isDisabled() { return (mode==UserMode.DISABLED); }

	public void makeActive() 
	{ 
		// ===BEGIN [P5]
		Verification.enable(uid);
		// ==END

		mode=UserMode.ACTIVE; 
	}
	public void makeFrozen() 
	{ 
		mode=UserMode.FROZEN; 
	}
	public void makeDisabled() 
	{ 
		// ===BEGIN [P5]
		Verification.disable(uid);
		// ==END

		mode=UserMode.DISABLED; 
	}
	public void makeUnfrozen() 
	{ 
		mode=UserMode.ACTIVE; 
	}

	// Sessions
	public UserSession getSession(Integer sid) 
	{
		UserSession s;
		
		Iterator<UserSession> iterator = sessions.iterator();
		while (iterator.hasNext()) {
		    s = iterator.next();
		    if (s.getId()==sid) return s;
		}
		return null;
	}
	public Integer openSession() 
	{
		Verification.openSession(uid);
		Verification.assertion(Verification.countOpenSessions(uid) <= 3, "P9 violated");
		
		Integer sid = next_session_id;
		
		UserSession session = new UserSession(uid, sid);
		session.openSession();
		sessions.add(session);

		next_session_id++;

		// Verification code for P2
		Verification.assertion(Verification.get_tsHasBeenInitialised(), "P2 violated");

		return(sid);
	}
	public void closeSession(Integer sid) 
	{
		Verification.closeSession(uid);		

		UserSession s = getSession(sid);

		s.closeSession();
	}

	// Accounts
	public UserAccount getAccount(String account_number) 
	{
		UserAccount a;
		
		Iterator<UserAccount> iterator = accounts.iterator();
		while (iterator.hasNext()) {
		    a = iterator.next();
		    if (a.getAccountNumber() == account_number) return a;
		}
		return null;
	} 
	public String createAccount(Integer sid) 
	{
		Verification.incCreateAccountCount(uid,sid);
		Verification.assertion(Verification.countCreateAccount(uid,sid)<=10, "P7 violated");
		
		String account_number = uid.toString() + next_account.toString();
		next_account++;
		UserAccount a = new UserAccount(uid, account_number);
		accounts.add(a);
		return account_number;
	}
	public void deleteAccount(String account_number) 
	{
		UserAccount a = getAccount(account_number);
		a.closeAccount();
	}

	public void withdrawFrom(String account_number, double amount)
	{
		// ===BEGIN [P5]
		Verification.assertion(!Verification.isDisabled(uid), "P5 violated");
		// ==END
		getAccount(account_number).withdraw(amount);
	}
	public void depositTo(String account_number, double amount)
	{
		Verification.incomingTransfer(uid);
		getAccount(account_number).deposit(amount);
	}
}
