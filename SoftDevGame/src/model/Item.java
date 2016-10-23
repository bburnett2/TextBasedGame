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

	protected String getName()
	{
		return name;
	}

	protected String getDescription()
	{
		return description;
	}

	protected String getAction()
	{
		return action;
	}

	protected boolean isKeyItem() 
	{
		return keyItem;
	}

	protected void pickUp() 
	{

	}

	protected void drop() 
	{

	}

	abstract public void use();

}
