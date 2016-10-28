package model;

import view.Console;

public class Artifacts extends Item 
{
	

	protected Artifacts(Object[] item)
	{
		super(item);
		// TODO Auto-generated constructor stubS
	}

	protected String use(Player player) 
	{
		String retString = "";
		if(completesPuzzle.keySet().contains(true)){
			player.addCompletedPuzzle(completesPuzzle.get(true));
			retString = "You solved a puzzle";
		}
		else{
			retString = "item used";
		}
		return retString;
	}

	@Override
	protected void use(Character character)
	{
		
		//This method does nothing at this time.  This wild only be called
		//if a monster is passed in and they will not use a artifacts.
	}

}
