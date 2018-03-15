package ltlstructure;

import java.util.Set;

public class Equivalence extends Binary {

	public Equivalence(LTL left, LTL right) {
		super(left, right);
	}
	
	public Equivalence() {
		super();
	}

	@Override
	public int hashCode() {
		return left.hashCode() + (int)'=' + right.hashCode();
	}

	@Override
	public String toString() {
		String res = "(";
		res += left.toString();
		res += " <=> ";
		res += right.toString();
		res += ")";
		
		return res;
	}

	@Override
	public LTL rewrite() {
		left = left.rewrite();
		right = right.rewrite();
		LTL newFormula = new And(new Or(new Not(left), right), new Or(new Not(
				right), left));
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
