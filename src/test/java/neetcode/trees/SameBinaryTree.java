package neetcode.trees;//https://leetcode.com/problems/same-tree/description/

import org.example.domain.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SameBinaryTree {

    @Test
    public void test() {
        TreeNode tree1 = new TreeNode(1);
        tree1.left = new TreeNode(2);
        tree1.right = new TreeNode(3);

        TreeNode tree2 = new TreeNode(1);
        tree2.left = new TreeNode(2);
        tree2.right = new TreeNode(3);

        TreeNode tree3 = new TreeNode(1);
        tree3.left = new TreeNode(3);
        tree3.right = new TreeNode(2);

        // Test the isSameTree method
        assertTrue(isSameTree(tree1, tree2));
        assertFalse(isSameTree(tree1, tree3));
    }

    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
