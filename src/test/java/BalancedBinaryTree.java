import org.example.domain.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BalancedBinaryTree {

    @Test
    public void test1() {
        TreeNode root = new TreeNode(3);
        TreeNode leftChild = new TreeNode(9);
        TreeNode rightChild = new TreeNode(20, new TreeNode(15), new TreeNode(7));
        root.left = leftChild;
        root.right = rightChild;
        assertTrue(isBalanced(root));
    }

    @Test
    public void test2() {
        TreeNode leftChild1 = new TreeNode(3, new TreeNode(4), new TreeNode(4));
        TreeNode leftChild = new TreeNode(2, leftChild1, new TreeNode(3));
        TreeNode rightChild = new TreeNode(2);
        TreeNode root = new TreeNode(1);
        leftChild.left = leftChild1;
        root.left = leftChild;
        root.right = rightChild;
        assertFalse(isBalanced(root));
    }

    @Test
    public void test3() {
        assertTrue(isBalanced(null));
    }

    private boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isBalancedLeafs(root) && isBalanced(root.left) && isBalanced(root.right);
    }

    private static boolean isBalancedLeafs(TreeNode root) {
        return Math.abs(depth(root.left) - depth(root.right)) <= 1;
    }

    private static int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        var leftChildDepth = 1 + depth(root.left);
        var rightChildDepth = 1 + depth(root.right);
        return Math.max(leftChildDepth, rightChildDepth);
    }
}
