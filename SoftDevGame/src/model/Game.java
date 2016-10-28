package model;

import java.sql.SQLException;
import java.util.ArrayList;

import database.DatabaseManager;

public class Game 
{
	Player player;
	DatabaseManager dbManage = new DatabaseManager();

	public void save()
	{
		String playerID = player.getPlayerID();
		int currentRoomID = player.getCurrentRoom();
		int playerHealth = player.getHealth();
		int playerDefense = player.getDefense();
		int playerAttack = player.getAttack();
		ArrayList<Integer> items = player.getUnequippedItems();
		ArrayList<Integer> puzzles = player.getCompletedPuzzles(); 
		ArrayList<Integer> monsters = player.getDefeatedMonsters();
		ArrayList<Item> equippedItemsOb = player.getEquipedItems();
		ArrayList<Integer> equppedItems = new ArrayList<>();
		for(Item item: equippedItemsOb)
		{
			equppedItems.add(item.getItemID());
		}
		Object[] gameInfo = new Object[9];
		gameInfo[0] = playerID;
		gameInfo[1] = currentRoomID;
		gameInfo[2] = playerHealth;
		gameInfo[3] = playerDefense;
		gameInfo[4] = playerAttack;
		gameInfo[5] = items;
		gameInfo[6] = puzzles;
		gameInfo[7] = monsters;
		gameInfo[8] = equppedItems;
		
		try
		{
		dbManage.saveGame(gameInfo);
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
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
