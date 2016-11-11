package model;

import java.util.ArrayList;

public class Monster extends Character
{

	private int id;
	private String description;
	protected ArrayList<Item> itemList;
	private boolean completesLevel;

	public Monster(Object[] monster)
	{
		super(monster);
		this.id = (int)monster[1];
		this.description = (String)monster[2];
		this.itemList = buildItems((ArrayList<Integer>)monster[6]);
		completesLevel = (boolean)monster[7];
		
		/**At the moment there is not a object from the DB with this information therefore
		not in the object array.  We can derive this from the constructor if you would
		like.
		this.deathscription = (String)monster[6];**/
	}
	
	
	@Override
	protected String die(Character attacker)
	{
		//deathscription goes here
		return ("The " + name + " died.\n");
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

	protected int getDefence()
	{
		return defense;
	}

	protected boolean completesLevel(){
		return completesLevel;
	}
}
