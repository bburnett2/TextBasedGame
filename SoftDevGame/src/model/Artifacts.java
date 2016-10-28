package model;

public class Artifacts extends Item 
{
	

	protected Artifacts(Object[] item)
	{
		super(item);
		// TODO Auto-generated constructor stubS
	}

	protected void use(Player player) 
	{
		if(completesPuzzle.keySet().contains(true)){
			player.addCompletedPuzzle(completesPuzzle.get(true));
		}
		else{
			
		}
	}

	@Override
	protected void use(Character character)
	{
		
		//This method does nothing at this time.  This wild only be called
		//if a monster is passed in and they will not use a artifacts.
	}

}
