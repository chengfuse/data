package com.company.LinkedList;

import java.util.Stack;
//演示栈的操作
public class TestStack {
    public static void main(String[] args) {
        Stack<HeroNode> heroNodeStack =new Stack<HeroNode>();
        //入栈
        HeroNode hero1 =new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 =new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 =new HeroNode(3,"吴用","智多星");
        HeroNode hero4 =new HeroNode(4,"林冲","豹子头");
        heroNodeStack.add(hero4);
        heroNodeStack.add(hero3);
        heroNodeStack.add(hero2);
        heroNodeStack.add(hero1);
        while(heroNodeStack.size()>0){
            System.out.println(heroNodeStack.pop());
        }
    }
}
