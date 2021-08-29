package com.company.queue;

import java.util.Scanner;

public class ArrayQueueDome {
    public  static  void  main (String[] args){
        //测试
        ArrayQueue queue=new ArrayQueue(3);
        char key=' ';
        Scanner input =new Scanner(System.in);
        boolean loop=true;
        while(loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列的头数据");
            key=input.next().charAt(0);//接受一个字符
            switch (key){
                case 's':
                    queue.showQueue();;
                    break;
                case 'e':
                    input.close();
                    loop=false;
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value= input.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try{
                        int  res=queue.getQueue();
                        System.out.printf("取出数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try{
                        int  res=queue.headQueue();
                        System.out.printf("取出列队的头的数据%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }
        System.out.println("程序已退出");
    }
}
//使用数组模拟队列-编写一个ArrayQueue类
class ArrayQueue{
    private int maxSize; //包是数组的最大容量
    private int  front; //队列头
    private int  rear; //队列尾
    private int[] arr; //该数据用于存放数据，模拟队列
    //创建队列的构造器
    public ArrayQueue(int  arrMaxSize){
        maxSize=arrMaxSize;
        arr=new int[maxSize];
         //指向队列尾部，指向队列尾的数据(即就是队列最后一个数据)
    }
    //判断队列是否满
    public boolean isFull(){
        return (rear+1)%maxSize==front;
    }
    //判断队列是否为空
    public boolean isEmpty(){
        return rear==front;
    }
    //添加数据到队列
    public void  addQueue(int n){
        if(isFull()){ //判断队列是否已满
            System.out.println("队列满，不能添加数据！");
            return;
        }
        //rear++; //rear往后移 :普通队列
        arr[rear]=n;
        //将rear后移，这里必须考虑取模
        rear=(rear+1)%maxSize;
    }
    //获得队列的数据，出队列
    public int getQueue(){
        if(isEmpty()){ //判断队列是否已空
            throw new RuntimeException("队列已空不能取出数据"); //抛出异常
        }
        //front++;//后移 :普通队列
        int value=arr[front];
        front=(front+1)%maxSize;
        return value;
    }
    //显示所有数据
    public  void showQueue(){
        if (isEmpty()){
            System.out.println("对列空的，没有数据~~~");
            return;
        }
        for (int i = front; i < front+Size(); i++) {
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
        }
    }
    //求出当前队列有效数据的个数
    public  int Size(){
        return (rear+maxSize-front)%maxSize;
    }
    //显示队列的头数据，不是取数据
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列已空,没有数据！");
        }
        return arr[front+1];
    }
}

