package ltlstructure;

import java.util.HashSet;
import java.util.Set;

public class AlternatingOr extends Binary {

	public AlternatingOr(LTL left, LTL right) {
		super(left, right);
	}

	public AlternatingOr() {
		super();
	}

	@Override
	public int hashCode() {
		return left.hashCode() + (int) '|' + right.hashCode();
	}

	@Override
	public String toString() {
		String res = "(";
		res += left.toString();
		res += " | ";
		res += right.toString();
		res += ")";

		return res;
	}

	@Override
	public Set<LTL> rho(LTL props) {
		Set<LTL> states = new HashSet<>();
		states.addAll(right.rho(props));
		states.addAll(left.rho(props));
		return states;
	}

	@Override
	public LTL alternatingRho(Set<LTL> props) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LTL moveAlternatingAndsInside() {
		LTL newleft = left.moveAlternatingAndsInside();
		LTL newright = right.moveAlternatingAndsInside();
		LTL newFormula = new AlternatingOr(newleft, newright);
		return newFormula;
	}
	
	@Override
	public Set<LTL> getSetFromAlternatingOrs() {
		Set<LTL> set = new HashSet<LTL>();
		set.addAll(left.getSetFromAlternatingOrs());
		set.addAll(right.getSetFromAlternatingOrs());
		return set;
	}
}
