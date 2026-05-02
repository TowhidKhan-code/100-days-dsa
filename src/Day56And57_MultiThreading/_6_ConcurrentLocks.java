package Day56And57_MultiThreading;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

//  ReentrantLock
class ReentrantLockDemo {
    private ReentrantLock lock = new ReentrantLock();
    private int count = 0;

    public void increment(){
        lock.lock(); // Acquire lock
        try{
            count++;
        }finally {
            lock.unlock(); // ALWAYS unlock in finally!
        }
    }

    public void tryIncrement(){
        if(lock.tryLock()){ // Non-blocking attempt
            try{
                count++;
            }finally {
                lock.unlock();
            }
        }else{
            System.out.println("Could not acquire lock - skipping");
        }
    }

    public void tryIncrementWithTimeout() throws InterruptedException {
        if (lock.tryLock(1, TimeUnit.SECONDS)) { // Wait at most 1 second
            try {
                count++;
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("Timeout waiting for lock");
        }
    }

    // Check lock state
    public void lockInfo() {
        System.out.println("Is locked: " + lock.isLocked());
        System.out.println("Lock count: " + lock.getHoldCount());
        System.out.println("Is held by current: " + lock.isHeldByCurrentThread());
        System.out.println("Waiting threads: " + lock.getQueueLength());
    }
}

/*
ReentrantLock vs synchronized:
synchronized:
→ Simple, automatic unlock
→ Cannot interrupt while waiting
→ Cannot check if locked
→ No timeout on lock acquisition

ReentrantLock:
→ Must manually lock/unlock (use try/finally)
→ Can interrupt waiting thread (lockInterruptibly)
→ Can try lock with timeout (tryLock)
→ Can check lock state (isLocked, getHoldCount)
→ Supports fair ordering (FIFO)
→ More control but more complex

Reentrancy: thread can acquire lock it already holds
synchronized: reentrant by default
ReentrantLock: reentrant by design (name says it)
*/

// ReadWriteLock
class Cache{
    private Map<String, String> data = new HashMap<>();
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private Lock readLock = rwLock.readLock();
    private Lock writeLock = rwLock.writeLock();

    /*
    ReadWriteLock allows:
    → Multiple readers simultaneously (no writer active)
    → Only one writer at a time (no readers active)

    Use when: reads are frequent, writes are rare
    Example: cache, configuration, lookup tables
    */

    public String get(String key) {
        readLock.lock(); // Multiple threads can read simultaneously
        try {
            return data.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public void put(String key, String value) {
        writeLock.lock(); // Exclusive write — no readers or writers
        try {
            data.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public boolean containsKey(String key) {
        readLock.lock();
        try {
            return data.containsKey(key);
        } finally {
            readLock.unlock();
        }
    }
}

//Condition Variables
class BoundedBuffer<T> {
    private Queue<T> queue = new LinkedList<>();
    private int capacity;
    private ReentrantLock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();  // Condition: buffer not full
    private Condition notEmpty = lock.newCondition(); // Condition: buffer not empty

    /*
    Condition is more flexible than wait/notify:
    → Multiple conditions per lock (wait/notify: one per object)
    → Clear semantics: notFull.await(), notEmpty.signal()
    → Works with ReentrantLock
    */

    public BoundedBuffer(int capacity) {
        this.capacity = capacity;
    }

    public void put(T item) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                notFull.await(); // Wait for not full condition
            }
            queue.add(item);
            notEmpty.signal(); // Signal: buffer now not empty
        } finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                notEmpty.await(); // Wait for not empty condition
            }
            T item = queue.poll();
            notFull.signal(); // Signal: buffer now not full
            return item;
        } finally {
            lock.unlock();
        }
    }
}


public class _6_ConcurrentLocks {
    public static void main(String[] args) {

    }
}
