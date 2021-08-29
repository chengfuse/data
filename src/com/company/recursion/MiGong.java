package com.company.recursion;

import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;

public class MiGong {
    public static void main(String[] args) {
        //先创建一个二维数组，模拟迷宫
        //地图
        int[][]  map=new int[8][7];
        //使用1表示墙
        //上下全部署为1
        for (int i = 0; i < 7; i++) {
            map[0][i]=1;
            map[7][i]=1;
        }
        //左右全部署为1
        for (int i = 0; i < 8; i++) {
            map[i][0]=1;
            map[i][6]=1;
        }
        //设置挡板，1表示
        map[3][1]=1;
        map[3][2]=1;
        //输出地图
        System.out.println("地图情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        //使用递归回溯给小球找路
        //setWay(map,1,1);
        setWay2(map,1,1);
        //输出地图，小球走过，并标志过的地图，状况
        System.out.println("小球走过，并标识过的，地图情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        int  num=0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                if(map[i][j]==2){
                   num++;
                }
            }
        }
        System.out.println("小球走了"+(num-1)+"步");
    }
    //使用递归回溯来个小球找路
    //说明
    //1.map 表示地图
    //2.i,j 表示地图的那个位置开始出发（1，1）
    //3.如果小球能到map[6][5]位置，则说明通路找到。
    //4.约定； 当map[i][j] 为0 表示该点没有走过当1表示墙；2表示通路可走 3表示已经走过，但是走不通
    //5.在走迷宫时，需要确定一个策略(方法) 下->友->上->左，如果该点走不通，再回溯
    /**
     * @param map 表示地图
     * @param i  从那个位置开始
     * @param j
     * @return  如果找到通路，就返回true，否则返回false
     * */
    public  static  boolean  setWay(int[][] map, int i,int j){
        if(map[6][5]==2){ //通路已找到
            return true;
        }else{
            if(map[i][j]==0){ //如果当前这个点没有走过
                //按照测略 下->右->上->左 走
                map[i][j]=2; //假定该店是可以走通
                if(setWay(map,i+1,j)){ //向下走
                    return true;
                }else if(setWay(map,i,j+1)){//向右走
                    return true;
                }else if(setWay(map,i-1,j)){//向上
                    return true;
                }else if(setWay(map,i,j-1)){//向左
                    return true;
                }else{
                    //说明该点是走不通，是死路
                    map[i][j]=3;
                    return false;
                }
            }else{ //如果map[i][j]!=0，可能是1，2，3
                return   false;
            }
        }
    }
    //修改找路策略上 ->右->下->左
    public  static  boolean  setWay2(int[][] map, int i,int j){
        if(map[6][5]==2){ //通路已找到
            return true;
        }else{
            if(map[i][j]==0){ //如果当前这个点没有走过
                //按照测略 上->右->下->左 走
                map[i][j]=2; //假定该店是可以走通
                if(setWay2(map,i-1,j)){ //向上走
                    return true;
                }else if(setWay2(map,i,j+1)){//向右走
                    return true;
                }else if(setWay2(map,i+1,j)){//向下
                    return true;
                }else if(setWay2(map,i,j-1)){//向左
                    return true;
                }else{
                    //说明该点是走不通，是死路
                    map[i][j]=3;
                    return false;
                }
            }else{ //如果map[i][j]!=0，可能是1，2，3
                return   false;
            }
        }
    }
}
