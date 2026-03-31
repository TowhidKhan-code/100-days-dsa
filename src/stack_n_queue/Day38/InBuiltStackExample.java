package stack_n_queue.Day38;

import java.util.Stack;

public class InBuiltStackExample {

    /*
    ============================================================
    JAVA BUILT-IN STACK

    Stack<E> extends Vector<E>
    - Synchronized (thread-safe but slower)
    - Legacy class, prefer Deque for new code

    KEY METHODS:
    - push(E item)  → Add to top, returns item
    - pop()         → Remove & return top
    - peek()        → View top without removing
    - isEmpty()     → Check if empty
    - search(Object)→ Returns 1-based position from top

    TIME COMPLEXITY:
    - push: O(1) amortized
    - pop:  O(1)
    - peek: O(1)
    ============================================================
    */

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        // Push elements
        stack.push(34);
        stack.push(12);
        stack.push(9);
        stack.push(4);
        stack.push(44);

        System.out.println("Stack: " + stack);  // [34, 12, 9, 4, 44]
        System.out.println("Top element: " + stack.peek());  // 44

        // Pop elements (LIFO order)
        System.out.println("Popped: " + stack.pop());  // 44
        System.out.println("Popped: " + stack.pop());  // 4
        System.out.println("Popped: " + stack.pop());  // 9

        System.out.println("Stack after pops: " + stack);  // [34, 12]

        // Check if empty
        System.out.println("Is empty? " + stack.isEmpty());  // false

        // Search (returns 1-based position from top)
        stack.push(100);
        System.out.println("Position of 12: " + stack.search(12));  // 2
    }
}