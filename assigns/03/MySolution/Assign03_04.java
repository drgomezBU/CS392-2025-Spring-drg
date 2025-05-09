import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class Assign03_04<T> extends Assign02_04<T> implements Iterable2<T> {

    @Override
    public Iterator<T> iterator() {
        return new ForwardIterator();
    }

    @Override
    public Iterator<T> riterator() {
        return new ReverseIterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        for (T item : this) {
            action.accept(item);
        }
    }

    @Override
    public void rforEach(Consumer<? super T> action) {
        Iterator<T> reverseIterator = riterator();
        while (reverseIterator.hasNext()) {
            action.accept(reverseIterator.next());
        }
    }

    // Forward iterator implementation
    private class ForwardIterator implements Iterator<T> {
        private Node currentNode = front;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = currentNode.data;
            currentNode = currentNode.next;
            return data;
        }
    }

    // Reverse iterator implementation
    private class ReverseIterator implements Iterator<T> {
        private Node currentNode = rear;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = currentNode.data;
            currentNode = currentNode.prev;
            return data;
        }
    }
}
