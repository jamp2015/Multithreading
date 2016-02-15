package com.jamp.multithreading;

import java.util.concurrent.ForkJoinPool;

import com.jamp.multithreading.sorting.DataLoader;
import com.jamp.multithreading.sorting.QuickSortForkStrategy;
import com.jamp.multithreading.sorting.QuickSortStrategy;
import com.jamp.multithreading.sorting.SortingStrategy;

public class App {

	public static final int	NUMBER_OF_ELEMENTS	= 50;
	
	private static ForkJoinPool	forkJoinPool = new ForkJoinPool();

	public static void main(String[] args) {
		
		runWithoutParallelCalculations();
		
		runWithParallelCalculations();

	}
	
	/**
	 * Case without parallel calculations
	 */
	private static void runWithoutParallelCalculations(){
		
		DataLoader dataLoader = new DataLoader(NUMBER_OF_ELEMENTS);

		Integer[] data = new Integer[dataLoader.getArray().size()];
		data = dataLoader.getArray().toArray(data);

		long begin = System.currentTimeMillis();

		SortingStrategy sortingStrategy = new QuickSortStrategy();
		sortingStrategy.sort(data);

		long end = System.currentTimeMillis();

		long dt = end - begin;
		System.out.println("\nThe time spent on sorting (without parallel calculations): " + dt + " ms");
		
		// Uncomment to show the array
		// showArray(data);
	}
	
	/**
	 * Case with parallel calculations
	 */
	private static void runWithParallelCalculations(){
		
		DataLoader dataLoader = new DataLoader(NUMBER_OF_ELEMENTS);

		Integer[] data = new Integer[dataLoader.getArray().size()];
		data = dataLoader.getArray().toArray(data);

		long begin = System.currentTimeMillis();
		
		QuickSortForkStrategy quickSort = new QuickSortForkStrategy(data);
	      
        forkJoinPool.invoke(quickSort);

        long end = System.currentTimeMillis();

        long dt = end - begin;
		System.out.println("\nThe time spent on sorting (with parallel calculations): " + dt + " ms");
		
		// Uncomment to show the array
		// showArray(data);
	}

	/**
	 * Shows an array.
	 *
	 * @param arr
	 */
	public static void showArray(Integer[] arr) {

		System.out.print("[");
		int arrLength = arr.length;

		for (int i = 0; i < arrLength; i++) {
			if (i == arrLength - 1) {
				System.out.print(arr[i] + "]");
			} else {
				System.out.print(arr[i] + ", ");
			}
		}
	}
}
