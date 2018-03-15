package ltlstructure;

import java.util.HashSet;
import java.util.Set;

public class Next extends Unary {

	public Next(LTL right) {
		super(right);
	}
	
	public Next() {
		super();
	}

	@Override
	public int hashCode() {
		return (int)'X' + right.hashCode();
	}

	@Override
	public String toString() {
		String res = "Next (";
		res += right.toString();
		res += ")";

		return res;
	}

	@Override
	public Set<LTL> rho(LTL props) {
		Set<LTL> states = new HashSet<>();
		states.add(right);
		return states;
	}

	@Override
	public LTL alternatingRho(Set<LTL> props) {
		return right;
	}
}
