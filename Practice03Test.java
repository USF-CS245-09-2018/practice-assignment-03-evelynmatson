import java.util.Random;
import java.util.Arrays;


public class Practice03Test {

	protected int count = 0;
	protected double [] arr;


	/**
	 * Constructor
	 */
	public Practice03Test (String [] args) {
		try {
			count = Integer.parseInt(args[0]);
		} catch (Exception e) {
			System.out.println("Defaulting array size to 20.");
			count = 20;
		}
		arr = new double[count];
		generate_array();
	}


	/**
	 * print_array: prints the array of doubles... formatted so it fits
	 * ... on many small screens.
	 */
	public void print_array() {
		System.out.println("------------------------------------");
		System.out.println("Array contains the values:");
		for (int i = 0; i < arr.length; i++) {
			System.out.printf("%.2f ", arr[i]);
			if (i > 0 && i % 9 == 0)
				System.out.println();
		}
		System.out.println("\n------------------------------------");
	}


	/**
	 * Fills the array with random double instances.
	 */
	public void generate_array() {
		Random rand = new Random();
		double min = 1.0;
		double max = 100.0;

		for (int i = 0; i < arr.length; i++) {
			arr[i] = min + rand.nextDouble() * (max-min);
		}
	}


	public int find_min_iterative () {
		int maxSoFar = 0;
		for (int i=1;i < this.arr.length;i++) {
			if (this.arr[i] < this.arr[maxSoFar]) {
				maxSoFar=i;
			}
		}
		return maxSoFar;
	}


	public int find_min_recursive () {
		return myBetterFindMinRecursive(this.arr);
	}

	// Made my own function to do this
	private int myFindMinRecursive(double[] arr, int maxIndexSearched, int indexWhereMinValueFound) {
		//base  case:
		if (arr.length-1 <= maxIndexSearched) {
			return indexWhereMinValueFound;
		}

		// Iterative step:
		else {
			if (arr[maxIndexSearched+1] < arr[indexWhereMinValueFound]) {
				return(myFindMinRecursive(arr,maxIndexSearched+1,maxIndexSearched+1));
			} else {
				return (myFindMinRecursive(arr,maxIndexSearched+1,indexWhereMinValueFound));
			}
		}
	}

	private int myBetterFindMinRecursive(double[] arr) {

		// Base Case: (if arr has 1 or fewer elements)
		if (arr.length==1) {
			return 0;
		} else if (arr.length==0) {
			return -1;
		}

		// Iterative step:
		double[] array1 = Arrays.copyOfRange(arr, 0, arr.length/2);
		double[] array2 = Arrays.copyOfRange(arr, arr.length/2, arr.length);

		// DEBUG CODE
		// System.out.println("array1 = " + Arrays.toString(array1));
		// System.out.println("array2 = " + Arrays.toString(array2));

		int minFirstHalf = myBetterFindMinRecursive(array1);
		int minSecondHalf = myBetterFindMinRecursive(array2);

		// DEBUG CODE
		// System.out.println("minFirstHalf (array1) = " + minFirstHalf);
		// System.out.println("minSecondHalf (array2) = " + minSecondHalf);

		if (array1[minFirstHalf] > array2[minSecondHalf]) {
			return minSecondHalf+arr.length/2;
		} else {
			return minFirstHalf;
		}

		
	}


	/**
	 * print_min: determines the min iteratively and recursively.
	 * ... and prints them both.
	 */
	public void print_min() {
		System.out.println("Iteratively determined min at index " + find_min_iterative());
		System.out.println("Recursively determined min at index " + find_min_recursive());
	}


	/**
	 * main for Practice 03: print the array and determine the min.
	 */
	public static void main(String [] args) {
		Practice03Test test = new Practice03Test(args);
		test.print_array();
		test.print_min();
	}

}
