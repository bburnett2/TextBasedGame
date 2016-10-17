package database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDB extends DatabaseManager {
	
	protected ItemDB() {
		
	}
	
	protected Object[] getItemPro(int itemNum){
		Object[] item = new Object[9];
		String sqlCall = "SELECT * FROM item WHERE itemID = " + itemNum;
		try{
		ResultSet itemRS = super.statement.executeQuery(sqlCall);
		item[0] = itemRS.getInt("ItemID");
		item[1] = itemRS.getString("Name");
		item[2] = itemRS.getString("Type");
		item[3] = itemRS.getString("Description");
		item[4] = itemRS.getInt("Defense");
		item[5] = itemRS.getInt("Attack");
		item[6] = itemRS.getInt("HP");
		item[7] = (itemRS.getInt("isKey") == 0) ? false : true;
		item[8] = itemRS.getString("Action");
		}
		catch(SQLException ex){
			
		}
		
		return item;
	}
}
