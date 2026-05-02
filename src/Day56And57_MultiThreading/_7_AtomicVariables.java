package Day56And57_MultiThreading;

import java.util.concurrent.atomic.*;

public class _7_AtomicVariables {
    public static void main(String[] args) throws InterruptedException {

        //ATOMICINTEGER - thread safe integer operations
        AtomicInteger atomicInt = new AtomicInteger(0);
        atomicInt.get();                         // Read
        atomicInt.set(10);                       // Write
        atomicInt.getAndSet(20);        // Read then write (returns old)
        atomicInt.incrementAndGet();             // ++count (returns new value)
        atomicInt.getAndIncrement();             // count++ (returns old value)
        atomicInt.decrementAndGet();             // --count
        atomicInt.addAndGet(5);            // count += 5 (returns new)
        atomicInt.getAndAdd(5);            // count += 5 (returns old)

        // COMPARE AND SWAP (CAS) — the core operation
        // If current == expected → set to update, return true
        // If current != expected → do nothing, return false
        boolean success = atomicInt.compareAndSet(20, 30);
        // If atomicInt is 20, set to 30 and return true
        // Otherwise return false

        // Thread-safe counter without synchronization
        AtomicInteger counter = new AtomicInteger(0);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) counter.incrementAndGet();
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) counter.incrementAndGet();
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Counter: " + counter.get()); // Always 20000!

        // ATOMICLONG
        AtomicLong atomicLong = new AtomicLong(0L);
        atomicLong.incrementAndGet();

        // ATOMICBOOLEAN
        AtomicBoolean atomicBool = new AtomicBoolean(false);
        atomicBool.compareAndSet(false, true); // Set to true if currently false

        // ATOMICREFERENCE — thread-safe object reference
        AtomicReference<String> atomicRef = new AtomicReference<>("initial");
        atomicRef.compareAndSet("initial", "updated"); // CAS on object

        // ATOMICINTEGERARRAY
        AtomicIntegerArray atomicArr = new AtomicIntegerArray(10);
        atomicArr.set(0, 5);
        atomicArr.getAndIncrement(0);

        // LONGADDER — high-throughput counter (better than AtomicLong under contention)
        LongAdder adder = new LongAdder();
        adder.increment();
        adder.add(5);
        long total = adder.sum();
        // LongAdder maintains multiple cells — reduces contention
        // sum() is approximate (for counters)
        // Use AtomicLong when you need exact CAS operations
        // Use LongAdder when you just need to add and get total

        // LONGACCUMULATOR — generalized LongAdder
        LongAccumulator max = new LongAccumulator(Long::max, Long.MIN_VALUE);
        max.accumulate(5);
        max.accumulate(10);
        max.accumulate(3);
        System.out.println(max.get()); // 10
    }
}
