package cqyc.structure.leetcode.tree;

/**
 * @author cqyc
 * @Description: 100. 相同的树
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * @date 2021/3/4
 */
public class IsSameTree {



   //Definition for a binary tree node.
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


    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if ((p == null && q != null) || (q == null && p != null)) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        boolean sameTree = isSameTree(p.left, q.left);
        if (sameTree) {
            sameTree = isSameTree(p.right, q.right);
        }
        return sameTree;
    }
}
