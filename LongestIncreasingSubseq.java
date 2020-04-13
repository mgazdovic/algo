/**
 * This file contains a dynamic programming implementation of longest increasing subsequence (LIS) problem. 
 * 
 * Reccurence: 
 * Let DP(i) be the LIS up to index i in seq. Then, for each i: 
 * DP(i+1) = 1 + max{DP(j)} for all j up to i (0 <= j <= i) where seq(i+1) > seq(j)
 * DP(0) = 1 (base case)
 * 
 * Time complexity: 	O(n * total)
 * Space complexity: 	O(total)
 * 		n -> number of available coins
 * 		total -> coin values target sum
 * 
 * @author Mislav.Gazdovic, mislav.gazdovic@gmail.com
 *
 */

package com.mgazdovic.algo.dp;

public class LongestIncreasingSubseq {

	/** Finds minimum number of coins required to get the desired total. 
	  * @param coins non-empty array of allowed coin values.
	  * @param total desired value to be reached by adding individual coins. 
	  * @return minimum number of coins required to get the desired total (returns -1 if solution not found). 
	  * @throws IllegalArgumentException if input contains no elements.
	  */
	public static int solve (int[] sequence) {
		// input validation
		if (sequence == null || sequence.length == 0) throw new IllegalArgumentException("Array must contain at least one element");
		
		int[] dp = new int[sequence.length]; 
		dp[0] = 1; // base case
		
		// bottom-up for LIS up to each index i
		for (int i = 0; i < sequence.length - 1; i++) {
			int max = 0; // keep track of max
			for (int j = 0; j <= i; j++) {
				// check previous solutions
				if (sequence[i+1] > sequence[j]) {
					max = Math.max(dp[j], max);
				}
			}
			// greater elements found -> increment max LIS
			// no greater elements found -> reset LIS to 1
			dp[i+1] = max + 1;
		}
		
		return getMax(dp);
	}
	
	/** Utility method to return maximum element from an input array.  
	  * @param array non-empty array. 
	  * @return maximum element in a given array. 
	  */
	private static int getMax(int[] array) {
		int max = 0;
		for (int i = 0; i < array.length; i++) {
			max = Math.max(array[i], max); 
		}
		return max;
	}
}
