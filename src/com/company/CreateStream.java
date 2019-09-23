package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CreateStream {
    // Способи створення стрімів
    private static void testBuildStream() throws Exception {
//        System.out.println("Test buildStream start");
//
//        // створення стріму із значень
//        Stream<String> streamFromValues = Stream.of("a1", "a2", "a3");
//        System.out.println("streamFromValues = " + streamFromValues.collect(Collectors.toList())); // виведе streamFromValues = [a1, a2, a3]

//        // створення стріму із массиву
//        String[] array = {"a1","a2","a3"};
//        Stream<String> streamFromArrays = Arrays.stream(array);
//        System.out.println("streamFromArrays = " + streamFromArrays.collect(Collectors.toList())); // виведе streamFromArrays = [a1, a2, a3]
//
//        Stream<String> streamFromArrays_1 = Stream.of(array);
//        System.out.println("streamFromArrays_1 = " + streamFromArrays_1.collect(Collectors.toList()));


//        // створення стріму із файлу (кожен запис у файлі буде окремим рядком у стрімі)
//        File file = new File("file_1.tmp");
//        file.deleteOnExit();
//        PrintWriter out = new PrintWriter(file);
//        out.println("a1");
//        out.println("a2");
//        out.println("a3");
//        out.close();
//
//        Stream<String> streamFromFiles = Files.lines(Paths.get(file.getAbsolutePath()));
//        System.out.println("streamFromFiles = " + streamFromFiles.collect(Collectors.toList())); // виведе streamFromFiles = [a1, a2, a3]
//

//        // створення стріму з колекції
        Collection<String> collection = Arrays.asList("a1", "a2", "a3");
        Stream<String> streamFromCollection = collection.stream();
        System.out.println("streamFromCollection = " + streamFromCollection.collect(Collectors.toList())); // виведе streamFromCollection = [a1, a2, a3]

//        // створення числового стріму з рядка
//        IntStream streamFromString = "123".chars();
//        System.out.print("streamFromString = ");
//        streamFromString.forEach(e -> System.out.print(e + ", ")); // виведе streamFromString = 49 , 50 , 51 ,
//        System.out.println();

//        // з допомогою Stream.builder
//        Stream.Builder<String> builder = Stream.builder();
//        Stream<String> streamFromBuilder = builder.add("a1").add("a2").add("a3").build();
//        System.out.println("streamFromBuilder = " + streamFromBuilder.collect(Collectors.toList())); // виведе streamFromFiles = [a1, a2, a3]

//        // створення безкінечних стрімів
//        // з допомогою Stream.iterate
//        Stream<Integer> streamFromIterate= Stream.iterate(1, n -> n + 2);
//        System.out.println("streamFromIterate = " + streamFromIterate.limit(5).collect(Collectors.toList())); // виведе streamFromIterate = [1, 3, 5, 7, 9]

//        // з допомогою Stream.generate
//        Stream<String> streamFromGenerate = Stream.generate(() -> "a1");
//        System.out.println("streamFromGenerate = " + streamFromGenerate.limit(5).collect(Collectors.toList())); // виведе streamFromGenerate = [a1, a1, a1, a1, a1]

//        // створення пустого стріму
//        Stream<String> streamEmpty = Stream.empty();
//        System.out.println("streamEmpty = " + streamEmpty.collect(Collectors.toList())); // виведе streamEmpty = []
//
//        // створить паралельний стрім з колекції
//        Stream<String> parallelStream = collection.parallelStream();
//        System.out.println("parallelStream = " + parallelStream.collect(Collectors.toList())); // виведе parallelStream = [a1, a2, a3]

//  створить стрім з списку файлів по визначеному шляху
        Stream<Path> streamFromPath = Files.list(Paths.get(""));
        System.out.println("streamFromPath = " + streamFromPath.collect(Collectors.toList())); // print list of files

        //  створить стрім з найдених файлів
        Stream<Path> streamFromFind = Files.find(Paths.get(""), 10, (p,a) -> true);
        System.out.println("streamFromFind = " + streamFromFind.collect(Collectors.toList())); // print list of files

        // створить стрім з дерева файлів
        Stream<Path> streamFromFileTree = Files.walk(Paths.get(""));
        System.out.println("streamFromFileTree = " + streamFromFileTree.collect(Collectors.toList())); // print list of files

        // створить стрім з RegExp pattern'a
        Stream<String> streamFromPattern = Pattern.compile(":")
                .splitAsStream("a1:a2:a3");
        System.out.println("streamFromPattern = " + streamFromPattern.collect(Collectors.joining(","))); // print a1,a2,a3

        // створить стрім з текстового файла використовуючи BufferedReader
        Path path = Files.write(Paths.get("./test.txt"), "test 1\ntest 2".getBytes()); // create temp file

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            Stream<String> streamFromBufferedReader = reader.lines();
            System.out.println("streamFromBufferedReader = " + streamFromBufferedReader.collect(Collectors.toList())); // print [test 1, test 2]
        }


    }


    public static void main(String[] args)  throws Exception {
        testBuildStream();
    }
}
