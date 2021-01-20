package cqyc.structure.List.linkedlist;

/**
 * @author cqyc
 * @Description:
 * @date 2021/1/9
 */
public class LinkedListYc<T> {

    private class Node {
        public T t;
        public Node next;

        public Node(T t, Node next) {
            this.t = t;
            this.next = next;
        }

        public Node(T t){
            this(t, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return t.toString();
        }
    }

    //指定头部中的节点
    private Node head;
    private int size;

    public LinkedListYc() {
        head = null;
        size = 0;
    }

    //获取链表中的元素个数
    public int getSize() {
        return size;
    }

    //返回链表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    //在链表中添加新的元素
    public void addFirst(T t) {
//        Node node = new Node(t);
//        node.next = head;
//        head = node;
        //相当于上面得写法
        head = new Node(t, head);
        size++;
    }

    //在链表得index(0 - based)位置添加新得元素e
    //在链表中不是一个常用得操作，练习用:)
    public void add(int index, T t) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("add failed, illegal index...");
        }
        if (index == 0) {
            addFirst(t);
        } else {
            Node prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
//            Node node = new Node(t);
//            node.next = prev.next;
//            prev.next = node;

            prev.next = new Node(t, prev.next);
            size++;
        }
    }

    public void addLast(T t) {
        this.add(size, t);
    }


}
