package finexam.MySolution;

// Custom predicate interface (replacement for java.util.function.Predicate)
interface MyPredicate<T> {
    boolean test(T t);
}

// Custom function interface (replacement for java.util.function.Function)
interface MyFunction<T, R> {
    R apply(T t);
}

// Custom dynamic array list implementation (replacement for java.util.List/ArrayList)
class MyList<T> {
    private Object[] data;
    private int size;

    // Default initial capacity to avoid immediate resizing
    public MyList() {
        this(10);
    }

    // Constructor with custom initial capacity
    public MyList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
        this.data = new Object[initialCapacity];
        this.size = 0;
    }

    // Add an element to the end of the list
    public void add(T element) {
        ensureCapacity(size + 1);
        data[size++] = element;
    }

    // Add all elements from another MyList<? extends T> to this list
    public void addAll(MyList<? extends T> other) {
        ensureCapacity(size + other.size());
        for (int i = 0; i < other.size(); i++) {
            // Safe to assign because other holds elements that are T or subclass of T
            @SuppressWarnings("unchecked")
            T element = (T) other.data[i];
            data[size + i] = element;
        }
        size += other.size();
    }

    // Get element at index (throws IndexOutOfBoundsException if index is invalid)
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) data[index];
    }

    // Current number of elements in the list
    public int size() {
        return size;
    }

    // Check if list is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Return a new MyList containing elements from index 'from' (inclusive) to 'to' (exclusive)
    public MyList<T> subList(int from, int to) {
        if (from < 0 || to > size || from > to) {
            throw new IndexOutOfBoundsException("subList indices out of range");
        }
        MyList<T> sub = new MyList<>(to - from);
        for (int i = from; i < to; i++) {
            sub.add(this.get(i));
        }
        return sub;
    }

    // Ensure the internal array has at least the given capacity
    private void ensureCapacity(int minCapacity) {
        if (minCapacity <= data.length) return;
        // Increase capacity (grow to double size or to minCapacity, whichever is larger)
        int newCapacity = Math.max(data.length * 2, minCapacity);
        Object[] newData = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
}
class MyStack<T> {
    private Object[] elements;
    private int size;

    public MyStack() {
        this.elements = new Object[16];
        this.size = 0;
    }

    public void push(T item) {
        ensureCapacity(size + 1);
        elements[size++] = item;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (size == 0) {
            return null;
        }
        return (T) elements[--size];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity <= elements.length) return;
        int newCap = Math.max(elements.length * 2, minCapacity);
        Object[] newArr = new Object[newCap];
        for (int i = 0; i < size; i++) {
            newArr[i] = elements[i];
        }
        elements = newArr;
    }
}
