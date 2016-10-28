package model;

import java.util.ArrayList;

public class Player extends Character 
{
	//ArrayList<Integer> contains the ID's of the completed/held things
	private String playerID;
	private ArrayList<Item> unequippedItems = new ArrayList<Item>();
	private ArrayList<Integer> completedPuzzles = new ArrayList<Integer>();
	private ArrayList<Integer> defeatedMonsters = new ArrayList<Integer>();
	private ArrayList<Item> equipedItems = new ArrayList<Item>();
	private int currentRoom, previousRoom;
	private boolean isFighting;
	
	protected Player(){
		playerID = "default";
		currentRoom = 3;
	}
	protected Player(Object[] player)
	{
		super(player);
	}

	protected String addItem(Item item){
		unequippedItems.add(item);
		return "Item added to unequipped items";
	}
	protected String die(Character attacker)
	{
		String deathscription = ("You were killed by the " + attacker.name + ".\n");
		return deathscription;
		//since the die() action is automatically called upon death, 
		//this method probably also ends the game or calls the method that does
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
	
	protected String listItems()
	{
		String str ="Your inventory\n";
		
		if (unequippedItems.size() > 0)
		{
			str += "Unequpped Items\n";
			for (Item item : unequippedItems)
				str += item.getName() + "\n";
		}
		
		if (equipedItems.size() > 0)
		{
			str += "\nEquiped Items\n";
			for (Item item : equipedItems)
				str += item.getName() + "\n";
		}
		return str;
	}
		
	protected void addCompletedPuzzle(int id)
	{
		completedPuzzles.add(id);
	}
	
	protected void addDefeatedMonster(int id)
	{
		defeatedMonsters.add(id);
	}
	
	protected boolean hasItem(String itemName) 
	{
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
	
	protected boolean hasItem(int itemID) 
	{
		boolean hasItem = false;
		for (Item item : unequippedItems)
		{
			if (item.getItemID() == itemID)
				hasItem = true;
		}
//		not sure exactly why but this does not work.		
//		if (unequippedItems.contains(itemID))
//			hasItem = true;
		
		for (Item item : equipedItems)
		{
			if (item.getItemID() == itemID)
				hasItem = true;
		}
		return hasItem;
	}
	
	protected boolean hasDefeated(int monster)
	{
		return defeatedMonsters.contains(monster);
	}

	protected boolean hasCompleted(int puzzle)
	{
		return completedPuzzles.contains(puzzle);
	}
	
	protected boolean useItem(Item item) 
	{
		return item.use(this);
	}
	
	protected int getCurrentRoom()
	{
		return currentRoom;
	}

	protected void setCurrentRoom(int currentRoom)
	{
		this.previousRoom = this.currentRoom;
		this.currentRoom = currentRoom;
	}

	public int getPreviousRoom()
	{
		return previousRoom;
	}

	/**getPlayerID
	 * @return playerID: String - 
	 * 
	 * returns the playerID
	 */
	protected String getPlayerID()
	{
		return playerID;
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
	
	protected boolean getFightingStatus()
	{
		return isFighting;
	}
	
	public boolean isFighting()
	{
		return isFighting;
	}

	protected void setFightingStatus(boolean status)
	{
		isFighting = status;
	}
	
}
