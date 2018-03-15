package transactionsystem;

public class UserSession {
	protected Integer sid;
	protected String log;
	protected Integer owner;
	
	public UserSession(Integer uid, Integer sid) {
		this.sid = sid;
		owner = uid;
		log = "";
	}

	public Integer getId() { return sid; }
	public Integer getOwner() { return owner; }
	public String getLog() { return log; }
	public void openSession() {
		Verification.openSessionP10(owner,sid);
	}
	public void log(String l) 
	{ 
		Verification.assertion(Verification.isOpenP10(owner,sid), "P10 violated");
		log+=l+"\n"; 
	}
	public void closeSession() 
	{
		Verification.closeSessionP10(owner,sid);
	}
	
	
}
