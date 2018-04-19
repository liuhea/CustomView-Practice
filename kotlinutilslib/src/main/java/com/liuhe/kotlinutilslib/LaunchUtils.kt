package com.liuhe.kotlinutilslib

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.content.pm.PackageManager
import android.net.Uri
import android.text.TextUtils


/**
 * 调启其它app
 * @author liuhe
 * @create 2018-04-19
 *
 */

/**
 * 小米应用市场
 */
const val MI_WEB_MARKET = "http://app.mi.com/details?id="

/**
 * 应用宝
 */
const val TENCENT_WEB_MARKET = "http://sj.qq.com/myapp/detail.htm?apkName="

/**
 * 启动指定app
 * @param applicationId App的包名
 * @param marketPkgId  应用市场的包名
 */
@JvmOverloads
fun Context.launchApp(applicationId: String, marketPkgId: String = "") {
    if (TextUtils.isEmpty(applicationId)) {
        return
    }

    if (isAppInstalled(applicationId)) {
        startActivity(this.packageManager.getLaunchIntentForPackage(applicationId))
    } else {
        goToMarket(applicationId, marketPkgId)
    }
}

/**
 * 检测某个应用是否安装
 *
 * @param applicationId App的包名
 */
fun Context.isAppInstalled(applicationId: String): Boolean {
    return try {
        applicationContext.packageManager.getPackageInfo(applicationId, 0)
        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}

/**
 * 去市场下载页面
 * @param applicationId App的包名
 * @param marketPkg 应用市场的包名
 */
fun Context.goToMarket(applicationId: String, marketPkg: String = "") {
    val goToMarket = Intent(ACTION_VIEW)
    goToMarket.data = Uri.parse("market://details?id=$applicationId")
    try {
        // 判断是否安装应用市场
        if (null != goToMarket.resolveActivity(applicationContext.packageManager)) {
            // 如果指定应用市场，需要判断是否安装
            if (!TextUtils.isEmpty(marketPkg) && isAppInstalled(marketPkg)) {
                goToMarket.`package` = marketPkg
            }
        } else {
            // 没有应用市场，跳转用户浏览器的指定应用市场，以
            goToMarket.data = Uri.parse("$TENCENT_WEB_MARKET$applicationId")
        }
        startActivity(goToMarket)
    } catch (e: ActivityNotFoundException) {
        e.printStackTrace()
    }
}