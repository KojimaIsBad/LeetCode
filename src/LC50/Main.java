package LC50;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(new Solution().myPow(2.00000, 2));
    }
}

/**
 * -100.0 < x < 100.0
 * -231 <= n <= 231-1
 * -104 <= xn <= 104
 */
class Solution {
    Map<Integer, Double> map = new HashMap<>();

    public double myPow(double x, int n) {
        if (x == 0)
            return 0;
        if (n == 0) {
            map.put(0, 1.0);
            return 1;
        }
        if (n == 1) {
            map.put(1, x);
            return x;
        }
        if (n == -1) {
            map.put(-1, 1 / x);
            return 1 / x;
        }
        int n1 = n / 2;
        int n2 = n - n1;
        double res1 = 0, res2 = 0;
        if (map.containsKey(n1))
            res1 = map.get(n1);
        else {
            res1 = myPow(x, n1);
            map.put(n1, res1);
        }
        if (map.containsKey(n2))
            res2 = map.get(n2);
        else {
            res2 = myPow(x, n2);
            map.put(n2, res2);

        }
        return res1 * res2;
    }
}

class Solution1 {
    /**
     * 快速幂法
     */
    public double pow(double x, int n) {
        //x=0的特殊情况
        if (x == 0d)
            return x;
        //|max_INTEGER|<|MIN_INTEGER|，因此需要用long来处理参数n
        long n1 = n;
        double res = 1;
        //如果幂为负数，则转化为正数幂处理
        if (n1 < 0) {
            x = 1 / x;
            n1 = -n1;
        }
        //分治思想
        /*
        若n为奇数，则x^n = x^(n/2)*x^(n/2)*x;
        若n为偶数，则x^n = x^(n/2)*x^(n/2);
         */
        /*
        if(n为奇数）
            return x*[pow(x^2,n/2)^2];
        if(n为偶数)
            return pow(x^2,n/2)^2
         */
        while (n1 > 0) {
            //n为奇数
            if (n1 % 2 == 1)//-> n+1 => x^2,n/2 => x*4,(n/2-1)/2
                res *= x;
            x *= x;
            n1 /= 2;
        }
        return res;
    }
}
