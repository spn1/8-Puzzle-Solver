

/**
 * An interface designed to represent a heuristic used in a search algorithm.
 * 
 * @author Spencer Newton
 *
 */
public interface Heuristic {

	/**
	 * Calculates a heuristic value using an implementation-dependent heuristic function
	 * based upon the current state of the search and the goal state of the search.
	 * 
	 * @param currentState The current state of the search.
	 * @param goalState The goal state of the search.
	 */
	public void calculateHeuristic( PuzzleNode currentState, PuzzleGrid goalState );

	/**
	 * Returns the heuristic value calculated by the calculateHeuristic function.
	 * 
	 * @return The heuristic value.
	 */
	public float getHeuristicValue();
	
}
