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
	
	protected Player()
	{
		
	}
		
	protected void addCompletedPuzzle(int id)
	{
		completedPuzzles.add(id);
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

	}

	@Override
	public String die()
	{
		// TODO Auto-generated method stub
		return null;
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
	
	@Override
	protected void attack()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	void takeDamage()
	{
		// TODO Auto-generated method stub
		
	}

}
