
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
 * This class - runs the game
 * 
 * Purpose  - to control all the interactions that happened during the course of the game
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
	 * 
	 * prints game ending for character death
	 */
	private void endOfGameByCharacterDeath()
	{
		print("You died, better luck next time");
		//endOfGame();
	}

	/**
	 * Method name: endOfGameByWin
	 * 
	 * prints gamed ending for a win
	 */
	private void endOfGameByWin()
	{
		print("Congratulations!! You WON!!!!");
		//endOfGame();
	}

	/**
	 * Method name: endOfGame
	 * 
	 * prints the end credits
	 */
	private void endOfGame()
	{
		print("Under The Feet of Many\n" +
				"Implemented by: \n\tBess Burnett\n\tDaniel Harris\n\tMichael Holtmann\n\tMarcus Moss \n" +
				"Designed by: \n\tMatthew Xiong \n\tMatthew Nelson \n\tJohanna Timmer");
		input.close();
	}

	/**
	 * Method name: mainLoop
	 * 
	 * checks for any key words and preforms the action associated with it
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
						endGameByWin = enterElevatorSubLoop();
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
					if(model.getRoom().hasMonster())
					{
						if(!(model.getRoom().getMonster().getHealth() > 0))
						{
							//last min bug fix.
							model.getRoom().monsterKilled();
							if(result.get(false)){
								endGameByWin = enterElevatorSubLoop();
							}
						}
					}
				}
				else if (commands.get(0).equalsIgnoreCase("enter")){
					endGameByWin = enterElevatorSubLoop();
				}
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
				else if(commands.get(0).equalsIgnoreCase("description"))
				{
					model.description();
				}
				else if(commands.get(0).equalsIgnoreCase("save"))
				{
					print(model.saveGame());
				}
				else if (commands.get(0).equalsIgnoreCase("quit"));
				else if(commands.get(0).equalsIgnoreCase("create")){
					boolean itemCreated = model.create();
					if(itemCreated){
						print("You made a 'C'");
					}
					else{
						print("You don't have the items to create anything");
					}
				}
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
	 * 
	 * puts the player in the elevator where they may select from a list of valid levels
	 * @throws GameException 
	 */
	private boolean enterElevatorSubLoop() throws GameException
	{
		boolean inElevator = true;
		String command;
		ArrayList<String> commands = new ArrayList<String>();

		boolean hasWon = model.enterElevator();
		if(hasWon){
			endOfGameByWin();
		}
		else{
			do
			{
				command = read();
				commands = parsString(command);

				if (!(commands.contains("exit")))
					inElevator = model.pushElevator(commands);
			}while(!(inElevator) && !(commands.contains("exit")) && !hasWon);
		}
		return hasWon;
	}

	//start new game with playerID, selected by user, or load saved game
	/**
	 * Method name: loadOrNew
	 * 
	 * allows the player to load a saved game or start a new one
	 */
	private void loadOrNew(){

		boolean hasPlayerID = false;
		while(!hasPlayerID){
			print("Select new game or select from the list of saved games: [\"new\" or name below] \n");
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
				if(!loadableGames.contains(name)){
					model.buildNewPlayer(name);
					hasPlayerID = true;
				}
				else{
					print("can't start new game with saved game name\n");
				}
			}
			else {
				print("invalid playerID");
			}

		}
		startGame();
	}

	/**
	 * Method name: startGame
	 * 
	 * begins the game
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
	 * @param str: String
	 * 
	 * prints the given string
	 */
	private void print(String str)
	{
		model.print(str);
	}

	/**
	 * Method name: read
	 * @return: String
	 * 
	 * reads in the user input and returns it
	 */
	public String read() 
	{
		String str=input.nextLine();
		return str;
	}

	/**
	 * Method name: parsString
	 * @param command: String
	 * @return ArrayList<String>
	 * 
	 * takes a user input and parses it out into an ArrayList
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
	 * @param answer1: String
	 * 
	 * provides a loop where the player completes the final puzzle
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
