package com.liuhe.lifecycledemo

import android.app.DialogFragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.liuhe.kotlinutilslib.log
import kotlinx.android.synthetic.main.activity_main.*


/**
 * 下拉状态栏是不是影响activity的生命周期?
 * 不会
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        "MainActivity->onCreate".log()
        val loginDialog = LoginDialog()

        btn_main_dialog.setOnClickListener({
            //            val builder = AlertDialog.Builder(this@MainActivity)
//            builder.setTitle("This is a sample")
//            builder.create()
//            builder.show()

            loginDialog.show(supportFragmentManager, MainActivity::class.java.simpleName)
        })
    }

    override fun onStart() {
        super.onStart()
        "MainActivity->onStart".log()

    }

    override fun onResume() {
        super.onResume()
        "MainActivity->onResume".log()

    }

    override fun onPause() {
        super.onPause()
        "MainActivity->onPause".log()

    }

    override fun onStop() {
        super.onStop()
        "MainActivity->onStop".log()

    }

    override fun onDestroy() {
        super.onDestroy()
        "MainActivity->onDestroy".log()

    }
}
