package com.liuhe.viewpagerdemo

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.support.v4.app.Fragment
import android.view.ViewGroup
import android.widget.TextView
import com.liuhe.kotlinutilslib.log

/**
 * Created by liuhe on 24/03/2018.
 */
@SuppressLint("ValidFragment")
class LazyFragment(val position: Int) : Fragment() {

    private var createView = false
    var visibleToUser = false

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        createView = true
        return TextView(container?.context).apply {
            text = "哈哈"
            textSize = 15f
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        visibleToUser=isVisibleToUser
        "setUserVisibleHint-$position-isVisibleToUser=$isVisibleToUser createView=$createView".log()
        lazyLoadData(isVisibleToUser)

    }

    private fun lazyLoadData(visibleToUser: Boolean) {
        if (createView && visibleToUser) {
            "加载数据$position".log()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        "onAttach".log()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        "onCreate".log()
    }

    override fun onResume() {
        super.onResume()
        lazyLoadData(visibleToUser)
    }
}