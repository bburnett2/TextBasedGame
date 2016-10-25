package model;

public class GameModel 
{
	private Armor armor = null;
	private Artifacts art = null;
	private Monster monster = null;
	private Player player = null;
	private Puzzle puzzle = null;
	private Room room = null;
	
	private Elevator elevator = new Elevator();
	private view.Console console = new view.Console();
	private database.DBController DB = new database.DBController();
	
	public void go(String direction)
	{
//		DB.getRoom(room.getDirection(direction));
	}
	
	public void print(String str)
	{
		console.print(DB.getTest());
	}
	
	private void exitRoom()
	{
		armor = null;
		art = null;
		monster = null;
		puzzle = null;
		room = null;
	}
}
