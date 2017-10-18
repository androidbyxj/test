package cn.ireader.ti.myapplication;

import android.util.Log;

/**
 * Created by Xj on 2017/5/24.
 */

public class Erzi extends Father {

    @Override
    public void print() {
        super.print();
        Log.e(TAG, "print: " + "Erzi的方法");
    }
}
