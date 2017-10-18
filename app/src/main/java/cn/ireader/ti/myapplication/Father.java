package cn.ireader.ti.myapplication;

import android.util.Log;

/**
 * Created by Xj on 2017/5/24.
 */

public class Father {
    public static final String TAG = "Father";
    public Father(){

    }

    public void print(){
        Log.e(TAG, "instance initializer: Father : " + "Father的print");
    }
}
