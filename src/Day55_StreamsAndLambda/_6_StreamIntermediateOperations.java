package Day55_StreamsAndLambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class _6_StreamIntermediateOperations {
    public static void main(String[] args) {
        //----- STREAM INTERMEDIATE OPERATIONS -----
        List<Integer> numbers = Arrays.asList(5,3,8,1,9,2,7,4,6);
        List<Integer> nums = Arrays.asList(5, 3, 8, 1, 9, 2, 7, 4, 6, 3, 1);
        List<String> words = Arrays.asList("banana","apple","cherry","date","mango");

        //FILTER — keep elements matching predicate
        List<Integer> evens = numbers.stream()
                .filter(n -> n %2 == 0)
                .collect(Collectors.toList());
        System.out.println("Evens: " + evens);

        // Chain filters
        List<Integer> evenAndGreaterThan4 = numbers.stream()
                .filter(n -> n % 2 == 0)
                .filter(n -> n > 4)
                .collect(Collectors.toList());

        //MAP — transform each element
        List<String> upperWords = words.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Squared: " + upperWords);

        //FLATMAP — transform then flatten
        //When each element maps to a Stream or Collection

        // Example 1: Flatten list of lists
        List<List<Integer>> nested = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6),
                Arrays.asList(7, 8, 9)
        );
        List<Integer> flat = nested.stream()
                .flatMap(List::stream)  // Flatten each inner list
                .collect(Collectors.toList());
        System.out.println("Flat: " + flat); // [1,2,3,4,5,6,7,8,9]

        // Example 2: Split sentences into words
        List<String> sentences = Arrays.asList("Hello World", "Java Streams");
        List<String> allWords = sentences.stream()
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .collect(Collectors.toList());
        System.out.println("Words: " + allWords); // [Hello, World, Java, Streams]

        // Example 3: Get all characters from words
        List<Character> chars = words.stream()
                .flatMap(w -> w.chars().mapToObj(c -> (char) c))
                .collect(Collectors.toList());
        System.out.println(chars);

        // MAP vs FLATMAP visual:
        // map:     Stream<List<Integer>> → [1,2],[3,4],[5,6]  (stream of lists)
        // flatMap: Stream<Integer>       → 1,2,3,4,5,6        (flat stream)

        //PEEK (for debugging)
        List<Integer> result = numbers.stream()
                .filter(n -> n > 3)
                .peek(n -> System.out.println("After filter: " + n))
                .map(n -> n * 2)
                .peek(n -> System.out.println("After map: " + n))
                .collect(Collectors.toList());

        // WARNING: peek is for debugging only
        // Do NOT use peek for business logic
        // peek may not execute in all cases (short-circuit operations)

        //mapToInt, mapToLong, mapToDouble, mapToObj in _8_NumericStream

        //SORTED — natural order
        List<Integer> sorted = numbers.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Sorted: " + sorted); // [1,2,3,4,5,6,7,8,9]

        // SORTED — custom comparator
        List<Integer> sortedDesc = numbers.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println("Desc: " + sortedDesc);

        // SORTED — by field
        List<String> sortedByLength = words.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
        System.out.println("By length: " + sortedByLength);

        // Multi-level sort
        List<String> byLengthThenAlpha = words.stream()
                .sorted(Comparator.comparingInt(String::length)
                        .thenComparing(Comparator.naturalOrder()))
                .collect(Collectors.toList());


        //DISTINCT — remove duplicates (uses equals)
        List<Integer> withDups = Arrays.asList(1, 2, 2, 3, 3, 3, 4);
        List<Integer> distinct = withDups.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Distinct: " + distinct); // [1,2,3,4]

        //LIMIT — take first n elements
        List<Integer> first3 = numbers.stream()
                .sorted()
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("First 3: " + first3); // [1,2,3]

        // Useful with infinite streams
        List<Integer> first10Evens = Stream.iterate(0, n -> n + 2)
                .limit(10)
                .collect(Collectors.toList()); // [0,2,4,6,8,10,12,14,16,18]

        //SKIP
        List<Integer> skip3 = numbers.stream()
                .sorted()
                .skip(3)
                .collect(Collectors.toList());
        System.out.println("Skip 3: " + skip3); // [4,5,6,7,8,9]

        // PAGINATION with skip and limit
        int pageSize = 3;
        int pageNumber = 2; // 0-indexed

        List<Integer> page = nums.stream()
                .skip((long) pageNumber * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList()); // Elements 6,7,8 (page 2)


        // TAKEWHILE — take elements WHILE predicate is true, stop at first false
        // Different from filter: filter checks ALL elements, takeWhile stops early

        List<Integer> sorted2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> taken = sorted2.stream()
                .takeWhile(n -> n < 5)
                .collect(Collectors.toList()); // [1,2,3,4] — stops at 5

        // With unsorted data — only takes from start:
        List<Integer> unsorted = Arrays.asList(1, 2, 7, 3, 4);
        List<Integer> takenUnsorted = unsorted.stream()
                .takeWhile(n -> n < 5)
                .collect(Collectors.toList()); // [1,2] — stops at 7 even though 3,4 < 5

        // DROPWHILE — skip elements WHILE predicate is true, take rest after first false
        List<Integer> dropped = sorted.stream()
                .dropWhile(n -> n < 5)
                .collect(Collectors.toList()); // [5,6,7,8,9,10]

        List<Integer> droppedUnsorted = unsorted.stream()
                .dropWhile(n -> n < 5)
                .collect(Collectors.toList()); // [7,3,4] — starts including from 7

        // USE CASE: processing log lines after a header
        List<String> logLines = Arrays.asList(
                "HEADER: version 1.0",
                "HEADER: date 2024",
                "INFO: server started",
                "ERROR: connection failed"
        );
        List<String> dataLines = logLines.stream()
                .dropWhile(line -> line.startsWith("HEADER"))
                .collect(Collectors.toList());
        // [INFO: server started, ERROR: connection failed]

        // takeWhile vs filter:
        // filter:    checks EVERY element, keeps matching ones
        // takeWhile: stops at FIRST non-matching element
    }
}
