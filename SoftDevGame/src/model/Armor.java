package model;

/**Class: Armor.java 
   * @author Bess Burnett, Daniel Harris, Michael Holtmann, Marcus Moss 
   * @version 1.0 
   * Course : ITEC 3869 Fall 2016
   * Written: Nov 11, 2016 
  	   * 
   * This class –  
   * 
   * Purpose: –  
   */
public class Armor extends Item 
{
	
	protected int defense;
	protected int attack;
	
	/**Armor
	 * @param item
	 * agr constructor
	 *
	 * creates a Armor object with 
	 */
	protected Armor(Object[] item)
	{
		super(item);
		this.defense = (int) item[4];
		this.attack = (int) item[5];
	}


	/* use
	 * 
	 */
	@Override
	protected boolean use(Player player)
	{
		player.setAttack(player.getAttack() + this.attack);
	    player.setDefense(player.getDefense() + this.defense);
		return false;
	}

	/* isEquippable
	 * 
	 */
	@Override
	protected boolean isEquippable()
	{
		return true;
	}

}
