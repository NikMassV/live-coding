import org.example.domain.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConvertSortedArrayToBinaryTree {

    @Test
    public void test() {
        int[] nums = {1, 2, 3};
        TreeNode tree = new TreeNode(2);
        tree.left = new TreeNode(1);
        tree.right = new TreeNode(3);
        assertEquals(tree, sortedArrayToBST(nums));
    }

    private TreeNode sortedArrayToBST(int[] nums) {
        TreeNode rootNode = buildSubBST(nums, 0, nums.length - 1);
        return rootNode;
    }

    private TreeNode buildSubBST(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode midNode = new TreeNode(nums[mid]);
        midNode.left = buildSubBST(nums, start, mid - 1);
        midNode.right = buildSubBST(nums, mid + 1, end);
        return midNode;
    }
}
