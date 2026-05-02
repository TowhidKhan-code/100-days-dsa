package Day56And57_MultiThreading;

/*
----- HOW TO THINK ABOUT DEADLOCK -----
DEADLOCK CONDITIONS (all four must be true):
1. Mutual Exclusion: at least one resource held exclusively
2. Hold and Wait: thread holds resource while waiting for another
3. No Preemption: resources cannot be forcibly taken
4. Circular Wait: circular chain of threads each waiting for next

CLASSIC EXAMPLE:
Thread A holds Lock1, wants Lock2
Thread B holds Lock2, wants Lock1
Both wait forever — DEADLOCK!

PREVENTING DEADLOCK:
1. Lock ordering: always acquire locks in same order
2. Lock timeout: give up after waiting too long
3. Deadlock detection: detect and recover
4. Avoid nested locks: don't hold one lock while acquiring another

*/

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class _5_Deadlock {
    private static final Object LOCK_A = new Object();
    private static final Object LOCK_B = new Object();

    // DEADLOCK - inconsistent lock ordering
    public static void deadlockExample(){
        Thread t1 = new Thread(()->{
            synchronized (LOCK_A){
                System.out.println("T1 holds LOCK_A");
                try{Thread.sleep(100); } catch (InterruptedException e) {}
                synchronized (LOCK_B){ // Waits for LOCK_B (held by T2)
                    System.out.println("T1 holds LOCK_A and LOCK_B");
                }
            }
        },"Thread-1");

        Thread t2 = new Thread(() -> {
            synchronized (LOCK_B) { // T2 acquires LOCK_B first
                System.out.println("T2 holds LOCK_B");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                synchronized (LOCK_A) { // Waits for LOCK_A (held by T1)
                    System.out.println("T2 holds LOCK_B and LOCK_A");
                }
            }
        }, "Thread-2");

        t1.start();
        t2.start();
        //DEADLOCK! Neither thread can proceed
    }

    // FIX 1 - Consistent lock ordering
    public static void fixedWithOrdering(){
        Thread t1 = new Thread(() -> {
            synchronized (LOCK_A) { // Always LOCK_A first
                synchronized (LOCK_B) {
                    System.out.println("T1 done");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (LOCK_A) { // Always LOCK_A first (same order!)
                synchronized (LOCK_B) {
                    System.out.println("T2 done");
                }
            }
        });

        t1.start();
        t2.start();
        // No deadlock — both threads try LOCK_A first
    }

    //FIX 2 - use tryLock with timeout (from java.util.concurrent.locks)
    public static void fixedWithTryLock() {
        ReentrantLock lockA = new ReentrantLock();
        ReentrantLock lockB = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            try {
                while (true) {
                    if (lockA.tryLock(100, TimeUnit.MILLISECONDS)) {
                        try {
                            if (lockB.tryLock(100, TimeUnit.MILLISECONDS)) {
                                try {
                                    System.out.println("T1 has both locks");
                                    return;
                                } finally {
                                    lockB.unlock();
                                }
                            }
                        } finally {
                            lockA.unlock();
                        }
                    }
                    Thread.sleep(50); // Back off before retry
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        t1.start();
    }

    static void main(String[] args) {
//        deadlockExample();
//        fixedWithOrdering();
    }
}
