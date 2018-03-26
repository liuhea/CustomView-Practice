package com.liuhe.videodemo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.liuhe.videodemo.activity.GsyVideoActivity
import com.liuhe.videodemo.activity.SurfaceViewActivity
import com.liuhe.videodemo.activity.VideoViewActivity
import kotlinx.android.synthetic.main.activity_list.*

/**
 * Created by liuhe on 26/03/2018.
 */
class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_list)
        val listOfClass = listOf(
                SurfaceViewActivity::class.java,
                VideoViewActivity::class.java,
                GsyVideoActivity::class.java
        )
        val listOfName = listOf(
                SurfaceViewActivity::class.java.simpleName,
                VideoViewActivity::class.java.simpleName,
                GsyVideoActivity::class.java.simpleName
        )

        lv_main.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfName)
        lv_main.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id
            ->
            run {
                val intent = Intent(this@ListActivity, listOfClass[position])
                startActivity(intent)
            }
        }
    }
}