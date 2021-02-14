package cqyc.structure.Tree.segmenttree;

/**
 * @author cqyc
 * @Description:
 * @date 2021/2/3
 */
public class NumArray {

    private SegmentTree<Integer> segmentTree;

    //第二种方法,sum[i]存储前i个元素和，sum[0] = 0;
    //sum[i] 中存储的是nums[0.....i-1]的和
    private int[] sum;

    public NumArray(int[] nums) {
//        if (nums.length > 0) {
//            Integer[] data = new Integer[nums.length];
//            for (int i = 0; i < nums.length; i++) {
//                data[i] = nums[i];
//            }
//            segmentTree = new SegmentTree<>(data, (i, j) -> i + j);
//        }
        sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
    }

    public int sumRange(int i, int j) {
//        if (segmentTree == null) {
//            throw new IllegalArgumentException("segment tree is null");
//        }
//        return segmentTree.query(i, j);
        return sum[j + 1] - sum[i];
    }


}
