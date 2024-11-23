package neetcode.trees;

import org.example.domain.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BalancedBinaryTree {

    @Test
    public void testBalanced() {
        TreeNode node4 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode root = new TreeNode(1);
        root.left = node2;
        root.right = node3;
        node2.right = node4;
        assertTrue(isBalanced(root));
    }

    @Test
    public void testNonBalanced() {
        TreeNode node5 = new TreeNode(5);
        TreeNode node4 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode root = new TreeNode(1);
        root.left = node2;
        root.right = node3;
        node2.right = node4;
        node4.left = node5;
        assertFalse(isBalanced(root));
    }

    private boolean isBalanced(TreeNode root) {
        return dfs(root)[0] == 1;
    }

    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{1, 0};
        }
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        boolean balanced = (left[0] == 1 && right[0] == 1) &&
                (Math.abs(left[1] - right[1]) <= 1);
        int height = 1 + Math.max(left[1], right[1]);
        return new int[]{balanced ? 1 : 0, height};
    }
}
