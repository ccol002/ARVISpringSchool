
package transactionsystem;

import java.util.Set;
import java.util.HashSet;


public class Verification {

static public void initialiseVerification()
{
Properties.initialise();
}

static public void fail(String s) {

System.out.println("ERROR: "+s);
}

}

