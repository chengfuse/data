package com.company.LinkedList;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("======测试=======");
        HeroNodeDouble hero1 =new HeroNodeDouble(1,"宋江","及时雨");
        HeroNodeDouble hero2 =new HeroNodeDouble(2,"卢俊义","玉麒麟");
        HeroNodeDouble hero3 =new HeroNodeDouble(3,"吴用","智多星");
        HeroNodeDouble hero4 =new HeroNodeDouble(4,"林冲","豹子头");
        DoubleLinkedList doubleLinkedList= new DoubleLinkedList();
//        doubleLinkedList.add(hero1);
//        doubleLinkedList.add(hero4);
//        doubleLinkedList.add(hero3);
//        doubleLinkedList.add(hero2);
//        doubleLinkedList.list();
//        System.out.println("修改后===========");
//        HeroNodeDouble hero5 =new HeroNodeDouble(4,"公孙胜","入云龙");
//        doubleLinkedList.updateHero(hero5);
//        doubleLinkedList.list();
//        System.out.println("删除后===========");
//        doubleLinkedList.del(4);
//        doubleLinkedList.list();
        System.out.println("有序添加");
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.list();
    }
}
class    DoubleLinkedList{
    // 先初始化一个头节点，头节点不要动，不存放数据
    private HeroNodeDouble head =new HeroNodeDouble(0,"","");
    public  HeroNodeDouble getHead(){
        return head;
    }
    //无序添加
    public void add(HeroNodeDouble heroNode){
        //因为头节点不能动，因此我们需要一个辅助遍历temp
        HeroNodeDouble temp=head;
        //遍历链表，找到最后
        while(true){
            //找到链表最后
            if(temp.next==null){
                break;
            }
            //如果没有找到将temp后移
            temp=temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //形成一个双向链表
        temp.next=heroNode;
        heroNode.pre=temp;
    }
    //修改英雄
    public void updateHero(HeroNodeDouble newHeroNode){
        //判断是否为空
        if(head.next==null){
            System.out.println("链表为空~~");
            return;
        }
        //找到需要修改的节点的no
        //定义一个变量
        HeroNodeDouble temp= head.next;
        boolean  flag= false ; // 表示是否找到该节点
        while(true){
            if(temp==null){
                System.out.println("已遍历完链表");
                break;
            }
            if(temp.no== newHeroNode.no){
                flag=true; //找到了对应的链表
                break;
            }
            temp=temp.next;
        }
        if(flag){
            temp.name= newHeroNode.name;
            temp.nickname= newHeroNode.nickname;
        }else{
            System.out.printf("当前编号:%d没有存入数据不能修改\n",newHeroNode.no);
        }
    }
    //遍历链表
    public void list(){
        //判断链表是否为空
        if(head.next==null){
            System.out.println("链表为空！");
            return;
        }
        //因为头节点，不能动，因此我们需要一个辅助变量来遍历
        HeroNodeDouble temp=head.next;
        while(true){
            //判断链表是否到最后
            if(temp==null){
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将temp往后移 不然死循环
            temp=temp.next;
        }
    }
    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    //(如果有这个排名，则添加失败，并给出提示)
    public void  addByOrder(HeroNodeDouble heroNode){
        //因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
        //因为单链表，因为我们找到的temp是位于 添加位置的前一个节点，否则插入不了
        HeroNodeDouble temp=head;
        boolean flag=false; //flag标志添加的编号是否存在，默认为false
        boolean flag1=false;
        while(true){
            if(temp.next==null){ //说明链表以到最后
                flag1=true;
                break;
            }
            if(temp.next.no>heroNode.no){//位置找到，就在temp的后面插入
                break;
            }else if(temp.next.no== heroNode.no){ //说明添加的heroNode已存在
                flag=true; //说明编号已存在
                break;
            }
            temp=temp.next; //往后移遍历当前链表
        }
        //判断flag的值
        if(flag){ //不能添加,说明编号已存在
            System.out.printf("准别添加的英雄编号%d已存在\n",heroNode.no);
        }else if(flag1){
           //防止下一个数据比第一次的小
            heroNode.next=temp.next;
            temp.next=heroNode;
            //第一个数据添加需要指定 当前节点连接前一个节点
            heroNode.pre=temp;
            flag1=false;
        } else{
            //no 小于temp的no 时 heroNode下一个节点指向 当前temp 的下一个节点
            heroNode.next=temp.next;
           //当前有序插入temp下一个前节点变成heroNode
            temp.next.pre=heroNode;
           //存入
            temp.next=heroNode;
            heroNode.pre=temp;
        }
    }

    public  void  del(int  no){
        HeroNodeDouble temp =head.next;
        boolean  flag= false;
        while (true){
            if(temp==null){
                break;
            }
            if(temp.no==no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
            //temp的前一个节点的下一个节点变成temp的下一个
            temp.pre.next=temp.next;
            //防止最后一个的下一个为空
            if(temp.next!=null){
                //当前temp的下一个前节点指向temp.的前一个节点
                temp.next.pre=temp.pre;
            }
        }else{
            System.out.println("要删除的节点不存在 ");
        }
    }

}
/**
 * 双项英雄链类
 * */
class HeroNodeDouble{
    public  int no;
    public  String  name;
    public  String   nickname; //昵称
    public  HeroNodeDouble next; // 指向下一个节点
    public  HeroNodeDouble pre; //指向前一个节点
    //构造器
    public HeroNodeDouble(int  no ,String  name ,String  nickname){
        this.no=no;
        this.name =name;
        this.nickname=nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
