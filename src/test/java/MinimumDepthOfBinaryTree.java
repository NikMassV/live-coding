import org.example.domain.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinimumDepthOfBinaryTree {

    @Test
    public void test() {
        TreeNode tree1 = new TreeNode(1);
        tree1.left = new TreeNode(2);
        tree1.right = new TreeNode(3);

        TreeNode tree2 = new TreeNode(1);
        tree2.left = tree1;
        tree2.right = new TreeNode(3);

        TreeNode treeNodeRoot = new TreeNode(1);
        treeNodeRoot.left = tree2;
        treeNodeRoot.right = new TreeNode(3);

        assertEquals(2, minDepth(treeNodeRoot));
    }

    private int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        var depth = 1;
        var queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            var nodesCount = queue.size();
            for (int i = 0; i < nodesCount; i++) {
                var node = Objects.requireNonNull(queue.poll());
                if (node.left == null && node.right == null) {
                    return depth;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            depth++;
        }
        return depth;
    }
}
