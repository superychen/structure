package cqyc.structure.Queue;


import java.util.Arrays;

/**
 * @author cqyc
 * @Description: 循环队列
 * @date 2021/1/8
 */
public class LoopQueueYc<T> implements QueueYc<T> {

    private T[] data;

    //队首所指向的位置
    private int front;

    //队尾所指向的位置
    private int tail;

    private int size;

    public LoopQueueYc(int capacity) {
        data = (T[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueueYc() {
        this(10);
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(T t) {
        //如果当前循环队列已经满了，则进行扩容
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }
        data[tail] = t;
        tail = (tail + 1) % data.length;
        size ++;
    }

    private void resize(int newCapacity) {
        T[] newData = (T[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public T dequue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }
        T ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        //缩容
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    @Override
    public T getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }
        return data[front];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Queue size = %d, capacity = %d\n", size, getCapacity()));
        builder.append("front: [");
        for (int i = front; i != tail; i = (i +1) % data.length){
            builder.append(data[i]);
            if ((i + 1) % data.length != tail) {
                builder.append(", ");
            }
        }
        builder.append("] :tail");
        return builder.toString();
    }

    public static void main(String[] args) {
        QueueYc<Integer> loopQueueYc = new LoopQueueYc<Integer>();
        for (int i = 0; i < 10; i++) {
            loopQueueYc.enqueue(i);
            System.out.println(loopQueueYc);
            if (i % 3 == 2) {
                loopQueueYc.dequue();
                System.out.println(loopQueueYc);
            }
        }
    }
}
