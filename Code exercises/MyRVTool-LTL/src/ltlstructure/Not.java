package ltlstructure;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Not extends Unary {

	public Not(LTL right) {
		super(right);
	}

	public Not() {
		super();
	}

	@Override
	public int hashCode() {
		return (int) '!' + right.hashCode();
	}

	@Override
	public String toString() {
		String res = "not ";
		res += right.toString();

		return res;
	}

	@Override
	public LTL negationNormalForm() {
		LTL newFormula;
		switch (right.getClass().getSimpleName()) {
		case "AlternatingAnd":
			AlternatingAnd alternatingAnd = new AlternatingAnd(((AlternatingAnd)right).left, ((AlternatingAnd)right).right);
			alternatingAnd.left = new Not(alternatingAnd.left);
			alternatingAnd.right = new Not(alternatingAnd.right);
			newFormula = new AlternatingOr(alternatingAnd.left.negationNormalForm(), alternatingAnd.right.negationNormalForm());
			break;
		case "AlternatingOr":
			AlternatingOr alternatingOr = new AlternatingOr(((AlternatingOr)right).left, ((AlternatingOr)right).right);
			alternatingOr.left = new Not(alternatingOr.left);
			alternatingOr.right = new Not(alternatingOr.right);
			newFormula = new AlternatingAnd(alternatingOr.left.negationNormalForm(), alternatingOr.right.negationNormalForm());
			break;
		case "And":
			And and = new And(((And)right).left, ((And)right).right);
			and.left = new Not(and.left);
			and.right = new Not(and.right);
			newFormula = new Or(and.left.negationNormalForm(), and.right.negationNormalForm());
			break;
		case "Bool":
			Bool bool = new Bool(((Bool)right).value);
			return new Bool(!bool.value);
		case "Next":
			Next next = new Next(((Next)right).right);
			next.right = new Not(next.right);
			newFormula = new Next(next.right.negationNormalForm());
			break;
		case "Not":
			Not not = new Not(((Not)right).right);
			newFormula = not.right;
			break;
		case "Or":
			Or or = new Or(((Or)right).left, ((Or)right).right);
			or.left = new Not(or.left);
			or.right = new Not(or.right);
			newFormula = new And(or.left.negationNormalForm(), or.right.negationNormalForm());
			break;
		case "Release":
			Release release = new Release(((Release)right).left, ((Release)right).right);
			release.left = new Not(release.left);
			release.right = new Not(release.right);
			newFormula = new Until(release.left.negationNormalForm(),
					release.right.negationNormalForm());
			break;
		case "Until":
			Until until = new Until(((Until)right).left, ((Until)right).right);
			until.left = new Not(until.left);
			until.right = new Not(until.right);
			newFormula = new Release(until.left.negationNormalForm(),
					until.right.negationNormalForm());
			break;
		case "Prop":
			return this;
		default:
			newFormula = this;
		}

		right = newFormula.negationNormalForm();
		return right;

	}

	@Override
	public List<LTL> closure() {
		List<LTL> closures = right.closure();
		if (!right.getClass().equals(Prop.class)) { // to avoid double prop
													// states
			closures.add(this);
			closures.add(new Not(this));
		}
		return closures;
	}

	@Override
	public Set<LTL> rho(LTL props) {
		Set<LTL> states = new HashSet<>();
		Set<LTL> rightStates = right.rho(props);
		for (LTL ltl : rightStates) {
			// if boolean, evaluate
			if (ltl.getClass().equals(Bool.class)) {
				// -- flip value of boolean --//
				states.add(new Bool(!((Bool) ltl).value));
			} else {
				// -- add not to prop --//
				states.add(new Not(ltl));
			}
		}
		return states;
	}

	@Override
	public boolean isFinalState() {
		if (right.getClass().equals(Until.class)) {
			return true;
		}

		if (right instanceof Bool && !((Bool)right).value) {
			return true;
		}
		return false;
	}

	@Override
	public LTL alternatingRho(Set<LTL> props) {
		LTL result;
		LTL rightRho = right.alternatingRho(props);
		if (rightRho instanceof Bool) {
			result = new Bool(!((Bool)rightRho).value);
		} else {
			result = new Not(rightRho);
		}
		return result;
	}
}
