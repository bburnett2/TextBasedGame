package model;


public class Consumables extends Item 
{

	int hp;
	
	protected Consumables(Object[] item)
	{
		super(item);
		this.hp = (int) item[6];
	}

	@Override
	protected void use(Character character)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean use(Player player)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
