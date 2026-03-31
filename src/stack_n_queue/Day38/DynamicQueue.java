package stack_n_queue.Day38;

public class DynamicQueue<T> extends CircularQueue<T> {

    /*
    ============================================================
    DYNAMIC QUEUE — Auto-resizing Circular Queue

    EXTENDS CircularQueue:
    - Inherits circular behavior
    - OVERRIDES add() to handle resize

    RESIZE COMPLEXITY:
    - Must maintain element ORDER
    - Elements might be wrapped around
    - Need to "unwrap" into new array

    AFTER RESIZE:
    - front = 0 (reset to beginning)
    - end = old size (points after last element)
    - All elements now contiguous

    TIME: O(1) amortized (resize is O(n) but rare)
    ============================================================
    */

    public DynamicQueue() {
        super();
    }

    public DynamicQueue(int size) {
        super(size);
    }


    // ============================================================
    // ADD — Override to add dynamic resizing
    //
    // RESIZE STEPS:
    // 1. Create new array (2x capacity)
    // 2. Copy elements in ORDER (handling wrap-around)
    // 3. Reset front = 0
    // 4. Set end = old data length (size before resize)
    // 5. Call parent's add() to actually add element
    //
    // CRITICAL: Copy in circular order, not array order!
    // ============================================================
    @Override
    public boolean add(T val) throws Exception {
        if (isFull()) {
            // Create new array with double capacity
            Object[] temp = new Object[data.length * 2];

            // Copy elements maintaining order
            // Start from 'front', copy 'size' elements
            for (int i = 0; i < data.length; i++) {
                temp[i] = data[(front + i) % data.length];
            }

            // Reset pointers
            front = 0;
            end = data.length;  // All old elements now at 0 to length-1

            // Replace array
            data = temp;

            System.out.println("Queue resized to: " + data.length);
        }

        // Call parent's add (handles normal circular add)
        return super.add(val);
    }
}