package aston;

import java.util.NoSuchElementException;

public class MyLinkedList<T> implements MyList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public MyLinkedList() {
    }

    @Override
    public void add(T v) {
        Node<T> newNode = new Node<>(tail, v, null);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void add(T v, int index) {
        checkIndexForAdd(index);
        if (index == 0) {
            Node<T> newNode = new Node<>(null, v, head);
            if (head != null) {
                head.prev = newNode;
            }
            head = newNode;
            if (tail == null) {
                tail = newNode;
            }
        } else if (index == size) {
            add(v);
        } else {
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            Node<T> newNode = new Node<>(current.prev, v, current);
            current.prev.next = newNode;
            current.prev = newNode;
        }

        size++;
    }

    @Override
    public T get(int index) {
        checkIndex(index);

        Node<T> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }

        return current.item;
    }

    @Override
    public void remove(T value) {
        if (head == null) {
            throw new NoSuchElementException("Element not found: " + value);
        }

        Node<T> current = head;

        while (current != null) {
            if (current.item.equals(value)) {
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
                return;
            }
            current = current.next;
        }

        throw new NoSuchElementException("Element not found: " + value);
    }

    @Override
    public void clear() {
        Node<T> current = head;

        while (current != null) {
            Node<T> next = current.next;
            current.prev = null;
            current.next = null;
            current.item = null;
            current = next;
        }

        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void sort() {
        if (head == null || head.next == null) {
            return;
        }
        head = mergeSort(head);

        Node<T> node = head;
        while (node != null && node.next != null) {
            node = node.next;
        }
        tail = node;
    }

    private Node<T> mergeSort(Node<T> head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node<T> middle = getMiddle(head);
        Node<T> nextOfMiddle = middle.next;
        middle.next = null;

        Node<T> left = mergeSort(head);
        Node<T> right = mergeSort(nextOfMiddle);

        return merge(left, right);
    }

    private Node<T> merge(Node<T> left, Node<T> right) {
        if (left == null) return right;
        if (right == null) return left;

        if (((Comparable<T>) left.item).compareTo(right.item) <= 0) {
            left.next = merge(left.next, right);
            left.next.prev = left;
            left.prev = null;
            return left;
        } else {
            right.next = merge(left, right.next);
            right.next.prev = right;
            right.prev = null;
            return right;
        }
    }

    private Node<T> getMiddle(Node<T> head) {
        if (head == null) return null;

        Node<T> slow = head;
        Node<T> fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        Node(MyLinkedList.Node<T> prev, T element, MyLinkedList.Node<T> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}