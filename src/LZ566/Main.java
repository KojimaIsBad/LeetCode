package LZ566;

public class Main {
    public static void main(String[] args) {
//        int[][] num = new int[2][];
//        num[0] = new int[]{1,2};
//        num[1] = new int[]{3,4};
        int num[][] = new int[4][1];
        num[0][0] = 1;
        num[1][0] = 2;
        num[2][0] = 3;
        num[3][0] = 4;

        int[][] res = new Solution().matrixReshape(num,1,4);
        for(int i=0;i<res.length;i++){
            System.out.print("Row "+i+" : ");
            for(int j=0;j<res[i].length;j++){
                System.out.print(res[i][j]+" ");
            }
            System.out.println();
        }

    }
}
class Solution {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int s1 = nums.length;
        int s2 = nums[0].length;
        if(s1*s2!=r*c)
            return nums;
        else{
            int res[][] = new int[r][c];
            for(int i=0;i<s1;i++)
                for (int j=0;j<s2;j++){
                    int index = i*s2+j;
                    int row = index/c;
                    int col = index-row*c;
                    res[row][col] = nums[i][j];
                }
            return res;
        }
    }
}
