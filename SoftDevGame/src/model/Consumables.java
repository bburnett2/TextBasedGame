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

	
	/**
	 * Item 20 is heard coded because it is the only one that will add to maxHealth.
	 * @param player
	 * @return
	 */
	@Override
	protected boolean use(Player player)
	{
		if (this.getItemID() == 20)
			player.addMaxHealth(hp);
		else 
			player.addHealth(hp);
		return false;
	}

	@Override
	protected boolean isEquippable()
	{
		return false;
	}

}
