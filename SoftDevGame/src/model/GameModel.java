package model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import error.GameException;

/**Class: GameModel.java 
   * @author Bess Burnett, Daniel Harris, Michael Holtmann, Marcus Moss
   * @version 1.0 
   * Course : ITEC 3860 Fall 2016
   * Written: Nov 14, 2016 
  	   * 
   * This class –  
   * 
   * Purpose: –  
   */
public class GameModel
{
	public final int FIRSTROOM = 3;

	// needs to be working from DB
	private Player player;
	private Room room = null;
	private FinalPuzzle fPuzzle = new FinalPuzzle();
	private Elevator elevator;
	private view.Console console = new view.Console();
	private database.DatabaseManager DB = new database.DatabaseManager();

	/**
	 * Method name: go <Direction>
	 * @param command
	 * @throws GameException
	 */
	public void go(ArrayList<String> command) throws GameException
	{
		if(command.size() < 2){
			throw new GameException("must enter a direction to go");
		}
		int direction;

		if (player.isFighting())
		{
			throw new GameException("You must attack or run.");
		}
		// check if door has restrictions
		String restricedDoor = "";
		if (room.restrictionPuzzleID != 0 && !player.hasCompleted(room.restrictionPuzzleID))
		{
			restricedDoor = room.restrictedDoor;
		}
		if (hasStr(command, "north") && !restricedDoor.equals("north"))
			direction = room.getNorth();
		else if (hasStr(command, "south") && !restricedDoor.equals("south"))
			direction = room.getSouth();
		else if (hasStr(command, "east") && !restricedDoor.equals("east"))
			direction = room.getEast();
		else if (hasStr(command, "west") && !restricedDoor.equals("west"))
			direction = room.getWest();
		else if (!restricedDoor.equalsIgnoreCase(""))
		{
			throw new GameException("Door is blocked, solve the puzzle first");
		}
		else
			throw new GameException("Not a valid direction.");

		if (direction == 0)
			throw new GameException("\nThere is not a door in that direction.\n");

		exitRoom();
		room = new Room(DB.getRoomInformation(direction), player);
		player.setCurrentRoom(room.getId());
		print(room.toString());
	}

	/**
	 * Method name: answer
	 * 
	 * used for traditional puzzles
	 * 
	 * @param commands
	 * @return
	 * @throws GameException
	 */
	public boolean answer(ArrayList<String> commands) throws GameException
	{
		boolean correct = false;
		boolean completesLevel = false;
		if (room.hasPuzzle())
			correct = room.answer(commands);
		else
			throw new GameException("There is no puzzle to answer in this room.");

		if (correct)
		{
			print("Congratulations, that was correct!!!");
			completesLevel = room.puzzle.completesLevel();
			player.addCompletedPuzzle(room.puzzle.getId());
			int itemRetruned = room.puzzle.getItemReturned();
			if(itemRetruned != 0){
				room.player.addItem(new Artifacts(getItemInfo(itemRetruned)));
			}
			room.completePuzzle();
		}
		else
			print("That was incorrect.");
		return completesLevel;
	}

	/**
	 * Method name: answerFinal1
	 * @param answer
	 * @return String
	 * @throws GameException
	 */
	public String answerFinal1(String answer) throws GameException
	{
		return fPuzzle.answer1(answer);
	}

	/**
	 * Method name: answerFinal2
	 * @param answer
	 * @return String
	 * @throws GameException
	 */
	public String answerFinal2(String answer) throws GameException
	{
		return fPuzzle.answer2(answer);
	}

	/**
	 * Method name: answerFinal3
	 * @param answer1
	 * @param answer2
	 * @param answer3
	 * @return String
	 * @throws GameException
	 */
	public String answerFinal3(String answer1, String answer2, String answer3) throws GameException
	{
		return fPuzzle.answer3(answer1, answer2, answer3);
	}

	/**
	 * Method name: finalAnswer
	 * @param answer
	 * @returnString
	 */
	public String finalAnswer(String answer)
	{
		Map<Boolean, String> result = fPuzzle.finalAnswer(answer);
		String retString;
		if (result.containsKey(true))
		{
			player.addItem(new Artifacts(getItemInfo(35)));
			player.addCompletedPuzzle(11);
			retString = result.get(true);
		}
		else
		{
			retString = result.get(false);
		}
		return retString;
	}

	/**
	 *  Method name: listItems
	 * @param commands
	 */
	public void listItems(ArrayList<String> commands)
	{
		print(player.listItems());
	}

	/**
	 * Method name: equip
	 * @param commands
	 */
	public void equip(ArrayList<String> commands)
	{
		String itemName = "";
		if (commands.size() > 1)
		{
			for (int i = 1; i < commands.size(); i++)
				itemName += commands.get(i) + " ";

			itemName = itemName.trim();

			print(player.addEquippedItem(itemName));
		}
		else
			print("You need to state the item to equip");
	}

	/**
	 * Method name: use
	 * @param commands
	 * @return boolean for end game
	 * @throws GameException
	 */
	public boolean use(ArrayList<String> commands) throws GameException
	{
		boolean completesLevel = false;
		String itemName = "";
		if (commands.size() > 1)
		{
			for (int i = 1; i < commands.size(); i++)
				itemName += commands.get(i) + " ";

			itemName = itemName.trim();
		}

		for (int i = 0; i < player.getUnequippedItems().size(); i++)
		{
			Item item = player.getUnequippedItems().get(i);
			if(item.getType().equalsIgnoreCase("consumable"))
			{
				item.use(player);
				player.removeItem(item.getItemID());
				stats();
			}
			else{
				if (item.getName().equalsIgnoreCase(itemName))
				{
					completesLevel = item.use(player);
					ArrayList<String> answer = new ArrayList<>();
					answer.add("space");
					answer.add("use " + item.getName());
					completesLevel = answer(answer);
				}
			}
		}
		return completesLevel;
	}

	/**
	 * Method name: drop
	 * @param commands
	 * @throws GameException
	 */
	public void drop(ArrayList<String> commands) throws GameException
	{
		String itemName = "";
		if (commands.size() > 1)
		{
			for (int i = 1; i < commands.size(); i++)
				itemName += commands.get(i) + " ";

			itemName = itemName.trim();

			print(player.drop(itemName));
		}
		else
			print("Please enter an item to drop");
	}

	/**
	 * Method name: run
	 * @param commands
	 * @throws GameException
	 */
	public void run(ArrayList<String> commands) throws GameException
	{
		if (player.isFighting())
		{
			exitRoom();
			room = new Room(DB.getRoomInformation(player.getPreviousRoom()), player);
			player.setCurrentRoom(room.getId());
			player.setFightingStatus(false);
			print(room.toString());
		}
		else
			print("There is nothing to run from.");
	}

	/**
	 * Method name: hasStr
	 * @param command
	 * @param str
	 * @return boolean
	 */
	private boolean hasStr(ArrayList<String> command, String str)
	{
		boolean hasStr = false;
		for (int i = 1; i < command.size(); i++)
		{
			if (command.get(i).equalsIgnoreCase(str))
				hasStr = true;
		}

		return hasStr;
	}

	/**
	 * Method name: attack
	 * @param commands
	 * @return
	 */
	public Map<Boolean, Boolean> attack(ArrayList<String> commands)
	{
		Map<Boolean, Boolean> youDied = new TreeMap<>();
		if (room.hasMonster())
		{
			print(room.fight());
			if (!player.isDead())
			{
				if (room.hasLevelCompletingMonster())
				{
					youDied.put(false, true);
				}
				else
				{
					youDied.put(false, false);
				}
			}
			else
			{
				youDied.put(true, false);
			}

		}
		else
			print("There is nothing to attack");

		return youDied;
	}

	/**
	 * Method name: pushElevator
	 * @param commands
	 * @return boolean for exiting elevator
	 */
	public boolean pushElevator(ArrayList<String> commands)
	{
		boolean openFloor = true;

		if (!(player.isFighting()))
		{
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
						elevator.setCurrentLevel(1);
						print(room.toString());
					}
					else
						console.print("This is not an open floor that you can go to at this time\n");
				}
				else if (commands.contains("2"))
				{
					openFloor = elevator.canGoToLevel(2);
					if (openFloor)
					{
						exitRoom();
						room = new Room(DB.getRoomInformation(elevator.getLevel2()), player);
						player.setCurrentRoom(room.getId());
						elevator.setCurrentLevel(2);
						print(room.toString());
					}
					else
						console.print("This is not an open floor that you can go to at this time\n");
				}
				else if (commands.contains("3"))
				{
					openFloor = elevator.canGoToLevel(3);
					if (openFloor)
					{
						exitRoom();
						room = new Room(DB.getRoomInformation(elevator.getLevel3()), player);
						player.setCurrentRoom(room.getId());
						elevator.setCurrentLevel(3);
						print(room.toString());
					}
					else
						console.print("This is not an open floor that you can go to at this time\n");
				}
				else if (commands.contains("4"))
				{
					openFloor = elevator.canGoToLevel(4);
					if (openFloor)
					{
						exitRoom();
						room = new Room(DB.getRoomInformation(elevator.getLevel4()), player);
						player.setCurrentRoom(room.getId());
						elevator.setCurrentLevel(4);
						print(room.toString());
					}
					else
						console.print("This is not an open floor that you can go to at this time\n");
				}
				else if (commands.contains("5"))
				{
					openFloor = elevator.canGoToLevel(5);
					if (openFloor)
					{
						exitRoom();
						room = new Room(DB.getRoomInformation(elevator.getLevel5()), player);
						player.setCurrentRoom(room.getId());
						elevator.setCurrentLevel(5);
						print(room.toString());
					}
					else
						console.print("This is not an open floor that you can go to at this time\n");
				}
				else if (commands.contains("6"))
				{
					openFloor = elevator.canGoToLevel(6);
					if (openFloor)
					{
						exitRoom();
						room = new Room(DB.getRoomInformation(elevator.getLevel6()), player);
						player.setCurrentRoom(room.getId());
						elevator.setCurrentLevel(6);
						print(room.toString());
					}
					else
						console.print("This is not an open floor that you can go to at this time\n");
				}
				else if (commands.contains("7"))
				{
					openFloor = elevator.canGoToLevel(7);
					if (openFloor)
					{
						exitRoom();
						room = new Room(DB.getRoomInformation(elevator.getLevel7()), player);
						player.setCurrentRoom(room.getId());
						elevator.setCurrentLevel(7);
						print(room.toString());
					}
					else
						console.print("This is not an open floor that you can go to at this time\n");
				}
				else if (commands.contains("8"))
				{
					openFloor = elevator.canGoToLevel(8);
					if (openFloor)
					{
						exitRoom();
						room = new Room(DB.getRoomInformation(elevator.getLevelFinal()), player);
						player.setCurrentRoom(room.getId());
						elevator.setCurrentLevel(8);
						print(room.toString());
					}
					else
						console.print("This is not an open floor that you can go to at this time\n");
				}
				else
					throw new GameException(
							"Not a valid command in the elevator.  You have to reenter the elevator to use again.");
			}
			catch (GameException e)
			{
				print(e.getMessage());
			}

		}
		return openFloor;
	}

	/**
	 * Method name: pickUp
	 * @param commands
	 */
	public void pickUp(ArrayList<String> commands)
	{
		Item removeItem = null;
		for (Item item : room.getItemList())
		{
			if (item.getName().contains(" "))
			{
				Scanner itemScan = new Scanner(item.getName());
				String itemName1 = itemScan.next().toLowerCase();
				String itemName2 = itemScan.next().toLowerCase();
				if (commands.contains(itemName1) && commands.contains(itemName2))
				{
					removeItem = item;
					print(room.player.addItem(item));
				}
			}
			if (commands.contains(item.getName().toLowerCase()) || commands.contains(item.getName()))
			{
				removeItem = item;
				print(room.player.addItem(item));
				// break;
			}
		}
		room.removeItem(removeItem);
	}

	/**
	 * Method name: print
	 * @param str
	 */
	public void print(String str)
	{
		console.print(str);
	}

	/**
	 * Method name: exitRoom
	 */
	public void exitRoom()
	{

		room = null;
	}

	/**
	 * Method name: firstRoom
	 */
	public void firstRoom()
	{

		try
		{
			room = new Room(DB.getRoomInformation(player.getCurrentRoom()), player);
		}
		catch (GameException ex)
		{
			print(ex.getMessage());
		}
		print(room.toString());
	}

	/**
	 * Method name: enterElevator
	 * @return
	 * @throws GameException
	 */
	public boolean enterElevator() throws GameException
	{
		if (player.isFighting())
		{
			throw new GameException("You have to attack or run before you can enter the elevator");
		}
		else
		{
			elevator = new Elevator(player);
			print(elevator.toString());
			return elevator.hasWon();
		}
	}

	/**
	 * Method name: help
	 * @param command
	 * @throws GameException
	 */
	public void help(ArrayList<String> command) throws GameException
	{
		StringBuilder help = new StringBuilder();
		help.append(
				"\n\nThis is the help menu. Some sample commands are listed below in the format of \"command <argument> (example or description)\":\n");
		help.append("\ngo <direction>			(go north)");
		help.append("\npick up <item>			(pickup clothing)");
		help.append("\nequip <item>			(equip hat)");
		help.append("\nuse <item>			(use bagel)");
		help.append("\ncreate				(used to create the C on floor 4)");
		help.append("\ndrop <item>			(drop bagel)");
		help.append("\nlist items			(lists items currently in your inventory)");
		help.append("\nplayer stats			(lists player status)");
		help.append("\nanswer <answer to puzzle> 	(answer 42)");
		help.append("\ndescription 			(restates the room's description)");
		help.append("\nattack				(used to attack a monster during a battle)");
		help.append("\nrun				(used to run from a monster during a battle)");
		help.append("\nenter 				(enter the elevator from any room)");
		help.append("\nexit				(leave the elevator, returning to your previous location)");
		help.append("\nsave				(saves the game)");
		help.append("\nquit				(ends the game)");
		print(help.toString());
	}

	/**
	 * Method name: getItemInfo
	 * @param itemNum
	 * @return Object[] for the DB
	 */
	public Object[] getItemInfo(int itemNum)
	{
		return DB.getItemInformation(itemNum);
	}

	/**
	 * Method name: getMonster
	 * @param monsterNumber
	 * @return Object[] for the DB
	 * @throws GameException
	 */
	public Object[] getMonster(int monsterNumber) throws GameException
	{
		return DB.getMonsterInformation(monsterNumber);
	}

	/**
	 * Method name: getPuzzle
	 * @param puzzleID
	 * @return Object[] forom the DB
	 */
	public Object[] getPuzzle(int puzzleID)
	{
		return DB.getPuzzleInformation(puzzleID);
	}

	/**
	 * Method name: getLoadableGames
	 * @return String array list
	 */
	public ArrayList<String> getLoadableGames()
	{
		ArrayList<String> loadableGames = DB.getLoadableGames();
		return loadableGames;
	}

	/**
	 * Method name: buildNewPlayer
	 * @param name
	 */
	public void buildNewPlayer(String name)
	{
		player = new Player(name);
	}

	/**
	 * Method name: buildPlayer
	 * @param name
	 */
	public void buildPlayer(String name)
	{
		player = new Player(DB.loadGame(name));
	}

	/**
	 * Method name: saveGame
	 * @return String
	 */
	public String saveGame()
	{
		return Game.save(player);
	}

	/**
	 * Method name: stats
	 */
	public void stats()
	{
		print(player.stats());
	}

	/**
	 * Method name: getPlayer
	 * @return Player
	 */
	public Player getPlayer()
	{
		return player;
	}

	/**
	 * Method name: getRoom
	 * @return Room
	 */
	public Room getRoom()
	{
		return room;
	}

	/**
	 * Method name: description
	 */
	public void description()
	{
		print(room.toString());
	}

	/**
	 * Method name: create
	 * @return boolean
	 */
	public boolean create()
	{
		boolean itemCreated = false;
		if (player.hasItem(21) && player.hasItem(22))
		{
			player.addItem(new Artifacts(getItemInfo(24)));
			player.removeItem(21);
			player.removeItem(22);
			itemCreated = true;
			player.addCompletedPuzzle(5);
		}
		return itemCreated;
	}
}
