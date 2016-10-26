package model;

import java.util.ArrayList;

public abstract class Character 
{

	private String name;
	private int health;
	private int defence;
	private int attack;
	private ArrayList itemList;

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
			health -= (damage - defence);
		}
		if (health < 1)
		{
			//print the string returned by the die() method
			die(attacker);
		}
	}

	abstract public String die(Character character);
}
