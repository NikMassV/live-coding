package neetcode.trees;

import org.example.domain.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LowestCommonAncestorInBinarySearchTree {

    @Test
    public void test() {
        TreeNode tree = new TreeNode(5);
        tree.left = new TreeNode(3);
        tree.left.left = new TreeNode(1);
        tree.left.right = new TreeNode(4);
        tree.left.right.left = new TreeNode(2);
        tree.right = new TreeNode(8);
        tree.right.left = new TreeNode(7);
        tree.right.right = new TreeNode(9);
        assertEquals(new TreeNode(5).val, Objects.requireNonNull(lowestCommonAncestor(tree, new TreeNode(3), new TreeNode(8))).val);
        assertEquals(new TreeNode(3).val, Objects.requireNonNull(lowestCommonAncestor(tree, new TreeNode(3), new TreeNode(4))).val);
        assertEquals(new TreeNode(8).val, Objects.requireNonNull(lowestCommonAncestor(tree, new TreeNode(7), new TreeNode(8))).val);
    }

    private TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode current = root;
        while (current != null) {
            if (p.val > current.val && q.val > current.val) {
                current = current.right;
            } else if (p.val < current.val && q.val < current.val) {
                current = current.left;
            } else {
                return current;
            }
        }
        return null;
    }
}
