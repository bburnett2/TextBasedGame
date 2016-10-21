package model;

public abstract class Item 
{

	private int id;
	private String name, description, action;
	private boolean keyItem;
	
	public Item(Object[] item)
	{
		this.id = (int)item[0];
		this.name = (String) item[1];
		this.description = (String)item[3];
		this.keyItem = (boolean)item[7];
		this.action = (String) item[8];
	}

	public boolean isKeyItem() 
	{
		return keyItem;
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
