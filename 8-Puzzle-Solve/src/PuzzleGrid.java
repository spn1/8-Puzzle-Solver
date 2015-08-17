import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * A class that represents the state of the puzzle grid at any one time.
 * 
 * @author Spencer Newton
 *
 */
public class PuzzleGrid 
{
    	/**
    	 * A 2D array of integers representing a state of the grid.
    	 */
	private int[][] grid;
	
	/**
	 * The width of the grid.
	 */
	private int width;
	
	/**
	 * The height of the grid.
	 */
	private int height;
	
	/**
	 * The x and y coordinates of the 0 element (the empty tile).
	 */
	private int x0;
	private int y0; 
	
	/**
	 * A Constructor for initializing the grid with the contents of a file.
	 * 
	 * @param filename The name of the file containing the grid data.
	 */
	public PuzzleGrid( String filename)
	{
	    loadGridFromFile( filename );
	}
	
	/**
	 * A Constructor for initializing a grid of width 'w' and height 'h' in 
	 * numerical order from left to right.
	 * 
	 * @param w	The width of the grid.
	 * @param h The height f the grid.
	 */
	public PuzzleGrid( int w, int h )
	{
		// Set object parameters using arguments
		width = w; height = h;
		
		// Create grid of width 'w' and height 'h'
		grid = new int[w][h];
		int count = 0;
		
		// For each x / y coordinate, set element of grid to count
		for (int j = 0; j < h; j++) 
		{
			for ( int i = 0; i < w; i++)
			{
				grid[i][j] = count;
				if ( count == 0 )
				{
					// If count = 0 (empty tile), record coordinates
					x0 = i;
					y0 = j;
				}
				count++;
			}
							
		}
	}
	
	/**
	 * A Constructor for initializing a grid with another grid (ie 
	 * copying the contents of one grid state to this new one.)
	 * 
	 * @param pg	The grid that the information will be copied from.
	 */
	public PuzzleGrid( PuzzleGrid pg )
	{
		// Set object parameters using pg's parameters.
		width = pg.getWidth();
		height = pg.getHeight();
		grid = new int[width][height];
		
		// For each x / y coordinate, copy contents of pg's grid to this grid.
		for ( int y = 0; y < height; y++ )
		{
			for ( int x = 0; x < width; x++ )
			{
				grid[x][y] = pg.getRawGrid()[x][y];
			}
		}
		
		// Also copy coordinates of empty tile.
		x0 = pg.getEmptyX();
		y0 = pg.getEmptyY();
	}
	
	/**
	 * Loads a puzzle grid state from a file. 
	 * <p>
	 * It is assumed that the file is in the format:
	 * 0,1,2
	 * 3,4,5
	 * 6,7,8
	 * where there numbers can be in any order.
	 * 
	 * @param filename The name of the file containing the grid state.
	 */
	public void loadGridFromFile( String filename )
	{
		try {
			BufferedReader br = new BufferedReader( new FileReader( filename ));
			String[] data = new String[width];
			String delimiter = ",", line = null;
			int i = 0, j = 0;
			
			try 
			{ 
				line = br.readLine(); 
				
				// Based on file format, determine grid width / height
				width = line.length() / 2 + 1;
				height = line.length() / 2 + 1;
				grid = new int[width][height];
				
				// While file isn't empty
				while ( line != null )
				{
					// Split lines using delimiter of ','
					data = line.split( delimiter );
					
					// Put contents of each formatted line into grid array
					for (i = 0; i < width; i++) 
					{
						grid[i][j] = Integer.parseInt( data[i] );
						if ( grid[i][j] == 0 ) 
						{
							x0 = i;
							y0 = j;
						}
					}
				
					j++;
					line = br.readLine();
				}
			}
			catch (IOException e) 
			{ 
				System.out.println( "Unable to read from file " + filename + ". Closing..." ); 
				System.exit(0);
			}
		}
		catch ( FileNotFoundException e)
		{
			System.out.println( "File with name " + filename + " not found. Closing..." );
			System.exit(0);
		}
	}
	
	/**
	 * This function translates the grid element 0 (the empty tile) by one in either
	 * the x or y direction. If there is no space to move, then nothing happens.
	 * 
	 * @param x Represents the direction of translation, negative for left, zero for none, positive for right
	 * @param y Represents the direction of translation, negative for up, zero for none, positive for down;
	 * @return True if the space was translated, false is not
	 * 
	 */ 
	public boolean translateSpace( int x, int y )
	{
		boolean success = false;
		
		// If translation is horizontal
		if ( x != 0 )
		{
			if ( x > 0 && x0 < 2) // Translate right, can't if x0 > 1
			{
				grid[x0][y0] = grid[x0+1][y0];
				grid[x0+1][y0] = 0;
				
				x0 = x0+1;
				success = true;
			}
			else if ( x < 0 && x0 > 0 ) // Translate left, can't if x0 < 1
			{
				grid[x0][y0] = grid[x0-1][y0];
				grid[x0-1][y0] = 0;
				
				x0 = x0-1;
				success = true;
			}
		} // If translation is vertical
		else if ( y != 0 ) // Can't translate diagonal, so if x != 0, then y wont be translated no matter
		{				   // what it is, and visa versa
			if ( y > 0 && y0 < 2) // Translate down, can't if y0 > 1
			{
				grid[x0][y0] = grid[x0][y0+1];
				grid[x0][y0+1] = 0;
				
				y0 = y0+1;
				success = true;
			}
			else if ( y < 0 && y0 > 0 ) // Translate up, cant if y0 < 1
			{
				grid[x0][y0] = grid[x0][y0-1];
				grid[x0][y0-1] = 0;
				
				y0 = y0-1;
				success = true;
			}
		}
		
		return success;
	}
	
	/**
	 * Print the grid to the console.
	 */
	public void printGrid()
	{
		for ( int y = 0; y < height; y++)
		{
			for ( int x = 0; x < width; x++ )
			{
				// If x & y are the same as the empty tile coordinates, print empty tile
				if ( x == x0 && y == y0 ) System.out.print( "  " );
				// Else print element of grid
				else System.out.print( grid[x][y] + " " );
			}
			System.out.print( "\n" );
		}
		System.out.print( "\n" );
	}
	
	/**
	 * Print the grid to the console with an indent to indicate that the grid
	 * is a child of another grid.
	 */
	public void printGridAsChild()
	{
		for ( int y = 0; y < height; y++)
		{
			System.out.print( "\t" );
			for ( int x = 0; x < width; x++ )
			{
				// If x & y are the same as the empty tile coordinates, print empty tile
				if ( x == x0 && y == y0 ) System.out.print( "  " );
				// Else print element of grid
				else System.out.print( grid[x][y] + " " );
			}
			System.out.print( "\n" );
		}
		System.out.print( "\n" );
	}
	
	/**
	 * Determine whether the grid in this object is equal 
	 * to (each tile is in the same position) the specified PuzzleGrid pg.
	 * 
	 * @param pg The PuzzleGrid that we are testing for equality.
	 * @return True if they are equal, false if not.
	 */
	public boolean equalTo( PuzzleGrid pg )
	{
		boolean equal = true;
		
		// For each element in each grid, test for equality
		// Loops break if it is found that two elements are not equal
		for ( int y = 0; y < height && equal == true; y++ )
		{
			for ( int x = 0; x < width && equal == true; x++ )
			{
				// If any element is not equal, equal = false
				if ( grid[x][y] != pg.getRawGrid()[x][y] ) 
				{
					equal = false;
				}
			}
		}
		
		return equal;
	}
	
	/**
	 * Returns the position of a number in the grid in the form of a 2D array, 
	 * where element [0] is the x coordinate and element [1] is the y coordinate.
	 * 
	 * @param tile The number we want to know the position of.
	 * @return A 2D array containing the coordinates of the number.
	 */
	public int[] getPositionInGrid( int tile )
	{
		int[] position = new int[2];
		
		for ( int y = 0; y < height; y++ )
		{
			for ( int x = 0; x < width; x++ )
			{
				if ( grid[x][y] == tile ) 
				{
					position[0] = x;
					position[1] = y;
					
					// Should break but meh
				}
			}
		}
		
		return position;
	}
	
	/**
	 * Returns the 2D array representing the puzzle grid.
	 * 
	 * @return 2D array representing the grid.
	 */
	public int[][] getRawGrid()
	{
		return grid;
	}
	
	/**
	 * Returns the width of the grid.
	 * 
	 * @return The width of the grid.
	 */
	public int getWidth()
	{
		return width;
	}
	
	/**
	 * Returns the height of the grid.
	 * 
	 * @return The height of the grid.
	 */
	public int getHeight()
	{
		return height;
	}
	
	/**
	 * Returns the x coordinate of the element containing 0 (the empty tile)
	 * 
	 * @return The x coordinate of the 0 element
	 */
	public int getEmptyX()
	{
		return x0;
	}
	
	/**
	 * Returns the y coordinate of the element containing 0 (the empty tile)
	 * 
	 * @return The y coordinate of the 0 element
	 */
	public int getEmptyY()
	{
		return y0;
	}
	
}
