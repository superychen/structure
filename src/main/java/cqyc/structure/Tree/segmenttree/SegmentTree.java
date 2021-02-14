package cqyc.structure.Tree.segmenttree;


/**
 * @author cqyc
 * @Description:
 * @date 2021/2/2
 */
public class SegmentTree<T> {

    private T[] tree;
    private T[] data;
    private Merger<T> merger;

    public SegmentTree(T[] arr, Merger<T> merger) {
        this.merger = merger;
        data = (T[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        //开辟4 * n得线段树得空间
        tree = (T[]) new Object[4 * arr.length];
        buildSegmentTree(0, 0, arr.length - 1);
    }

    //在treeIndex的位置创建表示区间[l, r]的线段树
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[r];
            return;
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);
        //合并接口由实际的真实业务场景决定
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    /**
     * 返回区间【queryL,queryR】的值
     */
    public T query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length) {
            throw new IllegalArgumentException("index is illegal");
        }
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    /**
     * 实现类 查询类的实现类
     */
    private T query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (queryL >= mid + 1) {
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {
             return query(leftTreeIndex, l, mid, queryL, queryR);
        }
        T leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        T rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(leftResult, rightResult);
    }

    public int getSize() {
        return data.length;
    }

    public T get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illeal.");
        }
        return data[index];
    }

    //返回完全二叉树得数组表示中，一个索引所表示得元素得左孩子节点得u偶姻
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    //返回完全二叉树得数组表示中，一个索引所表示得元素得右孩子节点得索引
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    public void set(int index, T t) {
        if (index < 0 || index >= data.length) {
            throw  new IllegalArgumentException("index is illegal");
        }
        data[index] = t;
        set(0, 0, data.length - 1, index, t);
    }

    //在以treeindex为根的线段树中更新index的值为e
    private void set(int treeIndex, int l, int r, int index, T t) {
        if (l == r) {
            tree[treeIndex] = t;
            return;
        }
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if (index >= mid + 1) {
            set(rightTreeIndex, mid + 1, r, index, t);
        } else {
            // index <= mid
            set(leftTreeIndex, l, mid, index, t);
        }
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null) {
                builder.append(tree[i]);
            } else {
                builder.append("null");
            }
            if (i != tree.length - 1)
                builder.append(", ");
        }
        builder.append(']');
        return builder.toString();
    }

    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> tree = new SegmentTree<>(nums, (a, b) -> a + b);
        System.out.println(tree);

        System.out.println(tree.query(0, 2));
        System.out.println(tree.query(0, 3));
    }

}
