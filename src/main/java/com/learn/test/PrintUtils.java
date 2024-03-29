package com.learn.test;

import com.alibaba.fastjson.JSONObject;
import com.learn.test.thead.ThreadUtils;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.CompletableFuture;

/**
 * @author Bai
 * @date 2022/4/26 21:20
 */
public class PrintUtils {

    public static void print(String str) {
        System.out.println(str);
    }

    public static void print(Object o) {
        System.out.println(JSONObject.toJSONString(o));
    }

    public static void toStr(Object o) {
        System.out.println(JSONObject.toJSONString(o));
    }

    public static void toStr(String msg, String o) {
        System.out.println(msg + " - " + o);
    }

    public static void oToStr(String msg, Object o) {
        System.out.println(msg + "：" + o);
    }

    public static void tco(Object s) {
        String cft = "[" + Thread.currentThread().getName() + "]" + "：" + JSONObject.toJSONString(s);
        //提交线程池进行异步输出，使得输出过程不影响当前线程的执行
        // 异步输出的好处：不会造成输出乱序，也不会造成当前线程阻塞
        CompletableFuture.runAsync(() -> {
            synchronized (System.out) {
                System.out.println(cft);
            }
        });
    }

    public static void tco(String msg, Object s) {
        String cft = "[" + Thread.currentThread().getName() + "]" + "：" + msg + JSONObject.toJSONString(s);
        //提交线程池进行异步输出，使得输出过程不影响当前线程的执行
        // 异步输出的好处：不会造成输出乱序，也不会造成当前线程阻塞
        CompletableFuture.runAsync(() -> {
            synchronized (System.out) {
                System.out.println(cft);
            }
        });
    }

    public synchronized static void synTco(String s) {
        print(s);
    }

    public synchronized static void printPool(String s) {
        String pool = "[" + ThreadUtils.getThreadName() + "]";
        print(pool + ":" + s);
    }


    public static void printClassLayout(Object o) {
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        System.out.println("---------------------");
    }

    public static void printSplitLine() {
        System.out.println("---------------------");
    }
}
