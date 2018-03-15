package ltlstructure;

import java.util.Set;

public class Eventually extends Unary {

	public Eventually(LTL right) {
		super(right);
	}
	
	public Eventually() {
		super();
	}

	@Override
	public int hashCode() {
		return (int)'F' + right.hashCode();
	}

	@Override
	public String toString() {
		String res = "Eventually (";
		res += right.toString();
		res += ")";

		return res;
	}

	@Override
	public LTL rewrite() {
		right = right.rewrite();
		LTL newFormula = new Until(new Bool(true), right);
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
