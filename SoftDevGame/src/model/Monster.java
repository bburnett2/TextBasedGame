package model;

import java.util.ArrayList;

public class Monster extends Character
{

	private int id;
	private String description;

	public Monster(Object[] monster)
	{
		super(monster);
		this.id = (int)monster[0];
		
		addItemAttributes();
		/**At the moment there is not a object from the DB with this information therefore
		not in the object array.  We can derive this from the constructor if you would
		like.
		this.deathscription = (String)monster[6];**/
	}
	
	private void addItemAttributes()
	{
				
	}

	@Override
	protected void takeDamage(Character attacker, int damage)
	{		
//		super.takeDamage(attacker, damage);
//		
//		if (health < 1)
//		{
//			//print the string returned by the die() method
//			this.die(attacker);
//		}
//		else
//		{
//			this.attack(player);
//		}
	}
	
	@Override
	protected String die()
	{
//		player.setFightingStatus(false);
//		player.addDefeatedMonster(this.id);
//		/** for now and testing adding a static string return
//		 * return deathscription;
//		**/
		return "Monster died";
	}

	protected int getId()
	{
		return id;
	}

	protected String getDescription()
	{
		return description;
	}

	protected int getAttack()
	{
		return attack;
	}

	protected int getHealth()
	{
		return health;
	}

	protected int getDefence()
	{
		return defence;
	}


//	protected void startFight() 
//	{
//		player.setFightingStatus(true);
//	}

}
