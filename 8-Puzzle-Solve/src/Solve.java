import java.util.ArrayList;


/**
 * A program to solve the 8-Puzzle using various search algorithms
 * 
 * @author Spencer Newton
 *
 */

public class Solve {
	/**
	 * The search algorithm object
	 */
	private SolutionFinder search;
	
	/**
	 * Entry method of program.
	 * 
	 * @param args Arguments from program execution
	 */
	public static void main(String[] args) {		
		Solve solver = new Solve();
		
		solver.Run(args[0], args[1], args[2]);			
	}
	
	/**
	 * Default constructor
	 */
	public Solve() { }
	
	/**
	 * Initiates search and prints data regarding it to the screen.
	 * <p>
	 * The method contains all the execution of the program, which includes 
	 * determining the search algorithm, running it, and printing out the result 
	 * with an analysis of the search.
	 * 
	 * @param startFilename	The file containing the start state of the puzzle
	 * @param goalFilename	The file containing the goal state of the puzzle
	 * @param searchAlgorithm	A code specifying the search algorithm to be used
	 */
	public void Run( String startFilename, String goalFilename, String searchAlgorithm )
	{
		// A list of states representing the path from beginning to end
		ArrayList<PuzzleGrid> solution = new ArrayList<PuzzleGrid>();
		// The initial grid state (loaded from startFilename)
		PuzzleGrid gridStart = new PuzzleGrid( startFilename ); 
		// The final grid state (loaded from goaFilename)
		PuzzleGrid gridGoal = new PuzzleGrid( goalFilename );
		
		// Print out start grid state
		System.out.println( "===== Start");
		gridStart.printGrid();
		
		// Print out goal grid state
		System.out.println( "===== Goal" );
		gridGoal.printGrid();
		
		// Determine which search algorithm to use based on intial argument
		switch( searchAlgorithm )
		{
		case "bfs":
			search = new BreadthFirstSearch();
			break;
		case "dfs":
			search = new DepthFirstSearch();
			break;
		case "gbfs":
			search = new GreedyBestFirstSearch();
			break;
		case "astar":
			search = new AStarSearch();
			break;
		default:
			break;
		}	
		
		// If search algorithm code recognised
		if ( search != null )
		{
			// Call method in the search algorithm object to find a solution path
			// from states gridStart to gridGoal
			solution = search.findSolution(gridStart, gridGoal);
			
			if ( solution != null )
			{
				// If solution found, then print out solution path
				System.out.println( "===== Solution Path" );
				for ( int i = 0; i < solution.size(); i++ )
				{
				solution.get(i).printGrid();
				}
			}
			else // If solution not found
			{
				System.out.println( "Could not find Solution :(" );
			}
			
			// Print analysis of search / solution and print reminder of intial arguments
			search.printSearchData();
			System.out.println( "     Start: " + startFilename );
			System.out.println( "       End: " + goalFilename );
			System.out.println( " Algorithm: " + searchAlgorithm );
		}
		else // If search algorithm code not recognised
		{
			System.out.println( "Search term not recognised." );
			System.out.println( "Try again with one of the following terms:" );
			System.out.println( "bfs \ndfs \ngbfs \nastar" );
		}
	}

}
