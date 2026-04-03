package trees.Day41;

public class BST {

    /*
    ============================================================
    BINARY SEARCH TREE (BST) IMPLEMENTATION

    BST PROPERTY:
    - Left subtree values < Node value
    - Right subtree values > Node value
    - Both subtrees are also BSTs

    ADVANTAGES:
    - Search, Insert, Delete: O(log n) average
    - Inorder traversal gives sorted order

    DISADVANTAGES:
    - Can degrade to O(n) if unbalanced
    - Need self-balancing for guaranteed performance

    THIS IMPLEMENTATION TRACKS:
    - Height of each node (for balance checking)
    ============================================================
    */

    private Node root;

    public BST() {
    }


    // ============================================================
    // NODE CLASS — Inner class with height tracking
    // ============================================================
    public class Node {
        private int value;
        private Node left;
        private Node right;
        private int height;

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


    // ============================================================
    // HEIGHT — Get height of a node
    //
    // Height of null = -1 (convention)
    // Height of leaf = 0
    // ============================================================
    public int height(Node node) {
        if (node == null) {
            return -1;
        }
        return node.height;
    }


    // ============================================================
    // IS EMPTY — Check if tree is empty
    // ============================================================
    public boolean isEmpty() {
        return root == null;
    }


    // ============================================================
    // INSERT — Add value to BST
    //
    // ALGORITHM:
    // 1. If tree empty, create root
    // 2. If value < node, go left
    // 3. If value > node, go right
    // 4. Update height after insertion
    //
    // TIME: O(log n) average, O(n) worst case (skewed)
    // ============================================================
    public void insert(int value) {
        root = insert(value, root);
    }

    private Node insert(int value, Node node) {
        // Base case: found empty spot
        if (node == null) {
            node = new Node(value);
            return node;
        }

        // BST property: smaller goes left
        if (value < node.value) {
            node.left = insert(value, node.left);
        }

        // BST property: larger goes right
        if (value > node.value) {
            node.right = insert(value, node.right);
        }

        // Update height after insertion
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        return node;
    }


    // ============================================================
    // POPULATE — Insert array of values
    // ============================================================
    public void populate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            this.insert(nums[i]);
        }
    }


    // ============================================================
    // POPULATE SORTED — Build balanced BST from sorted array
    //
    // ALGORITHM:
    // 1. Find middle element, make it root
    // 2. Recursively build left subtree from left half
    // 3. Recursively build right subtree from right half
    //
    // WHY: Inserting sorted array normally creates skewed tree
    // This ensures balanced tree with O(log n) height
    // ============================================================
    public void populatedSorted(int[] nums) {
        populatedSorted(nums, 0, nums.length);
    }

    private void populatedSorted(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }

        int mid = (start + end) / 2;

        this.insert(nums[mid]);               // Insert middle first
        populatedSorted(nums, start, mid);    // Then left half
        populatedSorted(nums, mid + 1, end);  // Then right half
    }


    // ============================================================
    // BALANCED — Check if tree is height-balanced
    //
    // BALANCED CONDITION:
    // For EVERY node: |height(left) - height(right)| ≤ 1
    //
    // RECURSIVE CHECK:
    // 1. Base case: null is balanced
    // 2. Check current node's balance
    // 3. Recursively check both subtrees
    // ============================================================
    public boolean balanced() {
        return balanced(root);
    }

    private boolean balanced(Node node) {
        if (node == null) {
            return true;
        }

        // Check balance at current node AND all descendants
        return Math.abs(height(node.left) - height(node.right)) <= 1
                && balanced(node.left)
                && balanced(node.right);
    }


    // ============================================================
    // DISPLAY — Preorder traversal with details
    // ============================================================
    public void display() {
        display(this.root, "Root Node: ");
    }

    private void display(Node node, String details) {
        if (node == null) {
            return;
        }

        System.out.println(details + node.value);
        display(node.left, "Left child of " + node.value + " : ");
        display(node.right, "Right child of " + node.value + " : ");
    }
    // ============================================================
    // TREE TRAVERSALS
    // ============================================================

    // PREORDER: Root → Left → Right
    public void preorder(Node node) {
        if (node == null) return;

        System.out.print(node.value + " ");  // Visit root FIRST
        preorder(node.left);                  // Then left
        preorder(node.right);                 // Then right
    }


    // INORDER: Left → Root → Right
    public void inorder(Node node) {
        if (node == null) return;

        inorder(node.left);                   // First left
        System.out.print(node.value + " ");   // Visit root MIDDLE
        inorder(node.right);                  // Then right
    }


    // POSTORDER: Left → Right → Root
    public void postorder(Node node) {
        if (node == null) return;

        postorder(node.left);                 // First left
        postorder(node.right);                // Then right
        System.out.print(node.value + " ");   // Visit root LAST
    }

}

