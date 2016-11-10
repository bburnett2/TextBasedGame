package model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import error.GameException;

public class GameModel 
{
	public final int FIRSTROOM = 3;

	//needs to be working from DB
	private Player player;
	private Room room = null;
	private FinalPuzzle fPuzzle = new FinalPuzzle();
	private Elevator elevator;
	private view.Console console = new view.Console();
	private database.DatabaseManager DB = new database.DatabaseManager();

	public void go(ArrayList<String> command) throws GameException
	{
		int direction;

		if(player.isFighting())
		{
			throw new GameException("You must fight or run.");
		}
		//check if door has restrictions
		String restricedDoor = "";
		if(room.restrictionPuzzleID != 0 && !player.hasCompleted(room.restrictionPuzzleID)){
			restricedDoor = room.restrictedDoor;
		}
		if(hasStr(command, "north") && !restricedDoor.equals("north"))
			direction = room.getNorth();
		else if(hasStr(command,"south") && !restricedDoor.equals("south"))
			direction = room.getSouth();
		else if(hasStr(command,"east") && !restricedDoor.equals("east"))
			direction = room.getEast();
		else if(hasStr(command,"west") && !restricedDoor.equals("west"))
			direction = room.getWest();
		else if(!restricedDoor.equalsIgnoreCase("")){
			throw new GameException("Door is blocked, solve the puzzle first");
		}
		else
			throw new GameException ("Not a valid direction.");

		if(direction == 0)
			throw new GameException("\nThere is not a door that direction.\n");

		exitRoom();
		room = new Room(DB.getRoomInformation(direction), player);
		player.setCurrentRoom(room.getId());
		print(room.toString());
	}



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
			}
			else
				print("That was incorrect.");
		return completesLevel;
	}

	public String answerFinal1(String answer) throws GameException{
		return fPuzzle.answer1(answer);
	}
	
	public String answerFinal2(String answer) throws GameException{
		return fPuzzle.answer2(answer);
	}
	
	public String answerFinal3(String answer1, String answer2, String answer3) throws GameException{
		return fPuzzle.answer3(answer1, answer2, answer3);
	}
	
	public String finalAnswer(String answer){
		Map<Boolean, String> result =  fPuzzle.finalAnswer(answer);
		String retString;
		if(result.containsKey(true)){
			player.addItem(new Artifacts(getItemInfo(35)));
			player.addCompletedPuzzle(11);
			retString = result.get(true);
		}
		else{
			retString = result.get(false);
		}
		return retString;
	}
	
	public void listItems(ArrayList<String> commands)
	{
		print(player.listItems());		
	}	

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


		// player should be telling if they have the item and equipping that item for good coding.
		//		Item itemToAdd = null;
		//		for(Item item : player.getUnequippedItems())
		//		{
		//			if(commands.contains(item.getName()) || commands.contains(item.getName().toLowerCase()))
		//			{
		//				if (item.isEquippable())
		//				{
		//					//print(player.addEquippedItem(item));
		//					itemToAdd = item;
		//					break;
		//				}
		//			}
		//		}
		//		print(player.addEquippedItem(itemToAdd));
	}

	public boolean use(ArrayList<String> commands)throws GameException
	{
		boolean completesLevel = false;
		String itemName = "";
		if (commands.size() > 1)
		{
			for (int i = 1; i < commands.size(); i++)
				itemName += commands.get(i) + " ";

			itemName = itemName.trim();

			print(player.drop(itemName));
		}
	
		for(Item item : player.getUnequippedItems()){
			//if(commands.contains(item.getName()) || commands.contains(item.getName().toLowerCase())){
			if (item.getName().equalsIgnoreCase(itemName))
			{
				boolean completesPuzzle = item.use(player);
				ArrayList<String> answer = new ArrayList<>();
				answer.add("space");
				answer.add("use " + item.getName());
				completesLevel = answer(answer);
			}
		}
		return completesLevel;
	}

	public void drop(ArrayList<String> commands)throws GameException
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
			print("Please enter a item to drop");
	}

	public void run(ArrayList<String> commands)throws GameException
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

	public Map<Boolean, Boolean> attack(ArrayList<String> commands)
	{
		Map<Boolean, Boolean> youDied = new TreeMap<>();
		if (room.hasMonster())
		{
			print(room.fight());
			if (!player.isDead()){
				if(room.hasLevelCompletingMonster()){
					youDied.put(false, true);
				}
				else{
					youDied.put(false, false);
				}
			}
			else{
				youDied.put(false, false);
			}
		}
		else
			print("There is nothing to attack");

		return youDied;

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
			print(e.getMessage());
		}

		return openFloor;
	}

	public void pickUp(ArrayList<String> commands){
		Item removeItem = null;
		for(Item item : room.getItemList()){
			if(item.getName().contains(" ")){
				Scanner itemScan = new Scanner(item.getName());
				String itemName1 = itemScan.next().toLowerCase();
				String itemName2 = itemScan.next().toLowerCase();
				if(commands.contains(itemName1) && commands.contains(itemName2)){
					removeItem = item;
					print(room.player.addItem(item));
				}
			}
			if(commands.contains(item.getName().toLowerCase()) || commands.contains(item.getName()))
			{
				removeItem = item;
				print(room.player.addItem(item));
				//				break;
			}
		}
		room.removeItem(removeItem);
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

		try{
			room = new Room(DB.getRoomInformation(player.getCurrentRoom()), player);
		}
		catch(GameException ex){
			print(ex.getMessage());
		}
		print(room.toString());
	}

	public void enterElevator()
	{
		elevator = new Elevator(player);
		print(elevator.toString());

	}

	public Object[] getItemInfo(int itemNum)
	{
		return DB.getItemInformation(itemNum);
	}

	public Object[] getMonster(int monsterNumber) throws GameException
	{
		return DB.getMonsterInformation(monsterNumber);
	}

	public Object[] getPuzzle(int puzzleID)
	{
		return DB.getPuzzleInformation(puzzleID);
	}


	public ArrayList<String> getLoadableGames()
	{
		ArrayList<String> loadableGames = DB.getLoadableGames();
		return loadableGames;
	}


	public void buildNewPlayer(String name)
	{
		player = new Player(name);
	}


	public void buildPlayer(String name)
	{
		player = new Player(DB.loadGame(name));
	}

	public String saveGame(){
		return Game.save(player);
	}


	public void stats(ArrayList<String> commands)
	{
		print(player.stats());		
	}
	
	public Player getPlayer(){
		return player;
	}

}
