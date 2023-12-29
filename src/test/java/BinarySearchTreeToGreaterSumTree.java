import org.example.domain.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearchTreeToGreaterSumTree {

    private int sum = 0;

    @Test
    public void test() {
        TreeNode tree1 = new TreeNode(2);
        tree1.left = new TreeNode(1);
        tree1.right = new TreeNode(3);

        TreeNode tree2 = new TreeNode(6);
        tree2.left = new TreeNode(5);
        tree2.right = new TreeNode(7);

        TreeNode treeNodeRoot = new TreeNode(4);
        treeNodeRoot.left = tree1;
        treeNodeRoot.right = tree2;

        TreeNode treeLeft = new TreeNode(24);
        tree1.left = new TreeNode(28);
        tree1.right = new TreeNode(27);

        TreeNode treeRight = new TreeNode(13);
        tree2.left = new TreeNode(18);
        tree2.right = new TreeNode(7);

        TreeNode treeNodeRootExpected = new TreeNode(22);
        treeNodeRootExpected.left = treeLeft;
        treeNodeRootExpected.right = treeRight;

//        assertEquals(treeNodeRootExpected, bstToGst(treeNodeRoot));
    }

    private TreeNode bstToGst(TreeNode root) {
        calculateSum(root);
        return root;
    }

    private void calculateSum(TreeNode currentNode) {
        if(currentNode == null) {
            return;
        }
        calculateSum(currentNode.right);
        sum += currentNode.val;
        currentNode.val = sum;
        calculateSum(currentNode.left);
        return;
    }
}
