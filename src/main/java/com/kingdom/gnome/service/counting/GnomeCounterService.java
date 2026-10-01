package com.kingdom.gnome.service.counting;

import com.kingdom.gnome.dao.entity.Gnome;

import java.util.ArrayList;
import java.util.List;

public class GnomeCounterService {
    private final ThreadSupplier threadSupplier;

    public GnomeCounterService(ThreadSupplier threadSupplier) {
        this.threadSupplier = threadSupplier;
    }

    public int getOccurrencesCount(String type, String value, List<Gnome> gnomes) {
        List<GnomeCounterTask> tasks = new ArrayList<>();

        int totalSize = gnomes.size();
        int threadCount = ThreadSupplier.getMachineThreadCount();

        fillTaskList(tasks, gnomes, type, value, totalSize, threadCount);

        List<Thread> threads = threadSupplier.buildThreads(new ArrayList<>(tasks));

        ThreadSupplier.startAll(threads);
        ThreadSupplier.joinAll(threads);

        return finalResult(tasks, threadCount);
    }

    // Переносим аргументы в параметры приватных методов
    private void fillTaskList(List<GnomeCounterTask> tasks, List<Gnome> gnomes, String type, String value, int totalSize, int threadCount) {
        for (int i = 0; i < threadCount; i++) {
            int leftBound = i * totalSize / threadCount;
            int rightBound = (i + 1) * totalSize / threadCount - 1;
            tasks.addLast(new GnomeCounterTask(gnomes, type, value, leftBound, rightBound));
        }
    }

    private int finalResult(List<GnomeCounterTask> tasks, int threadCount) {
        int result = 0;
        for (int i = 0; i < threadCount; i++) {
            result += tasks.get(i).getResult();
        }
        return result;
    }
}

