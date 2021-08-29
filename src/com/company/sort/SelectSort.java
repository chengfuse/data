package com.company.sort;

import java.util.Arrays;
//选择排序
public class SelectSort{
    public static void main(String[] args) {
        int[]  arr={101,58,7,1,34};
        selectSort(arr);
    }
    public  static  void  selectSort(int  arr[]){
        for (int i = 0; i < arr.length-1; i++) {
            int  minIndex=i;  //从0开始默认最小值
            int  min=arr[i];  //初始化最小值   并不是最小
            for (int j = i+1; j < arr.length; j++) {
                if(min > arr[j]){ //说明假定的最小值，并不是最小
                    min=arr[j]; //重置min
                    minIndex=j; //重置 minIndex
                }
            }
            //将最小值，放在arr[0]，即交换
            if(minIndex!=i){
                arr[minIndex]=arr[i];
                arr[i]=min;
            }
            System.out.println("第"+(i+1)+"轮后");
            System.out.println(Arrays.toString(arr));
        }
    }
}
