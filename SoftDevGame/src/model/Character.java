package model;

import java.util.ArrayList;

//Test Comment

public abstract class Character 
{

	private String name;
	private int health;
	private int defence;
	private int attack;
	private ArrayList itemList;

	abstract protected void attack();

	abstract void takeDamage();

	abstract public String die();
}
