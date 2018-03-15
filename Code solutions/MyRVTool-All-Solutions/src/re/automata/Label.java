package re.automata;

public class Label {

	public static Label empty = new Label("$");
	
	
	private String s;
	
	
	protected Label()
	{}
	
	public Label(String s)
	{
		this.s = s;
	}
	
	public int hashCode()
	{
		return s.hashCode();
	}
	
	public boolean equals(Object o)
	{
		if (o == null)
			return false;
		else if (!(o instanceof Label))
			return false;
		else 
			return ((Label)o).toString().equals(s);
	}
	
	public String toString()
	{
		return s;
	}
}
