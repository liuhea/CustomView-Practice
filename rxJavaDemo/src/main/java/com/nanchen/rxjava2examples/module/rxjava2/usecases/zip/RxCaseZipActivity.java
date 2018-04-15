package com.nanchen.rxjava2examples.module.rxjava2.usecases.zip;

import android.util.Log;

import com.nanchen.rxjava2examples.model.CategoryResult;
import com.nanchen.rxjava2examples.model.MobileAddress;
import com.nanchen.rxjava2examples.module.rxjava2.operators.item.RxOperatorBaseActivity;
import com.nanchen.rxjava2examples.net.Network;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * zip 操作符的使用场景
 * <p>
 * 结合多个接口的数据再更新 UI
 * <p>
 * Author: nanchen
 * Email: liushilin520@foxmail.com
 * Date: 2017-06-30  17:24
 */

public class RxCaseZipActivity extends RxOperatorBaseActivity {
    private static final String TAG = "RxCaseZipActivity";

    @Override
    protected String getSubTitle() {
        return "zip 操作符使用场景";
    }

    @Override
    protected void doSomething() {
        Observable<MobileAddress> observable1 = Rx2AndroidNetworking.get("http://www.wanandroid.com/tools/mockapi/2636/mobileplace")
                .build()
                .getObjectObservable(MobileAddress.class);

        Observable<CategoryResult> observable2 = Network.getGankApi()
                .getCategoryData("Android",1,1);

        Observable.zip(observable1, observable2, new BiFunction<MobileAddress, CategoryResult, String>() {
            @Override
            public String apply(@NonNull MobileAddress mobileAddress, @NonNull CategoryResult categoryResult) throws Exception {
                return "合并后的数据为：手机归属地："+mobileAddress.getResult().getMobilearea()+"人名："+categoryResult.results.get(0).who;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        mRxOperatorsText.append("\nsubscribe 线程:" + Thread.currentThread().getName() + "\n");
                        Log.e(TAG, "成功:" + s.toString() + "\n");
                        mRxOperatorsText.append("成功:" + s.toString() + "\n");

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e(TAG, "accept: 失败：" + throwable+"\n");
                    }
                });
    }
}
