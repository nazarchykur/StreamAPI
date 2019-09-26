package com.company;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;

public class MinMaxTest {
    // Метод max поверне максимальний елемент, як умову використовує компаратор
    // Метод min поверне мінімальний елемент, як умову використовує компаратор

    private static void methodMinMax() {
        // ************ Робота з рядками
        Collection<String> collection = Arrays.asList("a1", "a2", "a3", "a1");

        // знайти максимальне значення
        String max = collection.stream()
                .max(String::compareTo).get();
        System.out.println("max = " + max); // print max = a3

        // знайти мінімальне значення
        String min = collection.stream()
                .min(String::compareTo).get();
        System.out.println("min = " + min); // print min = a1


        // Get Min or Max Number
        Integer maxNumber = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .max(Comparator.comparing(Integer::valueOf))
                .get();

        Integer minNumber = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .min(Comparator.comparing(Integer::valueOf))
                .get();

        System.out.println("maxNumber = " + maxNumber); // print maxNumber = 9
        System.out.println("minNumber = " + minNumber); // print minNumber = 1

        // Get Min or Max String/Char
        String maxChar = Stream.of("B", "T", "D", "C", "X")
                .max(Comparator.comparing(String::valueOf))
                .get();

        String minChar = Stream.of("B", "T", "D", "C", "X")
                .min(Comparator.comparing(String::valueOf))
                .get();

        System.out.println("maxChar = " + maxChar); // print maxChar = X
        System.out.println("minChar = " + minChar); // print minChar = B



        Collection<People> people = Arrays.asList(
                new People("Alex", 16, Sex.MAN),
                new People("Brian", 23, Sex.MAN),
                new People("Alice", 42, Sex.WOMAN),
                new People("Bob", 69, Sex.MAN)
        );

        // знайти найстаршу людину
        People older = people.stream()
                // with lambda
//                .max((p1, p2) -> p1.getAge().compareTo(p2.getAge()))
                // with ref method
                .max(Comparator.comparing(People::getAge))
                .get();
        System.out.println("older = " + older); // print older = People{name='Bob', age=69, sex=MAN}

        // знайти наймолодшу людину
        People younger = people.stream()
//                .min((p1, p2) -> p1.getAge().compareTo(p2.getAge()))
                .min(Comparator.comparing(People::getAge))
                .get();
        System.out.println("younger " + younger); // print younger People{name='Alex', age=16, sex=MAN}

    }

    private enum Sex {
    MAN, WOMAN
    }
    private static class People {
        private final String name;
        private final Integer age;
        private final Sex sex;

        public People(String name, Integer age, Sex sex) {
            this.name = name;
            this.age = age;
            this.sex = sex;
        }

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }

        public Sex getSex() {
            return sex;
        }

        @Override
        public String toString() {
            return "People{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", sex=" + sex +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            People people = (People) o;
            return Objects.equals(name, people.name) &&
                    Objects.equals(age, people.age) &&
                    sex == people.sex;
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age, sex);
        }
    }

    public static void main(String[] args) {
        methodMinMax();
    }
}
