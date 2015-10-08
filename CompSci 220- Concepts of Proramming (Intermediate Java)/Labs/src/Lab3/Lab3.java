package

Lab3;

public

class Lab3 {

	/**
	
	* @param args
	
	*/
	
	public static void main(String[] args) {
	
		// 2.1 Declare a constant MAX_SIZE with initial value = 1000
		
		final int MAX_SIZE = 1000;
		// 2.2 Declare two arrays of integers: firstArray,
		// and secondArray. Both arrays have MAX_SIZE elements
		// Initialize both arrays. Values for array elements
		// are randomly generated and are from 0 to MAX_SIZE.
		// Two arrays should have identical values.
		// You may want to use Math.random() method to generate a random number.
		
		int firstArray[] = new int[MAX_SIZE];
		int secondArray[] = new int[MAX_SIZE];
		for (int i=0; i< firstArray.length; i++) {
			firstArray[i] = (int) Math.random() * MAX_SIZE;
			secondArray[i] = firstArray[i];
		}
		
		
		//2.3
		MergeSort ms = new MergeSort();
		SelectionSort ss = new SelectionSort();
		
		//2.4
		System.out.println("Array Size is " + firstArray.length);
		
		//2.5
		
		long startTime, duration;
		startTime = System.currentTimeMillis();
		ms.sort(firstArray, 0, firstArray.length-1);
		 duration = System.currentTimeMillis() -startTime;
		 System.out.println("Time taken to merge Sort: " + duration + "ms");
		 startTime = System.currentTimeMillis();
		 ss.sort(secondArray);
		 duration = System.currentTimeMillis() -startTime;
		 System.out.println("Time taken to selection Sort: " + duration + "ms");


	}
}