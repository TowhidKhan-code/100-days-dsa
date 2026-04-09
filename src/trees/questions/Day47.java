package trees.questions;

import java.util.*;

public class Day47 {

    void dfsStack(TreeNode node){
        if (node == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        while(!stack.isEmpty()){
            TreeNode removed = stack.pop();
            System.out.println(removed.val + " ");
            if (removed.right != null){
                stack.push(removed.right);
            }
            if (removed.left != null){
                stack.push(removed.left);
            }
        }
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // LC - 543 Diameter of Binary Tree
    int diameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        height(root);
        return diameter - 1;
    }
    int height(TreeNode node){
        if(node == null){
            return 0;
        }
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        int dia = leftHeight + rightHeight + 1;
        diameter = Math.max(dia,diameter);
        return Math.max(leftHeight,rightHeight) + 1;
    }

    //LC - 226 Invert Binary Tree (Google)
    public TreeNode invertTree(TreeNode root) {
        if (root == null){
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    //LC - 104 Maximum Depth of Binary Tree (Google)
    public int maxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        int depth = Math.max(left,right) + 1;
        return depth;
    }

    //LC - 108

    //LC - 114 Flatten Binary Tree to Linked List (Facebook)
    public void flatten(TreeNode root) {
        TreeNode current = root;
        while(current != null){
            if(current.left!=null){
                TreeNode temp = current.left;
                while(temp.right != null){
                    temp = temp.right;
                }
                temp.right = current.right;
                current.right = current.left;
                current.left = null;
            }
                current = current.right;
        }
    }

    //LC - 98 Validate Binary Search Tree
    public boolean isValidBST(TreeNode root) {
        return helper(root,null,null);
    }
    boolean helper(TreeNode node,Integer low,Integer high){
        if(node == null){
            return true;
        }

        if(low!=null && node.val<=low){
            return false;
        }

        if(high!=null && node.val>=high){
            return false;
        }

        boolean leftTree = helper(node.left,low,node.val);
        boolean rightTree = helper(node.right,node.val,high);

        return leftTree && rightTree;

    }

    //LC - 236. Lowest Common Ancestor of a Binary Tree (Amazon)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null){
            return null;
        }
        if(root == p || root == q){
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);

        if(left != null && right != null){
            return root;
        }
        return left == null ? right : left;
    }

    //LC - 230 Kth Smallest Element in a BST (Google,Amazon,Facebook)
    int count = 0;
    public int kthSmallest(TreeNode root, int k) {
        return helper2(root,k).val;
    }

    TreeNode helper2(TreeNode node,int k){
        if (node == null){
            return null;
        }
        TreeNode left = helper2(node.left,k);
        if (left != null){
            return left;
        }
        count++;
        if(count == k){
            return node;
        }
        return helper2(node.right,k);
    }

    //LC - 105 Construct Binary Tree from Preorder and Inorder Traversal (Amazon)
    //Can be optimised using heaps(later)
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0){
            return null;
        }
        int r = preorder[0];
        int index = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == r){
                index = i;
            }
        }
        TreeNode node = new TreeNode(r);
        node.left = buildTree(Arrays.copyOfRange(preorder,1,index+1),Arrays.copyOfRange(inorder,0,index));
        node.right = buildTree(Arrays.copyOfRange(preorder,index + 1,preorder.length),Arrays.copyOfRange(inorder,index+1,inorder.length));
        return node;
    }

    //LC - 297
    // Encodes a tree to a single string.
//    public String serialize(TreeNode root) {
//
//    }
//
//    // Decodes your encoded data to tree.
//    public TreeNode deserialize(String data) {
//
//    }

    //LC - 112 Path Sum (Amazon)
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null){
            return false;
        }
        if(root.val == targetSum && root.left == null && root.right == null){
            return true;
        }
        return hasPathSum(root.left,targetSum- root.val) || hasPathSum(root.right,targetSum-root.val);
    }

    //LC - 129 Sum Root to leaf numbers
    public int sumNumbers(TreeNode root) {
        return helper3(root,0);
    }

    int helper3(TreeNode node,int sum){
        if (node == null){
            return 0;
        }
        sum = sum * 10 + node.val;
        if (node.left == null&& node.right == null){
            return sum;
        }
        return helper3(node.left,sum) + helper3(node.right,sum);
    }

    //LC - 124 Binary Tree Maximum Path Sum (Facebook)
    int ans = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        helper4(root);
        return ans;
    }
    int helper4(TreeNode node){
        if(node == null){
            return 0;
        }
        int left = helper4(node.left);
        int right = helper4(node.right);

        left = Math.max(0,left);
        right = Math.max(0,right);
        int pathSum = left + right + node.val;
        ans = Math.max(ans,pathSum);
        return Math.max(left,right) + node.val;
    }

    // Path Exists in Binary tree from root to leaf
    public boolean findPath(TreeNode node,int[] arr){
        if(node == null){
            return arr.length == 0;
        }
        return helper5(node,arr,0);
    }
    boolean helper5(TreeNode node,int[] arr,int index){
        if (node == null){
            return false;
        }
        if (index >= arr.length || arr[index] != node.val){
            return false;
        }
        if (node.left == null && node.right == null && index == arr.length - 1){
            return true;
        }
        return helper5(node.left,arr,index+1) || helper5(node.right,arr,index+1);
    }

    // Path Exists in Binary tree from root to leaf
    public int countPath(TreeNode node,int targetSum){
        List<Integer> path = new ArrayList<>();
        return helper6(node,targetSum,path);
    }
    int helper6(TreeNode node, int sum, List<Integer> path){
        if (node == null){
            return 0;
        }
        path.add(node.val);
        int count = 0;
        int s = 0;
        ListIterator<Integer> itr = path.listIterator(path.size());
        while(itr.hasPrevious()){
            s += itr.previous();
            if (s == sum){
                count++;
            }
        }
        count += helper6(node.left,sum,path) + helper6(node.right,sum,path);
        path.remove(path.size() - 1);
        return count;
    }

    public List<List<Integer>> printPath(TreeNode node,int targetSum){
        List<List<Integer>> paths = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        helper7(node,targetSum,path,paths);
        return paths;
    }
    void helper7(TreeNode node, int sum, List<Integer> path,List<List<Integer>> paths){
        if (node == null){
            return;
        }
        path.add(node.val);
        if(node.val == sum && node.left == null && node.right == null){
            paths.add(new ArrayList<>(path));
        }else{
            helper7(node.left,sum-node.val,path,paths);
            helper7(node.right,sum-node.val,path,paths);
        }
        path.remove(path.size() - 1);

    }
}
