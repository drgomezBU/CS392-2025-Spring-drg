package finexam.MySolution;

// Depth-First Search: explores one branch fully before backtracking
class DFS<T> {
    public T search(T start, MyPredicate<T> isGoal, MyFunction<T, MyList<T>> expand) {
        MyStack<T> stack = new MyStack<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            T node = stack.pop();
            if (isGoal.test(node)) {
                return node;
            }
            MyList<T> children = expand.apply(node);
            // push children in reverse so that the first child is processed first
            for (int i = children.size() - 1; i >= 0; i--) {
                stack.push(children.get(i));
            }
        }

        return null;
    }
}
