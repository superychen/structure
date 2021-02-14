package cqyc.structure.unionFind;

import java.util.Arrays;
import java.util.Random;

/**
 * @author cqyc
 * @Description:
 * @date 2021/2/12
 */
public class UnionFindImpl2 implements UnionFind {

    //parent[i], 第i个元素指向的节点
    public int[] parent;

    public UnionFindImpl2(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
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
        parent[pRoot] = qRoot;
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    public static void main(String[] args) {
//        int size = 10000;
//        int m = 10000;
//        UnionFindImpl unionFind1 = new UnionFindImpl(size);
//        System.out.println("unionfind1: " + testUF(unionFind1, m) + "s");
//        UnionFindImpl2 unionFind2 = new UnionFindImpl2(size);
//        System.out.println("unionfind2: " + testUF(unionFind2, m) + "s");

        UnionFindImpl2 unionFindx = new UnionFindImpl2(10);
        unionFindx.unionElements(2,3);
        unionFindx.unionElements(2,6);
        unionFindx.unionElements(3,7);
        System.out.println(Arrays.toString(unionFindx.parent));
    }

    private static double testUF(UnionFind uf, int m) {
        int size = uf.getSize();
        Random random = new Random();
        long startTime = System.nanoTime();

        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a, b);
        }

        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a, b);
        }
        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;


    }
}
