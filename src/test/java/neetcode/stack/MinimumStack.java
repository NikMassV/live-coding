package neetcode.stack;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinimumStack {

    @Test
    public void test() {
        MinStack minStack = new MinStack();
        minStack.push(1);
        minStack.push(2);
        minStack.push(0);
        assertEquals(0, minStack.getMin());
        minStack.pop();
        assertEquals(2, minStack.top());
        assertEquals(1, minStack.getMin());
    }

    private class MinStack {

        private Stack<Integer> stack;
        private Stack<Integer> minStack;

        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val) {
            stack.push(val);
            val = Math.min(val, minStack.isEmpty() ? val : minStack.peek());
            minStack.push(val);
        }

        public void pop() {
            stack.pop();
            minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }
}
