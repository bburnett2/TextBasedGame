package model;

import java.util.ArrayList;

public class Monster extends Character
{

	private int id;
	private String discription;
	private int attack, health, defence;
	private ArrayList<Item> items;
	
	public Monster(Object[] monster)
	{
		this.id = (int)monster[0];
		this.discription = (String)monster[1];
		this.attack = (int)monster[2];
		this.health = (int)monster[3];
		this.defence = (int)monster[4];
		this.items = (ArrayList<Item>) monster[5];
		
	}

	public int getId()
	{
		return id;
	}

	public String getDiscription()
	{
		return discription;
	}

	public int getAttack()
	{
		return attack;
	}

	public int getHealth()
	{
		return health;
	}

	public int getDefence()
	{
		return defence;
	}

	public ArrayList<Item> getItems()
	{
		return items;
	}

	public void startFight() 
	{

	}

	@Override
	public String die()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
