package transactionsystem;

public aspect Properties {

	//P1
    before (UserInfo u): call(* *.makeGoldUser()) && target(u) 
    {
    	
    	Verification.assertion(
    		u.getCountry()=="Argentina", 
    		"P1 violated"
    	); 
    }
  
    
    //P3
    after (UserAccount a): (call(* *.withdraw(..)) || call(* *.deposit(..))) && target(a)
    {
    	Verification.assertion(a.getBalance() >= 0, "P3 violated");
    }
   
    //P4
    before (): call(* *.activateAccount())
    {
    	UserAccount a = (UserAccount)(thisJoinPoint.getTarget());
    	
    	Verification.assertion(
    		!(Verification.approvedAccounts.contains(a.getAccountNumber())),
    		"P4 violated");

    	Verification.approvedAccounts.add(a.getAccountNumber());
    }
    
}
