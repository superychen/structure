package cqyc.structure.Queue;

/**
 * @author cqyc
 * @Description:
 * @date 2020/4/25
 */
public interface QueueYc<T> {
    int getSize();

    boolean isEmpty();

    /**
     * 进入一个元素
     */
    void enqueue(T t);

    /**
     * 删除一个元素
     */
    T dequue();

    /**
     * 获取队首元素
     */
    T getFront();
}
