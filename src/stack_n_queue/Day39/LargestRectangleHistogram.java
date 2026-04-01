package stack_n_queue.Day39;

import java.util.Stack;

/*
============================================================
LC 84: LARGEST RECTANGLE IN HISTOGRAM

Given array of bar heights, find largest rectangle area.

BRUTE FORCE: O(n²)
For each bar, expand left and right while bars are >= current.
Area = height × width

OPTIMAL: O(n) using Monotonic Stack

KEY INSIGHT:
- For each bar, find: how far left can it extend? how far right?
- Left boundary: first shorter bar on left
- Right boundary: first shorter bar on right
- Width = right - left - 1

MONOTONIC STACK:
- Maintain stack of indices in increasing height order
- When we see a shorter bar, pop and calculate area
- Popped bar's right boundary is current index
- Popped bar's left boundary is new stack top

WHY IT WORKS:
- Stack maintains bars that could still extend right
- When shorter bar found, taller bars can't extend further
- Calculate their area before removing

TIME: O(n) — each element pushed and popped at most once
SPACE: O(n) — stack size
============================================================
*/

public class LargestRectangleHistogram {

    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();  // Store indices
        int maxArea = 0;

        // Process all bars
        for (int i = 0; i < heights.length; i++) {
            // Pop bars taller than current (they can't extend further)
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                maxArea = calculateArea(heights, stack, maxArea, i);
            }
            stack.push(i);
        }

        // Process remaining bars in stack
        int rightBoundary = heights.length;
        while (!stack.isEmpty()) {
            maxArea = calculateArea(heights, stack, maxArea, rightBoundary);
        }

        return maxArea;
    }

    private static int calculateArea(int[] heights, Stack<Integer> stack,
                                     int maxArea, int rightBoundary) {
        int height = heights[stack.pop()];
        int width;

        if (stack.isEmpty()) {
            // No left boundary — extends to index 0
            width = rightBoundary;
        } else {
            // Width = right boundary - left boundary - 1
            width = rightBoundary - stack.peek() - 1;
        }

        int area = height * width;
        return Math.max(area, maxArea);
    }

    public static void main(String[] args) {
        int[] heights1 = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea(heights1));  // 10

        int[] heights2 = {2, 4};
        System.out.println(largestRectangleArea(heights2));  // 4
    }
}