package ltlstructure;

import java.util.List;
import java.util.Set;

public abstract class LTL {

	public int index = -1;
	public int lowlink = -1;
	
	public abstract String toString();

	public abstract LTL negationNormalForm();

	public abstract LTL rewrite();

	public abstract List<LTL> closure();

	public abstract Set<LTL> props();

	public abstract Set<LTL> rho(LTL props);	
	
	public abstract LTL alternatingRho(Set<LTL> props);
	
	public abstract LTL moveAlternatingAndsInside();
	
	public abstract Set<LTL> getSetFromAlternatingOrs();
	
	public abstract Set<LTL> getSetFromAlternatingAnds();

	public boolean isFinalState() {
		return false;
	}

	@Override
	public abstract boolean equals(Object o);

	@Override
	public abstract int hashCode();
}
