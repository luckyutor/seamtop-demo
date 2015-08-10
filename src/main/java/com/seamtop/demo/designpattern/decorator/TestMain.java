package com.seamtop.demo.designpattern.decorator;

/**
 * Created by feng on 2015/8/8.
 */
public class TestMain {
    public static void main(String [] args){
        ConcreteComponent component = new ConcreteComponent();
        ConcreteDecoratorA decoratorAcomponent = new ConcreteDecoratorA(component);
        ConcreteDecoratorB decoratorBcomponent = new ConcreteDecoratorB(decoratorAcomponent);
        decoratorBcomponent.operation();
    }
}
