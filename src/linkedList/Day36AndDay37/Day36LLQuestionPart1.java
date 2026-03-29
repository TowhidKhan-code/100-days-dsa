package linkedList.Day36AndDay37;

public class Day36LLQuestionPart1 {

    /*
    ============================================================
    DAY 36 - LINKED LIST PROBLEMS
    Cycle Detection, Middle Element, Merge Lists, Remove Duplicates
    LeetCode: 83, 21, 141, 142, 202, 876

    FLOYD'S CYCLE DETECTION (Tortoise & Hare):
    - Two pointers: slow moves 1 step, fast moves 2 steps
    - If cycle exists → they will meet inside the cycle
    - If no cycle → fast reaches null
    - WHY they meet: fast gains 1 step per iteration on slow

    FINDING MIDDLE ELEMENT:
    - Same concept: when fast reaches end, slow is at middle
    - For even length: returns second middle element
    ============================================================
    */


    private Node head;
    private Node tail;
    private int size;

    public Day36LLQuestionPart1() {
        this.size = 0;
    }


    // ============================================================
    // INSERTION OPERATIONS - SINGLY LINKED LIST
    // ============================================================


    // ============================================================
    // Insert at the beginning — O(1)
    //
    // STEPS:
    // 1. Create new node
    // 2. Point new node's next to current head
    // 3. Update head to new node
    // 4. If list was empty, update tail also
    // ============================================================
    public void insertFirst(int val) {
        Node node = new Node(val);
        node.next = head;
        head = node;

        if (tail == null) {
            tail = head;
        }
        size++;
    }


    // ============================================================
    // Insert at the end — O(1) because we maintain tail pointer
    //
    // WITHOUT tail pointer: O(n) — must traverse entire list
    // WITH tail pointer: O(1) — direct access to last node
    // ============================================================
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


    // ============================================================
    // Insert at a specific index — O(n)
    //
    // STEPS:
    // 1. Handle edge cases (index 0 or index == size)
    // 2. Traverse to node at (index - 1)
    // 3. Insert new node between (index-1) and (index)
    //
    // WHY traverse to index-1:
    // We need to update the next pointer of previous node
    // ============================================================
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
    // Insertion with Recursion — O(n)
    //
    // CONCEPT:
    // - Recursively traverse until index becomes 0
    // - At index 0, insert new node and return it
    // - While backtracking, reconnect the links
    //
    // RECURSION PATTERN:
    // node.next = insertRec(val, --index, node.next)
    // This rebuilds the list with new node inserted
    // ============================================================
    public void insertRec(int val, int index) {
        head = insertRec(val, index, head);
    }

    private Node insertRec(int val, int index, Node node) {
        if (index == 0) {
            Node temp = new Node(val, node);
            size++;
            return temp;
        }
        node.next = insertRec(val, --index, node.next);
        return node;
    }


    // ============================================================
    // DELETION OPERATIONS
    // ============================================================


    // ============================================================
    // Delete first node — O(1)
    //
    // STEPS:
    // 1. Save value to return
    // 2. Move head to next node
    // 3. If list becomes empty, update tail to null
    // ============================================================
    public int deleteFirst() {
        int val = head.value;
        head = head.next;

        if (head == null) {
            tail = null;
        }
        size--;
        return val;
    }


    // ============================================================
    // Delete last node — O(n)
    //
    // WHY O(n) for singly linked list:
    // Must traverse to second-last node to update its next pointer
    //
    // DOUBLY LINKED LIST: O(1) — can access prev directly
    // ============================================================
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


    // ============================================================
    // Delete node at given index — O(n)
    //
    // STEPS:
    // 1. Handle edge cases (first or last node)
    // 2. Get node at (index - 1)
    // 3. Skip over the node to delete
    //    prev.next = prev.next.next
    // ============================================================
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


    // Find node with given value — O(n)
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


    // Get node at specific index — O(n)
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


    // ============================================================
    // LEETCODE 83 — Remove Duplicates from Sorted List
    // Given sorted linked list, delete all duplicates
    //
    // CONCEPT:
    // Since list is SORTED, duplicates are adjacent
    // If current.value == current.next.value → skip next node
    // Else → move to next node
    //
    // TIME: O(n) — single pass
    // SPACE: O(1) — no extra space
    //
    // VISUAL:
    // 1 -> 1 -> 2 -> 3 -> 3 -> NULL
    //      ↓ skip
    // 1 -> 2 -> 3 -> NULL
    // ============================================================
    public void removeDuplicates() {
        Node node = head;
        while (node.next != null) {
            if (node.value == node.next.value) {
                node.next = node.next.next;  // skip duplicate
                size--;
            } else {
                node = node.next;            // move forward
            }
        }
        tail = node;
        tail.next = null;
    }


    // ============================================================
    // LEETCODE 21 — Merge Two Sorted Lists
    // Merge two sorted linked lists into one sorted list
    //
    // CONCEPT (Two Pointer):
    // Compare heads of both lists
    // Take smaller value and add to result
    // Move pointer of list from which value was taken
    //
    // AFTER LOOP:
    // One list might have remaining elements
    // Add all remaining elements from non-empty list
    //
    // TIME: O(n + m) where n, m are lengths of lists
    // SPACE: O(n + m) for new list (can be O(1) if we reuse nodes)
    //
    // VISUAL:
    // List1: 1 -> 3 -> 5
    // List2: 2 -> 4 -> 6
    // Result: 1 -> 2 -> 3 -> 4 -> 5 -> 6
    // ============================================================
    public static Day36LLQuestionPart1 merge(Day36LLQuestionPart1 list1, Day36LLQuestionPart1 list2) {
        Node head1 = list1.head;
        Node head2 = list2.head;
        Day36LLQuestionPart1 ans = new Day36LLQuestionPart1();

        while (head1 != null && head2 != null) {
            if (head1.value < head2.value) {
                ans.insertLast(head1.value);
                head1 = head1.next;
            } else {
                ans.insertLast(head2.value);
                head2 = head2.next;
            }
        }

        // Add remaining elements from list1
        while (head1 != null) {
            ans.insertLast(head1.value);
            head1 = head1.next;
        }

        // Add remaining elements from list2
        while (head2 != null) {
            ans.insertLast(head2.value);
            head2 = head2.next;
        }

        return ans;
    }


    // ============================================================
    // LEETCODE 141 — Linked List Cycle Detection
    // Detect if linked list has a cycle
    //
    // FLOYD'S CYCLE DETECTION (Tortoise & Hare Algorithm):
    // - slow pointer moves 1 step at a time
    // - fast pointer moves 2 steps at a time
    // - If cycle exists → they will eventually meet
    // - If no cycle → fast will reach null
    //
    // WHY THEY MEET:
    // In each iteration, fast gains 1 step on slow
    // If there's a cycle, fast will "lap" slow
    // Distance between them decreases by 1 each step
    //
    // TIME: O(n)
    // SPACE: O(1) — only two pointers
    //
    // ASKED BY: Amazon, Microsoft
    //
    // VISUAL:
    // 1 -> 2 -> 3 -> 4
    //           ↑    ↓
    //           6 <- 5
    // slow: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 3...
    // fast: 1 -> 3 -> 5 -> 3 -> 5 -> 3...
    // They meet at some point in the cycle
    // ============================================================
    public static boolean hasCycle(Node head) {
        Node f = head;  // fast pointer
        Node s = head;  // slow pointer

        while (f != null && f.next != null) {
            f = f.next.next;  // move 2 steps
            s = s.next;       // move 1 step

            if (f == s) {
                return true;  // cycle detected — pointers met
            }
        }
        return false;  // fast reached end — no cycle
    }


    // ============================================================
    // Length of Cycle in Linked List
    // Find the length of cycle if it exists
    //
    // STEPS:
    // 1. Detect cycle using Floyd's algorithm
    // 2. Once pointers meet, keep one pointer fixed
    // 3. Move other pointer counting steps until back to meeting point
    //
    // TIME: O(n)
    // SPACE: O(1)
    // ============================================================
    public static int lengthOfCycle(Node head) {
        Node f = head;
        Node s = head;

        while (f != null && f.next != null) {
            f = f.next.next;
            s = s.next;

            if (f == s) {
                // Cycle found — now count length
                Node temp = s;
                int length = 0;
                do {
                    temp = temp.next;
                    length++;
                } while (temp != s);
                return length;
            }
        }
        return 0;  // no cycle
    }


    // ============================================================
    // LEETCODE 142 — Linked List Cycle II
    // Find the node where cycle begins
    //
    // ALGORITHM:
    // 1. Detect cycle and find length using above method
    // 2. Place two pointers at head
    // 3. Move second pointer ahead by 'length' steps
    // 4. Now move both pointers 1 step at a time
    // 5. They will meet at the start of cycle
    //
    // WHY THIS WORKS:
    // When second pointer is 'length' steps ahead,
    // both pointers are equidistant from cycle start
    // (one from outside, one from inside the cycle)
    //
    // ALTERNATIVE APPROACH:
    // After detection, place one pointer at head
    // Keep other at meeting point
    // Move both 1 step — they meet at cycle start
    // (Mathematical proof involves modular arithmetic)
    //
    // TIME: O(n)
    // SPACE: O(1)
    // ============================================================
    public Node startNode(Node head) {
        int length = 0;
        Node f = head;
        Node s = head;

        // Step 1: Find cycle and its length
        while (f != null && f.next != null) {
            f = f.next.next;
            s = s.next;
            if (f == s) {
                length = lengthOfCycle(s);
                break;
            }
        }

        if (length == 0) {
            return null;  // no cycle
        }

        // Step 2: Find the start node
        Node first = head;
        Node second = head;

        // Move second pointer ahead by cycle length
        while (length > 0) {
            second = second.next;
            length--;
        }

        // Move both until they meet — meeting point is cycle start
        while (first != second) {
            first = first.next;
            second = second.next;
        }
        return second;
    }


    // ============================================================
    // LEETCODE 202 — Happy Number
    // A number is happy if repeatedly summing squares of digits leads to 1
    // If not happy, it enters an infinite cycle
    //
    // INSIGHT:
    // The sequence of sums forms a linked list!
    // 19 → 82 → 68 → 100 → 1 (happy — ends at 1)
    // 2 → 4 → 16 → 37 → 58 → 89 → 145 → 42 → 20 → 4... (cycle!)
    //
    // USE FLOYD'S ALGORITHM:
    // - slow computes sum once per step
    // - fast computes sum twice per step
    // - If they meet at 1 → happy
    // - If they meet elsewhere → cycle → not happy
    //
    // TIME: O(log n) — number of digits is log₁₀(n)
    // SPACE: O(1)
    //
    // WHY THIS IS A LINKED LIST PROBLEM:
    // Each number points to its sum-of-squares
    // It's an implicit linked list!
    // ============================================================
    public static boolean isHappy(int n) {
        int slow = n;
        int fast = n;

        do {
            slow = findSquare(slow);             // one step
            fast = findSquare(findSquare(fast)); // two steps
        } while (slow != fast);

        // If they meet at 1, it's happy
        return slow == 1;
    }

    // Helper: Sum of squares of digits
    private static int findSquare(int n) {
        int ans = 0;
        while (n > 0) {
            int rem = n % 10;
            ans += rem * rem;
            n = n / 10;
        }
        return ans;
    }


    // ============================================================
    // LEETCODE 876 — Middle of the Linked List
    // Find middle node of linked list
    //
    // FLOYD'S ALGORITHM VARIATION:
    // - slow moves 1 step
    // - fast moves 2 steps
    // - When fast reaches end, slow is at middle
    //
    // FOR EVEN LENGTH:
    // Returns second middle node
    // [1,2,3,4,5,6] → returns node with value 4
    //
    // TO GET FIRST MIDDLE (for even length):
    // Change condition to: while(f.next != null && f.next.next != null)
    //
    // TIME: O(n)
    // SPACE: O(1)
    //
    // VISUAL:
    // 1 -> 2 -> 3 -> 4 -> 5 -> NULL
    // s         s         s
    // f              f              f (null)
    // When fast reaches end, slow is at 3 (middle)
    // ============================================================
    public Node middleNode(Node head) {
        Node s = head;  // slow
        Node f = head;  // fast

        while (f != null && f.next != null) {
            f = f.next.next;  // move 2 steps
            s = s.next;       // move 1 step
        }
        return s;  // slow is at middle
    }


    // ============================================================
    // INNER NODE CLASS
    // ============================================================
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


    // ============================================================
    // MAIN METHOD FOR TESTING
    // ============================================================
    public static void main(String[] args) {
        // Test Remove Duplicates
        Day36LLQuestionPart1 list = new Day36LLQuestionPart1();
        list.insertLast(1);
        list.insertLast(1);
        list.insertLast(2);
        list.insertLast(3);
        list.insertLast(3);
        System.out.println("Before removing duplicates:");
        list.display();
        list.removeDuplicates();
        System.out.println("After removing duplicates:");
        list.display();

        System.out.println();

        // Test Merge Two Lists
        Day36LLQuestionPart1 list1 = new Day36LLQuestionPart1();
        list1.insertLast(1);
        list1.insertLast(3);
        list1.insertLast(5);

        Day36LLQuestionPart1 list2 = new Day36LLQuestionPart1();
        list2.insertLast(2);
        list2.insertLast(4);
        list2.insertLast(6);

        System.out.println("List 1:");
        list1.display();
        System.out.println("List 2:");
        list2.display();

        Day36LLQuestionPart1 merged = Day36LLQuestionPart1.merge(list1, list2);
        System.out.println("Merged:");
        merged.display();

        System.out.println();

        // Test Happy Number
        System.out.println("Is 19 happy? " + isHappy(19));  // true
        System.out.println("Is 2 happy? " + isHappy(2));    // false
    }
}