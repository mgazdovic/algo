/**
 * This file contains an implementation of bottom-up merge sort. 
 * Merge sort is the most famous divide-and-conquer sorting algorithm. 
 * This is a fun little optimization over a simple recursive solution which avoids the recursion overhead using bottom-up approach.
 * 
 * Time complexity: 	O(n log n)
 * Space complexity: 	O(n)
 * 		n -> size of array
 * 
 * @author Mislav.Gazdovic, mislav.gazdovic@gmail.com
 *
 */

package com.mgazdovic.algo.sorting;

public class MergeSortBottomUp {
	
	  /** Performs bottom-up merge sort algorithm. 
	  * @param input non-empty array to be sorted.
	  * @throws IllegalArgumentException if input is null or contains no elements.
	  */
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] input) {
		// input validation
		if (input == null || input.length == 0) throw new IllegalArgumentException("Array must contain at least one element");
		
		final int N = input.length;
		Comparable[] aux = new Comparable[N];
		
		// merge all sorted subarrays of size 1, 2, 4, ..., N/2 -> N
		for (int sz = 1; sz<N; sz*=2)
			for (int i = 0; i<N; i+=2*sz)	
				merge(input, aux, i, i+sz, Math.min(i+2*sz, N));
		
		assert isSorted(input, 0, N);
	}
	
	/** Merges sorted arrays [fromIndex, midIndex> and [midIndex, toIndex> to sorted array [fromIndex, toIndex>
	  * @param input non-empty array to be sorted.
	  * @param aux auxiliary array for merge routine.
	  * @param fromIndex starting index for first subarray (inclusive).
	  * @param midIndex ending index for first subarray (exclusive) and starting index for second subarray (inclusive).
	  * @param toIndex ending index for second subarray (exclusive).
	  */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void merge (Comparable[] input, Comparable[] aux, int fromIndex, int midIndex, int toIndex) {
	
		assert isSorted(input, fromIndex, midIndex);
		assert isSorted(input, midIndex, toIndex);
		
		// create auxiliary array
		for (int k = fromIndex; k < toIndex; k++) {
			aux[k] = input[k];
		}
		
		// merge routine
		int i = fromIndex, j = midIndex, sortedIndex = fromIndex;
		while (sortedIndex < toIndex) {
			// check if any subarray done -> copy remaining
			if (i >= midIndex) {
				input[sortedIndex++] = aux[j++];
			}
			else if (j >= toIndex) {
				input[sortedIndex++] = aux[i++];
			}
			// both subarrays not done -> take smaller element
			else if (aux[i].compareTo(aux[j]) <= 0) {
				input[sortedIndex++] = aux[i++];
			}
			else {
				input[sortedIndex++] = aux[j++];
			}
		}
		
		assert isSorted(input, fromIndex, toIndex);
	}
	
	// 
	/** Method to check if input array is sorted between [fromIndex, toIndex>
	  * @param input non-empty array to be checked.
	  * @param fromIndex starting index (inclusive).
	  * @param toIndex ending index (exclusive).
	  * @throws IllegalArgumentException if input is not valid (null or invalid indexes). 
	  */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static boolean isSorted(Comparable[] input, int fromIndex, int toIndex) {
		// input validation
		if (input == null) throw new IllegalArgumentException("Input array cannot be null");
		if (fromIndex < 0 || toIndex > input.length) throw new IllegalArgumentException("Index out of bounds");
		if (fromIndex > toIndex - 1) throw new IllegalArgumentException("Must satisfy fromIndex <= toIndex - 1");
		
		// one element -> sorted
		if (fromIndex == toIndex - 1) {
			return true;
		}
		
		// two elements -> compare the two
		if (fromIndex == toIndex - 2) {
			return input[fromIndex].compareTo(input[toIndex-1]) <= 0;
		}
		
		// more elements -> check if any subsequent pair violates (ascending) sorted order
		for (int i = fromIndex + 1; i < toIndex; i++) {
			if (input[i].compareTo(input[i-1]) < 0) return false;
		}
		
		// no violations found
		return true;
	}
}
