package com.company;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class SortedTest {

    // Метод Sorted дозволяє сортувати значення
    // або в натуральному порядку, або задаючи Comparator
    public void testSorted() {
        System.out.println("Test sorted start");

        // ************ Робота з рядками
        Collection<String> collection = Arrays.asList("a1", "a4", "a3", "a2", "a1", "a4");

        // впорядкувати значення за алфавітом
        List<String> sorted = collection.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("sorted = " + sorted); // print sorted = [a1, a1, a2, a3, a4, a4]


        // впорядкувати значення за алфавітом і прибрати дублікати
        List<String> sortedDistinct = collection.stream()
                .sorted()
                .distinct()                   // don't print duplicate
                .collect(Collectors.toList());
        System.out.println("sortedDistinct = " + sortedDistinct); // print sortedDistinct = [a1, a2, a3, a4]

        // впорядкувати значення за алфавітом у зворотному порядку
        List<String> sortedReverse = collection.stream()
//                .sorted((o1, o2) -> -o1.compareTo(o2))
                .sorted((o1, o2) -> o2.compareTo(o1))
                .collect(Collectors.toList());
        System.out.println("sortedReverse = " + sortedReverse); // print sortedReverse = [a4, a4, a3, a2, a1, a1]

        List<String> sortedReverse_1 = collection.stream()
                //  Comparator.reverseOrder() = (o1, o2) -> -o1.compareTo(o2)
                // Comparator.reverseOrder() = (o1, o2) -> o2.compareTo(o1)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println("sortedReverse_1 = " + sortedReverse_1);
        // print the sane result
        // sortedReverse_1 = [a4, a4, a3, a2, a1, a1]


        // ************ Робота з об'єктами
        // створимо колекцію людей
        Collection<People> people = Arrays.asList(
                new People("Leo", 20, Sex.MAN),
                new People("Donnie", 25, Sex.MAN),
                new People("MJey", 18, Sex.WOMAN),
                new People("OldMan", 70, Sex.MAN)
        );

        // Фільтр по імені в зворотному алфавітному порядку
        Collection<People> byName = people.stream()
                //.sorted((o1, o2) -> o1.getName().compareTo(o2.getName())) // with lambda
                .sorted(Comparator.comparing(People::getName)) // with ref func
                .collect(Collectors.toList());
        System.out.println("byName = " + byName);
        // byName = [People{name='Donnie', age=25, sex=MAN}, People{name='Leo', age=20, sex=MAN}, People{name='MJey', age=18, sex=WOMAN}, People{name='OldMan', age=70, sex=MAN}]

        // відсортувати за віком
        List<People> sortedByAge = people.stream()
//                .sorted((p1, p2) -> p1.getAge() - p2.getAge())
                .sorted(Comparator.comparingInt(People::getAge))
                .collect(Collectors.toList());
        System.out.println("sortedByAge = " + sortedByAge);
        // print sortedByAge = [People{name='MJey', age=18, sex=WOMAN}, People{name='Leo', age=20, sex=MAN}, People{name='Donnie', age=25, sex=MAN}, People{name='OldMan', age=70, sex=MAN}]

        // відсортувати за віком у зворотньому порядку
        List<People> sortedByAgeReverse = people.stream()
                .sorted(Comparator.comparingInt(People::getAge).reversed())
                .collect(Collectors.toList());
        System.out.println("sortedByAgeReverse = " + sortedByAgeReverse);
        // print sortedByAgeReverse = [People{name='OldMan', age=70, sex=MAN}, People{name='Donnie', age=25, sex=MAN}, People{name='Leo', age=20, sex=MAN}, People{name='MJey', age=18, sex=WOMAN}]

        // Відсортувати спочатку за статтю, а потім за віком
        Collection<People> bySexAndAge = people.stream()
                // using lambda
                //.sorted( (o1, o2)-> o1.getSex() != o2.getSex() ? o1.getSex().compareTo(o2.getSex()) : o1.getAge().compareTo(o2.getAge()))

                // using ref func
                // спочатку порівнюємо по статті, а потім за віком
                // використовуємо thenComparing щоб зберегти порядок і логіку сортування
                .sorted(Comparator.comparing(People::getSex).thenComparing(People::getAge))
                .collect(Collectors.toList());
        System.out.println("bySexAndAge = " + bySexAndAge);
        // print bySexAndAge = [People{name='Leo', age=20, sex=MAN}, People{name='Donnie', age=25, sex=MAN}, People{name='OldMan', age=70, sex=MAN}, People{name='MJey', age=18, sex=WOMAN}]

    }



    private enum Sex {
        MAN,
        WOMAN
    }

    private class People {

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
        SortedTest sortedTest = new SortedTest();
        sortedTest.testSorted();

    }

}
