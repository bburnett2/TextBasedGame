package model;


public class Consumables extends Item 
{

	int hp;
	
	protected Consumables(Object[] item, Player player)
	{
		super(item, player);
		this.hp = (int) item[6];
	}

	@Override
	protected void use()
	{
		// TODO Auto-generated method stub
		
	}

}
