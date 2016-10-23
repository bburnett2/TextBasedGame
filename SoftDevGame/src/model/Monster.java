package model;

import java.util.ArrayList;

public class Monster extends Character
{

	private int id;
	private String description;
	private int attack, health, defence;
	private ArrayList<Item> items;
	
	public Monster(Object[] monster)
	{
		this.id = (int)monster[0];
		this.description = (String)monster[1];
		this.attack = (int)monster[2];
		this.health = (int)monster[3];
		this.defence = (int)monster[4];
		this.items = (ArrayList<Item>) monster[5];
		
	}

	protected int getId()
	{
		return id;
	}

	protected String getDescription()
	{
		return description;
	}

	protected int getAttack()
	{
		return attack;
	}

	protected int getHealth()
	{
		return health;
	}

	protected int getDefence()
	{
		return defence;
	}

	protected ArrayList<Item> getItems()
	{
		return items;
	}

	protected void startFight() 
	{

	}

	@Override
	public String die()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
