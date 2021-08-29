package com.company.sort;

import java.util.Arrays;

//插入排序
public class InsertSort {
    public static void main(String[] args) {
        int[]  arr={101,58,7,1,34};
        insertSort(arr);
    }
    public  static  void  insertSort(int arr[]){
        int insertVal=0;
        int  insertIndex=0;
        //使用for循环来把代码简化
        for (int i = 1; i < arr.length; i++) {
            //定义待插入的数
            insertVal=arr[i];
            insertIndex=i-1;//即arr[1]的前面这个数的下表
            //说明
            //给insertVal找到插入的位置
            //1.insertIndex>=0 保证在给 insertVal找到插入位置不越界
            //2.insertVal<arr[insertIndex]待插入的数，还没有找到插入位置
            //3.就需要将arr[insertIndex]后移
            while(insertIndex>=0&&insertVal<arr[insertIndex]){
                arr[insertIndex+1]=arr[insertIndex];
                insertIndex--;
            }
            //退出while循环时，说明插入的位置找到，insertIndex+1
            //举例：理解不了，我们一会debug
            //这里我们判断是否需要赋值
            if(insertIndex+1!=i){
                arr[insertIndex+1]=insertVal;
            }
            System.out.println("第"+i+"轮后");
            System.out.println(Arrays.toString(arr));
        }
    }
}
