package com.company;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.IntStream;

public class ReduceTest {
    // Метод reduce дозволяє виконувати агрегатні функції
    // над всією колекцією (такі як сума, знаходження мінімального або максимального значення і т.п.)
    // Він повертає одне Optional значення
          // map - перетворює один об'єкт в інший (наприклад, клас одного тип в інший)
          // mapToInt - перетворення об'єктів в числовий стрім (стрім, що складається з значень int)

    private static void methodReduce() {
        // ************ Робота з числовими об'єктами
        Collection<Integer> collection = Arrays.asList(1, 2, 3, 4, 2);

        // Повернути суму
        Integer sum = collection.stream()
                // with Stream API
                .reduce((s1, s2) -> s1 + s2).orElse(0);

                // old way
         Integer sumOld = 0;
         for(Integer i : collection){
             sumOld += i;
         }
        System.out.println("sum = " + sum); // print sum = 12
        System.out.println("sumOld = " + sumOld); // print sumOld = 12


        // **************** Повернути максимум

        // через stream Api
        Integer max1 = collection.stream()
                .reduce((s1, s2) -> s1 > s2 ? s1 : s2).orElse(0);

        // через stream Api reference method Integer::max
        Integer max2 = collection.stream()
                .reduce(Integer::max).orElse(0);

        //old way
        Integer maxOld = null;
        for(Integer i: collection) {
            maxOld = maxOld != null && maxOld > i ? maxOld : i;
        }
        maxOld = maxOld == null? 0 : maxOld;
        System.out.println("max1 = " + max1); // print max1 = 4
        System.out.println("max2 = " + max2); // print max2 = 4
        System.out.println("maxOld = " + maxOld); // print maxOld = 4



        // ******************** Повернути мінімум
        Integer min1 = collection.stream().reduce((s1, s2) -> s1 < s2 ? s1 : s2).orElse(0);

        // через stream Api
        Integer min2 = collection.stream().reduce(Integer::min).orElse(0);

        Integer minOld = null; // по старому методу
        for(Integer i: collection) {
            minOld = minOld != null && minOld < i? minOld: i;
        }
        minOld = minOld == null? 0 : minOld;
        System.out.println("min1 = " + min1); // print min1 = 1
        System.out.println("min2 = " + min2); // print min2 = 1
        System.out.println("minOld = " + minOld); // print minOld = 1


        // **************** Повернути останній елемент
        // через stream Api
        Integer last = collection.stream().reduce((s1, s2) -> s2).orElse(0);

        Integer lastOld = null; // по старому методу
        for(Integer i: collection) {
            lastOld = i;
        }
        lastOld = lastOld == null? 0 : lastOld;
        System.out.println("last = " + last); // print last = 2
        System.out.println("lastOld = " + lastOld); // print lastOld = 2

        // ************* Повернути суму чисел, які більше 3
        Integer sumMore3 = collection.stream()
                .filter(s -> s > 3)
//                .reduce(Integer::sum).orElse(0);
                .reduce((s1, s2) -> s1 + s2).orElse(0);

        // по старому методу
        Integer sumMore3old = 0;
        for(Integer i: collection) {
            if(i > 3) {
                sumMore3old += i;
            }
        }
        System.out.println("sumMore3 = " + sumMore3); // print sumMore3 = 4
        System.out.println("sumMore3old = " + sumMore3old); // print sumMore3old = 4

        // ******************* Повернути суму непарних чисел
        // через stream Api
        Integer sumOdd = collection.stream()
                .filter(i -> i % 2 != 0)
//                .reduce(Integer::sum).orElse(0);
                .reduce((s1, s2) -> s1 + s2).orElse(0);

        // по старому методу
        Integer sumOddOld = 0;
        for(Integer i: collection) {
            if(i % 2 != 0) {
                sumOddOld += i;
            }
        }
        System.out.println("sumOdd = " + sumOdd); // print sumOdd = 4
        System.out.println("sumOddOld = " + sumOddOld); //  print sumOddOld = 4


        // ************ Робота зі складними об'єктами
        // створимо колекцію людей
        Collection<People> people = Arrays.asList(
                new People("Leo", 20, Sex.MAN),
                new People("Donnie", 25, Sex.MAN),
                new People("MJey", 18, Sex.WOMAN),
                new People("OldMan", 70, Sex.MAN),
                new People("Nata", 26, Sex.WOMAN)
        );

        // Знайдемо найстаршого чоловіка
        int oldMan = people.stream()
                .filter(p -> p.getSex() == Sex.MAN)
                .map(People::getAge)
                .reduce((s1, s2) -> s1 > s2 ? s1 : s2)
                .get();
        System.out.println("oldMan = " + oldMan); // print oldMan = 70

        // Знайдемо наймолодшу людину у якої є буква "а" в імені
        int younger = people.stream()
                .filter(p -> p.getName().contains("a"))
                .mapToInt(People::getAge)
                //.reduce((p1, p2) -> p1 < p2 ? p1 : p2)
//                .reduce((p1, p2) -> Math.min(p1, p2))
                .reduce(Math::min)
                .orElse(0);
        System.out.println("younger = " + younger); // print younger = 26


        // To get the product of all elements
        // in given range excluding the
        // rightmost element
        int product = IntStream.range(2, 8)
                .reduce((num1, num2) -> num1 * num2)
                .orElse(-1);

        // Displaying the product
        System.out.println("The product is : " + product); //The product is : 5040

    }

    private enum Sex{
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
                    Objects.equals(sex, people.sex);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age, sex);
        }
    }

    public static void main(String[] args) {
        methodReduce();
    }
}
