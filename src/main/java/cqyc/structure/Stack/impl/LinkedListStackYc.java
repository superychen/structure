package cqyc.structure.Stack.impl;

import cqyc.structure.List.linkedlist.LinkedListYcTwo;
import cqyc.structure.Stack.StackYc;

/**
 * @author cqyc
 * @Description:
 * @date 2021/1/12
 */
public class LinkedListStackYc<T> implements StackYc<T> {

    private LinkedListYcTwo<T> list;

    private LinkedListStackYc() {
        list = new LinkedListYcTwo<T>();
    }


    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(T t) {
        list.addFirst(t);
    }

    @Override
    public T pop() {
        return list.removeFist();
    }

    @Override
    public T peek() {
        return list.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("stack top :");
        builder.append(list);
        return builder.toString();
    }

    public static void main(String[] args) {
        LinkedListStackYc<Integer> res = new LinkedListStackYc<Integer>();
        for (int i = 0; i < 5; i++) {
            res.push(i);
        }
        res.pop();
        System.out.println(res);
    }
}
