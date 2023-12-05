import org.example.domain.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaximumDepthOfBinaryTree {

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

        assertEquals(4, maxDepth(treeNodeRoot));
    }

    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftMaxDepth = maxDepth(root.left);
        int rightMaxDepth = maxDepth(root.right);
        return leftMaxDepth > rightMaxDepth ? leftMaxDepth + 1 : rightMaxDepth + 1;
    }
}
