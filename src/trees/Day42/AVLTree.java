package trees.Day42;

public class AVLTree {

    /*
    ============================================================
    AVL TREE IMPLEMENTATION

    FEATURES:
    - Self-balancing after every insertion
    - Maintains |BF| ≤ 1 for all nodes
    - Guarantees O(log n) operations

    ROTATIONS:
    - Right Rotation (LL case)
    - Left Rotation (RR case)
    - Left-Right Rotation (LR case)
    - Right-Left Rotation (RL case)
    ============================================================
    */

    private Node root;

    public AVLTree() {
    }


    // ============================================================
    // NODE CLASS
    // Same as BST but height is crucial for balance checking
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
    // HEIGHT METHODS
    // ============================================================
    public int height(Node node) {
        if (node == null) {
            return -1;  // Convention: null has height -1
        }
        return node.height;
    }

    public int height() {
        return root.height;
    }


    // ============================================================
    // IS EMPTY
    // ============================================================
    public boolean isEmpty() {
        return root == null;
    }


    // ============================================================
    // INSERT — With automatic balancing
    //
    // STEPS:
    // 1. Insert like regular BST
    // 2. Update height
    // 3. Check balance and rotate if needed
    //
    // TIME: O(log n) — height guaranteed log n
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

        // Balance the node if needed
        return rotate(node);
    }


    // ============================================================
    // ROTATE — Determine which rotation is needed
    //
    // ALGORITHM:
    // 1. Calculate balance factor
    // 2. If left-heavy (BF > 1):
    //    - Check if LL or LR case
    // 3. If right-heavy (BF < -1):
    //    - Check if RR or RL case
    // ============================================================
    private Node rotate(Node node) {
        // Left-heavy (BF > 1)
        if (height(node.left) - height(node.right) > 1) {

            // Left-Left case (left child is also left-heavy)
            if (height(node.left.left) - height(node.left.right) > 0) {
                return rightRotate(node);
            }

            // Left-Right case (left child is right-heavy)
            if (height(node.left.left) - height(node.left.right) < 0) {
                node.left = leftRotate(node.left);  // First left
                return rightRotate(node);            // Then right
            }
        }

        // Right-heavy (BF < -1)
        if (height(node.left) - height(node.right) < -1) {

            // Right-Right case (right child is also right-heavy)
            if (height(node.right.left) - height(node.right.right) < 0) {
                return leftRotate(node);
            }

            // Right-Left case (right child is left-heavy)
            if (height(node.right.left) - height(node.right.right) > 0) {
                node.right = rightRotate(node.right);  // First right
                return leftRotate(node);                // Then left
            }
        }

        // No rotation needed
        return node;
    }


    // ============================================================
    // RIGHT ROTATION (for LL case)
    //
    // BEFORE:       AFTER:
    //     P            C
    //    /            / \
    //   C      →→    G   P
    //  /                  \
    // G                    T
    //
    // STEPS:
    // 1. C becomes new root
    // 2. P becomes right child of C
    // 3. C's right child (T) becomes P's left child
    // 4. Update heights (P first, then C)
    // ============================================================
    private Node rightRotate(Node p) {
        Node c = p.left;       // C = left child of P
        Node t = c.right;      // T = right child of C

        // Perform rotation
        c.right = p;           // P becomes right child of C
        p.left = t;            // T becomes left child of P

        // Update heights (bottom-up: P first, then C)
        p.height = Math.max(height(p.left), height(p.right)) + 1;
        c.height = Math.max(height(c.left), height(c.right)) + 1;

        // Return new root
        return c;
    }


    // ============================================================
    // LEFT ROTATION (for RR case)
    //
    // BEFORE:       AFTER:
    //   C              P
    //    \            / \
    //     P    →→    C   G
    //      \        /
    //       G      T
    //
    // STEPS:
    // 1. P becomes new root
    // 2. C becomes left child of P
    // 3. P's left child (T) becomes C's right child
    // 4. Update heights (C first, then P)
    // ============================================================
    private Node leftRotate(Node c) {
        Node p = c.right;      // P = right child of C
        Node t = p.left;       // T = left child of P

        // Perform rotation
        p.left = c;            // C becomes left child of P
        c.right = t;           // T becomes right child of C

        // Update heights (bottom-up: C first, then P)
        c.height = Math.max(height(c.left), height(c.right)) + 1;
        p.height = Math.max(height(p.left), height(p.right)) + 1;

        // Return new root
        return p;
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
    // POPULATE SORTED — Still works efficiently with AVL!
    //
    // Note: Unlike regular BST, AVL can handle sorted input
    // without becoming skewed thanks to auto-balancing
    // ============================================================
    public void populatedSorted(int[] nums) {
        populatedSorted(nums, 0, nums.length);
    }

    private void populatedSorted(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }

        int mid = (start + end) / 2;

        this.insert(nums[mid]);
        populatedSorted(nums, start, mid);
        populatedSorted(nums, mid + 1, end);
    }


    // ============================================================
    // BALANCED — Check if tree is balanced
    //
    // For AVL, should always return true after insertions
    // (Useful for verification/debugging)
    // ============================================================
    public boolean balanced() {
        return balanced(root);
    }

    private boolean balanced(Node node) {
        if (node == null) {
            return true;
        }

        return Math.abs(height(node.left) - height(node.right)) <= 1
                && balanced(node.left)
                && balanced(node.right);
    }


    // ============================================================
    // DISPLAY
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
}