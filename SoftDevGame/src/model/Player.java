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
	
	
	protected Player()
	{
		
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
	
	protected void useItem() 
	{

	}

	protected void run() 
	{
		isFighting = false;
		//this should definitely do other things as well
	}

	@Override
	protected String die(Character monster)
	{
		String deathscription = ("You were killed by the " + monster.name + ".\n");
		return deathscription;
		//since the die() action is automatically called upon death, 
		//this method probably also ends the game or calls the method that does
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
	
	protected void setFightingStatus(boolean status)
	{
		isFighting = status;
	}
	
}
