package com.liuhe.customviewpractice.adapter

import android.content.Context
import android.graphics.PixelFormat
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.TextView
import com.liuhe.customviewpractice.R
import com.liuhe.customviewpractice.bean.Msg
import com.liuhe.customviewpractice.widget.DragCircleView


/**
 * 消息列表适配器
 * @author liuhe
 */
class MsgAdapter(private val context: Context, private val msgList: List<Msg>) : RecyclerView.Adapter<MsgAdapter.MyViewHolder>() {

    var touchListener: OnDragTouchListener

    init {
        touchListener = OnDragTouchListener(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_rcy, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return msgList?.size
    }

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        holder?.titleView?.text = msgList[position].title
        val unReadMsgCount = msgList[position].unReadMsgCount
        if (unReadMsgCount == 0) {
            holder?.unReadMsgCountView?.visibility = View.INVISIBLE
        } else {
            holder?.unReadMsgCountView?.visibility = View.VISIBLE
            holder?.unReadMsgCountView?.text = unReadMsgCount.toString() + ""
        }

        // 监听对应控件的触摸事件
        // 和重写onTouchEvent是有区别的
        // 如果一个控件重写了onTouchEvent并且返回true,切设置了触摸监听返回true,则MotionEvent交给OnTouchListener
        holder?.unReadMsgCountView?.setOnTouchListener(touchListener)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleView: TextView = itemView.findViewById(R.id.tv_title)
        var unReadMsgCountView: TextView = itemView.findViewById(R.id.tv_unReadMsgCount)
    }
}

class OnDragTouchListener(context: Context) : View.OnTouchListener {
    /**
     * WindowManager:这个类可以在任何的界面情况下添加一个额外的视图
     */
    private var manager: WindowManager? = null
    private var circleView: DragCircleView? = null
    private var params: WindowManager.LayoutParams? = null

    init {
        manager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        circleView = DragCircleView(context)
        params = WindowManager.LayoutParams()?.apply {
            height = WindowManager.LayoutParams.MATCH_PARENT
            width = WindowManager.LayoutParams.MATCH_PARENT
            //类型是透明
            format = PixelFormat.TRANSLUCENT
        }
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        // 自己想要的处理
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                (v as TextView).visibility = View.INVISIBLE
                val rawX = event.rawX
                val rawY = event.rawY
                circleView?.setDragViewPosition(rawX, rawY)
                circleView?.setText(v.text.toString().toInt())
                manager?.addView(circleView, params)
            }
            MotionEvent.ACTION_MOVE -> {
            }
            MotionEvent.ACTION_UP -> {
            }
        }
        circleView?.onTouchEvent(event)
        return true
    }
}

