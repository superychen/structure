package cqyc.structure.solution.jihe;

import cqyc.structure.Queue.PriorityQueueYc;

import java.util.*;

/**
 * @author cqyc
 * @Description: 队列相关算法
 * @date 2021/1/31
 */
public class QueueSolution {

    private class Freq implements Comparable<Freq>{

        private int t;
        private int freq;

        public Freq(int t, int freq) {
            this.t = t;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq another) {
            //todo 这是给我们自己实现一个优先队列用的比较，出现的频次越低的，优先级就越高
//            if (this.freq < another.freq) {
//                return 1;
//            } else if (this.freq > another.freq) {
//                return -1;
//            } else {
//                return 0;
//            }

            //因为java中的优先队列使用的是最小堆，我们自己实现的是最大堆，所以这里要改变一下
            if (this.freq > another.freq) {
                return 1;
            } else if (this.freq < another.freq) {
                return -1;
            } else return 0;
        }
    }

    /**
     * todo 使用我们自己的实现的优先队列
     * 347. 前 K 个高频元素
     * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
     * 输入: nums = [1,1,1,2,2,3], k = 2
     * 输出: [1,2]
     */
    public int[] topKFrequentMyself(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        PriorityQueueYc<Freq> pq = new PriorityQueueYc<Freq>();
        for (Integer key : map.keySet()) {
            if (pq.getSize() < k) {
                pq.enqueue(new Freq(key, map.get(key)));
            } else {
                if (map.get(key) > pq.getFront().freq) {
                    pq.dequue();
                    pq.enqueue(new Freq(key, map.get(key)));
                }
            }
        }

        List<Integer> list = new LinkedList<Integer>();
        while (!pq.isEmpty()) {
            list.add(pq.dequue().t);
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }


    /**
     * 使用java自带的优先队列
     */
    public int[] topKFrequent(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        PriorityQueue<Freq> pq = new PriorityQueue<Freq>();
        for (Integer key : map.keySet()) {
            if (pq.size() < k) {
                pq.add(new Freq(key, map.get(key)));
            } else {
                if (pq.peek().freq < map.get(key)) {
                    pq.remove();
                    pq.add(new Freq(key, map.get(key)));
                }
            }
        }
        List<Integer> list = new LinkedList<Integer>();
        while (!pq.isEmpty()) {
            list.add(pq.remove().t);
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public int[] topKFrequentYouHua(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return map.get(a) - map.get(b);
            }
        });
        for (Integer key : map.keySet()) {
            if (pq.size() < k) {
                pq.add(key);
            } else {
                if (map.get(pq.peek()) < map.get(key)) {
                    pq.remove();
                    pq.add(key);
                }
            }
        }
        List<Integer> list = new LinkedList<Integer>();
        while (!pq.isEmpty()) {
            list.add(pq.remove());
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,2,2,3};
        QueueSolution queueSolution = new QueueSolution();
        int[] ints = queueSolution.topKFrequent(nums, 2);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }


}
