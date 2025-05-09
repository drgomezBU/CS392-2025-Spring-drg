public class Assign06_02 {
    private static class Node<T> {
        // Node structure for 2-3-trees
        // A 2-node has 2 children, and a 3-node has 3 children
        private T left, right; // Values stored in the node
        private Node<T> nLeft, nCenter, nRight; // Child nodes
    }

    public boolean is23T(Node<?> root) {
        // Recursive implementation to check if the tree is a valid 2-3-tree
        if (root == null) {
            return true; // An empty tree is valid
        }

        // Check if the node is a 2-node
        if (root.right == null) {
            // A 2-node should not have a right child
            if (root.nRight != null) {
                return false;
            }
            // Recursively check the left and center children
            return is23T(root.nLeft) && is23T(root.nCenter);
        }

        // If the node is a 3-node, recursively check all children
        return is23T(root.nLeft) && is23T(root.nCenter) && is23T(root.nRight);
    }
}
