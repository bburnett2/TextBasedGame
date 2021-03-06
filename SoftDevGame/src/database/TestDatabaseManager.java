package database;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import error.GameException;

public class TestDatabaseManager
{

	DatabaseManager dbmanage = new DatabaseManager();
	@Test
	public void testGetRoomInformation()
	{
		Object[] actual = new Object[10];
		try{
			actual = dbmanage.getRoomInformation(30);
		}
		catch(GameException ex){

		}
		Object[] expected = new Object[10];
		expected[0] = 30;
		expected[2] = 6;
		expected[3] = 27;
		expected[4] = 0;
		expected[5] = 31;
		expected[6] = 0;
		expected[7] = "0,0";
		ArrayList<Integer> items = new ArrayList<>();
		items.add(25);
		expected[8] = items;
		expected[9] = 0;

		assertEquals(expected[0], actual[0]);
		assertEquals(actual[1], actual[1]);
		assertEquals(expected[2], actual[2]);
		assertEquals(expected[3], actual[3]);
		assertEquals(expected[4], actual[4]);
		assertEquals(expected[5], actual[5]);
		assertEquals(expected[6], actual[6]);
		assertEquals(expected[7], actual[7]);
		assertEquals(expected[8], actual[8]);
		assertEquals(expected[9], actual[9]);
	}

	@Test
	public void testGetRoomInformationInvalid(){
		String message = "";
		try{
			Object[] actual = dbmanage.getRoomInformation(-1);
		}
		catch(GameException ex){
			message = ex.getMessage();
		}
		assertEquals("Invalid RoomID", message);
	}

	@Test
	public void testGetRoomInformationInvalid2(){
		String message = "";
		try{
			Object[] actual = dbmanage.getRoomInformation(47);
		}
		catch(GameException ex){
			message = ex.getMessage();
		}
		assertEquals("Invalid RoomID", message);
	}

	@Test
	public void testGetMonsterInformation(){
		Object[] expected = new Object[7];
		expected[0] = "Gardener";
		expected[1] = 3;
		expected[2] = dbmanage.addLineBreaks("A sweaty man who looks like he’s been working hard non-stop. He has a crazed look of over-exhaustion in his eyes.");
		expected[3] = 1;
		expected[4] = 10;
		expected[5] = 0;
		ArrayList<Integer> items = new ArrayList<>();
		items.add(13);
		items.add(14);
		items.add(15);
		items.add(16);
		expected[6] = items;
		Object[] actual = null;

		try{
			actual = dbmanage.getMonsterInformation(3);
		}
		catch(GameException ex){

		}
		assertEquals(expected[0], actual[0]);
		assertEquals(expected[1], actual[1]);
		assertEquals(expected[2], actual[2]);
		assertEquals(expected[3], actual[3]);
		assertEquals(expected[4], actual[4]);
		assertEquals(expected[5], actual[5]);
		assertEquals(expected[6], actual[6]);
	}
	
	@Test
	public void testGetMonsterInformationInvalid(){
		String message = "";
		try{
			Object[] actual = dbmanage.getMonsterInformation(-2);
		}
		catch(GameException ex){
			message = ex.getMessage();
		}
		assertEquals("Invalid MonsterID", message);
	}

	@Test
	public void testGetMonsterInformationInvalid2(){
		String message = "";
		try{
			Object[] actual = dbmanage.getMonsterInformation(10);
		}
		catch(GameException ex){
			message = ex.getMessage();
		}
		assertEquals("Invalid MonsterID", message);
	}

	@Test
	public void testGetPuzzleInformation(){
		Object[] expected = new Object[6];
		expected[0] = 6;
		expected[1] = "use C";
		expected[2] = "use C";
		expected[3] = 24;
		expected[4] = 0;
		expected[5] = true;
		Object[] actual = new Object[6];
		try{
			actual = dbmanage.getPuzzleInformation(6);
		}
		catch(GameException ex){

		}
		assertEquals(expected[0], actual[0]);
		assertEquals(expected[1], actual[1]);
		assertEquals(expected[2], actual[2]);
		assertEquals(expected[3], actual[3]);
		assertEquals(expected[4], actual[4]);
		assertEquals(expected[5], actual[5]);
	}

	@Test
	public void testGetPuzzleInformationInvalid(){
		String message = "";
		try{
			Object[] actual = dbmanage.getPuzzleInformation(-2);
		}
		catch(GameException ex){
			message = ex.getMessage();
		}
		assertEquals("Invalid PuzzleID", message);
	}

	@Test
	public void testGetPuzzleInformationInvalid2(){
		String message = "";
		try{
			Object[] actual = dbmanage.getPuzzleInformation(13);
		}
		catch(GameException ex){
			message = ex.getMessage();
		}
		assertEquals("Invalid PuzzleID", message);
	}

	@Test
	public void testGetItemInformation(){
		Object[] expected = new Object[10];
		expected[0] = 4;
		expected[1] = "Square";
		expected[2] = "artifacts";
		expected[3] = "A wooden block shaped like an 'Square'.";
		expected[4] = 0;
		expected[5] = 0;
		expected[6] = 0;
		expected[7] = false;
		expected[8] = "none";
		Map<Boolean, Integer> map = new TreeMap<>();
		map.put(false, 0);
		expected[9] = map;

		Object[] actual = new Object[10];
		try{
			actual = dbmanage.getItemInformation(4);
		}
		catch(GameException ex){

		}

		assertEquals(expected[0], actual[0]);
		assertEquals(expected[1], actual[1]);
		assertEquals(expected[2], actual[2]);
		assertEquals(expected[3], actual[3]);
		assertEquals(expected[4], actual[4]);
		assertEquals(expected[5], actual[5]);
		assertEquals(expected[6], actual[6]);
		assertEquals(expected[7], actual[7]);
		assertEquals(expected[8], actual[8]);
		assertEquals(expected[9], actual[9]);
	}

	@Test
	public void testGetItemInformationInvalid(){
		String message = "";
		try{
			Object[] actual = dbmanage.getItemInformation(-1);
		}
		catch(GameException ex){
			message = ex.getMessage();
		}
		assertEquals("Invalid ItemID", message);
	}

	@Test
	public void testGetItemInformationInvalid2(){
		String message = "";
		try{
			Object[] actual = dbmanage.getItemInformation(48);
		}
		catch(GameException ex){
			message = ex.getMessage();
		}
		assertEquals("Invalid ItemID", message);
	}

	//	@Test
	//	public void testSaveGame(){
	//		Object[] expected = new Object[10];
	//		expected[0] = "unitTest";
	//		expected[1] = 11;
	//		expected[2] = 15;
	//		expected[3] = 5;
	//		expected[4] = 7;
	//		expected[5] = new ArrayList<Integer>(Arrays.asList(1, 4, 9));
	//		expected[6] = new ArrayList<Integer>(Arrays.asList(3));
	//		expected[7] = new ArrayList<Integer>(Arrays.asList(4, 7));
	//		expected[8] = new ArrayList<Integer>(Arrays.asList(8));
	//		expected[9] = 20;
	//		try{
	//			dbmanage.saveGame(expected);
	//		}
	//		catch (SQLException ex){
	//			ex.printStackTrace();
	//		}
	//		Object[] actual = dbmanage.loadGame("unitTest");
	//		
	//		assertEquals(expected[0], actual[0]);
	//		assertEquals(expected[1], actual[1]);
	//		assertEquals(expected[2], actual[4]);
	//		assertEquals(expected[3], actual[5]);
	//		assertEquals(expected[4], actual[3]);
	//		assertEquals(expected[5], actual[6]);
	//		assertEquals(expected[6], actual[2]);
	//		assertEquals(expected[7], actual[7]);
	//		assertEquals(expected[8], actual[8]);
	//		assertEquals(expected[9], actual[9]);
	//	}

	@Test
	public void testGetLoadableGames(){
		ArrayList<String> actual = dbmanage.getLoadableGames();
		ArrayList<String> expected = new ArrayList<>(Arrays.asList("unitTest"));
		assertEquals(expected.get(0), actual.get(0));
	}
}
