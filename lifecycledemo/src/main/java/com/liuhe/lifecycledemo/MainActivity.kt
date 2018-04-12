package com.liuhe.lifecycledemo

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import android.content.Intent
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.liuhe.kotlinutilslib.log
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 下拉状态栏是不是影响activity的生命周期?
 * 不会
 */
class MainActivity : AppCompatActivity(), LifecycleOwner {

    private var mLifecycleRegistry: LifecycleRegistry? = null

    override fun getLifecycle(): LifecycleRegistry {
        return mLifecycleRegistry!!
    }

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

        mLifecycleRegistry = LifecycleRegistry(this)
        mLifecycleRegistry?.markState(Lifecycle.State.CREATED)
    }

    override fun onStart() {
        super.onStart()
        mLifecycleRegistry?.markState(Lifecycle.State.STARTED)

        "MainActivity->onStart".log()

    }

    override fun onResume() {
        super.onResume()
        mLifecycleRegistry?.markState(Lifecycle.State.RESUMED)

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
        mLifecycleRegistry?.markState(Lifecycle.State.DESTROYED)

        "MainActivity->onDestroy".log()

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        "MainActivity->onNewIntent-----".log()
    }

    override fun onRestart() {
        super.onRestart()
        "MainActivity->onRestart-------".log()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        "MainActivity->onConfigurationChanged-------".log()

    }
}
