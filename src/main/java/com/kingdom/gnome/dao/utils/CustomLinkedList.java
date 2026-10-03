package com.kingdom.gnome.dao.utils;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class CustomLinkedList<E> implements List<E> {

    public static class Node<E> {
        E value;
        Node<E> prev;
        Node<E> next;

        public Node(E value) {
            this.value = value;
            this.prev = null;
            this.next = null;
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
        Node<E> newNode = new Node<>(value);
        if (tail != null) {
            tail.next = newNode;
            newNode.prev = tail;
        }
        else {
            head = newNode;
        }
        tail = newNode;
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
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
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
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer action) {
        List.super.forEach(action);
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public Object[] toArray(IntFunction generator) {
        return List.super.toArray(generator);
    }


    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeIf(Predicate filter) {
        return List.super.removeIf(filter);
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
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
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
