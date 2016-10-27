package model;

import java.util.ArrayList;

import error.GameException;

public class GameModel 
{
	public final int FIRSTROOM = 3;

	private Player player = new Player();;
	private Room room = null;

	private Elevator elevator = new Elevator(player);
	private view.Console console = new view.Console();
	private database.DatabaseManager DB = new database.DatabaseManager();

	public void go(ArrayList<String> command) throws GameException
	{
		int direction;

		if(hasStr(command, "north"))
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

	public void equip(ArrayList<String> commands)
	{
		// TODO Auto-generated method stub

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


	public void enterElevator()
	{
		console.print(elevator.toString());

	}


	public boolean pushElevator(ArrayList<String> commands)
	{
		boolean openFloor = true;


		try
		{
			if (commands.contains("1"))
			{
				openFloor = elevator.canGoToLevel(1);
				if (openFloor)
				{
					exitRoom();
					room = new Room(DB.getRoomInformation(elevator.getLevel1()), player);
					player.setCurrentRoom(room.getId());
					print(room.toString());
				}
				else
					console.print("Not a open floor that you can go to at this time\n");
			}
			else if (commands.contains("2"))
			{
				openFloor = elevator.canGoToLevel(2);
				if (openFloor)
				{
					exitRoom();
					room = new Room(DB.getRoomInformation(elevator.getLevel2()), player);
					player.setCurrentRoom(room.getId());
					print(room.toString());
				}
				else
					console.print("Not a open floor that you can go to at this time\n");
			}
			else if (commands.contains("3"))
			{
				openFloor = elevator.canGoToLevel(3);
				if (openFloor)
				{
					exitRoom();
					room = new Room(DB.getRoomInformation(elevator.getLevel3()), player);
					player.setCurrentRoom(room.getId());
					print(room.toString());
				}
				else
					console.print("Not a open floor that you can go to at this time\n");
			}
			else if (commands.contains("4"))
			{
				openFloor = elevator.canGoToLevel(4);
				if (openFloor)
				{
					exitRoom();
					room = new Room(DB.getRoomInformation(elevator.getLevel4()), player);
					player.setCurrentRoom(room.getId());
					print(room.toString());
				}
				else
					console.print("Not a open floor that you can go to at this time\n");
			}
			else if (commands.contains("5"))
			{
				openFloor = elevator.canGoToLevel(5);
				if (openFloor)
				{
					exitRoom();
					room = new Room(DB.getRoomInformation(elevator.getLevel5()), player);
					player.setCurrentRoom(room.getId());
					print(room.toString());
				}
				else
					console.print("Not a open floor that you can go to at this time\n");
			}
			else if (commands.contains("6"))
			{
				openFloor = elevator.canGoToLevel(6);
				if (openFloor)
				{
					exitRoom();
					room = new Room(DB.getRoomInformation(elevator.getLevel1()), player);
					player.setCurrentRoom(room.getId());
					print(room.toString());
				}
				else
					console.print("Not a open floor that you can go to at this time\n");
			}
			else if (commands.contains("7"))
			{
				openFloor = elevator.canGoToLevel(7);
				if (openFloor)
				{
					exitRoom();
					room = new Room(DB.getRoomInformation(elevator.getLevel7()), player);
					player.setCurrentRoom(room.getId());
					print(room.toString());
				}
				else
					console.print("Not a open floor that you can go to at this time\n");
			}
			else if (commands.contains("8"))
			{
				openFloor = elevator.canGoToLevel(8);
				if (openFloor)
				{
					exitRoom();
					room = new Room(DB.getRoomInformation(elevator.getLevelFinal()), player);
					player.setCurrentRoom(room.getId());
					print(room.toString());
				}
				else
					console.print("Not a open floor that you can go to at this time\n");
			}
			else
				throw new GameException("Not a valid command in the elevator");
		}
		catch (GameException e)
		{
			console.print(e.getMessage());
		}


		return openFloor;
	}

}
