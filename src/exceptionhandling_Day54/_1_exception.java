package exceptionhandling_Day54;

public class _1_exception {
    static void main(String[] args) {
        /*Syntax
        try {
             Code that might throw exception
             If exception thrown: jump to matching catch
             If no exception: skip all catch blocks
        } catch (ExceptionType1 e) {
             Handle ExceptionType1
             e contains exception information
        } catch (ExceptionType2 e) {
             Handle ExceptionType2
        } finally {
             ALWAYS runs: exception or not
             Use for cleanup: close files, connections
        }
         */

        // Example 1: ArithmeticException
        try {
            int a = 10;
            int b = 0;
            int result = a / b;         // Throws ArithmeticException
            System.out.println(result); // Never reached
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero!");
            System.out.println("Message: " + e.getMessage());
        }

        // Example 2: NullPointerException
        try {
            String str = null;
            int len = str.length();     // Throws NullPointerException
        } catch (NullPointerException e) {
            System.out.println("String is null!");
        }

        // Example 3: ArrayIndexOutOfBoundsException
        try {
            int[] arr = {1, 2, 3};
            int val = arr[10];          // Throws ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index out of bounds: " + e.getMessage());
        }

        // Example 4: NumberFormatException
        try {
            int num = Integer.parseInt("abc"); // Throws NumberFormatException
        } catch (NumberFormatException e) {
            System.out.println("Cannot parse string to number!");
        }

        System.out.println("Program continues after handling exceptions");
    }

    public static void finallyDemo() {
        System.out.println("=== finally always runs ===");

        // Case 1: No exception
        try {
            System.out.println("try: no exception");
        } catch (Exception e) {
            System.out.println("catch: never runs");
        } finally {
            System.out.println("finally: ALWAYS runs");
        }
        // Output: try: no exception
        //         finally: ALWAYS runs

        // Case 2: Exception caught
        try {
            System.out.println("try: before exception");
            int x = 1 / 0;
            System.out.println("try: after exception (never runs)");
        } catch (ArithmeticException e) {
            System.out.println("catch: handling exception");
        } finally {
            System.out.println("finally: ALWAYS runs");
        }
        // Output: try: before exception
        //         catch: handling exception
        //         finally: ALWAYS runs

        // Case 3: Exception NOT caught (propagates)
        try {
            try {
                int x = 1 / 0;
            } finally {
                System.out.println("finally: runs even when not caught!");
                // This prints before exception propagates up
            }
        } catch (ArithmeticException e) {
            System.out.println("outer catch handles it");
        }
    }

/*
FINALLY USE CASES:
→ Close file streams
→ Close database connections
→ Release locks
→ Log completion
→ Reset state

FINALLY DOES NOT RUN WHEN:
→ System.exit() is called
→ JVM crashes
→ Thread is killed externally

RETURN IN FINALLY:
If finally has return statement, it overrides try/catch return.
This is BAD PRACTICE — avoid return in finally.
*/
}
