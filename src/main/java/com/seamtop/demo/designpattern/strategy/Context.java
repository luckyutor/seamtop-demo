package com.seamtop.demo.designpattern.strategy;

/**
 * Created by feng on 2015/8/8.
 */
//上下文对象
public class Context {
    Strategy strategy;
    public Context(Strategy strategy){
        this.strategy = strategy;
    }
    public void contextInterface(){
        strategy.AlogrithmInterface();
    }
}
