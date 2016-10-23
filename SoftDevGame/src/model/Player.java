package model;

import java.util.ArrayList;

public class Player extends Character 
{

	private boolean[] completedPuzzles = new boolean[11];
	private boolean[] defeatedMonsters = new boolean[8];
	private ArrayList<Item> equipedItems = new ArrayList<Item>();
	private int currentRoom;
	
	protected Player()
	{
		for (int i = 0; i < completedPuzzles.length; i++)
			completedPuzzles[i] = false;
		for (int i = 0; i < defeatedMonsters.length; i ++)
			defeatedMonsters[i] = false;
	}
	
	protected void addCompletedPuzzle(int id)
	{
		completedPuzzles[id] = true;
	}
	

	protected void hasItem() 
	{

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
