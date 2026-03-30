package linkedList.Day36AndDay37;

public class MergeSort {

    /*
    ============================================================
    LC 148 — SORT LIST (Merge Sort for Linked List)
    Sort a linked list in O(n log n) time and O(1) space

    WHY MERGE SORT FOR LINKED LIST:
    - Quick Sort needs random access (bad for linked list)
    - Merge Sort only needs sequential access (perfect!)
    - No extra array needed (unlike array merge sort)

    ALGORITHM:
    1. Find middle and split list into two halves
    2. Recursively sort left half
    3. Recursively sort right half
    4. Merge both sorted halves

    TIME: O(n log n)
      - log n levels of recursion
      - O(n) work at each level (merge)

    SPACE: O(log n) — recursion stack
      - Not O(n) because we're not creating new nodes
      - Just rearranging pointers

    ASKED BY: Google, Facebook, Microsoft, Amazon
    ============================================================
    */


    // ============================================================
    // MAIN SORTING FUNCTION
    //
    // BASE CASE:
    // - Empty list (head == null)
    // - Single node (head.next == null)
    // Both are already sorted!
    //
    // RECURSIVE CASE:
    // 1. Split at middle
    // 2. Sort left half
    // 3. Sort right half
    // 4. Merge sorted halves
    //
    // VISUAL:
    // 4 → 2 → 1 → 3
    //     ↓ split
    // [4 → 2] [1 → 3]
    //     ↓ sort each
    // [2 → 4] [1 → 3]
    //     ↓ merge
    // 1 → 2 → 3 → 4
    // ============================================================
    public ListNode sortList(ListNode head) {
        // Base case: empty or single node
        if (head == null || head.next == null) {
            return head;
        }

        // Step 1: Find middle and split
        ListNode mid = getMid(head);

        // Step 2: Recursively sort left half (head to mid-1)
        ListNode left = sortList(head);

        // Step 3: Recursively sort right half (mid to end)
        ListNode right = sortList(mid);

        // Step 4: Merge sorted halves
        return merge(left, right);
    }


    // ============================================================
    // MERGE TWO SORTED LISTS
    //
    // DUMMY HEAD TECHNIQUE:
    // - Create dummy node to simplify edge cases
    // - Build result list by appending smaller element
    // - Return dummy.next (actual head)
    //
    // PROCESS:
    // 1. Compare heads of both lists
    // 2. Take smaller one, attach to tail
    // 3. Move that list's pointer forward
    // 4. When one list exhausts, attach remaining
    //
    // WHY DUMMY HEAD:
    // - No special case for first element
    // - Cleaner code
    //
    // VISUAL:
    // list1: 2 → 4
    // list2: 1 → 3
    //
    // Compare 2 vs 1 → take 1
    // Compare 2 vs 3 → take 2
    // Compare 4 vs 3 → take 3
    // Remaining: 4
    // Result: 1 → 2 → 3 → 4
    //
    // TIME: O(n + m)
    // SPACE: O(1) — reusing existing nodes
    // ============================================================
    ListNode merge(ListNode list1, ListNode list2) {
        // Dummy head simplifies building result
        ListNode dummyHead = new ListNode();
        ListNode tail = dummyHead;

        // Compare and take smaller
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }

        // Attach remaining elements
        tail.next = (list1 != null) ? list1 : list2;

        return dummyHead.next;
    }


    // ============================================================
    // GET MIDDLE AND SPLIT LIST
    //
    // THIS IS THE TRICKY PART!
    //
    // DIFFERENCE FROM REGULAR FIND-MIDDLE:
    // - We need to SPLIT the list, not just find middle
    // - Must break the link before middle
    // - midPrev.next = null disconnects the two halves
    //
    // WHY midPrev INSTEAD OF SLOW/FAST:
    // - We need pointer to node BEFORE middle
    // - So we can set midPrev.next = null to split
    //
    // ALGORITHM:
    // - midPrev tracks the node before middle
    // - head moves 2 steps (like fast pointer)
    // - midPrev moves 1 step (like slow pointer)
    // - At end: midPrev.next is the middle
    //
    // VISUAL:
    // 1 → 2 → 3 → 4 → NULL
    //     ↑       ↑
    //  midPrev   mid
    //
    // After split:
    // List 1: 1 → 2 → NULL
    // List 2: 3 → 4 → NULL
    //
    // EDGE CASE — TWO NODES:
    // 1 → 2 → NULL
    // midPrev = 1, mid = 2
    // After: [1] and [2]
    //
    // WHY (midPrev == null) ? head : midPrev.next:
    // - First iteration: midPrev is null, set it to head
    // - After that: move midPrev forward normally
    // ============================================================
    ListNode getMid(ListNode head) {
        ListNode midPrev = null;

        // Move head 2 steps, midPrev 1 step
        while (head != null && head.next != null) {
            // First iteration: start midPrev at head
            // Otherwise: move midPrev one step
            midPrev = (midPrev == null) ? head : midPrev.next;
            head = head.next.next;  // move 2 steps
        }

        // midPrev.next is the middle node
        ListNode mid = midPrev.next;

        // CRITICAL: Split the list!
        midPrev.next = null;

        return mid;
    }


    // ============================================================
    // MAIN METHOD FOR TESTING
    // ============================================================
    public static void main(String[] args) {
        MergeSort sorter = new MergeSort();

        // Create unsorted list: 4 → 2 → 1 → 3
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);

        System.out.print("Before sorting: ");
        printList(head);

        head = sorter.sortList(head);

        System.out.print("After sorting:  ");
        printList(head);

        // Test with more elements: 5 → 3 → 8 → 1 → 2 → 7 → 4 → 6
        ListNode head2 = new ListNode(5);
        head2.next = new ListNode(3);
        head2.next.next = new ListNode(8);
        head2.next.next.next = new ListNode(1);
        head2.next.next.next.next = new ListNode(2);
        head2.next.next.next.next.next = new ListNode(7);
        head2.next.next.next.next.next.next = new ListNode(4);
        head2.next.next.next.next.next.next.next = new ListNode(6);

        System.out.print("\nBefore sorting: ");
        printList(head2);

        head2 = sorter.sortList(head2);

        System.out.print("After sorting:  ");
        printList(head2);
    }

    // Helper to print list
    private static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(" → ");
            }
            current = current.next;
        }
        System.out.println();
    }
}



