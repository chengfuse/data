package com.company.search;

public class BinarySearchDome {
    public static void main(String[] args) {
        // write your code here
        int[]  number  ={1,2,3,4,5,6,7,8,9};
        int  item=9;
        int  i=TwoSearch(number,item);
        System.out.println(number[i]);
    }
    public static   int  TwoSearch(int[]  number,int item){
        int  low =0; //第一个位置
        int  high=number.length-1; //最后一个位置
        int  count =0;
        while (low<=high){
            int  mid=(low+high)/2; //找到中间的位置
            int  guess=number[mid];
            count++;
            if (guess == item) {
                System.out.println("循环"+count+"找到了");
                return  mid;
            }
            if (guess>item){ //数组大于item
                high=mid-1;  //去除数组右边的内容
            }else{        //数组小于item
                low=mid+1;  //去除数组左边的内容
            }
        }
        return  0;
    }
}
