package com.example.chatuiplaygroud;

import android.app.Service;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.content.Intent;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

    /** 标识服务如果被杀死之后的行为 */
    int mStartMode;

    /** 绑定的客户端接口 */
    IBinder mBinder;

    /** 标识是否可以使用onRebind */
    boolean mAllowRebind;
/*
    private Message mActivityMessenger;
    private Handler handler;
    private Messenger mServiceMessenger;

    HandlerThread handlerThread = new HandlerThread("serviceCalculate");
    handlerThread.start();

    handler = new Handler(handlerThread.getLooper()){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0x11){

                if(mActivityMessenger == null) {
                    mActivityMessenger = msg.replyTo;
                }

                //模拟耗时任务
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                //发送结果回Activity
                Message message = this.obtainMessage();
                message.what = 0x12;
                message.arg1 = msg.arg1 + msg.arg2;
                try {
                    mActivityMessenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    */

    /** 当服务被创建时调用. */
    @Override
    public void onCreate() {
        super.onCreate();
        //Log.i("TAG", "onCreate()");
    }

    /** 调用startService()启动服务时回调 */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        //Toast.makeText(this, "service is on", Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

    /** 通过bindService()绑定到服务的客户端 */
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /** 通过unbindService()解除所有客户端绑定时调用 */
    @Override
    public boolean onUnbind(Intent intent) {
        return mAllowRebind;
    }

    /** 通过bindService()将客户端绑定到服务时调用*/
    @Override
    public void onRebind(Intent intent) {

    }

    /** 服务不再有用且将要被销毁时调用 */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "service is off", Toast.LENGTH_LONG).show();
    }
}
