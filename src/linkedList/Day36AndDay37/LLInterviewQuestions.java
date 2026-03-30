package linkedList.Day36AndDay37;

public class LLInterviewQuestions {

    /*
    ============================================================
    DAY 36 - LINKED LIST INTERVIEW QUESTIONS: LEETCODE SPECIFIC ANSWERS
    Floyd's Algorithm
    DAY 37 -
    Reversal Problems, Palindrome, Rotation

    Companies: Google, Amazon, Microsoft, Facebook, Apple, LinkedIn

    LeetCode Problems Covered:
    - LC 141: Linked List Cycle
    - LC 142: Linked List Cycle II
    - LC 202: Happy Number
    - LC 876: Middle of the Linked List
    DAY 37
    - LC 206: Reverse Linked List
    - LC 92:  Reverse Linked List II
    - LC 25:  Reverse Nodes in K-Group
    - LC 234: Palindrome Linked List
    - LC 143: Reorder List
    - LC 61:  Rotate List
    ============================================================
    */


    // ============================================================
    // LC 141 — LINKED LIST CYCLE
    // Detect if linked list has a cycle
    //
    // FLOYD'S CYCLE DETECTION (Tortoise & Hare):
    // - slow moves 1 step, fast moves 2 steps
    // - If cycle exists → they meet inside cycle
    // - If no cycle → fast reaches null
    //
    // WHY THEY MEET:
    // - Relative speed = 1 step per iteration
    // - Fast gains on slow, gap decreases by 1 each time
    // - Eventually gap = 0 → they meet
    //
    // TIME: O(n)
    // SPACE: O(1)
    // ASKED BY: Amazon, Microsoft
    // ============================================================
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;  // 2 steps
            slow = slow.next;       // 1 step

            if (fast == slow) {
                return true;  // cycle detected
            }
        }
        return false;  // fast reached end — no cycle
    }


    // ============================================================
    // FIND LENGTH OF CYCLE
    // After detecting cycle, count nodes in it
    //
    // ALGORITHM:
    // 1. Detect cycle (find meeting point)
    // 2. Keep one pointer fixed at meeting point
    // 3. Move other pointer counting steps until it returns
    //
    // WHY do-while:
    // - Must execute at least once before checking condition
    // - temp starts at slow, we need to go around once
    //
    // TIME: O(n)
    // SPACE: O(1)
    // ============================================================
    public int lengthCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                // Calculate the length from meeting point
                ListNode temp = slow;
                int length = 0;

                do {
                    temp = temp.next;
                    length++;
                } while (temp != slow);

                return length;
            }
        }
        return 0;  // no cycle
    }


    // ============================================================
    // LC 142 — LINKED LIST CYCLE II
    // Find the node where cycle begins
    //
    // ALGORITHM:
    // 1. Detect cycle and find its length
    // 2. Place two pointers at head
    // 3. Move one pointer ahead by 'length' steps
    // 4. Move both one step at a time → they meet at cycle start
    //
    // WHY THIS WORKS:
    // - Second pointer is exactly one cycle ahead
    // - When first enters cycle, second is one full loop ahead
    // - In a cycle, one loop ahead = same position
    // - They meet at the entrance!
    //
    // TIME: O(n)
    // SPACE: O(1)
    // ============================================================
    public ListNode detectCycle(ListNode head) {
        int length = 0;

        ListNode fast = head;
        ListNode slow = head;

        // Step 1: Detect cycle and find length
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                length = lengthCycle(slow);
                break;
            }
        }

        // No cycle found
        if (length == 0) {
            return null;
        }

        // Step 2: Find the start node
        ListNode f = head;
        ListNode s = head;

        // Move s ahead by cycle length
        while (length > 0) {
            s = s.next;
            length--;
        }

        // Move both until they meet at cycle start
        while (f != s) {
            f = f.next;
            s = s.next;
        }

        return s;
    }


    // ============================================================
    // LC 202 — HAPPY NUMBER
    // Repeatedly sum squares of digits until 1 or cycle
    //
    // KEY INSIGHT:
    // The sequence forms an IMPLICIT linked list!
    // Each number "points to" its sum-of-squares
    //
    // Use Floyd's Algorithm:
    // - If they meet at 1 → happy
    // - If they meet elsewhere → cycle → not happy
    //
    // EXAMPLE (Happy):
    // 19 → 82 → 68 → 100 → 1 ✓
    //
    // EXAMPLE (Not Happy):
    // 2 → 4 → 16 → 37 → 58 → 89 → 145 → 42 → 20 → 4... (cycle)
    //
    // TIME: O(log n)
    // SPACE: O(1)
    // ASKED BY: Google
    // ============================================================
    public boolean isHappy(int n) {
        int slow = n;
        int fast = n;

        do {
            slow = findSquare(slow);                    // 1 step
            fast = findSquare(findSquare(fast));        // 2 steps
        } while (slow != fast);

        return slow == 1;
    }

    // Helper: Calculate sum of squares of digits
    private int findSquare(int number) {
        int ans = 0;
        while (number > 0) {
            int rem = number % 10;
            ans += rem * rem;
            number /= 10;
        }
        return ans;
    }


    // ============================================================
    // LC 876 — MIDDLE OF THE LINKED LIST
    // Find middle node of linked list
    //
    // ALGORITHM:
    // - slow moves 1 step, fast moves 2 steps
    // - When fast reaches end, slow is at middle
    //
    // FOR EVEN LENGTH:
    // Returns second middle node
    // [1,2,3,4,5,6] → returns 4
    //
    // TIME: O(n)
    // SPACE: O(1)
    // ============================================================
    public ListNode middleNode(ListNode head) {
        ListNode s = head;
        ListNode f = head;

        while (f != null && f.next != null) {
            s = s.next;        // 1 step
            f = f.next.next;   // 2 steps
        }

        return s;
    }


//    DAY 37
    // ============================================================
    // LC 206 — REVERSE LINKED LIST
    // Reverse entire linked list
    //
    // THREE POINTER TECHNIQUE:
    // - prev: previous node (starts null)
    // - present: current node being processed
    // - next: next node (saved before reversing)
    //
    // PROCESS:
    // 1. Save next node
    // 2. Reverse current link (point to prev)
    // 3. Move prev and present forward
    // 4. Move next forward (if exists)
    //
    // VISUAL:
    // 1 → 2 → 3 → NULL
    // NULL ← 1 ← 2 ← 3
    //
    // TIME: O(n)
    // SPACE: O(1)
    // ASKED BY: Google, Apple, Amazon, Microsoft
    // ============================================================
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode prev = null;
        ListNode present = head;
        ListNode next = present.next;

        while (present != null) {
            present.next = prev;    // reverse link
            prev = present;         // move prev forward
            present = next;         // move present forward

            if (next != null) {
                next = next.next;   // move next forward
            }
        }

        return prev;  // new head
    }


    // ============================================================
    // LC 92 — REVERSE LINKED LIST II
    // Reverse list from position left to right
    //
    // ALGORITHM:
    // 1. Skip first (left-1) nodes
    // 2. Save 'last' (node before reversal) and 'newEnd' (first node to reverse)
    // 3. Reverse nodes from left to right
    // 4. Reconnect: last → reversed part → rest of list
    //
    // EDGE CASES:
    // - left == right: no reversal needed
    // - left == 1: head changes
    //
    // VISUAL:
    // 1 → 2 → 3 → 4 → 5, left=2, right=4
    // 1 → 4 → 3 → 2 → 5
    //
    // TIME: O(n)
    // SPACE: O(1)
    // ASKED BY: Google, Microsoft, Facebook
    // ============================================================
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) {
            return head;
        }

        // Skip the first left-1 nodes
        ListNode current = head;
        ListNode prev = null;

        for (int i = 0; current != null && i < left - 1; i++) {
            prev = current;
            current = current.next;
        }

        // Save pointers for reconnection
        ListNode last = prev;       // node before reversal section
        ListNode newEnd = current;  // will become end of reversed section

        // Reverse between left and right
        ListNode next = current.next;

        for (int i = 0; current != null && i < right - left + 1; i++) {
            current.next = prev;
            prev = current;
            current = next;

            if (next != null) {
                next = next.next;
            }
        }

        // Reconnect
        if (last != null) {
            last.next = prev;    // connect part before reversal
        } else {
            head = prev;         // reversal started from head
        }

        newEnd.next = current;   // connect reversed part to rest

        return head;
    }


    // ============================================================
    // LC 25 — REVERSE NODES IN K-GROUP
    // Reverse every k nodes in the list
    //
    // ALGORITHM:
    // 1. Calculate total length
    // 2. Determine how many complete groups of k exist
    // 3. For each group: reverse k nodes, reconnect
    //
    // KEY INSIGHT:
    // - Only reverse COMPLETE groups
    // - Remaining nodes (< k) stay in original order
    //
    // VISUAL:
    // 1 → 2 → 3 → 4 → 5, k=2
    // 2 → 1 → 4 → 3 → 5
    //
    // TIME: O(n)
    // SPACE: O(1)
    // ASKED BY: Google, Amazon, Facebook, Microsoft
    // ============================================================
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 1 || head == null) {
            return head;
        }

        ListNode current = head;
        ListNode prev = null;

        int length = getLength(head);
        int count = length / k;  // number of complete groups

        while (count > 0) {
            ListNode last = prev;
            ListNode newEnd = current;

            // Reverse k nodes
            ListNode next = current.next;

            for (int i = 0; current != null && i < k; i++) {
                current.next = prev;
                prev = current;
                current = next;

                if (next != null) {
                    next = next.next;
                }
            }

            // Reconnect
            if (last != null) {
                last.next = prev;
            } else {
                head = prev;
            }

            newEnd.next = current;
            prev = newEnd;
            count--;
        }

        return head;
    }

    // Helper: Get length of linked list
    public int getLength(ListNode head) {
        ListNode node = head;
        int length = 0;

        while (node != null) {
            length++;
            node = node.next;
        }

        return length;
    }


    // ============================================================
    // REVERSE ALTERNATE K-GROUP
    // Reverse first k nodes, skip next k, repeat
    //
    // ALGORITHM:
    // 1. Reverse k nodes
    // 2. Skip next k nodes
    // 3. Repeat until end
    //
    // VISUAL:
    // 1 → 2 → 3 → 4 → 5 → 6 → 7 → 8, k=2
    // 2 → 1 → 3 → 4 → 6 → 5 → 7 → 8
    //  reverse  skip   reverse  skip
    //
    // TIME: O(n)
    // SPACE: O(1)
    // SOURCE: GeeksForGeeks
    // ============================================================
    public ListNode reverseAlternateKGroup(ListNode head, int k) {
        if (k <= 1 || head == null) {
            return head;
        }

        ListNode current = head;
        ListNode prev = null;

        while (current != null) {
            ListNode last = prev;
            ListNode newEnd = current;

            // Reverse k nodes
            ListNode next = current.next;

            for (int i = 0; current != null && i < k; i++) {
                current.next = prev;
                prev = current;
                current = next;

                if (next != null) {
                    next = next.next;
                }
            }

            // Reconnect
            if (last != null) {
                last.next = prev;
            } else {
                head = prev;
            }

            newEnd.next = current;

            // Skip the next k nodes (no reversal)
            for (int i = 0; current != null && i < k; i++) {
                prev = current;
                current = current.next;
            }
        }

        return head;
    }


    // ============================================================
    // LC 234 — PALINDROME LINKED LIST
    // Check if linked list is a palindrome
    //
    // ALGORITHM:
    // 1. Find middle of list
    // 2. Reverse second half
    // 3. Compare first half with reversed second half
    // 4. (Optional) Restore original list by re-reversing
    //
    // WHY RESTORE:
    // - Good practice to not modify input permanently
    // - May be required in some interview scenarios
    //
    // VISUAL:
    // 1 → 2 → 2 → 1
    // First half: 1 → 2
    // Second half reversed: 1 → 2
    // Compare: 1==1 ✓, 2==2 ✓ → Palindrome!
    //
    // TIME: O(n)
    // SPACE: O(1)
    // ASKED BY: LinkedIn, Google, Facebook, Microsoft, Amazon, Apple
    // ============================================================
    public boolean isPalindrome(ListNode head) {
        ListNode mid = middleNode(head);
        ListNode headSecond = reverseList(mid);
        ListNode rereverseHead = headSecond;  // save for restoration

        // Compare both halves
        while (head != null && headSecond != null) {
            if (head.val != headSecond.val) {
                break;
            }
            head = head.next;
            headSecond = headSecond.next;
        }

        // Restore original list
        reverseList(rereverseHead);

        // If either pointer reached null, it's palindrome
        return head == null || headSecond == null;
    }


    // ============================================================
    // LC 143 — REORDER LIST
    // Reorder: L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → ...
    //
    // ALGORITHM:
    // 1. Find middle of list
    // 2. Reverse second half
    // 3. Merge first half and reversed second half alternately
    //
    // VISUAL:
    // 1 → 2 → 3 → 4 → 5
    // First half: 1 → 2 → 3
    // Second half reversed: 5 → 4
    // Merge: 1 → 5 → 2 → 4 → 3
    //
    // TIME: O(n)
    // SPACE: O(1)
    // ASKED BY: Google, Facebook
    // ============================================================
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        ListNode mid = middleNode(head);
        ListNode hs = reverseList(mid);  // head of second half
        ListNode hf = head;              // head of first half

        // Merge alternately
        while (hf != null && hs != null) {
            ListNode temp = hf.next;
            hf.next = hs;
            hf = temp;

            temp = hs.next;
            hs.next = hf;
            hs = temp;
        }

        // Set tail's next to null
        if (hf != null) {
            hf.next = null;
        }
    }


    // ============================================================
    // LC 61 — ROTATE LIST
    // Rotate list to the right by k places
    //
    // ALGORITHM:
    // 1. Find length and last node
    // 2. Connect last to head (make circular)
    // 3. Calculate actual rotations needed (k % length)
    // 4. Find new tail (length - rotations steps from head)
    // 5. Break circle at new tail
    //
    // KEY INSIGHT:
    // Rotating right by k = moving tail to (length - k) position
    //
    // VISUAL:
    // 1 → 2 → 3 → 4 → 5, k=2
    // Rotate right by 2: 4 → 5 → 1 → 2 → 3
    //
    // TIME: O(n)
    // SPACE: O(1)
    // ASKED BY: Facebook, Twitter, Google
    // ============================================================
    public ListNode rotateRight(ListNode head, int k) {
        if (k <= 0 || head == null || head.next == null) {
            return head;
        }

        // Step 1: Find length and last node
        ListNode last = head;
        int length = 1;

        while (last.next != null) {
            last = last.next;
            length++;
        }

        // Step 2: Make circular
        last.next = head;

        // Step 3: Calculate rotations
        int rotations = k % length;
        int skip = length - rotations;

        // Step 4: Find new tail
        ListNode newLast = head;

        for (int i = 0; i < skip - 1; i++) {
            newLast = newLast.next;
        }

        // Step 5: Break circle and set new head
        head = newLast.next;
        newLast.next = null;

        return head;
    }


    // ============================================================
    // MAIN METHOD FOR TESTING
    // ============================================================
    public static void main(String[] args) {
        LLInterviewQuestions solution = new LLInterviewQuestions();

        // Test Happy Number
        System.out.println("Is 19 happy? " + solution.isHappy(19));  // true
        System.out.println("Is 2 happy? " + solution.isHappy(2));    // false

        // Create test list: 1 → 2 → 3 → 4 → 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        // Test Middle
        ListNode mid = solution.middleNode(head);
        System.out.println("Middle element: " + mid.val);  // 3

        // Test Reverse
        System.out.print("Original: ");
        printList(head);

        head = solution.reverseList(head);
        System.out.print("Reversed: ");
        printList(head);

        // Test Palindrome
        ListNode palindrome = new ListNode(1);
        palindrome.next = new ListNode(2);
        palindrome.next.next = new ListNode(2);
        palindrome.next.next.next = new ListNode(1);
        System.out.println("Is palindrome? " + solution.isPalindrome(palindrome));  // true
    }

    // Helper method to print list
    private static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " → ");
            current = current.next;
        }
        System.out.println("NULL");
    }
}


// ============================================================
// LISTNODE CLASS
// Standard LeetCode ListNode definition
// ============================================================
class ListNode {
    int val;
    ListNode next;

    public ListNode() {
    }

    ListNode(int x) {
        val = x;
        next = null;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}