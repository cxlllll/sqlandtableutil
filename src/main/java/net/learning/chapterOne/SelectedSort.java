package net.learning.chapterOne;

import net.learning.utils.TestSamPle;

import java.util.Arrays;

/**
 * 选择排序：每次都交换，知道交换到固定位置
 */
public class SelectedSort {
    public static void selectedSort(Integer[] arr) {
        if(null == arr || arr.length<2){
            return;
        }
        // 从0开始
        for(int i =arr.length-1;i>=0;i--){
            for(int j = 0;j<i;j++){
                if(arr[j]>arr[j+1]){
                    TestSamPle.swap(arr,j,j+1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr3 = TestSamPle.arr3;
        selectedSort(arr3);
        System.out.println(Arrays.asList(arr3));
    }
}
