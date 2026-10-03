package utils;

import com.kingdom.gnome.dao.utils.CustomLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CustomLinkedListTest {

    private CustomLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new CustomLinkedList<>();
    }

    @Test
    void testAdd() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(3, list.size());
        assertEquals(1, (int) list.get(0));
        assertEquals(2, (int) list.get(1));
        assertEquals(3, (int) list.get(2));
    }

    @Test
    void testAddAtIndex() {
        list.add(1);
        list.add(2);
        list.add(3);

        list.add(1, 100);

        assertEquals(4, list.size());
        assertEquals(1, (int) list.get(0));
        assertEquals(100, (int) list.get(1));
        assertEquals(2, (int) list.get(2));
        assertEquals(3, (int) list.get(3));
    }

    @Test
    void testAddFirst() {
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);

        assertEquals(3, list.size());
        assertEquals(3, (int) list.get(0));
        assertEquals(2, (int) list.get(1));
        assertEquals(1, (int) list.get(2));
    }

    @Test
    void testAddLast() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        assertEquals(3, list.size());
        assertEquals(1, (int) list.get(0));
        assertEquals(2, (int) list.get(1));
        assertEquals(3, (int) list.get(2));
    }

    @Test
    void testGet() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(1, (int) list.get(0));
        assertEquals(2, (int) list.get(1));
        assertEquals(3, (int) list.get(2));
    }

    @Test
    void testSet() {
        list.add(1);
        list.add(2);
        list.add(3);

        list.set(1, 100);

        assertEquals(3, list.size());
        assertEquals(1, (int) list.get(0));
        assertEquals(100, (int) list.get(1));
        assertEquals(3, (int) list.get(2));
    }

    @Test
    void testRemove() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(3, list.size());
        assertEquals(1, list.remove(0));
        assertEquals(3, list.remove(1));
        assertEquals(2, list.remove(0));

        assertEquals(0, list.size());
    }

    @Test
    void testRemoveFirst() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(3, list.size());
        assertEquals(1, (int) list.removeFirst());
        assertEquals(2, list.size());
        assertEquals(2, (int) list.get(0));
        assertEquals(3, (int) list.get(1));
    }

    @Test
    void testRemoveLast() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(3, list.size());
        assertEquals(3, (int) list.removeLast());
        assertEquals(2, list.size());
        assertEquals(1, (int) list.get(0));
        assertEquals(2, (int) list.get(1));
    }

    @Test
    void testClear() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(3, list.size());
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.add(1);
        assertFalse(list.isEmpty());
    }

    @Test
    void testIndexOf() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(0, list.indexOf(1));
        assertEquals(1, list.indexOf(2));
        assertEquals(2, list.indexOf(3));
    }

    @Test
    void testLastIndexOf() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(2);

        assertEquals(3, list.lastIndexOf(2));
        assertEquals(0, list.lastIndexOf(1));
        assertEquals(2, list.lastIndexOf(3));
    }

    @Test
    void testRemoveObject() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(2);

        assertTrue(list.remove(Integer.valueOf(2)));
        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(2, list.get(2));
    }

    @Test
    void testToArray() {
        list.add(1);
        list.add(2);
        list.add(3);

        Object[] array = list.toArray(new Object[0]);
        Object[] expected = new Object[]{1, 2, 3};
        assertArrayEquals(expected, array);
    }

    @Test
    void testToArrayWithArray() {
        list.add(1);
        list.add(2);
        list.add(3);

        Object[] largeArray = new Object[5];
        Object[] resultLarge = list.toArray(largeArray);
        assertSame(largeArray, resultLarge);
        assertEquals(5, largeArray.length);

        assertEquals(Integer.valueOf(1), largeArray[0]);
        assertEquals(Integer.valueOf(2), largeArray[1]);
        assertEquals(Integer.valueOf(3), largeArray[2]);
        assertNull(largeArray[3]);

        Object[] smallArray = new Object[1];
        Object[] resultSmall = list.toArray(smallArray);
        assertNotSame(smallArray, resultSmall);
        assertEquals(3, resultSmall.length);
        assertEquals(Integer.valueOf(1), resultSmall[0]);
    }

    @Test
    void testGetFirst() {
        list.add(10);
        list.add(20);
        assertEquals(10, (int) list.getFirst());
    }

    @Test
    void testGetLast() {
        list.add(10);
        list.add(20);
        assertEquals(20, (int) list.getLast());
    }

    @Test
    void testListIterator() {
        assertNull(list.listIterator());
    }

    @Test
    void testListIteratorWithIndex() {
        assertNull(list.listIterator(0));
    }

    @Test
    void testSubList() {
        List<Integer> sub = list.subList(0, 0);
        assertNotNull(sub);
        assertTrue(sub.isEmpty());
    }

    @Test
    void testSpliterator() {
        assertNotNull(list.spliterator());
    }

    @Test
    void testStream() {
        list.add(1);
        Stream<Integer> stream = list.stream();
        assertNotNull(stream);
    }

    @Test
    void testParallelStream() {
        list.add(1);
        Stream<Integer> pStream = list.parallelStream();
        assertNotNull(pStream);
    }

    @Test
    void testReversed() {
        assertNotNull(list.reversed());
    }

    @Test
    void testContains() {
        list.add(1);
        assertFalse(list.contains(1));
    }

    @Test
    void testIterator() {
        assertNull(list.iterator());
    }

    @Test
    void testForEach() {
        list.add(5);
        List<Integer> result = new ArrayList<>();

        list.forEach(element -> result.add((Integer) element));

        assertEquals(1, result.size());
        assertEquals(Integer.valueOf(5), result.get(0));
    }

    @Test
    void testAddAll() {
        assertFalse(list.addAll(List.of(1, 2)));
    }

    @Test
    void testAddAllWithIndex() {
        assertFalse(list.addAll(0, List.of(1, 2)));
    }

    @Test
    void testRemoveIf() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertTrue(list.removeIf(n -> ((Integer) n) > 1));

        assertEquals(1, list.size());
        assertEquals(1, (int) list.get(0));
    }

    @Test
    void testReplaceAll() {
        list.add(1);
        list.add(2);

        list.replaceAll(n -> ((Integer) n) * 2);

        assertEquals(2, list.size());
        assertEquals(2, (int) list.get(0));
        assertEquals(4, (int) list.get(1));
    }

    @Test
    void testSort() {
        list.add(3);
        list.add(1);
        list.add(2);

        list.sort(Comparator.naturalOrder());

        assertEquals(3, list.size());
        assertEquals(1, (int) list.get(0));
        assertEquals(2, (int) list.get(1));
        assertEquals(3, (int) list.get(2));
    }

    @Test
    void testRetainAll() {
        assertFalse(list.retainAll(List.of(1)));
    }

    @Test
    void testRemoveAll() {
        assertFalse(list.removeAll(List.of(1)));
    }
}
