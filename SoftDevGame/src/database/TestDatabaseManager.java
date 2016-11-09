package database;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
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
		Object[] actual = dbmanage.getRoomInformation(30);
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
	public void testGetPuzzleInformation(){
		Object[] expected = new Object[6];
		expected[0] = 6;
		expected[1] = "use 'C'";
		expected[2] = "use 'C'";
		expected[3] = 24;
		expected[4] = 0;
		expected[5] = true;
		Object[] actual = dbmanage.getPuzzleInformation(6);
		
		assertEquals(expected[0], actual[0]);
		assertEquals(expected[1], actual[1]);
		assertEquals(expected[2], actual[2]);
		assertEquals(expected[3], actual[3]);
		assertEquals(expected[4], actual[4]);
		assertEquals(expected[5], actual[5]);
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
		
		Object[] actual = dbmanage.getItemInformation(4);
		
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
}
