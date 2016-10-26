package model;

public abstract class Item 
{

	private int id;
	private String name, description, action;
	private boolean keyItem;
	private Player player;
	
	public Item(Object[] item, Player player)
	{
		this.id = (int)item[0];
		this.name = (String) item[1];
		this.description = (String)item[3];
		this.keyItem = (boolean)item[7];
		this.action = (String) item[8];
		this.player = player;
	}

	protected int getItemID(){
		return id;
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

	abstract protected void use();

}
