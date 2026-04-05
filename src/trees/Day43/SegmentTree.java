package trees.Day43;

public class SegmentTree {

    /*
    ============================================================
    SEGMENT TREE IMPLEMENTATION

    PURPOSE:
    - Efficient range queries (sum, min, max)
    - Efficient point updates
    - Both operations in O(log n)

    USE CASES:
    - Range sum queries
    - Range minimum/maximum queries
    - Frequency counting in ranges
    - Finding GCD/LCM of range
    ============================================================
    */

    private Node root;

    // ============================================================
    // CONSTRUCTOR — Build tree from array
    // ============================================================
    public SegmentTree(int[] arr) {
        // Create a tree using this array
        this.root = constructTree(arr, 0, arr.length - 1);
    }


    // ============================================================
    // NODE CLASS — Stores range information
    // ============================================================
    private static class Node {
        int data;           // Aggregate value (sum, min, max)
        int startInterval;  // Left boundary of range
        int endInterval;    // Right boundary of range
        Node left;          // Left child
        Node right;         // Right child

        public Node(int startInterval, int endInterval) {
            this.startInterval = startInterval;
            this.endInterval = endInterval;
        }
    }


    // ============================================================
    // CONSTRUCT TREE — Recursive build
    //
    // ALGORITHM:
    // 1. Base case: start == end (leaf node)
    // 2. Recursive: split range, build children
    // 3. Combine children's data
    //
    // TIME: O(n)
    // SPACE: O(n) nodes + O(log n) recursion stack
    // ============================================================
    private Node constructTree(int[] arr, int start, int end) {
        // BASE CASE: Leaf node
        if (start == end) {
            Node leaf = new Node(start, end);
            leaf.data = arr[start];  // Leaf stores single element
            return leaf;
        }

        // RECURSIVE CASE: Internal node
        // Create new node with index range
        Node node = new Node(start, end);
        int mid = (start + end) / 2;

        // Build left subtree: [start, mid]
        node.left = this.constructTree(arr, start, mid);

        // Build right subtree: [mid+1, end]
        node.right = this.constructTree(arr, mid + 1, end);

        // Combine children (for sum)
        node.data = node.left.data + node.right.data;

        return node;
    }


    // ============================================================
    // DISPLAY — Visualize tree structure
    // ============================================================
    public void display() {
        display(this.root);
    }

    private void display(Node node) {
        String str = "";

        // Left child info
        if (node.left != null) {
            str = str + "Interval=[" + node.left.startInterval + "-"
                    + node.left.endInterval + "] and data: "
                    + node.left.data + " => ";
        } else {
            str = str + "No left child ";
        }

        // Current node info
        str = str + "Interval=[" + node.startInterval + "-"
                + node.endInterval + "] and data: " + node.data + " <= ";

        // Right child info
        if (node.right != null) {
            str = str + "Interval=[" + node.right.startInterval + "-"
                    + node.right.endInterval + "] and data: "
                    + node.right.data;
        } else {
            str = str + "No right child ";
        }

        System.out.println(str + '\n');

        // Recurse on children
        if (node.left != null) {
            display(node.left);
        }
        if (node.right != null) {
            display(node.right);
        }
    }

    // ============================================================
// QUERY — Range sum query
//
// Returns sum of elements from index qsi to qei
//
// PARAMETERS:
// qsi = query start index
// qei = query end index
//
// TIME: O(log n)
// ============================================================
    public int query(int qsi, int qei) {
        return this.query(this.root, qsi, qei);
    }

    private int query(Node node, int qsi, int qei) {
        // CASE 1: Complete overlap
        // Node's range [startInterval, endInterval] is completely inside [qsi, qei]
        if (node.startInterval >= qsi && node.endInterval <= qei) {
            return node.data;
        }

        // CASE 2: No overlap
        // Node's range is completely outside [qsi, qei]
        else if (node.startInterval > qei || node.endInterval < qsi) {
            return 0;  // No contribution
        }

        // CASE 3: Partial overlap
        // Some part of node's range overlaps with [qsi, qei]
        else {
            int leftSum = this.query(node.left, qsi, qei);
            int rightSum = this.query(node.right, qsi, qei);
            return leftSum + rightSum;
        }
    }

    // ============================================================
// UPDATE — Update value at index
//
// PARAMETERS:
// index = position to update
// value = new value to set
//
// PROCESS:
// 1. Find leaf node representing index
// 2. Update leaf
// 3. Propagate changes up to root
//
// TIME: O(log n)
// ============================================================
    public void update(int index, int value) {
        this.root.data = update(this.root, index, value);
    }

    private int update(Node node, int index, int value) {
        // Check if index is within this node's range
        if (node.startInterval <= index && node.endInterval >= index) {

            // BASE CASE: Leaf node (found the element to update)
            if (index == node.startInterval && index == node.endInterval) {
                node.data = value;
                return node.data;
            }

            // RECURSIVE CASE: Internal node
            else {
                // Update children (only one path will actually change)
                int leftAns = update(node.left, index, value);
                int rightAns = update(node.right, index, value);

                // Recalculate current node from children
                node.data = leftAns + rightAns;
                return node.data;
            }
        }

        // Index not in this subtree, return unchanged
        return node.data;
    }
}