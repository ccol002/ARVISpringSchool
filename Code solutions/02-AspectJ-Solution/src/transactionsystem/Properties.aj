package transactionsystem;

public aspect Properties {

    before (): call(* *.initialise())
    {
    	Verification.event_initialise();
    }
    
    before (): call(* UserInfo.openSession())
    {
    	Verification.event_openSession();
    }

    before (): call(* *.makeGoldUser()) 
    {
    	UserInfo u = (UserInfo)(thisJoinPoint.getTarget());
    	Verification.assertion(
    		u.getCountry()=="Argentina", 
    		"P1 violated"
    	); 
    }

    
    before (): call(* *.activateAccount())
    {
    	UserAccount a = (UserAccount)(thisJoinPoint.getTarget());
    	
    	Verification.assertion(
    		!(Verification.approvedAccounts.contains(a.getAccountNumber())),
    		"P4 violated");

    	Verification.approvedAccounts.add(a.getAccountNumber());
    }
    
    after (): call(* *.withdraw(..)) || call(* *.deposit(..))
    {
    	UserAccount a = (UserAccount)(thisJoinPoint.getTarget());
    	
    	Verification.assertion(a.getBalance() >= 0, "P3 violated");
    }

    before (): call(* UserInfo.makeDisabled(..)) 
    {
    	Integer uid = ((UserInfo)(thisJoinPoint.getTarget())).getId();
    	Verification.disableUser(uid);
    }
    before (): call(* UserInfo.makeActive(..)) 
    {
    	Integer uid = ((UserInfo)(thisJoinPoint.getTarget())).getId();
    	Verification.enableUser(uid);
    }
    before (): call(* UserInfo.withdrawFrom(..))
    {
    	Integer uid = ((UserInfo)(thisJoinPoint.getTarget())).getId();
    	Verification.assertion(!Verification.isDisabled(uid), "P5 violated");
    }

    before (): call(* UserInfo.greylist(..)) 
    {
    	Integer uid = ((UserInfo)(thisJoinPoint.getTarget())).getId();
    	Verification.greylist(uid);
    }
    before (): call(* UserInfo.blacklist(..)) 
    {
    	Integer uid = ((UserInfo)(thisJoinPoint.getTarget())).getId();
    	Verification.ungreylist(uid);
    }
    before (): call(* UserInfo.whitelist(..)) 
    {
    	UserInfo u = (UserInfo)(thisJoinPoint.getTarget());
    	Integer uid = u.getId();
    	if (u.isGreylisted()) 
    		Verification.assertion(Verification.transferCount(uid) >= 3, "P6 violated");

    	Verification.ungreylist(uid);
    }
    before (): call(* UserInfo.depositTo(..))
    {
    	Integer uid = ((UserInfo)(thisJoinPoint.getTarget())).getId();
    	Verification.incomingTransfer(uid);
    }

    before (Integer sid): call(* UserInfo.createAccount(..)) && args(sid)
    {
    	Integer uid = ((UserInfo)(thisJoinPoint.getTarget())).getId();

    	Verification.incCreateAccountCount(uid,sid);
    	Verification.assertion(Verification.countCreateAccount(uid,sid)<=10, "P7 violated");
    }
    
    before (Integer uid, Integer sid, String to_account_number, double amount): 
    call(* *.USER_depositFromExternal(..)) && args(uid,sid,to_account_number, amount) 
    {
    	Verification.externalTransferAttempt(amount);
    }
    before (Integer uid, Integer sid, String from_account_number, double amount): 
    call(* *.USER_payToExternal(..)) && args(uid,sid,from_account_number, amount) 
    {
    	Verification.externalTransferAttempt(amount);
    }
    before (): call(* *.ADMIN_reconcile())
    {
    	Verification.resetExternalTransferAttempts();
    }
    
    // P9:
    before (): call(* UserInfo.openSession(..)) 
    {
    	UserInfo u = (UserInfo)(thisJoinPoint.getTarget());
    	Verification.openSession(u); 
    	Verification.assertion(Verification.countOpenSessions(u) <= 3, "P9 violated");
    }
    before (): call(* UserInfo.closeSession(..)) 
    {
    	UserInfo u = (UserInfo)(thisJoinPoint.getTarget());
    	Verification.closeSession(u); 
    }

    before (): call(* UserSession.openSession(..)) 
    {
    	UserSession s = (UserSession)(thisJoinPoint.getTarget());
    	Verification.session_login(s); 
    }
    before (): call(* UserSession.closeSession(..)) 
    {
    	UserSession s = (UserSession)(thisJoinPoint.getTarget());
    	Verification.session_logout(s);
    }
    before (): call(* UserSession.log(..)) 
    {
    	UserSession s = (UserSession)(thisJoinPoint.getTarget());
    	Verification.assertion(Verification.session_isLogged(s), "P10 violated");
    }
    
    
}
