package model;

import java.util.ArrayList;

/**Class: Player.java 
   * @author Bess Burnett, Daniel Harris, Michael Holtmann, Marcus Moss
   * @version 1.0 
   * Course : ITEC 3860  Fall 2016
   * Written: Nov 14, 2016 
  	   * 
   * This class –  
   * 
   * Purpose: –  
   */
public class Player extends Character 
{
	private ArrayList<Item> unequippedItems = new ArrayList<Item>();
	private ArrayList<Integer> completedPuzzles = new ArrayList<Integer>();
	private ArrayList<Integer> defeatedMonsters = new ArrayList<Integer>();
	private ArrayList<Item> equipedItems = new ArrayList<Item>();
	protected int currentRoom, previousRoom, maxHealth;
	private boolean isFighting;

	/**Player
	 * @param playerID
	 * agr constructor
	 *
	 * creates a Player object with 
	 */
	protected Player(String playerID)
	{
		super(playerID);
		currentRoom = 3;
		previousRoom = 3;
		maxHealth = 10;
		isFighting = false;
	}

	/**Player
	 * @param player
	 * agr constructor
	 *
	 * creates a Player object with 
	 */
	protected Player(Object[] player)
	{
		super(player);
		currentRoom = (int)player[1];
		completedPuzzles = (ArrayList<Integer>)player[2];
		unequippedItems = buildItems((ArrayList<Integer>)player[6]);
		defeatedMonsters = (ArrayList<Integer>)player[7];
		equipedItems = buildItems((ArrayList<Integer>)player[8]);
		maxHealth = 10;
	}

	/**
	 * Method name: addItem
	 * @param item
	 * @return
	 */
	protected String addItem(Item item)
	{
		if(!unequippedItems.contains(item)){
			unequippedItems.add(item);
		}
		return "Item has been added to unequipped items";
	}

	/**
	 * Method name: addCompletedPuzzle
	 * @param id
	 */
	protected void addCompletedPuzzle(int id)
	{
		if(!completedPuzzles.contains(id)){
			completedPuzzles.add(id);
		}
	}

	/**
	 * Method name: addDefeatedMonster
	 * @param id
	 */
	protected void addDefeatedMonster(int id)
	{
		if(!defeatedMonsters.contains(id)){
			defeatedMonsters.add(id);
		}
	}

	/**
	 * Method name: addEquippedItem
	 * @param itemName
	 * @return
	 */
	protected String addEquippedItem(String itemName)
	{
		String str = "";
		if (hasUnequippedItem(itemName))
		{
			for (int i = 0; i < unequippedItems.size(); i++)
			{
				if (unequippedItems.get(i).getName().equalsIgnoreCase(itemName))
				{
					if(unequippedItems.get(i).isEquippable())
					{
						unequippedItems.get(i).use(this);
						Armor temp = (Armor)unequippedItems.get(i);
						if(!equipedItems.contains(temp)){
							equipedItems.add(temp);
						}
						str = "Item " + unequippedItems.get(i).getName() + " has been equipped";
						if (temp.attack > 0)
							str += "\n" + name + " attack has been increased by " + temp.attack + " points.";
						if (temp.defense > 0)
							str += "\nDefense has been increased by " + temp.defense + " points.";
						unequippedItems.remove(temp);
					}
					else
						return unequippedItems.get(i).getName() + " is not an item that can be equipped";
				}

			}
		}
		else
			str = "No item can be quipped";
		return str;	
	}

	
	/** Method name: die
	 * @param attacker
	 * @return
	 *
	 */
	protected String die(Character attacker)
	{
		String deathscription = ("You were killed by the " + attacker.name + ".\n");	
		return deathscription;
	}

	/** Method name: buildItems
	 * @param itemInts
	 * @return
	 *
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
				else if(type.equalsIgnoreCase("consumable"))
					items.add(new Consumables(item));
				count++;
			}
		}
		return items;
	}

	/**
	 * Method name: listItems
	 * @return
	 */
	protected String listItems()
	{
		String str ="Your inventory\n";

		if (unequippedItems.size() > 0)
		{
			str += "Unequiped Items\n";
			for (Item item : unequippedItems)
				str += item.getName() + "\n";
		}

		if (equipedItems.size() > 0)
		{
			str += "\nEquiped Items\n";
			for (Item item : equipedItems)
				str += item.getName() + "\n";
		}
		if (unequippedItems.size() == 0 && equipedItems.size() == 0)
			str += "empty";
		return str;
	}

	/**
	 * Method name: drop
	 * @param itemName
	 * @return
	 */
	protected String drop(String itemName)
	{
		String str = "";
		if (hasItem(itemName))
		{
			for (int i = 0; i < unequippedItems.size(); i++)
			{
				if(unequippedItems.get(i).getName().equalsIgnoreCase(itemName) && !unequippedItems.get(i).isKeyItem())
				{
					str = "You have dropped " + unequippedItems.get(i).getName();
					unequippedItems.remove(i);
				}
			}

			for (int i = 0; i < equipedItems.size(); i++)
			{
				if(equipedItems.get(i).getName().equalsIgnoreCase(itemName) && !equipedItems.get(i).isKeyItem())
				{
					str = "You have dropped " + equipedItems.get(i).getName();
					Armor temp = (Armor)equipedItems.get(i);
					setAttack(attack - temp.attack);
					setDefense(defense - temp.defense);
					equipedItems.remove(i);
				}
			}
		}
		else
			str = "You do not have that item to drop";
		return str;
	}

	/**
	 * Method name: hasUnequippedItem
	 * @param itemName
	 * @return
	 */
	private boolean hasUnequippedItem(String itemName)
	{
		boolean hasItem = false;
		for (Item item : unequippedItems)
		{

			if (item.getName().equalsIgnoreCase(itemName))
				hasItem = true;
		}
		return hasItem;
	}

	/**
	 * Method name: hasItem
	 * @param itemName
	 * @return
	 */
	protected boolean hasItem(String itemName) 
	{
		itemName.trim();
		boolean hasItem = false;
		for (Item item : unequippedItems)
		{
			if (item.getName().equalsIgnoreCase(itemName))
				hasItem = true;
		}

		for (Item item : equipedItems)
		{
			if (item.getName().equalsIgnoreCase(itemName))
				hasItem = true;
		}
		return hasItem;
	}

	/**
	 * Method name: hasItem
	 * @param itemID
	 * @return
	 */
	protected boolean hasItem(int itemID) 
	{
		boolean hasItem = false;
		for (Item item : unequippedItems)
		{
			if (item.getItemID() == itemID)
				hasItem = true;
		}

		for (Item item : equipedItems)
		{
			if (item.getItemID() == itemID)
				hasItem = true;
		}
		return hasItem;
	}

	/**
	 * Method name: hasDefeated
	 * @param monster
	 * @return
	 */
	protected boolean hasDefeated(int monster)
	{
		return defeatedMonsters.contains(monster);
	}

	/**
	 * Method name: hasCompleted
	 * @param puzzle
	 * @return
	 */
	protected boolean hasCompleted(int puzzle)
	{
		return completedPuzzles.contains(puzzle);
	}

	/**
	 * Method name: useItem
	 * @param item
	 * @return
	 */
	protected boolean useItem(Item item) 
	{
		return item.use(this);
	}

	/**
	 * Method name: getCurrentRoom
	 * @return
	 */
	public int getCurrentRoom()
	{
		return currentRoom;
	}

	/**
	 * Method name: setCurrentRoom
	 * @param currentRoom
	 */
	protected void setCurrentRoom(int currentRoom)
	{
		this.previousRoom = this.currentRoom;
		this.currentRoom = currentRoom;
	}

	public int getPreviousRoom()
	{
		return previousRoom;
	}


	/**getUnequippedItems
	 * @return unequippedItems: ArrayList<Integer> - 
	 * 
	 * returns the unequippedItems
	 */
	protected ArrayList<Item> getUnequippedItems()
	{
		return unequippedItems;
	}

	/**getCompletedPuzzles
	 * @return completedPuzzles: ArrayList<Integer> - 
	 * 
	 * returns the completedPuzzles
	 */
	protected ArrayList<Integer> getCompletedPuzzles()
	{
		return completedPuzzles;
	}

	/**getDefeatedMonsters
	 * @return defeatedMonsters: ArrayList<Integer> - 
	 * 
	 * returns the defeatedMonsters
	 */
	protected ArrayList<Integer> getDefeatedMonsters()
	{
		return defeatedMonsters;
	}

	/**getEquipedItems
	 * @return equipedItems: ArrayList<Item> - 
	 * 
	 * returns the equipedItems
	 */
	protected ArrayList<Item> getEquipedItems()
	{
		return equipedItems;
	}

	//	protected boolean getFightingStatus()
	//	{
	//		return isFighting;
	//	}

	/**
	 * Method name: isFighting
	 * @return
	 */
	protected boolean isFighting()
	{
		return isFighting;
	}

	protected void setFightingStatus(boolean status)
	{
		isFighting = status;
	}

	/**
	 * Method name: addMaxHealth
	 * @param hp
	 */
	protected void addMaxHealth(int hp)
	{
		maxHealth += hp;		
	}

	/**
	 * Method name: addHealth
	 * @param hp
	 */
	protected void addHealth(int hp)
	{
		if (super.health + hp <= maxHealth)
			super.health += hp;	
		else
			super.health = maxHealth;
	}

	/**
	 * Method name: stats
	 * @return
	 */
	protected String stats()
	{
		return name + " current health is " + health + " and  can have max health of " + maxHealth + ". \n" +
				"They have " + attack + " attack and " + defense + " defense.";
	}

	/**
	 * Method name: removeItem
	 * @param id
	 */
	public void removeItem(int id)
	{
		for(int i = 0; i < unequippedItems.size(); i++)
		{
			if (unequippedItems.get(i).getItemID() == id)
				unequippedItems.remove(i);
		}

	}

}
