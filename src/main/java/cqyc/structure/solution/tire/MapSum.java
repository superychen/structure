package cqyc.structure.solution.tire;

import java.util.TreeMap;

/**
 * @author cqyc
 * @Description:
 * @date 2021/2/11
 */
public class MapSum {

    private class Node {
        public Integer value;
        public TreeMap<Character, Node> next;

        public Node(int value) {
            this.value = value;
            this.next = new TreeMap<>();
        }

        public Node() {
            this(0);
        }
    }

    private Node root;

    /** Initialize your data structure here. */
    public MapSum() {
        root = new Node();
    }

    public void insert(String key, int val) {
        Node cur = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        cur.value = val;
    }

    public int sum(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return 0;
            }
            cur = cur.next.get(c);
        }
        return sum(cur);
    }

    private int sum(Node node) {
        int res = node.value;
//        if (node.next.size() == 0) {
//            return res;
//        }
        for (Character c : node.next.keySet()) {
            res += sum(node.next.get(c));
        }
        return res;
    }

}
