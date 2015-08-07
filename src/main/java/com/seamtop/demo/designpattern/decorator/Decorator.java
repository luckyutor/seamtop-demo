package com.seamtop.demo.designpattern.decorator;

/**
 * Created by feng on 2015/8/8.
 */
public class Decorator implements Component {

    private Component component;

    public Decorator(Component component){
        this.component = component;
    }

    public void operation(){
        if(component != null){
            component.operation();
        }
    }
}
