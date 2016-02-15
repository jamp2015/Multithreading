package com.jamp.multithreading.sorting;

import java.util.concurrent.RecursiveAction;

public class QuickSortForkStrategy extends RecursiveAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 181089793551967870L;
	private Integer[]	numbers;
	private int	      number;
	private int low;
	private int high;
	
	/**
	 * Default constructor.
	 * 
	 * @param values
	 */
	public QuickSortForkStrategy(Integer[] values) {
		// check for empty or null array
		if (values == null || values.length == 0) {
			return;
		}
		
		this.numbers = values;
		number = values.length;
		this.low = 0;
		this.high = number - 1;		
	}
	
	/**
	 * Constructor.
	 * 
	 * @param values
	 * @param low
	 * @param high
	 */
	public QuickSortForkStrategy(Integer[] values, int low, int high) {
		// check for empty or null array
		if (values == null || values.length == 0) {
			return;
		}
		
		this.numbers = values;
		number = values.length;
		this.low = low;
		this.high = high;		
	}

	@Override
	protected void compute() {
		
		int i = low, j = high;
		
		// Get the pivot element from the middle of the list
		int pivotIndex = low + (high - low) / 2;
		int pivotValue = numbers[pivotIndex];
		
	
		// Divide into two lists
		while (i <= j) {
			// If the current value from the left list is smaller then the pivot
			// element then get the next element from the left list
			while (numbers[i] < pivotValue) {
				i++;
			}
			// If the current value from the right list is larger then the pivot
			// element then get the next element from the right list
			while (numbers[j] > pivotValue) {
				j--;
			}

			// If we have found a values in the left list which is larger then
			// the pivot element and if we have found a value in the right list
			// which is smaller then the pivot element then we exchange the
			// values.
			// As we are done we can increase i and j
			if (i <= j) {
				exchange(i, j);
				i++;
				j--;
			}
		}
		
	/*	invokeAll(new QuickSort2(numbers, low, pivotIndex - 1));
		invokeAll(new QuickSort2(numbers, pivotIndex + 1 , high));*/
		
		// Recursion
		if (low < j)
		{
			invokeAll(new QuickSortForkStrategy(numbers, low, j));
		}
		if (i < high){
			invokeAll(new QuickSortForkStrategy(numbers, i, high));
		}
		
		}

	private void exchange(int i, int j) {
	  if (i != j){
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	  }
	}
}
