/*
 * PriorityQueue.java
 *
 * Computer Science 392, Boston University
 */

/*
 * A generic class that implements our Queue interface using a linked list.
 */
public class PriorityQueue<T extends Comparable<T>> implements Queue<T> {
    private Heap<T> heap;

    public PriorityQueue(int maxSize) {
        heap = new Heap<>(maxSize);
    }

    @Override
    public boolean insert(T item) {
        return heap.insert(item);
    }

    @Override
    public T remove() {
        return heap.remove();
    }

    @Override
    public T peek() {
        if (heap.isEmpty()) return null;
        return heap.remove(); // Peek is not directly supported in Heap
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public boolean isFull() {
        return false; // Heap can grow dynamically
    }
}
