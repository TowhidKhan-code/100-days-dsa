package Day55_StreamsAndLambda;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class _4_MethodReference {
    public static void main(String[] args) {
        //Four Types

        // TYPE 1: Static Method Reference
        // ClassName::staticMethod
        // Lambda: x -> ClassName.staticMethod(x)

        Function<String, Integer> parse1 = s -> Integer.parseInt(s);
        Function<String, Integer> parse2 = Integer::parseInt;  // Equivalent

        Function<Double, Double> sqrt1 = x -> Math.sqrt(x);
        Function<Double, Double> sqrt2 = Math::sqrt;           // Equivalent

        Consumer<String> print1 = s -> System.out.println(s);
        Consumer<String> print2 = System.out::println;         // Equivalent

        // TYPE 2: Instance Method Reference (specific instance)
        // instance::instanceMethod
        // Lambda: x -> instance.instanceMethod(x)

        String prefix = "Hello, ";
        Function<String, String> addPrefix1 = s -> prefix.concat(s);
        Function<String, String> addPrefix2 = prefix::concat;   // Equivalent

        StringBuilder sb = new StringBuilder();
        Consumer<String> append1 = s -> sb.append(s);
        Consumer<String> append2 = sb::append;                  // Equivalent

        // TYPE 3: Instance Method Reference (arbitrary instance of class)
        // ClassName::instanceMethod
        // Lambda: (instance, x) -> instance.instanceMethod(x)
        //      OR: instance -> instance.instanceMethod()

        Function<String, String> upper1 = s -> s.toUpperCase();
        Function<String, String> upper2 = String::toUpperCase;  // Equivalent

        Function<String, Integer> length1 = s -> s.length();
        Function<String, Integer> length2 = String::length;     // Equivalent

        BiFunction<String, String, Boolean> startsWith1 = (s, prefix2) -> s.startsWith(prefix2);
        BiFunction<String, String, Boolean> startsWith2 = String::startsWith; // Equivalent

        // TYPE 4: Constructor Reference
        // ClassName::new
        // Lambda: x -> new ClassName(x)

        Supplier<ArrayList<String>> listSupplier1 = () -> new ArrayList<>();
        Supplier<ArrayList<String>> listSupplier2 = ArrayList::new;  // Equivalent

        Function<String, StringBuilder> sbCreator1 = s -> new StringBuilder(s);
        Function<String, StringBuilder> sbCreator2 = StringBuilder::new; // Equivalent

        //Method References in Practice

            List<String> names = Arrays.asList("Charlie", "Alice", "Bob", "Dave");

            // Print all — System.out::println is instance method ref on specific instance
            names.forEach(System.out::println);

            // Sort alphabetically — String::compareTo
            names.sort(String::compareTo);
            System.out.println(names); // [Alice, Bob, Charlie, Dave]

            // Sort by length — using method reference
            names.sort(Comparator.comparingInt(String::length));
            System.out.println(names);

            // Convert to uppercase
            List<String> upper = names.stream()
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());
            System.out.println(upper); // [BOB, ALICE, DAVE, CHARLIE]

            // Parse strings to integers
            List<String> numberStrings = Arrays.asList("1", "2", "3", "4");
            List<Integer> numbers = numberStrings.stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            System.out.println(numbers); // [1, 2, 3, 4]

            // Constructor reference for object creation
            List<String> words = Arrays.asList("hello", "world");
            List<StringBuilder> builders = words.stream()
                    .map(StringBuilder::new)
                    .collect(Collectors.toList());

            // Filter non-null using Objects::nonNull
            List<String> withNulls = Arrays.asList("a", null, "b", null, "c");
            List<String> withoutNulls = withNulls.stream()
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            System.out.println(withoutNulls); // [a, b, c]
        }
}
