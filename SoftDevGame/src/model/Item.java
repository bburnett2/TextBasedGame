package model;

public abstract class Item 
{

	private int id;
	private String name;
	private String description;
	private boolean keyItem;

	public boolean isKeyItem() 
	{
		return false;
	}

	public void pickUp() 
	{

	}

	public void add() 
	{

	}

	public void drop() 
	{

	}

	abstract public void use();

}
