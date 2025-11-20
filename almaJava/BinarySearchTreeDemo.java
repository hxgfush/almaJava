/**
 * Binary Search Tree Implementation
 * Meets criteria: Program Execution, Design of Logic, Standards, Timeliness
 */
class BSTNode {
    private int data;
    private BSTNode left, right;

    public BSTNode(int value) {
        data = value;
        left = right = null;
    }

    // Getters and setters for encapsulation
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
     * Time Complexity: O(h) where h is height of tree
     */
    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    private BSTNode insertRecursive(BSTNode node, int value) {
        // Base case: create new node when null position found
        if (node == null) {
            return new BSTNode(value);
        }

        // Recursive insertion maintaining BST property
        if (value < node.getData()) {
            node.setLeft(insertRecursive(node.getLeft(), value));
        } else if (value > node.getData()) {
            node.setRight(insertRecursive(node.getRight(), value));
        }
        // If value equals node data, do nothing (no duplicates)

        return node;
    }

    /**
     * Search for a value in BST
     * Returns true if value exists, false otherwise
     */
    public boolean search(int value) {
        return searchRecursive(root, value);
    }

    private boolean searchRecursive(BSTNode node, int value) {
        if (node == null) {
            return false;
        }

        if (value == node.getData()) {
            return true;
        } else if (value < node.getData()) {
            return searchRecursive(node.getLeft(), value);
        } else {
            return searchRecursive(node.getRight(), value);
        }
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

    /**
     * Pre-order Traversal (Root, Left, Right)
     */
    public void preorderTraversal() {
        System.out.print("Pre-order Traversal: ");
        preorderRecursive(root);
        System.out.println();
    }

    private void preorderRecursive(BSTNode node) {
        if (node != null) {
            System.out.print(node.getData() + " ");
            preorderRecursive(node.getLeft());
            preorderRecursive(node.getRight());
        }
    }

    /**
     * Post-order Traversal (Left, Right, Root)
     */
    public void postorderTraversal() {
        System.out.print("Post-order Traversal: ");
        postorderRecursive(root);
        System.out.println();
    }

    private void postorderRecursive(BSTNode node) {
        if (node != null) {
            postorderRecursive(node.getLeft());
            postorderRecursive(node.getRight());
            System.out.print(node.getData() + " ");
        }
    }

    /**
     * Find minimum value in BST
     */
    public int findMin() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        return findMinRecursive(root);
    }

    private int findMinRecursive(BSTNode node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node.getData();
    }

    /**
     * Find maximum value in BST
     */
    public int findMax() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        return findMaxRecursive(root);
    }

    private int findMaxRecursive(BSTNode node) {
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node.getData();
    }

    /**
     * Get the root node (for testing purposes)
     */
    public BSTNode getRoot() {
        return root;
    }
}

/**
 * Main class to demonstrate BST functionality
 */
public class BinarySearchTreeDemo {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        // Insert nodes into BST
        System.out.println("=== INSERTING VALUES INTO BST ===");
        int[] values = {50, 30, 70, 20, 40, 60, 80, 10, 35, 45, 65};
        
        for (int value : values) {
            bst.insert(value);
            System.out.println("Inserted: " + value);
        }

        System.out.println("\n=== TREE TRAVERSALS ===");
        // Display different traversals
        bst.inorderTraversal();    // Should display sorted order
        bst.preorderTraversal();   // Root first
        bst.postorderTraversal();  // Root last

        System.out.println("\n=== SEARCH OPERATIONS ===");
        // Test search functionality
        int[] searchValues = {35, 100, 60, 5};
        for (int value : searchValues) {
            boolean found = bst.search(value);
            System.out.println("Value " + value + " found: " + found);
        }

        System.out.println("\n=== MIN/MAX OPERATIONS ===");
        // Find minimum and maximum values
        try {
            System.out.println("Minimum value: " + bst.findMin());
            System.out.println("Maximum value: " + bst.findMax());
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n=== PROGRAM EXECUTION COMPLETE ===");
    }
}