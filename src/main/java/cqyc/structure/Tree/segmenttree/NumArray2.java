package cqyc.structure.Tree.segmenttree;

/**
 * @author cqyc
 * @Description: 数据预处理
 * @date 2021/2/3
 */
public class NumArray2 {

    //第二种方法,sum[i]存储前i个元素和，sum[0] = 0;
    //sum[i] 中存储的是nums[0.....i-1]的和
    private int[] sum;

    private int[] data;

    public NumArray2(int[] nums) {
        data = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            data[i] = nums[i];
        }
        sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
    }

    public void update(int index, int val) {
        data[index] = val;
        //从需要修改的索引开始修改，一次重新计算
        for (int i = index + 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + data[i -1];
        }
    }

    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }


}
