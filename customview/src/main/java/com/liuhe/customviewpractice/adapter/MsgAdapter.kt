package com.liuhe.customviewpractice.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.liuhe.customviewpractice.R
import com.liuhe.customviewpractice.bean.Msg


/**
 * 消息列表适配器
 * @author liuhe
 */
class MsgAdapter(private val context: Context, private val msgList: List<Msg>) : RecyclerView.Adapter<MsgAdapter.MyViewHolder>() {
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
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleView: TextView = itemView.findViewById(R.id.tv_title)
        var unReadMsgCountView: TextView = itemView.findViewById(R.id.tv_unReadMsgCount)
    }
}

