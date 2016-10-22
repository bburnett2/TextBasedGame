package database;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;

public class DatabaseManager {
	private String sDriverName = "org.sqlite.JDBC";
	private String sDBName = "textGame.db";
	private String sJDBC = "jdbc:sqlite";
	private String sDBURL = sJDBC + ":" + sDBName;
	protected Connection connection;
	protected int timeOut = 30;
	protected Statement statement;
	protected String sqlCall;
	protected ResultSet resultSet;

	public DatabaseManager(){
		try{
			String sDriverName = "org.sqlite.JDBC";
			Class.forName(sDriverName);
			connection = DriverManager.getConnection(sDBURL);
			statement = connection.createStatement();
			statement.setQueryTimeout(timeOut);
		}
		catch(SQLException ex){
			System.out.println("conection not made");
		}
		catch (ClassNotFoundException e) {
			System.out.println("class not found");
		}
	}

	public Object[] getRoomInformation(int roomNum){
		RoomDB roomDB = new RoomDB();
		return roomDB.getRoomInformationPro(roomNum);
	}

	public Object[] getItemInformation(int itemNum){
		ItemDB itemDB = new ItemDB();
		return itemDB.getItemInformationPro(itemNum);
	}
	
	public Object[] getMonsterInformation(int monsterNum){
		MonsterDB monsterDB = new MonsterDB();
		return monsterDB.getMonsterInformationPro(monsterNum);
	}
	
	public Object[] getPuzzleInformation(int puzzleNum){
		PuzzleDB puzzleDB = new PuzzleDB();
		return puzzleDB.getPuzzleInformationPro(puzzleNum);
	}
	
	public void saveGame(Object[] gameInfo) throws SQLException{
		SavedGamesDB savedGamesDB = new SavedGamesDB();
		savedGamesDB.saveGamePro(gameInfo);
	}

//	public static void main(String[] args) throws ClassNotFoundException{
//		DatabaseManager dbManage = new DatabaseManager();
//		dbManage = new SavedGamesDB();
//		Object[] tester = new Object[5];
//		tester[0] = "testPlayer1";
//		tester[1] = 2;
//		tester[2] = 4;
//		tester[3] = 43;
//		tester[4] = 42;
//		
//		try{
//			dbManage.saveGame(tester);
//			System.out.println("ok");
//		}
//		catch(SQLException ex){
//			System.out.println(ex.getMessage());
//		}

//		try{
//			dbManage = new RoomDB();
//		}
//		catch(Exception ex){
//			System.out.println(ex.getMessage());
//		}
//		Object[] room = dbManage.getRoomInformation(1);
//		for(int i = 0; i < room.length; i++){
//			if(room[i] != null){
//				System.out.println(room[i].toString());
//			}
//			else{
//				System.out.println("null");
//			}
//		}
//		ArrayList<Integer> items = (ArrayList<Integer>) room[8];
//		int i = 0;
//		for(Integer integer : items){
//				Object[] item = dbManage.getItemInformation(integer);
//				for(int j = 0; j < item.length; j++){
//					System.out.println(item[j]);
//			}
//		}
//
//	}
}
