package database;

import java.sql.SQLException;
import java.util.ArrayList;

public class MonsterDB extends DatabaseManager
{

	protected Object[] getMonsterInformationPro(int monsterNum){
		sqlCall = "SELECT * FROM monster WHERE monsterID = " + monsterNum;
		Object[] monster = new Object[7];
		try{
			resultSet = statement.executeQuery(sqlCall);
			monster[0] = resultSet.getInt("MonsterID");
			monster[1] = addLineBreaks(resultSet.getString("Description"));
			monster[2] = resultSet.getInt("Attack");
			monster[3] = resultSet.getInt("Health");
			monster[4] = resultSet.getInt("Defense");
			monster[5] = getMonsterItemsInts(monsterNum);
			monster[6] = "fred";
		}
		catch(SQLException ex){

		}
		return monster;
	}
	
	protected ArrayList<Integer> getMonsterItemsInts(int monsterNum){
		sqlCall = "SELECT itemID FROM monster_item WHERE monsterID = " + monsterNum;
		ArrayList<Integer> items = new ArrayList<Integer>();
		try{
			resultSet = statement.executeQuery(sqlCall);
			while(resultSet.next()){
				items.add(resultSet.getInt("ItemID"));
			}
		}
		catch(SQLException ex){
			
		}
		return items;
	}

}
