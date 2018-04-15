package com.example.grendaodemo.bean;

import com.example.grendaodemo.dao.DaoUserDao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by liuhe on 19/03/2018.
 */
@Entity
public class DaoUser2 {
    public String name;

    @Generated(hash = 1008707987)
    public DaoUser2(String name) {
        this.name = name;
    }

    @Generated(hash = 717602967)
    public DaoUser2() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
