package model;

public class Puzzle 
{

	private int id;
	private boolean isCompleted;
	private String description;
	private String answer;
	private int itemNeeded;
	private int itemReturned;
	private boolean completesLevel;


	protected Puzzle(Object[] puzzle)
	{
		this.id = (int)puzzle[0];
		this.description = (String)puzzle[1];
		this.answer = (String)puzzle[2];
		this.itemNeeded = (int)puzzle[3];
		this.itemReturned = (int)puzzle[4];
		this.completesLevel = (boolean)puzzle[5];

		this.isCompleted = false;
	}

	protected boolean answer(String usrAmswer) 
	{
		if (answer.equalsIgnoreCase(usrAmswer))
			isCompleted = true;
		return isCompleted;
	}

	protected int getId()
	{
		return id;
	}

	protected void setCompleted(boolean isCompleted)
	{
		this.isCompleted = isCompleted;
	}

	protected boolean completesLevel(){
		return completesLevel;
	}

	protected String getDescription()
	{
		return description;
	}

}