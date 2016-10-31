package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import database.DatabaseManager;

public class TestItem
{
	Item item;
	DatabaseManager dbManage = new DatabaseManager();
	
	@Test
	public void testGetItemID()
	{
		Object[] itemInfo = dbManage.getItemInformation(4);
		item = new Artifacts(itemInfo);
		assertEquals(4, item.getItemID());
	}
	
	@Test
	public void testGetName()
	{
		Object[] itemInfo = dbManage.getItemInformation(4);
		item = new Artifacts(itemInfo);
		assertEquals("Square", item.getName());
	}

	@Test
	public void testGetDescription()
	{
		Object[] itemInfo = dbManage.getItemInformation(4);
		item = new Artifacts(itemInfo);
		assertEquals("A wooden block shaped like an 'Square'.", item.getDescription());
	}

	

}
