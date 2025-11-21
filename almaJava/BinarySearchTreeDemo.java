/**
 * Binary Search Tree Implementation - In-order Traversal Only
 */
class BSTNode {
    private int data;
    private BSTNode left, right;

    public BSTNode(int value) {
        data = value;
        left = right = null;
    }

    public int getData() { return data; }
    public void setData(int data) { this.data = data; }
    public BSTNode getLeft() { return left; }
    public void setLeft(BSTNode left) { this.left = left; }
    public BSTNode getRight() { return right; }
    public void setRight(BSTNode right) { this.right = right; }
}

class BinarySearchTree {
    private BSTNode root;

    public BinarySearchTree() {
        root = null;
    }

    /**
     * Insert a new node into the BST
     */
    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    private BSTNode insertRecursive(BSTNode node, int value) {
        if (node == null) {
            return new BSTNode(value);
        }

        if (value < node.getData()) {
            node.setLeft(insertRecursive(node.getLeft(), value));
        } else if (value > node.getData()) {
            node.setRight(insertRecursive(node.getRight(), value));
        }

        return node;
    }

    /**
     * In-order Traversal (Left, Root, Right)
     * Produces sorted output for BST
     */
    public void inorderTraversal() {
        System.out.print("In-order Traversal: ");
        inorderRecursive(root);
        System.out.println();
    }

    private void inorderRecursive(BSTNode node) {
        if (node != null) {
            inorderRecursive(node.getLeft());
            System.out.print(node.getData() + " ");
            inorderRecursive(node.getRight());
        }
    }
}

/**
 * Demo class to show in-order traversal
 */
public class BinarySearchTreeDemo {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        // Insert nodes into BST
        System.out.println("=== INSERTING VALUES INTO BST ===");
        int[] values = {65, 90, 23, 56, 34, 67, 54, 23, 17, 86, 49}; 
        
        for (int value : values) {
            bst.insert(value);
            System.out.println("Inserted: " + value);
        }

        System.out.println("\n=== IN-ORDER TRAVERSAL ===");
        // Display in-order traversal (sorted order)
        bst.inorderTraversal();

        System.out.println("\n=== PROGRAM EXECUTION COMPLETE ===");
    }
}

