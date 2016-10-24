package model;

import java.util.ArrayList;

import error.GameException;

public class GameModel 
{
	public final int FIRSTROOM = 3;

	private Player player = new Player();;
	private Room room = null;
	
	private Elevator elevator = new Elevator();
	private view.Console console = new view.Console();
	private database.DatabaseManager DB = new database.DatabaseManager();
	
	public void go(ArrayList<String> command) throws GameException
	{
		int direction;
//		if(command.get(1).equalsIgnoreCase("north"))
		if(hasStr(command, "South"))
			direction = room.getNorth();
		else if(hasStr(command,"south"))
			direction = room.getSouth();
		else if(hasStr(command,"east"))
			direction = room.getEast();
		else if(hasStr(command,"west"))
			direction = room.getWest();
		else
			throw new GameException ("Not a valid direction");
		
		if(direction == 0)
			throw new GameException("\nThere is not a door that direction\n");
		
		exitRoom();
		room = new Room(DB.getRoomInformation(direction), player);
		player.setCurrentRoom(room.getId());
		print(room.toString());
	}
	
	private boolean hasStr(ArrayList<String> command, String str)
	{
		boolean hasStr = false;
		for(int i = 1; i < command.size(); i ++)
		{
			if(command.get(i).equalsIgnoreCase(str))
				hasStr = true;
		}
			
		return hasStr;
	}

	public void answer(ArrayList<String> commands) throws GameException
	{
		boolean correct = false;
		if (room.hasPuzzle())
			correct = room.answer(commands);
		else 
			throw new GameException("There is no puzzle to answer in this room");
		
		if (correct)
		{
			console.print("Congratulations that was correct!!!");
			player.addCompletedPuzzle(room.puzzle.getId());
		}
		else
			console.print("That was incorrect");
		
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
		room = new Room(DB.getRoomInformation(FIRSTROOM), player);
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

	public void equip(ArrayList<String> commands)
	{
		// TODO Auto-generated method stub
		
	}

}
