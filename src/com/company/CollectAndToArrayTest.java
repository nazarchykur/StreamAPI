package com.company;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CollectAndToArrayTest {


    // Метод collect перетворює stream в колекцію або іншу структуру даних
    // Корисні статичні методи з Collectors:
    // toList, toCollection, toSet - представляють стрім у вигляді списку, колекції або набору
    // toConcurrentMap, toMap - дозволяють перетворити стрім в map, використовуючи зазначені функції
    // averagingInt, averagingDouble, averagingLong - повертають середнє значення
    // summingInt, summingDouble, summingLong - повертає суму
    // summarizingInt, summarizingDouble, summarizingLong - повертають SummaryStatistics з різними агрегатними значеннями
    // partitioningBy - розділяє колекцію на дві частини які відповідають умовам і повертає їх як Map <Boolean, List>
    // groupingBy - розділити колекцію за умовою і повернути Map <N, List <T >>, де T - тип останнього стріму, N - значення роздільник
    // mapping - додаткові перетворення значень для складних Collector'ів

    private static void methodCollectAndToArray() {
        // ******** Робота з числами
        System.out.println(" ******** Робота з числами");
        Collection<Integer> numbers = Arrays.asList(1, 2, 3, 4);
// Строитель обычно представляет собой статический класс- член (раздел 4.10) класса, который он строит.
        Integer[] integers = numbers.stream().toArray(Integer[]::new);

        Integer[] integers_1 = numbers.toArray(new Integer[0]);

        System.out.println("integers = " + Arrays.toString(integers)); // integers = [1, 2, 3, 4]
        System.out.println("integers_1 = " + Arrays.toString(integers_1)); // integers_1 = [1, 2, 3, 4]

        Object[] objects = numbers.toArray();
        System.out.println("objects = " + Arrays.toString(objects)); // objects = [1, 2, 3, 4]

        int[] array_1 = numbers.stream().mapToInt(x -> x).toArray();
        System.out.println("array_1 = " + array_1); // array_1 = [I@723279cf
        System.out.println("array_1 = " + Arrays.toString(array_1)); // array_1 = [1, 2, 3, 4]

        int[] array_2 = IntStream.range(1, 5).toArray();
        System.out.println("array_2 = " + Arrays.toString(array_2)); // array_2 = [1, 2, 3, 4]

        int[] array_3 = IntStream.rangeClosed(1, 5).toArray();
        System.out.println("array_3 = " + Arrays.toString(array_3)); // array_3 = [1, 2, 3, 4, 5]


        // Отримати суму непарних чисел
        long sumOdd = numbers.stream()
                .collect(Collectors.summingInt(p -> p % 2 == 1 ? p : 0));
        System.out.println("sumOdd = " + sumOdd); // print sumOdd = 4

        long sumOdd_1 = numbers.stream().mapToInt(p -> p % 2 == 1 ? p : 0).sum();
        System.out.println("sumOdd_1 = " + sumOdd_1); // print sumOdd_1 = 4

        // відняти від кожного елемента 1 і здобути середнє значення
        double average = numbers.stream()
                .collect(Collectors.averagingInt((p) -> p - 1));
        System.out.println("average = " + average); // print average = 1.5

        double average_1 = numbers.stream()
                .collect(Collectors.averagingInt(p -> p));
        System.out.println("average_1 = " + average_1); // average_1 = 2.5

        // Додати до кожного числа 3 і отримати статистику
        IntSummaryStatistics statistics = numbers.stream()
                .collect(Collectors.summarizingInt(p -> p + 3));
        System.out.println("statistics = " + statistics);
        // print statistics = IntSummaryStatistics{count=4, sum=22, min=4, average=5.500000, max=7}


        // Отримати суму парних чисел через IntSummaryStatistics
        long sumEven = numbers.stream()
                .collect(Collectors.summarizingInt(p -> p % 2 == 0 ? p : 0))
                .getSum();

        // can take some other info :
        //                .getCount()
        //                .getAverage()
        //                .getMin()
        //                .getMax()
        System.out.println("sumEven = " + sumEven); // print sumEven = 6

        // Розділити числа на парні і непарні
        Map<Boolean, List<Integer>> parts = numbers.stream()
                .collect(Collectors.partitioningBy(p -> p % 2 == 0));
        System.out.println("parts = " + parts); // print parts = {false=[1, 3], true=[2, 4]}

        // кількість елементів
        long countOfNumbers = numbers.stream()
//                .collect(Collectors.counting());
                .count();
        System.out.println("countOfNumbers: " + countOfNumbers); // countOfNumbers: 4


        System.out.println();
        System.out.println("******** Робота з рядками");
        // ******** Робота з рядками
        Collection<String> strings = Arrays.asList("a1", "b2", "c3", "a1");


        String[] strings1 = Stream.of("one", "two", "three", "four")
                .filter(s -> s.startsWith("t"))
                .toArray(String[]::new);

        System.out.println("strings1 = " + Arrays.toString(strings1));


        // Отримання списку з колекції рядків без дублікатів
        List<String> distinct = strings.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("distinct = " + distinct); // print distinct = [a1, b2, c3]

        // Отримання масиву унікальних значень з колекції рядків
        String[] array = strings.stream()
                .distinct()
                .map(String::toUpperCase)
                .toArray(String[]::new);
        System.out.println("array = " + Arrays.asList(array)); // print array = [A1, B2, C3]

        // Об'єднати всі елементи в один рядок через роздільник : і обернути тегами <b> ... </ b>
        String join = strings.stream()
                //        .collect(Collectors.joining(" - "));    // join = a1 - b2 - c3 - a1
                .collect(Collectors.joining(" : ", "<b> ", " </b>"));

        System.out.println("join = " + join); // print <b> a1 : b2 : c3 : a1 </b>


        // Перетворити в map, де перший символ ключ, другий символ значення
        Map<String, String> map = strings.stream()
                .distinct()
                .collect(Collectors.toMap(p -> p.substring(0, 1), p -> p.substring(1, 2)));
        System.out.println("map = " + map); // print map = {a=1, b=2, c=3}

        // Перетворити в map, згрупувавши по першому символу рядка
        Map<String, List<String>> groups = strings.stream()
                .collect(Collectors.groupingBy(p -> p.substring(0, 1)));
        System.out.println("groups = " + groups); // print groups = {a=[a1, a1], b=[b2], c=[c3]}


        // Перетворити в map, згрупувавши по першому символу рядка і в якості значення взяти другий символ об'єднаємо через :
        Map<String, String> groupJoin = strings.stream()
                .collect(Collectors.groupingBy(p -> p.substring(0, 1),
                        Collectors.mapping(p -> p.substring(1, 2),
                                Collectors.joining(":")))
                );
        System.out.println("groupJoin = " + groupJoin); // print groupJoin = {a=1:1, b=2, c=3}


        // Напишемо власний Collector, який буде виконувати об'єднання рядків за допомогою StringBuilder
        Collector<String, StringBuilder, String> stringBuilderCollector = Collector.of(
                StringBuilder::new, // метод ініціалізації акумулятора
                (b, s) -> b.append(s).append(" , "), // метод обробки кожного елемента
                (b1, b2) -> b1.append(b2).append(" , "), // метод з'єднання двох акумуляторів при паралельному виконанні
                StringBuilder::toString // кінцевий метод
        );
        String joinBuilder = strings.stream()
                .collect(stringBuilderCollector);
        System.out.println("joinBuilder = " + joinBuilder); // print joinBuilder = a1 , b2 , c3 , a1 ,

        // Аналог Collector'а вище стилем JDK7 і нижче
        StringBuilder b = new StringBuilder(); // метод ініціалізації акумулятора
        for (String s : strings) {
            b.append(s).append(" , "); // метод обробки кожного елемента
        }
        String joinBuilderOld = b.toString(); // кінцевий метод
        System.out.println("joinBuilderOld = " + joinBuilderOld); // joinBuilderOld = a1 , b2 , c3 , a1 ,


        System.out.println();
        System.out.println(" ******************** робота з обєктами ");
        // ******************** робота з обєктами
        // створити List<Person>
        List<Person> listPerson = new ArrayList<>();
        listPerson.add(new Person(100, "Mohan"));
        listPerson.add(new Person(200, "Sohan"));
        listPerson.add(new Person(300, "Mahesh"));

        // замапити List<Person> і вивести кожен елемент mapPerson у вигляді "Key: " + x +", value: "+ y
        Map<Integer, String> mapPerson = listPerson.stream()
                .collect(Collectors.toMap(Person::getId, Person::getName));

        mapPerson.forEach((x, y) -> System.out.println("Key: " + x + ", value: " + y));
        System.out.println();

        //створюємо employeeList
        List<Employee> employeeList = Arrays.asList(
                new Employee("Tom Jones", 45, 15000.00, 190),
                new Employee("Tom Jones", 45, 7000.00, 220),
                new Employee("Ethan Hardy", 65, 8000.00, 1008),
                new Employee("Nancy Smith", 22, 10000.00, 5),
                new Employee("Deborah Sprightly", 29, 9000.00, 45)
        );

        //Using Collectors.averagingInt()
        Double avgAge = employeeList.stream()
                .collect(Collectors.averagingInt(Employee::getAge));
        System.out.println("Average age using Collectors.averagingInt: " + avgAge);

        //Using Collectors.averagingLong()
        Double avgLeaves = employeeList.stream()
                .collect(Collectors.averagingLong(Employee::getLeaves));
        System.out.println("Average leaves using Collectors.averagingLong: " + avgLeaves);

        //Using Collectors.averagingDouble()
        Double avgSalary = employeeList.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println("Average salary using Collectors.averagingDouble: " + avgSalary);

        System.out.println();

        Employee[] employeesArray = employeeList.stream()
                .filter(el -> el.getSalary() > 8000)
                .toArray(Employee[]::new);
        System.out.println("employeesArray = " + Arrays.toString(employeesArray));
        // print employeesArray = [Employee{name='Tom Jones', age=45, salary=15000.0, leaves=190}, Employee{name='Nancy Smith', age=22, salary=10000.0, leaves=5}, Employee{name='Deborah Sprightly', age=29, salary=9000.0, leaves=45}]

        System.out.println();

        //Collecting stream elements in a LinkedList
        LinkedList<Employee> employeeLinkedList = employeeList.stream()
                .collect(Collectors.toCollection(LinkedList::new));

        LinkedList<Employee> employeeLinkedList_1 = new LinkedList<>(employeeList);
        System.out.println("No.of employees in employeeLinkedList: " + employeeLinkedList.size());
        System.out.println("Employees in employeeLinkedList: " + employeeLinkedList);

        System.out.println("No.of employees in employeeLinkedList: " + employeeLinkedList_1.size());
        System.out.println("Employees in employeeLinkedList: " + employeeLinkedList_1);


        //Collecting stream elements in a HashSet

        HashSet<Employee> employeeHashSet = employeeList.stream()
                .collect(Collectors.toCollection(HashSet::new));
        // employeeHashSet == employeeHashSet_1
        HashSet<Employee> employeeHashSet_1 = new HashSet<>(employeeList);

        System.out.println();
        System.out.println("No.of employees in employeeHashSet: " + employeeHashSet.size());
        System.out.println("Employees in employeeHashSet: " + employeeHashSet);

        System.out.println("No.of employees in employeeHashSet: " + employeeHashSet_1.size());
        System.out.println("Employees in employeeHashSet: " + employeeHashSet_1);
        //No.of employees in employeeHashSet: 5
        //Employees in employeeHashSet: [Employee{name='Ethan Hardy', age=65, salary=8000.0, leaves=1008}, Employee{name='Deborah Sprightly', age=29, salary=9000.0, leaves=45}, Employee{name='Tom Jones', age=45, salary=15000.0, leaves=190}, Employee{name='Tom Jones', age=45, salary=7000.0, leaves=220}, Employee{name='Nancy Smith', age=22, salary=10000.0, leaves=5}]
        //No.of employees in employeeHashSet: 5
        //Employees in employeeHashSet: [Employee{name='Ethan Hardy', age=65, salary=8000.0, leaves=1008}, Employee{name='Deborah Sprightly', age=29, salary=9000.0, leaves=45}, Employee{name='Tom Jones', age=45, salary=15000.0, leaves=190}, Employee{name='Tom Jones', age=45, salary=7000.0, leaves=220}, Employee{name='Nancy Smith', age=22, salary=10000.0, leaves=5}]


        System.out.println();
        Map<Boolean, List<Employee>> employeeMap = employeeList.stream()
                .collect(Collectors.partitioningBy((Employee emp) -> emp.getAge() > 30));
        System.out.println("Employees partitioned based on Predicate - 'age > 30'");
        employeeMap.forEach((Boolean key, List<Employee> empList) -> System.out.println(key + "->" + empList));
        //false->[Employee{name='Nancy Smith', age=22, salary=10000.0, leaves=5}, Employee{name='Deborah Sprightly', age=29, salary=9000.0, leaves=45}]
        //true->[Employee{name='Tom Jones', age=45, salary=15000.0, leaves=190}, Employee{name='Tom Jones', age=45, salary=7000.0, leaves=220}, Employee{name='Ethan Hardy', age=65, salary=8000.0, leaves=1008}]


        System.out.println();
        Map<Boolean, Long> employeeMapCount = employeeList.stream()
                .collect(Collectors.partitioningBy(
                        (Employee emp) -> (emp.getAge() > 30),
                        Collectors.counting()
                ));
        System.out.println("Employee count in the 2 partitioned age groups");
        employeeMapCount.forEach((Boolean key, Long count) -> System.out.println(key + " count -> " + count));
        // false count -> 2
        //true count -> 3
    }

    public static class Person {
        private Integer id;
        private String name;

        public Person(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }


    public static class Employee {
        private String name;
        private Integer age;
        private Double salary;
        private long leaves;

        public Employee(String name, Integer age, Double salary, long leaves) {
            this.name = name;
            this.age = age;
            this.salary = salary;
            this.leaves = leaves;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Double getSalary() {
            return salary;
        }

        public void setSalary(Double salary) {
            this.salary = salary;
        }

        public long getLeaves() {
            return leaves;
        }

        public void setLeaves(long leaves) {
            this.leaves = leaves;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Employee employee = (Employee) o;
            return leaves == employee.leaves &&
                    Objects.equals(name, employee.name) &&
                    Objects.equals(age, employee.age) &&
                    Objects.equals(salary, employee.salary);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age, salary, leaves);
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", salary=" + salary +
                    ", leaves=" + leaves +
                    '}';
        }
    }


    public static void main(String[] args) {
        methodCollectAndToArray();
    }

}
