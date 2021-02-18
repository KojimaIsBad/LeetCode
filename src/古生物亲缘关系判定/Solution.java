package 古生物亲缘关系判定;

import java.util.Scanner;


public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        String str = scanner.next();
        String str = "GACTA,AGACT";
        System.out.println(new Solution().getDiff(str));
    }
    public int getDiff(String DNA) {
        int count = 0;

        //处理这两个DNA序列
        String str[] = DNA.split(",");
        int l0 = str[0].length();
        int l1 = str[1].length();

        //用短串匹配长串
        count = compute(str[0],str[1],0,0);
        System.out.println("count="+count);
        int sum = Math.max(l0,l1)-count;
        return sum;
    }

    private int compute(String DNA1,String DNA2,int index1,int index2){
        if(index1>=DNA1.length()||index2>=DNA2.length())
            return 0;
        if(DNA1.charAt(index1)==DNA2.charAt(index2)){
            return 1+compute(DNA1,DNA2,index1+1,index2+1);
        }else return Math.max(compute(DNA1,DNA2,index1+1,index2),compute(DNA1,DNA2,index1,index2+1));

    }

}

