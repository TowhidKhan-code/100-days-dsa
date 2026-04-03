package trees.Day41;

import java.util.Scanner;

public class BinaryTree {

    /*
    ============================================================
    BINARY TREE IMPLEMENTATION

    FEATURES:
    - Generic node type (can store any data)
    - User input population
    - Display methods (simple and pretty)

    NOTE: This is a GENERAL binary tree
    - No ordering constraint
    - User decides structure via input
    ============================================================
    */

    private Node root;

    public BinaryTree() {
    }


    // ============================================================
    // POPULATE TREE VIA USER INPUT
    //
    // Recursively asks user if they want to add left/right children
    // Builds tree interactively
    // ============================================================
    public <T> void populate(Scanner scanner) {
        System.out.println("Enter the root Node: ");
        T value = (T) (scanner.nextLine());
        root = new Node<>(value);
        populate(scanner, root);
    }

    private <T> void populate(Scanner scanner, Node node) {
        // Ask for left child
        System.out.println("Do you want to enter left of: " + node.value);
        boolean left = scanner.nextBoolean();
        scanner.nextLine();  // Consume newline

        if (left) {
            System.out.println("Enter the value of the left of " + node.value);
            T value = (T) scanner.nextLine();
            node.left = new Node<>(value);
            populate(scanner, node.left);  // Recurse for left subtree
        }

        // Ask for right child
        System.out.println("Do you want to enter right of: " + node.value);
        boolean right = scanner.nextBoolean();
        scanner.nextLine();

        if (right) {
            System.out.println("Enter the value of the right of " + node.value);
            T value = (T) scanner.nextLine();
            node.right = new Node<>(value);
            populate(scanner, node.right);  // Recurse for right subtree
        }
    }


    // ============================================================
    // SIMPLE DISPLAY — Preorder with indentation
    // ============================================================
    public void display() {
        display(root, "");
    }

    private void display(Node node, String indent) {
        if (node == null) return;

        System.out.println(indent + node.value);
        display(node.left, indent + "\\t");   // Add tab for each level
        display(node.right, indent + "\\t");
    }


    // ============================================================
    // PRETTY DISPLAY — Visual tree structure
    //
    // Prints tree rotated 90° counterclockwise
    // Right subtree on top, left on bottom
    // ============================================================
    public void prettyDisplay() {
        prettyDisplay(root, 0);
    }

    private void prettyDisplay(Node node, int level) {
        if (node == null) {
            return;
        }

        // Print right subtree first (appears on top)
        prettyDisplay(node.right, level + 1);

        // Print current node with indentation
        if (level != 0) {
            for (int i = 0; i < level - 1; i++) {
                System.out.print("|\\t\\t");
            }
            System.out.println("|----->" + node.value);
        } else {
            System.out.println(node.value);
        }

        // Print left subtree (appears on bottom)
        prettyDisplay(node.left, level + 1);
    }


    // ============================================================
    // NODE CLASS — Generic inner class
    // ============================================================
    private static class Node<T> {
        private T value;
        private Node left;
        private Node right;

        public Node(T val) {
            this.value = val;
        }
    }
}