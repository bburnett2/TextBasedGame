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
		this.itemList = (ArrayList<Item>) room[7];
		this.puzzle = (int)room[9];
	}

	public String move() 
	{
		return null;
	}

	public int getDirection(String direction)
	{
		// TODO Auto-generated method stub
		return 1;
	}

}