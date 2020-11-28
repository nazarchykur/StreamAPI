package com.company;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ForEachAndPeekTest {

    //  ForEach і Peek по суті роблять одне і теж,
    //  змінюють властивості об'єктів в Stream,
    //  єдина різниця між ними в тому що ForEach термінальна і вона закінчує роботу зі стрімом,
    //  в той час як Peek конвеєрна і робота зі стрімом триває.

    // Метод ForEach застосовує вказаний метод до кожного елементу стріму і закінчує роботу зі стрімом

    private static void methodForEach() {
        System.out.println("For each start");
        Collection<String> collection = Arrays.asList("a1", "a2", "a3", "a1");

        // через уикл
        for (String s : collection) {
            String e = s.toUpperCase();
            System.out.print(e + ",");
        }
        // вивести інфо по кожному елементі стріму
        System.out.print("forEach = ");
        collection.stream()
//                .map(s -> s.toUpperCase()) // lambda
                .map(String::toUpperCase)
                .forEach((e) -> System.out.print(e + ",")); // print forEach = A1,A2,A3,A1,

        System.out.println();

        Collection<StringBuilder> list = Arrays.asList(
                new StringBuilder("a1"),
                new StringBuilder("a2"),
                new StringBuilder("a3")
        );
        list.stream()
                .forEachOrdered((p) -> p.append("_new"));
        System.out.println("forEachOrdered = " + list); // print forEachOrdered = [a1_new, a2_new, a3_new]
    }

    // Метод Peek повертає той же стрім, але при цьому застосовує вказаний метод до кожного елементу стріму
    private static void methodPeek() {
        System.out.println();
        System.out.println("Test peek start");
        Collection<String> collection = Arrays.asList("a1", "a2", "a3", "a1");

        // напечатає дебажне інфо по кожному елементі стріма
        System.out.print("peak1 = ");
        List<String> peek = collection.stream()
                .map(String::toUpperCase)
                .peek(e -> System.out.print(e + ","))
                .collect(Collectors.toList());
        System.out.println();                 // print peak1 = A1,A2,A3,A1,
        System.out.println("peek = " + peek); // print peek = [A1, A2, A3, A1]

        Collection<StringBuilder> list = Arrays.asList(
                new StringBuilder("a1"),
                new StringBuilder("a2"),
                new StringBuilder("a3")
        );
        List<StringBuilder> newList = list.stream()
                .peek( p -> p.append("_new") )
                .collect(Collectors.toList());
        System.out.println("newList = " + newList); // print newList = [a1_new, a2_new, a3_new]
    }


    public static void main(String[] args) throws Exception {
        methodForEach();
        methodPeek();
    }
}
