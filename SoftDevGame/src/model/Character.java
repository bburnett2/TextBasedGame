package model;

import java.util.ArrayList;

/**Class: Character.java 
   * @author Bess Burnett, Daniel Harris, Michael Holtmann, Marcus Moss
   * @version 1.0 
   * Course : ITEC 3860  Fall 2016
   * Written: Nov 14, 2016 
  	   * 
   * This class –  
   * 
   * Purpose: –  
   */
public abstract class Character 
{

	protected String name;
	protected int health;
	protected int defense;
	protected int attack;


	public Character(String name){
		health = 10;
		defense = 5;
		attack = 5;
		this.name = name;
	}

	/**getItemList
	 * @return itemList: ArrayList<Item> - 
	 * 
	 * returns the itemList
	 */

	public Character(Object[] character)
	{
		this.name = (String)character[0];
		this.attack = (int)character[3];
		this.health = (int)character[4];
		this.defense = (int)character[5];
	}

	/**
	 * calls the takeDamage() method on the enemy, using this Character's attack power
	 * @param enemy	the one who is being attacked
	 */

	protected String attack(Character enemy) 
	{ 
		return (enemy.takeDamage(this));
	}

	/**
	 * implements damage reduction based on the defender's defense value
	 * defender's health will then be reduced by nonnegative values
	 * if the defender would die from this, the die() method is invoked
	 * @param attacker	the Character who is attacking
	 */	

	protected String takeDamage(Character attacker) 
	{
		StringBuilder text = new StringBuilder(attacker.name + " attacks " + this.name + ".\n");
		if (attacker.attack > this.defense)
		{
			int damage = (attacker.attack - this.defense);
			health -= damage;
			text.append(damage + " damage\n");
		}
		else
		{
			text.append(attacker.name + "'s attack was completely neutralized.\n");
		}
		return text.toString();
	}

	/**
	 * Method name: buildItems
	 * @param itemInts
	 * @return
	 */
	protected ArrayList<Item> buildItems(ArrayList<Integer> itemInts)
	{
		GameModel model = new GameModel();
		ArrayList<Item> items = new ArrayList<Item>();

		int count = 0;
		if(!itemInts.isEmpty())
		{
			while(count < itemInts.size())
			{
				Object[] item = model.getItemInfo(itemInts.get(count));
				String type = (String)item[2];

				if (type.equalsIgnoreCase("Armor"))
					items.add(new Armor(item));
				else if(type.equalsIgnoreCase("Artifacts"))
					items.add(new Artifacts(item));
				else if(type.equalsIgnoreCase("Consumables"))
					items.add(new Consumables(item));
				count++;
			}
		}
		return items;
	}

	/**getHealth
	 * @return health: int - 
	 * 
	 * returns the health
	 */
	public int getHealth()
	{
		return health;
	}

	/**
	 * Method name: isDead
	 * @return
	 */
	protected boolean isDead()
	{
		return (health < 1);
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

	protected String getName()
	{
		return name;
	}

	protected void setName(String name)
	{
		this.name = name;
	}

	/**setDefense
	 * @param defense: int - defense
	 *
	 * sets defense to the given value
	 */
	protected void setDefense(int defence)
	{
		this.defense = defence;
	}

	/**
	 * Method name: die
	 * @param attacker
	 * @return
	 */
	abstract protected String die(Character attacker);

}
