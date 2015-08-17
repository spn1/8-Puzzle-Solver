============ 8-Puzzle Solver =============
Program Written by Spencer Newton


This program will provide the solution (if possible) to any 8-puzzle style solution using the various provided searching algorithms. 

The 8-puzzle game consists of a 3x3 grid where all the sections but one contains a tile with a number on it (from 1 to 8). The tiles can be moved left / right / up / down to an empty section. The goal of the game is to move the tiles from the arrangement they begin in the a specified goal state, after which the puzzle is considered complete.

This program will solve an 8-Puzzle using one of four search algorithms (Depth First Search, Breadth First Search, Greedy Best First Search, and A-Star Search). If a solution is possible, it will print out a list of puzzle states that it has found to get from start to finish.


======= How to Run:

 1 - Navigate to the 8-Puzzle-Solve/bin directory form the command line
 2 - Run the Solve class with the command java Solve x y z where:
	x = a txt file containing the initial puzzle state
	y = a txt file containing the final puzzle state
	z = a string indicating which algorithm to use:
	  - bfs = Best First Search
	  - dfs = Depth First Search
	  - gbfs = Greedy Best First Search
	  - astar = A-Star Search
 3 - The program will then print out it's search pattern, and once it has found a solution 
 
A set of examples has been supplied to test the program with. An example execution would be:
	"java Solve testStart1.txt testGoal1.txt astar"
	
Depending on the start / finish states and the algorithm used, the solution may take a while to find (especially for Depth First Search).	

A report I wrote on the project can be seen in the Report Folder.