package trees.advancequestionsDay53;

import java.util.PriorityQueue;

//LC - 230
class KthSmallest {
    public int kthSmallest(TreeNode root, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        helper(root, minHeap, k);

        // remove k elements
        int ans = 0;
        for(int i=0; i<k; i++) {
            ans = minHeap.poll();
        }
        return ans;
    }

    private void helper(TreeNode node, PriorityQueue<Integer> minHeap, int k) {
        if (node == null) {
            return;
        }

        helper(node.left, minHeap, k);

        minHeap.offer(node.val);

        helper(node.right, minHeap, k);
    }

    //Without Heap Solution
//    private int k;
//    private int ans;
//
//    public int kthSmallest(TreeNode root, int k) {
//        this.k = k;
//        helper(root);
//        return ans;
//    }
//
//    private void helper(TreeNode node) {
//        if (node == null) {
//            return;
//        }
//
//        helper(node.left);
//
//        k--;
//        if(k==0) {
//            ans = node.val;
//            return;
//        }
//
//        helper(node.right);
//    }
}