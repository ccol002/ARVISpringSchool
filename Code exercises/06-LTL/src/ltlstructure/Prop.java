package ltlstructure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Prop extends LTL {

	String name;

	public Prop(String name) {
		this.name = name;
	}

	public Prop() {
		super();
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
		return name.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;

		if (getClass() != o.getClass())
			return false;

		if (this.name.equals(((Prop) o).name)) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return name.replaceAll("()", "");
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

	@Override
	public Set<LTL> props() {
		Set<LTL> props = new HashSet<>();
		props.add(this);
		return props;
	}

	@Override
	public Set<LTL> rho(LTL props) {
		Set<LTL> states = new HashSet<>();
		if (((Prop) props).name.equals(this.name)) {
			states.add(new Bool(true));
		}
		// -- if this prop is not in input --//
		if (states.size() == 0) {
			states.add(new Bool(false));
		}
		return states;
	}

	@Override
	public LTL alternatingRho(Set<LTL> props) {
		if (!props.isEmpty() && props.contains(this)) {
			return new Bool(true);
		} else {
			return new Bool(false);
		}
//		if (((Prop) props).name.equals(this.name)) {
//			return new Bool(true);
//		} else {
//			return new Bool(false);
//		}
	}

}
