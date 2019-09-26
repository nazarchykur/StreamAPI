package com.company;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class LimitSkipTest {
// Метод Limit дозволяє обмежити вибірку певною кількістю перших елементів
private static void testLimit() {

    Collection<String> collection = Arrays.asList("a1", "a2", "a3", "a1");

    // Повернути перші два елементи
    List<String> limit = collection.stream()
            .limit(2)
            .collect(Collectors.toList());

    System.out.println("limit = " + limit); // print limit = [a1, a2]

    // Повернути два елементи починаючи з другого
    List<String> skipTo = collection.stream()
            .skip(1)
            .limit(2)
            .collect(Collectors.toList());
    System.out.println("skipTo = " + skipTo); // print skipTo = [a2, a3]

    // повернути останній елемент колекції
    String last = collection.stream()
            .skip(collection.size() - 1)
            .findAny().orElse("1");
    System.out.println("last = " + last ); // print last = a1
}

    public static void main(String[] args)  throws Exception {
        testLimit();
    }

}
