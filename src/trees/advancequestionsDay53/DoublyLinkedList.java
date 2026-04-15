package trees.advancequestionsDay53;

public class DoublyLinkedList {
    Node head;
    Node tail;

    public TreeNode convert(TreeNode root) {
        if (root == null) {
            return null;
        }

        helper(root);

        return head;
    }

    private void helper(TreeNode node) {
        if(node == null) {
            return null;
        }

        helper(node.left);


        Node newNode = new Node(node.val);

        if(head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        helper(node.right);

    }
}

class Node {
    int val;
    Node prev;
    Node next;

    public Node(int val) {
        this.val = val;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode (int val) {
        this.val = val;
    }
}
