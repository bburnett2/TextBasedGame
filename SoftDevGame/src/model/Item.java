package model;

import java.util.Map;
import java.util.TreeMap;

public abstract class Item 
{

	private int id;
	protected String name, description, action;
	private boolean keyItem;
	protected Map<Boolean, Integer> completesPuzzle;
	protected String type;

	public Item(Object[] item)
	{
		this.id = (int)item[0];
		this.name = (String) item[1];
		type = (String) item[2];
		this.description = (String)item[3];
		this.keyItem = (boolean)item[7];
		this.action = (String) item[8];
		this.completesPuzzle = (TreeMap<Boolean, Integer>)item[9];
	}

	protected String getType(){
		return type;
	}
	
	protected int getItemID()
	{
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


	abstract protected boolean use(Player player);

	abstract protected boolean isEquippable();

}
