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
	private int currentRoom;
	private int playerHealth;
	private int playerDefense;
	private int playerAttack;
	private boolean isFighting;
	public Object setPlayerAttack;
	
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

	protected boolean hasItem(int itemID) 
	{
		return unequippedItems.contains(itemID);
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
	public String die(Character monster)
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
		this.currentRoom = currentRoom;
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

	/**getPlayerHealth
	 * @return playerHealth: int - 
	 * 
	 * returns the playerHealth
	 */
	protected int getPlayerHealth()
	{
		return playerHealth;
	}

	/**getPlayerDefense
	 * @return playerDefense: int - 
	 * 
	 * returns the playerDefense
	 */
	protected int getPlayerDefense()
	{
		return playerDefense;
	}

	/**getPlayerAttack
	 * @return playerAttack: int - 
	 * 
	 * returns the playerAttack
	 */
	
	protected int getPlayerAttack()
	{
		return playerAttack;
	}
	
	protected boolean getFightingStatus()
	{
		return isFighting;
	}
	
	protected void setFightingStatus(boolean status)
	{
		isFighting = status;
	}
	
	public boolean hasDefeated(int monster)
	{
		return defeatedMonsters.contains(monster);
	}

	public boolean hasCompleted(int puzzle)
	{
		return completedPuzzles.contains(puzzle);
	}


	public void setPlayerAttack(int newAttack)
	{
		this.attack = newAttack;
		
	}

	public void setPlayerDefense(int newDefense)
	{
		this.playerDefense = newDefense;
		
	}
	public void setPlayerHealth()
	{
		this.playerHealth = newHealth;
	}


}
