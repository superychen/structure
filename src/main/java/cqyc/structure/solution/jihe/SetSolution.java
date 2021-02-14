package cqyc.structure.solution.jihe;

import java.util.*;

/**
 * @author cqyc
 * @Description: 集合相关简单小算法
 * @date 2021/1/25
 */
public class SetSolution {


    /**
     * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出：[2]
     * 输出结果中的每个元素一定是唯一的。
     * 我们可以不考虑输出结果的顺序
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new TreeSet<Integer>();
        for (int i : nums1) {
            set.add(i);
        }
        int min = Math.min(nums1.length, nums2.length);
        int[] ints = new int[min];
        int k = 0;
        for (int i : nums2) {
            if (set.contains(i)) {
                ints[k++] = i;
                set.remove(i);
            }
        }
        return Arrays.copyOf(ints, k);
    }

    /**
     * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出：[2,2]
     * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
     * 我们可以不考虑输出结果的顺序。
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for (int i : nums1) {
            Integer res = map.get(i);
            if (res == null) {
                map.put(i, 1);
            } else {
                map.put(i, res + 1);
            }
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i : nums2) {
            if (map.containsKey(i)) {
                Integer res = map.get(i);
                list.add(i);
                map.put(i, res - 1);
                if (res - 1 == 0) {
                    map.remove(i);
                }
            }
        }

        int[] ints = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ints[i] = list.get(i);
        }
        return ints;
     }

    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素
     * 输入: [2,2,1]
     * 输出: 1
     */
    public int singleNumber(int[] nums) {
//        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        int single = 0;
        for (int num : nums) {
//            Integer res = map.get(num);
//            if (res == null) {
//                map.put(num, 1);
//            } else {
//                map.put(num, res + 1);
//            }
            single ^= num;
        }
//        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
//            if (entry.getValue() == 1) {
//                return entry.getKey();
//            }
//        }
        return single;
    }

    /**
     *  给你一个有效的 IPv4 地址 address，返回这个 IP 地址的无效化版本。
     *  所谓无效化 IP 地址，其实就是用 "[.]" 代替了每个 "."。
     *  输入：address = "1.1.1.1"
     * 输出："1[.]1[.]1[.]1"
     */
    public String defangIPaddr(String address) {
        return address.replace(".", "[.]");
    }

    /**
     * 给你一个整数数组 nums，请你返回其中位数为 偶数 的数字的个数。
     *  输入：nums = [555,901,482,1771]
     * 输出：1
     * 只有 1771 是位数为偶数的数字。
     */
    public int findNumbers(int[] nums) {
        int i = 0;
        for (int num : nums) {
            String s = String.valueOf(num);
            if (s.length() % 2 == 0) {
                i++;
            }
        }
        return i;
    }


    public static void main(String[] args) {
        SetSolution setSolution = new SetSolution();
        int[] nums1 = new int[]{1, 2, 2, 1, 5};
        int[] nums2 = new int[]{2, 2};
        int[] intersect = setSolution.intersect(nums1, nums2);
        for (int i : intersect) {
            System.out.println(i);
        }

        int i = setSolution.singleNumber(nums1);
        System.out.println(i);

        System.out.println("-------------");
        int a = 2 ^ 4;
        System.out.println(a);

        setSolution.defangIPaddr("1.1.1.1");
    }
}
