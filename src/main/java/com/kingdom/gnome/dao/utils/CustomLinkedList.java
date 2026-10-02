package com.kingdom.gnome.dao.utils;

public class CustomLinkedList<E> {

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

    public void add(E value) {
        Node<E> newNode = new Node<E>(value);
        if (size == 0) {
            head = tail = newNode;
        }
        else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

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

    public void addAtHead(E value) {
        if (head != null) {
            Node<E> newNode = new Node<E>(value);
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
            size++;
        }
        else {
            Node<E> newNode = new Node<E>(value);
            head = newNode;
            tail = newNode;
            size++;
        }
    }

    public void addAtTail(E value) {
        if (tail != null) {
            Node<E> newNode = new Node<E>(value);
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            size++;
        }
        else {
            Node<E> newNode = new Node<E>(value);
            head = newNode;
            tail = newNode;
            size++;
        }
    }

    public void deleteAtIndex(int index) {
        if (index == 0) {
            deleteAtHead();
        } else if (index == size - 1) {
            deleteAtTail();
        } else if (index > 0 && index < size - 1) {
            Node<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            current.prev.next = current.next;
            current.next.prev = current.prev;
            size--;
        }
        else throw new IndexOutOfBoundsException("Index out of bounds");
    }

    public void deleteAtHead() {
        if (size == 1) { clear(); }
        else {
            head.next.prev = null;
            head = head.next;
            size--;
        }
    }

    public void deleteAtTail() {
        if ( size == 1) { clear(); }
        else if (size > 1) {
            tail.prev.next = null;
            tail = tail.prev;
            size--;
        }
    }

    public void addAtIndex(int index, E value) {
        if (index == 0) {
            addAtHead(value);
        } else if (index == size) {
            addAtTail(value);
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

    public void clear() {
        head = tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }
}
