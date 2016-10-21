package model;

import java.util.ArrayList;

public class Room 
{

	private int id;
	private String discription;
	private ArrayList<Item> itemList;
	private int north, south, east, west;
	private int monster, puzzle;
	
	protected Room(Object[] room)
	{
		this.id = (int)room[0];
		this.discription = (String) room[1];
		this.monster = (int)room[2];
		this.north = (int)room[3];
		this.south = (int)room[4];
		this.east = (int)room[5];
		this.west = (int)room[6];
		// room[7] is restriction
		this.itemList = buildItems((ArrayList<Integer>) room[8]);
		this.puzzle = (int)room[9];
	}
	
	private ArrayList<Item> buildItems(ArrayList<Integer> itemInts)
	{
		GameModel model = new GameModel();
		ArrayList<Item> items;
		
		int count = 0;
		while(!itemInts.isEmpty())
		{
			
			items.add(new Item(model.getItemInfo(count)));
			count++;
		}
		return items;
	}

	public String toString()
	{
		String str;
		str = discription;
		if (itemList.isEmpty())
		{
			str += "\n In this room there is:";
			for (int i = 0; i < itemList.size(); i++)
			{
				str += "\n " + itemList.get(i).getDescription();
			}
		}
		return str;	
	}

}