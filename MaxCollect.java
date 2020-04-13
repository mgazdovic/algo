/**
 * This file contains a dynamic programming implementation of maximum collection in a grid. 
 * Start position is top left, end position is bottom right (moves allowed are right and down). 
 * The collect value for each position is given in the input grid.
 * The task is to find the maximum possible value obtained from the given grid in one grid pass from top left to bottom right. 
 * 
 * Time complexity: 	O(m*n)
 * Space complexity: 	O(m*n)
 * 		m -> number of rows in a grid
 * 		n -> number of columns in a grid
 * 
 * @author Mislav.Gazdovic, mislav.gazdovic@gmail.com
 *
 */

package com.mgazdovic.algo.dp;

public class MaxCollect {
	
	/** Finds maximum collect in a single path through a grid. 
	  * @param grid non-empty array of allowed coin values.
	  * @return maximum achievable collect value.  
	  * @throws IllegalArgumentException if grid is invalid (empty or contains no elements).
	  */
	public static int solve (int[][] grid) {
		// input validation
		if (grid == null || grid.length == 0) throw new IllegalArgumentException("Grid must contain at least one element");
		
		// grid size
		int m = grid.length;
		int n = grid[0].length;
		
		// subproblem solutions
		int[][] dp = new int[m][n];
		int fromUp;
		int fromLeft;
		
		// bottom-up grid progression (top left -> bottom right)
		for(int r = 0; r < m; r++) {
			for (int c = 0; c < n; c++) {
				fromUp = r > 0 ? dp[r - 1][c] : 0;
				fromLeft = c > 0 ? dp[r][c - 1] : 0;
				
				// choose best so far and add current position's value
				dp[r][c] = Math.max(fromUp, fromLeft) + grid[r][c];
			}
		}
		return dp[m-1][n-1];
	}
}
