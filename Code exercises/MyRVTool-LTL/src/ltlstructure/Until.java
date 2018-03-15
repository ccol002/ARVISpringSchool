package ltlstructure;

import java.util.HashSet;
import java.util.Set;

public class Until extends Binary {

	public Until(LTL left, LTL right) {
		super(left, right);
	}

	public Until() {
		super();
	}

	@Override
	public int hashCode() {
		return left.hashCode() + (int) 'U' + right.hashCode();
	}

	@Override
	public String toString() {
		String res = "(";
		res += left.toString();
		res += " Until ";
		res += right.toString();
		res += ")";

		return res;
	}

	@Override
	public Set<LTL> rho(LTL props) {
		Set<LTL> states = new HashSet<>();
		states.addAll(right.rho(props));

		for (LTL ltlL : left.rho(props)) {
			states.add(new AlternatingAnd(ltlL, this));
//			// -- if boolean, evaluate --//
//			if (ltlL.getClass().equals(Bool.class)) {
//				if (((Bool) ltlL).value) { // if true
//					states.add(this);
//				} else {
//					states.add(new Bool(false));
//				}
//			} else { // cannot evaluate
//				states.add(new And(ltlL, this));
//			}
		}

		return states;
	}

	@Override
	public LTL alternatingRho(Set<LTL> props) {
		LTL result;
		LTL rightRho = right.alternatingRho(props);
		LTL leftRho = left.alternatingRho(props);
		result = new AlternatingOr(rightRho, new AlternatingAnd(leftRho, this));
		if (rightRho instanceof Bool) {
			if (((Bool)rightRho).value) { //true
				result = new Bool(true);
			} else { //false
				if (leftRho instanceof Bool) {
					if (((Bool)leftRho).value) {
						result = this;
					} else {
						result = new Bool(false);
					}
				} else {
					result = new AlternatingAnd(leftRho, this);
				}
			}
		} else {
			if (leftRho instanceof Bool) {
				if (((Bool)leftRho).value) {
					result = new AlternatingOr(rightRho, this);
				} else {
					result = rightRho;
				}
			} else {
				result = new AlternatingOr(rightRho, new AlternatingAnd(leftRho, this));
			}
		}
		return result;
	}
}
