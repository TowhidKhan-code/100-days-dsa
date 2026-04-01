package stack_n_queue.Day39;

import java.util.Stack;

/*
============================================================
LC 20: VALID PARENTHESES

Given string with '(', ')', '{', '}', '[', ']'
Return true if brackets are valid

VALID:
- Every open bracket has matching close
- Brackets close in correct order (LIFO)

ALGORITHM:
1. See opening bracket → push to stack
2. See closing bracket → pop and check if matches
3. End of string → stack should be empty

WHY STACK:
- Most recent opening bracket must close first (LIFO)
- Stack naturally maintains this order

TIME: O(n) — single pass
SPACE: O(n) — stack size in worst case

EDGE CASES:
- Empty string → true
- Only closing brackets → false (stack empty when trying to pop)
- Only opening brackets → false (stack not empty at end)
============================================================
*/

public class ValidParentheses {

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            // Opening bracket → push
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            }
            // Closing bracket → pop and match
            else {
                if (ch == ')') {
                    if (stack.isEmpty() || stack.pop() != '(') {
                        return false;
                    }
                }
                if (ch == '}') {
                    if (stack.isEmpty() || stack.pop() != '{') {
                        return false;
                    }
                }
                if (ch == ']') {
                    if (stack.isEmpty() || stack.pop() != '[') {
                        return false;
                    }
                }
            }
        }

        // Stack should be empty if all brackets matched
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("(){}[]"));    // true
        System.out.println(isValid("([{}])"));    // true
        System.out.println(isValid("((){}[]"));   // false — extra (
        System.out.println(isValid("([)]"));      // false — wrong order
        System.out.println(isValid(""));          // true — empty is valid
    }
}