/**
 * This file contains two implementations of binary search (recursive and iterative). 
 * 
 * Time complexity: 	O(log n)
 * Space complexity: 	O(1)
 * 		n -> size of array
 * 
 * @author Mislav.Gazdovic, mislav.gazdovic@gmail.com
 *
 */

package com.mgazdovic.algo.searching;

public class BinarySearch {

	/** Performs binary search algorithm on a sorted array (iterative). 
	  * @param items sorted array in which item of interest is searched for.
	  * @param item that is being searched for. 
	  * @return index of (any) found element within input (-1 if not found). 
	  */
	public static <T extends Comparable<T>> int find(T[] items, T item) {
		// check if array contains any elements
		if (items == null || items.length == 0) return -1;
		
		// range [from, to>
		int from = 0;
		int to = items.length;
		
		int current;
		while (from < to) {
			// middle: (from + to) / 2 -> (bit shift is faster and also avoids overflow)
			current = (from+to)>>>1; 
			
			if (items[current].compareTo(item) == 0) {
				// found at current position
				return current;
			}
			if (items[current].compareTo(item) > 0) {
				// next -> [from, current>
				to = current;
			}
			else {
				// next -> [current + 1, to>
				from = current + 1;
			}
		}
		
		// not found
		return -1;
	}
	
	/** Performs binary search algorithm on a sorted array (recursive). 
	  * @param items sorted array in which item of interest is searched for.
	  * @param item that is being searched for. 
	  * @return index of (any) found element within input (-1 if not found). 
	  */
	public static <T extends Comparable<T>> int findR(T[] items, T item) {
		// check if array contains any elements
		if (items == null || items.length == 0) return -1;
		
		return findR(items, item, 0, items.length);
	}
	
	/** Recursive method for binary search algorithm on a sorted array. 
	  * @param items sorted array in which item of interest is searched for.
	  * @param item that is being searched for. 
	  * @param from starting index (inclusive).
	  * @param to ending index (exclusive).
	  * @return index of (any) found element within input (-1 if not found). 
	  */
	private static <T extends Comparable<T>> int findR(T[] items, T item, int from, int to) {
		// not found
		if (from >= to) return -1;
		
		// middle: (from + to) / 2 -> (bit shift is faster and also avoids overflow)
		int current = (from+to)>>>1;
		
		if (items[current].compareTo(item) == 0) {
			// found at current position
			return current;
		}
		if (items[current].compareTo(item) > 0) {
			// next -> [from, current>
			return findR(items, item, from, current);
		}
		else {
			// next -> [current + 1, to>
			return findR(items, item, current + 1, to);
		}
	}
}
