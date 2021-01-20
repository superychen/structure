package cqyc.structure.solution.recursion;

/**
 * @author cqyc
 * @Description: 递归： 利用递归来进行arr计算和
 * @date 2021/1/13
 */
public class Sum {

    public static int sum(int[] arr) {
        return sum(arr, 0);
    }

    public static int sum(int[] arr, int l) {
        if (l == arr.length) {
            return 0;
        }
        return arr[l] + sum(arr, l + 1);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(sum(nums));
    }
}
