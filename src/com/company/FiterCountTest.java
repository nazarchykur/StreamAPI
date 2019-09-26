package com.company;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FiterCountTest {

// filter - повертає stream, в якому є тільки елементи, що відповідають умові фільтра
// count - повертає кількість елементів в Стрімі
// collect - перетворює stream в колекцію або іншу структуру даних
// mapToInt - перетворити об'єкт в числовий стрім (стрім, що містить числа)

    private static void testFilterCount() {
        System.out.println();

        System.out.println("Test filter and count start");

        // ************ Робота з рядками
        Collection<String> collection = Arrays.asList("a1", "a2", "a3", "a1");

        // Повернути кількість входжень об'єкта
        long count = collection.stream().filter("a1"::equals).count();
        System.out.println("count = " + count); // print count = 2;

        // вибрати всі елементи по шаблону
        List<String> select = collection.stream().filter(s -> s.contains("1")).collect(Collectors.toList());
        System.out.println("select = " + select); // print select = [a1, a1]


        // ************ Робота зі складними об'єктами
        // створимо колекцію людей
        Collection<People> people = Arrays.asList(
                new People("Leo", 20, Sex.MAN),
                new People("Doni", 25, Sex.MAN),
                new People("MJey", 18, Sex.WOMEN),
                new People("OldMan", 70, Sex.MAN)
        );

        // Вибрати чоловіків-военообязанних
        List<People> militaryService = people
                .stream()
                .filter(p -> p.getAge() >= 18 && p.getAge() < 27 && p.getSex() == Sex.MAN)
                .collect(Collectors.toList());
        System.out.println("militaryService = " + militaryService); // print militaryService = [People{name='Leo', age=20, sex=MAN}, People{name='Doni', age=25, sex=MAN}]

        // Знайти середній вік серед чоловіків
        double manAverageAge = people
                .stream()
                .filter(p -> p.getSex() == Sex.MAN)
                .mapToInt(People::getAge)
                .average()
                .getAsDouble();
        System.out.println("manAverageAge = " + manAverageAge); // print manAverageAge = 38.333333333333336


        // Знайти кількість потенційно работоздатних людей у вибірці (тобто від 18 років і з огляду на що жінки виходять в 55 років, а чоловік в 60 на пенсію)
        long peopleWhoCanWork = people
                .stream()
                .filter(p -> p.getAge() >= 18)
                .filter(p -> (p.getSex() == Sex.WOMEN && p.getAge() < 55) || (p.getSex() == Sex.MAN && p.getAge() < 60)).count();
        System.out.println("peopleWhoCanWork = " + peopleWhoCanWork); // print peopleWhoCanWork = 3
    }

    private enum Sex {
        MAN,
        WOMEN
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
    }

    public static void main(String[] args) {
        testFilterCount();
    }


}
