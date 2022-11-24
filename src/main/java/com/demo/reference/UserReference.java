package com.demo.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author gaoyanzhen
 * @since 2022-06-29
 */
public class UserReference {
    public static void main(String[] args) {
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        List<WeakReference> weakReferenceList = new ArrayList<>();

        for (int i = 0; i < 1000000; i++) {
            WeakReference<User> weakReference = new WeakReference<>(new User(Math.round(Math.random() * 1000)), referenceQueue);
            weakReferenceList.add(weakReference);
        }

        WeakReference weakReference;
        int count = 0;

        while ((weakReference = (WeakReference) referenceQueue.poll()) != null) {
            System.out.println("JVM 清理了：" + weakReference + "，从 WeakReference 中取出对象值为：" + weakReference.get());
            count++;
        }

        System.out.println("weakReference 中元素数目为：" + count);

        System.out.println("在不被清理的情况下，可以从WeakReference中取出对象值为：" +
                new WeakReference<>(new User(Math.round(Math.random() * 1000)), referenceQueue).get());
    }
}
