package LC22;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：["()"]
 *
 * n的取值范围为[1,8]
 */

public class LC22 {
    public static void main(String[] args) {
        System.out.println("hello world");
        int n =3;
        List<String> list = new Solution().generateParenthesis(n);
        for(String s:list)
            System.out.println(s);
    }
}

class Solution {
    /**
     *  从左到右逐位添加括号
     *
     * @param list  放置结果的数组
     * @param left  当前左括号数量
     * @param right 当前右括号数量
     * @param cur   第一个空位的索引值
     * @param n     要求的括号对数
     * @param temp 中间串
     */
    private void solute(List<String> list,int left,int right,int cur,int n,StringBuilder temp){

        //当生成完毕后
        if(left+right==2*n){
            list.add(temp.toString());
            return;
        }

        //尝试加左括号：只要当前左括号数量<=括号对数，加左括号即合法
        if(left<n){
            temp.setCharAt(cur,'(');
            solute(list,left+1,right,cur+1,n,temp);
        }
        //尝试加右括号：只要当前左括号数量>右括号数量，加右括号即合法
        if(left>=right+1){
            temp.setCharAt(cur,')');
            solute(list,left,right+1,cur+1,n,temp);
        }
    }
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        /*利用StringBuilder暂存中间串*/
        StringBuilder temp = new StringBuilder();
        temp.setLength(2*n);
        solute(list,0,0,0,n,temp);
        return list;
    }
}