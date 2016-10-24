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
		ArrayList<Integer> items = (ArrayList<Integer>)gameInfo[5];
		ArrayList<Integer> puzzles = (ArrayList<Integer>)gameInfo[6]; 
		ArrayList<Integer> monsters = (ArrayList<Integer>)gameInfo[7];
		ArrayList<Integer> equippedItems = (ArrayList<Integer>)gameInfo[8];

		sqlCall = "INSERT INTO Saved_Game (PlayerID,CurrentRoom,PlayerHealth,PlayerDefenese,PlayerAttack) "
				+ "VALUES(" + "'" + playerID + "'" + "," + "'" +  currentRoomID + "'" +  "," +
				"'" + playerHealth + "'" + "," + "'" + playerDefense + "'"  + ",'" + playerAttack + "'" + ");";
		statement.executeUpdate(sqlCall);
		saveItems(playerID, items);
		savePuzzles(playerID, puzzles);
		saveMonsters(playerID, monsters);
		saveEquippedItems(playerID, equippedItems);
	}

	private void saveItems(String playerID, ArrayList<Integer> items) throws SQLException{
		for(Integer integer : items){
			sqlCall = "INSERT INTO Player_Item (PlayerID, ItemID) VALUES(";
			sqlCall += "'" +  playerID + "'" + "," +  (int)integer + ",";
			sqlCall = sqlCall.substring(0, sqlCall.length() - 1);
			sqlCall += ")";
			statement.executeUpdate(sqlCall);
		}
	}
	
	private void savePuzzles(String playerID, ArrayList<Integer> puzzles) throws SQLException{
		for(Integer integer : puzzles){
			sqlCall = "INSERT INTO Player_CPuzzle (PlayerID, PuzzleID) VALUES(";
			sqlCall += "'" + playerID + "'" +  "," +  (int)integer + ",";
			sqlCall = sqlCall.substring(0, sqlCall.length() - 1);
			sqlCall += ")";
			statement.executeUpdate(sqlCall);
		}
		
	}

	private void saveMonsters(String playerID, ArrayList<Integer> monsters) throws SQLException{
		for(Integer integer : monsters){
			sqlCall = "INSERT INTO Player_DMonster (PlayerID, MonsterID) VALUES(";
			sqlCall += "'" + playerID + "'" +  "," + (int)integer + ",";
			sqlCall = sqlCall.substring(0, sqlCall.length() - 1);
			sqlCall += ")";
			statement.executeUpdate(sqlCall);
		}
	}
	
	private void saveEquippedItems(String playerID, ArrayList<Integer> equippedItems) throws SQLException{
		for(Integer integer : equippedItems){
			sqlCall = "INSERT INTO Player_Item_Equipped (PlayerID, ItemID) VALUES(";
			sqlCall += "'" + playerID + "'" +  "," + (int)integer + ",";
			sqlCall = sqlCall.substring(0, sqlCall.length() - 1);
			sqlCall += ")";
			statement.executeUpdate(sqlCall);
		}
	}
}
