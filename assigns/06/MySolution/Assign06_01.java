public class Assign06_01 {
    final int R = 0; // for red
    final int B = 1; // for black
    private class Node {
	private int color;
	private Node left;
	private Node right;
    }
    public boolean isRBT(Node root) {
        // A valid red-black tree must have a black root
        if (root != null && root.color == R) {
            return false;
        }
        // Check the tree recursively
        return checkRecursion(root);
    }

    private boolean checkRecursion(Node root) {
        if (root == null) {
            return true; // Null nodes are valid
        }

        // Red node cannot have red children
        if (root.color == R) {
            if ((root.left != null && root.left.color == R) || 
                (root.right != null && root.right.color == R)) {
                return false;
            }
        }

        // Recursively check left and right subtrees
        return checkRecursion(root.left) && checkRecursion(root.right);
    }
}
