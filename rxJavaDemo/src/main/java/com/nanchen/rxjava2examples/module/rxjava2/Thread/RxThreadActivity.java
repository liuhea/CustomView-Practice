package com.nanchen.rxjava2examples.module.rxjava2.Thread;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.nanchen.rxjava2examples.module.rxjava2.operators.item.RxOperatorBaseActivity;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Thread 原谅我把线程调度切换放在了例子一页，实属无奈
 * <p>
 * Author: nanchen
 * Email: liushilin520@foxmail.com
 * Date: 2017-07-03  14:45
 */

public class RxThreadActivity extends RxOperatorBaseActivity {
    private static final String TAG = "RxThreadActivity";
    MyHandler handler = new MyHandler(this);

    static class MyHandler extends Handler {
        private WeakReference<Activity> mActivity;

        public MyHandler(RxThreadActivity activity) {
            mActivity = new WeakReference<Activity>(activity);
        }


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mActivity.get() == null) {
                return;
            }
            Toast.makeText(mActivity.get(), "msg.obj:" + msg.obj, Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected String getSubTitle() {
        return "线程调度";
    }

    @Override
    protected void doSomething() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                Handler handler = new Handler();
            }
        }).start();

        Message message = new Message();
        message.obj = Thread.currentThread().getName();
        message.what = 1;
        handler.sendMessage(message);

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                Log.e(TAG, "Observable thread is : " + Thread.currentThread().getName());
                e.onNext(1);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "After observeOn(mainThread)，Current thread is " + Thread.currentThread().getName());
                    }
                })
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "After observeOn(io)，Current thread is " + Thread.currentThread().getName());
                    }
                });
    }
}
