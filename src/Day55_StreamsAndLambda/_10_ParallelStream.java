package Day55_StreamsAndLambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
Parallel streams divide the stream into multiple substreams
and process them concurrently using multiple threads from the ForkJoinPool.
They can significantly improve performance for large datasets with CPU-intensive operations
but can cause issues with stateful operations, ordered outputs, and thread safety.

HOW PARALLEL STREAMS WORK:
1. Stream is split into multiple chunks (using Spliterator)
2. Each chunk processed by a separate thread
3. Threads come from ForkJoinPool.commonPool()
4. Results are merged (combined) at end

DEFAULT THREAD POOL:
ForkJoinPool.commonPool()
Number of threads = number of CPU cores - 1
Can be configured with system property or custom pool

WHEN PARALLEL HELPS:
✓ Large data sets (thousands+ elements)
✓ CPU-intensive operations (complex computations)
✓ Operations that are independent (no shared state)
✓ Associative operations (order doesn't matter)
✓ Unordered collections (HashSet faster than ArrayList parallel)

WHEN PARALLEL HURTS:
✗ Small data sets (thread overhead > benefit)
✗ Operations with side effects (shared mutable state)
✗ Operations requiring order (sorted, findFirst slow in parallel)
✗ IO-bound operations (threads block waiting for IO)
✗ Short operations (overhead not worth it)
✗ Operations on sequential sources (LinkedList hard to split)

*/

public class _10_ParallelStream {
    public static void main(String[] args) {
        List<Integer> numbers = IntStream.rangeClosed(1, 1_000_000)
                .boxed()
                .collect(Collectors.toList());

        // CREATE PARALLEL STREAM
        Stream<Integer> parallel1 = numbers.parallelStream();
        Stream<Integer> parallel2 = numbers.stream().parallel();

        // CHECK IF PARALLEL
        boolean isParallel = numbers.parallelStream().isParallel(); // true
        boolean isSeq = numbers.stream().isParallel();              // false

        // CONVERT BACK TO SEQUENTIAL
        Stream<Integer> sequential = numbers.parallelStream().sequential();

        // SUM using parallel (associative operation — safe!)
        long sum = numbers.parallelStream()
                .mapToLong(Integer::longValue)
                .sum();
        System.out.println("Sum: " + sum);

        // PARALLEL vs SEQUENTIAL timing
        long start = System.currentTimeMillis();
        long seqSum = numbers.stream()
                .mapToLong(n -> expensiveComputation(n))
                .sum();
        System.out.println("Sequential: " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        long parSum = numbers.parallelStream()
                .mapToLong(n -> expensiveComputation(n))
                .sum();
        System.out.println("Parallel: " + (System.currentTimeMillis() - start) + "ms");

        // ORDER ISSUES with parallel
        System.out.println("forEach (random order) ");
        numbers.subList(0, 10).parallelStream()
                .forEach(System.out::println); // Random order!

        System.out.println("forEachOrdered (maintains order) ");
        numbers.subList(0, 10).parallelStream()
                .forEachOrdered(System.out::println); // Correct order, slower

        // findFirst vs findAny in parallel
        Optional<Integer> first = numbers.parallelStream()
                .filter(n -> n > 500_000)
                .findFirst();  // Returns 500001 (first in order — slower in parallel)

        Optional<Integer> any = numbers.parallelStream()
                .filter(n -> n > 500_000)
                .findAny();    // Returns any match (faster in parallel — non-deterministic)
        System.out.println(first.get() + " " + any.get());


        // Thread Safety Issues with Parallel Streams
        List<Integer> numbers2 = IntStream.rangeClosed(1, 10000)
                .boxed().collect(Collectors.toList());

        // WRONG — shared mutable state (race condition!)
        List<Integer> unsafeResult = new ArrayList<>();
        numbers2.parallelStream()
                .filter(n -> n % 2 == 0)
                .forEach(unsafeResult::add); // Multiple threads writing to ArrayList!
        System.out.println("Unsafe size: " + unsafeResult.size()); // WRONG SIZE!

        // CORRECT — use collect() (thread-safe)
        List<Integer> safeResult = numbers2.parallelStream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList()); // Collectors handle thread safety
        System.out.println("Safe size: " + safeResult.size()); // Correct!

        // CORRECT — use thread-safe collection
        List<Integer> syncResult = new CopyOnWriteArrayList<>();
        numbers2.parallelStream()
                .filter(n -> n % 2 == 0)
                .forEach(syncResult::add); // Thread-safe but slow

        // WRONG — stateful lambda
        int[] count = {0};
        numbers2.parallelStream()
                .forEach(n -> count[0]++); // Race condition on count[0]!
        System.out.println("Wrong count: " + count[0]);

        // CORRECT — use count() terminal operation
        long correctCount = numbers2.parallelStream()
                .filter(n -> n % 2 == 0)
                .count();
        System.out.println("Correct count: " + correctCount);

        // CORRECT — AtomicInteger for thread-safe counting
        AtomicInteger atomicCount = new AtomicInteger(0);
        numbers2.parallelStream()
                .filter(n -> n % 2 == 0)
                .forEach(n -> atomicCount.incrementAndGet());
        System.out.println("Atomic count: " + atomicCount.get());

        //Custom ForkJoinPool for Parallel Streams

        // By default: parallel streams use common ForkJoinPool
        // This pool is shared by ALL parallel streams in the JVM
        // For more control: use custom ForkJoinPool

        ForkJoinPool customPool = new ForkJoinPool(4); // 4 threads

        try {
            List<Integer> result = customPool.submit(() ->
                    numbers.parallelStream()
                            .filter(n -> n % 2 == 0)
                            .collect(Collectors.toList())
            ).get();

            System.out.println("Result size: " + result.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            customPool.shutdown();
        }

        // WHY CUSTOM POOL?
        // Isolate parallel stream work from other parallel operations
        // Control number of threads for specific tasks
        // Avoid starving common pool
    }

    static long expensiveComputation(long n) {
        // Simulate CPU-intensive work
        return (long) Math.sqrt(n) * n;
    }
}
