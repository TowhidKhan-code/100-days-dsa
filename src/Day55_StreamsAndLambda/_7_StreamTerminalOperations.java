package Day55_StreamsAndLambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class _7_StreamTerminalOperations {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5,3,8,1,9,2,7,4,6);
        List<String> words = Arrays.asList("banana","apple","cherry","date","mango");

        // COLLECT — most important terminal operation
        // Discussed in detail in Collectors section in _5_StreamDemo

        // FOREACH — consume each element, no return
        numbers.stream().forEach(System.out::println);

        // FOREACHORDERED — processes in encounter order (important for parallel!)
        numbers.parallelStream().forEach(System.out::println);        // Random order!
        numbers.parallelStream().forEachOrdered(System.out::println); // Maintains order

        // FOR SEQUENTIAL STREAMS: forEach and forEachOrdered behave the same
        // FOR PARALLEL STREAMS: use forEachOrdered to maintain order

        //COUNT
        long count = numbers.stream()
                .filter(n -> n>5)
                .count();
        System.out.println("Count > 5: " + count); // 4

        // MIN and MAX — return Optional (might be empty stream)
        Optional<Integer> min = numbers.stream().min(Comparator.naturalOrder());
        Optional<Integer> max = numbers.stream().max(Comparator.naturalOrder());

        min.ifPresent(m -> System.out.println("Min: " + m));

        // Min/Max of objects
        Optional<String> shortestWord = words.stream()
                .min(Comparator.comparingInt(String::length));

        Optional<String> longestWord = words.stream()
                .max(Comparator.comparingInt(String::length));

        // FINDFIRST — first element of stream (Optional)
        Optional<Integer> first = numbers.stream()
                .filter(n -> n > 5)
                .findFirst(); // Returns 6 (first matching in encounter order)

        // FINDANY — any element (Optional) — faster in parallel
        Optional<Integer> any = numbers.parallelStream()
                .filter(n -> n > 5)
                .findAny(); // Returns any element > 5 (non-deterministic in parallel)

        // findFirst vs findAny:
        // Sequential: both return same element
        // Parallel: findFirst always returns first in order (slower)
        //           findAny returns whichever is found first (faster)

        //anyMatch, allMatch, noneMatch
        // SHORT-CIRCUIT operations — stop as soon as result is determined

        // ANYMATCH — returns true if ANY element matches
        boolean anyEven = numbers.stream().anyMatch(n -> n % 2 == 0); // true
        // Stops at first even number found — does not process all

        // ALLMATCH — returns true if ALL elements match
        boolean allPositive = numbers.stream().allMatch(n -> n > 0); // true
        // Stops at first non-matching — does not process all

        // NONEMATCH — returns true if NO elements match
        boolean noneNegative = numbers.stream().noneMatch(n -> n < 0); // true
        // Stops at first matching element

        // EMPTY STREAM BEHAVIOR:
        boolean p = Stream.empty().anyMatch(x -> true);  // false (no elements)
        boolean q = Stream.empty().allMatch(x -> false); // true (vacuously true!)
        boolean r = Stream.empty().noneMatch(x -> true); // true (no elements match)

        // Common use:
        List<String> emails = Arrays.asList("a@b.com", "invalid", "x@y.com");
        boolean allValid = emails.stream()
                .allMatch(e -> e.contains("@"));
        System.out.println(allValid); // false

        // REDUCE — aggregate stream to single value

        // Form 1: reduce(identity, accumulator)
        // identity = starting value, always returns value (not Optional)
        int sum = numbers.stream()
                .reduce(0, Integer::sum);              // 0+1+2+...+10 = 55

        int product = numbers.stream()
                .reduce(1, (a, b) -> a * b);          // 1*2*3*...*10

        int max1 = numbers.stream()
                .reduce(Integer.MIN_VALUE, Integer::max);

        String concat = words.stream()
                .reduce("", (a, b) -> a + b);         // "helloworld..."

        String concatWithSep = words.stream()
                .reduce("", (a, b) -> a.isEmpty() ? b : a + ", " + b);

        // Form 2: reduce(accumulator) — no identity
        // Returns Optional (stream might be empty)
        Optional<Integer> sumOpt = numbers.stream()
                .reduce((a, b) -> a + b);

        Optional<Integer> maxOpt = numbers.stream()
                .reduce(Integer::max);

        // Form 3: reduce(identity, accumulator, combiner)
        // For PARALLEL streams — combiner merges partial results
        int parallelSum = numbers.parallelStream()
                .reduce(0,
                        (a, b) -> a + b,    // accumulator
                        (a, b) -> a + b);   // combiner (merges partial results)

        // HOW REDUCE WORKS:
        // reduce(0, +) on [1,2,3,4,5]:
        // Start: 0
        // Step 1: 0 + 1 = 1
        // Step 2: 1 + 2 = 3
        // Step 3: 3 + 3 = 6
        // Step 4: 6 + 4 = 10
        // Step 5: 10 + 5 = 15
        // Result: 15

        // TOARRAY — convert stream to array

        // Object array
        Object[] objArray = numbers.stream().toArray();

        // Typed array (preferred)
        Integer[] integerArray = numbers.stream().toArray(Integer[]::new);
        String[] stringArray = words.stream().toArray(String[]::new);

        // With transformation
        String[] upperArray = words.stream()
                .map(String::toUpperCase)
                .toArray(String[]::new);

        // IntStream to int[]
        int[] primitiveArray = IntStream.rangeClosed(1, 5).toArray();// [1, 2, 3, 4, 5]


    }
}
