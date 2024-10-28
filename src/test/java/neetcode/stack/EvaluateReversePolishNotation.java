package neetcode.stack;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EvaluateReversePolishNotation {

    @Test
    public void test() {
        assertEquals(5, evalRPN(new String[]{"1", "2", "+", "3", "*", "4", "-"}));
    }

    private int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String c : tokens) {
            switch (c) {
                case "+" -> stack.push(stack.pop() + stack.pop());
                case "-" -> {
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(b - a);
                }
                case "*" -> stack.push(stack.pop() * stack.pop());
                case "/" -> {
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push((int) ((double) b / a));
                }
                default -> stack.push(Integer.parseInt(c));
            }
        }
        return stack.pop();
    }
}
