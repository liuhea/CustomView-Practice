package com.example.dagger2demo;

import dagger.Module;
import dagger.Provides;

/**
 * 提供依赖对象的实例(容器)
 * @author liuhe
 */
@Module
public class MainModule {
    /**
     * 标明该方法提供依赖的对象
     * 比如：提供Person对象
     */
    @Provides
    Person providePerson() {
        return new Person();
    }
}
