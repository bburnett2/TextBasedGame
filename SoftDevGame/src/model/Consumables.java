package model;


public class Consumables extends Item 
{

	int hp;
	
	protected Consumables(Object[] item)
	{
		super(item);
		this.hp = (int) item[6];
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
		player.removeItem(getItemID());
		return false;
	}

	@Override
	protected boolean isEquippable()
	{
		return false;
	}

}
