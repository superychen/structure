package cqyc.structure.Stack.impl;

import cqyc.structure.Stack.StackYc;
import cqyc.structure.array.ArrayYcThree;

/**
 * @author cqyc
 * @Description:
 * @date 2021/1/5
 */
public class ArrayStackYc<T> implements StackYc<T> {

    ArrayYcThree<T> array;

    public ArrayStackYc(int capacity) {
        array = new ArrayYcThree<T>(capacity);
    }

    public ArrayStackYc() {
        array = new ArrayYcThree<T>();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void push(T t) {
        array.addLast(t);
    }

    @Override
    public T pop() {
        return array.removeLast();
    }

    @Override
    public T peek() {
        return array.getLast();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Stack: ");
        builder.append('[');
        for (int i = 0; i < array.getSize(); i++) {
            builder.append(array.get(i));
            if (i != array.getSize() - 1) {
                builder.append(",");
            }
        }
        builder.append("] TOP");
        return builder.toString();
    }
}
