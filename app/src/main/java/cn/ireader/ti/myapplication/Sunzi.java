package cn.ireader.ti.myapplication;

import android.util.Log;

/**
 * Created by Xj on 2017/5/24.
 */

public class Sunzi extends Father {
    public static final String TAG = "Sunzi";
    public Sunzi() {
    }


    @Override
    public void print() {
        super.print();
        Log.e(TAG, "print: " + "Sunzi的方法");
    }
}
