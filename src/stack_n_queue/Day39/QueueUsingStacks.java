package stack_n_queue.Day39;

import java.util.Stack;

/*
============================================================
LC 232: IMPLEMENT QUEUE USING STACKS

APPROACH: Insert Efficient
- add() is O(1) — just push to first stack
- remove()/peek() is O(n) — transfer all elements

WHY TWO STACKS:
- Stack is LIFO, Queue is FIFO
- Reversing LIFO twice gives FIFO!
- Stack 1 → Stack 2 reverses order
- Top of Stack 2 is oldest element (front of queue)

ALTERNATIVE: Remove Efficient
- add() is O(n) — transfer all, push new, transfer back
- remove()/peek() is O(1) — just pop from stack

TIME COMPLEXITY (Insert Efficient):
- add: O(1)
- remove: O(n)
- peek: O(n)

SPACE: O(n) for the two stacks
============================================================
*/

public class QueueUsingStacks<T> {
    private Stack<T> first;   // Primary stack for adding
    private Stack<T> second;  // Helper stack for removing

    public QueueUsingStacks() {
        this.first = new Stack<>();
        this.second = new Stack<>();
    }


    // ============================================================
    // ADD — O(1)
    // Simply push to first stack
    // ============================================================
    public void add(T val) {
        first.push(val);
    }


    // ============================================================
    // REMOVE — O(n)
    //
    // STEPS:
    // 1. Transfer all from first to second (reverses order)
    // 2. Pop from second (this is the front element)
    // 3. Transfer back from second to first
    //
    // WHY TRANSFER BACK:
    // To maintain correct order for future operations
    // ============================================================
    public T remove() {
        // Step 1: Transfer first → second
        while (!first.isEmpty()) {
            second.push(first.pop());
        }

        // Step 2: Remove front element
        T removed = second.pop();

        // Step 3: Transfer second → first
        while (!second.isEmpty()) {
            first.push(second.pop());
        }

        return removed;
    }


    // ============================================================
    // PEEK — O(n)
    // Same as remove, but don't actually remove
    // ============================================================
    public T peek() {
        // Transfer first → second
        while (!first.isEmpty()) {
            second.push(first.pop());
        }

        // Peek front element
        T peeked = second.peek();

        // Transfer second → first
        while (!second.isEmpty()) {
            first.push(second.pop());
        }

        return peeked;
    }


    // ============================================================
    // IS EMPTY — O(1)
    // ============================================================
    public boolean isEmpty() {
        return first.isEmpty();
    }
}