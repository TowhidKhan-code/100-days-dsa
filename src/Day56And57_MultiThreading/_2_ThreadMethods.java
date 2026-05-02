package Day56And57_MultiThreading;

public class _2_ThreadMethods {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("Thread running");
           try{
               Thread.sleep(2000);
           } catch (InterruptedException e) {
               System.out.println("Thread interrupted!");
               Thread.currentThread().interrupt();
           }
        },"MyThread");

        //START - begin thread execution
        thread.start();

        //GET NAME / SET NAME
        System.out.println(thread.getName()); // MyThread
        thread.setName("RenamedThread");

        //GET ID - unique long ID
        System.out.println(thread.getId());

        // IS ALIVE — returns true if thread started and not terminated
        System.out.println(thread.isAlive()); // true while running

        // GET STATE
        System.out.println(thread.getState()); // RUNNABLE, SLEEPING, etc.

        // PRIORITY (1=MIN, 5=NORMAL, 10=MAX)
        thread.setPriority(Thread.MAX_PRIORITY); // 10
        thread.setPriority(Thread.MIN_PRIORITY); // 1
        thread.setPriority(Thread.NORM_PRIORITY); // 5
        System.out.println(thread.getPriority());

        // DAEMON THREAD
        // Daemon threads: background threads that die when all non-daemon threads die
        // JVM exits when only daemon threads remain
        Thread daemon = new Thread(() -> {
            while (true) {
                System.out.println("Daemon running");
                try { Thread.sleep(500); }
                catch (InterruptedException e) { break; }
            }
        });
        daemon.setDaemon(true);  // Must set BEFORE start()
        daemon.start();
        // When main thread ends, daemon thread dies automatically

        // JOIN — wait for thread to finish
        thread.join();       // Wait indefinitely
        // thread.join(1000); // Wait at most 1000ms

        // INTERRUPT — signal thread to stop
        thread.interrupt();
        // Sets interrupt flag — thread must check it

        // CHECK INTERRUPT
        if (Thread.currentThread().isInterrupted()) {
            // Thread was interrupted
        }

        // SLEEP — pause current thread
        Thread.sleep(1000); // Pause 1 second
        // Does NOT release locks during sleep!

        // YIELD — hint to scheduler to give other threads a chance
        Thread.yield();
        // Moves from running to runnable — scheduler may ignore

        // CURRENT THREAD
        Thread current = Thread.currentThread();
        System.out.println(current.getName()); // main

        // GET ALL ACTIVE THREADS
        int activeCount = Thread.activeCount();
        Thread[] allThreads = new Thread[activeCount];
        Thread.enumerate(allThreads);
    }
}

/*
sleep(ms):
→ Called on Thread class (static): Thread.sleep(ms)
→ Pauses current thread for specified time
→ Does NOT release any locks held
→ Throws InterruptedException
→ After timeout: goes to RUNNABLE
→ Use: pause execution for fixed time

wait():
→ Called on Object: object.wait()
→ Must be inside synchronized block
→ RELEASES the lock on that object
→ Thread goes to WAITING state
→ Woken by notify() or notifyAll()
        → Use: wait for condition to be true

yield():
→ Called on Thread class (static): Thread.yield()
→ Suggests scheduler to run other threads
→ Does NOT release locks
→ Thread goes back to RUNNABLE immediately
→ Scheduler may ignore hint
→ Use: give other equal-priority threads a chance
*/