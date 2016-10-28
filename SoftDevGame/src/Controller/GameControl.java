package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import error.GameException;


public class GameControl 
{
	model.GameModel model = new model.GameModel();
	Scanner input = new Scanner(System.in);
	ArrayList<String> validCommands = new ArrayList<>();


	public static void main(String[] args)
	{
		GameControl run = new GameControl();
		run.startGame();
		run.mainLoop();
	}


	private void mainLoop()
	{
		String command;
		ArrayList<String> commands = new ArrayList<String>();

		do{
			command = read();
			commands = parsString(command);

			try{
				//cannot use a switch because of the complexity of the []
				if (commands.get(0).equalsIgnoreCase("go"))
					model.go(commands);
				else if (commands.get(0).equalsIgnoreCase("answer")){
					boolean completesLevel = model.answer(commands);
					if(completesLevel)
						enterElevatorSubLoop();
				}
				else if (commands.get(0).equalsIgnoreCase("equip"))
					model.equip(commands);
				else if (commands.get(0).equalsIgnoreCase("enter"))
					enterElevatorSubLoop();
				else if (commands.get(0).equalsIgnoreCase("use"))
					model.use(commands);
				else if (commands.get(0).equalsIgnoreCase("run"))
					model.run(commands);
				else if (commands.get(0).equalsIgnoreCase("quit"));
				else
					throw new GameException ("Not a valid action command.");

			}catch (GameException exc)
			{
				print(exc.getMessage());
			}

		}while(!(command.equalsIgnoreCase("quit")));

	}


	private void enterElevatorSubLoop()
	{
		boolean inElevator = true;
		String command;
		ArrayList<String> commands = new ArrayList<String>();

		model.enterElevator();

		do
		{
			command = read();
			commands = parsString(command);

			if (!(commands.contains("exit")))
				inElevator = model.pushElevator(commands);
		}while(!(inElevator) && !(commands.contains("exit")));
	}


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

	private void print(String str)
	{
		model.print(str);
	}

	public String read() 
	{
		String str=input.nextLine();
		return str;
	}

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

}
