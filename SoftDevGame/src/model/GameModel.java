package model;

public class GameModel 
{

	private Player player = null;
	private Room room = null;
	
	private Elevator elevator = new Elevator();
	private view.Console console = new view.Console();
	private database.DatabaseManager DB = new database.DatabaseManager();
	
	public void go(String direction)
	{
//		DB.getRoom(room.getDirection(direction));
	}
	
	public void print(String str)
	{
		console.print(str);
	}
	
	public void exitRoom()
	{

		room = null;
	}

	public void firstRoom()
	{
		room = new Room(DB.getRoomInformation(1));
		print(room.toString());
	}

	public Object[] getItemInfo(int itemNum)
	{
		return DB.getItemInformation(itemNum);
	}
}
