package library.example.models;

import library.example.Interfaces.StringList;
import library.example.exception.InvalidIndexException;
import library.example.exception.StorageIsFullException;
import library.example.exception.ValidateNullException;
import library.example.exception.ElementNotFoundException;

import java.util.Arrays;


public class StringListImpl implements StringList {

    private final String[] list;
    private int size;

    public StringListImpl() {
        list = new String[10];
    }

    public StringListImpl(int initSize) {
        list = new String[initSize];
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= list.length) {
            throw new InvalidIndexException("Вы вышли за рамки массива");
        }
    }

    private void validatesItem(String item) {
        if (item == null) {
            throw new  ElementNotFoundException("Элемент не найден");
        }
    }

    private void ensureCapacity() {
        if (size == list.length) {
            throw new StorageIsFullException();
        }
    }



    public String[] getList() {
        return list;
    }

    @Override
    public String add(String item) {
        validatesItem(item);
        ensureCapacity();
        list[size] = item;
        size++;
        return item;
    }

    @Override
    public String add(int index, String item) {
        validatesItem(item);
        ensureCapacity();
        validateIndex(index);
        System.arraycopy(list, index, list, index + 1, size - index);
        list[index] = item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        validatesItem(item);
        validateIndex(index);
        ensureCapacity();
        list[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        validatesItem(item);
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
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        validatesItem(item);
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
            throw new ValidateNullException("Индекс не найден");
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