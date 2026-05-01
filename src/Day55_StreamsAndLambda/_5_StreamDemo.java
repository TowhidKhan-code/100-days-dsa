package Day55_StreamsAndLambda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.*;

/*
The Stream API introduced in Java 8 provides a declarative way to process collections of data.
A stream is a sequence of elements that supports sequential and parallel processing operations.
Streams are lazy — operations are not executed until a terminal operation is called.
Streams consist of a source, zero or more intermediate operations, and one terminal operation.

---How to Think About Streams---:
STREAM PIPELINE:
Source → Intermediate Operations → Terminal Operation

SOURCE:
→ Collection.stream()
→ Arrays.stream(array)
→ Stream.of(values)
→ Stream.generate(supplier)
→ Stream.iterate(seed, function)
→ IntStream.range(start, end)

INTERMEDIATE OPERATIONS (lazy — return Stream):
→ filter(Predicate)     — keep elements matching predicate
→ map(Function)         — transform each element
→ flatMap(Function)     — flatten nested streams
→ sorted()              — natural order sort
→ sorted(Comparator)    — custom sort
→ distinct()            — remove duplicates
→ limit(n)              — take first n elements
→ skip(n)               — skip first n elements
→ peek(Consumer)        — debug, side effects
→ mapToInt/Long/Double  — convert to primitive stream

TERMINAL OPERATIONS (eager — trigger processing):
→ collect(Collector)    — gather into collection
→ forEach(Consumer)     — process each element
→ count()               — count elements
→ findFirst()           — first element (Optional)
→ findAny()             — any element (Optional)
→ min(Comparator)       — minimum (Optional)
→ max(Comparator)       — maximum (Optional)
→ anyMatch(Predicate)   — any element matches?
→ allMatch(Predicate)   — all elements match?
→ noneMatch(Predicate)  — no elements match?
→ reduce(identity, op)  — aggregate to single value
→ toArray()             — convert to array

IMPORTANT:
→ Stream can only be consumed ONCE
→ After terminal operation: stream is closed
→ Cannot reuse a stream
*/

public class _5_StreamDemo {
    public static void main(String[] args) {
        //----- STREAM CREATION -----

        //FROM COLLECTION
        List<String> list1 = Arrays.asList("a","b","c");
        Stream<String> s1 = list1.stream(); // Sequential
        Stream<String> s2 = list1.parallelStream(); // Parallel

        Set<Integer> set1 = new HashSet<>(Arrays.asList(1,2,3,2));
        Stream<Integer> s3 = set1.stream();

        Map<String,Integer> map = new HashMap<>();
        Stream<Map.Entry<String,Integer>> s4 = map.entrySet().stream();
        Stream<String> s5 = map.keySet().stream();
        Stream<Integer> s6 = map.values().stream();

        // FROM ARRAY
        String[] arr = {"a", "b","c"};
        Stream<String> s7 = Arrays.stream(arr);
        Stream<String> s8 = Arrays.stream(arr,1,3);  // Partial: ["b","c"]

        int[] intArr = {1, 2, 3, 4, 5};
        IntStream s9 = Arrays.stream(intArr);

        // FROM VALUES DIRECTLY
        Stream<String> s10 = Stream.of("a", "b", "c");
        Stream<Integer> s11 = Stream.of(1, 2, 3, 4, 5);
        Stream<String> s12 = Stream.ofNullable("hello"); // Java 9 — empty if null
        Stream<String> s13 = Stream.ofNullable(null);    // Empty stream

        // EMPTY STREAM
        Stream<String> empty = Stream.empty();

        // NUMERIC STREAMS
        IntStream r1 = IntStream.range(1, 6);        // 1,2,3,4,5 (exclusive end)
        IntStream r2 = IntStream.rangeClosed(1, 5);  // 1,2,3,4,5 (inclusive end)
        LongStream r3 = LongStream.range(1L, 100L);
        DoubleStream r4 = DoubleStream.of(1.1, 2.2, 3.3);

        // INFINITE STREAMS
        // Stream.generate — no state, each element independent
        Stream<Double> randoms = Stream.generate(Math::random);
        Stream<String> hellos = Stream.generate(() -> "hello");

        // Stream.iterate — stateful, each element depends on previous
        Stream<Integer> evens1 = Stream.iterate(0, n -> n + 2);

        // Java 9 — iterate with predicate (acts like for loop)
        Stream<Integer> bounded = Stream.iterate(0, n -> n < 10, n -> n + 1);
        // 0,1,2,3,4,5,6,7,8,9 — stops when n >= 10

        // STREAM CONCAT
        Stream<String> first = Stream.of("a", "b");
        Stream<String> second = Stream.of("c", "d");
        Stream<String> merged = Stream.concat(first, second); // a,b,c,d

        // FROM FILE (lines of file as stream)
//        Stream<String> lines = Files.lines(Paths.get("file.txt"));
//        // Must close! Use try-with-resources
//        try (Stream<String> fileLines = Files.lines(Paths.get("file.txt"))) {
//            fileLines.forEach(System.out::println);
//        }

        // FROM STRING (split by regex)
        Stream<String> words2 = Pattern.compile("\\\\s+").splitAsStream("hello world java");
        // hello, world, java

        // FROM BUFFEREDREADER
//        try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
//            Stream<String> readerLines = br.lines();
//            readerLines.forEach(System.out::println);
//        }

        // BUILDER PATTERN
        Stream<String> built = Stream.<String>builder()
                .add("a")
                .add("b")
                .add("c")
                .build();



     //----- Collectors — collect() Terminal Operation -----
        List<String> words = Arrays.asList("banana","apple","cherry","date","mango");

        // Collect to List (most common)
        List<String> list = words.stream().collect(Collectors.toList());
        System.out.println(list);

        // TO UNMODIFIABLE LIST (Java 10)
        List<String> immutableList = words.stream()
                .collect(Collectors.toUnmodifiableList());
        // immutableList.add("x"); → UnsupportedOperationException

        // TO SET
        Set<String> set = words.stream()
                .collect(Collectors.toSet());

        // Collect to specific collection
        LinkedList<String> linkedList = words.stream()
                .collect(Collectors.toCollection(LinkedList::new));

        TreeSet<String> treeSet = words.stream()
                .collect(Collectors.toCollection(TreeSet::new)); // Sorted!

        ArrayDeque<String> deque = words.stream()
                .collect(Collectors.toCollection(ArrayDeque::new));

        // JOINING
        String joined = words.stream().collect(Collectors.joining());
        // "bananaapple..."

        String withDelimiter = words.stream().collect(Collectors.joining(", "));
        // "banana, apple, ..."

        String withAll = words.stream().collect(Collectors.joining(", ", "[", "]"));
        // "[banana, apple, ...]"

        // COUNTING
        long count = words.stream()
                .collect(Collectors.counting());

        //  AVERAGING
        double avgLength = words.stream()
                .collect(Collectors.averagingInt(String::length));
        System.out.println("Avg length: " + avgLength);

        // SUMMING
        int totalLength = words.stream()
                .collect(Collectors.summingInt(String::length));

        long totalLong = words.stream()
                .collect(Collectors.summingLong(String::length));

        // STATISTICS — all at once
        IntSummaryStatistics stats = words.stream()
                .collect(Collectors.summarizingInt(String::length));
        System.out.println("Count: " + stats.getCount());
        System.out.println("Sum: " + stats.getSum());
        System.out.println("Min: " + stats.getMin());
        System.out.println("Max: " + stats.getMax());
        System.out.println("Avg: " + stats.getAverage());

        // MIN BY / MAX BY
        Optional<String> shortest = words.stream()
                .collect(Collectors.minBy(Comparator.comparingInt(String::length)));

        Optional<String> longest = words.stream()
                .collect(Collectors.maxBy(Comparator.comparingInt(String::length)));

        //GROUP BY
        Map<Character, List<String>> groupedByFirstChar = words.stream()
                .collect(Collectors.groupingBy(s -> s.charAt(0)));
        System.out.println(groupedByFirstChar);

        // Group by with downstream collector
        Map<Character, Long> countByFirstChar = words.stream()
                .collect(Collectors.groupingBy(
                        s -> s.charAt(0),
                        Collectors.counting()
                ));
        System.out.println(countByFirstChar);

        // Partition by (splits into true/false groups)
        Map<Boolean, List<String>> partitioned = words.stream()
                .collect(Collectors.partitioningBy(s -> s.length() > 5));
        System.out.println("Long words: " + partitioned.get(true));
        System.out.println("Short words: " + partitioned.get(false));

        // toMap
        // BASIC toMap(keyMapper, valueMapper)
        Map<String, Integer> wordToLength = words.stream()
                .collect(Collectors.toMap(
                        s -> s,           // key: word itself
                        String::length    // value: length
                ));
        // {apple=5, banana=6, cherry=6}

        // DANGER: duplicate keys throw IllegalStateException!
        List<String> withDup = Arrays.asList("apple", "ant");
        // Both start with 'a' — THROWS if grouping by first char with toMap!

        // FIX: toMap with MERGE FUNCTION (handles duplicate keys)
        Map<Character, String> firstCharToWord = withDup.stream()
                .collect(Collectors.toMap(
                        s -> s.charAt(0),              // key: first char
                        s -> s,                         // value: word
                        (existing, newVal) -> existing + ", " + newVal // merge duplicates
                ));
        // {a="apple, ant"}
    }
}
