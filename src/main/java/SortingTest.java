import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.LongAccumulator;

public class SortingTest{
	public static void main(String[] args) throws IOException{
		//Initializes output stream to file with given path
		FileOutputStream fos = new FileOutputStream("../../../time.csv");
		DataOutputStream dos = new DataOutputStream(fos);
		//Writes neccesary info to csv file
		dos.writeChars("Length of Array");
        dos.writeChars(",");
        dos.writeChars("Insertion Sort");
        dos.writeChars(",");
		dos.writeChars("Quick Sort");
		dos.writeChars(",");
		dos.writeChars("Merge Sort");
		dos.writeChars(",");
		dos.writeChars("Heap Sort");
		dos.writeChars("\n");
		//Runs each sorting algorithm and increments the given length n by 1000 each time
		for(int i = 100; i <= 100000; i += 1000){
			//Generates a new array random array of size i to run through each algorithm
			int[] arr1 = randomArray(i);
			double time_insertionSort_1 = sortingTime("insertionSort", arr1);
			double time_quickSort_1 = sortingTime("quickSort", arr1);
			double time_mergeSort_1 = sortingTime("mergeSort", arr1);
			double time_heapSort_1 = sortingTime("heapSort", arr1);
			int[] arr2 = randomArray(i);
			double time_insertionSort_2 = sortingTime("insertionSort", arr2);
			double time_quickSort_2 = sortingTime("quickSort", arr2);
			double time_mergeSort_2 = sortingTime("mergeSort", arr2);
			double time_heapSort_2 = sortingTime("heapSort", arr2);
			int[] arr3 = randomArray(i);
			double time_insertionSort_3 = sortingTime("insertionSort", arr3);
			double time_quickSort_3 = sortingTime("quickSort", arr3);
			double time_mergeSort_3 = sortingTime("mergeSort", arr3);
			double time_heapSort_3 = sortingTime("heapSort", arr3);
			//Calculates average time of each sorting algorithm by adding all 3 times per algorith and dividing by 3
			double time_insertionSort = ((time_insertionSort_1 + time_insertionSort_2 + time_insertionSort_3) / 3.0);
			double time_heapSort = ((time_heapSort_1 + time_heapSort_2 + time_heapSort_3) / 3.0);
			double time_mergeSort = ((time_mergeSort_1 + time_mergeSort_2 + time_mergeSort_3) / 3.0);
			double time_quickSort = ((time_quickSort_1 + time_quickSort_2 + time_quickSort_3) / 3.0);
			// Writes results to csv file
			dos.writeChars(Integer.toString(i));
			dos.writeChars(",");
			dos.writeChars(Double.toString(time_insertionSort));
			dos.writeChars(",");
			dos.writeChars(Double.toString(time_quickSort));
			dos.writeChars(",");
			dos.writeChars(Double.toString(time_mergeSort));
			dos.writeChars(",");
			dos.writeChars(Double.toString(time_heapSort));
			dos.writeChars("\n");
    	}
		dos.close();
}

	// this method generate a random int array with the length as size
	// each random int should be in [0, 10000]
	// parameters: size
	// return data: int[]
	public static int[] randomArray(int size) {
		Random rand = new Random();
        int[] array = new int[size];
        for(int i = 0; i < array.length; i++){
            //ensures that randomly generated int is between 0 inclusive and 1001 exclusive
            array[i] = rand.nextInt(1001);
        }
        return array;
	}

	// this method counts the time it takes to sort arr using sortingAlgorithm
	// parameters: sortingAlgorithm and int array
	// return data: double
	public static double sortingTime(String sortingAlgorithm, int[] arr){
		long startTime = 0;
		long endTime = 0;
		/*Checks if sorting algorithm being used is correct
			and times take the start and end time in nanoseconds */
		if(sortingAlgorithm.equals("insertionSort")){
			startTime = System.nanoTime();
			SortingPack.insertionSort(arrCoppy);
			endTime = System.nanoTime();
		}
		else if(sortingAlgorithm.equals("quickSort")){
			startTime = System.nanoTime();
			SortingPack.quickSort(arrCoppy);
			endTime = System.nanoTime();
		}
		else if(sortingAlgorithm.equals("mergeSort")){
			startTime = System.nanoTime();
			SortingPack.mergeSort(arrCoppy);
			endTime = System.nanoTime();
		}
		else if(sortingAlgorithm.equals("heapSort")){
			startTime = System.nanoTime();
			SortingPack.heapSort(arrCoppy);
			endTime = System.nanoTime();
		}
		else{
			System.out.println("fail");
		}
		long totalTime = endTime - startTime;
		return totalTime;
	}
}

  // you are welcome to add any supporting methods
