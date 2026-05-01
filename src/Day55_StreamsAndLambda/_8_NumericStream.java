package Day55_StreamsAndLambda;

import java.util.*;
import java.util.stream.*;

public class _8_NumericStream {
    public static void main(String[] args) {
        // WHY PRIMITIVE STREAMS?
        // Stream<Integer>: each int boxed as Integer object — heap allocation
        // IntStream: uses primitive int — no boxing — much faster for numeric ops


        //IntStream
        IntStream.range(1,6).forEach(System.out::println); // 6 is exclusive
        IntStream.rangeClosed(1,6).forEach(System.out::println); // 6 is inclusive

        int sum = IntStream.rangeClosed(1,100).sum();
        System.out.println(sum);
        OptionalDouble avg = IntStream.of(1,2,3,4,5).average();
        System.out.println(avg.getAsDouble());

        //mapToInt: Convert Stream<String> to IntStream
        // Converts to primitive stream — avoids boxing overhead
        List<String> words = Arrays.asList("hello", "world", "java");
        int totalLength = words.stream()
                .mapToInt(String::length)
                .sum(); // Direct sum on IntStream — no boxing!
        System.out.println("Total Length: " + totalLength);

        IntSummaryStatistics stats = words.stream()
                .mapToInt(String::length)
                .summaryStatistics();

        // MAPTOLONG — Stream<T> → LongStream
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        LongStream longStream = numbers.stream()
                .mapToLong(Integer::longValue);

        // MAPTODOUBLE — Stream<T> → DoubleStream
        DoubleStream doubleStream = numbers.stream()
                .mapToDouble(Integer::doubleValue);

        // MAPTOOBJ — IntStream → Stream<T> (reverse of mapToInt)
        Stream<String> strings = IntStream.rangeClosed(1, 5)
                .mapToObj(i -> "Item " + i);// ["Item 1", "Item 2", "Item 3", "Item 4", "Item 5"]

        // boxed: convert IntStream to Stream<Integer>
        List<Integer> list = IntStream.rangeClosed(1, 5)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(list); // [1, 2, 3, 4, 5]


        // Generate infinite streams
        Stream<Integer> ones = Stream.generate(() -> 1);
        ones.limit(5).forEach(System.out::print); // 11111

        Stream<Integer> counting = Stream.iterate(1, n -> n + 1);
        counting.limit(10).forEach(System.out::print); // 12345678910


    }
}
