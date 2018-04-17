package com.example.grendaodemo

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.grendaodemo.bean.DaoUser
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Context.MODE_MULTI_PROCESS




class MainActivity : AppCompatActivity() {

    private var count: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txt_main_db.text = queryUserDb()

        btn_main_insert.setOnClickListener({
            App.daoSession.daoUserDao.insertOrReplace(DaoUser(count++, "xiaoming$count", (12 + count).toInt()))
            txt_main_db.text = queryUserDb()
        })
        val s = applicationContext.getSharedPreferences("UserData", Context.MODE_MULTI_PROCESS)

    }

    private fun queryUserDb(): String? {
        val stringBuilder = StringBuilder()
        val queryBuilder = App.daoSession.queryBuilder(DaoUser::class.java)
        queryBuilder.listLazy().forEach {
            stringBuilder.append(it.name + "\n")
        }
        return stringBuilder.toString()
    }
}
