package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class ItemDB extends DatabaseManager {

	protected ItemDB() {

	}

	protected Object[] getItemInformationPro(int itemNum){
		Object[] item = new Object[10];
		sqlCall = "SELECT * FROM item WHERE itemID = " + itemNum;
		try{
			resultSet = super.statement.executeQuery(sqlCall);
			item[0] = resultSet.getInt("ItemID");
			item[1] = resultSet.getString("Name");
			item[2] = resultSet.getString("Type");
			item[3] = resultSet.getString("Description");
			item[4] = resultSet.getInt("Defense");
			item[5] = resultSet.getInt("Attack");
			item[6] = resultSet.getInt("HealthPoints");
			item[7] = (resultSet.getInt("isKey") == 0) ? false : true;
			item[8] = resultSet.getString("Action");
			int levelCompletes = resultSet.getInt("Completes_Level");
			Map<Boolean, Integer> completesLevel = new TreeMap<>();
			if (levelCompletes != 0){
				completesLevel.put(true, levelCompletes);
			}
			else {
				completesLevel.put(false, 0);
			}
			item[9] = completesLevel;
		}
		catch(SQLException ex){

		}

		return item;
	}
}
