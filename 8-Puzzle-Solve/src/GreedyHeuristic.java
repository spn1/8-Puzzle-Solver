

/**
 * An implementation of the Heuristic interface that only takes into account
 * the estimated cheapest path to the goal node from the current node.
 * 
 * @author Spencer Newton
 *
 */
public class GreedyHeuristic implements Heuristic {
	/**
	 * The heuristic value of the attached node.
	 */
	private float heuristic = 0.0f;
	
	/**
	 * Default Constructor
	 */
	public GreedyHeuristic() { }
	
	/**
	 * Calculates the heuristic based on the current state and goal state of the search, using
	 * a greedy heuristic that only takes into account the estimated cost to the goal.
	 */
	public void calculateHeuristic( PuzzleNode currentState, PuzzleGrid goalState )
	{
		// Need PuzzleGrid, not PuzzleNode, for calculation
		PuzzleGrid currentGrid = currentState.getGrid();
		int size = goalState.getWidth() * goalState.getHeight();
		int[] currentPosition = new int[2]; // Coordinates in grid; [0] = x, [1] = y
		int[] destination = new int[2]; // Coordinates in grid; [0] = x, [1] = y
		int distance; // Manhattan distance
		int totalDistance = 0; // Sum of distances for all tiles
		
		/* for each number from 0 to (width * height)
		 * 		record position in currentState
		 * 		calculate difference from goal position in number of moves (absolute difference in x and y)
		 *		sum differences for each number
		 * total sum = heuristic
		*/
		for ( int i = 0; i < size; i++)
		{
			currentPosition = currentGrid.getPositionInGrid( i );
			destination = goalState.getPositionInGrid( i );
			distance = Math.abs( currentPosition[0] - destination[0]) + 
					Math.abs( currentPosition[1] - destination[1]);
			totalDistance += distance;	
		}
		
		heuristic = totalDistance;
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
