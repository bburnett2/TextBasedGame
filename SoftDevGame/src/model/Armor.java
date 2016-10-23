package model;

public class Armor extends Item 
{
	
	private int defense;
	private int attack;
	
	protected Armor(Object[] item)
	{
		super(item);
		this.defense = (int) item[4];
		this.attack = (int) item[5];
	}

	//add defense or attack to total
	public void use() 
	{

	}


}
