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
 * 启动指定app
 * @param applicationId App的包名
 * @param marketPkgId  应用市场的包名
 */
@JvmOverloads
fun Context.launchApp(applicationId: String, marketPkgId: String = "") {
    // 判断是否安装过App，否则去市场下载
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
    val uri = Uri.parse("market://details?id=$applicationId")
    val goToMarket = Intent(ACTION_VIEW, uri)
    try {
        // 如果没给市场的包名，则系统会弹出市场的列表让你进行选择。
        if (!TextUtils.isEmpty(marketPkg)) {
            goToMarket.`package` = marketPkg
        }
        startActivity(goToMarket)
    } catch (e: ActivityNotFoundException) {
        e.printStackTrace()
        goToMarket.data = Uri.parse("http://m.app.mi.comdetails?id=" + getPackageName());

    }
}

//
////存在手机里没安装应用市场的情况，跳转会包异常，做一个接收判断
//if (intent.resolveActivity(getPackageManager()) != null) { //可以接收
//    startActivity(intent);
//} else { //没有应用市场，我们通过浏览器跳转到Google Play
//    intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()));
////这里存在一个极端情况就是有些用户浏览器也没有，再判断一次
//    if (intent.resolveActivity(getPackageManager()) != null) { //有浏览器
//        startActivity(intent);
//    } else { //天哪，这还是智能手机吗？
//        Toast.makeText(this, "天啊，您没安装应用市场，连浏览器也没有，您买个手机干啥？", Toast.LENGTH_SHORT).show();