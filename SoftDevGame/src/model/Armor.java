package model;

public class Armor extends Item 
{
	
	protected int defense;
	protected int attack;
	
	protected Armor(Object[] item)
	{
		super(item);
		this.defense = (int) item[4];
		this.attack = (int) item[5];
	}


	@Override
	protected boolean use(Player player)
	{
		player.setAttack(player.getAttack() + this.attack);
	    player.setDefense(player.getDefense() + this.defense);
		return false;
	}

	@Override
	protected boolean isEquippable()
	{
		return true;
	}

}
