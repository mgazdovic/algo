/**
 * This file contains an implementation of bottom-up merge sort. 
 * Merge sort is a famous divide-and-conquer sorting algorithm. 
 * This is a fun optimization over a simple recursive solution which avoids the recursion overhead using bottom-up approach.
 * 
 * Time complexity: 	O(n log n)
 * Space complexity: 	O(n)
 * 		n -> size of array to be sorted
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
		for (int size = 1; size < N; size *= 2)
			for (int i = 0; i < N; i += 2*size)	
				merge(input, aux, i, i + size, Math.min(i + 2*size, N));
		
		assert isSorted(input, 0, N);
	}
	
	/** Merges sorted subarrays [from, mid> and [mid, to> into a sorted array [from, to>
	  * @param input non-empty array to be sorted.
	  * @param aux auxiliary array for merge routine.
	  * @param from starting index for first subarray (inclusive).
	  * @param mid ending index for first subarray (exclusive) and starting index for second subarray (inclusive).
	  * @param to ending index for second subarray (exclusive).
	  */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void merge (Comparable[] input, Comparable[] aux, int from, int mid, int to) {
	
		assert isSorted(input, from, mid);
		assert isSorted(input, mid, to);
		
		// prepare auxiliary array
		for (int k = from; k < to; k++) {
			aux[k] = input[k];
		}
		
		// merge routine
		int i = from, j = mid, sortedIndex = from;
		while (sortedIndex < to) {
			// check if any subarray done -> copy remaining
			if (i >= mid) {
				input[sortedIndex++] = aux[j++];
			}
			else if (j >= to) {
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
		
		assert isSorted(input, from, to);
	}
	
	/** Method to check if input array is sorted between [from, to>
	  * @param input non-empty array to be checked.
	  * @param from starting index (inclusive).
	  * @param to ending index (exclusive).
	  * @throws IllegalArgumentException if input is not valid (null or invalid indexes). 
	  */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static boolean isSorted(Comparable[] input, int from, int to) {
		// input validation
		if (input == null) throw new IllegalArgumentException("Input cannot be null");
		if (from < 0 || to > input.length) throw new IllegalArgumentException("Index out of bounds");
		if (from >= to) throw new IllegalArgumentException("Must satisfy from < to");
				
		// check if any subsequent pair violates (ascending) sorted order
		for (int i = from + 1; i < to; i++) {
			if (input[i].compareTo(input[i-1]) < 0) return false;
		}
		
		// no violations found
		return true;
	}
}
