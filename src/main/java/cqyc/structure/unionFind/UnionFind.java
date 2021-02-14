package cqyc.structure.unionFind;

/**
 * @author cqyc
 * @Description:
 * @date 2020/4/25
 */
public interface UnionFind {

    /**
     * id 为p, id 为q的并查集
     */
    boolean isConnected(int p, int q);

    void unionElements(int p, int q);

    int getSize();
}
