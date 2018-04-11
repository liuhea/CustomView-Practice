package com.liuhe.aiqiyidemo.launchmode

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.liuhe.aiqiyidemo.R
import com.liuhe.kotlinutilslib.log
import kotlinx.android.synthetic.main.activity_a.*

class CActivity : AppCompatActivity() {
    private val TAG = "CActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)
        "$TAG-onCreate".log()

        txt_launch.text = "CActivity"

        txt_launch.setOnClickListener {
            val intent = Intent(this@CActivity, AActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
            startActivity(intent)
        }
    }


    override fun onStart() {
        super.onStart()
        "$TAG-onStart".log()
    }

    override fun onResume() {
        super.onResume()
        "$TAG-onResume".log()

    }

    override fun onPause() {
        super.onPause()
        "$TAG-onPause".log()

    }

    override fun onStop() {
        super.onStop()
        "$TAG-onStop".log()

    }

    override fun onDestroy() {
        super.onDestroy()
        "$TAG-onDestroy".log()

    }
}
