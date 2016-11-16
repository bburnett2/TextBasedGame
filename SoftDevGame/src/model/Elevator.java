package model;

public class Elevator 
{
	private int currentLevel = 1;
	
	private Player player;
	private int Level1 = 3;
	private int Level2 = 11;
	private int Level3 = 17;
	private int Level4 = 21;
	private int Level5 = 24;
	private int Level6 = 37;
	private int Level7 = 44;
	private int LevelFinal = 46;
	
	private boolean isL1Complete = false;
	private boolean isL2Complete = false;
	private boolean isL3Complete = false;
	private boolean isL4Complete = false;
	private boolean isL5Complete = false;
	private boolean isL6Complete = false;
	private boolean isL7Complete = false;
	private boolean isFianlComplete = false;
	
	protected Elevator(Player player)
	{
		this.player = player;
	}

	public String toString()
	{
		checkCompleted();
		
		String str = "The elevator floor selection panel has: \n1";
		
		if (isL1Complete)
			str+= "\n2 \n3";
		if (isL2Complete || isL3Complete)
			str += "\n4 \n5";
		if (isL4Complete || isL5Complete)
			str += "\n6 \n7";
		if (isL6Complete && isL7Complete)
			str += "\n8";
		
		str += "\nare lit up";
		
		return str;		
	}
	
	public boolean canGoToLevel(int level)
	{
		boolean canGo = false;
		
		if (level == 1)
			canGo = true;
		if (level == 2 || level == 3)
			canGo = isL1Complete;
		if (level == 4 || level == 5)
		{
			if (isL2Complete || isL3Complete)
			canGo = true;
		}
		if (level == 6 || level == 7)
		{
			if (isL4Complete || isL5Complete)
			canGo = true;
		}
		if (level == 8)
		{
			if (isL6Complete && isL7Complete)
			canGo = true;
		}
		
		return canGo;
	}
	
	private void checkCompleted()
	{
		compleateL1();
		compleateL2();
		compleateL3();
		compleateL4();
		compleateL5();
		compleateL6();
		compleateL7();
		completeFinal();
	}

	private void completeFinal()
	{
		if(player.hasDefeated(8))
		{
			isFianlComplete = true;
		}
	}
	
	private void compleateL7()
	{
		if (player.hasCompleted(10))
				isL7Complete = true;
	}
	
	private void compleateL6()
	{
		if (player.hasCompleted(9))
				isL6Complete = true;
	}
	
	private void compleateL5()
	{
		if (player.hasDefeated(5))
				isL5Complete = true;
	}
	
	private void compleateL4()
	{
		if (player.hasCompleted(6))
				isL4Complete = true;
	}
	
	private void compleateL3()
	{
		if (player.hasDefeated(3))
				isL3Complete = true;
	}
	
	private void compleateL2()
	{
		if (player.hasCompleted(2))
				isL2Complete = true;
	}
	
	private void compleateL1()
	{
		if (player.hasCompleted(1))
				isL1Complete = true;
	}

	protected int getLevel1()
	{
		return Level1;
	}

	protected int getLevel2()
	{
		return Level2;
	}

	protected int getLevel3()
	{
		return Level3;
	}

	protected int getLevel4()
	{
		return Level4;
	}

	protected int getLevel5()
	{
		return Level5;
	}

	protected int getLevel6()
	{
		return Level6;
	}

	protected int getLevel7()
	{
		return Level7;
	}

	protected int getLevelFinal()
	{
		return LevelFinal;
	}

	protected boolean hasWon()
	{
		checkCompleted();
		return isFianlComplete;
	}

	protected int getCurrentLevel()
	{
		return currentLevel;
	}

	protected void setCurrentLevel(int currentLevel)
	{
		this.currentLevel = currentLevel;
	}
}
