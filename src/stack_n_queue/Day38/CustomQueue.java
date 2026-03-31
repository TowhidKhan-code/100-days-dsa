package stack_n_queue.Day38;

public class CustomQueue<T> {

    /*
    ============================================================
    CUSTOM QUEUE IMPLEMENTATION (Generic)

    INTERNAL STRUCTURE:
    - Object[] data → stores elements
    - int end       → index where next element will be added

    OPERATIONS:
    - add(T val)  → Add at end, increment end
    - remove()    → Remove from front (index 0), shift left
    - peek()      → View front without removing

    PROBLEM WITH THIS IMPLEMENTATION:
    - remove() is O(n) due to shifting!
    - For every removal, all elements shift left
    - Solution: Circular Queue

    TIME COMPLEXITY:
    - add: O(1)
    - remove: O(n) ← PROBLEM!
    - peek: O(1)
    ============================================================
    */

    protected Object[] data;
    private static final int DEFAULT_SIZE = 10;
    int end = 0;  // Index where next element will be added

    public CustomQueue() {
        this(DEFAULT_SIZE);
    }

    public CustomQueue(int size) {
        this.data = new Object[size];
    }


    // ============================================================
    // ADD — Add element to rear
    //
    // STEPS:
    // 1. Check if full → throw exception
    // 2. Add element at 'end' index
    // 3. Increment 'end'
    //
    // TIME: O(1)
    // ============================================================
    public boolean add(T val) throws Exception {
        if (isFull()) {
            throw new Exception("Queue is full!");
        }
        data[end++] = val;
        return true;
    }


    // ============================================================
    // REMOVE — Remove element from front
    //
    // STEPS:
    // 1. Check if empty → throw exception
    // 2. Save element at index 0 (front)
    // 3. Shift all elements left by 1
    // 4. Decrement 'end'
    // 5. Return saved element
    //
    // TIME: O(n) — due to shifting!
    //
    // WHY SHIFTING IS BAD:
    // For a queue of 1000 elements, every remove
    // shifts 999 elements. Very inefficient!
    // ============================================================
    public T remove() throws Exception {
        if (isEmpty()) {
            throw new Exception("Queue is empty");
        }

        T removed = (T) data[0];  // Save front element

        // Shift all elements left
        for (int i = 1; i < end; i++) {
            data[i - 1] = data[i];
        }

        end--;
        return removed;
    }


    // ============================================================
    // PEEK — View front element without removing
    // TIME: O(1)
    // ============================================================
    public T peek() throws Exception {
        if (isEmpty()) {
            throw new Exception("Empty Queue");
        }
        return (T) data[0];
    }


    // ============================================================
    // DISPLAY — Print all elements
    // ============================================================
    public void display() {
        for (int i = 0; i < end; i++) {
            System.out.print(data[i] + " <- ");
        }
        System.out.println("END");
    }


    // ============================================================
    // HELPER METHODS
    // ============================================================

    public boolean isFull() {
        return end == data.length;
    }

    public boolean isEmpty() {
        return end == 0;
    }
}