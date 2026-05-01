package Day55_StreamsAndLambda;

import java.util.Comparator;

public class _1_Lambda {
   public  static void main(String[] args) {
        // FULL SYNTAX:
        // (parameters) -> { body; return value; }

       // Anonymous Inner Class
       Comparator<String> comp1 = new Comparator<String>() {
           @Override
           public int compare(String a, String b) {
               return a.length() - b.length();
           }
       };

       // Lambda — same thing
       Comparator<String> comp2 = (a, b) -> a.length() - b.length();

       // Anonymous Runnable
       Runnable r1 = new Runnable() {
           @Override
           public void run() {
               System.out.println("Running!");
           }
       };

       int x = 10;
       // x = 20; // Uncommenting this breaks the lambda below
       Runnable r = () -> System.out.println("x = " + x);
       r.run(); // x = 10
    }

    public class VariableCapture {

        int instanceVar = 10; // Instance variable — can access and modify

        public void demo() {
            int localVar = 20;          // Local variable — must be effectively final
            final int finalVar = 30;    // Explicitly final — fine

            // CAN access instance variables
            Runnable r1 = () -> System.out.println(instanceVar); // OK

            // CAN access effectively final local variables
            Runnable r2 = () -> System.out.println(localVar);    // OK (never reassigned)

            // CAN access explicitly final
            Runnable r3 = () -> System.out.println(finalVar);    // OK

            // CANNOT modify local variables from lambda
            // Runnable r4 = () -> localVar = 50;  // COMPILE ERROR

            // CANNOT access local variable if reassigned after
            // localVar = 50; // This would make localVar not effectively final
            // Runnable r5 = () -> System.out.println(localVar); // COMPILE ERROR

            // CAN modify instance variables
            Runnable r6 = () -> instanceVar = 99;  // OK

            // WHY THIS RESTRICTION?
            // Local variables live on the stack
            // Lambda may run on different thread after local variable is gone
            // Final/effectively final ensures safety
        }

    }
}
