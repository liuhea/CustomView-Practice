package com.liuhe.aiqiyidemo;

/**
 * 单例测试类
 *
 * @author liuhe
 * @date 2018-05-25
 */
public class SingleUtils {

    private SingleUtils() {
    }

    static class Holder {
        static final SingleUtils instance = new SingleUtils();
    }

    public static SingleUtils getInstance() {
        return Holder.instance;
    }

    public String varStr;


}
