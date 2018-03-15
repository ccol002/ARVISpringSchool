package ltlstructure;

import java.util.HashSet;
import java.util.Set;

public class Or extends Binary {

	public Or(LTL left, LTL right) {
		super(left, right);
	}

	public Or() {
		super();
	}

	@Override
	public int hashCode() {
		return left.hashCode() + (int) 'v' + right.hashCode();
	}

	@Override
	public String toString() {
		String res = "(";
		res += left.toString();
		res += " or ";
		res += right.toString();
		res += ")";

		return res;
	}

	@Override
	public Set<LTL> rho(LTL props) {
		Set<LTL> states = new HashSet<>();
		Set<LTL> rightStates = right.rho(props);
		Set<LTL> leftStates = right.rho(props);
		for (LTL ltlR : rightStates) {
			for (LTL ltlL : leftStates) {
				if (ltlR.equals(ltlL)) {
					// -- P v P = P --//
					states.add(ltlL);
				} else {
					// if (ltlR.getClass().equals(Bool.class) &&
					// ltlL.getClass().equals(Bool.class)) {
					// states.add(new Bool(((Bool) ltlR).value || ((Bool)
					// ltlL).value));
					// }
					// // -- else follow inductive definition
					// else {
					states.add(ltlL);
					states.add(ltlR);
				}
			}
		}
		return states;
	}
	
	@Override
	public LTL alternatingRho(Set<LTL> props) {
		LTL result;
		LTL leftRho = left.alternatingRho(props);
		LTL rightRho = right.alternatingRho(props);
		if (rightRho instanceof Bool) {
			if (((Bool)rightRho).value) { //true v P =  true
				result = new Bool(true);
			} else { //false v P = P
				result = leftRho;
			}
		} else {
			if (leftRho instanceof Bool) {
				if (((Bool)leftRho).value) { //true v P =  true
					result = new Bool(true);
				} else { //false v P = P
					result = rightRho;
				}
			} else {
				result = new AlternatingOr(leftRho, rightRho);
			}
		}
		return result;
	}
}
