package LC53;

import java.util.Arrays;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 */
public class Min {
    public static void main(String[] args) {
        int[] a = {-2,1,-3,4,-1,2,1,-5,4};
//        System.out.println(new Solution().maxDP(a));
    }
}

class Solution {
    /**
     * 暴力法O(n^2)
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            //从a开始计算
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                max = sum > max ? sum : max;
            }
        }
        return max;
    }

    /**
     * 贪心算法
     * @param nums
     * @return
     */
    public int maxTX(int[] nums){
        int max = Integer.MIN_VALUE;
        int sum = 0;
        /**
         * 遍历到某个元素时，若这个元素之前序列的和为负，则丢弃之前的序列
         */
        for(int i=0;i<nums.length;i++){
            if(sum>=0)
                sum += nums[i];
            else
                sum = nums[i];
            max=Integer.max(max,sum);
        }
        return max;
    }

    public int DP(int[] nums){
        for(int i=1;i<nums.length;i++){
            if(nums[i-1]>=0)
                nums[i]+=nums[i-1];
        }
        Arrays.sort(nums);
        return nums[nums.length-1];
    }

}
