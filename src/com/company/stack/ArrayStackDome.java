package com.company.stack;

import java.lang.reflect.Array;
import java.util.Scanner;

public class ArrayStackDome {
    public static void main(String[] args) {
       //完成表达式的运算
        String  expression="7+2-2*5+11-4"; //处理多位数的问题
        //创建两个栈
        ArrayStack  numberStack=new ArrayStack(10);
        ArrayStack  operStack=new ArrayStack(10);
        //定义需要的相关变量
        int index=0;//用于扫描
        int  num1=0;
        int  num2=0;
        int  oper=0;
        int  res=0;
        char ch=' '; //将每次扫描得到char保存到ch
        String keepNum="";//用于拼接多位数
        //开始while循环的扫描expression
        while (true){
            //依次得到expression的每一个字符
            ch=expression.substring(index,index+1).charAt(0);
            //判断ch是什么，然后做相应的处理
            if(operStack.isOper(ch)){ //如果是运算符号
                //判断当前的符号栈是否为空
                if(!operStack.isEmpty()){
                    //如果符号的栈操作付，就进行比较，如果当前的操作付的优先级小于或者等于栈中的操作符就需要从数栈中pop出两个数
                    //从符号栈中pop出一个符号，进行运算将结果，入数栈，然后将当前的操作符入符号栈
                    if(operStack.priority(ch)<=operStack.priority(operStack.peek())){
                        num1=numberStack.pop();
                        num2= numberStack.pop();
                        oper=operStack.pop();
                        //把运算结果入数栈
                        res=numberStack.cal(num1,num2,oper);
                        //然后将当前的操作符入符号栈
                        numberStack.push(res);
                    }else{
                        //如果当前的操作的优先级大于栈中的操作，就直接入符号栈
                        operStack.push(ch);
                    }
                }else{
                    //如果为空直接入符号栈
                    operStack.push(ch);//1+3
                }
            }else{//如果是入就直接入数栈
                //numberStack.push(ch-48); 当前传入的数字是字符串类型的 需要转换 或者查看阿斯克码表进行相减
                //分析思路
                //1.当处理多位数时，不能发现是一个数就立即入栈，因为他可能是一个多位数
                //2.在处理数，需要想expression的表达式的index后再看一位如果是数进行扫描 如果是符号就返回
                //3.因此我们需要定义一个变量字符串，用于拼接
                //处理多位数
                keepNum+=ch;
                //如果ch已经是expressiond 最后一位数就直接入栈
                if (index==expression.length()-1){
                    numberStack.push(Integer.parseInt(keepNum));
                }else{
                    //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入栈
                    //注意是看后一位，不是index++
                    if(operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                        //如果最后一位是运算符，则入栈keepNum=”1“ 或者 ”123“
                        numberStack.push(Integer.parseInt(keepNum));
                        //keepNum一定要清空‘
                        keepNum="";
                    }
                }
            }
            //让index+1，判断是否扫描到expression的最后。
            index++;
            if(index>=expression.length()){
                break;
            }
        }
        //当表达式扫描完毕。就顺序的从，数栈的符号栈中的pop出相对应的数和符号，并运行。
        while (true){
            //如果符号栈为空，则计算到最后的结果，数栈中只有一个数字【结果】
            if(operStack.isEmpty()){
                break;
            }
            num1=numberStack.pop();
            num2=numberStack.pop();
            oper=operStack.pop();
            res=numberStack.cal(num1,num2,oper);
            numberStack.push(res); //入栈
        }
        //将数栈的最后数，pop出，就式结果
        int res2=numberStack.pop();
        System.out.printf("表达式： %s=%d\n",expression,res2);
    }
}
class  ArrayStack{
    private int  top=-1;  // 表示栈顶
    private int[]  data; //数据
    private int maxSize; //栈的大小
    //返回当前栈的顶值，并不取出
    public  int  peek(){
        return data[top];
    }
    public  ArrayStack(int maxSize){
        this.maxSize=maxSize;
        data=new int[this.maxSize];
    }
    //判断已满
    public  boolean  isFull(){
         return top==maxSize-1;
    }
    //判断是否为空
    public boolean  isEmpty(){
        return  top==-1;
    }
    public  void  push(int  value){
        //栈是否已满
        if(isFull()){
            System.out.println("栈满！");
            return;
        }
        top++;
        data[top]=value;
    }
    public  int  pop(){
        //判断栈是否为空
        if(isEmpty()){
            System.out.println("栈空 请填数据");
            return 0;
        }
        int  val=data[top];
        top--;
        return val;
    }
    //遍历栈 从顶端开始 也就是最后添加的数据
    public  void  list(){
        if (isEmpty()){
            System.out.println("栈空 请填数据");
            return ;
        }
        for (int i = top; i >=0 ; i--) {
            System.out.printf("start[%d]=%d\n",i,data[i]);
        }
    }
    //返回运算符的优先级 用数字表示
    //数字越大，则优先级越高
    public  int  priority(int  oper){
        if(oper=='*'||oper=='/'){
            return 1;
        }else if(oper=='+'||oper=='-'){
            return  0;
        }else{
            return -1;  //目前表达式值判断 + - * /
        }
    }
    //判断是不是一个运算符
    public boolean  isOper(int  oper){
        return oper=='*'||oper=='/' ||oper=='+'||oper=='-';
    }
    public  int cal(int  num1,int num2,int oper){
        int  res=0;
        switch(oper){
            case '*':
                res=num1*num2;
                break;
            case '-':
                res=num2-num1;
                break;
            case '/':
                res=num2/num1;
                break;
            case '+':
                res=num1+num2;
                break;
        }
        return  res;
    }
}
