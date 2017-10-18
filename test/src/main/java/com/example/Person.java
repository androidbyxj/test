package com.example;

import java.util.ArrayList;
import java.util.List;

import sun.rmi.runtime.Log;

/**
 * Created by zy on 2016/9/21.
 */
public class Person {

    public static final String TAG = "Person";
    
    static {
        System.out.println(TAG);
    }
    private static final List<Integer> mOnlineIndexMap = new ArrayList<>();

    static {
        System.out.println("集合是否为空 :"+ (null == mOnlineIndexMap));
        mOnlineIndexMap.add(0);
        mOnlineIndexMap.add(1);
        mOnlineIndexMap.add(2);
        mOnlineIndexMap.add(3);
        mOnlineIndexMap.add(4);
    }

    public Person(){}
}
