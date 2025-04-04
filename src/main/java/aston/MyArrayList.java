package aston;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements MyList<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_LIST = {};
    private Object[] data;
    private int size;

    public MyArrayList() {
        data = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public MyArrayList(int capacity) {
        data = (capacity > 0) ? new Object[capacity] : EMPTY_LIST;
        size = 0;
    }

    @Override
    public void add(T value) {
        myEnsureCapacity();
        data[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        myEnsureCapacity();

        System.arraycopy(data, index, data, index + 1, size - index);

        data[index] = value;
        size++;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) data[index];
    }

    @Override
    public void remove(T value) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(value)) {
                int numMoved = size - i - 1;
                if (numMoved > 0) {
                    System.arraycopy(data, i + 1, data, i, numMoved);
                }
                data[--size] = null;
                return;
            }
        }
        throw new NoSuchElementException("Element not found: " + value);
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    @Override
    public void sort() {
        Arrays.sort(data, 0, size, (a, b) -> ((Comparable<T>) a).compareTo((T) b));
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void myEnsureCapacity() {
        if (size == data.length) {
            int newCapacity = data.length + (data.length >> 1);
            data = Arrays.copyOf(data, newCapacity);
        }
    }
}
