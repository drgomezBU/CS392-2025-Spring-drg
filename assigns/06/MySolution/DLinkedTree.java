import java.util.*;

public class DLinkedTree {

    /* ************************ ************************ */
    // HX-2025-04-17: For Assignment 6-3 (100 points)
    /* ************************ ************************ */
    
    // An inner class for the nodes in the tree
    private class Node {
        private int key;         // the key field
        private int size;        // the size of the subtree rooted at this node
        private LLList data;     // list of data for this key
        private Node left;       // reference to the left child
        private Node right;      // reference to the right child
        private Node parent;     // reference to the parent node
        
        private Node(int key, Object data) {
            this.key = key;
            this.data = new LLList();
            this.data.addItem(data, 0);
            this.left = null;
            this.right = null;
            this.parent = null;
            this.size = 1; // Initially, the size of the subtree is 1 (the node itself)
        }
    }
    
    /* ************************ ************************ */

    private Node root;

    /* ************************ ************************ */

    // Helper method to update the size of a node's subtree
    private void updateSize(Node node) {
        if (node != null) {
            int leftSize = (node.left != null) ? node.left.size : 0;
            int rightSize = (node.right != null) ? node.right.size : 0;
            node.size = 1 + leftSize + rightSize;
        }
    }

    // Insert a key and associated data into the tree
    public void insert(int key, Object data) {
        root = insert(root, key, data, null);
    }

    private Node insert(Node node, int key, Object data, Node parent) {
        if (node == null) {
            Node newNode = new Node(key, data);
            newNode.parent = parent;
            return newNode;
        }
        if (key < node.key) {
            node.left = insert(node.left, key, data, node);
        } else if (key > node.key) {
            node.right = insert(node.right, key, data, node);
        } else {
            node.data.addItem(data, 0); // Add data to the existing key
        }
        updateSize(node);
        return node;
    }

    // Delete a key from the tree
    public LLList delete(int key) {
        Node[] result = delete(root, key);
        root = result[0];
        return (result[1] != null) ? result[1].data : null;
    }

    private Node[] delete(Node node, int key) {
        if (node == null) return new Node[]{null, null};

        Node deletedNode = null;
        if (key < node.key) {
            Node[] result = delete(node.left, key);
            node.left = result[0];
            deletedNode = result[1];
        } else if (key > node.key) {
            Node[] result = delete(node.right, key);
            node.right = result[0];
            deletedNode = result[1];
        } else {
            deletedNode = node;
            if (node.left == null) {
                return new Node[]{node.right, deletedNode};
            } else if (node.right == null) {
                return new Node[]{node.left, deletedNode};
            } else {
                Node successor = findMin(node.right);
                node.key = successor.key;
                node.data = successor.data;
                Node[] result = delete(node.right, successor.key);
                node.right = result[0];
            }
        }
        updateSize(node);
        return new Node[]{node, deletedNode};
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // Re-root the tree at the given node
    public void reRoot(int key) {
        Node target = search(root, key);
        if (target != null) {
            root = reRootHelper(target);
            root.parent = null;
        }
    }

    private Node reRootHelper(Node node) {
        if (node.parent == null) {
            return node;
        }
        Node parent = node.parent;
        if (node == parent.left) {
            parent.left = null;
        } else {
            parent.right = null;
        }
        updateSize(parent);

        node.parent = null;
        if (parent.parent != null) {
            Node grandParent = parent.parent;
            if (parent == grandParent.left) {
                grandParent.left = node;
            } else {
                grandParent.right = node;
            }
            node.parent = grandParent;
        }

        if (parent.left != null) {
            parent.left.parent = node;
        }
        if (parent.right != null) {
            parent.right.parent = node;
        }

        node.left = parent.left;
        node.right = parent.right;
        parent.left = null;
        parent.right = null;

        updateSize(node);
        return node;
    }

    // Search for a node with the given key
    private Node search(Node node, int key) {
        if (node == null || node.key == key) {
            return node;
        }
        if (key < node.key) {
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }
    }

    // In-order traversal for testing
    public void inorderPrint() {
        inorderPrint(root);
        System.out.println();
    }

    private void inorderPrint(Node node) {
        if (node != null) {
            inorderPrint(node.left);
            System.out.print(node.key + " ");
            inorderPrint(node.right);
        }
    }

    public static void main(String[] args) {
        DLinkedTree tree = new DLinkedTree();
        tree.insert(10, "data10");
        tree.insert(5, "data5");
        tree.insert(15, "data15");
        tree.insert(3, "data3");
        tree.insert(7, "data7");

        System.out.println("In-order before re-root:");
        tree.inorderPrint();

        tree.reRoot(5);

        System.out.println("In-order after re-root:");
        tree.inorderPrint();
    }
}
