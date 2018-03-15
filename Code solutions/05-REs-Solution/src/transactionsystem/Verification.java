
package transactionsystem;

import java.util.Set;
import java.util.HashSet;


public class Verification {

static Set<String> msgs;

static public void initialiseVerification()
{
System.out.println("\n\n");
msgs = new HashSet<String>();
Properties.initialise();
}

static public void fail(String s) {

if (msgs.add(s))
System.out.println("ERROR: "+s);
}

}

