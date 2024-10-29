package library.example.models;

import library.example.Interfaces.StringList;
import library.example.exception.IndexIsNullException;
import library.example.exception.ElementNotFoundException;

import java.util.Arrays;

public class StringListImpl implements StringList {

    private String[] list;
    private int size = 0;

    public StringListImpl() {
        list = new String[0];
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= list.length) {
            throw new IndexIsNullException("Вы вышли за рамки массива");
        }
    }

    private void checkNull(String item) {
        if (item == null) {
            throw new  ElementNotFoundException("Элемент не найден");
        }
    }

    private void ensureCapacity() {
        if (size == list.length) {
            list = Arrays.copyOf(list, list.length + 1);
        }
    }

    public String[] getList() {
        return list;
    }

    @Override
    public String add(String item) {
        checkNull(item);
        ensureCapacity();
        list[size] = item;
        size++;
        return item;
    }

    @Override
    public String add(int index, String item) {
        checkNull(item);
        ensureCapacity();
        validateIndex(index);
        System.arraycopy(list, index, list, index + 1, size - index);
        list[index] = item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        checkNull(item);
        validateIndex(index);
        ensureCapacity();
        list[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        checkNull(item);
        int index = indexOf(item);
        if (index == -1) {
            throw new ElementNotFoundException("Элемент не найден");
        }
        for (int i = 0; i < list.length; i++) {
            if (item.equals(list[i])) {
                System.arraycopy(list, i + 1, list, i, list.length - i - 1);
                list[list.length - 1] = null;

            }
        }
        size--;
        return list[list.length - 1];
    }

    @Override
    public String remove(int index) {
        validateIndex(index);
        String removedIndex = list[index];
        int moved = size - index - 1;
        if (moved > 0) {
            System.arraycopy(list, index + 1, list, index, moved);
        }
        size--;
        list[moved + 1] = null;
        return removedIndex;
    }

    @Override
    public boolean contains(String item) {
        for (String element : list) {
            if (item.equals(element)) {
                return true;
            }
        }
        throw new ElementNotFoundException("Элемент не найден");
    }

    @Override
    public int indexOf(String item) {
        checkNull(item);
        for (int i = 0; i < size; i++) {
            if (item.equals(list[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = size - 1; i >= 0; i--) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        if(list[index] != null) {
            return list[index];
        } else {
            throw new IndexIsNullException("Индекс не найден");
        }
    }

    @Override
    public boolean equals(StringList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
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
    public void clear() {
        size = 0;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(list, size);
    }
}
