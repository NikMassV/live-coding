import org.example.domain.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SumOfNodesWithEvenValuedGrandparent {

    @Test
    public void test() {
        TreeNode tree1 = new TreeNode(1);
        tree1.left = new TreeNode(2);
        tree1.right = new TreeNode(7);

        TreeNode tree2 = new TreeNode(8);
        tree2.left = new TreeNode(1);
        tree2.right = new TreeNode(3);

        TreeNode treeNodeRoot = new TreeNode(6);
        treeNodeRoot.left = tree2;
        treeNodeRoot.right = tree1;
        assertEquals(13, sumEvenGrandparent(treeNodeRoot));
    }

    private int sumEvenGrandparent(TreeNode root) {
        return calculateSubSum(root, -1, -1);
    }

    private int calculateSubSum(TreeNode currentNode, int parentValue, int grandParentValue) {
        int subResult = 0;
        if (grandParentValue % 2 == 0) {
            subResult = currentNode.val;
        }
        if (currentNode.left != null) {
            subResult += calculateSubSum(currentNode.left, currentNode.val, parentValue);
        }
        if (currentNode.right != null) {
            subResult += calculateSubSum(currentNode.right, currentNode.val, parentValue);
        }
        return subResult;
    }
}
