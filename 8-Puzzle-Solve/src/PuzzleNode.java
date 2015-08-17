import java.util.ArrayList;


/**
 * Represents a grid state and information about said state in the context of a search.
 * <p>
 * Includes heuristic values, any children the state may have, and the parent of the 
 * state if visited during a search.
 * 
 * @author Spencer Newton
 *
 */
public class PuzzleNode {
	/**
	 * The grid state the information contain within is regarding.
	 */
	private PuzzleGrid grid;
	
	/**
	 * The heuristic used, specified by the search algorithm that created the object.
	 */
	private Heuristic heuristic;
	
	/**
	 * A list of children of the PuzzleGrid.
	 * A child represents one legal move from the current state.
	 */
	private ArrayList<PuzzleNode> children = new ArrayList<PuzzleNode>();
	
	/**
	 * The parent of the state in the context of a search.
	 */
	private PuzzleNode parent;
	
	/**
	 * Constructor for a PuzzleNode.
	 * 
	 * @param grid 	The grid state this node represents.
	 * @param h		The heuristic that is being used by the search algorithm.
	 */
	public PuzzleNode( PuzzleGrid grid, Heuristic h )
	{
		this.grid = grid;
		this.heuristic = h;
	}
	
	/**
	 * Generates and returns a list of child nodes of the grid state, where a 
	 * child node represents one legal move from the grid state.
	 * 
	 * @return		An ArrayList of child nodes extending from the grid state.
	 */
	public ArrayList<PuzzleNode> createChildren()
	{
		PuzzleNode pn;
		PuzzleGrid pg;
		
		/*
		 * Child Generation Procedure (next 4 blocks of code):
		 * 1 - Copy this grid state into new object
		 * 2 - Try to move the empty space (0) up / left / right / down
		 * 3 - If possible (translateSpace returns true)... 
		 * 		Create new PuzzleNode for the grid state, copying the current used heuristic using 
		 * 		reflection.
		 * 		Set the parent of the new PuzzleNode to this object
		 * 		Add the new PuzzleNode to the list of Children for this object
		 * 4 - If not possible then do nothing
		 */

		// Up
		pg = new PuzzleGrid( grid );
		if ( pg.translateSpace(0, -1) ) 
		{ 
			try {
				pn = new PuzzleNode( pg, heuristic.getClass().newInstance() );
				pn.setParent( this );
				children.add( pn ); 
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		// Left
		pg = new PuzzleGrid( grid );
		if ( pg.translateSpace(-1, 0) ) 
		{ 
			try {
				pn = new PuzzleNode( pg, heuristic.getClass().newInstance() );
				pn.setParent( this );
				children.add( pn ); 
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		// Right
		pg = new PuzzleGrid( grid );
		if ( pg.translateSpace(1, 0) ) 
		{ 
			try {
				pn = new PuzzleNode( pg, heuristic.getClass().newInstance() );
				pn.setParent( this );
				children.add( pn ); 
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
				
		// Down
		pg = new PuzzleGrid( grid );
		if ( pg.translateSpace(0, 1) ) // True if it is possible to move empty square
		{ 
			try {
				pn = new PuzzleNode( pg, heuristic.getClass().newInstance() );
				pn.setParent( this );
				children.add( pn ); 
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		return children;
	}
	
	/**
	 * Calculates the value using the heuristic object based upon the
	 * specified goal grid state.
	 * 
	 * @param goalState 	The goal state of the solution.
	 */
	public void determineHeuristic( PuzzleGrid goalState )
	{
	    if ( heuristic != null )
	    { 
		heuristic.calculateHeuristic( this, goalState);
	    }
	}	
	
	/**
	 * Returns the heuristic value based on this grid state.
	 * 
	 * @return Heuristic float value gained from the heuristic object.
	 */
	public float getHeuristicValue()
	{
	    if ( heuristic != null )
	    {
		return heuristic.getHeuristicValue();
	    } else {
		return 9999.f;
	    }
	}
	
	/**
	 * Determine whether the grid state in this object is equal 
	 * to (each tile is in the same position) the specified PuzzleNode pn.
	 * 
	 * @param pn	The PuzzleNode object we are testing for equality.
	 * @return	Whether the objects are equal to each other.
	 */
	public boolean equalTo( PuzzleNode pn )
	{
	    return grid.equalTo( pn.getGrid() );
	}
	
	/**
	 * Returns the list of child nodes of this grid state.
	 * 
	 * @return	An ArrayList containing child nodes of this grid state.
	 */
	public ArrayList<PuzzleNode> getChildren()
	{
	    return children;
	}
	
	/**
	 * Sets the parent parameter of this grid state during a search.
	 * 
	 * @param pn	The parent PuzzleNode.
	 */
	public void setParent( PuzzleNode pn )
	{
	    this.parent = pn;
	}

	/**
	 * Returns the parent of this grid state during a search.
	 * 
	 * @return 	The parent of this grid state.
	 */
	public PuzzleNode getParent()
	{
	    return parent;
	}
	
	/**
	 * Returns the puzzle grid state of this node.
	 * 
	 * @return	The puzzle grid state of this node.
	 */
	public PuzzleGrid getGrid()
	{
	    return grid;
	}
	
	/**
	 * Sets the grid state of this node.
	 * 
	 * @param grid		The new puzzle grid state of this node.
	 */
	public void setGrid( PuzzleGrid grid )
	{
	    this.grid = grid;
	}
	
}
