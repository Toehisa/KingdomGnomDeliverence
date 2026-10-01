package com.kingdom.gnome.service.counting;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.FutureTask;

public class ThreadSupplier {
    public ThreadSupplier() {}

    public static int getMachineThreadCount() {
        return Runtime.getRuntime().availableProcessors();
    }

    public List<Thread> buildThreads(List<FutureTask<Integer>> tasks) {
        List<Thread> threads = new LinkedList<>();

        for(var task : tasks) {
            threads.addLast(new Thread(task));
        }

        return threads;
    }

    public static void startAll(List<Thread> threads) {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public static void joinAll(List<Thread> threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
