package cqyc.structure.Tree.set;

/**
 * @author cqyc
 * @Description:
 * @date 2020/4/25
 */
public interface SetYc<T> {

    void add(T t);

    void remove(T t);

    boolean contains(T t);

    int getSize();

    boolean isEmpty();

}
