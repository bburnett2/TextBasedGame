package database;

import java.sql.SQLException;
import java.util.ArrayList;

public class MonsterDB extends DatabaseManager
{

	public Object[] getMonster(int monsterNum){
		sqlCall = "SELECT * FROM monster WHERE monsterID = " + monsterNum;
		Object[] monster = new Object[6];
		try{
			resultSet = statement.executeQuery(sqlCall);
			monster[0] = resultSet.getInt("MonsterID");
			monster[1] = resultSet.getString("Description");
			monster[2] = resultSet.getInt("Attack");
			monster[3] = resultSet.getInt("Health");
			monster[4] = resultSet.getInt("Defense");
			monster[5] = getMonsterItems(monsterNum);
		}
		catch(SQLException ex){

		}
		return monster;
	}
	
	public ArrayList<Integer> getMonsterItems(int monsterNum){
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
