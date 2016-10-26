package model;

public class Elevator 
{
	private Player player = null;
	private int Level1 = 3;
	private int Level2 = 11;
	private int Level3 = 17;
	private int Level4 = 21;
	private int Level5 = 24;
	private int Level6 = 37;
	private int Level7 = 44;
	private int LevelFinal = 46;
	
	//needs methods to find completed and open floors and print that to user.  Then user will
	//input which floor.
	public Elevator(Player player)
	{
		this.player = player;
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
