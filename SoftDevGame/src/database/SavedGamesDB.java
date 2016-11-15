package database;

import java.sql.SQLException;
import java.util.ArrayList;

import error.GameException;

public class SavedGamesDB extends DatabaseManager{

	public Object[] getGameInformation() {
		Object[] gameInfo = new Object[1];
		return gameInfo;
	}

	protected String saveGamePro(Object[] gameInfo){
		String retString ="";
		String playerID = (String)gameInfo[0];
		int currentRoomID = (int)gameInfo[1];
		int playerHealth = (int)gameInfo[2];
		int playerDefense =(int) gameInfo[3];
		int playerAttack = (int)gameInfo[4];
		ArrayList<Integer> items = (ArrayList<Integer>)gameInfo[5];
		ArrayList<Integer> puzzles = (ArrayList<Integer>)gameInfo[6]; 
		ArrayList<Integer> monsters = (ArrayList<Integer>)gameInfo[7];
		ArrayList<Integer> equippedItems = (ArrayList<Integer>)gameInfo[8];
		int maxHealth = (int)gameInfo[9];

		sqlCall = "INSERT INTO Saved_Game (PlayerID,CurrentRoom,PlayerHealth,PlayerDefenese,PlayerAttack,MaxHealth) "
				+ "VALUES(" + "'" + playerID + "'" + "," + "'" +  currentRoomID + "'" +  "," +
				"'" + playerHealth + "'" + "," + "'" + playerDefense + "'"  + ",'" + playerAttack + "'" + ",'" +
				maxHealth + "');";
		try{
			statement.executeUpdate(sqlCall);
		}
		catch(SQLException ex){
			ex.getMessage();
			retString = updateSavedGame(playerID, currentRoomID, playerHealth, playerDefense, playerAttack, items,
					puzzles, monsters, equippedItems, maxHealth);
		}
		try{
			saveItems(playerID, items);
			savePuzzles(playerID, puzzles);
			saveMonsters(playerID, monsters);
			saveEquippedItems(playerID, equippedItems);
		}
		catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
		finally {
			try{
				statement.close();
			}
			catch(SQLException ex){

			}
		}
		return retString;
	}

	private String updateSavedGame(String playerID, int currentRoomID, int playerHealth, int playerDefense,
			int playerAttack, ArrayList<Integer> items, ArrayList<Integer> puzzles, 
			ArrayList<Integer> monsters, ArrayList<Integer> equippedItems, int maxHealth){

		sqlCall = "UPDATE Saved_Game SET CurrentRoom = " + currentRoomID + ", PlayerHealth = " + playerHealth + 
				", PlayerDefenese = " + playerDefense + ", PlayerAttack = " + playerAttack + ", MaxHealth = "
				+ "'" + maxHealth + "' WHERE PlayerID = '" + playerID + "';";
		try{
			statement = connection.createStatement();
			statement.executeUpdate(sqlCall);
			saveItems(playerID, items);
			savePuzzles(playerID, puzzles);
			saveMonsters(playerID, monsters);
			saveEquippedItems(playerID, equippedItems);
		}
		catch(SQLException ex){
			System.out.println(ex.getMessage() + "update");
		}
		finally{
			try{
				statement.close();
			}
			catch (SQLException e){
				e.printStackTrace();
			}
		}
		return "Game Saved";
	}

	private void saveItems(String playerID, ArrayList<Integer> items){
		for(Integer integer : items){
			sqlCall = "INSERT INTO Player_Item (PlayerID, ItemID) VALUES(";
			sqlCall += "'" +  playerID + "'" + "," +  (int)integer + ",";
			sqlCall = sqlCall.substring(0, sqlCall.length() - 1);
			sqlCall += ")";
			try{
				statement.executeUpdate(sqlCall);
			}
			catch(SQLException ex){

			}
		}
		try{
			statement.close();
		}
		catch (SQLException e){

		}
		finally{
			try{
				statement.close();
			}
			catch (SQLException e){
				e.printStackTrace();
			}
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
		try{
			statement.close();
		}
		catch(SQLException ex){

		}
		finally{
			try{
				statement.close();
			}
			catch (SQLException e){
				e.printStackTrace();
			}
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
		try{
			statement.close();
		}
		catch(SQLException ex){

		}
		finally{
			try{
				statement.close();
			}
			catch (SQLException e){
				e.printStackTrace();
			}
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
		try{
			statement.close();
		}
		catch(SQLException ex){

		}
		finally{
			try{
				statement.close();
			}
			catch (SQLException e){
				e.printStackTrace();
			}
		}
	}

	protected Object[] loadGamePro(String PlayerID){
		Object[] gameInfo = new Object[10];
		sqlCall = "SELECT * FROM Saved_Game WHERE PlayerID = '" + PlayerID + "'";
		try{
			resultSet = statement.executeQuery(sqlCall);
			gameInfo[0] = resultSet.getString("PlayerID");
			gameInfo[1] = resultSet.getInt("CurrentRoom");
			gameInfo[3] = resultSet.getInt("PlayerAttack");
			gameInfo[4] = resultSet.getInt("PlayerHealth");
			gameInfo[5] = resultSet.getInt("PlayerDefenese");
			gameInfo[9] = resultSet.getInt("MaxHealth");
			try{
				statement.close();
			}
			catch (SQLException e){

			}
			gameInfo[2] = loadCompletedPuzzles(PlayerID);
			gameInfo[6] = loadItems(PlayerID);
			gameInfo[7] = loadDefeatedMonsters(PlayerID);
			gameInfo[8] = loadEquippedItems(PlayerID);
		}
		catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
		return gameInfo;
	}

	private ArrayList<Integer> loadItems(String playerID){
		ArrayList<Integer> items = new ArrayList<>();
		sqlCall = "SELECT * FROM Player_Item WHERE PlayerID = '" + playerID + "'";
		try{
			resultSet = statement.executeQuery(sqlCall);
			while(resultSet.next()){
				Integer itemID = resultSet.getInt("ItemID");
				if(!items.contains(itemID)){
					items.add(itemID);
				}
			}
			statement.close();
		}
		catch(SQLException ex){

		}
		finally {
			try{
				statement.close();
			}
			catch(SQLException ex){

			}
		}
		return items;
	}

	private ArrayList<Integer> loadCompletedPuzzles(String playerID){
		ArrayList<Integer> puzzles = new ArrayList<>();
		sqlCall = "SELECT * FROM Player_CPuzzle WHERE PlayerID = '" + playerID + "'";
		try{
			resultSet = statement.executeQuery(sqlCall);
			while(resultSet.next()){
				puzzles.add(resultSet.getInt("PuzzleID"));
			}
		}
		catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
		finally {
			try{
				statement.close();
			}
			catch(SQLException ex){

			}
		}
		return puzzles;
	}

	private ArrayList<Integer> loadEquippedItems(String playerID){
		ArrayList<Integer> equippedItems = new ArrayList<>();
		sqlCall = "SELECT * FROM Player_Item_Equipped WHERE PlayerID = '" + playerID + "'";
		try{
			resultSet = statement.executeQuery(sqlCall);
			while(resultSet.next()){
				Integer itemID = resultSet.getInt("ItemID");
				if(!equippedItems.contains(itemID)){
					equippedItems.add(itemID);
				}
			}
		}
		catch(SQLException ex){

		}
		finally {
			try{
				statement.close();
			}
			catch(SQLException ex){

			}
		}
		return equippedItems;
	}

	private ArrayList<Integer> loadDefeatedMonsters(String playerID){
		ArrayList<Integer> monsters = new ArrayList<>();
		sqlCall = "SELECT * FROM Player_DMonster WHERE PlayerID = '" + playerID+ "'";
		try{
			resultSet = statement.executeQuery(sqlCall);
			while(resultSet.next()){
				monsters.add(resultSet.getInt("MonsterID"));
			}
		}
		catch(SQLException ex){

		}
		finally {
			try{
				statement.close();
			}
			catch(SQLException ex){

			}
		}
		return monsters;
	}

	protected ArrayList<String> getLoadableGamesPro(){
		ArrayList<String> savedGames = new ArrayList<>();
		sqlCall = "SELECT PlayerID FROM Saved_Game";
		try{
			resultSet = statement.executeQuery(sqlCall);
			while(resultSet.next()){
				savedGames.add(resultSet.getString("PlayerID"));
			}
		} catch(SQLException ex){

		}
		finally {
			try{
				statement.close();
			}
			catch(SQLException ex){

			}
		}
		return savedGames;
	}

	private void deleteAll() throws SQLException{
		sqlCall = "DELETE FROM Player_Item";
		statement.executeUpdate(sqlCall);
		statement.close();
		sqlCall = "DELETE FROM Player_Item_Equipped";
		statement.executeUpdate(sqlCall);
		statement.close();
		sqlCall = "DELETE FROM Player_CPuzzle";
		statement.executeUpdate(sqlCall);
		statement.close();
		sqlCall = "DELETE FROM Player_DMonster";
		statement.executeUpdate(sqlCall);
		statement.close();
		sqlCall = "DELETE FROM Saved_Game";
		statement.executeUpdate(sqlCall);
		statement.close();
	}

//		public static void main(String[] args){
//			SavedGamesDB sg = new SavedGamesDB();
//			try
//			{
//				sg.deleteAll();
//			}
//			catch (SQLException ex)
//			{
//				// TODO Auto-generated catch block
//				ex.printStackTrace();
//			}
//		}
}
