package cqyc.structure.List.linkedlist;

/**
 * @author cqyc
 * @Description: 使用链表中的虚拟头节点来处理
 * @date 2021/1/9
 */
public class LinkedListYcTwo<T> {

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
    private Node dummyHead;
    private int size;

    public LinkedListYcTwo() {
        //todo 在这里定义一个虚拟头节点，为了新增node的时候去除多余判断
        dummyHead = new Node(null, null);
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


    //在链表得index(0 - based)位置添加新得元素e
    //在链表中不是一个常用得操作，练习用:)
    public void add(int index, T t) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("add failed, illegal index...");
        }
        Node prev = dummyHead;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }
        prev.next = new Node(t, prev.next);
        size++;
    }

    //在链表中添加新的元素
    public void addFirst(T t) {
        add(0, t);
    }

    public void addLast(T t) {
        this.add(size, t);
    }

    //在获得链表的第index(0-based)个位置的元素
    //在链表中不是一个常用的操作，练习用
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("get failed. Illegal index...");
        }
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.t;
    }

    public T getFirst() {
        return get(0);
    }

    public T getLast() {
        return get(size - 1);
    }

    //修改链表的第index(0-based)个位置的元素为e
    //在链表中不是一个常用的操作，练习用：）
    public void set(int index, T t) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Illegal index....");
        }
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.t = t;
    }

    //查找链表中是否有元素e
    public boolean contains(T t) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (t.equals(cur.t)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    //从链表中删除index(0 -based)位置的元素，返回删除的元素
    //在链表中不是一个常用的操作，练习用：）
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove failed, Index is illegal");
        }
        //以dummyNode为起点
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size--;
        return retNode.t;
    }

    public T removeFist() {
        return remove(0);
    }

    public T removeLast() {
        return remove(size - 1);
    }

    // 从链表中删除元素e
    public void removeElement(T t){
        Node prev = dummyHead;
        while(prev.next != null){
            if(prev.next.t.equals(t))
                break;
            prev = prev.next;
        }

        if(prev.next != null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node cur = dummyHead.next;
        while (cur != null) {
            builder.append(cur.t).append("->");
            cur = cur.next;
        }
        builder.append("NULL");
        return builder.toString();
    }

    public static void main(String[] args) {
        LinkedListYcTwo<Integer> linkedListYcTwo = new LinkedListYcTwo<Integer>();
        for (int i = 0; i < 5; i++) {
            linkedListYcTwo.addFirst(i);
            System.out.println(linkedListYcTwo);
        }
        linkedListYcTwo.add(2, 666);
        System.out.println(linkedListYcTwo);

        linkedListYcTwo.remove(2);
        System.out.println(linkedListYcTwo);
    }
}
