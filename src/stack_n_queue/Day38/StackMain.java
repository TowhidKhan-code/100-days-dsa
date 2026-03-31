package stack_n_queue.Day38;

public class StackMain {

    public static void main(String[] args) throws Exception {

        System.out.println("=== Testing Custom Stack ===");
        CustomStack<Integer> stack = new CustomStack<>(5);
        stack.push(34);
        stack.push(12);
        stack.push(9);
        stack.push(4);
        stack.push(44);

        System.out.println("Peek: " + stack.peek());  // 44

        // Pop all elements
        System.out.print("Popping: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(stack.pop() + " ");  // 44 4 9 12 34
        }
        System.out.println();


        System.out.println("\n=== Testing Dynamic Stack ===");
        DynamicStack<Integer> dynStack = new DynamicStack<>(3);
        dynStack.push(34);
        dynStack.push(12);
        dynStack.push(9);
        // Stack is full, next push triggers resize
        dynStack.push(4);   // Resize to 6
        dynStack.push(44);
        dynStack.push(90);
        dynStack.push(100); // Resize to 12

        // Pop all elements
        System.out.print("Popping: ");
        while (!dynStack.isEmpty()) {
            System.out.print(dynStack.pop() + " ");
        }
        System.out.println();
    }
}