package library.example.models;

import library.example.Interfaces.IntegerList;
import library.example.exception.ElementNotFoundException;
import library.example.exception.InvalidIndexException;
import library.example.exception.StorageIsFullException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private Integer[] list;
    private int size;

    public IntegerListImpl(int initialCapacity) {
        this.list = new Integer[initialCapacity];
    }

    public IntegerListImpl() {
        this.list = new Integer[10];
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= list.length) {
            throw new InvalidIndexException("Вы вышли за рамки массива");
        }
    }

    private void validatesItem(Integer item) {
        if (item == null) {
            throw new ElementNotFoundException("Элемент не найден");
        }
    }

    private void ensureCapacity() {
        if (size == list.length) {
            throw new StorageIsFullException();
        }
    }

    private void grow() {
        if (size >= list.length ) {
            list = Arrays.copyOf(list, (int) (list.length * 1.5));
        }
    }

    @Override
    public Integer add(Integer item) {
        grow();
        validatesItem(item);
        list[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        validatesItem(item);
        validateIndex(index);
        ensureCapacity();
        list[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        validatesItem(item);
        validateIndex(index);
        ensureCapacity();
        list[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
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
    public Integer remove(int index) {
        validateIndex(index);
        Integer removedIndex = list[index];
        int moved = size - index - 1;
        if (moved > 0) {
            System.arraycopy(list, index + 1, list, index, moved);
        }
        size--;
        list[moved + 1] = null;
        return removedIndex;
    }

    public void sort(Integer[] list) {
        for (int i = 1; i < list.length; i++) {
            int temp = list[i];
            int j = i;
            while (j > 0 && list[j - 1] >= temp) {
                list[j] = list[j - 1];
                j--;
            }
            list[j] = temp;
        }
    }

    public static void swapElement(Integer[] arr, int left, int right) {
        Integer temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    @Override
    public void quickSort(Integer[] arr, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(arr, left, right);
            quickSort(arr, left, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, right);
        }
    }

    public static int partition(Integer[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                i++;
                swapElement(arr, i, j);
            }
        }
        swapElement(arr, i + 1, right);
        return i + 1;
    }

    @Override
    public boolean contains(Integer item) {
        Integer[] storageArray = toArray();
        sort(storageArray);
        return binarySearch(storageArray, item);
    }

    private boolean binarySearch(Integer[] list, Integer element) {
        int min = 0;
        int max = list.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (element.equals(list[mid])) {
                return true;
            }
            if (element < list[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        validatesItem(item);
        for (int i = 0; i < size; i++) {
            if (item.equals(list[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        validatesItem(item);
        for (int i = size - 1; i >= 0; i--) {
            if (item.equals(list[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        return list[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return list.length;
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
    public Integer[] toArray() {
        return new Integer[0];
    }

}
