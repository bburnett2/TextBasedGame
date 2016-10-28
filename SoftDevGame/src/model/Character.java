package model;

import java.util.ArrayList;

public abstract class Character 
{

	protected String name;
	protected int health;
	protected int defense;
	protected int attack;
	private String description;
	protected ArrayList<Item> itemList;
	
	public Character(){
		health = 10;
		defense = 5;
		attack = 5;
		itemList = new ArrayList<>();
	}
	
	/**getItemList
	 * @return itemList: ArrayList<Item> - 
	 * 
	 * returns the itemList
	 */
	protected ArrayList<Item> getItemList()
	{
		return itemList;
	}

	public Character(Object[] monster)
	{

		this.description = (String)monster[1];
		this.attack = (int)monster[2];
		this.health = (int)monster[3];
		this.defense = (int)monster[4];
		this.itemList = buildItems((ArrayList<Integer>)monster[5]);

		/**At the moment there is not a object from the DB with this information therefore
		not in the object array.  We can derive this from the constructor if you would
		like.
		this.deathscription = (String)monster[6];**/
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
			text.append(attacker.name + "'s attack was completely negated.\n");
		}
		return text.toString();
	}
	
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
	protected int getHealth()
	{
		return health;
	}
	
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

	/**setDefense
	 * @param defense: int - defense
	 *
	 * sets defense to the given value
	 */
	protected void setDefense(int defence)
	{
		this.defense = defence;
	}
	
	protected void addItem(Item itemToAdd){
		itemList.add(itemToAdd);
	}

	abstract protected String die(Character attacker);


//	void addCompletedPuzzle(Integer integer)
//	{
//		
//	}

}
