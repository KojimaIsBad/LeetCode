package LC665;

/**
 *
 */
public class Main {
    public static void main(String[] args) {
        int[] array = {4, 2, 3};
        System.out.println(new Solution().checkPossibility(array));
//        System.out.println(new Solution().newCheck(array));
    }
}

class Solution {
    public boolean checkPossibility(int[] nums) {
        int noneAscCount = 0;
//        for(int i=0;i<nums.length-1;i++){
//            if(nums[i]>nums[i+1])
//                noneAscCount++;
//        }
//
//        if(noneAscCount>1)
//            return false;
//
//        return true;
        noneAscCount = newCheck(nums, 0, 0);
        return noneAscCount > 1 ? false : true;
    }

    public int newCheck(int[] nums, int cur, int count) {
        if (count > 1 || cur == nums.length - 1)
            return count;
//        count += nums[cur]>nums[cur+1] ? 1 : 0;
        //出现递减情况
        if (nums[cur] > nums[cur + 1]) {
            int self = 2, next = 2;
            if (cur == 0 || nums[cur + 1] >= nums[cur - 1]) {
                int[] modifySelf = new int[nums.length];
                System.arraycopy(nums, 0, modifySelf, 0, nums.length);
                modifySelf[cur] = nums[cur + 1];
                self = newCheck(modifySelf, cur + 1, count + 1);
            }

            int[] modifyNext = new int[nums.length];
            System.arraycopy(nums, 0, modifyNext, 0, nums.length);
            modifyNext[cur + 1] = nums[cur];
            next = newCheck(modifyNext, cur + 1, count + 1);

            return Math.min(self, next);
        }

        return newCheck(nums, cur + 1, count);
    }


}

class Solution2 {

    public boolean checkPossibility(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int cur = nums[i];
            int next = nums[i + 1];
            //出现递减情况
            if (cur > next) {
                //改大小数
                nums[i + 1] = cur;
                if (checkIfSorted(nums))
                    return true;
                nums[i + 1] = next;
                nums[i] = next;
                return checkIfSorted(nums);
            }

        }
        return true;
    }

    boolean checkIfSorted(int[] nums) {
        if (nums.length == 1)
            return true;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1])
                return false;
        }
        return true;
    }
}

