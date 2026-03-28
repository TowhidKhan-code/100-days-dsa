package linkedList.Day35;

/*
============================================================
DAY 35 - CIRCULAR LINKED LIST
============================================================
*/

public class CLL {
    /*
    ============================================================
    CIRCULAR LINKED LIST

    Definition:
    A circular linked list is a variation of linked list where
    the last node points back to the first node forming a circle.

    Advantages:
    - No null reference at the end
    - Useful for round-robin scheduling, multiplayer games, etc.
    ============================================================
    */

    private Node head;
    private Node tail;

    public CLL() {
        this.head = null;
        this.tail = null;
    }

    // Insert a node
    public void insert(int val) {
        Node node = new Node(val);

        if (head == null) {
            head = node;
            tail = node;
            return;
        }

        tail.next = node;
        node.next = head;
        tail = node;
    }

    // Delete a node with given value
    public void delete(int val) {
        Node node = head;
        if (node == null) {
            return;
        }

        // Only one node
        if (head == tail) {
            head = null;
            tail = null;
            return;
        }

        // Delete head node
        if (node.val == val) {
            head = head.next;
            tail.next = head;
            return;
        }

        // Delete any other node
        do {
            Node n = node.next;
            if (n.val == val) {
                node.next = n.next;
                break;
            }
            node = node.next;
        } while (node != head);
    }

    // Display circular linked list
    public void display() {
        Node node = head;
        if (head != null) {
            do {
                System.out.print(node.val + " -> ");
                node = node.next;
            } while (node != head);
        }
        System.out.println("HEAD");
    }

    private class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }
}