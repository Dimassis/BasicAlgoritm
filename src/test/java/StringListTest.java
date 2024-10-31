import library.example.Interfaces.StringList;
import library.example.exception.ElementNotFoundException;
import library.example.exception.ValidateNullException;
import library.example.models.StringListImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

public class StringListTest {

    StringList list;

    @Before
    public void setUp() throws Exception {
        list = new StringListImpl();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
    }

    @Test
    public void testAddItem() {
        String expected = "L";
        list.add(expected);
        assertEquals(expected, list.get(4));
        assertThrows(ElementNotFoundException.class, () -> list.add(null));
    }

    @Test
    public void testAddIndex() {
        String expected = "Z";
        list.add(1, expected);
        assertEquals(expected, list.get(1));
        assertThrows(ElementNotFoundException.class, () -> list.add(2, null));
        assertThrows(ValidateNullException.class, () -> list.add(14, "Z"));
    }

    @Test
    public void testRemoveItem() {
        String expected = "D";
        list.add(expected);
        assertEquals(expected, list.remove(4));
        assertThrows(ElementNotFoundException.class, () -> list.remove("s"));
    }

    @Test
    public void testRemoveIndex() {
        String expected = "Z";
        list.add(1, expected);
        assertEquals(expected, list.remove(1));
        assertThrows(ValidateNullException.class, () -> list.remove(14));
    }

    @Test
    public void testSet() {
        list.set(0, "M");
        assertEquals("M", list.get(0));
        assertThrows(ElementNotFoundException.class, () -> list.set(1, null));
        assertThrows(ValidateNullException.class, () -> list.set(14, "Z"));
    }

    @Test
    public void testContains() {
        list.add("K");
        assertTrue(list.contains("K"));
        assertThrows(ElementNotFoundException.class, () -> list.contains("Z"));
    }

    @Test
    public void testIndexOf() {
        list.add("P");
        assertEquals(4, list.indexOf("P"));
        assertEquals(-1, list.indexOf("Z"));
    }

    @Test
    public void testLastIndexOf() {
        list.add("P");
        list.add("Z");
        assertEquals(4, list.lastIndexOf("P"));
        assertEquals(5, list.lastIndexOf("Z"));
        assertEquals(-1, list.lastIndexOf("Zz"));
    }

    @Test
    public void testGet() {
        assertEquals("A", list.get(0));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> list.get(23));
    }

    @Test
    public void testSize() {
        assertEquals(4, list.size());
        list.add("A");
        assertEquals(5, list.size());
    }

    @Test
    public void testIsEmpty() {
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testClear() {
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testToArray() {
        StringList list2 = new StringListImpl();
        list2.add("A");
        list2.add("B");
        list2.add("C");
        list2.add("D");
        assertArrayEquals(list.toArray(), list2.toArray());

    }

}
