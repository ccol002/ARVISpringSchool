package re.structure;

import java.util.Set;

import re.automata.NFA;


public class Seq extends RE{

	private RE op1, op2;
	
	public Seq(RE op1, RE op2)
	{
		this.op1 = op1;
		this.op2 = op2;
	}	
	
	public RE get1()
	{
		return op1;
	}
	
	public RE get2()
	{
		return op2;
	}
	
	public Set<Var> getVars()
	{
		Set<Var> vars = op1.getVars();
		vars.addAll(op2.getVars());
		
		return vars;
	}
	
	public NFA toAutomaton(Set<String> identifiers) throws Exception
	{
		return NFA.sequence(op1.toAutomaton(identifiers), op2.toAutomaton(identifiers));
	}
	
	public String toString()
	{
		return "(" + op1 + " ; " + op2 + ")";
	}
	
	public boolean hasEmpty()
	{
		return op1.hasEmpty() && op2.hasEmpty();
	}
	
	public boolean isNothing()
	{
		return op1.isNothing() || op2.isNothing();
	}
	
	public RE clone()
	{
		return new Seq(op1.clone(), op2.clone());
	}
	
	public String getConstructor()
	{
		return "new Seq(" + op1.getConstructor() + "," + op2.getConstructor()+ ")";
	}
	
	public RE residual(String input)
	{
		if (op1.hasEmpty())
			return new Or(new Seq (op1.residual(input),op2), op2.residual(input));
		else
			return new Seq(op1.residual(input), op2);
	}
	
	public RE simplify()
	{
		op1 = op1.simplify();
		op2 = op2.simplify();
		
		if (op1 instanceof Nothing)
			return new Nothing();
		else if (op1 instanceof End)
			return op2;
		
		return this;
	}
	
}
