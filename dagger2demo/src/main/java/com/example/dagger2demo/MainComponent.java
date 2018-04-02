package com.example.dagger2demo;

import dagger.Component;

/**
 * 沟通部分Component
 * 作为桥梁，沟通调用者和依赖对象库
 * @author liuhe
 */
@Component(modules = MainModule.class)
public interface MainComponent {

    /**
     * 定义注入的方法
     *
     * @param activity
     */
    void inject(MainActivity activity);
}
