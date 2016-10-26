package model;

import java.util.ArrayList;

public class Room 
{

	private int id;
	private String discription;
	private ArrayList<Item> itemList;
	private int north, south, east, west;
	private Monster monster;
	protected Puzzle puzzle;
	protected Player player;

	protected Room(Object[] room, Player player)
	{
		this.id = (int)room[0];
		this.discription = (String) room[1];
		this.monster = buildMonster((int)room[2]);
		this.north = (int)room[3];
		this.south = (int)room[4];
		this.east = (int)room[5];
		this.west = (int)room[6];
		// room[7] is restriction
		this.itemList = buildItems((ArrayList<Integer>) room[8]);
		this.puzzle = buildPuzzle((int)room[9]);

		this.player = player;
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
			if (!(player.hasDefeated(monster.getId())))
				str += "\n" + monster.getDescription();
		}


		if (!(puzzle == null))
			{
			 if(!(player.hasCompleted(puzzle.getId())))
					str += "\n" + puzzle.getDescription();
			}
		return str;	
	}


	protected Puzzle buildPuzzle(int puzzleID)
	{
		GameModel model = new GameModel();
		if (puzzleID > 0)
			return new Puzzle(model.getPuzzle(puzzleID));
		else
			return null;
	}

	protected Monster buildMonster(int monsterNumber)
	{
		GameModel model = new GameModel();
		if (monsterNumber > 0)
			return new Monster(model.getMonster(monsterNumber));
		else
			return null;
	}

	protected ArrayList<Item> buildItems(ArrayList<Integer> itemInts)
	{
		GameModel model = new GameModel();
		ArrayList<Item> items = new ArrayList<Item>();

		int count = 1;
		if(!itemInts.isEmpty())
		{
			while(count <= itemInts.size())
			{
				Object[] item = model.getItemInfo(count);
				String type = (String)item[2];

				if (type.equalsIgnoreCase("Armor"))
					items.add(new Armor(item));
				else if(type.equalsIgnoreCase("Artifacts"))
					items.add(new Artifacts(item));
				else if(type.equalsIgnoreCase("Consumables"))
					items.add(new Consumables(item));
				count++;
			}
		}
		return items;
	}

	protected boolean answer(ArrayList<String> commands)
	{
		boolean correct = false;
		correct = puzzle.answer(commands.get(1));
		return correct;	
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

	protected boolean hasPuzzle()
	{
		boolean hasPuzzle = false;
		if (!(puzzle == null))
			hasPuzzle = true;
		return hasPuzzle;
	}


}