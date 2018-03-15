package ltlstructure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bool extends LTL {

	boolean value;

	public Bool(String value) {
		if (value.equals("true")) {
			this.value = true;
		} else if (value.equals("false")) {
			this.value = false;
		}
	}

	public Bool(boolean value) {
		this.value = value;
	}
	
	public LTL moveAlternatingAndsInside() {
		return this;
	}
	
	public Set<LTL> getSetFromAlternatingOrs() {
		Set<LTL> set = new HashSet<LTL>();
		set.add(this);
		return set;
	}
	
	public Set<LTL> getSetFromAlternatingAnds() {
		Set<LTL> set = new HashSet<LTL>();
		set.add(this);
		return set;
	}

	@Override
	public int hashCode() {
		if (value) {
			return 1;
		}
		return 0;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;

		if (getClass() != o.getClass())
			return false;

		if (this.value == ((Bool) o).value) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return value + "";
	}

	@Override
	public LTL rewrite() {
		return this;
	}

	@Override
	public LTL negationNormalForm() {
		return this;
	}

	@Override
	public List<LTL> closure() {
		List<LTL> list = new ArrayList<LTL>();
		list.add(this);
		list.add(new Not(this));
		return list;
	}

	public Set<LTL> props() {
		return new HashSet<>();
	}

	@Override
	public Set<LTL> rho(LTL props) {
		Set<LTL> states = new HashSet<>();
		states.add(new Bool(value));
		return states;
	}

	@Override
	public LTL alternatingRho(Set<LTL> props) {
		return this;
	}

	@Override
	public boolean isFinalState() {
		return value;
	}
}
