package trees.questions;

import java.util.*;

public class Day46 {

    //LC-102(Binary level order traversal)
    public List<List<Integer>> levelOrder(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>(levelSize);
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevel.add(currentNode.val);
                if (currentNode.left != null){
                    queue.offer(currentNode.left);
                }
                if(currentNode.right != null){
                    queue.offer(currentNode.right);
                }
            }
            result.add(currentLevel);
        }
        return result;
    }


    //LC-637(Average of Levels in Binary Tree) - Google
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if(root == null){
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            int levelSize = queue.size();
            double averageLevel = 0;
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                averageLevel += currentNode.val;
                if (currentNode.left != null){
                    queue.offer(currentNode.left);
                }
                if(currentNode.right != null){
                    queue.offer(currentNode.right);
                }
            }
            result.add(averageLevel/levelSize);
        }
        return result;
    }

    //Level Order Successor of a node - (Google)
    public TreeNode successorNode(TreeNode root,int target){
        if(root == null){
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
            if (currentNode.left != null){
                queue.offer(currentNode.left);
            }
            if(currentNode.right != null){
                queue.offer(currentNode.right);
            }
            if(currentNode.val == target){
                break;
            }
        }
        return queue.peek();
    }

    //LC-103 (Binary Tree Zigzag Level Order Traversal) -Google,Amazon,Microsoft
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }

        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);

        boolean rev = false;

        while(!deque.isEmpty()){
            int levelSize = deque.size();
            List<Integer> currentLevel = new ArrayList<>(levelSize);
            for (int i = 0; i < levelSize; i++) {
                if (rev){
                    TreeNode currentNode = deque.pollLast();
                    currentLevel.add(currentNode.val);
                    if (currentNode.right != null){
                        deque.offerFirst(currentNode.right);
                    }
                    if (currentNode.left != null){
                        deque.offerFirst(currentNode.left);
                    }
                }else{
                    TreeNode currentNode = deque.pollFirst();
                    currentLevel.add(currentNode.val);

                    if (currentNode.left != null){
                        deque.offerLast(currentNode.left);
                    }
                    if (currentNode.right != null){
                        deque.offerLast(currentNode.right);
                    }
                }
            }
            result.add(currentLevel);
            rev = !rev;
        }
        return result;
    }

    //LC-107 Binary Tree Level Order Traversal II
    public List<List<Integer>> levelOrderBottom(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>(levelSize);
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevel.add(currentNode.val);
                if (currentNode.left != null){
                    queue.offer(currentNode.left);
                }
                if(currentNode.right != null){
                    queue.offer(currentNode.right);
                }
            }
            result.add(0,currentLevel);
        }
        return result;
    }

    //LC - 116 Populating Next Right Pointers in Each Node - Amazon(London)Software Development Role
//    public Node connect(Node root) {
        //Approach 1- With Queue
//        List<List<Node>> levelsList = new ArrayList<>();
//        if(root == null){
//            return null;
//        }
//        Queue<Node> queue = new LinkedList<>();
//        queue.offer(root);
//
//        while(!queue.isEmpty()){
//            int levelSize = queue.size();
//            List<Node> currentLevel = new ArrayList<>(levelSize);
//            for (int i = 0; i < levelSize; i++) {
//                Node currentNode = queue.poll();
//                currentLevel.add(currentNode);
//                if (currentNode.left != null){
//                    queue.offer(currentNode.left);
//                }
//                if(currentNode.right != null){
//                    queue.offer(currentNode.right);
//                }
//            }
//            levelsList.add(currentLevel);
//        }
//        for (int i = 0; i < levelsList.size(); i++) {
//            if(levelsList.get(i).size() > 1){
//                for (int j = 1; j < levelsList.get(i).size(); j++) {
//                    levelsList.get(i).get(j-1).next = levelsList.get(i).get(j);
//                }
//            }
//        }
//        return root;

        //Approach 2 - Without Queue

//        if(root == null){
//            return null;
//        }
//        Node leftMost = root;
//        while(leftMost.left != null){
//            Node currentNode = leftMost;
//            while(currentNode != null){
//                currentNode.left.next = currentNode.right;
//                if(currentNode.next != null){
//                    currentNode.right.next = currentNode.next.left;
//                    currentNode = currentNode.next;
//                }
//            }
//                leftMost = leftMost.left;
//        }
//        return root;


//    }

    //More Optimised Solutions for LC-116
    public Node connect3(Node root) {
        Queue<Node> queue = new LinkedList<>();
        if(root != null) {
            queue.add(root);
        }

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                Node node = queue.poll();
                //last element of the level should point to null always;
                if(i < size - 1) { //important check
                    node.next = queue.peek();
                }

                if(node.left != null) {
                    queue.add(node.left);
                }
                if(node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return root;
    }

    public Node connect(Node root) {
        return recurse(root);
    }

    private Node recurse(Node root) {
        if(root == null) {
            return root;
        }

        if(root.left != null) {
            root.left.next = root.right;
        }

        if(root.right != null && root.next != null) {
            root.right.next = root.next.left;
        }
        recurse(root.left);
        recurse(root.right);

        return root;
    }

    //LC-199 Binary Tree Right Side View - Amazon
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                if(i == levelSize - 1){
                    result.add(currentNode.val);
                }
                if(currentNode.left != null){
                    queue.offer(currentNode.left);
                }
                if(currentNode.right != null){
                    queue.offer(currentNode.right);
                }
            }
        }
        return result;
    }

    //LC- 993 Cousins in Binary Tree
    public boolean isCousins(TreeNode root, int x, int y) {
        TreeNode xx = findNode(root,x);
        TreeNode yy = findNode(root,y);

        return ((level(root,xx,0) == level(root,yy,0)) && (
                !isSibling(root,xx,yy)));
    }

    private TreeNode findNode(TreeNode node,int x){
        if(node == null){
            return null;
        }
        if(node.val == x){
            return node;
        }
        TreeNode n = findNode(node.left,x);
        if(n != null){
            return n;
        }
        return findNode(node.right,x);
    }

    private boolean isSibling(TreeNode node,TreeNode x,TreeNode y){
        if(node == null){
            return false;
        }
        return (
                (node.left == x && node.right == y) || (node.left == y && node.right == x) ||
                        isSibling(node.left,x,y) || isSibling(node.right,x,y)
                );
    }

    private int level(TreeNode node, TreeNode x,int lev){
        if (node == null){
            return  0;
        }
        if(node == x){
            return lev;
        }
        int l = level(node.left,x,lev+1);
        if(l != 0){
            return l;
        }
        return level(node.right,x,lev+1);
    }

    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        while(!queue.isEmpty()){
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();

            if(left == null && right == null){
                continue;
            }
            if(left == null || right == null){
                return false;
            }
            if(left.val != right.val){
                return false;
            }
            queue.offer(left.left);
            queue.offer(right.right);
            queue.offer(left.right);
            queue.offer(right.left);

        }
        return true;

        //Oms Solution
//        if (root == null) return true;
//        return check(root.left, root.right);
//    }
//
//    private boolean check(TreeNode left, TreeNode right) {
//        if (left == null && right == null) {
//            return true;
//        }
//        if (left == null || right == null) {
//            return false;
//        }
//
//        if (left.val != right.val) return false;
//        return check(left.right, right.left) && check (left.left, right.right);
//    }
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

         class Node {
             public int val;
             public Node left;
             public Node right;
             public Node next;

             public Node() {}

             public Node(int _val) {
                 val = _val;
             }

             public Node(int _val, Node _left, Node _right, Node _next) {
                 val = _val;
                 left = _left;
                 right = _right;
                 next = _next;
             }
         }
}

