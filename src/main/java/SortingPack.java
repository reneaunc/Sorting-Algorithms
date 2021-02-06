import java.util.*;

class SortingPack {
    // just in case you need tis method for testing
    public static void main(String[] args) {
        // something
    }

    // implementation of insertion sort
    // parameters: int array unsortedArr
    // return: sorted int array
    public static int[] insertionSort(int[] unsortedArr) {
        //loop through the whole array
        for (int i = 1; i < unsortedArr.length; i++) {
            int key = unsortedArr[i];
            int j = i - 1;
            //move the element at i to the left until it is in the right spot in the sorted segment
            while (j >= 0 && unsortedArr[j] > key) {
                unsortedArr[j+1] = unsortedArr[j];
                j--;
            }
            unsortedArr[j+1] = key;
            }
        return unsortedArr;
    }

    //wrapper method for quick sort
    public static int[] quickSort(int[] unsortedArr) {
        return quickSort(unsortedArr, 0, unsortedArr.length-1);
    }

    // second quickSort method
    public static int[] quickSort(int[] arr, int first, int last) {
        if (first < last) {

            int splitpoint = partition(arr, first, last);
            quickSort(arr, first, splitpoint-1);
            quickSort(arr, splitpoint+1, last);
        }
        return arr;
    }
    
    // helper method for quickSort
    public static int partition(int[] arr, int first, int last) {
        int pivotValue = arr[first];
        
        int left = first+1;
        int right = last;
        boolean done = false;
        
        
        while (!done) {
            
            //move the right and left pointers until a value on the wrong side of the pivor value is found
            while (left <= right && arr[left] <= pivotValue) {
                left++;
            }
            while (left <= right && arr[right] >= pivotValue) {
                right--;
            }
            //if right and left have swapped then we have checked the whole array
            if (right < left) {
                done = true;
            }
            //otherwise swap right and left to put the values on the correct side of pivot 
            else {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }
        
        //swap right with pivot so the pivot value is in the middle
        arr[first] = arr[right];
        arr[right] = pivotValue;

        return right;
    }
    
    // implementation of merge sort
    // parameters: int array unsortedArr
    // return: sorted int array
    public static int[] mergeSort(int[] unsortedArr) {
        //if the array only has 1 length we have reached the bottom
        if (unsortedArr.length < 2) {
            return unsortedArr;
        } else {
            //split the array into two halves and recursively call mergesort
            
            //split
            int mid = unsortedArr.length/2;
            int[] left = new int[mid];
            int[] right = new int[unsortedArr.length - mid];

            //clone the arrays
            for (int i = 0; i < mid; i++) {
                left[i] = unsortedArr[i];
            }
            for (int i = mid; i < unsortedArr.length; i++) {
                right[i - mid] = unsortedArr[i];
            }

            //call recursive mergesort
            mergeSort(left);
            mergeSort(right);

            //now that we have called merge sort on them left and right are now sorted
            //merfe the left and right array onto unsortedArr
            merge(unsortedArr, left, right);
        }
        return unsortedArr;
    }
    
    // helper method for mergeSort
    public static void merge(int[] arr, int[] left, int[] right) {
        int i = 0;
        int j = 0;
        int k = 0;
        //add the smaller of the two values from the left and right array until we reach the end
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        //dump the rest of whichever array has remaining items
        while (i < left.length) {
            arr[k++] = left[i++];
        }
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }

    // implementation of heap sort
    // parameters: int array unsortedArr
    // return: sorted int array
    public static int[] heapSort(int[] unsortedArr) {
        MaxHeap heap = new MaxHeap(unsortedArr);
        
        int[] sortedArr = new int[unsortedArr.length];
        
        //loop in reverse order, adding the largest element to that part of the sorted list
        for(int i = unsortedArr.length - 1; i >= 0; i--){
            sortedArr[i] = heap.removeMax();
        }

        return sortedArr;
    }
    // you are welcome to add any supporting methods
}
