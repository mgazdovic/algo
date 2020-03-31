/**
 * This file contains an iterative implementation of quick sort... 
 * Much more fun than simple recursion, see for yourself ;)
 * 
 * Time complexity: 	O(n log n) expected (with high probability -> random pivot)
 * Space complexity: 	O(log n)
 * 		n -> size of array to be sorted
 * 
 * @author Mislav.Gazdovic, mislav.gazdovic@gmail.com
 *
 */

package com.mgazdovic.algo.sorting;

import java.util.Deque;
import java.util.LinkedList;

public class QuickSortIterative {
	
	/** Performs quick sort algorithm (iterative). 
	  * @param input non-empty array to be sorted.
	  * @throws IllegalArgumentException if input is null or contains no elements.
	  */
	public static <T extends Comparable<T>> void sort(T[] input) {
		// input validation
		if (input == null || input.length == 0) throw new IllegalArgumentException("Array must contain at least one element");
		// single element -> sorted
		if (input.length == 1) return;
		
		// create "to do" list of [from, to> pairs
		Deque<IndexPair> todo = new LinkedList<>();
		todo.push(new IndexPair(0, input.length));
		
		// check to do list
		while (!todo.isEmpty()) {
			
			// fetch next from to do list
			IndexPair next = todo.pop();
			int from = next.from, to = next.to;
			
			// partition puts pivot in correct position and divides array into two (potentially unsorted) subarrays
			int partitionIndex = partition(input, from, to);
		
			// add left subarray if there is still something to do
			if (partitionIndex > from) {
				todo.push(new IndexPair(from, partitionIndex));
			}
			// add right subarray if there is still something to do
			if (partitionIndex + 1 < to) {
				todo.push(new IndexPair(partitionIndex + 1, to));
			}
		}
	}
	
	/** Partitions array range [from, to> into two subarrays such that pivot element (at returned index) is in correct position. 
	  * @param input non-empty array to be partitioned.
	  * @param from starting index (inclusive).
	  * @param to ending index (exclusive).
	  * @return index of pivot element which is now in correct sorted position. 
	  */
	private static <T extends Comparable<T>> int partition(T[] input, int from, int to) {
		// move random pivot to last position (randomness improves performance)
		int last = to - 1;
		int randomIndex = from + (int)(Math.random()*(last - from)); // random index in range [from, to>
		int pivotIndex = last;
		arraySwap(input, randomIndex, pivotIndex);
		
		// keep track of partition index
		int partitionIndex = from;
		
		// scan through array and move elements less than pivot
		for(int i = from; i < to; i++) {
			if (input[i].compareTo(input[pivotIndex]) < 0) {
				arraySwap(input, i, partitionIndex++);
			}
		}
		
		// move pivot to correct position
		arraySwap(input, pivotIndex, partitionIndex);
		
		assert isPartitioned(input, from, to, partitionIndex);
		return partitionIndex;
	}
	
	/** Utility method to swap two elements in an array. 
	  * @param input array in which elements are swapped.
	  * @param swapIndex1 index of first element.
	  * @param swapIndex2 index of second element.
	  */
	private static <T> void arraySwap(T[] array, int swapIndex1, int swapIndex2) {
		T temp = array[swapIndex1];
		array[swapIndex1] = array[swapIndex2];
		array[swapIndex2] = temp;
	}
	
	/** Method to check if input array is partitioned between [from, to> around element at partitionIndex. 
	  * @param input non-empty array to be checked.
	  * @param from starting index (inclusive).
	  * @param to ending index (exclusive).
	  * @param partitionIndex index of pivot element around which input array partitioning is checked.
	  * @return true if range is correctly partitioned around element at partition index; false otherwise. 
	  * @throws IllegalArgumentException if input is not valid (null or invalid indexes). 
	  */
	private static <T extends Comparable<T>> boolean isPartitioned(T[] input, int from, int to, int partitionIndex) {
		// input validation
		if (input == null) throw new IllegalArgumentException("Input cannot be null");
		if (from < 0 || to > input.length) throw new IllegalArgumentException("Index out of bounds");
		if (from >= to) throw new IllegalArgumentException("Must satisfy from < to");
		if (partitionIndex < from || partitionIndex >= to) throw new IllegalArgumentException("Invalid partitionIndex");
		
		// scan through array and check left (<) and right (>) side by comparing with partition element
		for (int i = from; i < to; i++) {
			if (i < partitionIndex && input[i].compareTo(input[partitionIndex]) > 0) return false;
			if (i > partitionIndex && input[i].compareTo(input[partitionIndex]) < 0) return false;		
		}
		
		// no violation found
		return true;
	}
	
	// Supplementary data structure for [from, to> index pairs. 
	private static class IndexPair {
		final int from;
		final int to;
		
		public IndexPair(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}
	
	/** Performs quick sort algorithm (recursive). 
	  * @param input non-empty array to be sorted.
	  * @throws IllegalArgumentException if input is null or contains no elements.
	  */
	public static <T extends Comparable<T>> void sortR(T[] input) {
		// input validation
		if (input == null || input.length == 0) throw new IllegalArgumentException("Array must contain at least one element");
			
		// recursive solution (simple but naughty naughty)
		sort(input, 0, input.length);
		
	}
	
	/** Sorting method for recursive implementation of quick sort algorithm. 
	  * @param input non-empty array to be sorted.
	  * @param from starting index (inclusive).
	  * @param to ending index (exclusive).
	  */
	private static <T extends Comparable<T>> void sort(T[] input, int from, int to) {
		// base case -> single element
		if (from >= to - 1) return;
		
		// partition puts pivot in correct position and divides array into two (potentially unsorted) subarrays
		int partitionIndex = partition(input, from, to);
		
		// sort both subarrays
		sort(input, from, partitionIndex);
		sort(input, partitionIndex + 1, to);
	}
}
