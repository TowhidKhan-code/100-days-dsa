package Day55_StreamsAndLambda;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.*;

public class _3_BuiltInFunctionalInterfaces {
    public static void main(String[] args) {
        //Predicate - Boolean Test
        //Syntax - Predicate<InputType> varName = test to perform;
        Predicate<Integer> isPositive = n -> n > 0;
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<String> isEmpty = s -> s.isEmpty();
        Predicate<String> isLong = s -> s.length() > 5;
        System.out.println(isPositive.test(5));   // true
        System.out.println(isPositive.test(-3));  // false
        System.out.println(isEven.test(4));        // true

        // COMBINING PREDICATES:

        // AND: both must be true
        Predicate<Integer> isPositiveEven = isPositive.and(isEven);
        System.out.println(isPositiveEven.test(4));   // true (positive AND even)

        // OR: at least one must be true
        Predicate<Integer> isPositiveOrEven = isPositive.or(isEven);
        System.out.println(isPositiveOrEven.test(3));   // true (positive)

        // NEGATE: flip the result
        Predicate<Integer> isNegative = isPositive.negate();
        System.out.println(isNegative.test(-5));  // true
        System.out.println(isNegative.test(5));   // false

        // STATIC: not() method
        Predicate<String> isNotEmpty = Predicate.not(isEmpty);
        System.out.println(isNotEmpty.test("hello"));  // true
        System.out.println(isNotEmpty.test(""));       // false

        // Predicate.isEqual()
        Predicate<String> isHello = Predicate.isEqual("hello");
        System.out.println(isHello.test("hello"));  // true
        System.out.println(isHello.test("world"));  // false

        // Use in filter
        List<Integer> numbers = Arrays.asList(1, -2, 3, -4, 5, 6);
        numbers.stream()
                .filter(isPositiveEven)
                .forEach(System.out::println); // 6


        //Function --- R apply(T t)
        //Syntax - Function<InputType,OutputType> varName = operation to apply;
        // Basic function: input → output (different types)
        Function<String, Integer> strToLength = s -> s.length();
        Function<Integer, String> intToStr = n -> "Value: " + n;
        Function<String, String> toUpper = s -> s.toUpperCase();
        Function<Integer, Integer> square = n -> n * n;

        System.out.println(strToLength.apply("Hello"));  // 5
        System.out.println(intToStr.apply(42));           // Value: 42
        System.out.println(toUpper.apply("hello"));       // HELLO
        System.out.println(square.apply(4));              // 16

        // COMPOSING FUNCTIONS:

        // andThen: f.andThen(g) = g(f(x)) — apply f first, then g
        Function<String, String> lengthThenString =
                strToLength.andThen(intToStr);
        // strToLength("Hello") = 5, intToStr(5) = "Value: 5"
        System.out.println(lengthThenString.apply("Hello")); // Value: 5

        // compose: f.compose(g) = f(g(x)) — apply g first, then f
        Function<Integer, String> squareThenString =
                intToStr.compose(square);
        // square(4) = 16, intToStr(16) = "Value: 16"
        System.out.println(squareThenString.apply(4));  // Value: 16

        // Chain multiple functions
        Function<String, String> pipeline =
                toUpper
                        .andThen(s -> s + "!!!")
                        .andThen(s -> "[" + s + "]");
        System.out.println(pipeline.apply("hello")); // [HELLO!!!]

        // Identity function - Returns a function that always returns its input argument.
        Function<String, String> identity = Function.identity();
        System.out.println(identity.apply("unchanged")); // unchanged

        // BiFunction: two inputs, one output
        BiFunction<String, Integer, String> repeat =
                (s, n) -> s.repeat(n);
        System.out.println(repeat.apply("ab", 3)); // ababab

        // UnaryOperator: same input and output type
        UnaryOperator<String> addExclaim = s -> s + "!";
        System.out.println(addExclaim.apply("Hello")); // Hello!

        // BinaryOperator: two same-type inputs, same-type output
        BinaryOperator<Integer> add = (a, b) -> a + b;
        System.out.println(add.apply(3, 4));  // 7


        //Consumer
        // Basic consumer: takes input, returns nothing
        Consumer<String> print = s -> System.out.println(s);
        Consumer<String> printUpper = s -> System.out.println(s.toUpperCase());
        Consumer<Integer> printDouble = n -> System.out.println(n * 2);

        print.accept("Hello");        // Hello
        printUpper.accept("Hello");   // HELLO
        printDouble.accept(5);         // 10

        // CHAINING with andThen:
        // first.andThen(second) — executes first, then second
        Consumer<String> printBoth = print.andThen(printUpper);
        printBoth.accept("hello");
        // hello
        // HELLO

        // Practical use: forEach
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        names.forEach(name -> System.out.println("Hello " + name));
        names.forEach(System.out::println); // Method reference

        // Process list with consumer
        Consumer<List<Integer>> printAll = list -> list.forEach(System.out::println);
        printAll.accept(Arrays.asList(1, 2, 3));

        // BiConsumer: two inputs, no output
        BiConsumer<String, Integer> printWithCount =
                (s, n) -> System.out.println(n + ": " + s);
        printWithCount.accept("Hello", 1);  // 1: Hello
        printWithCount.accept("World", 2);  // 2: World

        // Map forEach uses BiConsumer
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.forEach((k, v) -> System.out.println(k + " = " + v));

        //Supplier
        // Basic supplier: no input, returns value
        Supplier<String> hello = () -> "Hello, World!";
        Supplier<Integer> randomInt = () -> (int)(Math.random() * 100);
        Supplier<List<String>> newList = () -> new ArrayList<>();
        Supplier<LocalDateTime> now = () -> LocalDateTime.now();

        System.out.println(hello.get());       // Hello, World!
        System.out.println(randomInt.get());
        System.out.println(now.get());

        // Lazy initialization with Supplier
//        Supplier<ExpensiveObject> lazyObject = () -> new ExpensiveObject();
//        // Object not created yet!
//        // Created only when get() is called
//        if (needsObject()) {
//            ExpensiveObject obj = lazyObject.get(); // Created here
//        }
        // Supplier for factory pattern
//        Supplier<DatabaseConnection> connFactory =
//                () -> new DatabaseConnection("localhost:5432");
//
//        DatabaseConnection conn = connFactory.get();

        // Optional.orElseGet uses Supplier (lazy — only called if empty)
        Optional<String> opt = Optional.empty();
        String value = opt.orElseGet(() -> "default value");
        System.out.println(value); // default value

        // vs orElse (eager — always evaluates)
        String value2 = opt.orElse(expensiveComputation()); // Always runs!
        String value3 = opt.orElseGet(() -> expensiveComputation()); // Only if empty
    }


    // BiPredicate: two arguments
    static void biPredicateDemo() {
        BiPredicate<String, Integer> hasLength = (s, len) -> s.length() == len;
        System.out.println(hasLength.test("hello", 5));  // true
        System.out.println(hasLength.test("hi", 5));     // false
    }

    static String expensiveComputation() {
        System.out.println("Computing...");
        return "result";
    }
}
