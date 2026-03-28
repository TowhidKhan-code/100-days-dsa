package linkedList.Day35;

/*
============================================================
DAY 35 - LINKED LIST (Singly, Doubly & Circular)
============================================================
*/

public class LL {
    /*
    ============================================================
    LINKED LIST

    Definition:
    A Linked List is a linear data structure where elements (nodes)
    are stored in non-contiguous memory locations and each node
    contains a data field and a reference (link) to the next node.

    Advantages over Array:
    - Dynamic size (can grow/shrink at runtime)
    - Efficient insertions and deletions (O(1) if position is known)
    - No memory wastage due to fixed size

    Disadvantages:
    - No random access (must traverse from head)
    - Extra memory for storing pointers/references

    Types of Linked Lists:
    1. Singly Linked List
    2. Doubly Linked List
    3. Circular Linked List
    ============================================================
    */

    private Node head;
    private Node tail;
    private int size;

    public LL() {
        this.size = 0;
    }

    // ============================================================
    // INSERTION OPERATIONS - SINGLY LINKED LIST
    // ============================================================

    // Insert at the beginning - O(1)
    public void insertFirst(int val) {
        Node node = new Node(val);
        node.next = head;
        head = node;

        if (tail == null) {
            tail = head;
        }
        size++;
    }

    // Insert at the end - O(1) because we maintain tail pointer
    public void insertLast(int val) {
        if (tail == null) {
            insertFirst(val);
            return;
        }

        Node node = new Node(val);
        tail.next = node;
        tail = node;
        size++;
    }

    // Insert at a specific index - O(n)
    public void insert(int val, int index) {
        if (index == 0) {
            insertFirst(val);
            return;
        }
        if (index == size) {
            insertLast(val);
            return;
        }

        Node temp = head;
        for (int i = 1; i < index; i++) {
            temp = temp.next;
        }

        Node node = new Node(val, temp.next);
        temp.next = node;
        size++;
    }

    // ============================================================
    // DELETION OPERATIONS
    // ============================================================

    // Delete first node - O(1)
    public int deleteFirst() {
        int val = head.value;
        head = head.next;

        if (head == null) {
            tail = null;
        }
        size--;
        return val;
    }

    // Delete last node - O(n)
    public int deleteLast() {
        if (size <= 1) {
            return deleteFirst();
        }

        Node secondLast = get(size - 2);
        int val = tail.value;
        tail = secondLast;
        tail.next = null;
        size--;
        return val;
    }

    // Delete node at given index - O(n)
    public int delete(int index) {
        if (index == 0) {
            return deleteFirst();
        }
        if (index == size - 1) {
            return deleteLast();
        }

        Node prev = get(index - 1);
        int val = prev.next.value;

        prev.next = prev.next.next;
        size--;
        return val;
    }

    // ============================================================
    // HELPER METHODS
    // ============================================================

    // Find node with given value
    public Node find(int value) {
        Node node = head;
        while (node != null) {
            if (node.value == value) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    // Get node at specific index
    public Node get(int index) {
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    // Display the linked list
    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.value + " -> ");
            temp = temp.next;
        }
        System.out.println("END");
    }

    // Inner Node Class
    private class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
}