package com.jamp.multithreading.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DataLoader implements DataDao {

	private int	          arraySize	= 10;
	private int	          min	    = 0;
	private int	          max	    = 100;

	private List<Integer>	array;

	public DataLoader() {
		initializeRandom();
	}

	public DataLoader(int arraySize) {
		this.arraySize = arraySize;
		initializeRandom();
	}

	@Override
	public List<Integer> getArray() {
		return array;
	}

	/**
	 * Initializes the array with random values.
	 */
	public void initializeRandom() {
		array = new ArrayList<Integer>();

		for (int i = 0; i < arraySize; i++) {
			array.add(ThreadLocalRandom.current().nextInt(min, max + 1));
		}
	}
}
