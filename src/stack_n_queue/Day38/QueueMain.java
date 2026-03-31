package stack_n_queue.Day38;

public class QueueMain {

    public static void main(String[] args) throws Exception {

        System.out.println("=== Testing Custom Queue ===");
        CustomQueue<Integer> basicQueue = new CustomQueue<>(5);
        basicQueue.add(1);
        basicQueue.add(2);
        basicQueue.add(3);
        basicQueue.display();  // 1 <- 2 <- 3 <- END
        System.out.println("Removed: " + basicQueue.remove());  // 1
        basicQueue.display();  // 2 <- 3 <- END


        System.out.println("\n=== Testing Circular Queue ===");
        CircularQueue<Integer> circularQueue = new CircularQueue<>(5);
        circularQueue.add(35);
        circularQueue.add(2);
        circularQueue.add(12);
        circularQueue.add(3);
        circularQueue.add(5);
        circularQueue.display();  // 35 <- 2 <- 12 <- 3 <- 5 <- END

        circularQueue.remove();  // Remove 35
        circularQueue.remove();  // Remove 2
        circularQueue.add(100);  // Wraps around!
        circularQueue.add(200);  // Wraps around!
        circularQueue.display();  // 12 <- 3 <- 5 <- 100 <- 200 <- END


        System.out.println("\n=== Testing Dynamic Queue ===");
        DynamicQueue<Integer> dynQueue = new DynamicQueue<>(3);
        dynQueue.add(10);
        dynQueue.add(20);
        dynQueue.add(30);
        dynQueue.display();  // 10 <- 20 <- 30 <- END

        dynQueue.add(40);  // Triggers resize!
        dynQueue.display();  // 10 <- 20 <- 30 <- 40 <- END

        dynQueue.add(50);
        dynQueue.add(60);
        dynQueue.add(70);  // Triggers another resize!
        dynQueue.display();
    }
}