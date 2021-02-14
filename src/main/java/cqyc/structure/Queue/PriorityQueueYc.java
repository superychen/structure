package cqyc.structure.Queue;

import cqyc.structure.Queue.heap.MaxHeap;


/**
 * @author cqyc
 * @Description:
 * @date 2021/1/31
 */
public class PriorityQueueYc<T extends Comparable<T>> implements QueueYc<T> {

    private MaxHeap<T> maxHeap;

    public PriorityQueueYc() {
        maxHeap = new MaxHeap<T>();
    }


    @Override
    public int getSize() {
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public void enqueue(T t) {
        maxHeap.add(t);
    }

    @Override
    public T dequue() {
        return maxHeap.extractMax();
    }

    @Override
    public T getFront() {
        return maxHeap.findMax();
    }
}
