package model;

import view.Console;

public class Artifacts extends Item 
{
	

	protected Artifacts(Object[] item)
	{
		super(item);
		// TODO Auto-generated constructor stubS
	}

	protected boolean use(Player player) 
	{
		boolean completes = false;
		if(completesPuzzle.keySet().contains(true))
		{
			completes = true;
		}
		return completes;
	}

	@Override
	protected void use(Character character)
	{
		
		//This method does nothing at this time.  This wild only be called
		//if a monster is passed in and they will not use a artifacts.
	}

	@Override
	protected boolean isEquippable()
	{
		return false;
	}

}
