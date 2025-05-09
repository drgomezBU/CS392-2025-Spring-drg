/*
 * BU CAS CS 392
 * For generic array-based heap implementation
 * Please finish the code so that HeapTest (given)
 * can use to code here to run.
 */

/**
 * Heap - a generic collection class that implements 
 * a max-at-top heap using an array
 */
public class Heap<T extends Comparable<T>> {
    private T[] contents;
    private int numItems;
    
    public Heap(int maxSize) {
        contents = (T[])new Comparable[maxSize];
        numItems = 0;
    }
    
    public Heap(T[] arr) {
        // Note that we don't copy the array, so that heapsort can
        // sort the array in place.
        contents = arr;
        numItems = arr.length;
        makeHeap();
    }
    
    /* 
     * makeHeap - turn the elements in the contents array into a
     * representation of a max-at-top heap.
     */
    private void makeHeap() {
        for (int i = numItems / 2 - 1; i >= 0; i--) {
            sink(i);
        }
    }
    
    /** 
     * insert - add the specified item to the heap and sift it
     * up into its proper position. It returs true if inserted
     * and false if no more room for insertion
     */
    public boolean insert(T item) {
        if (numItems == contents.length) return false;
        contents[numItems] = item;
        swim(numItems);
        numItems++;
        return true;
    }
    
    /**
     * remove and return the item at the top of the heap, and adjust
     * the remaining items so that we still have a heap.
     */
    public T remove() {
        if (isEmpty()) return null;
        T root = contents[0];
        contents[0] = contents[--numItems];
        sink(0);
        return root;
    }
    
    /**
     * isEmpty - does the heap currently have no items?
     */
    public boolean isEmpty() {
        return (numItems == 0);
    }
    
    /**
     * toString - create a string representation of the heap of the form
     * { ( root ) ( items in level 1 ) ( items in level 2 ) ... }
     */
    public String toString() {
        String str = "{ ";
        
        int start = 0;
        int levelSize = 1;
        while (start < numItems) {
            // print all of the items at the current level of the tree
            str += "( ";
            for (int i = start; i < start + levelSize && i < numItems; i++)
                str += (contents[i] + " ");
            str += ") ";
            
            // move down to the next level
            start += levelSize;
            levelSize *= 2;
        }
        
        str += "}";
        return str;
    }

    private void swim(int i) {
        while (i > 0 && contents[i].compareTo(contents[(i - 1) / 2]) > 0) {
            swap(i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    private void sink(int i) {
        while (2 * i + 1 < numItems) {
            int j = 2 * i + 1;
            if (j + 1 < numItems && contents[j].compareTo(contents[j + 1]) < 0) j++;
            if (contents[i].compareTo(contents[j]) >= 0) break;
            swap(i, j);
            i = j;
        }
    }

    private void swap(int i, int j) {
        T temp = contents[i];
        contents[i] = contents[j];
        contents[j] = temp;
    }
}
