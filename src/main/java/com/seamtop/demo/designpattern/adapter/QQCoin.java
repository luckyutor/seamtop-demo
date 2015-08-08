package com.seamtop.demo.designpattern.adapter;

/**
 * Created by feng on 2015/8/8.
 */
public class QQCoin implements Target{

    /**
     * 人民币实体对象
     */
    public Rmb rmbImpl;

    /**
     * QQ币数量
     */
    public int count;

    /**
     * 构造方法
     * @param rmb
     */
    public QQCoin(Rmb rmb){
        this.count = rmb.getMoney() * 10;
    }

    /**
     * 具体实现方法
     */
    @Override
    public void buyGameProps() {
        System.out.println("您购买了 " + count + " 个道具！");
    }

}
