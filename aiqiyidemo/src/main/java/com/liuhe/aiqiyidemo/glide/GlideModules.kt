package com.liuhe.aiqiyidemo.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.module.LibraryGlideModule
import okhttp3.OkHttpClient


/**
 * http://blog.51cto.com/liangxiao/1966795
 *
 * @author liuhe
 * @create 2018-04-10
 *
 */
class HttpGlideModules : LibraryGlideModule() {

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)

        var builder = OkHttpClient.Builder()

    }
}