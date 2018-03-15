package ltlstructure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Binary extends LTL {

	public LTL left;
	public LTL right;
	
	public Binary() {
		super();
	}

	public Binary(LTL left, LTL right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		
		 if (getClass() != o.getClass())
	            return false;
		 
		if (this.left.equals(((Binary)o).left) && this.right.equals(((Binary)o).right)) {
			return true;
		}
		return false;
	}

	public String toString() {
		String res = "";
		res += left.toString();
		res += right.toString();

		return res;
	}

	public LTL rewrite() {
		left = left.rewrite();
		right = right.rewrite();
		return this;
	}

	public LTL negationNormalForm() {
		LTL newleft = left.negationNormalForm();
		LTL newright = right.negationNormalForm();
		left = newleft;
		right = newright;
		return this;
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

	public List<LTL> closure() {
		List<LTL> closures = new ArrayList<>(right.closure());
		closures.addAll(left.closure());
		if (this instanceof And){
			closures.add(new And(left, right));
			closures.add(new Or(new Not(left), new Not(right)));
		} else if (this instanceof Or){
			closures.add(new Or(left, right));
			closures.add(new And(new Not(left), new Not(right)));
		} else if (this instanceof Release){
			closures.add(new Release(left, right));
			closures.add(new Until(new Not(left), new Not(right)));
		} else if (this instanceof Until){
			closures.add(new Until(left, right));
			closures.add(new Release(new Not(left), new Not(right)));
		}
		//closures.add(this);
		//closures.add(new Not(this));
		return closures;
	}

	public Set<LTL> props() {
		Set<LTL> props = left.props();
		props.addAll(right.props());
		return props;
	}
}
