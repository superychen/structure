package cqyc.structure.Stack;

/**
 * @author cqyc
 * @Description:
 * @date 2021/1/5
 */
public interface StackYc<T> {

    int getSize();

    boolean isEmpty();

    void push(T t);

    T pop();

    T peek();
}
