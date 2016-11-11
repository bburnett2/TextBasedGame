package model;

import java.util.Map;
import java.util.TreeMap;

import error.GameException;

public class FinalPuzzle{
	private String answer = "right";
	protected int puzzleID = 11;

	public FinalPuzzle()
	{

	}

	public String answer1(String answer) throws GameException
	{
		String retString;
		if(answer.equalsIgnoreCase("right"))
		{
			retString = "You are asking the right gaurd a question, would you like to ask about the left or right box?";
		}
		else if(answer.equalsIgnoreCase("left"))
		{
			retString = "You are asking the left gaurd a question, would you like to ask about the left or right box?";
		}
		else
		{
			throw new GameException("invalid answer");
		}
		return retString;
	}

	public String answer2(String answer) throws GameException
	{
		String retString = "Which question would you like to ask (select the letter): \n" +
				"A.) Would the other guard say that this is the treat box? \n" +
				"B.) Would the other guard say that you would say this is the treat box? \n" +
				"C.) Is this the treat box? \nD.) Is the other box the treat box?"; 
		if (answer.equalsIgnoreCase("right") || answer.equalsIgnoreCase("left"))
		{

		}
		else 
		{
			throw new GameException("invalid answer");
		}
		return retString;
	}
//check against email for accuracy
	public String answer3(String answer1, String answer2, String answer3)throws GameException
	{
		String retString ="";
		answer3 = answer3.toUpperCase();
		if(answer1.equalsIgnoreCase("left"))
		{
			if(answer2.equalsIgnoreCase("left"))
			{
				switch(answer3){
					case "A": retString = "yes";
					break;
					case "B": retString = "no";
					break;
					case "C": retString = "no";
					break;
					case "D": retString = "yes";
					break;
					default: throw new GameException("invalid input");
				}
			}
			else
			{
				switch(answer3)
				{
					case "A": retString = "no";
					break;
					case "B": retString = "no";
					break;
					case "C": retString = "yes";
					break;
					case "D": retString = "no";
					break;
					default: throw new GameException("invalid input");
				}
			}
		}
		
		if(answer1.equalsIgnoreCase("right"))
		{
			if(answer2.equalsIgnoreCase("left"))
			{
				switch(answer3){
					case "A": retString = "yes";
					break;
					case "B": retString = "yes";
					break;
					case "C": retString = "yes";
					break;
					case "D": retString = "no";
					break;
					default: throw new GameException("invalid input");
				}
			}
			else
			{
				switch(answer3)
				{
					case "A": retString = "no";
					break;
					case "B": retString = "yes";
					break;
					case "C": retString = "no";
					break;
					case "D": retString = "yes";
					break;
					default: throw new GameException("invalid input");
				}
			}
		}
		return retString;
	}
	
	public Map<Boolean, String> finalAnswer(String answer)
	{
		Map<Boolean, String> retMap = new TreeMap<>();
		if(answer.equalsIgnoreCase("right"))
		{
			retMap.put(true, "Correct, your prize is a Revive");
		}
		else{
			retMap.put(false, "The box opens and glitter flies out onto your face. You made the wrong choice, and you now look utterly silly."); 
		}
		return retMap;
	}
}


