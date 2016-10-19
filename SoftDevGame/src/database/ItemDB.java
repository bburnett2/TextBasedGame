package database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDB extends DatabaseManager {
	
	protected ItemDB() {
		
	}
	
	protected Object[] getItemPro(int itemNum){
		Object[] item = new Object[9];
		sqlCall = "SELECT * FROM item WHERE itemID = " + itemNum;
		try{
		resultSet = super.statement.executeQuery(sqlCall);
		item[0] = resultSet.getInt("ItemID");
		item[1] = resultSet.getString("Name");
		item[2] = resultSet.getString("Type");
		item[3] = resultSet.getString("Description");
		item[4] = resultSet.getInt("Defense");
		item[5] = resultSet.getInt("Attack");
		item[6] = resultSet.getInt("HP");
		item[7] = (resultSet.getInt("isKey") == 0) ? false : true;
		item[8] = resultSet.getString("Action");
		}
		catch(SQLException ex){
			
		}
		
		return item;
	}
}
