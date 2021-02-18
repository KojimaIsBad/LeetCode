package LC638;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LC638 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<Integer> price = new ArrayList<>(Arrays.asList(2, 5));
        List<List<Integer>> special = new ArrayList<>();
        special.add(new ArrayList<>(Arrays.asList(3, 0, 5)));
        special.add(new ArrayList<>(Arrays.asList(1, 2, 10)));
        List<Integer> needs = new ArrayList<>(Arrays.asList(9, 6));
        System.out.println(solution.shoppingOffers(price, special, needs));
    }
}

class Solution {
    List<Integer> list = new ArrayList<>();
    int dp[][][][][][];
    /**
     * [1,2,10]
     *
     * @param price   每种物品的价格
     * @param special 大礼包物品清单以及价格
     * @param needs   所需物品的重量
     * @return
     */
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        /*
        当前总金额
        仍需购买的物品清单
        $10 A5 B4 C3 [2,1,3,8] [6,2,0,7]
        new price = 18[3,3,0] 或者 17[0,2,3]
        每当ABC商品采购达到要求的时候输出金额
         */
//        boolean isFinished = true;
//        for(int i:needs)
//            if(i>0){
//                isFinished = false;
//                break;
//            }
//        if(isFinished)
//            return money;
//        money += shoppingOffers(price,special,needs);
        int size[] = new int[6];
        for (int i : size) i = 0;
        for (int i = 0; i < needs.size(); i++)
            size[i] = needs.get(i);
        dp = new int[size[0]][size[1]][size[2]][size[3]][size[4]][size[5]];
        dp[0][0][0][0][0][0] = 0;
        for(int i0=0;i0<6;i0++)
            for(int i1=0;i1<6;i1++)
                for(int i2=0;i2<6;i2++)
                    for(int i3=0;i3<6;i3++)
                        for(int i4=0;i4<6;i4++)
                            for(int i5=0;i5<6;i5++){

                            }
        return 0;
    }

}