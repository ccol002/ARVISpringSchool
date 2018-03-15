package ltlstructure;

import java.util.Set;

public class Xor extends Binary {

	public Xor(LTL left, LTL right) {
		super(left, right);
	}
	
	public Xor() {
		super();
	}

	@Override
	public int hashCode() {
		return left.hashCode() + (int)'x' + right.hashCode();
	}

	@Override
	public String toString() {
		String res = "(";
		res += left.toString();
		res += " xor ";
		res += right.toString();
		res += ")";

		return res;
	}

	@Override
	public LTL rewrite() {
		left = left.rewrite();
		right = right.rewrite();
		LTL newFormula = new Or(new And(left, new Not(right)), new And(right,
				new Not(left)));
		return newFormula;
	}

	@Override
	public Set<LTL> rho(LTL props) {
		// -- will never be used due to rewriting rules --//
		return null;
	}

	@Override
	public LTL alternatingRho(Set<LTL> props) {
		// -- will never be used due to rewriting rules --//
		return null;
	}
}
