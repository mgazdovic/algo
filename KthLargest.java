/**
 * This file contains two implementations of finding the k-th largest item within an array. 
 * Time complexity: 
 * -> 1) using partitioning (quick select)  -> O(n^2) worst case, O(n) expected (with high probability -> random pivot)
 * -> 2) maintaining a heap of fixed size k -> O(n log k) worst case
 * Space complexity:
 * -> 1) O(1) 
 * -> 2) O(k)
 * 		n -> size of array, k -> rank of element to be found
 * 
 * @author Mislav.Gazdovic, mislav.gazdovic@gmail.com
 *
 */

package com.mgazdovic.algo.searching;

import java.util.PriorityQueue;
import com.mgazdovic.algo.sorting.QuickSortIterative;

public class KthLargest {
	
	/** Performs quick select algorithm. 
	  * @param input non-empty array which is partitioned until k-th largest element is returned.
	  * @param k rank of element to be found. 
	  * @return k-th largest element (any element with rank equal to k). 
	  * @throws IllegalArgumentException if input contains no elements or k is invalid.
	  */
	public static <T extends Comparable<T>> T quickSelect(T[] input, int k) {
		// input validation
		inputValidOrThrow(input, k);
		
		final int n = input.length;
		
		int from = 0;
		int to = n;
		
		// sorted index of k-th largest
		int kSortedIndex = n - k;
		
		// repeat partitioning until k-th largest is at it's sorted position
		int partitionIndex;
		while (true) {
		
			// partition puts pivot in correct position and divides array into two subarrays (<=, pivot, >=)
			partitionIndex = QuickSortIterative.partition(input, from, to);

			// found it
			if (partitionIndex == kSortedIndex) {
				return input[kSortedIndex];
			}
			
			// will be found in range [from, partitionIndex>
			if (partitionIndex > kSortedIndex) {
				to = partitionIndex;
			}
			// will be found in range [partitionIndex + 1, to>
			else {
				from = partitionIndex + 1;
			}	
		}
	}
	
	/** Performs k-largest selection algorithm using min heap. 
	  * @param input non-empty array which is partitioned until k-th largest element is returned.
	  * @param k rank of element to be found. 
	  * @return k-th largest element (any element with rank equal to k). 
	  * @throws IllegalArgumentException if input contains no elements or k is invalid.
	  */
	public static <T extends Comparable<T>> T minHeapSelect(T[] input, int k) {
		// input validation
		inputValidOrThrow(input, k);
		
		// build heap of size k
		PriorityQueue<T> minHeap = new PriorityQueue<>();
		for (int i = 0; i < k; i++) {
			minHeap.add(input[i]);
		}
		
		// maintain size k (keep replacing root with greater elements)
		for (int i = k; i < input.length; i++) {
			if (input[i].compareTo(minHeap.peek()) > 0) {
				minHeap.poll();
				minHeap.add(input[i]);
			}
		}
		
		// min-heap of size k -> root is k-th largest
		return minHeap.peek();
	}
	
	private static void inputValidOrThrow(Object[] input, int k) {
		if (input == null || input.length == 0) throw new IllegalArgumentException("Array must contain at least one element");
		if (k < 1 || k > input.length) throw new IllegalArgumentException("k must be between 1 and array length");
	}
}
