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
	public static <T extends Comparable<T>> void sort(T[] input) {
		// input validation
		if (input == null || input.length == 0) throw new IllegalArgumentException("Array must contain at least one element");
		
		final int N = input.length;
		T[] aux = input.clone();
		
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
	private static <T extends Comparable<T>> void merge (T[] input, T[] aux, int from, int mid, int to) {
	
		assert isSorted(input, from, mid);	// first subarray
		assert isSorted(input, mid, to);	// second subarray
		
		// prepare auxiliary array
		for (int i = from; i < to; i++) {
			aux[i] = input[i];
		}
		
		// merge routine
		int firstIndex = from, secondIndex = mid, sortedIndex = from;
		while (sortedIndex < to) {
			// check if any subarray done -> copy remaining
			if (firstIndex >= mid) {
				input[sortedIndex++] = aux[secondIndex++];
			}
			else if (secondIndex >= to) {
				input[sortedIndex++] = aux[firstIndex++];
			}
			// both subarrays not done -> take smaller element
			else if (aux[firstIndex].compareTo(aux[secondIndex]) <= 0) {
				input[sortedIndex++] = aux[firstIndex++];
			}
			else {
				input[sortedIndex++] = aux[secondIndex++];
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
	private static <T extends Comparable<T>> boolean isSorted(T[] input, int from, int to) {
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
