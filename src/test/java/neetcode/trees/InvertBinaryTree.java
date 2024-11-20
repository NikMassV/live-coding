package neetcode.trees;

import org.example.domain.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvertBinaryTree {

    @Test
    public void test() {
        TreeNode actual = new TreeNode(3);
        actual.left = new TreeNode(2);
        actual.right = new TreeNode(1);
        TreeNode expected = new TreeNode(3);
        expected.left = new TreeNode(1);
        expected.right = new TreeNode(2);
        assertEquals(expected, invertTree(actual));
    }

    private TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return root;
    }
}
