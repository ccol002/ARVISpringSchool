package re.automata;

import java.util.Set;

public class State {

	
	private static int id = 0;

	public static final boolean STARTING = true;
	public static final boolean NONSTARTING = false;
	public static final boolean FINAL = true;
	public static final boolean NONFINAL = false;
	
	
	private Label label;
	private boolean starting, finall;

	private boolean tempFinal;//used for intermediary results while complementing to avoid cloning
	
	public State()
	{
		label = new Label(Integer.toString(id++));
		starting = false;
		finall = false;
	}
	
	public State(Label label)
	{
		this.label = label;
		starting = false;
		finall = false;
	}
	
	public State(String s)
	{
		this.label = new Label(s);
		starting = false;
		finall = false;
	}
	
	public State(boolean starting, boolean finall)
	{
		label = new Label(Integer.toString(id++));
		this.starting = starting;
		this.finall = finall;
	}
	
	public State(Label label, boolean starting, boolean finall)
	{
		this.label= label;
		this.starting = starting;
		this.finall = finall;
	}
	
	public State(String s, boolean starting, boolean finall)
	{
		this.label= new Label(s);
		this.starting = starting;
		this.finall = finall;
	}
	
	public State(Set<String> set,boolean starting, boolean finall)
	{
		label= new CompositeLabel();
		((CompositeLabel)label).getSubLabels().addAll(set);
		this.starting = starting;
		this.finall = finall;
	}
	
	public Label getLabel()
	{
		return label;
	}
	
	public boolean isStarting()
	{
		return starting;
	}
	
	public boolean isFinal()
	{
		return finall;
	}
	
	public void setStarting(boolean starting)
	{
		this.starting = starting;
	}
	
	public void setFinal(boolean finall)
	{
		this.finall = finall;
	}
	
	public boolean isTempFinal()
	{
		return tempFinal;
	}
	
	public void applyTempFinal()
	{
		this.finall = tempFinal;
	}
	
	public void setTempFinal(boolean finall)
	{
		this.tempFinal = finall;
	}
	
	
	public int hashCode()
	{
		return label.hashCode();
	}
	
	//note that two state are considered equal if their label is the same
	//(even though their type (starting/final) might differ) 
	public boolean equals(Object o)
	{
		if (o == null)
			return false;
		else if (!(o instanceof State))
			return false;
		else 
		{
			Label l = ((State)o).getLabel();
			if (l == null && label == null)
				return true;
			else if (l==null)
				return false;
			else 
				return l.equals(label);
		}
	}
	
	
	public String toString()
	{
		if (!isStarting() && !isFinal())
			return "N_" + label.toString();
		
		if (isStarting() && isFinal())
			return "SF_" + label.toString();
		
		if (isStarting())
			return "S_" + label.toString();
			
		//if (isFinal())  //commented to remove compiler error
			return "F_" + label.toString();
		
		
	}

	public static void resetIdGenerator() {
	
		State.id = 0;
		
	}
	
}
