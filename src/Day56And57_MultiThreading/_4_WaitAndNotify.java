package Day56And57_MultiThreading;

import java.util.LinkedList;
import java.util.Queue;

/*
---HOW TO THINK ABOUT WAIT/NOTIFY: ---

PRODUCER-CONSUMER PROBLEM:
→ Producer creates data, puts in buffer
→ Consumer takes data from buffer
→ Buffer has limited capacity

WITHOUT COORDINATION:
→ Consumer tries to get from empty buffer → error
→ Producer tries to put in full buffer → error

WITH wait/notify:
→ Consumer: if buffer empty → wait()
→ Producer: after adding → notify()
→ Producer: if buffer full → wait()
→ Consumer: after removing → notify()

RULES:
→ wait() and notify() must be called inside synchronized block
→ wait() on the SAME object as the synchronized lock
→ wait() releases the lock while waiting
→ notify() wakes one waiting thread
→ notifyAll() wakes all waiting threads
→ Woken thread must reacquire lock before continuing
→ ALWAYS use while loop, not if, to check condition
 */

class SharedBuffer{
    private Queue<Integer> buffer = new LinkedList<>();
    private int maxSize;

    public SharedBuffer(int maxSize){
        this.maxSize = maxSize;
    }

    // Producer calls this
    public synchronized void produce(int item) throws InterruptedException {
        // WHILE (not if) — guard against spurious wakeups
        while(buffer.size() == maxSize){
            System.out.println("Buffer full,producer waiting...");
            wait(); // Release lock, wait for consumer to notify
        }
        buffer.add(item);
        System.out.println("Produced: " + item + " | Buffer: " + buffer);
        notifyAll(); // Wake up waiting consumers
    }

    // Consumer calls this
    public synchronized int consume() throws InterruptedException {
        // WHILE (not if) — guard against spurious wakeups
        while (buffer.isEmpty()) {
            System.out.println("Buffer empty, consumer waiting...");
            wait(); // Release lock, wait for producer to notify
        }

        int item = buffer.poll();
        System.out.println("Consumed: " + item + " | Buffer: " + buffer);
        notifyAll(); // Wake up waiting producers
        return item;
    }
}

class Producer implements Runnable{
    private SharedBuffer buffer;

    public Producer(SharedBuffer buffer){
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10 ; i++) {
            try{
                buffer.produce(i);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}

class Consumer implements Runnable {
    private SharedBuffer buffer;

    public Consumer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                buffer.consume();
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}


public class _4_WaitAndNotify {
    public static void main(String[] args) throws InterruptedException {
        SharedBuffer buffer = new SharedBuffer(3);
        Thread producer = new Thread(new Producer(buffer),"Producer");
        Thread consumer = new Thread(new Consumer(buffer),"Consumer");
        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }
}

/*
WHY WHILE NOT IF?
if (buffer.isEmpty()) {
    wait();         // Thread wakes up
    consume();      // Assumes buffer still has item — WRONG!
}

Between wait() releasing lock and thread reacquiring lock,
another consumer might have taken the item!
while re-checks the condition after wakeup — safe!

SPURIOUS WAKEUPS:
Thread can wake up from wait() without being notified
(platform-specific behavior)
while loop handles this correctly (re-checks condition)
if does not (assumes condition changed — bug!)
*/
