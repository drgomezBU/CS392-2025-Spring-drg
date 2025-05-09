package finexam.MySolution;

// Breadth-First Search: explores level by level
class BFS<T> {
    public T search(T start, MyPredicate<T> isGoal, MyFunction<T, MyList<T>> expand) {
        MyList<T> queue = new MyList<>();
        queue.add(start);
        return bfsRec(queue, isGoal, expand);
    }
    private T bfsRec(MyList<T> queue, MyPredicate<T> isGoal, MyFunction<T, MyList<T>> expand) {
        if (queue.isEmpty()) {
            return null;
        }
        T node = queue.get(0);
        if (isGoal.test(node)) {
            return node;
        }
        // Remove the first element and explore its children (FIFO order)
        MyList<T> tail = queue.subList(1, queue.size());
        MyList<T> children = expand.apply(node);
        // Create a new queue consisting of the remaining elements followed by all children
        MyList<T> newQueue = new MyList<>(tail.size() + children.size());
        newQueue.addAll(tail);
        newQueue.addAll(children);
        return bfsRec(newQueue, isGoal, expand);
    }
}
