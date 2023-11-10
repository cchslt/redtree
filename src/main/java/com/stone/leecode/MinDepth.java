package com.stone.leecode;


import java.util.LinkedList;
import java.util.Queue;

/**
 * @author chen
 * @create 2021-03-13 11:53
 **/

public class MinDepth {

    private int solution(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int step = 1;


        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i <size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left == null && cur.right == null) {
                    return step;
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            step++;
        }
        return step;

    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}


