package re.structure;

import java.util.Set;
import re.automata.NFA;

public abstract class RE {
	
	/* RE ::= 
	 *     |    ?        Any
	 *     |    0        Nothing
	 *     |    1        End of string
	 *     |    a        Proposition
	 *     |    !a       All the propositions except a
	 *     |    RE + RE  Choice
	 *     |    RE ; RE  Sequence
	 *     |    RE*      Repetition
	 *     |    (RE)     Bracketed expression
	 */
	
	public abstract NFA toAutomaton(Set<String> identifiers) throws Exception;

	public abstract Set<Var> getVars();
	
	public abstract boolean hasEmpty();
	
	public abstract RE clone();
	
	public abstract RE residual(String input);
	
	public abstract String getConstructor();
	
	public abstract boolean isNothing();
	
	public abstract RE simplify();
	
}
