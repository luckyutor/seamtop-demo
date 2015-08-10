package com.seamtop.demo.designpattern.decorator;

/**
 * Created by feng on 2015/8/8.
 */
public class ConcreteDecoratorA extends  Decorator{

    public ConcreteDecoratorA(Component component){
        super(component);
    }

    public void operation(){
        super.operation();
    }
}
