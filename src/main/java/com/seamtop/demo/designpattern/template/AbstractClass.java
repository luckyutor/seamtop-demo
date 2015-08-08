package com.seamtop.demo.designpattern.template;

/**
 * Created by feng on 2015/8/8.
 */
public abstract class AbstractClass {
    public abstract void opeate1();
    public abstract void operate2();
    public void operate3(){
        opeate1();
        operate2();
    }
}
