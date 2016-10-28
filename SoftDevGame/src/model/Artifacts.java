package model;

public class Artifacts extends Item 
{
	

	protected Artifacts(Object[] item, Player player)
	{
		super(item, player);
		// TODO Auto-generated constructor stub
	}

	protected void use() 
	{
		if(completesPuzzle.keySet().contains(true)){
			player.addCompletedPuzzle(completesPuzzle.get(true));
		}
		else{
			
		}
	}


}
