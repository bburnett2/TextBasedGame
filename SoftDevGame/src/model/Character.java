package model;

import java.util.ArrayList;

public abstract class Character 
{

	protected String name;
	protected int health;
	protected int defence;
	protected int attack;
	private String description;
	protected ArrayList itemList;
	
	public Character(Object[] monster)
	{

		this.description = (String)monster[1];
		this.attack = (int)monster[2];
		this.health = (int)monster[3];
		this.defence = (int)monster[4];
		this.itemList = (ArrayList<Item>) monster[5];

		/**At the moment there is not a object from the DB with this information therefore
		not in the object array.  We can derive this from the constructor if you would
		like.
		this.deathscription = (String)monster[6];**/
	}

	/**
	 * calls the takeDamage() method on the enemy, using this Character's attack power
	 * @param enemy	the one who is being attacked
	 */
	
	protected void attack(Character enemy) 
	{
		//print something like (name + " attacks " + enemy.name + ".\n") 
		enemy.takeDamage(this, attack);
	}
	
	/**
	 * implements damage reduction based on the defender's defense value
	 * defender's health will then be reduced by nonnegative values
	 * if the defender would die from this, the die() method is invoked
	 * @param attacker	the Character who is attacking
	 * @param damage	the power of the attack before reduction by the enemy's defense
	 */

	protected void takeDamage(Character attacker, int damage) 
	{
		if (damage > this.defence)
		{
			health -= (damage - this.defence);
		}
		else
		{
			//print something signifying that the damage of the attack was completely negated
		}
	}

	/**getHealth
	 * @return health: int - 
	 * 
	 * returns the health
	 */
	protected int getHealth()
	{
		return health;
	}
	
	protected boolean isDead()
	{
		boolean dead = true;
		if (health > 0) dead = false;
		return dead;
	}

	/**getAttack
	 * @return attack: int - 
	 * 
	 * returns the attack
	 */
	protected int getAttack()
	{
		return attack;
	}

	/**setHealth
	 * @param health: int - health
	 *
	 * sets health to the given value
	 */
	protected void setHealth(int health)
	{
		this.health = health;
	}

	/**setAttack
	 * @param attack: int - attack
	 *
	 * sets attack to the given value
	 */
	protected void setAttack(int attack)
	{
		this.attack = attack;
	}

	/**getDefense
	 * @return defense: int - 
	 * 
	 * returns the defense
	 */
	protected int getDefense()
	{
		return defence;
	}

	/**setDefense
	 * @param defense: int - defense
	 *
	 * sets defense to the given value
	 */
	protected void setDefense(int defence)
	{
		this.defence = defence;
	}
	
	protected abstract String die(Character character);
	
}
