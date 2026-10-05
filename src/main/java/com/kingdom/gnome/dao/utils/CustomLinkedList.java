package com.kingdom.gnome.dao.utils;

import org.jspecify.annotations.NonNull;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class CustomLinkedList<E> implements List<E> {

    private static class Node<E> {
        private E value;
        private Node<E> prev = null;
        private Node<E> next = null;

        private Node(E value) {
            this.value = value;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public CustomLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean add(E value) {
        Node<E> newNode = new Node<>(value);
        if (size == 0) {
            head = tail = newNode;
        }
        else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
        return true;
    }

    @Override
    public void add(int index, E value) {
        if (index == 0) {
            addFirst(value);
        } else if (index == size) {
            addLast(value);
        } else if (index > 0 && index < size) {
            Node<E> newNode = new Node<>(value);
            Node<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            newNode.prev = current.prev;
            newNode.next = current;
            current.prev.next = newNode;
            current.prev = newNode;
            size++;
        }
        else throw new IndexOutOfBoundsException("Index out of bounds");
    }

    @Override
    public void addFirst(E value) {
        Node<E> newNode = new Node<>(value);
        if (head != null) {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        }
        else {
            head = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void addLast(E value) {
        if (value == null) return;
        Node<E> newNode = new Node<>(value);
        if (head == null) {
            tail = head = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }

    @Override
    public E set(int index, E value) {
        E deletable = null;
        if (index >= 0 && index < size) {
            Node<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            deletable = current.value;
            current.value = value;
        }
        return deletable;
    }

    @Override
    public E remove(int index) {
        E deletable = null;
        if (index == 0) {
            deletable = removeFirst();
        } else if (index == size - 1) {
            deletable = removeLast();
        } else if (index > 0 && index < size - 1) {
            Node<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            deletable = current.value;
            current.prev.next = current.next;
            current.next.prev = current.prev;
            size--;
        }
        else throw new IndexOutOfBoundsException("Index out of bounds");
        return deletable;
    }

    @Override
    public E removeFirst() {
        Node<E> deletable = head;
        if (size == 1) { clear(); }
        else {
            head.next.prev = null;
            head = head.next;
            size--;
        }
        return deletable.value;
    }

    @Override
    public E removeLast() {
        Node<E> deletable = tail;
        if ( size == 1) { clear(); }
        else if (size > 1) {
            tail.prev.next = null;
            tail = tail.prev;
            size--;
        }
        return deletable.value;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public E getFirst() {
        return head.value;
    }

    @Override
    public E getLast() {
        return tail.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int indexOf(Object o) {
        Node<E> current = head;

        for (int i = 0; current != null; i++) {
            var val = current.value;
            if (val != null && val.equals(o)) return i;
            if (o == null && val == null) return i;
            current = current.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Node<E> current = tail;

        for (int i = size - 1; current != null; i--) {
            var val = current.value;
            if (val != null && val.equals(o)) return i;
            if (o == null && val == null) return i;
            current = current.prev;
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new CustomListIterator(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new CustomListIterator(index);
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return List.of();
    }

    @Override
    public Spliterator spliterator() {
        return List.super.spliterator();
    }

    @Override
    public Stream stream() {
        return List.super.stream();
    }

    @Override
    public Stream parallelStream() {
        return List.super.parallelStream();
    }

    @Override
    public List reversed() {
        return List.super.reversed();
    }

    @Override
    public boolean contains(Object o) {
        Node<E> current = head;

        for(int i = 0; i < size; i++) {
            if (current.value.equals(o)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new CustomIterator();
    }

    @Override
    public void forEach(Consumer action) {
        List.super.forEach(action);
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        var current = head;

        for (int i = 0; current != null; i++) {
            arr[i] = current.value;
            current = current.next;
        }

        return arr;
    }

    @Override
    public Object[] toArray(IntFunction generator) {
        Object[] arr = (Object[]) generator.apply(size);
        var current = head;

        for (int i = 0; current != null; i++) {
            arr[i] = current.value;
            current = current.next;
        }

        return arr;
    }


    public boolean remove(Object o) {
        Node<E> current = head;

        while (current != null) {
            if (Objects.equals(o, current.value)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }

                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }

                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }


    @Override
    public boolean addAll(@NonNull Collection<? extends E> c) {
        if (c == null || c.isEmpty()) {
            return false;
        }
        for (E element : c){
            add(element);
        }

        return true;
    }

    @Override
    public boolean removeIf(Predicate filter) {
        return List.super.removeIf(filter);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (c == null || c.isEmpty()) {
            return false;
        }
        var arr = (E[]) c.toArray();
        for(int j = 0; j < arr.length-1; j++) {
            add(index, arr[j]);
        }
        return true;
    }

    @Override
    public void replaceAll(UnaryOperator operator) {
        List.super.replaceAll(operator);
    }

    @Override
    public void sort(Comparator c) {
        List.super.sort(c);
    }

    @Override
    public boolean retainAll(@NonNull Collection<?> c) {
        boolean modified = false;
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            E element = iterator.next();
            if (!c.contains(element)) {
                iterator.remove();
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean res = false;
        for(var i : c) {
            res = remove(c);
        }
        return res;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object[] toArray(Object[] a) {
        if (a.length < size) {
            a = (Object[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        }

        Node<E> current = head;
        for (int i = 0; current != null; i++) {
            a[i] = current.value;
            current = current.next;
        }

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    private class CustomIterator implements Iterator<E> {
        Node<E> current;
        int cursor;

        CustomIterator() {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return this.cursor != size;
        }

        @Override
        public E next() {
            if(cursor >= size) {
                throw new NoSuchElementException();
            } else {
                E data = current.value;
                current = current.next;
                cursor++;
                return data;
            }
        }
    }

    public void deleteAtIndex(int index) {
        if (index > 0 && index < size - 1) {
            Node<E> current = head;

            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }

            current.next = current.next.next;
            size--;
        } else if (index == 0) {
            deleteAtStart();
        } else if (index == size - 1) {
            deleteAtTail();
        }
    }

    public void deleteAtStart() {
        if (size == 1) {
            clear();
        } else if (head != null) {
            head = head.next;
            size--;
        }
    }

    public void deleteAtTail() {
        if (size > 1) {
            Node<E> current = head;

            while (current.next.next != null) {
                current = current.next;
            }

            current.next = null;

            tail = current;
            size--;
        } else if (size == 1) {
            clear();
            size = 0;
        }
    }

    private class CustomListIterator extends CustomLinkedList<E>.CustomIterator implements ListIterator<E> {
        CustomListIterator(int index) {
            super();
            cursor = index;
            for(int i = 0; i < cursor; i++) {
                current = current.next;
            }
        }

        @Override
        public boolean hasPrevious() {
            return this.cursor != 0;
        }

        @Override
        public E previous() {
            if (!hasPrevious())throw new NoSuchElementException();
            return CustomLinkedList.this.get(--cursor);
        }

        @Override
        public int nextIndex() {
            return this.cursor;
        }

        @Override
        public int previousIndex() {
            return this.cursor - 1;
        }

        @Override
        public void remove() {
            deleteAtIndex(cursor);
        }

        @Override
        public void set(E e) {
            if (cursor == 0) throw new IllegalStateException();
            CustomLinkedList.this.set(cursor - 1, e);
        }

        @Override
        public void add(E e) {
            CustomLinkedList.this.add(cursor, e);
        }
    }
}
