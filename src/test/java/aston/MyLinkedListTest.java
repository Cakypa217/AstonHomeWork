package aston;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MyLinkedListTest {
    private MyList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new MyLinkedList<>();
    }

    @Test
    void testAddAndSize() {
        list.add(10);
        list.add(20);
        list.add(30);
    }

    @Test
    void testGet() {
        list.add(100);
        list.add(200);

        assertEquals(100, list.get(0));
        assertEquals(200, list.get(1));
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
    void testRemoveFirstElement() {
        list.add(1);
        list.add(2);
        list.remove(1);

        assertThrows(NoSuchElementException.class, () -> list.remove(1));
    }

    @Test
    void testRemoveLastElement() {
        list.add(1);
        list.add(2);
        list.remove(2);

        assertThrows(NoSuchElementException.class, () -> list.remove(2));
    }

    @Test
    void testRemoveFromEmpty() {
        assertThrows(NoSuchElementException.class, () -> list.remove(100));
    }
}