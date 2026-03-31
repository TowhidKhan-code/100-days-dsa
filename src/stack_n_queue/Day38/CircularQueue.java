package stack_n_queue.Day38;

public class CircularQueue<T> {

    /*
    ============================================================
    CIRCULAR QUEUE IMPLEMENTATION

    WHY CIRCULAR:
    - Avoids O(n) shifting in remove()
    - Reuses empty spaces at beginning of array
    - Both add and remove become O(1)

    INTERNAL STRUCTURE:
    - Object[] data → stores elements
    - int front     → index of front element
    - int end       → index where next element will be added
    - int size      → current number of elements

    KEY INSIGHT:
    - After operation, apply modulo: index = index % data.length
    - This makes the array "wrap around"

    VISUALIZING CIRCULAR:
    Array: [0][1][2][3][4] can be thought of as:

         [0]
       /     \
     [4]     [1]
       \     /
        [3][2]

    TIME COMPLEXITY:
    - add: O(1)
    - remove: O(1) ← FIXED!
    - peek: O(1)
    ============================================================
    */

    protected Object[] data;
    private static final int DEFAULT_SIZE = 10;

    int front = 0;  // Index of front element
    int end = 0;    // Index where next element will be added
    int size = 0;   // Current number of elements

    public CircularQueue() {
        this(DEFAULT_SIZE);
    }

    public CircularQueue(int size) {
        this.data = new Object[size];
    }


    // ============================================================
    // ADD — Add element at end (rear)
    //
    // STEPS:
    // 1. Check if full → throw exception
    // 2. Add element at 'end' index
    // 3. Increment 'end' with wrap-around: end = (end + 1) % length
    // 4. Increment size
    //
    // WRAP-AROUND EXAMPLE:
    // Array length = 5
    // end = 4 (last index)
    // After add: end = (4 + 1) % 5 = 0 (wraps to beginning!)
    //
    // TIME: O(1)
    // ============================================================
    public boolean add(T val) throws Exception {
        if (isFull()) {
            throw new Exception("Queue is full!");
        }

        data[end++] = val;
        end = end % data.length;  // Wrap around
        size++;
        return true;
    }


    // ============================================================
    // REMOVE — Remove element from front
    //
    // STEPS:
    // 1. Check if empty → throw exception
    // 2. Save element at 'front' index
    // 3. Increment 'front' with wrap-around
    // 4. Decrement size
    // 5. Return saved element
    //
    // NO SHIFTING NEEDED!
    //
    // TIME: O(1) ← Much better than basic queue!
    // ============================================================
    public T remove() throws Exception {
        if (isEmpty()) {
            throw new Exception("Queue is Empty!");
        }

        T removed = (T) data[front++];
        front = front % data.length;  // Wrap around
        size--;
        return removed;
    }


    // ============================================================
    // FRONT — View front element without removing
    // TIME: O(1)
    // ============================================================
    public T front() throws Exception {
        if (isEmpty()) {
            throw new Exception("Queue is empty");
        }
        return (T) data[front];
    }


    // ============================================================
    // DISPLAY — Print all elements (handling wrap-around)
    //
    // TRICKY PART:
    // - Can't just print from 0 to end
    // - Must start from 'front' and wrap around
    // - Use do-while to handle case where front == end
    // ============================================================
    public void display() {
        if (isEmpty()) {
            System.out.println("EMPTY");
            return;
        }

        int i = front;
        do {
            System.out.print(data[i] + " <- ");
            i++;
            i = i % data.length;  // Wrap around
        } while (i != end);

        System.out.println("END");
    }


    // ============================================================
    // HELPER METHODS
    //
    // WHY SIZE VARIABLE:
    // - Can't just check if front == end (could be empty OR full!)
    // - Size definitively tells us element count
    // ============================================================

    public boolean isFull() {
        return size == data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}