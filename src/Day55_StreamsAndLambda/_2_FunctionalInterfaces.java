package Day55_StreamsAndLambda;

public class _2_FunctionalInterfaces {
    // Basic functional interface
    @FunctionalInterface
    interface Greeting {
        void greet(String name);
        // Only ONE abstract method allowed
    }

    // Generic functional interface
    @FunctionalInterface
    interface Transformer<T, R> {
        R transform(T input);
    }

    // With default and static methods (still functional)
    @FunctionalInterface
    interface Calculator {
        int calculate(int a, int b); // Only one abstract method

        // Default methods allowed
        default void printResult(int a, int b) {
            System.out.println("Result: " + calculate(a, b));
        }

        // Static methods allowed
        static Calculator add() {
            return (a, b) -> a + b;
        }
    }

    // Using custom functional interfaces
        public static void main(String[] args) {

            // Implement with lambda
            Greeting hello = name -> System.out.println("Hello, " + name + "!");
            Greeting bye = name -> System.out.println("Goodbye, " + name + "!");

            hello.greet("Towhid");  // Hello, Towhid!
            bye.greet("Towhid");    // Goodbye, Towhid!

            // Generic transformer
            Transformer<String, Integer> lengthOf = s -> s.length();
            Transformer<Integer, String> toString = n -> "Number: " + n;

            System.out.println(lengthOf.transform("Hello")); // 5
            System.out.println(toString.transform(42));       // Number: 42

            // Calculator
            Calculator adder = (a, b) -> a + b;
            Calculator multiplier = (a, b) -> a * b;

            System.out.println(adder.calculate(5, 3));       // 8
            adder.printResult(5, 3);                          // Result: 8
            System.out.println(multiplier.calculate(5, 3));  // 15

            // Pass lambda as argument
            processNumbers(10, 5, (a, b) -> a + b);          // 15
            processNumbers(10, 5, (a, b) -> a * b);          // 50
            processNumbers(10, 5, (a, b) -> Math.max(a, b)); // 10
        }

        // Method accepting functional interface
        static void processNumbers(int a, int b, Calculator calc) {
            System.out.println("Processing: " + calc.calculate(a, b));
        }
}
