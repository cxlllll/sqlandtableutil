package net.learning.chapterOne;

import net.learning.utils.TestSamPle;

import java.util.Arrays;

/**
 * 插入排序 像打牌一样，拿到一张牌，找到他合适的位置以后就不需要再动其他的了，然后重新拿出一张牌继续以上操作
 */
public class InsertSort {

    public static  void insertSort(Integer[] arr){
        if(null ==arr || arr.length<2){
            return;
        }
        for(int i=1;i< arr.length;i++){
            //关键是这个for的第二个比较  arr[j+1] >arr[j]，这个条件决定了如果符合条件了就不需要继续执行代码进行比较了
            for(int j = i-1;j>=0 && arr[j+1] <arr[j];j--){
                TestSamPle.swap(arr,j,j+1);
            }
        }
    }



    public static void main(String[] args) {
        Integer[] arr3= {11,2,8,3,10,5,7};
        insertSort(arr3);
        System.out.println(Arrays.asList(arr3));
    }
}
