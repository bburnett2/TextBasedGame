package src.database;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;

public class Database {
	private String sDriverName = "org.sqlite.JDBC";
	private String sDBName = "textGame.db";
	private String sJDBC = "jdbc:sqlite";
	private String sDBURL = sJDBC + ":" + sDBName;
	protected Connection connection;
	protected int timeOut = 30;
	protected Statement statement;

	protected Database(){
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
			System.out.println("class not foun");
		}
	}

	public Object[] getRoom(int roomNum){
		RoomDB roomDB = new RoomDB();
		return roomDB.getRoomPro(roomNum);
	}

	public Object[] getItem(int itemNum){
		ItemDB itemDB = new ItemDB();
		return itemDB.getItemPro(itemNum);
	}

	public static void main(String[] args) throws ClassNotFoundException{
		Database dbManage = null;
		try{
			dbManage = new RoomDB();
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		Object[] room = dbManage.getRoom(3);
		for(int i = 0; i < room.length; i++){
			if(room[i] != null){
				System.out.println(room[i].toString());
			}
			else{
				System.out.println("null");
			}
		}
		int[] items = (int[]) room[8];
		if(items[0] != 0){
			for(int i = 0; i <items.length; i++){
				Object[] item = dbManage.getItem(items[i]);
				for(int j = 0; j < item.length; j++){
					System.out.println(item[j]);
				}
			}
		}

	}
}
