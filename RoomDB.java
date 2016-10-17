import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RoomDB extends DatabaseManager {

	protected RoomDB(){

	}

	protected Object[] getRoomPro(int roomNum){
		String sqlCall = "SELECT * FROM Room WHERE roomID = " + roomNum;
		Object[] roomInfo = new Object[10];
		try{
			ResultSet room = super.statement.executeQuery(sqlCall);
			roomInfo[0] = room.getInt("RoomID");
			roomInfo[1] = room.getString("Description");
			roomInfo[2] = room.getInt("MonsterID");
			roomInfo[3] = room.getInt("North");
			roomInfo[4] = room.getInt("South");;
			roomInfo[5] = room.getInt("East");;
			roomInfo[6] = room.getInt("West");;
			roomInfo[7] = 0;
			roomInfo[8] = getRoomItems(roomNum);
			roomInfo[9] = getRoomPuzzles(roomNum);
		}
		catch(SQLException ex){

		}
		return roomInfo;
	}

	protected int[] getRoomItems(int roomNum) {
		String sqlCall = "SELECT ItemID FROM Room_Item WHERE roomID = " + roomNum;
		int[] itemIDS = new int[10];
		int index = 0;

		try{
			ResultSet roomItemRS = super.statement.executeQuery(sqlCall);
			while(roomItemRS.next()){
				itemIDS[index] = roomItemRS.getInt("ItemID");
				index++;
			}
		}
		catch(SQLException ex){

		}
		return itemIDS;
	}

	protected int getRoomPuzzles(int roomNum){
		String sqlCall = "SELECT PuzzleID FROM Room_Puzzle WHERE roomID = " +roomNum;
		int roomP = 0;
		try{
		ResultSet roomPuzRS = super.statement.executeQuery(sqlCall);
		roomP = roomPuzRS.getInt("PuzzleID");
		}
		catch(SQLException ex){
			
		}
		return roomP;
	}
}
