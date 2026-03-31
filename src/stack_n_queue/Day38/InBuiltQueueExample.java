package stack_n_queue.Day38;

import java.util.LinkedList;
import java.util.Queue;

public class InBuiltQueueExample {

    /*
    ============================================================
    JAVA BUILT-IN QUEUE

    Queue<E> is an INTERFACE
    - Common implementations: LinkedList, ArrayDeque, PriorityQueue

    KEY METHODS:
    - add(E e)    → Add to rear (throws exception if full)
    - offer(E e)  → Add to rear (returns false if full)
    - remove()    → Remove front (throws exception if empty)
    - poll()      → Remove front (returns null if empty)
    - element()   → View front (throws exception if empty)
    - peek()      → View front (returns null if empty)

    PREFER poll() AND peek():
    - They return null instead of throwing exceptions
    - More graceful handling

    TIME COMPLEXITY:
    - add/offer: O(1)
    - remove/poll: O(1)
    - peek: O(1)
    ============================================================
    */

    public static void main(String[] args) {
        // Queue is interface, LinkedList is implementation
        Queue<Integer> queue = new LinkedList<>();

        // Add elements (to rear)
        queue.add(35);
        queue.add(2);
        queue.add(12);
        queue.add(3);
        queue.add(5);

        System.out.println("Queue: " + queue);  // [35, 2, 12, 3, 5]
        System.out.println("Front: " + queue.peek());  // 35

        // Remove elements (from front, FIFO order)
        System.out.println("Removed: " + queue.remove());  // 35
        System.out.println("Removed: " + queue.remove());  // 2
        System.out.println("Removed: " + queue.remove());  // 12

        System.out.println("Queue after removes: " + queue);  // [3, 5]

        // Check if empty
        System.out.println("Is empty? " + queue.isEmpty());  // false
    }
}