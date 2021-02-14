package cqyc.structure.Tree.segmenttree;

/**
 * @author cqyc
 * @Description:
 * @date 2020/4/25
 */
public interface Merger<T> {

    T merge(T a, T b);

}
