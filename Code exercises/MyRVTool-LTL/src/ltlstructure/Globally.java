package ltlstructure;

import java.util.Set;

public class Globally extends Unary {

	public Globally(LTL right) {
		super(right);
	}
	
	public Globally() {
		super();
	}

	@Override
	public int hashCode() {
		return (int)'G' + right.hashCode();
	}

	@Override
	public String toString() {
		String res = "Globally (";
		res += right.toString();
		res += ")";

		return res;
	}

	@Override
	public LTL rewrite() {
		right = right.rewrite();
		LTL newFormula = new Release(new Bool(false), right);
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
