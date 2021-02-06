import java.util.*;

class MaxHeap {
    private ArrayList<Integer> data;

    //default constructor
    public MaxHeap(){
        data = new ArrayList<Integer>();
    }

    //construct a max heap from an array of values
    public MaxHeap(int[] arr){
        //create the array list
        data = new ArrayList<Integer>();
        
        //add the array to the arrayList
		for(int value : arr){
			data.add(value);
		}

		//the index of the last node that has at least one child
		int begin = (data.size() / 2) - 1;
		
		//loop backwards from the beginning until zero
		//apply heapify to each node
		for(int i = begin; i >= 0; i--){
			heapify(i);
		}

    }

    public void insert(int element){
        int i = data.size();
		//add the new element to the data
		data.add(element);

		//climb the element up the list until it is larger than its children and smaller than its parent.
		while(data.get(parentOf(i)) < data.get(i)){
			//swap the parent and the child
            
            int temp = data.get( parentOf(i));
			data.set(parentOf(i), data.get(i));
			data.set(i, temp);

			//increment i
			i = parentOf(i);
		}
    }

    public int getMax(){
        if(data.size() == 0){
			throw new RuntimeException("Cannot get from an empty heap.");
		}
		return data.get(0);
    }

    public int removeMax(){
        if(data.size() == 0){
			throw new RuntimeException("Cannot remove from an empty heap.");
		}

        //the current top value will be returned
		int returnValue =  data.get(0);
		
		//place the last item at the top of the heap
		data.set(0, data.get(data.size() - 1));
		data.remove(data.size() - 1);
		
		//restore the max-heap properties.
		heapify(0);

		return returnValue;
    }

    private void heapify(int index){
        //repeat until the the node at index has at least one child
        //or the heap is correct
		boolean finished = false;
		while(childOf(index, 0) < data.size() && !finished){
			
			//the left child is the larges of the two children if the right one doesn't exist
			//or the left child is larger than the right child
			boolean leftChildLargest = (childOf(index, 1) >= data.size()) || (data.get(childOf(index, 0)) > data.get(childOf(index, 1)));
			//set the index to the larger child
			int largestChildIndex = leftChildLargest ? childOf(index, 0) : childOf(index, 1);

			//if the element at index is smaller than its largest child
			if (data.get(index) < data.get(largestChildIndex)){
				//swap the element at index with it's largest child
				int temp = data.get(index);
				data.set(index, data.get(largestChildIndex));
				data.set(largestChildIndex, temp);
			}
			else{
				/* if no swap happened that means the rest of the heap below index
				adhears to the maxheap properties and we can stop */ 
				finished = true;
			}

			//keep progressing down the heap
			index = largestChildIndex;

		}
    }

    //returns the index of the parent from an index
	private int parentOf(int index){
		return (index - 1) / 2;
	}

    //reurns the index of the given child
	private int childOf(int index, int child){
		//child 0 is left and child 1 is right
		if(child > 1 || child < 0){
			throw new RuntimeException(child + " is not a valid child number. It must be either 1 or 0.");
		}
		else{
			return index*2 + 1 + child;
		}


	}
}