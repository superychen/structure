package cqyc.structure.Tree.Trie;

import cqyc.structure.Tree.set.BinariestreeSetYc;
import cqyc.structure.Tree.set.FileOperation;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * @author cqyc
 * @Description: 字典树，前缀树
 * @date 2021/2/10
 */
public class Trie {

    private class Node {
        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie() {
        root = new Node();
        size = 0;
    }

    //获得trie中存储的单词数量
    public int getSize() {
        return size;
    }

    //向trie中添加一个新的单词
    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt (i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        if(!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    //利用递归实现包含
    public boolean containsRecursion(String word) {
        if(word == null || word.length() == 0) {
            return false;
        }
        return containsRecursion(root, word, 0);
    }

    private boolean containsRecursion(Node node, String word, int index) {
        char c = word.charAt(index);
        if (node.next.get(c) == null) {
            return false;
        }
        if (index == (word.length() - 1)) {
            return node.next.get(c).isWord;
        }
        return containsRecursion(node.next.get(c), word, index + 1);
    }

    //查询是否在Trie中有单词以prefix为前缀
    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }

    public static void main(String[] args) {
//        String filename = "D:\\cqyc\\java\\structure\\src\\pride-and-prejudice.txt";
//        System.out.println("pride-and-prejudice.txt");
//        ArrayList<String> words = new ArrayList<>();
//        if (FileOperation.readFile(filename, words)) {
//            long startTime = System.nanoTime();
//            BinariestreeSetYc<String> setYc = new BinariestreeSetYc<>();
//            for (String word : words) {
//                setYc.add(word);
//            }
//            for (String word : words) {
//                setYc.contains(word);
//            }
//            long endTime = System.nanoTime();
//            double time = (endTime - startTime) / 1000000000.0;
//            System.out.println("Total different words: " + setYc.getSize());
//            System.out.println("BSTSet: " + time + "s");
//            //========
//
//            startTime = System.nanoTime();
//            Trie trie = new Trie();
//            for (String word : words) {
//                trie.add(word);
//            }
//            for (String word : words) {
//                trie.containsRecursion(word);
//            }
//            endTime = System.nanoTime();
//            time = (endTime - startTime) / 1000000000.0;
//            System.out.println("Total different words: " + trie.getSize());
//            System.out.println("BSTSet: " + time + "s");
//        }

        Trie trie = new Trie();
        trie.add("beating");
        trie.add("x");
        System.out.println("=======");
        System.out.println(trie.containsRecursion(null));
        System.out.println(trie.containsRecursion("x"));
        System.out.println(trie.containsRecursion("xobea"));
        System.out.println(trie.containsRecursion("bea"));
        System.out.println(trie.containsRecursion("beatinx"));
        System.out.println(trie.containsRecursion("beatingxx"));
        System.out.println(trie.containsRecursion("bassc"));
        Trie trie2 = new Trie();
    }


}
