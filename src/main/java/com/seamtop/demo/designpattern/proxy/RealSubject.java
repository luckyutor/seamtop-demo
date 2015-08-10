package com.seamtop.demo.designpattern.proxy;

/**
 * Created by feng on 2015/8/8.
 */
public class RealSubject implements Subject {
    public RealSubject(){}
    public void request(){
        System.out.println("real subject");
    }
}
