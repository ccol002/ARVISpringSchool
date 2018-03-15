package ltlstructure;

import java.util.HashSet;
import java.util.Set;

public class And extends Binary {

	public And(LTL left, LTL right) {
		super(left, right);
	}

	public And() {
		super();
	}

	@Override
	public int hashCode() {
		return left.hashCode() + (int) '^' + right.hashCode();
	}

	@Override
	public String toString() {
		String res = "(";
		res += left.toString();
		res += " and ";
		res += right.toString();
		res += ")";

		return res;
	}

	@Override
	public Set<LTL> rho(LTL props) {
		Set<LTL> states = new HashSet<>();
		Set<LTL> rightStates = right.rho(props);
		Set<LTL> leftStates = right.rho(props);
//		if (leftStates.equals(rightStates)) {
//			return leftStates;
//		}
		for (LTL ltlR : rightStates) {
			for (LTL ltlL : leftStates) {
				states.add(new AlternatingAnd(ltlL, ltlR));
//				if (ltlR.getClass().equals(And.class) && !ltlL.getClass().equals(And.class)
//						&& (((And) ltlR).right.equals(ltlL) || ((And) ltlR).left.equals(ltlL))) {
//					// -- P^P=P --//
//					states.add(ltlR);
//				} else if (ltlL.getClass().equals(And.class) && !ltlR.getClass().equals(And.class)
//						&& (((And) ltlL).right.equals(ltlR) || ((And) ltlL).left.equals(ltlR))) {
//					// -- P^P=P --//
//					states.add(ltlL);
				
				
//				if (ltlR.equals(ltlL)) {
//					// -- P^P=P --//
//					states.add(ltlL);
//				} else if (ltlR.getClass().equals(Bool.class)) {
//					if (ltlL.getClass().equals(Bool.class)) {
//						// -- if both boolean, evaluate --//
//						states.add(new Bool(((Bool) ltlR).value && ((Bool) ltlL).value));
//					} else {
//						// -- if only right is boolean, evaluate --//
//						if (((Bool) ltlR).value) {
//							// -- if right is true, only consider left
//							states.add(ltlL);
//						} else {
//							// -- if right is false, both false
//							states.add(new Bool(false));
//						}
//					}
//				} else {
//					if (ltlL.getClass().equals(Bool.class)) {
//						// -- if only left is boolean, evaluate --//
//						if (((Bool) ltlL).value) {
//							// -- if left is true, only consider right
//							states.add(ltlR);
//						} else {
//							// -- if left is false, both false
//							states.add(new Bool(false));
//						}
//					} else {
//						// -- if neither boolean, follow inductive definition
//						states.add(new And(ltlL, ltlR));
//					}
//				}
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
			if (((Bool)rightRho).value) { //true ^ P =  P
				result = leftRho;
			} else { //false ^ P = false
				result = new Bool(false);
			}
		} else {
			if (leftRho instanceof Bool) {
				if (((Bool)leftRho).value) { //true ^ P =  P
					result = rightRho;
				} else {//false ^ P = false
					result = new Bool(false);
				}
			} else {
				result = new AlternatingAnd(leftRho, rightRho);
			}
		}
		return result;
	}
}
