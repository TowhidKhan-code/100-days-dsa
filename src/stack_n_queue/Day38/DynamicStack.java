package stack_n_queue.Day38;

public class DynamicStack<T> extends CustomStack<T> {

    /*
    ============================================================
    DYNAMIC STACK — Auto-resizing Stack

    EXTENDS CustomStack:
    - Inherits all functionality
    - OVERRIDES push() to handle resize

    RESIZE STRATEGY:
    - When full, double the capacity
    - Copy all elements to new array
    - Continue with push operation

    WHY DOUBLE (2x):
    - Amortized O(1) push
    - Balance between memory and resize frequency
    - Industry standard (ArrayList does same)

    AMORTIZED ANALYSIS:
    - Most pushes: O(1)
    - Occasional resize: O(n)
    - Average over n operations: O(1) per operation
    ============================================================
    */

    public DynamicStack() {
        super();
    }

    public DynamicStack(int size) {
        super(size);
    }


    // ============================================================
    // PUSH — Override to add dynamic resizing
    //
    // DIFFERENCE FROM PARENT:
    // - Parent throws exception when full
    // - This version resizes and continues
    //
    // STEPS:
    // 1. Check if full
    // 2. If full → create new array (2x size)
    // 3. Copy all elements
    // 4. Replace old array
    // 5. Continue with normal push
    // ============================================================
    @Override
    public boolean push(T val) throws StackException {
        if (isFull()) {
            // Create new array with double capacity
            Object[] temp = new Object[data.length * 2];

            // Copy all elements
            for (int i = 0; i < data.length; i++) {
                temp[i] = data[i];
            }

            // Replace array
            data = temp;

            System.out.println("Stack resized to: " + data.length);
        }

        // Normal push operation
        ptr++;
        data[ptr] = val;
        return true;
    }
}