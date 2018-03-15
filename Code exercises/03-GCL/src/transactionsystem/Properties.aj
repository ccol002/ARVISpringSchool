
package transactionsystem;



public aspect Properties {
before (UserInfo u): call(* *.makeGoldUser(..)) &&
    target(u){ if (!(u.getCountry().equals("Argentina"))) { Verification.fail("P1 violated"); }}


}
