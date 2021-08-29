package com.company.LinkedList;

public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList  list=new CircleSingleLinkedList();
        list.addBoy(5);
        list.showBoy();
        list.countBoy(3,2,5);
    }
}
//创建一个单项环形链表
class
CircleSingleLinkedList{
    //创建一个first 节点，当前没有编号
    private  Boy first=null;
    //添加小孩节点，构成一个环形链表
    public  void  addBoy(int  nums){
        if (nums < 1) { //校验
            System.out.println("num 值不正确！");
            return;
        }
        Boy curBoy=null; //辅助指针,帮助构建环形链表
        //使用for来创建我们的环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号创建小孩接点
            Boy boy=new Boy(i);
            //如果是第一个小孩
            if(i==1){
                first=boy; //第一指向boy
                first.setNext(first); //构成环
                curBoy=first; //让curBoy指向第一个小孩
            }else{
                curBoy.setNext(boy); //curBoy的前一个小孩的下一个为boy
                boy.setNext(first); //boy的最后的下一个变成first形成一个环
                curBoy=boy;    //将当前的boy给curBoy以便于下次存入
            }
        }
    }
    //遍历当前的环形链表
    public  void  showBoy(){
        //判断链表是否为空
        if(first==null){
            System.out.println("没有任何小孩~~~");
            return;
        }
        //因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy curBoy=first;
        while (true){
            System.out.printf("小孩的编号%d \n",curBoy.getNo());
            if(curBoy.getNext()==first){ //说明已经遍历完了
                break;
            }
            curBoy=curBoy.getNext();//后移
        }
    }
    /**
     * @param  startNo 表示第几个开始
     * @param   countNum 数几下
     * @param  nums  有多少小孩
     * */
    public void countBoy(int  startNo,int countNum,int nums){
        //校验
        if(first==null||startNo<1||startNo>nums){
            System.out.println("参数有误，请重新输入");
            return;
        }
        //创建要给辅助指针，帮助完成小孩出圈
        Boy helper=first;
        //需求创建一个辅助指针（变量）helper，实现指定这个环形链表的最后一个节点
        while (true){
            if (helper.getNext()==first){ //说明helper指向最后一个节点
                break;
            }
            helper=helper.getNext();
        }
        //小孩报数前让first 和helper移动到k-1 次 简称从第几个开始
        for (int i = 0; i < startNo-1; i++) {
            first=first.getNext();
            helper=helper.getNext();
        }
        //当小孩报数时，让first和helper指针同时的移动m-1次，然后出圈
        //这里是一个循环操作，知道圈中只有一个节点
        while (true) {
            if (helper==first){ //圈中只有一个节点了
                break;
            }
            //让first和helper指针同时的移动countNum-1  简称数到的位置
            for (int i = 0; i < countNum-1; i++) {
                first=first.getNext();
                helper=helper.getNext();
            }
            //这时first指针的节点，就要出圈的小孩节点
            System.out.printf("小孩%d出圈\n",first.getNo());
            first=first.getNext(); //当前位置的下一个 也就出圈人后面一个人开始数
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d\n",first.getNo());
    }
}

//定义一个boy类
class Boy{
    private  int no; //编号

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    private Boy next; //指向下一个节点，默认null

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
    public  Boy(int no){
        this.no=no;
    }
}
