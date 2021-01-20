package cqyc.structure.Queue;

/**
 * @author cqyc
 * @Description:
 * @date 2021/1/12
 */
public class LinkedListQueue<T> implements QueueYc<T> {

    private class Node {
        private T t;
        private Node next;

        public Node(T t, Node next) {
            this.t = t;
            this.next = next;
        }

        public Node(T t) {
            this(t, null);
        }

        public Node() {
            this(null, null);
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(T t) {
        //第一次入队，需要判断为空，并且进行赋值
        if (tail == null) {
            tail = new Node(t);
            head = tail;
        } else {
            //tail节点指向下一个节点
            tail.next = new Node(t);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public T dequue() {
        //出队需考虑为空，以及数据只有1条得情况:需要
        if (isEmpty()) {
            throw new IllegalArgumentException("当前队列数据为空!!!");
        }
        Node retNode = head;
        head = head.next;
        retNode.next = null;
        if (head == null) {
            tail = null;
        }
        size--;
        return retNode.t;
    }

    @Override
    public T getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("当前队列数据为空!!!");
        }
        return head.t;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Queue: front ");
        Node cur = head;
        while (cur != null){
            builder.append(cur.t).append("->");
            cur = cur.next;
        }
        builder.append("NULL tail");
        return builder.toString();
    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> res = new LinkedListQueue<Integer>();
        for (int i = 0; i < 10; i++) {
            res.enqueue(i);
            System.out.println(res);
            if (i % 3 == 2) {
                res.dequue();
                System.out.println(res);
            }
        }

    }
}
