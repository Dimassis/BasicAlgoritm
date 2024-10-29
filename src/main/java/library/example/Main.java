package library.example;

import library.example.Interfaces.StringList;
import library.example.models.StringListImpl;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        StringList list = new StringListImpl();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        list.add("F");
        System.out.println(Arrays.toString(list.toArray()));
        list.add(1,"2");
        System.out.println(Arrays.toString(list.toArray()));
        list.remove("B");
        System.out.println(Arrays.toString(list.toArray()));
        list.remove(1);
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(list.indexOf("F"));
        System.out.println(list.lastIndexOf("C"));
        list.add("C");
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(list.lastIndexOf("C"));
        System.out.println(list.get(5));

    }
}