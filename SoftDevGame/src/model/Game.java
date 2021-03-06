package model;

import java.sql.SQLException;
import java.util.ArrayList;

import database.DatabaseManager;

public class Game 
{
	private static DatabaseManager dbManage = new DatabaseManager();

	public static String save(Player player)
	{
		String name = player.getName();
		int currentRoomID = player.getCurrentRoom();
		int playerHealth = player.getHealth();
		int playerDefense = player.getDefense();
		int playerAttack = player.getAttack();
		int maxHealth = player.getMaxHealth();
		ArrayList<Item> items = player.getUnequippedItems();
		ArrayList<Integer> puzzles = player.getCompletedPuzzles(); 
		ArrayList<Integer> monsters = player.getDefeatedMonsters();
		ArrayList<Item> equippedItemsOb = player.getEquipedItems();
		ArrayList<Integer> equppedItems = new ArrayList<>();
		for(Item item: equippedItemsOb)
		{
			equppedItems.add(item.getItemID());
		}
		Object[] gameInfo = new Object[10];
		gameInfo[0] = name;
		gameInfo[1] = currentRoomID;
		gameInfo[2] = playerHealth;
		gameInfo[3] = playerDefense;
		gameInfo[4] = playerAttack;
		gameInfo[5] = getIDS(items);
		gameInfo[6] = puzzles;
		gameInfo[7] = monsters;
		gameInfo[8] = equppedItems;
		gameInfo[9] = maxHealth;
		String retString = "Game not saved";
		try
		{
			retString = dbManage.saveGame(gameInfo);
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		return retString;
	}

	private static ArrayList<Integer> getIDS(ArrayList<Item> items){
		ArrayList<Integer> itemIDS = new ArrayList<>();
		for(Item item : items){
			itemIDS.add(item.getItemID());
		}
		return itemIDS;
	}

	public void load() 
	{

	}

	public void help() 
	{

	}

	public void availableGames()
	{

	}

	public void newGame()
	{

	}

	public void quit() 
	{

	}

}
