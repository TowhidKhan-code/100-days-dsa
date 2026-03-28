package linkedList.Day35;

/*
============================================================
DAY 35 - DOUBLY LINKED LIST
============================================================
*/

public class DLL {
    /*
    ============================================================
    DOUBLY LINKED LIST

    Definition:
    Each node contains three fields: previous pointer, data, and next pointer.
    Allows traversal in both forward and backward directions.

    Advantages:
    - Can traverse backward easily
    - Deletion is easier (no need to find previous node)
    ============================================================
    */

    private Node head;

    // Insert at first position
    public void insertFirst(int val) {
        Node node = new Node(val);
        node.next = head;
        node.prev = null;

        if (head != null) {
            head.prev = node;
        }
        head = node;
    }

    // Insert at last position
    public void insertLast(int val) {
        Node node = new Node(val);
        node.next = null;

        if (head == null) {
            node.prev = null;
            head = node;
            return;
        }

        Node last = head;
        while (last.next != null) {
            last = last.next;
        }

        last.next = node;
        node.prev = last;
    }

    // Insert after a given value
    public void insert(int after, int val) {
        Node p = find(after);
        if (p == null) {
            System.out.println("Node does not exist");
            return;
        }

        Node node = new Node(val);
        node.next = p.next;
        p.next = node;
        node.prev = p;

        if (node.next != null) {
            node.next.prev = node;
        }
    }

    // Find node with given value
    public Node find(int value) {
        Node node = head;
        while (node != null) {
            if (node.val == value) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    // Display forward and reverse
    public void display() {
        Node node = head;
        Node last = null;

        System.out.print("Forward : ");
        while (node != null) {
            System.out.print(node.val + " -> ");
            last = node;
            node = node.next;
        }
        System.out.println("END");

        System.out.print("Reverse : ");
        while (last != null) {
            System.out.print(last.val + " -> ");
            last = last.prev;
        }
        System.out.println("START");
    }

    private class Node {
        int val;
        Node next;
        Node prev;

        public Node(int val) {
            this.val = val;
        }
    }
}