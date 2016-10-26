package model;

public class Elevator 
{
	private int currentLevel = 1;
	
	private Player player = null;
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

	public String toString()
	{
		checkCompleted();
		
		String str = "";
		
		if (isL1Complete)
			str+= "The elevator floor selection panel has: \n1 \n2 \n3";
		if (isL2Complete || isL3Complete)
			str += "\n4 \n5";
		if (isL4Complete || isL5Complete)
			str += " \n6 \n7";
		if (isL6Complete && isL7Complete)
			str += "\n8";
		
		str += "\nAre lit up";
		
		return str;		
	}
	
	protected void checkCompleted()
	{
		compleateL1();
		compleateL2();
		compleateL3();
		compleateL4();
		compleateL5();
		compleateL6();
		compleateL7();
	}
	
	protected void compleateL7()
	{
		if (player.hasCompleted(10))
				isL7Complete = true;
	}
	
	protected void compleateL6()
	{
		if (player.hasCompleted(9))
				isL6Complete = true;
	}
	
	protected void compleateL5()
	{
		if (player.hasDefeated(5))
				isL5Complete = true;
	}
	
	protected void compleateL4()
	{
		if (player.hasCompleted(6))
				isL4Complete = true;
	}
	
	protected void compleateL3()
	{
		if (player.hasDefeated(3))
				isL3Complete = true;
	}
	
	protected void compleateL2()
	{
		if (player.hasCompleted(2))
				isL2Complete = true;
	}
	
	protected void compleateL1()
	{
		if (player.hasCompleted(1))
				isL1Complete = true;
	}
	
	public Elevator(Player player)
	{
		this.player = player;
	}
	
	public boolean levelComplete(){
		return false;
	}
	
	public String floors(int[] monsters, int[] puzzles)
	{
		
		return "";
	}

	public Player getPlayer()
	{
		return player;
	}

	public void setPlayer(Player player)
	{
		this.player = player;
	}

	public int getLevel1()
	{
		return Level1;
	}

	public void setLevel1(int level1)
	{
		Level1 = level1;
	}

	public int getLevel2()
	{
		return Level2;
	}

	public void setLevel2(int level2)
	{
		Level2 = level2;
	}

	public int getLevel3()
	{
		return Level3;
	}

	public void setLevel3(int level3)
	{
		Level3 = level3;
	}

	public int getLevel4()
	{
		return Level4;
	}

	public void setLevel4(int level4)
	{
		Level4 = level4;
	}

	public int getLevel5()
	{
		return Level5;
	}

	public void setLevel5(int level5)
	{
		Level5 = level5;
	}

	public int getLevel6()
	{
		return Level6;
	}

	public void setLevel6(int level6)
	{
		Level6 = level6;
	}

	public int getLevel7()
	{
		return Level7;
	}

	public void setLevel7(int level7)
	{
		Level7 = level7;
	}

	public int getLevelFinal()
	{
		return LevelFinal;
	}

	public void setLevelFinal(int levelFinal)
	{
		LevelFinal = levelFinal;
	}

}
