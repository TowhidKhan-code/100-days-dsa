package Day55_StreamsAndLambda;

import java.util.Optional;

public class _9_OptionalDemo {
    public static void main(String[] args) {

        //Create Optional
        Optional<String> empty = Optional.empty();
        Optional<String> present = Optional.of("Hello");
        Optional<String> nullable = Optional.ofNullable(null); // Empty
        Optional<String> nullable2 = Optional.ofNullable("value"); // Present

        // Check
        System.out.println(present.isPresent());  // true
        System.out.println(empty.isPresent());     // false
        System.out.println(empty.isEmpty());       // true

        // Get Value
        System.out.println(present.get()); // Hello (throws if empty)
        System.out.println(empty.orElse("default")); // default (eager)
        System.out.println(empty.orElseGet(() -> computeDefault())); // lazy

        // Transform
        Optional<Integer> length = present.map(String::length);
        Optional<String> upper = present.map(String::toUpperCase);

        // FlatMap (when function returns Optional)
        Optional<String> result = present.flatMap(s ->
                s.length() > 3 ? Optional.of(s) : Optional.empty()
        );
        System.out.println(result);

        // Filter
        Optional<String> longWord = present.filter(s -> s.length() > 3);
        System.out.println(longWord); // Optional[Hello]

        // ifPresent
        present.ifPresent(s -> System.out.println("Value: " + s));

        // ifPresentOrElse (Java 9)
        empty.ifPresentOrElse(
                s -> System.out.println("Found: " + s),
                () -> System.out.println("Not found!")
        );

        // Stream from Optional (Java 9)
        present.stream().forEach(System.out::println);
    }

    static String computeDefault() {
        return "computed default";
    }
}
