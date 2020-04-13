package helloWorld;

import java.util.Arrays;

public class LongestNonDecreasing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] seq = { 1, 15, 16, 7, 17, 18, 19 };
		//int[] seq = { 1, 2, 3, 3, 3, 1, 5, 4, 3, 2, 1, 10, 10, 10, 10, 15, 10, 10, 16, 17};
				
		System.out.print("Sequence: ");
		for (int num : seq) {
			System.out.print(num);
			System.out.print("; ");
		}
		System.out.println();
		System.out.format(
				"Longest non decreasing sequence is %d.",
				longestNonDecreasing(seq)
			);
	}
	
	// WRONG!
	// example seq: 1 2 2 3 3 3 4 5 2 7 8 9
	// 				_ _ _ _ _ _ _ _ _ _ _ _
	// 
	// reccurence: L(i, last) = 
	//							L(i-1, last) 		if seq(i) < last
	//							L(i-1, seq(i))+1 	otherwise
	// base case: L(1) = 1
	// solution: L(N) where N = size of input array
	
	// correct:
	// 1 2 5 92 3 44 23 18 9 (size N)
	// create array L[i] where L(i) is the LIS up to index i
	// initialize all elements to 1 (base case)
	// L(i+1) = 1 + max(L[j]) where sequence(i+1) > sequence(j), j = 0..i
	// O(N^2) time, O(N) space
	static int longestNonDecreasing(int[] sequence) {
		int[] dp = new int[sequence.length]; 
		Arrays.fill(dp, 1); // base case (assume all LIS -> 1)
		
		// bottom-up for LIS up to each index i
		for (int i = 0; i < sequence.length - 1; i++) {
			int max = 0; // keep track of max
			for (int j = 0; j <= i; j++) {
				// check previous solutions
				if (sequence[i+1] >= sequence[j]) {
					max = Math.max(dp[j], max);
				}
			}
			// greater elements found -> increment max LIS
			// no greater elements found -> reset LIS to 1
			dp[i+1] = max > 0 ? (max + 1) : 1;		
		}
		
		return getMax(dp);
	}
	
	static int getMax(int[] array) {
		int max = 0;
		for (int i = 0; i < array.length; i++) {
			max = Math.max(array[i], max); 
		}
		return max;
	}
}
