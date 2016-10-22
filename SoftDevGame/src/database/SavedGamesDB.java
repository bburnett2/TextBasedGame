package database;

import java.sql.SQLException;
import java.util.ArrayList;

public class SavedGamesDB extends DatabaseManager{

	public Object[] getGameInformation() {
		Object[] gameInfo = new Object[1];
		return gameInfo;
	}

	protected void saveGamePro(Object[] gameInfo) throws SQLException{
		String playerID = (String)gameInfo[0];
		int currentRoomID = (int)gameInfo[1];
		int playerHealth = (int)gameInfo[2];
		int playerDefense =(int) gameInfo[3];
		int playerAttack = (int)gameInfo[4];
//		ArrayList<Integer> items = (ArrayList<Integer>)gameInfo[5];
//				[6] = completedPuzzle: 
//				[7] = defeatedMonsters:
//				[8] = equippedItems: ArrayList<Items> (? pass Integer)
		sqlCall = "INSERT INTO Saved_Game (PlayerID,CurrentRoom,PlayerHealth,PlayerDefenese) "
				+ "VALUES(" + "'" + playerID + "'" + "," + "'" +  currentRoomID + "'" +  "," +
				"'" + playerHealth + "'" + "," + "'" + playerDefense + "'" /* + ",'" + playerAttack + "'"*/ + ");";
		statement.executeUpdate(sqlCall);
	}

}
