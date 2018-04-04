package com.liuhe.kotlinutilslib

import android.content.Context
import android.os.Build
import android.webkit.*

/**
 *
 * @author liuhe
 * @create 2018-04-03
 *
 */
class H5WebView(context: Context) : WebView(context) {

    init {
        initSettings()
    }

    private fun initSettings() {
        val webSettings = this.settings.apply {
            //设置支持js
            javaScriptEnabled = true

            //开启 DOM 存储功能
            domStorageEnabled = true

            //缓存策略
            cacheMode = if (isConnected(context)) {
                WebSettings.LOAD_DEFAULT
            } else {
                WebSettings.LOAD_CACHE_ELSE_NETWORK
            }

            //H5缓存
            setAppCacheEnabled(true)
        }


//        webSettings.allowFileAccess = true
//        webSettings.useWideViewPort = true
//        webSettings.loadWithOverviewMode = true
//        webSettings.builtInZoomControls = false
//        webSettings.displayZoomControls = false
//        webSettings.defaultTextEncodingName = "UTF-8"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            webSettings.allowFileAccessFromFileURLs = true
            webSettings.allowUniversalAccessFromFileURLs = true
        }

        /*
        * Android5.0以上，系统默认禁止了mixed content和第三方cookie.
        * 统现在可以智能选择HTML文档的portion来绘制。这种新特性可以减少内存footprint并改进性能。若要一次性渲染整个HTML文档，可以调用这个方法enableSlowWholeDocumentDraw().
        */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val cookieManager = CookieManager.getInstance()
            cookieManager.setAcceptThirdPartyCookies(this, true)
            webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE
        }
    }
}