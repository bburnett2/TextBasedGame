package model;

import java.util.ArrayList;

import error.GameException;

public class GameModel 
{
	public int FIRSTROOM = 1;

	private Player player = null;
	private Room room = null;
	
	private Elevator elevator = new Elevator();
	private view.Console console = new view.Console();
	private database.DatabaseManager DB = new database.DatabaseManager();
	
	public void go(ArrayList<String> command) throws GameException
	{
		int direction;
		if(command.get(1).equalsIgnoreCase("north"))
			direction = room.getNorth();
		else if(command.get(1).equalsIgnoreCase("south"))
			direction = room.getSouth();
		else if(command.get(1).equalsIgnoreCase("east"))
			direction = room.getEast();
		else if(command.get(1).equalsIgnoreCase("west"))
			direction = room.getWest();
		else
			throw new GameException ("Not a valid direction");
		
		if(direction == 0)
			throw new GameException("Not a valid direction");
		
		exitRoom();
		room = new Room(DB.getRoomInformation(direction));
		print(room.toString());
	}
	
	public void print(String str)
	{
		console.print(str);
	}
	
	public void exitRoom()
	{

		room = null;
	}

	public void firstRoom()
	{
		room = new Room(DB.getRoomInformation(FIRSTROOM));
		print(room.toString());
	}

	public Object[] getItemInfo(int itemNum)
	{
		return DB.getItemInformation(itemNum);
	}

	public Object[] getMonster(int monsterNumber)
	{
		return DB.getMonsterInformation(monsterNumber);
	}

	public Object[] getPuzzle(int puzzleID)
	{
		return DB.getPuzzleInformation(puzzleID);
	}

}
