
/**
 * An implementation of the Heuristic interface that only takes into account
 * the estimated cheapest path to the goal node from the current node and the
 * total path length so far
 * 
 * @author Spencer Newton
 *
 */
public class AStarHeuristic implements Heuristic {
	
	/**
	 * The heuristic value of the attached node.
	 */
	private float heuristic = 0.0f;
	
	/**
	 * Default Constructor
	 */
	public AStarHeuristic() { }
	
	/**
	 * Calculates the heuristic based on the current state and goal state of the search, using
	 * the A* heuristic that takes into account the estimated cost to the goal and the total
	 * path cost so far.
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
		
		/* DISTANCE TO GOAL CALCULATION:
		 * for each number from 0 to (width * height)
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
		
		
		/* PATH COST SO FAR CALCULATION:
		 * Count number of parents (each move costs 1) until startNode is reached
		 */
		int costSoFar = 0; // number of steps taken (each step is 'distance' of 1)
		PuzzleNode parent = currentState;
		
		// If parent is null, we have reached the start state, and thus the end of the current path.
		while ( parent != null )
		{
			parent = parent.getParent();
			costSoFar++;
		}
		
		// Sum path cost and estimated distance to goal
		heuristic = totalDistance + costSoFar;
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
