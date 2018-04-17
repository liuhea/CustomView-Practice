package com.example.grendaodemo

import android.app.Application
import com.example.grendaodemo.dao.DaoMaster
import com.example.grendaodemo.dao.DaoSession

/**
 * Created by liuhe on 19/03/2018.
 */
class App : Application() {

    companion object {
        lateinit var daoSession: DaoSession
    }

    override fun onCreate() {
        super.onCreate()

        initGreenDao()

// do this once, for example in your Application class
//        helper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
//        db = helper.getWritableDatabase();
//        daoMaster = new DaoMaster(db);
//        daoSession = daoMaster.newSession();
//// do this in your activities/fragments to get hold of a DAO
//        noteDao = daoSession.getNoteDao();
    }

    private fun initGreenDao() {
        val devOpenHelper = DaoMaster.DevOpenHelper(this, "note.db")
        val db = devOpenHelper.writableDatabase
        val daoMaster = DaoMaster(db)
        daoSession = daoMaster.newSession()
    }
}