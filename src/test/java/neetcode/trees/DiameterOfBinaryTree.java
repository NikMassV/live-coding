package neetcode.trees;

import org.example.domain.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiameterOfBinaryTree {

    @Test
    public void test() {
        TreeNode tree = new TreeNode(3);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(1);
        assertEquals(2, diameterOfBinaryTree(tree));
    }

    private int diameterOfBinaryTree(TreeNode root) {
        int[] res = new int[1];
        dfs(root, res);
        return res[0];
    }

    private int dfs(TreeNode root, int[] res) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left, res);
        int right = dfs(root.right, res);
        res[0] = Math.max(res[0], left + right);
        return 1 + Math.max(left, right);
    }
}
