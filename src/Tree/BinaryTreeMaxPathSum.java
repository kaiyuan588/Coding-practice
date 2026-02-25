
public class BinaryTreeMaxPathSum {
// Given the root of a non-empty binary tree, return the maximum path sum of any non-empty path.
// A path in a binary tree is a sequence of nodes where each pair of adjacent nodes has an edge connecting them. A node can not appear in the sequence more than once. The path does not necessarily need to include the root.
// The path sum of a path is the sum of the node's values in the path.

// Example 1:
// Input: root = [1,2,3]
// Output: 6

// Example 2:
// Input: root = [-15,10,20,null,null,15,5,-5]
// Output: 40
// Explanation: The path is 15 -> 20 -> 5 with a sum of 15 + 20 + 5 = 40.

    int res = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return res;
    }

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        // cal res => max(res, root.val, root.val + left + right, root.val + left, root.val + right)
        int leftRightRoot = left+right+root.val;
        int rightRoot = right+root.val;
        int leftRoot = left+root.val;

        int max = Math.max(leftRightRoot, Math.max(leftRoot, rightRoot));

        res = Math.max(res, Math.max(root.val, max));
        return Math.max(Math.max(root.val, left + root.val), Math.max(root.val, right + root.val));
    }

    public int maxPathSumOptimal(TreeNode root) {
        dfs(root);
        return res;
    }

    public int dfsOptimal(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // cut the branch if the previous node is negatvie
        int left = Math.max(0, dfs(root.left));
        int right = Math.max(0, dfs(root.right));

        // since the branch been cut, we can just compare left+right+root.val and re 
        res = Math.max(res, left+right+root.val);

        // since the branch been cut, we can just return the root.val + max of left or right return to the top
        return root.val + Math.max(left, right);
    }
}
