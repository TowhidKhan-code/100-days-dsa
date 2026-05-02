package Day56And57_MultiThreading;

/*
Synchronization is the mechanism to control access to shared resources by multiple threads.
Without synchronization, concurrent access to shared data causes race conditions
where the final result depends on thread scheduling.
Java synchronization uses monitor locks — every object has an intrinsic lock.
Only one thread can hold an object's lock at a time.
*/


// RACE CONDITION - THE PROBLEM
class Counter{
    int count = 0; // Shared mutable states

    void increment(){
        count++; // NOT atomic! Three operations:
                 // 1. Read count (e.g., 5)
                 // 2. Add 1 (get 6)
                 // 3. Write back (store 6)
    }
}

// WITHOUT SYNCHRONIZATION:
// Thread A reads count = 5
// Thread B reads count = 5 (before A writes!)
// Thread A writes 6
// Thread B writes 6
// Expected: 7, Got: 6 — RACE CONDITION!


// SYNCHRONIZED METHOD
class SynchronizedCounter{
    private int count = 0;

    // Synchronized method — only one thread can execute at a time
    public synchronized void increment() {
        count++;
        // Implicitly: lock acquired on 'this' object
        // After return: lock released
    }

    public synchronized void decrement() {
        count--;
    }

    // Synchronized getter ensures visibility too
    public synchronized int getCount() {
        return count;
    }
}
// HOW IT WORKS:
// Thread A calls increment() → acquires lock on counter object
// Thread B calls increment() → tries to acquire lock → BLOCKED
// Thread A finishes → releases lock
// Thread B acquires lock → executes
// Result: always correct!

// STATIC SYNCHRONIZED METHOD — locks on Class object
class StaticSync {
    private static int staticCount = 0;

    public static synchronized void incrementStatic() {
        staticCount++;
        // Locks on StaticSync.class, not on 'this'
    }

    public static int getStaticCount() {
        return staticCount;
    }
}

// SYNCHRONIZED BLOCK
class BankAccount {
    private double balance;
    private String accountId;
    private final Object lock = new Object(); // Explicit lock object

    public BankAccount(String id, double initialBalance) {
        this.accountId = id;
        this.balance = initialBalance;
    }

    // Synchronized on this — entire method locked
    public synchronized void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
        balance += amount;
    }

    // Synchronized block — more granular control
    public void withdraw(double amount) {
        // Validation outside sync block (no lock needed)
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");

        synchronized (this) { // Lock only the critical section
            if (balance < amount) {
                throw new IllegalStateException("Insufficient funds");
            }
            balance -= amount;
        }
        // Other non-critical code here runs without lock
        System.out.println("Withdrew: " + amount);
    }

    // Using explicit lock object
    public void transfer(BankAccount other, double amount) {
        // Lock ordering: always lock lower accountId first (prevents deadlock)
        BankAccount first = accountId.compareTo(other.accountId) < 0 ? this : other;
        BankAccount second = first == this ? other : this;

        synchronized (first) {
            synchronized (second) {
                this.balance -= amount;
                other.balance += amount;
            }
        }
    }

    public double getBalance() {
        synchronized (this) {
            return balance;
        }
    }
}

/*
synchronized method vs synchronized block:
Method: entire method is synchronized (coarse-grained)
Block:  only specific code is synchronized (fine-grained)

Benefits of synchronized block:
→ Lock only what you need to protect
→ Reduce time holding lock
→ Better throughput
→ Can use different lock objects for different data
*/

//VOLATILE KEYWORD
class VolatileDemo {
    /*
    WITHOUT volatile:
    → Each thread has its own CPU cache
    → Changes to variable may not be visible to other threads
    → Thread A writes flag = true to its cache
    → Thread B reads flag = false from its cache (stale!)

    WITH volatile:
    → All reads/writes go directly to main memory
    → Changes immediately visible to all threads
    → Prevents CPU caching of this variable
    → Also prevents instruction reordering around this variable

    volatile does NOT make operations atomic!
    count++ is still not safe with volatile
    Use AtomicInteger for atomic operations
    */

    private volatile boolean flag = false;
    private volatile int value = 0;

    public void writer() {
        value = 42;         // Write to value
        flag = true;        // Signal reader (volatile write)
    }

    public void reader() {
        while (!flag) {     // Wait for signal (volatile read)
            // Spin wait — inefficient, but demonstrates concept
        }
        System.out.println("Value: " + value); // Always sees 42!
        // Without volatile: might see flag=true but value=0 (stale)
    }

    // CLASSIC USE CASE: stop flag for threads
    class WorkerThread extends Thread {
        private volatile boolean running = true;

        @Override
        public void run() {
            while (running) {
                doWork();
            }
            System.out.println("Worker stopped");
        }

        public void stopWorker() {
            running = false; // Volatile ensures other threads see this
        }

        private void doWork() {
            // Do actual work
        }
    }
}


public class _3_Synchronization {
    public static void main(String[] args) throws InterruptedException {
        //RACE CONDITION DEMO
        Counter counter = new Counter();
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // Expected: 20000, Actual: LESS THAN 20000 (race condition!)
        System.out.println("Count: " + counter.count);

        // Synchronized Method DEMO
        SynchronizedCounter counter2 = new SynchronizedCounter();
        Thread t3 = new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                counter2.increment();
            }
        });

        Thread t4 = new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                counter2.increment();
            }
        });
        t3.start();
        t4.start();
        t3.join();
        t4.join();

        System.out.println("Count: " + counter2.getCount()); // Correct: 20000

        //Static Synchronized Method Demo
        Thread t5 = new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                StaticSync.incrementStatic();
            }
        });

        Thread t6 = new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                StaticSync.incrementStatic();
            }
        });
        t5.start();
        t6.start();
        t5.join();
        t6.join();
        System.out.println("Count: " + StaticSync.getStaticCount()); //Correct: 20000
    }
}
