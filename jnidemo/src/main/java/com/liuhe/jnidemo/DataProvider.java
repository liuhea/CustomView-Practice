package com.liuhe.jnidemo;

/**
 * @author liuhe
 * @date 2018-04-06
 */
public class DataProvider {

    /**
     * @param name
     * @param password
     * @return
     */
    public native int login(int name, int password);
}
