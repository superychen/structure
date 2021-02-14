package cqyc.structure.Queue.heap;

import cqyc.structure.array.ArrayYcThree;

import java.util.Random;

/**
 * @author cqyc
 * @Description: 最大堆（用数组来转入完全二叉树）
 * @date 2021/1/26
 */
public class MaxHeap<T extends Comparable<T>> {

    private ArrayYcThree<T> data;

    public MaxHeap(int capacity) {
        data = new ArrayYcThree<T>(capacity);
    }

    public MaxHeap() {
        data = new ArrayYcThree<T>();
    }

    /**
     * heapify操作
     */
    public MaxHeap(T[] arr) {
        data = new ArrayYcThree<T>(arr);
        for (int i = parent(arr.length - 1); i >= 0 ; i--) {
            siftDown(i);
        }
    }

    //返回堆中的元素个数
    public int size() {
        return data.getSize();
    }

    //返回一个布尔值，表示堆中是否为空
    public boolean isEmpty() {
        return data.isEmpty();
    }

    //返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        }
        return (index - 1) / 2;
    }

     //返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    //返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    //向堆中添加元素
    public void add(T t) {
        data.addLast(t);
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int k) {
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            //进行交换
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    public T findMax() {
        if (data.getSize() == 0) {
            throw new IllegalArgumentException("Can't not findMax when heap is empty");
        }
        return data.get(0);
    }

    //去除堆中最大元素
    public  T extractMax() {
        T ret = findMax();
        //对索引最后一个值，以及最大的值进行交换
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    private void siftDown(int k) {
        while (leftChild(k) < data.getSize()) {
            //先找到下沉的对应的是左孩子还是右孩子,然后data[j]就是左右孩子中最大的一个
            int j = leftChild(k);
            if(j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                j = rightChild(k);
            }
            //如果当前节点大于左右孩子，就直接跳出循环
            if (data.get(k).compareTo(data.get(j)) >= 0) {
                break;
            }
            data.swap(k, j);
            k = j;
        }
    }

    //取出堆中的最大元素，并且替换成元素e
    public T replace(T t) {
        T ret = findMax();
        data.set(0, t);
        siftDown(0);
        return ret;
    }

    public static void main(String[] args) {
        int n = 1000000;
        Random random = new Random();
        Integer[] integers = new Integer[n];
        for (int i = 0; i < n; i++) {
            integers[i] = random.nextInt(Integer.MAX_VALUE);
        }
        double time1 = testHeap(integers, false);
        System.out.println("Without heapify: " + time1 + "s");
        double time2 = testHeap(integers, true);
        System.out.println("with heapify: " + time2 + "s");
    }

    private static double testHeap(Integer[] testData, boolean isHeapify) {
        long startTime = System.nanoTime();
        MaxHeap<Integer> maxHeap;
        if (isHeapify) {
            maxHeap = new MaxHeap<Integer>(testData);
        } else {
            maxHeap = new MaxHeap<Integer>();
            for (Integer num : testData) {
                maxHeap.add(num);
            }
        }
        int[] arr = new int[testData.length];
        for (int i = 0; i < testData.length; i++) {
            arr[i] = maxHeap.extractMax();
        }

        for (int i = 1; i < testData.length; i++) {
            if (arr[i - 1] < arr[i]) {
                throw new IllegalArgumentException("Error");
            }
        }
        System.out.println("Test MaxHeap completed");

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;

    }
}
