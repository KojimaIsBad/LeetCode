package 数组组成最大数;

import java.util.*;

/**
 * 给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
 */
public class Solution {
    /**
     * 错误解法，没有注意到数组的每个数是不能拆的
     *
     * @param args
     */
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        String str = in.next();
        str = str.substring(1,str.length()-1);
        String[] strs = str.split(",");
        Arrays.sort(strs,(o1,o2)->(o2+o1).compareTo(o1+o2));
        StringBuilder stringBuilder = new StringBuilder();
        for(String s:strs) stringBuilder.append(s);
        System.out.print(stringBuilder.toString());
    }


    public static void correct() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        String[] str1 = str.substring(1, str.length() - 1).split(",");
        //数据的数量
        int length = str1.length;
        Map<String, Integer> digits = new HashMap<>();

        for (String s : str1)
            digits.put(s, s.length());

        Collection<Integer> set = digits.values();
        Integer[] digs = set.toArray(new Integer[0]);
        Arrays.sort(digs);

        int times = digs[0];
        for (int t = 0; t < 1; t++)
            for (int i = 1; i < length; i++) {
                int cur = length - 1;
                while (cur > 0) {
                    char now = str1[cur].charAt(t);
                    char pred = str1[cur].charAt(t);
                    if (now > pred) {
                        //swap
                        String temp = str1[cur];
                        str1[cur] = str1[cur - 1];
                        str1[cur - 1] = temp;
                    }
                    cur--;
                }
            }
        for(String s:str1)
            System.out.print(s);

        //两次冒泡

    }


}
