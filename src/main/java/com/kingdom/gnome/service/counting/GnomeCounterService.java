package com.kingdom.gnome.service.counting;

import com.kingdom.gnome.dao.entity.Gnome;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class GnomeCounterService {
    private final ExecutorService threadSupplier;

    public GnomeCounterService(ExecutorService threadSupplier) {
        this.threadSupplier = threadSupplier;
    }

    public int getOccurrencesCount(String type, String value, List<Gnome> gnomes) {
        int totalSize = gnomes.size();
        int threadCount = Runtime.getRuntime().availableProcessors();

        List<Callable<Integer>> tasks = createTaskList(gnomes, type, value, totalSize, threadCount);
        List<Future<Integer>> taskResults;

        try {
            taskResults = threadSupplier.invokeAll(tasks);
            return finalResult(taskResults);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Что то не так с подсчетом вхождений: "+e);
        } catch (ExecutionException e) {
            throw new RuntimeException("Фоновый поток упал с ошибкой", e.getCause());
        }

        return 0;
    }

    private List<Callable<Integer>> createTaskList(List<Gnome> gnomes, String type, String value, int totalSize, int threadCount) {
        List<Callable<Integer>> tasks = new LinkedList<>();
        for (int i = 0; i < threadCount; i++) {
            int leftBound = i * totalSize / threadCount;
            int rightBound = (i + 1) * totalSize / threadCount - 1;
            tasks.addLast(new GnomeCounterTask(gnomes, type, value, leftBound, rightBound));
        }

        return tasks;
    }

    private int finalResult(List<Future<Integer>> tasks) throws ExecutionException, InterruptedException {
        int result = 0;

        for (var task : tasks) {
            result += task.get();
        }

        return result;
    }
}

