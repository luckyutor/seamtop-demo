package com.seamtop.demo.designpattern.adapter;

/**
 * Created by feng on 2015/8/8.
 */
public class TestMain {
        public static void main(String [] args){
            Rmb rmb = new Rmb(5);
            QQCoin coin = new QQCoin(rmb);
            coin.buyGameProps();
        }
}
