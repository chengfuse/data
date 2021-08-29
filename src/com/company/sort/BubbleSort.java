package com.company.sort;

 

import java.util.Arrays;
//冒泡排序
public class BubbleSort {
    public static void main(String[] args) {
        int[]  nums={-1,6,4,-2,9};
        int  temp=0;
        boolean flag=false; //辅助指针帮助减少替换
        for (int i = 0; i < nums.length-1; i++) {
            for (int j = 0; j < nums.length-1-i; j++) {
                if(nums[j]>nums[j+1]){
                    flag=true;
                    temp=nums[j];
                    nums[j]=nums[j+1];
                    nums[j+1]=temp;
                }
            }
            if(!flag){
                break;
            }else{
                flag=false;
            }
            System.out.println("第"+(i+1)+"次循环得到的");
            System.out.println(Arrays.toString(nums));
        }
    }
}
