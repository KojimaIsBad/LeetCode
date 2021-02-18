package 化学表达式解析;

import java.util.*;

/**
 * 给定一个用字符串展示的化学公式，计算每种元素的个数。
 * 规则如下：
 * <p>
 * 元素命名采用驼峰命名，例如 Mg
 * () 代表内部的基团，代表阴离子团
 * [] 代表模内部链节通过化学键的连接，并聚合
 * 例如：H2O => H2O1 Mg(OH)2 => H2Mg1O2
 * <p>
 * 输入描述:
 * 化学公式的字符串表达式，例如：K4[ON(SO3)2]2
 * <p>
 * 输出描述:
 * 元素名称及个数：K4N2O14S4，并且按照元素名称升序排列
 * <p>
 * 测试用例
 * 输入
 * K4[ON(SO3)2]2
 * 输出
 * K4N2O14S4
 */
public class Solution {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String formula = scanner.next();
        String formula = "Mg(OH)2";
//        String formula = "MgPUiK";
        decode(formula);


    }

    /*
    需要考虑的若干问题
    1.识别化学公式
        括号和中括号的使别：根据不同的情况倍乘括号内元素的数量
        数字n的三种出现情况：
            ①紧跟在元素后方，代表有n*该元素
            ②紧跟在括号后方，代表括号内的所有元素都要乘以n
            ③紧跟在大括号后方，也是相同的情况
        具体的方式：通过栈实现括号匹配
    2.存储元素的数据结构
        HashMap
        输出元素的方法
        利用HashMap.keySet得到所有元素，再用get方法依次得到所有化学式的出现数目实现输出
     */
    private static void decode(String formula) {
        int len = formula.length();
        int p = 0;

        Util util = new Util();

        Map<String,Integer> resMap = new HashMap<>();

        Stack<Character> stack = new Stack<>();
        while (p < len) {
            System.out.println(p);
            char cur = formula.charAt(p);
            p++;
            //每次处理一个中括号内的元素
            if (cur != ']')
                stack.push(cur);
            else {//cur = ']' , p实际上是指向']'的下一个元素
                char next = formula.charAt(p);
                //如果"]"紧跟着的是数字x，那么括号内的元素数量*x
                //否则，元素数量*1
                int argv = (Character.isDigit(next)) ? Integer.parseInt(String.valueOf(next)) : 1;
                Stack<Integer> num = new Stack<>();
                while (!stack.empty()){
                    char top = stack.pop();
                    //是数字的情况
                    if(Character.isDigit(top)){
                        num.push(Integer.parseInt(String.valueOf(top)));
                        int arg2 = Integer.parseInt(String.valueOf(top));
                        top = stack.pop();
                        //小写字母
                        if(Character.isLowerCase(top)) {
                            char temp = top;
                            top = stack.pop();
                            String element = String.valueOf(top)+String.valueOf(temp);
                            util.set(resMap,element,argv*arg2);
                        }
                        //大写字母
                        if(Character.isUpperCase(top)){
                            String element = String.valueOf(top);
                            util.set(resMap,element,argv*arg2);
                        }
                        //是括号的情况
                        if(top==')'){
                            while (top!='('){
                                if(Character.isLowerCase(top)) {
                                    char temp = top;
                                    top = stack.pop();
                                    String element = top +String.valueOf(temp);
                                    util.set(resMap,element,argv*arg2);
                                }
                                //大写字母
                                if(Character.isUpperCase(top)){
                                    String element = String.valueOf(top);
                                    util.set(resMap,element,argv*arg2);
                                }
                            }
                        }
                        //是数字的情况
                        if(Character.isDigit(top)){
                            int arg1 = Integer.valueOf(top);
                            top = stack.pop();
                            //小写字母
                            if(Character.isLowerCase(top)) {
                                char temp = top;
                                top = stack.pop();
                                String element = String.valueOf(top)+String.valueOf(temp);
                                util.set(resMap,element,argv*arg2);
                            }
                            //大写字母
                            if(Character.isUpperCase(top)){
                                String element = String.valueOf(top);
                                util.set(resMap,element,argv*arg2);
                            }
                        }
                    }
                    //是括号的情况,且括号系数为1
                    if(top==')'){
                        while (top!='('){
                            if(Character.isLowerCase(top)) {
                                char temp = top;
                                top = stack.pop();
                                String element = top +String.valueOf(temp);
                                util.set(resMap,element,argv);
                            }
                            //大写字母
                            if(Character.isUpperCase(top)){
                                String element = String.valueOf(top);
                                util.set(resMap,element,argv);
                            }
                        }
                    }
                    //小写字母
                    if(Character.isLowerCase(top)) {
                        char temp = top;
                        top = stack.pop();
                        String element = String.valueOf(top)+String.valueOf(temp);
                        util.set(resMap,element,argv);
                    }
                    //大写字母
                    if(Character.isUpperCase(top)){
                        String element = String.valueOf(top);
                        util.set(resMap,element,argv);
                    }

                }
            }
        }
        System.out.println("p");
        //不含中括号的情况
        if(!stack.empty()) {
            System.out.println("stack not empty");
            while (!stack.empty()){
                System.out.println("stack not empty");
                char top = stack.pop();
                //是数字的情况
                if(Character.isDigit(top)){
                    int argv = Integer.valueOf(String.valueOf(top));
                    System.out.println("argv="+argv);
                    top = stack.pop();
                    //小写字母
                    if(Character.isLowerCase(top)) {
                        char temp = top;
                        top = stack.pop();
                        String element = String.valueOf(top)+String.valueOf(temp);
                        util.set(resMap,element,argv);
                    }
                    //大写字母
                    if(Character.isUpperCase(top)){
                        String element = String.valueOf(top);
                        util.set(resMap,element,argv);
                    }
                    if(top==')'){
                        System.out.println("enter )");
                        char high = stack.pop();
                        int arg1 = Character.isDigit(high)?Integer.valueOf(String.valueOf(high)):1;
                        System.out.println("arg1="+arg1);
                        do {
                            if(Character.isLowerCase(top)) {
                                char temp = top;
                                top = stack.pop();
                                String element = top +String.valueOf(temp);
                                util.set(resMap,element,argv*arg1);
                            }
                            //大写字母
                            else if(Character.isUpperCase(top)){
                                String element = String.valueOf(top);
                                util.set(resMap,element,argv*arg1);
                            }
                            else if(Character.isDigit(top)){
                                int arg2 = Integer.valueOf(String.valueOf(top));
                                top = stack.pop();
                                //小写字母
                                if(Character.isLowerCase(top)) {
                                    char temp = top;
                                    top = stack.pop();
                                    String element = String.valueOf(top)+String.valueOf(temp);
                                    util.set(resMap,element,argv*arg1*arg2);
                                }
                                //大写字母
                                else if(Character.isUpperCase(top)){
                                    String element = String.valueOf(top);
                                    util.set(resMap,element,argv*arg1*arg2);
                                }
                            }
                            if (stack.empty())
                                break;
                            else
                                top = stack.pop();
                        }while (top != ')');
                    }
                }
                //是括号的情况,且括号系数为1
                else if(top==')'){
                    System.out.println("括号处理");
                    char high = stack.pop();
                    int argv = Character.isDigit(high)?Integer.valueOf(String.valueOf(high)):1;
                    while (top!='('){
                        if(Character.isLowerCase(top)) {
                            char temp = top;
                            top = stack.pop();
                            String element = top +String.valueOf(temp);
                            System.out.println("x1");
                            util.set(resMap,element,argv);
                        }
                        //大写字母
                        else if(Character.isUpperCase(top)){
                            String element = String.valueOf(top);
                            System.out.println("x2");
                            util.set(resMap,element,argv);
                        }
                        else if(Character.isDigit(top)){
                            int arg1 = Integer.valueOf(String.valueOf(top));
                            top = stack.pop();
                            //小写字母
                            if(Character.isLowerCase(top)) {
                                char temp = top;
                                top = stack.pop();
                                String element = String.valueOf(top)+String.valueOf(temp);
                                System.out.println("x");
                                util.set(resMap,element,argv*arg1);
                            }
                            //大写字母
                            else if(Character.isUpperCase(top)){
                                String element = String.valueOf(top);
                                util.set(resMap,element,argv*arg1);
                            }
                        }
                    }
                }
                else if(Character.isLowerCase(top)){
                    char temp = top;
                    top = stack.pop();
                    String element = String.valueOf(top)+String.valueOf(temp);
                    System.out.println("2x");
                    util.set(resMap,element,1);
                }
                else if(Character.isUpperCase(top)){
                    String element = String.valueOf(top);
                    System.out.println("4x");
                    util.set(resMap,element,1);
                };
            }

            //output
            Collection<String> set = resMap.keySet();
            for(String s : set){
                System.out.print(s+resMap.get(s));
            }


        }
    }
}

class Util{
    public void set(Map<String,Integer> map,String elememt,int value){
        if(map.get(elememt)==null)
            map.put(elememt,value);
        else{
            int curValue = map.get(elememt);
            map.put(elememt,curValue+value);
        }
    }

}
