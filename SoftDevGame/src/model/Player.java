package model;

import java.util.ArrayList;

public class Player extends Character 
{
	//ArrayList<Integer> contains the ID's of the completed/held things
	private String playerID;
	private ArrayList<Integer> unequippedItems = new ArrayList<Integer>();
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
	
	@Override
	protected void takeDamage(Character monster)
	{		
		super.takeDamage(monster);
		
		if (health < 1)
		{
			//print the string returned by the die() method
			this.die(monster);
		}
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
		return unequippedItems.contains(itemName);
	}
	
	//I dont know if we are going to need this.....Michael
	protected boolean hasItem(int itemID) 
	{
		return unequippedItems.contains(itemID);
	}
	
	protected boolean hasDefeated(int monster)
	{
		return defeatedMonsters.contains(monster);
	}

	protected boolean hasCompleted(int puzzle)
	{
		return completedPuzzles.contains(puzzle);
	}
	
	protected String useItem(Item item) 
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
	protected ArrayList<Integer> getUnequippedItems()
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
