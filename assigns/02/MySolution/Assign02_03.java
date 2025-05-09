@SuppressWarnings("unchecked")
public class Assign02_03<T> implements Deque<T> {
    T[] deque;
    int front;
    int rear;
    private int size;
    int capacity;

    public Assign02_03() {
        capacity = 10;
        deque = (T[]) new Object[capacity];
        front = -1;
        rear = 0;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T takeout_at_beg() {
        if (isEmpty()) return null;
        T item = deque[front];
        front = (front + 1) % capacity;
        size--;
        return item;
    }

    @Override
    public void insert_at_beg(T x) {
        if (isFull()) doubleCapacity();
        front = (front - 1 + capacity) % capacity;
        deque[front] = x;
        size++;
    }

    @Override
    public T takeout_at_end() {
        if (isEmpty()) return null;
        rear = (rear - 1 + capacity) % capacity;
        T item = deque[rear];
        size--;
        return item;
    }

    @Override
    public void insert_at_end(T x) {
        if (isFull()) doubleCapacity();
        deque[rear] = x;
        rear = (rear + 1) % capacity;
        size++;
    }

    private void doubleCapacity() {
        T[] newDeque = (T[]) new Object[capacity * 2];
        for (int i = 0; i < size; i++) {
            newDeque[i] = deque[(front + i) % capacity];
        }
        deque = newDeque;
        front = 0;
        rear = size;
        capacity *= 2;
    }
}
