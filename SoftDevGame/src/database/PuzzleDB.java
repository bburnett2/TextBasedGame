package database;

import java.sql.SQLException;

public class PuzzleDB extends DatabaseManager{

	protected Object[] getPuzzleInformationPro(int puzzleNum) {
		sqlCall = "SELECT * FROM puzzle WHERE puzzleID = " + puzzleNum;
		Object[] puzzle = new Object[6];
		try{
			resultSet = statement.executeQuery(sqlCall);
			puzzle[0] = resultSet.getInt("PuzzleID");
			puzzle[1] = addLineBreaks(resultSet.getString("Description"));
			puzzle[2] = resultSet.getString("Answer");
			puzzle[3] = resultSet.getInt("ItemNeeded");
			puzzle[4] = resultSet.getInt("ItemRecieved");
			puzzle[5] = (resultSet.getInt("CompletesLevel") == 0) ? false : true;
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
		return puzzle;
	}

}
