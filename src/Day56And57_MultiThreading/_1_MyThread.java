package Day56And57_MultiThreading;
/*
Multithreading is a programming technique where
multiple threads execute concurrently within a single process sharing the same memory space.
A thread is the smallest unit of execution within a process.
Java provides built-in support for multithreading through the java.lang.Thread class
and the java.util.concurrent package.
Multithreading improves application performance by utilizing multiple CPU cores
and keeping the CPU busy during IO operations.

---HOW TO THINK ABOUT MULTITHREADING:---
PROCESS vs THREAD:
Process:
→ Independent program in execution
→ Has its own memory space
→ Heavy to create and destroy
→ Processes do not share memory

Thread:
→ Lightweight unit within a process
→ Shares memory with other threads in same process
→ Light to create and destroy
→ Threads share heap, static variables, open files

WHY MULTITHREADING?
→ Better CPU utilization (use multiple cores)
→ Responsive UI (background tasks don't freeze UI)
→ Faster IO (thread waits for IO, another thread runs)
→ Parallel processing of independent tasks

CHALLENGES:
→ Race conditions (two threads modify same data)
→ Deadlock (two threads wait for each other forever)
→ Starvation (thread never gets CPU time)
→ Livelock (threads keep responding to each other)
→ Visibility (one thread's changes not seen by another)
→ Ordering (CPU and compiler reorder instructions)

---THREAD LIFECYCLE---
THREAD STATES:
NEW:
→ Thread object created but start() not called
→ Thread thread = new Thread(runnable);

RUNNABLE:
→ After start() called
→ Thread may be running or waiting for CPU
→ JVM schedules threads — no guarantee which runs

BLOCKED:
→ Waiting to acquire a monitor lock (synchronized block)
→ Another thread holds the lock

WAITING:
→ Waiting indefinitely for another thread
→ Caused by: wait(), join(), LockSupport.park()
→ Must be explicitly notified to resume

TIMED_WAITING:
→ Waiting for a specified time
→ Caused by: sleep(ms), wait(ms), join(ms)
→ Resumes after timeout or explicit notification

TERMINATED:
→ Thread finished execution (run() returned)
→ OR uncaught exception thrown
→ Cannot be restarted once terminated

TRANSITIONS:
NEW → RUNNABLE: start()
RUNNABLE → BLOCKED: trying to enter synchronized block
RUNNABLE → WAITING: wait(), join()
RUNNABLE → TIMED_WAITING: sleep(), wait(ms)
BLOCKED → RUNNABLE: lock acquired
WAITING → RUNNABLE: notify(), notifyAll(), interrupt()
TIMED_WAITING → RUNNABLE: timeout or notification
RUNNABLE → TERMINATED: run() completes

*/

//CREATING THREADS :

import java.util.concurrent.*;

//Method 1 — Extending Thread Class
class MyThread extends Thread{
    private String name;

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        //Code to execute in this thread
        for (int i = 0; i < 5; i++) {
            System.out.println(name + " - Count: " + i + " [Thread: " + Thread.currentThread().getName() + "]");
            try{
                Thread.sleep(100); // Pause 100ms
            } catch (InterruptedException e) {
                System.out.println(name + " interrupted!");
                Thread.currentThread().interrupt(); // Restore interrupt flag
                return;
            }
        }
    }
}
/*
PROBLEM WITH EXTENDING THREAD:
→ Java has single inheritance
→ If your class extends Thread, it cannot extend anything else
→ Extending Thread means your class IS-A Thread
→ But really your class HAS-A task to run
→ Better to use Runnable (more flexible)
*/

//Method 2 — Implementing Runnable
class PrintTask implements Runnable{
    private String message;
    private int count;

    public PrintTask(String message,int count){
        this.message = message;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println(message + " - " + i + " [" + Thread.currentThread().getName() + "]");
            try{
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}

//METHOD 3 : Implementing Callable<V> (Returns Result)
class SumTask implements Callable<Long>{
    private int start;
    private int end;

    public SumTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Long call() throws Exception {
        long sum = 0;
        for (int i = start; i <= end; i++) {
            sum+=i;
            Thread.sleep(1); // Simulate work
        }
        System.out.println("Sum from " + start + " to " + end +
                " = " + sum + " [" + Thread.currentThread().getName() + "]");
        return sum;
    }
}


public class _1_MyThread{
    public static void main(String[] args) {

        //METHOD 1: Extending Thread Class
        MyThread t1 = new MyThread("Thread-A");
        MyThread t2 = new MyThread("Thread-B");

        t1.start(); // Start thread - do NOT call run() directly
        t2.start();

        //t1.run() - WRONG! Runs on main thread, not new thread

        System.out.println("Main thread continues...");

        //Wait for threads to finish
        try{
            t1.join(); // Main thread waits for t1 to finish
            t2.join(); // Main thread waits for t2 to finish
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Both threads finished!");

        //METHOD 2: Implementing Runnable Interface
        Runnable task1 = new PrintTask("Hello", 5);
        Runnable task2 = new PrintTask("World", 5);

        Thread t3 = new Thread(task1,"Thread-A");
        Thread t4 = new Thread(task2,"Thread-B");

        t3.start();
        t4.start();

        // Using lambda (Runnable is a functional interface
        Thread t5 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("Lambda thread: " + i);
            }
        },"Lambda-Thread");
        t5.start();
        try {
            t3.join();
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        //METHOD 3: Implementing Callable<V> Interface
        ExecutorService executor = Executors.newFixedThreadPool(3);

        //Submit Callable - returns Future
        Future<Long> future1 = executor.submit(new SumTask(1,100));
        Future<Long> future2 = executor.submit(new SumTask(101,200));
        Future<Long> future3 = executor.submit(new SumTask(201,300));

        // Get results (blocks until done)
        Long result1,result2,result3 = null; // Waits for completion
        try {
            result1 = future1.get();
            result2 = future2.get();
            result3 = future3.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }


        System.out.println("Total: " + (result1 + result2 + result3));

        executor.shutdown();

        /*
        Runnable vs Callable:
        Runnable: void run()  — no return, no checked exception
        Callable: T call()    — returns value, can throw checked exception
        */

        //METHOD 4: Lambda Thread
        // Single task
        Thread t6 = new Thread(() -> System.out.println("Simple lambda thread"));

        // Complex task
        Thread t7 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Count: " + i);
                try { Thread.sleep(100); }
                catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        });

        t6.start();
        t7.start();
        try {
            t6.join();
            t7.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
