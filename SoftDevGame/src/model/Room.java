package model;

import java.util.ArrayList;
import java.util.Scanner;

import error.GameException;

/**Class: Room.java 
   * @author Bess Burnett, Daniel Harris, Michael Holtmann, Marcus Moss
   * @version 1.0 
   * Course : ITEC 3860 Fall 2016
   * Written: Nov 14, 2016 
  	   * 
   * This class –  
   * 
   * Purpose: –  
   */
public class Room 
{

	private int id;
	private String discription;
	private ArrayList<Item> itemList;
	private int north, south, east, west;
	private Monster monster;
	protected Puzzle puzzle;
	protected Player player;
	protected int restrictionPuzzleID;
	protected String restrictedDoor;

	/**Room
	 * @param room
	 * @param player
	 * @throws GameException
	 * agr constructor
	 *
	 * creates a Room object with 
	 */
	protected Room(Object[] room, Player player)throws GameException
	{
		this.player = player;
		this.id = (int)room[0];
		this.discription = (String) room[1];
		this.monster = buildMonster((int)room[2]);
		this.north = (int)room[3];
		this.south = (int)room[4];
		this.east = (int)room[5];
		this.west = (int)room[6];
		String restrictSt = (String)room[7];
		Scanner restrict = new Scanner(restrictSt);
		restrict.useDelimiter(",");
		this.restrictionPuzzleID = restrict.nextInt();
		this.restrictedDoor = restrict.next();
		this.itemList = buildItems((ArrayList<Integer>) room[8]);
		this.puzzle = buildPuzzle((int)room[9]);
		restrict.close();
	}

	//toString is required to be public
	public String toString()
	{
		String str;
		str = discription;
		if (!itemList.isEmpty())
		{
			str += "\n The room has the folowing Items:";
			for (int i = 0; i < itemList.size(); i++)
			{
				str += "\n " + itemList.get(i).getName();
				str += "\n\t " + itemList.get(i).getDescription();
			}
		}

		if (!(monster == null))
		{
			str += "\n\n" + monster.getDescription();
			str += startFight();
		}


		if (!(puzzle == null))
		{
			if(puzzle.getId() != 11)
				str += "\n\n" + puzzle.getDescription();
		}
		return str;	
	}


	/**
	 * Method name: buildPuzzle
	 * @param puzzleID
	 * @return
	 */
	protected Puzzle buildPuzzle(int puzzleID)
	{
		GameModel model = new GameModel();
		if (puzzleID > 0 && !(player.hasCompleted(puzzleID)))
			return new Puzzle(model.getPuzzle(puzzleID));
		else
			return null;
	}

	/**
	 * Method name: buildMonster
	 * @param monsterNumber
	 * @return
	 * @throws GameException
	 */
	protected Monster buildMonster(int monsterNumber) throws GameException
	{
		GameModel model = new GameModel();
		if (monsterNumber > 0  && !(player.hasDefeated(monsterNumber)))
			return new Monster(model.getMonster(monsterNumber));
		else
			return null;
	}

	/**
	 * Method name: buildItems
	 * @param itemInts
	 * @return
	 */
	protected ArrayList<Item> buildItems(ArrayList<Integer> itemInts)
	{
		GameModel model = new GameModel();
		ArrayList<Item> items = new ArrayList<Item>();

		int count = 0;
		if(!itemInts.isEmpty())
		{
			while(count < itemInts.size())
			{
				if (!(player.hasItem(itemInts.get(count))))
				{
					Object[] item = model.getItemInfo(itemInts.get(count));
					String type = (String)item[2];

					if (type.equalsIgnoreCase("Armor"))
						items.add(new Armor(item));
					else if(type.equalsIgnoreCase("Artifacts"))
						items.add(new Artifacts(item));
					else if(type.equalsIgnoreCase("Consumable"))
						items.add(new Consumables(item));
				}
				count++;
			}
		}
		return items;
	}

	/**
	 * Method name: startFight
	 * @return
	 */
	protected String startFight()
	{
		player.setFightingStatus(true);
		return("\n\nYou are fighting the " + monster.name + ".\n");
		//maybe a generic "the monster attacks you!!" is here as well
	}


	//this is intended to be the method called when the player enters the attack command
	//it contains the logic for one round attacks between the player and a monster
	/**
	 * Method name: fight
	 * @return
	 */
	protected String fight()
	{
		StringBuilder str = new StringBuilder();
		str.append(player.attack(monster));
		if(monster.isDead())
		{
			player.setFightingStatus(false);
			str.append(monster.die(player));
			player.addDefeatedMonster(monster.getId());
			if (monster.itemList.size() > 0)
			{
				//method which adds the defeated monster's items to the player
				str.append("Upon death, " + monster.name + " dropped and gave you ");
				if (monster.itemList.size() == 1)
				{
					player.addItem(monster.itemList.get(0));
					str.append(monster.itemList.get(0).getName());
				}
				else
				{
					int last = 0;
					for (int i = 0; i < monster.itemList.size()-1; i++)
					{
						player.addItem(monster.itemList.get(i));
						str.append(monster.itemList.get(i).getName() + ", ");
						last = i + 1;
					}
					str.append("and " + monster.itemList.get(last).getName() + ".\n");
					player.addItem(monster.itemList.get(last));
				}
			}
		}
		else
		{
			str.append(monster.attack(player));
			if(player.isDead())
			{
				str.append(player.die(monster));
			}
			else
			{
				str.append("\n\nYou are fighting the " + monster.name + ".\n");
			}
		}
		return str.toString();
	}

	/**getItemList
	 * @return itemList: ArrayList<Item> - 
	 * 
	 * returns the itemList
	 */
	protected ArrayList<Item> getItemList()
	{
		return itemList;
	}

	/**
	 * Method name: answer
	 * @param commands
	 * @return
	 */
	protected boolean answer(ArrayList<String> commands)
	{
		boolean correct = false;
		String answerSt = "";
		for(int i = 1; i < commands.size(); i++)
		{
			answerSt += commands.get(i) + " ";
		}
		answerSt = answerSt.trim();
		correct = puzzle.answer(answerSt);
		return correct;	
	}

	public Monster getMonster()
	{
		return monster;
	}

	protected int getNorth()
	{
		return north;
	}

	protected int getSouth()
	{
		return south;
	}

	protected int getEast()
	{
		return east;
	}

	protected int getWest()
	{
		return west;
	}

	protected int getId()
	{
		return id;
	}

	/**
	 * Method name: hasPuzzle
	 * @return
	 */
	protected boolean hasPuzzle()
	{
		boolean hasPuzzle = false;
		if (!(puzzle == null))
			hasPuzzle = true;
		return hasPuzzle;
	}

	/**
	 * Method name: removeItem
	 * @param item
	 */
	protected void removeItem(Item item)
	{
		itemList.remove(item);
	}

	/**
	 * Method name: hasMonster
	 * @return
	 */
	public boolean hasMonster()
	{
		boolean hasMonster = true;
		if (monster == null)
			hasMonster = false;
		return hasMonster;
	}

	/**
	 * Method name: hasFinalPuzzle
	 * @return
	 */
	public boolean hasFinalPuzzle()
	{
		boolean hasFinal = false;
		if(id == 46)
		{
			hasFinal = true;
		}
		return hasFinal;
	}

	/**
	 * Method name: hasLevelCompletingMonster
	 * @return
	 */
	public boolean hasLevelCompletingMonster()
	{
		if(monster.completesLevel())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Method name: completePuzzle
	 */
	public void completePuzzle()
	{
		puzzle = null;		
	}

	/**
	 * Method name: monsterKilled
	 */
	public void monsterKilled()
	{
		monster = null;
		
	}

}