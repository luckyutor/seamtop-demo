package com.seamtop.demo.designpattern.abstractfactory;

/**
 * Created by feng on 2015/8/7.
 */
public class Creator1 implements ICreator {

    public IProduct createProductA(){
        return new ProductA();
    }

    public IProduct createProductB(){
        return new ProductB();
    }
}
