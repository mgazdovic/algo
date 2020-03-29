/**
 * This file contains an implementation of bottom-up merge sort. 
 * Merge sort is the most famous divide-and-conquer sorting algorithm.
 * Time complexity: 	O(n log n)
 * Space complexity: 	O(n)
 * 		n -> size of array
 * 
 * @author Mislav.Gazdovic, mislav.gazdovic@gmail.com
 *
 */
package com.mgazdovic.algo.sorting;

/**
 * @author Mislav.Gazdovic, mislav.gazdovic@gmail.com
 *
 */
public class MergeSortBottomUp {
	
	// O(n log n) time, O(n) space
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] input) {
		final int N = input.length;
		Comparable[] aux = new Comparable[N];
		
		// merge all sorted subarrays of size 1, 2, 4, ..., N/2 -> N
		for (int sz = 1; sz<N; sz*=2)
			for (int i = 0; i<N; i+=2*sz)	
				merge(input, aux, i, i+sz, Math.min(i+2*sz, N));
		
		assert isSorted(input, 0, N);
	}
	
	// merge sorted arrays [startIndex, midIndex> and [midIndex, endIndex> to sorted array [startIndex, endIndex>
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void merge (Comparable[] input, Comparable[] aux, int startIndex, int midIndex, int endIndex) {
	
		assert isSorted(input, startIndex, midIndex);
		assert isSorted(input, midIndex, endIndex);
		
		// create auxiliary array
		for (int k = startIndex; k < endIndex; k++) {
			aux[k] = input[k];
		}
		
		// merge routine
		int i = startIndex, j = midIndex, sortedIndex = startIndex;
		while (sortedIndex < endIndex) {
			// check if any subarray done -> copy remaining
			if (i >= midIndex) {
				input[sortedIndex++] = aux[j++];
			}
			else if (j >= endIndex) {
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
		
		assert isSorted(input, startIndex, endIndex);
	}
	
	// method to check if input array is sorted between startIndex and endIndex
	// O(n) time, O(1) space
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static boolean isSorted(Comparable[] input, int startIndex, int endIndex) {
		// input validation
		if (startIndex < 0 || endIndex >= input.length) throw new IllegalArgumentException("Index out of bounds");
		if (startIndex > endIndex) throw new IllegalArgumentException("Must satisfy startIndex <= endIndex");
			
		// single element -> sorted
		if (startIndex == endIndex) return true;
			
		// check if any subsequent pair violates (ascending) sorted order
		for (int i = startIndex + 1; i < endIndex; i++) {
			if (input[i].compareTo(input[i-1]) < 0) return false;
		}
		
		// no violations found
		return true;
	}
}
