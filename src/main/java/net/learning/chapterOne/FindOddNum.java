package net.learning.chapterOne;

/**
 * 问题1：整型数组 两个数 一类是出现偶数次的数  另一个是出现奇数次的数 找出出现奇数次那个数
 */
public class FindOddNum {
    private static  final Integer[] arr= {10,10,11,11,11,2,2};


    public static  int findOddNum(Integer[] arr){
        int result = 0;// 0 亦或任何数都等于0
        for(int a:arr){
            result = result^ a;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(findOddNum(arr));
    }
}
