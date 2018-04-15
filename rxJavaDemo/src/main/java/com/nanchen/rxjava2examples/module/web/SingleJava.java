package com.nanchen.rxjava2examples.module.web;

/**
 * Created by liuhe on 12/03/2018.
 */

public class SingleJava {
    private static class Holder {
        private static final SingleJava instance = new SingleJava();
    }

    private SingleJava() {
    }

    public SingleJava getInstance() {
        return Holder.instance;
    }
}
