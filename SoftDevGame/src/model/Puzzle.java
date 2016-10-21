package model;

public class Puzzle 
{

	private int id;
	private boolean isCompleted;
	private String description;
	private String answer;
	private int itemNeeded;
	private int itemReturned;
	private int completesLevel;

	public Puzzle(Object[] puzzle)
	{
		this.id = (int)puzzle[0];
		this.description = (String)puzzle[1];
		this.answer = (String)puzzle[2];
		this.itemNeeded = (int)puzzle[3];
		this.itemReturned = (int)puzzle[4];
		this.completesLevel = (int)puzzle[5];
	}

	public void answer() 
	{

	}

}