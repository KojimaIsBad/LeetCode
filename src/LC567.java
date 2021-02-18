import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC567 {
    public static void main(String[] args) {
//        System.out.println(new Solution().check("adc", "dcda"));
        for(char a='a';a<='z';a++){
            System.out.println(a);
        }
    }
}

/**
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * <p>
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 */

class Solution {
    /**
     * 判断s2是否包含s1
     *
     * @param s1 模式
     * @param s2 长串
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        //s1中各字符的出现频率
        int[] cnt1 = new int[26];
        //s2中各字符的出现频率
        int[] cnt2 = new int[26];
        //只统计一个窗口内的字符计数
        for (int i = 0; i < n; ++i) {
            ++cnt1[s1.charAt(i) - 'a'];
            ++cnt2[s2.charAt(i) - 'a'];
        }
        //s1 s2出现频率相同
        if (Arrays.equals(cnt1, cnt2)) {
            return true;
        }
        //窗口右滑
        //滑动窗口每向右滑动一次，就多统计一次进入窗口的字符，少统计一次离开窗口的字符
        for (int i = n; i < m; ++i) {
            ++cnt2[s2.charAt(i) - 'a'];
            --cnt2[s2.charAt(i - n) - 'a'];
            if (Arrays.equals(cnt1, cnt2)) {
                return true;
            }
        }
        return false;
    }
    public boolean checkInclusion1(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        Map<Character,Integer> c1 = new HashMap<>();
        Map<Character,Integer> c2 = new HashMap<>();
        for(char a='a';a<='z';a++){
            c1.put(a,0);
            c2.put(a,0);
        }
        if (n > m) {
            return false;
        }
        //s1中各字符的出现频率
//        int[] cnt1 = new int[26];
        //s2中各字符的出现频率
//        int[] cnt2 = new int[26];
        //只统计一个窗口内的字符计数
        for (int i = 0; i < n; ++i) {
//            ++cnt1[s1.charAt(i) - 'a'];
//            ++cnt2[s2.charAt(i) - 'a'];
            c1.put(s1.charAt(i),c1.get(s1.charAt(i))+1);
            c2.put(s2.charAt(i),c2.get(s2.charAt(i))+1);

        }
        //s1 s2出现频率相同
        if (c1.equals(c2)) {
            return true;
        }
        //窗口右滑
        //滑动窗口每向右滑动一次，就多统计一次进入窗口的字符，少统计一次离开窗口的字符
        for (int i = n; i < m; ++i) {
//            ++cnt2[s2.charAt(i) - 'a'];
            c2.put(s2.charAt(i),c2.get(s2.charAt(i))+1);
//            --cnt2[s2.charAt(i - n) - 'a'];
            c2.put(s2.charAt(i-n),c2.get(s2.charAt(i))-1);
            if (c1.equals(c2)) {
                return true;
            }
        }
        return false;
    }

    public boolean check(String s1, String s2) {
        Map<Character, Integer> map = new HashMap<>();
        char[] pattern = s1.toCharArray();
        char[] set = s2.toCharArray();
        for (int i = 0; i < s1.length(); i++) {
            int val = 0;
            char cur = pattern[i];
            if (map.containsKey(cur))
                val = map.get(cur);
            map.put(cur, ++val);
        }
        A:for (int i = 0; i < s2.length() - s1.length()+1; i++) {
            int count = 0;
            char cur = set[i];
            B:while (count < s1.length()) {
                if (map.containsKey(cur) && map.get(cur) > 0) {
                    int newVal = map.get(cur);
                    map.put(cur, newVal - 1);
                    count++;
                    if(count==s1.length())
                        return true;
                    else
                        cur=s2.charAt(i+count);
                } else {
                    if (count > 0) {//出现失配，恢复现场
                        for (int j = 0; j < count; j++){
                            char target = set[i + j];
                            int oldVal = map.get(target);
                            map.put(target, oldVal + 1);
                        }

                    }
                    continue A;
                }
            }


        }
        return false;
    }
}