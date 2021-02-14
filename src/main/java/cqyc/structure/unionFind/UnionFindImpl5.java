package cqyc.structure.unionFind;


import java.util.Arrays;

/**
 * @author cqyc
 * @Description:
 * @date 2021/2/12
 */
public class UnionFindImpl5 implements UnionFind {

    //parent[i], 第i个元素指向的节点
    private int[] parent;
    private int[] rank;

    public UnionFindImpl5(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    //使用递归进行路径压缩
    private int find(int p) {
        if (p < 0 && p > parent.length) {
            throw new IllegalArgumentException("p is out of bound");
        }
        if (p != parent[p]) {
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    //根据两个元素所在树的rank不同判断合并方向
    //将rank低的集合合并到rank高的集合
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot){
            return;
        }
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[qRoot] < rank[pRoot]){
            parent[qRoot] = pRoot;
        } else {
            //rank[pRoot] == rank[qRoot]
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }


    public static void main(String[] args) {
        UnionFindImpl5 unionFindx = new UnionFindImpl5(10);
        unionFindx.unionElements(2,3);
        System.out.println(Arrays.toString(unionFindx.parent));
        unionFindx.unionElements(2,6);
        System.out.println(Arrays.toString(unionFindx.parent));
        unionFindx.unionElements(3,7);
        System.out.println(Arrays.toString(unionFindx.parent));
    }

}
