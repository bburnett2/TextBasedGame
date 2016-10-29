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
	protected void use(Character character) 
	{
		character.setAttack(character.getAttack() + this.attack);
	    character.setDefense(character.getDefense() + this.defense);		
	}

	@Override
	protected boolean use(Player player)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean isEquippable()
	{
		return true;
	}

}
