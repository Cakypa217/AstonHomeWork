package aston;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MyArrayListTest {

    private MyList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new MyArrayList<>();
    }

    @Test
    void testAdd() {
        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    @Test
    void testAddAtIndex() {
        list.add(10);
        list.add(20);
        list.add(30);

        list.add(15, 1);

        assertEquals(15, list.get(1));
        assertEquals(20, list.get(2));
    }

    @Test
    void testRemove() {
        list.add(10);
        list.add(20);
        list.add(30);

        list.remove(20);

        assertThrows(NoSuchElementException.class, () -> list.remove(20));
    }

    @Test
    void testClear() {
        list.add(10);
        list.add(20);
        list.add(30);

        list.clear();

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    void testSort() {
        list.add(30);
        list.add(10);
        list.add(20);

        list.sort();

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    @Test
    void testGetOutOfBounds() {
        list.add(10);
        list.add(20);

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(2));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    @Test
    void testAddOutOfBounds() {
        list.add(10);
        list.add(20);

        assertThrows(IndexOutOfBoundsException.class, () -> list.add(30, 3));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(30, -1));
    }
}
