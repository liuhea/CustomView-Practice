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

    private var rootView: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        "onCreateView$position".log()
        /**
         * 防止Fragment多次切换时调用onCreateView重新加载View
         */
//        if (null == rootView) {
        rootView = TextView(container?.context).apply {
            text = "哈哈$position"
            textSize = 15f
        }
        /**
         * [1]为了保证一开始加载Fragment的时候判断是否需要加载数据,仅第一次onCreateView加载数据
         *
         * [2]也可以放在onResume中做处理，这样每次唤醒页面，就重新加载数据
         */
        if (userVisibleHint) {
            // 加载数据
            lazyLoadData()
        }
//        } else {
//            /**
//             * 缓存的rootView需要判断是否已经被加过parent，Layout形式
//             * 如果有parent需要从parent删除，要不然会发生这个rootView已经有parent的错误。
//             */
//            val parent = rootView?.parent as ViewGroup
//            parent?.removeView(rootView)
//        }
        return rootView!!
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        "setUserVisibleHint-$position-isVisibleToUser=$isVisibleToUser ".log()
        if (isVisibleToUser && isVisible) {
            lazyLoadData()
        }
    }

    private fun lazyLoadData() {
        "加载数据$position".log()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        "onAttach$position".log()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        "onCreate$position".log()
    }

    override fun onDestroy() {
        super.onDestroy()
        "onDestroy$position".log()

    }

    override fun onDetach() {
        super.onDetach()
        "onDetach$position".log()
    }
}