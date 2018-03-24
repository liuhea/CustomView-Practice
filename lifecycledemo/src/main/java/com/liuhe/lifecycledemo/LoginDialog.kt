package com.liuhe.lifecycledemo

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.liuhe.kotlinutilslib.log

/**
 * Created by liuhe on 24/03/2018.
 */
class LoginDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        "LoginDialog->onCreateDialog".log()
        return AlertDialog.Builder(activity).apply {
            setTitle("loginDialog")
        }.create()


    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        "LoginDialog->onAttach".log()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        "LoginDialog->onCreate".log()

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
        "LoginDialog->onCreateView".log()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        "LoginDialog->onActivityCreated".log()

    }

    override fun onStart() {
        super.onStart()
        "LoginDialog->onStart".log()

    }

    override fun onResume() {
        super.onResume()
        "LoginDialog->onResume".log()

    }

    override fun onPause() {
        super.onPause()
        "LoginDialog->onPause".log()

    }

    override fun onStop() {
        super.onStop()
        "LoginDialog->onStop".log()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        "LoginDialog->onDestroyView".log()

    }


    override fun onDestroy() {
        super.onDestroy()
        "LoginDialog->onDestroy".log()

    }

    override fun onDetach() {
        super.onDetach()
        "LoginDialog->onDetach".log()

    }
}

