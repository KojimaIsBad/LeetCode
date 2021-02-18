package 古生物亲缘关系判定;

import java.util.Scanner;

/**
 * 利用动态规划来解决问题
 */
public class Solution2 {
    public static void main(String[] args) {
//        String str = scanner.next();
        String str = "GACTA,AGACT";
        System.out.println(new Solution2().compute(str));
    }

    /**
     * 动他规划解法
     * @param DNA
     * @return
     */
    public int compute(String DNA){
        int count =0;
        //处理这两个DNA序列
        String str[] = DNA.split(",");
        int l0 = str[0].length();
        int l1 = str[1].length();

        //dp[i][j]代表由DNA[0]的【0,i-1】突变到DNA[1]【0,j-1】所需的突变次数
        int dp[][] = new int[l0+1][];
        for(int i=0;i<l0;i++)
            dp[i] = new int[l1+1];
        //设定初始情况
        dp[0][0] = 0;
        for(int i=0;i<=l0;i++)
            dp[i][0]= i;
        for(int j=0;j<=l1;j++)
            dp[0][j] = j;

        //对其余情况进行动态规划
        for(int i=1;i<=l0;i++){
            for(int j=1;j<=l1;j++){
                //当前比较的字符匹配的情况
                /**
                 * 要注意char at 的参数问题
                 */
                if(str[0].charAt(i-1)==str[1].charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{//字符不匹配的情况
                    int replace = dp[i-1][j-1];
                    /**
                     * dp[i-1][j] stands for replace;dp[i][j-1] stands for add
                     */
                    int modify = Math.min(dp[i-1][j],dp[i][j-1]);
                    dp[i][j] = 1+Math.min(replace,modify);
                }
            }

        }


        return dp[l0][l1];
    }
}
/**
 * 思考过程
 */
/*
AGCTAGCT
OAGCT->GCACT
GACTA
AGACT
最小异变数 =最长DNA长度 -  最长部分匹配字串长度 + 1

有多种方式可以达成异变

需要改变第一条向第二条发展

获取两条DNA序列的最小异变树
AX GX
1 1
//利用替换
dp[0][0] = 0;
dp[1][1] = 1+dp[0][0];

//利用增加
GAX->GX dp[i][j-1]
dp[1][1] = dp[1][0]+1

A->G dp[i][j]
=>
X->G dp[i-1][j]
//利用删除
dp[1][1]=2+dp[0][0]
 */
