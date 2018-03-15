package ltlstructure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Unary extends LTL {

	public LTL right;
	
	public Unary() {
		
	}

	public Unary(LTL right) {
		this.right = right;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		
		 if (getClass() != o.getClass())
	            return false;
		 
		if (this.right.equals(((Unary)o).right)) {
			return true;
		}
		return false;
	}

	public String toString() {
		return right.toString();
	}

	public LTL rewrite() {
		right = right.rewrite();
		return this;
	}

	public LTL negationNormalForm() {
		LTL newright = right.negationNormalForm();
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
		if (this instanceof Not){
			closures.add(new Not(right));
			closures.add(right); //due to 'not not'
		} else if (this instanceof Next){
			closures.add(new Next(right));
			closures.add(new Next(new Not(right)));
		}
		//closures.add(this);
		//closures.add(new Not(this));
		return closures;
	}

	public Set<LTL> props() {
		return right.props();
	}
}
