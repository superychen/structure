package cqyc.structure.unionFind;

/**
 * @author cqyc
 * @Description:
 * @date 2021/2/11
 */
public class UnionFindImpl implements UnionFind {

    private int[] id;

    public UnionFindImpl(int size) {
        id = new int[size];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    //查找元素p所对应的集合编号
    private int find(int p) {
        if (p < 0 && p >= id.length){
            throw new IllegalArgumentException("p is out of bound.");
        }
        return id[p];
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    //这种并查集

    /**
     * unionElements(p,q) ---> O((n)
     * isConnected(p,q) ---> O(1)
     */
    @Override
    public void unionElements(int p, int q) {
        int pId = find(p);
        int qId = find(q);
        if (pId == qId){
            return;
        }
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId) {
                id[i] = qId;
            }
        }
    }

    @Override
    public int getSize() {
        return id.length;
    }
}
