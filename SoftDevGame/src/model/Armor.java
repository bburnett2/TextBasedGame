package model;

public class Armor extends Item 
{
	
	private int defense;
	private int attack;
	
	protected Armor(Object[] item, Player player)
	{
		super(item, player);
		this.defense = (int) item[4];
		this.attack = (int) item[5];
	}

	//add defense or attack to total
	//bess - will add if completes puzzle
	protected void use() 
	{
		player.setPlayerAttack(player.getPlayerAttack() + this.attack);
	    player.setPlayerDefense(player.getPlayerDefense() + this.defense);
	    player.getPlayerDefense();
		
	}


}
