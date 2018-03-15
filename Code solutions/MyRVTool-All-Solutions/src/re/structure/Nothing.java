package re.structure;

import java.util.HashSet;
import java.util.Set;

import re.automata.NFA;



public class Nothing extends RE{

	
	public Nothing()
	{
	}
	
	public Set<Var> getVars()
	{
		return new HashSet<Var>();
	}
	
	public NFA toAutomaton(Set<String> identifiers) throws Exception
	{
		NFA fa = new NFA();
		
		return fa;
	}
	
	
	public String toString()
	{
		return "0";
	}
	
	public boolean hasEmpty()
	{
		return false;
	}
	
	public boolean isNothing()
	{
		return true;
	}
	
	public RE clone()
	{
		return new Nothing();
	}
	
	public String getConstructor()
	{
		return "new Nothing()";
	}
	
	public RE residual(String input)
	{
		return new Nothing();
	}
	
	public RE simplify()
	{	
		return this;
	}
}