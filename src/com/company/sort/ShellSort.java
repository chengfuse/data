package com.company.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
//希尔排序
public class ShellSort {
    public static void main(String[] args) {
        int[]  arr={8,9,1,7,2,3,5,4,6,0};
//        int[] arr=new int[80000];
//        for (int i = 0; i < 80000; i++) {
//            arr[i]=(int)(Math.random()+80000);
//        }
//        Date date=new Date();
//        SimpleDateFormat  format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String  dataStr=format.format(date);
//        System.out.println(dataStr);
        shellSort2(arr);
//        Date date2=new Date();
//        dataStr=format.format(date);
//        System.out.println(dataStr);
    }
    //交换方式
    public  static void shellSort(int[]  arr){
        int  temp=0; //帮助替换
        int count=0;
        //根据前面的逐步分析。使用循环处理
        for (int gap = arr.length/2;gap >0; gap/=2) {
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中所有的元素(共gap组，每组有个元素)，步长gap
                for (int j = i-gap; j >=0; j-=gap) {
                    //如果当前元素大于加上部后的那个元素说明交换
                    if(arr[j]>arr[j+gap]){
                        temp=arr[j];
                        arr[j]=arr[j+gap];
                        arr[j+gap]=temp;
                    }
                }
            }
            System.out.println("希尔排序第"+(++count)+"论="+ Arrays.toString(arr));
        }
    }
    //移位方式
    public  static void shellSort2(int[]  arr){
        int  temp=0; //帮助替换
        int count=0;
        //增量gap,并逐步的缩小增量
        for (int gap = arr.length/2;gap >0; gap/=2) {
            //从第gap个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int  j=i;
                temp=arr[j];
                if(arr[j]<arr[j-gap]){
                    while (j-gap>=0&& temp<arr[j-gap]){
                        //移动
                        arr[j]=arr[j-gap];
                        j-=gap;
                    }
                    //当退出while后，就给temp找到插入的位置
                    arr[j]=temp;
                }
            }
        }
    }
}
