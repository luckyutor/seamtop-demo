package com.seamtop.demo.designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by feng on 2015/8/8.
 */
public class ProxySubject implements InvocationHandler {
    private Subject obj;
    public ProxySubject(){}
    public ProxySubject(Subject obj){
        this.obj = obj;
    }

    public Object invoke(Object proxy,Method method,Object[] args) throws Exception{
        System.out.println("预处理工作");
        method.invoke(obj, args);
        System.out.println("后续工作");
        return null;
    }
}
