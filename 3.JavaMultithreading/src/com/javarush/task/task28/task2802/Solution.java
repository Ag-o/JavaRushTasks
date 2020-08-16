package com.javarush.task.task28.task2802;


import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* 
Пишем свою ThreadFactory
*/
public class Solution {

    public static void main(String[] args) {
        class EmulatorThreadFactoryTask implements Runnable {
            @Override
            public void run() {
                emulateThreadFactory();
            }
        }

        ThreadGroup group = new ThreadGroup("firstGroup");
        Thread thread = new Thread(group, new EmulatorThreadFactoryTask());

        ThreadGroup group2 = new ThreadGroup("secondGroup");
        Thread thread2 = new Thread(group2, new EmulatorThreadFactoryTask());

        thread.start();
        thread2.start();
    }

    private static void emulateThreadFactory() {
        AmigoThreadFactory factory = new AmigoThreadFactory();
        Runnable r = () -> System.out.println(Thread.currentThread().getName());
        factory.newThread(r).start();
        factory.newThread(r).start();
        factory.newThread(r).start();
    }

    public static class AmigoThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolCount = new AtomicInteger(1);
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        volatile int myPoolNumber;
        String threadName = "-thread-";
        String poolName = "-pool-";

        public AmigoThreadFactory() {
            this.myPoolNumber = poolCount.get();
            poolCount.incrementAndGet();
        }

        @Override
        public Thread newThread(Runnable r) {
            String newName = threadName + threadNumber.toString();
            threadNumber.incrementAndGet();
            Thread t = new Thread(r, newName);
            t.setDaemon(false);

            t.setPriority(Thread.NORM_PRIORITY);

            t.setName(t.getThreadGroup().getName() + this.poolName + myPoolNumber + newName);
            return t;
        }
    }
}
