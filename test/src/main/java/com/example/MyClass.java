package com.example;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MyClass {

    public static void main(String[] args) {
        //        String str = "Hello,World! in Java.f";
        //        Pattern pattern = Pattern.compile("vaf",Pattern.CASE_INSENSITIVE);
        //        Matcher matcher = pattern.matcher(str);
        //        System.out.println(matcher.matches());
        //        System.out.println(matcher.find());
        //        while(matcher.find()){
        //            System.out.println(" -------------start ----------------");
        //            System.out.println(matcher.groupCount());
        //        }
        //        Pattern p = Pattern.compile("a*b");
        //        Matcher m = p.matcher("aaaaab");
        //        boolean b = m.matches();
        //        System.out.println(b);
        //    }

//        List<String> mName = null; /*= new ArrayList<>()*/;
//        for (String bean : mName) {
//            System.out.println(bean);
//        }

        System.out.println(0x00000100);
        System.out.println(" ---------------------------------");
        ArrayList<Integer> mRefreshType = new ArrayList<>(3);

        mRefreshType.add(100);
        mRefreshType.add(101);
        mRefreshType.add(102);
//        if(!mRefreshType.contains(102)) {
            mRefreshType.add(1023);
//        }


        Iterator it = mRefreshType.iterator();
        while (it.hasNext()) {
            int msg = (int) it.next();
            System.out.println(msg);
//            it.remove();
        }

        System.out.println(mRefreshType.size());


//        int i = 0>>1;
//        System.out.println(i);
//        int i1 = 1>>1;
//        System.out.println(i1);
//        int i2 = 2>>1;
//        System.out.println(i2);
//        int i3 = 3>>1;
//        System.out.println(i3);
//        int i4 = 100>>1;
//        System.out.println(i4);



        //        int i = 0%6;
//        System.out.println(i);

//         String text="(content:\"rcpt to root\";pcre:\"word\";)";
//         String rule1="content:\".+\"";    //贪婪模式
//         String rule2="content:\".+?\"";    //非贪婪模式
//
//         System.out.println("文本："+text);
//         System.out.println("贪婪模式："+rule1);
//         Pattern p1 =Pattern.compile(rule1);
//         Matcher m1 = p1.matcher(text);
//         while(m1.find()){
//                 System.out.println("匹配结果："+m1.group(0));
//         }
//
//         System.out.println("非贪婪模式："+rule2);
//         Pattern p2 =Pattern.compile(rule2);
//         Matcher m2 = p2.matcher(text);
//         while(m2.find()){
//                 System.out.println("匹配结果："+m2.group(0));
//         }
//        setKeyWordColor("微微一笑很秦城","微|微|笑");
//        int a = (int) Math.round(1.8);
//        System.out.println(a);
//        int b = (int)Math.round(1.2);
//        System.out.println(b);
        String tmp = "'——————'";
        System.out.println(tmp);
    }

    public static void setKeyWordColor(String content, String keyword){
        Pattern p = Pattern.compile(keyword);
        Matcher m = p.matcher(content);
        System.out.println(m.find());
        while (m.find()){
            int start = m.start();
            int end = m.end();
            System.out.println(start + ", " + end);
        }
    }
}
