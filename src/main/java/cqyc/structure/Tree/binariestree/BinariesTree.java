package cqyc.structure.Tree.binariestree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author cqyc
 * @Description: 二分搜索树, 因为要具有可比较性，所以T继承了Comparable接口
 * @date 2021/1/17
 */
public class BinariesTree<T extends Comparable<T>> {

    private class Node {
        private T t;
        private Node left;
        private Node right;

        public Node(T t) {
            this.t = t;
            this.left = null;
            this.right = null;
        }
    }

    //根节点
    private Node root;
    private int size;

    public BinariesTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //向二分搜索树中添加新的元素
    public void add(T t) {
        root = add(root, t);
    }

    public Node add(Node node, T t) {
        if (node == null) {
            size++;
            return new Node(t);
        }

        if (t.compareTo(node.t) < 0) {
            node.left = add(node.left, t);
        } else if (t.compareTo(node.t) > 0) {
            node.right = add(node.right, t);
        }
        return node;
    }

    //看二分搜索树是否包含元素e
    public boolean contains(T t) {
        return contains(root, t);
    }

    private boolean contains(Node node, T t) {
        if (node == null) {
            return false;
        }
        if (t.compareTo(node.t) == 0) {
            return true;
        } else if (t.compareTo(node.t) < 0){
            return contains(node.left, t);
        } else { //t.compareTo(node.t) > 0
            return contains(node.right, t);
        }
    }

    //看以node为根的二分搜索树中是否包含元素e,递归算法

//    //向以node为根的二分搜索树中插入元素E,递归算法
//    private void add(Node node, T t) {
//        //处理相同的情况
//        if (t.equals(node.t)) {
//            return;
//        } else if (t.compareTo(node.t) < 0 && node.left == null) {
//            // 小于节点的值，就往左边插入值
//            node.left = new Node(t);
//            size ++;
//            return;
//        } else if (t.compareTo(node.t) > 0 && node.right == null) {
//            //大于节点的值，就往右边插入值
//            node.right = new Node(t);
//            size ++;
//            return;
//        }
//
//        if (t.compareTo(node.t) < 0) {
//            add(node.left, t);
//        } else {
//            add(node.right, t);
//        }
//    }

    //二分搜索树的前序遍历
    public void preOrder() {
        preOrder(root);
    }

    //前序遍历以node为根的二分搜索树，递归算法
    private void preOrder(Node node) {
        if(node == null) {
            return;
        }
        System.out.println(node.t);
        preOrder(node.left);
        preOrder(node.right);
    }

    //二分搜索树的非递归的前序遍历
    public void preOrderNR() {
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.t);

            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }


    //二分搜索树的中序遍历
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.t);
        inOrder(node.right);
    }


    //二叉树的后续遍历
    public void afterOrder() {
        afterOrder(root);
    }

    private void afterOrder(Node node) {
        if (node == null) {
            return;
        }
        afterOrder(node.left);
        afterOrder(node.right);
        System.out.println(node.t);
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        generateBstString(root, 0, builder);
        return builder.toString();
    }

    private void generateBstString(Node node, int depth, StringBuilder builder) {
        if (node == null) {
            builder.append(generateDepthString(depth)).append("null\n");
            return;
        }
        builder.append(generateDepthString(depth)).append(node.t).append("\n");
        generateBstString(node.left, depth + 1, builder);
        generateBstString(node.right, depth + 1, builder);
    }

    private String generateDepthString(int depth) {
       StringBuilder builder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            builder.append("--");
        }
        return builder.toString();
    }

    //二分搜索树的层序遍历,使用队列来进行操作 (广度优先算法)
    public void levelOrder() {
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        while (!q.isEmpty()) {
            Node cur = q.remove();
            System.out.println(cur.t);

            if (cur.left != null) {
                q.add(cur.left);
            }
            if (cur.right != null) {
                q.add(cur.right);
            }
        }
    }

    /**
     * 二叉树相关删除
     */
    //寻找二分搜索树的最小元素
    public T minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }
        return minimum(root).t;
    }

    //返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    //寻找二分搜索树种最大元素
    public T maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }
        return maximum(root).t;
    }

    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    //从二分搜索数中删除最小值所在节点，返回最小值
    public T removeMin() {
        T ret = minimum();
        root = removeMin(root);
        return ret;
    }

    //删除掉以node为根的二分搜索树中的最小节点
    //返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    //从二分搜索树中删除最大值所在节点，返回最大值
    public T removeMax() {
        T ret = maximum();
        root = removeMax(root);
        return ret;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size --;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    public static void main(String[] args) {
        BinariesTree<Integer> tree = new BinariesTree<Integer>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num : nums) {
            tree.add(num);
        }
        //////////////////
        //      5       //
        //     /  \     //
        //    3    6    //
        //   / \    \   //
        //  2   4    8  //
        //////////////////
//        tree.preOrder();
        System.out.println(tree);

        System.out.println("-------");
        tree.inOrder();

        System.out.println("---------");
        tree.afterOrder();

        System.out.println("---------1111");
        tree.levelOrder();
    }
}
