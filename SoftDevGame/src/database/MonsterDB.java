package database;

import java.sql.SQLException;
import java.util.ArrayList;

import error.GameException;

public class MonsterDB extends DatabaseManager
{

	protected Object[] getMonsterInformationPro(int monsterNum){
		sqlCall = "SELECT * FROM monster WHERE monsterID = " + monsterNum;
		Object[] monster = new Object[8];
		try{
			resultSet = statement.executeQuery(sqlCall);
			monster[0] = resultSet.getString("MonsterName");
			monster[1] = resultSet.getInt("MonsterID");
			monster[2] = addLineBreaks(resultSet.getString("Description"));
			monster[3] = resultSet.getInt("Attack");
			monster[4] = resultSet.getInt("Health");
			monster[5] = resultSet.getInt("Defense");
			monster[7] = (resultSet.getInt("CompletesLevel") == 1) ? true : false;
			monster[6] = getMonsterItemsInts(monsterNum);
		}
		catch(SQLException ex){
		//	throw new GameException("Invalid MonsterID");
		}
		finally {
			try{
				statement.close();
			}
			catch(SQLException ex){
				
			}
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
		finally {
			try{
				statement.close();
			}
			catch(SQLException ex){
				
			}
		}
		return items;
	}

}
