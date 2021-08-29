package com.company.LinkedList;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 =new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 =new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 =new HeroNode(3,"吴用","智多星");
        HeroNode hero4 =new HeroNode(4,"林冲","豹子头");
        SingleLinkedList list =new SingleLinkedList();
        //无序添加
//        list.add(hero1);
//        list.add(hero3);
//        list.add(hero4);
//        list.add(hero2);
//        list.list();
        //有序添加
        System.out.println("修改前");
        list.addByOrder(hero1);
        list.addByOrder(hero4);
        list.addByOrder(hero3);
        list.addByOrder(hero2);
//        list.list();
        list.reversetList(list.getHead());
        list.list();
//        System.out.println("当前HeroNode长度=="+list.getLength(list.getHead()));
//        HeroNode find=list.findLastIndexNode(list.getHead(),1);
//        System.out.println(find.toString());
//        System.out.println("修改后");
//        HeroNode newHeroNode =new HeroNode(4,"林冲","小猫咪");
//        list.updateHero(newHeroNode);
//        list.list();
//        System.out.println("删除");
//        list.del(1);
//        list.del(4);
//        list.list();
    }
}
/**
 * 定义 SingleLinkedList管理英雄类
 * */
class SingleLinkedList{
   // 先初始化一个头节点，头节点不要动，不存放数据
    private HeroNode head =new HeroNode(0,"","");
    public  HeroNode getHead(){
        return head;
    }

    //添加节点到单项链表
    //思路，当不考虑编号顺数
    //1.找到当前链表的最后一个节点
    //2.将最后这个节点的next指向新的节点
    public void add(HeroNode heroNode){
        //因为头节点不能动，因此我们需要一个辅助遍历temp
        HeroNode temp=head;
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
        //将最后这个节点的next指向新的节点
        temp.next=heroNode;
    }
    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    //(如果有这个排名，则添加失败，并给出提示)
    public void  addByOrder(HeroNode heroNode){
        //因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
        //因为单链表，因为我们找到的temp是位于 添加位置的前一个节点，否则插入不了
        HeroNode temp=head;
        boolean flag=false; //flag标志添加的编号是否存在，默认为false
        while(true){
            if(temp.next==null){ //说明链表以到最后
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
        }else{
            //插入到链表中，temp的后面
            heroNode.next=temp.next;
            temp.next=heroNode;
        }
    }

    //修改英雄
    public void updateHero(HeroNode newHeroNode){
        //判断是否为空
        if(head.next==null){
            System.out.println("链表为空~~");
            return;
        }
        //找到需要修改的节点的no
        //定义一个变量
        HeroNode temp= head.next;
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
    public  void  del(int  no){
        HeroNode temp =head;
        boolean  flag= false;
        while (true){
            if(temp.next==null){
                break;
            }
            if(temp.next.no==no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
            temp.next=temp.next.next;
        }else{
            System.out.println("要删除的节点不存在 ");
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
        HeroNode temp=head.next;
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
    /**
     * @param head 链表的头节点
     * @return 返回有效节点的个数
     * */
    public  static  int  getLength(HeroNode  head){
        if(head.next==null){
            return 0;
        }
        int  length=0;
        //定义辅助变量不包括头节点头节点
        HeroNode cur =head.next;
        while (cur!=null){
            length++; //遍历
            cur=cur.next;
        }
        return length;
    }
    //查找单链表倒数弟k个节点
    /**
     * 思路
     * 1.编写一个方法，接受head节点，同时接受一个index
     * 2.index表示是倒数地index个节点
     * 3.先把链表从头到位遍历，得到链表的总长度 getLength
     * 4.得到size后，我们从链表的第一个开始遍历(size-index)个，就可以得到
     * 5.如果找到，则返回该节点，否则返回null
     * */
     public  static  HeroNode findLastIndexNode(HeroNode head,int index){
         //判断如果链表为空，返回null
         if(head.next==null){
            return null; //没有找到
         }
         //第一个遍历得到链表的长度（节点个数）
         int size=getLength(head);
         //第二遍 size-inde位置，就是我们倒数的第k个节点
         //先做一个index的校验
         if(index<=0||index>size){
             return null;
         }
         //定义给辅助变量，for循环定位到倒数的index
         HeroNode cur=head.next;
         for (int i = 0; i < size-index; i++) {
             cur=cur.next;
         }
         return cur;
     }
     /**
      * 单项链表实现反转
      * 思路：
      * 1.定义一个节点 reverseHead=new  HeroNode();
      * 2.从头到位遍历原来的链表，每遍历一个节点就将取出，并放在新的链表reversHead的最前端
      * 3.原来的链表head.next=reversHead.next
      * */
     public  static void  reversetList(HeroNode head){
         //如果当前节点为空 或只有一个无需反转直接返回
         if(head.next==null||head.next.next==null){
             return;
         }
         //定一个辅助的指针（变量），帮助我们遍历原来的链表
         HeroNode cur =head.next;
         HeroNode next=null; //指向当前的cur的下一个节点存储
         HeroNode reversHead=new HeroNode(0,"","");
         //遍历原来的链表，每遍历一个节点，就将取出，并放在新的链表reversHead的最前端
         while(cur!=null){
             next=cur.next; //暂时保存当前的下一个节点
             cur.next=reversHead.next; //将cur的下一个节点指向新的链表的最前端
             reversHead.next=cur;//将cur连接到新的链表上
             cur=next; //让cur往后移
         }
         //将head.next 指向reverseHead.next,实现单链表的反转
         head.next=reversHead.next;
     }
}
/**
 * 英雄链类
 * */
class HeroNode{
    public  int no;
    public  String  name;
    public  String   nickname; //昵称
    public  HeroNode next; // 指向下一个节点
    //构造器
     public HeroNode(int  no ,String  name ,String  nickname){
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
