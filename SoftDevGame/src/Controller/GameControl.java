package Controller;
//get commit
public class GameControl 
{
	model.GameModel model = new model.GameModel();
	
	private String commands;
	
	public static void main(String[] args)
	{
		GameControl run = new GameControl();
		run.startGame();
	}
	
	private void startGame()
	{
		model.firstRoom();		
	}

	private void print(String str)
	{
		model.print(str);
	}

	public void read() 
	{

	}

	public void parsString() 
	{

	}

}
