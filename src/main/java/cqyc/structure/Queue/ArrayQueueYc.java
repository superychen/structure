package cqyc.structure.Queue;

import cqyc.structure.array.ArrayYc;
import cqyc.structure.array.ArrayYcThree;

/**
 * @author cqyc
 * @Description:
 * @date 2021/1/8
 */
public class ArrayQueueYc<T> implements QueueYc<T> {

    private ArrayYcThree<T> arrayYc;

    public ArrayQueueYc(int capacity) {
        arrayYc = new ArrayYcThree<T>(capacity);
    }

    public ArrayQueueYc() {
        arrayYc = new ArrayYcThree<T>();
    }

    @Override
    public int getSize() {
        return arrayYc.getSize();
    }

    @Override
    public boolean isEmpty() {
        return arrayYc.isEmpty();
    }

    @Override
    public void enqueue(T t) {
        arrayYc.addLast(t);
    }

    @Override
    public T dequue() {
        return arrayYc.removeFirst();
    }

    @Override
    public T getFront() {
        return arrayYc.getFirst();
    }

    public int getCapacity() {
        return arrayYc.getCapacity();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Queue:  "));
        builder.append("front [");
        for (int i = 0; i < arrayYc.getSize(); i++) {
            builder.append(arrayYc.get(i));
            if (i != arrayYc.getSize() - 1) {
                builder.append(", ");
            }
        }
        builder.append("] tail");
        return builder.toString();
    }
}
