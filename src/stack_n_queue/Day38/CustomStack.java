package stack_n_queue.Day38;

public class CustomStack<T> {

    /*
    ============================================================
    CUSTOM STACK IMPLEMENTATION (Generic)

    INTERNAL STRUCTURE:
    - Object[] data  → stores elements
    - int ptr        → index of top element (-1 when empty)

    WHY Object[] INSTEAD OF T[]:
    - Java doesn't allow: new T[size] (generic array creation)
    - Solution: Object[] with casting on retrieval

    OPERATIONS:
    - push(T val) → Add to top, ptr++
    - pop()       → Remove from top, ptr--
    - peek()      → View top without removing
    - isEmpty()   → Check if ptr == -1
    - isFull()    → Check if ptr == length - 1

    TIME: All operations O(1)
    SPACE: O(n) where n is capacity
    ============================================================
    */

    protected Object[] data;
    private static final int DEFAULT_SIZE = 10;
    int ptr = -1;  // Points to top element, -1 means empty

    // Default constructor
    public CustomStack() {
        this(DEFAULT_SIZE);
    }

    // Constructor with custom size
    public CustomStack(int size) {
        this.data = new Object[size];
    }


    // ============================================================
    // PUSH — Add element to top
    //
    // STEPS:
    // 1. Check if full → throw exception
    // 2. Increment ptr (move to next empty slot)
    // 3. Add element at ptr position
    //
    // WHY ptr++ BEFORE adding:
    // ptr starts at -1 (empty)
    // After ptr++, ptr = 0 → first valid index
    // ============================================================
    public boolean push(T val) throws StackException {
        if (isFull()) {
            throw new StackException("Stack is Full");
        }
        ptr++;
        data[ptr] = val;
        return true;
    }


    // ============================================================
    // POP — Remove and return top element
    //
    // STEPS:
    // 1. Check if empty → throw exception
    // 2. Return element at ptr and decrement ptr
    //
    // data[ptr--] explanation:
    // - First returns data[ptr]
    // - Then decrements ptr
    // - Element still exists in array but is "logically removed"
    // ============================================================
    public T pop() throws StackException {
        if (isEmpty()) {
            throw new StackException("Stack is Empty");
        }
        return (T) data[ptr--];
    }


    // ============================================================
    // PEEK — View top element without removing
    // ============================================================
    public T peek() throws StackException {
        if (isEmpty()) {
            throw new StackException("Cannot peek from an empty stack");
        }
        return (T) data[ptr];
    }


    // ============================================================
    // HELPER METHODS
    // ============================================================

    public boolean isFull() {
        return ptr == data.length - 1;
    }

    public boolean isEmpty() {
        return ptr == -1;
    }

    public int size() {
        return ptr + 1;
    }
}