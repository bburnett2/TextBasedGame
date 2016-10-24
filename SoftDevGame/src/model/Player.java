package model;

import java.util.ArrayList;

public class Player extends Character 
{
	//ArrayList<Integer> contains the ID's of the completed/held things
	private ArrayList<Integer> unequippedItems = new ArrayList<Integer>();;
	private ArrayList<Integer> completedPuzzles = new ArrayList<Integer>();;
	private ArrayList<Integer> defeatedMonsters = new ArrayList<Integer>();
	private ArrayList<Item> equipedItems = new ArrayList<Item>();
	private int currentRoom;
	
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

}
