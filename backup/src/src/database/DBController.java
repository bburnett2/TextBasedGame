package src.database;

public class DBController 
{
	private ItemDB item = new ItemDB();
	private MonsterDB monster = new MonsterDB();
	private PlayerDB player = new PlayerDB();
	private PuzzleDB puzzle = new PuzzleDB();
	private RoomDB room = new RoomDB();
	private SavedGamesDB games = new SavedGamesDB();

	public String getTest() 
	{
		return "this is from the DB";
	}

}
