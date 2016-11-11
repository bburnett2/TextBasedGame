killerB
package Controller;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import error.GameException;
//import model.Player;


/**Class: GameControl.java 
   * @author Bess Burnett, Daniel Harris, Michael Holtmann, Marcus Moss
   * @version 1.0 
   * Course : ITEC 3860 Fall 2016
   * Written: Nov 11, 2016 
  	   * 
   * This class
   * 
   * Purpose  
   */
public class GameControl 
{
	model.GameModel model = new model.GameModel();
	Scanner input = new Scanner(System.in);
	ArrayList<String> validCommands = new ArrayList<>();


	public static void main(String[] args)
	{
		GameControl run = new GameControl();
		run.loadOrNew();
		run.mainLoop();
		run.endOfGame();
	}


	/**
	 * Method name: endOfGameByCharacterDeath
	 */
	private void endOfGameByCharacterDeath()
	{
		print("You died, better luck next time");
		//endOfGame();
	}

	/**
	 * Method name: endOfGameByWin
	 */
	private void endOfGameByWin()
	{
		print("Congratulations!! You WON!!!!");
		//endOfGame();
	}

	/**
	 * Method name: endOfGame
	 */
	private void endOfGame()
	{
		print("Under The Feet of Many\n" +
				"Implemented by: \n\tBess Burnett\n\tDaniel Harris\n\tMichael Holtmann\n\tMarcus Moss" +
				"Designed by: \n\tMatthew Xiong \n\tMatthew Nelson \n\tJohanna Timmer \n\tTimothy Warren");
		input.close();
	}

	/**
	 * Method name: mainLoop
	 */
	private void mainLoop()
	{
		String command;
		boolean endGameByDeath = false;
		boolean endGameByWin = false;
		ArrayList<String> commands = new ArrayList<String>();

		do{
			command = read();
			commands = parsString(command);

			try{
				//cannot use a switch because of the complexity of the []
				if (commands.get(0).equalsIgnoreCase("go"))
					model.go(commands);
				else if (commands.get(0).equalsIgnoreCase("answer") && model.getPlayer().getCurrentRoom() != 46)
				{
					boolean completesLevel = model.answer(commands);
					if(completesLevel)
						enterElevatorSubLoop();
				}
				else if (commands.get(0).equalsIgnoreCase("answer") && model.getPlayer().getCurrentRoom() == 46){
					enterFinalSubloop(commands.get(1));
				}
				else if (commands.get(0).equalsIgnoreCase("equip"))
					model.equip(commands);
				else if (commands.get(0).equalsIgnoreCase("help"))
					model.help(commands);
				else if ((commands.size() > 1) && (commands.get(0) + commands.get(1)).equalsIgnoreCase("playerstats"))
					model.stats();
				else if (commands.get(0).equalsIgnoreCase("drop"))
					model.drop(commands);
				else if (commands.get(0).equalsIgnoreCase("attack"))
				{
					Map<Boolean, Boolean> result = model.attack(commands);
					endGameByDeath = (result.containsKey(true)) ? true : false;
					if(endGameByDeath){
						endOfGameByCharacterDeath();
					}
					if(!(model.getRoom().getMonster().getHealth() > 0))
					{
						if(result.get(false)){
							enterElevatorSubLoop();
						}
					}
				}
				else if (commands.get(0).equalsIgnoreCase("enter"))
					enterElevatorSubLoop();
				else if (commands.get(0).equalsIgnoreCase("use"))
				{
					boolean completesLevel = model.use(commands);
					if(completesLevel)
						enterElevatorSubLoop();
				}
				else if (commands.get(0).equalsIgnoreCase("run"))
					model.run(commands);
				else if ((commands.size() > 1) && (commands.get(0) + commands.get(1)).equalsIgnoreCase("listitems"))
					model.listItems(commands);
				else if ((commands.size() > 1) && (commands.get(0) + commands.get(1)).equalsIgnoreCase("pickup"))
				{
					model.pickUp(commands);
				}
				else if(commands.get(0).equalsIgnoreCase("save"))
				{
					print(model.saveGame());
				}
				else if (commands.get(0).equalsIgnoreCase("quit"));
				else
					throw new GameException ("Not a valid action command.");

			}catch (GameException exc)
			{
				print(exc.getMessage());
			}

		}while(!(command.equalsIgnoreCase("quit")) && !(endGameByDeath) && !endGameByWin);

	}


	/**
	 * Method name: enterElevatorSubLoop
	 */
	private void enterElevatorSubLoop()
	{
		boolean inElevator = true;
		String command;
		ArrayList<String> commands = new ArrayList<String>();

		boolean hasWon = model.enterElevator();
		if(hasWon){
			endOfGameByWin();
		}
		do
		{
			command = read();
			commands = parsString(command);

			if (!(commands.contains("exit")))
				inElevator = model.pushElevator(commands);
		}while(!(inElevator) && !(commands.contains("exit")));
	}

	//start new game with playerID, selected by user, or load saved game
	/**
	 * Method name: loadOrNew
	 */
	private void loadOrNew(){

		boolean hasPlayerID = false;
		while(!hasPlayerID){
			print("Select new game or select from the list of saved games: \n");
			ArrayList<String> loadableGames = model.getLoadableGames();
			for(String playerID : loadableGames){
				print(playerID);
			}
			String name = read();
			if(loadableGames.contains(name)){
				model.buildPlayer(name);
				hasPlayerID = true;
			}
			else if (name.equalsIgnoreCase("new")){
				print("What would you like your PlayerID to be");
				name = read();
				model.buildNewPlayer(name);
				hasPlayerID = true;
			}
			else {
				print("invalid playerID");
			}

		}
		startGame();
	}

	/**
	 * Method name: startGame
	 */
	private void startGame()
	{
		model.firstRoom();
		validCommands.add("Go");
		validCommands.add("Answer");
		validCommands.add("Equip");
		validCommands.add("Help");
		validCommands.add("Enter");
		validCommands.add("Use");
		validCommands.add("quit");
	}

	/**
	 * Method name: print
	 * @param str
	 */
	private void print(String str)
	{
		model.print(str);
	}

	/**
	 * Method name: read
	 * @return
	 */
	public String read() 
	{
		String str=input.nextLine();
		return str;
	}

	/**
	 * Method name: parsString
	 * @param command
	 * @return
	 */
	public ArrayList<String> parsString(String command) 
	{
		String[] splitStr = command.split(" ");;
		ArrayList<String> parsCommand = new ArrayList<String>();
		for (int i = 0; i < splitStr.length; i++)
		{
			parsCommand.add(splitStr[i]);
		}
		return parsCommand;
	}

	/**
	 * Method name: enterFinalSubloop
	 * @param answer1
	 */
	public void enterFinalSubloop(String answer1)
	{
		boolean hasValid = false;

		while(!hasValid)
		{
			try
			{
				print(model.answerFinal1(answer1));
				hasValid = true;
			}
			catch(GameException ex)
			{
				print(ex.getMessage());
			}
		}

		hasValid = false;
		String answer2 = "";

		while(!hasValid)
		{
			answer2 = read();
			try
			{
				print(model.answerFinal2(answer2));
				hasValid = true;
			}
			catch(GameException ex)
			{
				print(ex.getMessage());
			}
		}

		String answer3;
		hasValid = false;
		while(!hasValid)
		{
			answer3 = read();
			try
			{
				print(model.answerFinal3(answer1, answer2, answer3));
				hasValid = true;
			}
			catch(GameException ex)
			{
				print(ex.getMessage());
			}
		}

		print("Which box do you think holds the treat?");
		String answerFinal = read();
		print(model.finalAnswer(answerFinal));
	}
}
