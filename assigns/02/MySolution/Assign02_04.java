public class Assign02_04<T> implements Deque<T> {
    private class Node {
        T data;
        Node prev, next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node front, rear;
    private int size;

    public Assign02_04() {
        front = rear = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T takeout_at_beg() {
        if (isEmpty()) return null;
        T data = front.data;
        front = front.next;
        if (front != null) front.prev = null;
        else rear = null;
        size--;
        return data;
    }

    @Override
    public void insert_at_beg(T x) {
        Node newNode = new Node(x);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            newNode.next = front;
            front.prev = newNode;
            front = newNode;
        }
        size++;
    }

    @Override
    public T takeout_at_end() {
        if (isEmpty()) return null;
        T data = rear.data;
        rear = rear.prev;
        if (rear != null) rear.next = null;
        else front = null;
        size--;
        return data;
    }

    @Override
    public void insert_at_end(T x) {
        Node newNode = new Node(x);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            newNode.prev = rear;
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }
}
