package com.mgazdovic.algo.dp;

import java.util.Arrays;

public class MinCoins {
	
	/** Finds minimum number of coins required to get the desired total. 
	  * @param coins non-empty array of allowed coin values.
	  * @param total desired value to be reached by adding individual coins. 
	  * @return minimum number of coins required to get the desired total (returns -1 if solution not found). 
	  * @throws IllegalArgumentException if input contains no elements or total is invalid.
	  */
	public static int solve (int[] coins, int total) {
		// input validation
		if (coins == null || coins.length == 0) throw new IllegalArgumentException("Array must contain at least one coin value");
		if (total <= 0) throw new IllegalArgumentException("Total must be a positive number");
		
		int[] dp = new int[total+1]; 
		Arrays.fill(dp, Integer.MAX_VALUE); // max value = not reachable
		dp[0] = 0; // base case
		
		// bottom-up for each total
		for (int i = 1; i <= total; i++) {
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < coins.length; j++) {
				int prev = i - coins[j]; // check previous solution
				if (prev >= 0 && dp[prev] != Integer.MAX_VALUE) {
					min = Math.min(dp[prev] + 1, min);
				}
			}
			dp[i] = min;
		}
		// solution found? 
		if (dp[total] == Integer.MAX_VALUE) return -1; // -1 = not found
		return dp[total];
	}
}
