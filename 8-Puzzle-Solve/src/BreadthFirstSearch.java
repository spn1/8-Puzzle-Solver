import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;


/**
 * An implementation of the SolutionFinder interface that will find the solution
 * to a problem using a Breadth First Search algorithm.
 * 
 * @author Spencer Newton
 *
 */

public class BreadthFirstSearch implements SolutionFinder {
	/**
	 * A list containing the states already visited along the search for the solution
	 */
	private ArrayList<PuzzleNode> statesVisited = new ArrayList<PuzzleNode>();
	
	/**
	 * A list containing the solution - a list of nodes from the start state to the goal state using
	 * legal moves.
	 */
	private ArrayList<PuzzleGrid> solution = new ArrayList<PuzzleGrid>();
	
	/**
	 * A Linked List containing the states that have been opened and need to be searched. 
	 * The list is treated as if it were a queue - elements are only added to the end and 
	 * retrieved from the front.
	 */
	private LinkedList<PuzzleNode> openStates = new LinkedList<PuzzleNode>(); // offer = add to tail; poll = get from head;
	
	// Analysis Parameters
	private long runTime = 0; // In milliseconds
	private long startTime, endTime; // In nanoseconds
	
	/**
	 * Default Constructor.
	 */
	public BreadthFirstSearch() { }
	
	/**
	 * Finds a solution of the puzzle from the start state to the goal state using a 
	 * Breadth First Search Algorithm.
	 *
	 * @see SolutionFinder#findSolution(PuzzleGrid, PuzzleGrid)
	 */
	public ArrayList<PuzzleGrid> findSolution(PuzzleGrid startState, PuzzleGrid goalState)
	{
		// Initialize solution list
		solution = new ArrayList<PuzzleGrid>();
		
		//Initialize list of children
		ArrayList<PuzzleNode> children = new ArrayList<PuzzleNode>();
		PuzzleNode currentState = null;
		
		boolean solutionFound = false, stateVisited = false;
		int nodeCounter = 0;
		
		// Create PuzzleNode using start state and an EmptyHeuristic and offer to end of open states queue
		openStates.offer( new PuzzleNode( startState, new EmptyHeuristic() ) );
		
		// Record start time of search
		startTime = System.nanoTime();
		
		System.out.println( "===== Nodes Opened" );
		
		// Continue search while there are still nodes to be searched AND a solution hasn't been found
		while ( !openStates.isEmpty() && solutionFound == false )
		{
			// Poll from head of openStates = currentState
			currentState = openStates.poll();
			nodeCounter++;
			
			// =========================================== Debug
			System.out.println( "Current = " + nodeCounter );
			currentState.getGrid().printGrid();
			// =========================================== Debug

			// If currentState == goalState, break and record path
			if ( currentState.getGrid().equalTo( goalState ) )
			{
				solutionFound = true;
				break;
			}
			
			// Else, poll currentState for child nodes
			children = currentState.createChildren();
			for ( int i=0; i < children.size(); i++ )
			{
				// Instead of using list.contains( child ), the list is manually searched

				// Check whether each child node hasn't already been visited				
				stateVisited = false;
				for ( PuzzleNode newNode : statesVisited )
				{
					// If statesVisited already contains child
					if ( children.get(i).equalTo( newNode ) ) 
					{ 
						System.out.println( "\t Closed \n" );
						stateVisited = true;
						break;
					}
				}
				
				// Check whether each child node hasn't already been opened, but hasn't been searched
				for ( PuzzleNode newNode : openStates )
				{
					// If openStates already contains child
					if ( children.get(i).equalTo( newNode ) ) 
					{ 
						System.out.println( "\t Opened \n" );
						stateVisited = true;
						break;
					}
				}
				
				
				// CHILD HAS NOT BEEN VISITED OR OPENED
				if ( stateVisited != true )
				{
					openStates.offer( children.get(i) );
					
					// =========================================== Debug
					children.get(i).getGrid().printGridAsChild(); // Wont print child if in statesVisited
//					try {
//						Thread.sleep( 100 );
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
					// =========================================== Debug
				}
			}
			
			// Add currentState to statesVisited, remove currentState from openStates
			statesVisited.add( currentState );	
			openStates.remove( currentState );		
		}
		
		// Search ended, so record time (nanoseconds_ and calculate total search time (miliseconds)
		endTime = System.nanoTime();
		runTime = (endTime - startTime) / 1000000;
		
		if ( solutionFound == true )
		{
			boolean pathFound = false;
			// Recreate path to solution
			// At this point, currentState is the goal
			// Concatenate parent-child chain until startState is reached
			
			// Add current state to solution list
			// Recursively prepend parent of current state to solution list
			// End recursion when currentState = startState
			solution.add( currentState.getGrid() );
			while ( pathFound == false )
			{
				currentState = currentState.getParent();
				solution.add( 0, currentState.getGrid() );
				if ( currentState.getGrid().equalTo( startState ) ) pathFound = true;
			}
		} 
		else //Solution wasnt found
		{
			System.out.println( "Could not find Solution :(" );
		}
		
		return solution;
	}
	
	public void printSearchData()
	{
		System.out.println( " === SEARCH ANALYSIS === " );
		System.out.println( " Run Time: " + runTime + " milliseconds" );
		System.out.println( " Nodes Searched: " + statesVisited.size() );
		System.out.println( " Solution Length: " + solution.size() );
		System.out.println( " ======================= " );
	}

}