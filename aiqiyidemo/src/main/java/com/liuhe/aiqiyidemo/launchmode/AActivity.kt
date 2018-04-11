package com.liuhe.aiqiyidemo.launchmode

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.liuhe.aiqiyidemo.R
import com.liuhe.kotlinutilslib.log
import kotlinx.android.synthetic.main.activity_a.*


/**
 * https://blog.csdn.net/liuhe688/article/details/6761337
 * 总结篇之三：Activity的task相关
 */
class AActivity : AppCompatActivity() {
    val url = "https://rs.shejijia.com/floorplan/render/images/2017-12-3/da9a264e-2d34-47f4-929e-5a6ad00b6b5a/3b495e96_c4b9_42aa_9dc2_7e7cb39f5db4.jpg?x-oss-process=style/hd"
    private val gifUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523356278951&di=1f4538f637c572a147f1cdf29d21abeb&imgtype=0&src=http%3A%2F%2Fimg18.3lian.com%2Fd%2Ffile%2F201704%2F06%2F3e7dea6b7bbb67bb163946445cdda09c.gif"

    private val TAG = "AActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)
        "$TAG-onCreate".log()

        txt_launch.text = "AActivity"
        txt_launch.setOnClickListener {
            val intent = Intent(this@AActivity, BActivity::class.java)
            startActivity(intent)
        }

        btn_load_img.setOnClickListener{
            Glide.with(this).load(url).into(img_launch)
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
