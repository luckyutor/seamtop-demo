package com.seamtop.demo.designpattern.simplefactory;

/**
 * Created by feng on 2015/8/7.
 */
public class ProductFactory {

    public static void main(String [] args){
        IProduct product = null;
        char str = 'B';
        switch (str){
            case 'A':
                product = new ProductA();
                break;
            case 'B':
                product = new ProductB();
                break;
            default:
                break;
        }
        if(product != null){
            product.say();
        }
    }
}
