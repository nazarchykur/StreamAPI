package com.company;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Na");
        map.put(2, "za");
        map.put(3, "rius");
        System.out.println("map = " + map);
        String s = map.get(2);
        System.out.println(s);
        map.entrySet().forEach(System.out::println);
        System.out.println("map.keySet() = " + map.keySet());
        System.out.println("map.values() = " + map.values());
        map.put(null, "zz");
        System.out.println("map = " + map);
        map.put(null, "xx");
        System.out.println("map = " + map);
        map.put(5, null);
        System.out.println("map = " + map);
        map.put(6, null);
        System.out.println("map = " + map);
    }
}
