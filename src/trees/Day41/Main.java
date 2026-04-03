package trees.Day41;

import java.util.Scanner;

public class Main {

    /*
    ============================================================
    TREE DEMONSTRATION

    Option 1: Interactive Binary Tree (user input)
    Option 2: BST with array population
    ============================================================
    */

    public static void main(String[] args) {

        // ============================================================
        // Interactive Binary Tree
        // ============================================================
         Scanner scanner = new Scanner(System.in);
         BinaryTree tree = new BinaryTree();
         tree.populate(scanner);
         tree.prettyDisplay();


        // ============================================================
        // BST with array
        // ============================================================
        BST tree2 = new BST();
        int[] nums = {5, 2, 7, 1, 4, 6, 9, 8, 3, 10};
        tree2.populate(nums);
        tree2.display();

        System.out.println("\\nIs tree balanced? " + tree2.balanced());


        // ============================================================
        // Balanced BST from sorted array
        // ============================================================
        BST balancedTree = new BST();
        int[] sorted = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        balancedTree.populatedSorted(sorted);
        balancedTree.display();

        System.out.println("\\nIs balanced tree balanced? " + balancedTree.balanced());
    }
}