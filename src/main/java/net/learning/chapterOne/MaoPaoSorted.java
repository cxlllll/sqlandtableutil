package net.learning.chapterOne;

import net.learning.utils.TestSamPle;

import java.util.Arrays;

/**
 * 冒泡排序  每次只冒一个 遍历完以后才交换
 */
public class MaoPaoSorted {
    public static  void maoPaoSort(Integer[] arr){
        if(null == arr || arr.length<2){
            return;
        }
        for(int i =arr.length-1;i>0;i--){
            int index = 0;
            for(int j =0;j<i;j++){
                if(arr[index]<arr[j+1]){
                    index = j+1;
                }
            }
            if(index != i){
                TestSamPle.swap(arr,index,i);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr3 = TestSamPle.arr3;
        maoPaoSort(arr3);
        System.out.println(Arrays.asList(arr3));
    }
}
