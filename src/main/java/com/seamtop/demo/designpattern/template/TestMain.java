package com.seamtop.demo.designpattern.template;

/**
 * Created by feng on 2015/8/8.
 */
public class TestMain {
    public static void main(String [] args){
        AbstractClass class1 = new ConcreteClass1();
        class1.operate3();
        AbstractClass class2 = new ConcreteClass2();
        class2.operate3();
    }
}
