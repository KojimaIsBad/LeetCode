package lc172;

/**
 * 给定一个整数 n，返回 n! 结果尾数中零的数量。
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(new Solution().trailingZeroes(7));
    }
}
class Solution {
    //阶乘直接算会溢出
    public int trailingZeroes(int n) {


        //计算n!
        int res = 1;
        for(int i=1;i<=n;i++)
            res *=i;

        //num = 尾数中0的个数
        int num = 0;
        //计算n!的位数
        String temp = Integer.toString(res);
        int digs = temp.length();
        //逐位判断
        for(int i=0;i<digs;i++){
            if(temp.charAt(i)=='0')
                num++;
        }
        return num;
    }
}
