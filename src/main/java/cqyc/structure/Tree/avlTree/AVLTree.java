package cqyc.structure.Tree.avlTree;

import cqyc.structure.Tree.set.FileOperation;
import cqyc.structure.map.LinkedListMapYc;
import cqyc.structure.map.MapYc;

import java.util.ArrayList;

/**
 * @author cqyc
 * @Description:
 * @date 2021/1/24
 */
public class AVLTree<K extends Comparable<K>, V> implements MapYc<K, V> {

    private class Node {
        public K key;
        public V value;
        public Node left;
        public Node right;
        //记录当前节点所处在的高度
        public int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    //对节点y进行向右旋转操作，返回旋转后新的根节点
    //对节点y进行向右旋转操作，返回旋转后新的根节点X
    //         y                              x
    //        / \                           /   \
    //       x  T4                         z     y
    //      / \     ------------------->  / \   / \
    //     z   T3                        T1 T2 T3  T4
    //    / \
    //   T1  T2
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;
        x.right = y;
        y.left = T3;

        //更新高度
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }


    //         y                              x
    //        / \                           /   \
    //       T1  x                         y     z
    //          / \  -------------------> / \   / \
    //         T2  z                     T1 T2 T3  T4
    //            / \
    //           T3  T4
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;

        //向左旋转
        x.left = y;
        y.right = T2;

        //更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }


    //向二分搜索树种添加新的元素
    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    //向以node为根的二分搜索树中插入元素（key, value），递归算法
    //返回插入新节点后二分搜搜树的根
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            //key.compareTo(node.key) == 0
            node.value = value;
        }

        //更新height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        //平衡维护
        //LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }
        //RR
        if (balanceFactor < -1  && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }
        //LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        //RL
        if  (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    //判断当前的树结构是否是二分搜索树的结构
    public boolean isBST() {
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            //中序遍历不是以升序开始
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    private void inOrder(Node node, ArrayList<K> keys) {
        if (node == null) {
            return;
        }
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    //判断该二叉树是否是一颗平衡二叉树
    public boolean isBalanced() {
        return isBalanced(root);
    }

    //判断以Node为根的二叉树是否是一颗平衡二叉树，递归算法
    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }

        return isBalanced(node.left) && isBalanced(node.right);
    }

    //返回以node为根节点的二分搜索树，key所在的节点
    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) == 0) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else {
            return getNode(node.right, key);
        }
    }

    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        Node retNode;
        if (key.compareTo(node.key) < 0) {
            node.left =  remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            } else {
                Node successor = minimum(node.right);
//            successor.right = removeMin(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                node.left = node.right = null;
                retNode = successor;
            }
        }

        if (retNode == null) {
            return null;
        }

        //更新height
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

        //计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);
        //平衡维护
        //LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            return rightRotate(retNode);
        }
        //RR
        if (balanceFactor < -1  && getBalanceFactor(retNode.right) <= 0) {
            return leftRotate(retNode);
        }
        //LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        //RL
        if  (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }
        return retNode;
    }

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + " doesn't exist");
        }
        node.value = newValue;
    }

    @Override
    public int getSize() {
        return size;
    }

    //封装一个获取高度的方法
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    //获取每一个节点想对应的平衡因子
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    //返回以nod为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

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

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<String>();
//        AVLTree<Integer, String> tree = new AVLTree<>();
//        tree.add(1, "yc");
//        tree.add(2, "yc2");
//        tree.remove(2);
//        System.out.println(tree.get(1));
//        System.out.println(tree.get(2));
        String filename = "D:\\cqyc\\java\\structure\\src\\pride-and-prejudice.txt";
        AVLTree<String, Integer> bstMap = new AVLTree<String, Integer>();
        double time1 = testMap(bstMap, filename);
        System.out.println("BST Map: " + time1 + "s");
        System.out.println(bstMap.isBST());
        System.out.println(bstMap.isBalanced());

//        MapYc<String, Integer> linkedListMapYc = new LinkedListMapYc<String, Integer>();
//        double time2 = testMap(linkedListMapYc, filename);
//        System.out.println("BST Map: " + time2 + "s");
    }

    private static double testMap(AVLTree<String, Integer> map, String filename) {
        long startTime = System.nanoTime();

        System.out.println(filename);
        ArrayList<String> words = new ArrayList<String>();
        if (FileOperation.readFile(filename, words)) {
            System.out.println("Total words:" + words.size());
            for (String word : words) {
                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                } else {
                    map.add(word, 1);
                }
            }
            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));

            for (String word : words) {
                map.remove(word);
                if (!map.isBST() || !map.isBalanced()) {
                    throw new RuntimeException("error");
                }
            }
        }


        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

}
