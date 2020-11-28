package com.company;

import java.util.Arrays;
import java.util.Collection;

public class TestStream {
    public static void main(String[] args) {
        Collection<String> collection = Arrays.asList("a1", "a2", "a3", "a1", null);

        // Повернути кількість входжень об'єкта
        long count = collection.stream().filter("a1"::equals).count();
        System.out.println("count = " + count); // print count = 2;
    }
}
