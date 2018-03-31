package com.liuhe.customviewpractice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.liuhe.kotlinutilslib.log
import com.liuhe.customviewpractice.bean.PieData
import com.liuhe.customviewpractice.widget.PieView
import kotlinx.android.synthetic.main.activity_pie.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pie)
        "onCreate()执行完毕".log()
        mockPieData()
    }

    /**
     * 模拟扇形数据
     */
    private fun mockPieData() {
        var pies = mutableListOf<PieData>()
        val colors = listOf(0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
                0xFFE6B800, 0xFF7CFC00)
        for (i in 0 until colors.size) {
            pies.add(PieData((i+1).toFloat(), colors[i]))
        }
        pie_main?.setData(pies)
    }


    override fun onStart() {
        super.onStart()
        "onStart()执行完毕".log()
    }

    override fun onResume() {
        super.onResume()
        "onResume()执行完毕".log()
    }

    override fun onPause() {
        super.onPause()
        "onPause()执行完毕".log()
    }

    override fun onStop() {
        super.onStop()
        "onStop()执行完毕".log()
    }

    override fun onDestroy() {
        super.onDestroy()
        "onDestroy()执行完毕".log()
    }
}
