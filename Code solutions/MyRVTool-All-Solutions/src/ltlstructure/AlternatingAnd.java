package ltlstructure;

import java.util.HashSet;
import java.util.Set;

public class AlternatingAnd extends Binary {

	public AlternatingAnd(LTL left, LTL right) {
		super(left, right);
	}

	public AlternatingAnd() {
		super();
	}

	@Override
	public int hashCode() {
		return left.hashCode() + (int) '&' + right.hashCode();
	}

	@Override
	public String toString() {
		String res = "(";
		res += left.toString();
		res += " & ";
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
		LTL newFormula;
		if (newleft instanceof AlternatingOr && newright instanceof AlternatingOr) {
			AlternatingOr leftOr = (AlternatingOr)newleft;
			AlternatingOr rightOr = (AlternatingOr)newright;
			AlternatingAnd and1 = new AlternatingAnd(leftOr.left, rightOr.left);
			AlternatingAnd and2 = new AlternatingAnd(leftOr.left, rightOr.right);
			AlternatingAnd and3 = new AlternatingAnd(leftOr.right, rightOr.left);
			AlternatingAnd and4 = new AlternatingAnd(leftOr.right, rightOr.right);
			newFormula = new AlternatingOr(new AlternatingOr(and1, and2), new AlternatingOr(and3, and4));
		} else if (newleft instanceof AlternatingOr) {
			AlternatingOr leftOr = (AlternatingOr)newleft;
			AlternatingAnd and1 = new AlternatingAnd(leftOr.left, newright);
			AlternatingAnd and2 = new AlternatingAnd(leftOr.right, newright);
			newFormula = new AlternatingOr(and1, and2);
		} else if (newleft instanceof AlternatingOr) {
			AlternatingOr rightOr = (AlternatingOr)newright;
			AlternatingAnd and1 = new AlternatingAnd(rightOr.left, newleft);
			AlternatingAnd and2 = new AlternatingAnd(rightOr.right, newleft);
			newFormula = new AlternatingOr(and1, and2);
		} else {
			newFormula = new AlternatingAnd(newleft, newright);
		}		
		return newFormula;
	}
	
	@Override
	public Set<LTL> getSetFromAlternatingAnds() {
		Set<LTL> set = new HashSet<LTL>();
		set.addAll(left.getSetFromAlternatingAnds());
		set.addAll(right.getSetFromAlternatingAnds());
		return set;
	}
}
