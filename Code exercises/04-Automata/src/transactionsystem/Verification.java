
package transactionsystem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

public class Verification {




static public void initialiseVerification()
{
// required to reset the automata to their initial state
Properties.initialise();


}

static public void fail(String s) {
System.out.println("ERROR: "+s);
}

}

