package stack_n_queue.Day39;

import java.util.Stack;

/*
============================================================
LC 921: MINIMUM ADD TO MAKE PARENTHESES VALID

Given string of '(' and ')', return minimum additions needed.

APPROACH 1 — Using Stack:
- Push '(' to stack
- For ')', if stack has '(' → pop (matched)
           else → push ')' (unmatched)
- Answer = stack size (all unmatched brackets)

APPROACH 2 — Using Counters:
- Count unmatched '(' (open)
- Count unmatched ')' (close)
- Answer = open + close

TIME: O(n)
SPACE: O(n) for stack, O(1) for counter approach
============================================================
*/

public class ValidParenthesesMinAdd {

    // ============================================================
    // APPROACH 1: Using Stack
    // ============================================================
    public static int minAddToMakeValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (ch == ')') {
                // Try to match with existing '('
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();  // Matched! Remove the '('
                } else {
                    stack.push(ch);  // Unmatched ')'
                }
            } else {
                stack.push(ch);  // Push '('
            }
        }

        // Stack contains all unmatched brackets
        return stack.size();
    }


    // ============================================================
    // APPROACH 2: Using Counters (Optimized — O(1) space)
    // ============================================================
    public static int minAddToMakeValidOptimized(String s) {
        int openNeeded = 0;   // Unmatched '(' that need ')'
        int closeNeeded = 0;  // Unmatched ')' that need '('

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                openNeeded++;  // Need a ')' to match
            } else {
                if (openNeeded > 0) {
                    openNeeded--;  // Matched with an '('
                } else {
                    closeNeeded++;  // Unmatched ')', need an '('
                }
            }
        }

        return openNeeded + closeNeeded;
    }

    public static void main(String[] args) {
        System.out.println(minAddToMakeValid("(()("));  // 2
        System.out.println(minAddToMakeValid("())"));   // 1
        System.out.println(minAddToMakeValid("((("));   // 3
        System.out.println(minAddToMakeValid("()"));    // 0
    }
}