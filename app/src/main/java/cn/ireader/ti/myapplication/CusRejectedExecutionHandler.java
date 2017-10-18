package cn.ireader.ti.myapplication;

import android.util.Log;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Xj on 2017/5/18.
 */

public class CusRejectedExecutionHandler implements RejectedExecutionHandler {
    public static final String TAG = "RejectedExeHandler";
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        Log.e(TAG, "rejectedExecution: 当前处于的线程名称：" + Thread.currentThread().getName() + " . ");
//        MainActivity.MyRunnable runnable = (MainActivity.MyRunnable) r;
//        Log.e(TAG, "rejectedExecution: " + "执行Id为 - " + runnable.mId + " - 的线程，发生异常.");
        try{
//            Thread.sleep(2000);
//            executor.execute(runnable);
        }
        catch(Exception e) {
            Log.e(TAG, "rejectedExecution: " + "尝试添加第 - ");
        }
    }
}
