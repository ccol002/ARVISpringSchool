
package transactionsystem;

public class Verification {
static public void initialiseVerification()
{
Properties.init();
}

static public void fail(String s) {
System.out.println("ERROR: "+s);
}

}

