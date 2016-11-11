package model;

/**Class: Artifacts.java 
   * @author Bess Burnett, Daniel Harris, Michael Holtmann, Marcus Moss
   * @version 1.0 
   * Course : ITEC 3860 Fall 2016
   * Written: Nov 11, 2016 
  	   * 
   * This class –  
   * 
   * Purpose: –  
   */
public class Artifacts extends Item 
{
	

	/**Artifacts
	 * @param item
	 * agr constructor
	 *
	 * creates a Artifacts object with 
	 */
	protected Artifacts(Object[] item)
	{
		super(item);
		// TODO Auto-generated constructor stubS
	}

	/* use
	 * 
	 */
	protected boolean use(Player player) 
	{
		boolean completes = false;
		if(completesPuzzle.keySet().contains(true))
		{
			completes = true;
		}
		return completes;
	}

	/* isEquippable
	 * 
	 */
	@Override
	protected boolean isEquippable()
	{
		return false;
	}

}
