import java.util.ArrayList;


/**
 * An interface used for the implementation of search algorithms
 * 
 * @author Spencer Newton
 *
 */
public interface SolutionFinder {

	/**
	 * Finds a sequence of states to get from startState to goalState.
	 * 
	 * @param startState The initial state of the problem
	 * @param goalState The goal state of the problem
	 * @return The solution to the problem, in the form of a list on sequential states 
	 * 		   that represent a number of moves to get from the initial state to the goal state.
	 */
	public ArrayList<PuzzleGrid> findSolution( PuzzleGrid startState, PuzzleGrid goalState );
	
	/**
	 * Prints to the console data from the search, specifically the number of nodes
	 * explored and the time taken to do the search.
	 */
	public void printSearchData();
}
