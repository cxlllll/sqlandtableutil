package net.learning.chapterOne;

import net.learning.utils.TestSamPle;

/**
 * 整型数组，一共两种数，一种数出现奇数次，奇数次的数有俩个，一种是出现偶数次，不管多少个，请找出出现奇数次的两种数
 */
public class FindTwoOddNum {

    public static  int[] findTwoOddNum(Integer[] arr){
        //1.首先将所有数进行有^运算 ，最终结果是得到 出现奇数次的两个数的亦或结果resultTwoOdd
        int resultTwoOdd =0;
        for(int a:arr){
            resultTwoOdd ^= a;
        }
        //2.因为两个数是不一样的，所以resultTwoOdd不可能是0，所以resultTwoOdd二进制中的1的位置都是两个数的二进制此位置上不为1的地方
            int findRightOnePosition = resultTwoOdd&(~resultTwoOdd+1);
        //3.找到resultTwoOdd最右边的1 以为将数分为2类
        int odd1= 0;
        int odd2=0;
        for(int a:arr){
            if((findRightOnePosition&a) ==1){
                odd1 ^=a ;
            }else{
                odd2 ^=a;
            }
        }
        System.out.println(odd1+"   "+odd2);
    return new int[]{odd2, odd2};
    }


    public static void main(String[] args) {
        findTwoOddNum(TestSamPle.arr2);
    }
}
