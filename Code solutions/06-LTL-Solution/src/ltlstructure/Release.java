package ltlstructure;

import java.util.HashSet;
import java.util.Set;

public class Release extends Binary {

	public Release(LTL left, LTL right) {
		super(left, right);
	}

	public Release() {
		super();
	}

	@Override
	public int hashCode() {
		return left.hashCode() + (int) 'R' + right.hashCode();
	}

	@Override
	public String toString() {
		String res = "(";
		res += left.toString();
		res += " Release ";
		res += right.toString();
		res += ")";

		return res;
	}

	@Override
	public Set<LTL> rho(LTL props) {
		Set<LTL> states = new HashSet<>();
		states.addAll(left.rho(props));

		for (LTL ltlR : right.rho(props)) {
			states.add(new AlternatingAnd(ltlR, this));
//			// -- if boolean, evaluate --//
//			if (ltlR.getClass().equals(Bool.class)) {
//				if (((Bool) ltlR).value) { // if true
//					states.add(this);
//				} else {
//					states.add(new Bool(false));
//				}
//			} else { // cannot evaluate
//				states.add(new And(ltlR, this));
//			}
		}

		return states;
	}

	@Override
	public boolean isFinalState() {
		return true;
	}
	
	@Override
	public LTL alternatingRho(Set<LTL> props) {
		LTL result;
		LTL leftRho = left.alternatingRho(props);
		LTL rightRho = right.alternatingRho(props);
		result = new AlternatingAnd(rightRho, new AlternatingOr(leftRho, this));
		if (rightRho instanceof Bool) {
			if (((Bool)rightRho).value) { //true
				// evaluate alternating or
				if (leftRho instanceof Bool) {
					if (((Bool)leftRho).value) {
						result = new Bool(true);
					} else {
						result = this;
					}
				} else {
					result = new AlternatingOr(leftRho, this);
				}
			} else { //false
				result = new Bool(false);
			}
		} else {
			if (leftRho instanceof Bool) {
				if (((Bool)leftRho).value) {
					result = rightRho;
				} else {
					result = new AlternatingAnd(rightRho, this);
				}
			} else {
				result = new AlternatingAnd(rightRho, new AlternatingOr(leftRho, this));
			}
		}
		return result;
	}
}
