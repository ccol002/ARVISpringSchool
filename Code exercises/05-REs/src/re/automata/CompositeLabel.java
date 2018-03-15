package re.automata;

import java.util.Set;
import java.util.TreeSet;

public class CompositeLabel extends Label {

	private Set<String> subLabels = new TreeSet<String>(); 
	
	
	public CompositeLabel()
	{
		super();
	}
	
	public CompositeLabel(String s)
	{
		super();
		addLabel(s);
	}
	
	public CompositeLabel(String... ss)
	{
		super();
		for (String s: ss)
			addLabel(s);
	}
	
//	public CompositeLabel(Set<String> ss)
//	{
//		super();
//		for (String s: ss)
//			addLabel(s);
//	}
	
	public CompositeLabel(Set<State> ss)
	{
		super();
		for (State s : ss)
			addLabel(s.getLabel().toString());
	}
	
	public CompositeLabel addLabel(String s)
	{
		subLabels.add(s);
		return this;
	}
	
	public boolean addComposite(CompositeLabel cl)
	{
		return subLabels.addAll(cl.getSubLabels());
	}
	
	public int hashCode()
	{
		return 0;
	}
	
	public boolean equals(Object o)
	{
		if (o == null)
			return false;
		else if (!(o instanceof CompositeLabel))
			return false;
		else 
			return ((CompositeLabel)o).toString().equals(toString());
	}
	
	public Set<String> getSubLabels()
	{ 
		return subLabels;
	}
	
	public String toString()
	{
		return "{"+subLabels+"}";
	}
	
}
