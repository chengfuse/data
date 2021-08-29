package com.company.sparsearray;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class Main {
    private static final String FILEPATH="D:\\稀疏数组.txt";
    public static void main(String[] args) throws  Exception{
        File f =new File(FILEPATH);
        if(!f.exists()){
            f.createNewFile();
        }
	   //创建原始的二维数组 11*11
       // 0: 没有棋子 1:黑棋 2:白棋
       int chessArr1 [][] =new int[11][11];
        chessArr1[1][2]=1;
        chessArr1[2][3]=2;
        chessArr1[2][4]=1;
	   //输出原始的二维数组
        for (int[] row:chessArr1) {
            for (int data:row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        //二维数组转稀疏数组
        //先遍历二维数组 得到非0的值
        int sum=0;
        for(int i=0;i<chessArr1.length;i++){
            for (int j=0;j<chessArr1.length;j++){
                if(chessArr1[i][j]!=0){
                    sum++;
                }
            }
        }
        //创建对应的稀疏数组
        int sparseArr[][] =new  int[sum+1][3];
        //添加值给稀疏数组
        System.out.println(chessArr1[0].length);
        sparseArr[0][0]=11;
        sparseArr[0][1]=11;
        sparseArr[0][2]=sum;
        //遍历二维数组，将非0的值存到sparesArr中
        int count =0; //count用于记录第几个非0的数据
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1.length; j++) {
                if(chessArr1[i][j]!=0){
                    count++;
                    sparseArr[count][0]=i;
                    sparseArr[count][1]=j;
                    sparseArr[count][2]=chessArr1[i][j];
                }
            }
        }
        //输出稀疏数组 的形式
        String  value=sparseArr[0][0]+"\t"+sparseArr[0][1]+"\t"+sparseArr[0][2]+"\t";
        System.out.println("得到的稀疏数组~~~~~~");
        System.out.println("row\tcol\tvalue"); //row：竖行 col：横行 value:值
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
            if(i+1<sparseArr.length){
                value+=sparseArr[i+1][0]+"\t"+sparseArr[i+1][1]+"\t"+sparseArr[i+1][2]+"\t";
            }
        }
        //将稀疏数组存入文文件
        OutputStream os =new FileOutputStream(f);
        os.write(value.getBytes(StandardCharsets.UTF_8));
        //从文件取出稀疏数组
        InputStream is =new FileInputStream(f);
        int len =0;
        String  s=null;
        byte[] bytes=new byte[is.available()];
        while ((len=is.read(bytes))!=-1){
             s =new String(bytes,0,len);
        }
        String[] sp=s.split("\t");
        int[] numbers=new  int[sp.length];
        for (int i = 0; i <sp.length ; i++) {
            numbers[i]=Integer.parseInt(sp[i]);
        }
        os.close();
        is.close();
        //从文件中找到值在返回个稀疏数组
        System.out.println("文件得到稀疏数组转换！");
        int sparseArr2[][] =new  int[numbers[2]+1][3];
        int c=0;
        for (int i = 0; i < sparseArr2.length; i++) {
            sparseArr2[i][0]=numbers[c];
            c++;
            sparseArr2[i][1]=numbers[c];
            c++;
            sparseArr2[i][2]=numbers[c];
            c++;
        }
        for (int i = 0; i < sparseArr2.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparseArr2[i][0],sparseArr2[i][1],sparseArr2[i][2]);
        }
        //将稀疏数组回复成二维数据
        int chessArr2 [][] =new int[sparseArr2[0][0]][sparseArr2[0][1]];
        for (int i = 1; i < sparseArr2.length; i++) {
            chessArr2[sparseArr2[i][0]][sparseArr2[i][1]]=sparseArr2[i][2];
        }
        //二维数组输出
        System.out.println("稀疏在转回二维数组~~~~~~");
        for (int row[]:chessArr2) {
            for (int data:row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
}
