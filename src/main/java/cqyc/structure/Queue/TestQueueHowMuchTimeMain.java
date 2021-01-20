package cqyc.structure.Queue;

import java.util.Random;

/**
 * @author cqyc
 * @Description:
 * @date 2021/1/8
 */
public class TestQueueHowMuchTimeMain {

    //测试使用q运行opCount个enqueue和dequeue操作所需要的时间，单位：秒
    private static double testQueue(QueueYc<Integer> q, int opCount) {
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            q.dequue();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int opCount = 100000;
        QueueYc<Integer> arrayQueue = new ArrayQueueYc<Integer>();
        double time1 = testQueue(arrayQueue, opCount);
        System.out.println("array queue, time: " + time1 + " s");

        QueueYc<Integer> loopQueueYc = new LoopQueueYc<Integer>();
        double time2 = testQueue(loopQueueYc, opCount);
        System.out.println("loopQueue, time: " + time2 + " s");

        QueueYc<Integer> linkedListQueue = new LinkedListQueue<Integer>();
        double time3 = testQueue(linkedListQueue, opCount);
        System.out.println("linkedListQueue, time: " + time3 + " s");

    }
}
