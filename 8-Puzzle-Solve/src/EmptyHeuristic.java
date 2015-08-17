

/**
 * An implementation of the heuristic for use in algorithms that do not use a heuristic (always returns 1)
 * 
 * @author Spencer Newton
 *
 */
public class EmptyHeuristic implements Heuristic {
	
	/**
	 * The heuristic value of the attached node.
	 */
	private float heuristic = 0.0f;
	
	/**
	 * Default Constructor
	 */
	public EmptyHeuristic() { }
	
	/**
	 * Should calculate the heuristic based on the current state and goal state of the search, 
	 * however this implemention indicates a lack of a heuristic, and so sets it to be 1.
	 * 
	 */
	public void calculateHeuristic( PuzzleNode startState, PuzzleGrid goalState )
	{
		heuristic = 1.0f;
	}
	
	/**
	 * Returns the heuristic value calculated by the calculateHeuristic function.
	 * 
	 * @return The heuristic value.
	 */
	public float getHeuristicValue() 
	{
		return heuristic;
	}
}
