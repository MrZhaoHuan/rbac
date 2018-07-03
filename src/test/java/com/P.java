package com;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-06-16 17:51
 * @描述
 */
public abstract class P {

    public P(){
        t1();
        t2();
    }
    private void t1(){
        System.out.println("t1答案是:"+a1());
    }
    protected abstract String a1();

    private void t2(){
        System.out.println("t2答案是:"+ a2());
    }
    protected abstract String a2();
}
