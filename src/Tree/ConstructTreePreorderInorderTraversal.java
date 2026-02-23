import java.util.*;


public class ConstructTreePreorderInorderTraversal {

// You are given two integer arrays preorder and inorder.
// preorder is the preorder traversal of a binary tree
// inorder is the inorder traversal of the same tree
// Both arrays are of the same size and consist of unique values.
// Rebuild the binary tree from the preorder and inorder traversals and return its root.

// Example 1:
// Input: preorder = [1,2,3,4], inorder = [2,1,3,4]
// Output: [1,2,3,null,null,null,4]

        //     1
        // 2       3
        //             4
// Example 2:
// Input: preorder = [1], inorder = [1]
// Output: [1]

/*
    clarify:
    1.	Are values unique?
	2.	Can we assume the traversals are valid and consistent?
	3.	What are the constraints on N / could it be very deep?
*/

// First value in the preorder always be the root

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        // find root position in inorder
        int mid = 0;
        while (inorder[mid] != root.val) {
            mid++;
        }
        // inorder's mid can tell us how many left subtrees in the current level
        // e.g. preorder [1,2,3,4], inorder = [2,1,3,4], when root = 1 can be found in the second index of inorder, so there are 1 node in left and three nodes in right.
        // So by this information, we can find preorder [1,2,3,4], the coorsponding left subtree is from [rootIndex+1...rootIndex+1+size(1)]
        int[] preLeft = Arrays.copyOfRange(preorder, 1, mid+1); // in java exclusive on the right side
        int[] inLeft = Arrays.copyOfRange(inorder, 0, mid);

        int[] preRight = Arrays.copyOfRange(preorder, mid+1, preorder.length);
        int[] inRight = Arrays.copyOfRange(inorder, mid+1, inorder.length);

        root.left = buildTree(preLeft, inLeft);
        root.right = buildTree(preRight, inRight);
        return root;
    }
    /*
    Time complexity:
    1. worst case O(n^2), if each level of the tree has only one node, and there are n level, each level would be:
    cost(n) + cost(n-1) + cost(n-2) + ... + cost(1), total n levels, so the time would be 1 + 2 + ... + n => (1+n)*n/2 => n^2
    2. if the tree is balanced, each level would have total cost of (n), and there would be logn level, so the time will be n*logn

    Space complexity
    1. worst case n^2
    2. balanced case, n
    */

    public TreeNode buildTreeOptimal(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return build(preorder, 0, 0, inorder.length, inorderMap);
    }

    public TreeNode build(int[] preorder, int preIndex, int inorderLeft, int inorderRight, Map<Integer, Integer> inorderMap) {
        if (inorderLeft >= inorderRight) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preIndex]);
        int mid = inorderMap.get(preorder[preIndex]);

        int leftSizeInorder = mid - inorderLeft;
        
        root.left = build(preorder, preIndex+1, inorderLeft, mid, inorderMap);
        root.right = build(preorder, preIndex+1 + leftSizeInorder, mid+1, inorderRight, inorderMap);
        return root;
    }

    /*
        Time Complexity:
            O(n)   // visit each node once

        Space Complexity:
            O(n)   // hashmap + recursion stack
            stack = O(log n) balanced
            stack = O(n) worst case
    */



    public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            TreeNode() {}
            TreeNode(int val) { this.val = val; }
            TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
