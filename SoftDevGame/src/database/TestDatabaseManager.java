package database;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

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

}
