package stack_n_queue.Day39;

import java.util.Arrays;

/*
============================================================
HACKERRANK: GAME OF TWO STACKS

Two stacks A and B, maximum sum allowed.
Pick from top of either stack.
Maximize number of picks without exceeding sum.

RECURSIVE APPROACH:
- At each step, try picking from A or B
- Recurse with remaining elements
- Return maximum picks possible

TIME: O(2^(n+m)) — exponential (each step has 2 choices)
SPACE: O(n+m) — recursion depth

NOTE: This solution may TLE for large inputs.
Optimized solution uses two-pointer approach.
============================================================
*/

public class GameOfTwoStacks {

    public static int twoStacks(int maxSum, int[] a, int[] b) {
        return twoStacks(maxSum, a, b, 0, 0) - 1;
    }

    private static int twoStacks(int maxSum, int[] a, int[] b, int sum, int count) {
        // Base case: exceeded sum limit
        if (sum > maxSum) {
            return count;
        }

        // Base case: one stack is empty
        if (a.length == 0 || b.length == 0) {
            return count;
        }

        // Try picking from stack A
        int ans1 = twoStacks(
                maxSum,
                Arrays.copyOfRange(a, 1, a.length),  // Remove top of A
                b,
                sum + a[0],
                count + 1
        );

        // Try picking from stack B
        int ans2 = twoStacks(
                maxSum,
                a,
                Arrays.copyOfRange(b, 1, b.length),  // Remove top of B
                sum + b[0],
                count + 1
        );

        // Return maximum of both choices
        return Math.max(ans1, ans2);
    }
}