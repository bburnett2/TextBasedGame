package model;

import java.util.ArrayList;

public abstract class Character 
{

	protected String name;
	protected int health;
	protected int defense;
	protected int attack;
	protected ArrayList itemList;

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
		if (damage > this.defense)
		{
			health -= (damage - this.defense);
		}
		if (health < 1)
		{
			//print the string returned by the die() method
			die(attacker);
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
		return defense;
	}

	/**setDefense
	 * @param defense: int - defense
	 *
	 * sets defense to the given value
	 */
	protected void setDefense(int defense)
	{
		this.defense = defense;
	}
	
	protected abstract String die(Character character);
	
}
