package cqyc.structure.unionFind;


import java.util.Arrays;

/**
 * @author cqyc
 * @Description:
 * @date 2021/2/12
 */
public class UnionFindImpl3 implements UnionFind {

    //parent[i], 第i个元素指向的节点
    private int[] parent;
    private int[] sz;

    public UnionFindImpl3(int size) {
        parent = new int[size];
        sz = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            sz[i] = i;
        }
    }

    private int find(int p) {
        if (p < 0 && p > parent.length) {
            throw new IllegalArgumentException("p is out of bound");
        }
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot){
            return;
        }
        if (sz[pRoot] < sz[qRoot]) {
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }


    public static void main(String[] args) {
        UnionFindImpl3 unionFindx = new UnionFindImpl3(10);
        unionFindx.unionElements(2,3);
        System.out.println(Arrays.toString(unionFindx.parent));
        unionFindx.unionElements(2,6);
        System.out.println(Arrays.toString(unionFindx.parent));
        unionFindx.unionElements(3,7);
        System.out.println(Arrays.toString(unionFindx.parent));
    }

}
