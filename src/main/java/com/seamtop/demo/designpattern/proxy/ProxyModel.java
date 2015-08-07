package com.seamtop.demo.designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by feng on 2015/8/8.
 */
public class ProxyModel {
    public static void main(String [] args){
        RealSubject realSubject = new RealSubject();
        Class<?>cla = realSubject.getClass();
        InvocationHandler handler = new ProxySubject(realSubject);
        Subject subject = (Subject) Proxy.newProxyInstance(cla.getClassLoader(),cla.getInterfaces(),handler);
        subject.request();
    }
}
