package com.seamtop.demo.designpattern.abstractfactory;

/**
 * Created by feng on 2015/8/7.
 */
public class TestMain {
    public static void main(String [] args){
        ICreator creator1 = new Creator1();
        creator1.createProductA();
        ICreator creator2 = new Creator2();
        creator2.createProductB();
    }
}
