package com.kingdom.gnome.service.counting;

import com.kingdom.gnome.dao.entity.Gnome;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class GnomeCounterService {
    private final ThreadSupplier threadSupplier;

    public GnomeCounterService(ThreadSupplier threadSupplier) {
        this.threadSupplier = threadSupplier;
    }

    public int getOccurrencesCount(String type, String value, List<Gnome> gnomes) {
        int totalSize = gnomes.size();
        int threadCount = ThreadSupplier.getMachineThreadCount();

        List<FutureTask<Integer>> tasks = createTaskList(gnomes, type, value, totalSize, threadCount);
        List<Thread> threads = threadSupplier.buildThreads(tasks);

        ThreadSupplier.startAll(threads);
        ThreadSupplier.joinAll(threads);

        return finalResult(tasks, threadCount);
    }

    private List<FutureTask<Integer>> createTaskList(List<Gnome> gnomes, String type, String value, int totalSize, int threadCount) {
        List<FutureTask<Integer>> tasks = new LinkedList<>();
        for (int i = 0; i < threadCount; i++) {
            int leftBound = i * totalSize / threadCount;
            int rightBound = (i + 1) * totalSize / threadCount - 1;
            tasks.addLast(new FutureTask<>(new GnomeCounterTask(gnomes, type, value, leftBound, rightBound)));
        }

        return tasks;
    }

    private int finalResult(List<FutureTask<Integer>> tasks, int threadCount) {
        int result = 0;
        try {
            for (int i = 0; i < threadCount; i++) {
                result += tasks.get(i).get();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();// нужен в случае завершения программы, чтобы логика верхнего уровня завершила выполнение и не ушла у луп
            throw new RuntimeException("Главный поток был прерван", e);
        } catch (ExecutionException e) {
            throw new RuntimeException("Фоновый поток упал с ошибкой", e.getCause());
        }

        return result;
    }
}

