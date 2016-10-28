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
		item[3] = addLineBreaks(resultSet.getString("Description"));
		item[4] = resultSet.getInt("Defense");
		item[5] = resultSet.getInt("Attack");
		item[6] = resultSet.getInt("HealthPoints");
		item[7] = (resultSet.getInt("isKey") == 0) ? false : true;
		item[8] = resultSet.getString("Action");
		item[9] = createBooleanMap(resultSet.getInt("Completes_Puzzle"));
		}
		catch(SQLException ex){
			
		}
		
		return item;
	}
	
	protected Map<Boolean, Integer> createBooleanMap(int puzzNum){
		Map<Boolean, Integer> completesPuzzle = new TreeMap<Boolean, Integer>();
		if(puzzNum != 0){
			completesPuzzle.put(true, puzzNum);
		}
		else{
			completesPuzzle.put(false, puzzNum);
		}
		return completesPuzzle;
	}
}
