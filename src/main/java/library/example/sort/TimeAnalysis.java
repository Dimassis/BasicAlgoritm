package library.example.sort;

import library.example.models.IntegerListImpl;

import java.util.Arrays;

public class TimeAnalysis {
    public static void main(String[] args) {
        Integer[] randomArray = new Integer[10];
        for (int i = 0; i < randomArray.length; i++) {
            randomArray[i] = (int) (Math.random() * 10);
        }

        Integer[] BubbleSort = Arrays.copyOf(randomArray, randomArray.length);
        Integer[] SelectedSort = Arrays.copyOf(randomArray, randomArray.length);
        Integer[] InsertionSort = Arrays.copyOf(randomArray, randomArray.length);
        Integer[] InsertionSort2 = Arrays.copyOf(randomArray, randomArray.length);
        // Insertion Sort
        long start = System.currentTimeMillis();
        SortGenerator.sortInsertion(InsertionSort);
        System.out.println("Insertion: " + (System.currentTimeMillis() - start) + " ms");


        // Bubble Sort
        start = System.currentTimeMillis();
        SortGenerator.sortBubble(BubbleSort);
        System.out.println("Bubble: " + (System.currentTimeMillis() - start) + " ms");
        // Selected Sort
        start = System.currentTimeMillis();
        SortGenerator.sortSelected(SelectedSort);
        System.out.println("Selected: " + (System.currentTimeMillis() - start) + " ms");


    }
}

