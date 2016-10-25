package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RoomDB extends DatabaseManager {

	protected RoomDB(){

	}

	protected Object[] getRoomInformationPro(int roomNum){
		sqlCall = "SELECT * FROM Room WHERE roomID = " + roomNum;
		Object[] roomInfo = new Object[10];
		try{
			resultSet = super.statement.executeQuery(sqlCall);
			roomInfo[0] = resultSet.getInt("RoomID");
			roomInfo[1] = resultSet.getString("Description");
			roomInfo[2] = resultSet.getInt("MonsterID");
			roomInfo[3] = resultSet.getInt("North");
			roomInfo[4] = resultSet.getInt("South");;
			roomInfo[5] = resultSet.getInt("East");;
			roomInfo[6] = resultSet.getInt("West");;
			roomInfo[7] = 0;
			roomInfo[8] = getRoomItemInts(roomNum);
			roomInfo[9] = getRoomPuzzles(roomNum);
		}
		catch(SQLException ex){

		}
		return roomInfo;
	}

	protected ArrayList<Integer> getRoomItemInts(int roomNum) {
		sqlCall = "SELECT ItemID FROM Room_Item WHERE roomID = " + roomNum;
		ArrayList<Integer> itemIDS = new ArrayList<Integer>();
		try{
			resultSet = super.statement.executeQuery(sqlCall);
			while(resultSet.next()){
				itemIDS.add(resultSet.getInt("ItemID"));
			}
		}
		catch(SQLException ex){

		}
		return itemIDS;
	}

	protected int getRoomPuzzles(int roomNum){
		sqlCall = "SELECT PuzzleID FROM Room_Puzzle WHERE roomID = " +roomNum;
		int roomP = 0;
		try{
		resultSet = super.statement.executeQuery(sqlCall);
		roomP = resultSet.getInt("PuzzleID");
		}
		catch(SQLException ex){
			
		}
		return roomP;
	}
}
