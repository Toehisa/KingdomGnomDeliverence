package com.kingdom.gnome.service.sorting;

import java.util.Comparator;

public class MergeSort<T> {

    private final T[] items;
    private final T[] aux;
    private final Comparator<T> comparator;

    public MergeSort(T[] items, Comparator<T> comparator) {
        this.items = items;
        this.comparator = comparator;
        this.aux = (T[]) new Object[items.length];
    }

    public void sort() {
        sort(0, items.length - 1);
    }

    private void sort(int low, int high) {
        if (low >= high) {
            return;
        }

        int mid = low + (high - low) / 2;

        sort(low, mid);
        sort(mid + 1, high);

        merge(low, mid, high);
    }

    private void merge(int low, int mid, int high) {

        for (int i = low; i <= high; i++) {
            aux[i] = items[i];
        }

        int left = low;
        int right = mid + 1;

        for (int i = low; i <= high; i++) {

            if (left > mid) {
                items[i] = aux[right++];

            } else if (right > high) {
                items[i] = aux[left++];

            } else if (comparator.compare(aux[left], aux[right]) <= 0) {
                items[i] = aux[left++];

            } else {
                items[i] = aux[right++];
            }
        }
    }

    public T[] getSortedItems() {
        return items;
    }
}