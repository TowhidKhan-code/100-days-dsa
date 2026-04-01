package stack_n_queue.Day39;

import java.util.Scanner;

import static stack_n_queue.Day39.GameOfTwoStacks.twoStacks;

public class Main {
    public static void main(String[] args) {
//        QueueUsingStacks<Integer> queue = new QueueUsingStacks<>();
//        queue.add(23);
//        queue.add(35);
//        queue.add(46);
//        System.out.println(queue.peek());
//        queue.add(78);
//        System.out.println(queue.remove());
//        System.out.println(queue.remove());
//
//        queue.add(24);
//        System.out.println(queue.remove());
//        System.out.println(queue.remove());
//        System.out.println(queue.remove());

        Scanner sc = new Scanner(System.in);
        int noOfGames = sc.nextInt();
        for (int i = 0; i < noOfGames; i++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int maxSum = sc.nextInt();
            int[] a = new int[n];
            int[] b = new int[m];
            for (int j = 0; j < n; j++) {
                a[j] = sc.nextInt();
            }

            for (int j = 0; j < m; j++) {
                b[j] = sc.nextInt();
            }
//        int[] a = {4,2,4,6,1};
//        int[] b = {2,1,8,5};
            System.out.println(twoStacks(maxSum,a,b));
        }

    }
}
