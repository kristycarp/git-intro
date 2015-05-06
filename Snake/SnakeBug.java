// Kristy Carpenter

import info.gridworld.actor.*;
import info.gridworld.grid.*; 
import java.util.*;

public class SnakeBug extends Bug
{

	/** The amount to grow the tail by. Don't grow if this is 0. */
	private int growBy; 
	
	/** An array list of locations that comprise the tail. */
	private ArrayList<Location> array;


	/** 
	 * constructor. 
	 * Set the initial tail size and the data structure that holds the tail. 
	 */
	
	public SnakeBug()
	{
		growBy = 4; 
		
		array = new ArrayList<Location>(); 

	}


	/**
	 *  Find a random, empty location in the grid
	 *	 @return - a random empty location on the grid
	 */
	private Location getRandomEmptyLocation()
	{
		Grid<Actor> gr = getGrid(); 
		Random r = new Random();
		Location loc = null; 
		while ( true )
		{		
			int row = r.nextInt(gr.getNumRows()); 
			int col = r.nextInt(gr.getNumCols()); 			
			loc = new Location(row, col); 
			Actor a = gr.get(loc); 
			if ( a == null )
				return loc; 
		}
	}

	/**
	 *  Create a random Flower in a location on the grid. 
	 *
	 */
	public void randomFlower()
	{
		Location loc = getRandomEmptyLocation();
      Flower f = new Flower();
      f.putSelfInGrid(getGrid(), loc);
	}
	
	/**
	 *  If the bug can't move (it runs into the edge or a rock) then the game is
	 *  over. Print out the score (the tail size) and end. If it can, then just move 
	 *  the normal way. 
	 *
	 */
	public void act()
	{
		if ( !canMove() )
		{
			// This will put up a dialog box with the length of the tail... 
			javax.swing.JOptionPane.showMessageDialog(null, "Score: " + array.size(), "GAME OVER!", 0);
			System.exit(0);
		}
		else
			move(); 
	}
			
			
	/** 
	 * Move the bug. 
	 * If the bug hits a flower, grow the tail by 3, and move the flower. 
	 * If there is still more tail to grow, add a rock to the tail
	 * If there is no more tail to grow, just make it appear like it's moving by adding one to the beginning
	 * and removing it from the end
	 */
	public void move()
	{
		Grid<Actor> gr = getGrid(); 
		if ( gr == null )
			return; 

		Location loc = getLocation(); 
		Location next = loc.getAdjacentLocation(getDirection()); 

		if ( gr.isValid(next))
		{
			Actor neighbor = gr.get(next); 
			boolean addFlower = false; 
			if ( neighbor != null || neighbor instanceof Flower )
			{	
				growBy += 3; 
				neighbor.removeSelfFromGrid(); 
				addFlower = true; 
			}
			moveTo(next); 
			addRockBehindHead(loc); 
			// If a flower was eaten, put a new one randomly on the grid. 
			if (addFlower)
				randomFlower();
				
			// Grow only if you have to... 
			if ( growBy > 0 )
				growBy--; 
			else
				eraseTail(); 
		}
	}
						
				
	/** 
	 *  It will create a Rock object at location loc and add loc to the beginning of the tail list.
	 *  @param loc - the Location that the Rock object should be created at 
	 */ 	
	private void addRockBehindHead(Location loc)
	{
		Rock r = new Rock();
      r.putSelfInGrid(getGrid(), loc);
      array.add(0, loc);
	}
	
	/** 
	 *  Remove the last Rock in the snake to create the illusion of movement.  It removes the last element 
	 *  of the ArrayList from both the ArrayList and the grid.
	 */
	public void eraseTail()
	{
		Grid g = getGrid();
      Rock r = (Rock) g.get(array.remove(array.size() - 1));
      r.removeSelfFromGrid();
	}
		
}