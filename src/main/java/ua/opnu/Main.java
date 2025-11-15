package ua.opnu;

import java.util.*;
import java.util.function.*;

class Student {
    private String name;
    private String group;
    private int[] marks;

    public Student(String name, String group, int[] marks) {
        this.name = name;
        this.group = group;
        this.marks = marks;
    }

    public String getName() { return name; }
    public String getGroup() { return group; }
    public int[] getMarks() { return marks; }
}

public class Main {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10};

        // ===== Завдання 1 =====
        Predicate<Integer> isPrime = n -> {
            if (n < 2) return false;
            for (int i = 2; i <= Math.sqrt(n); i++)
                if (n % i == 0) return false;
            return true;
        };
        System.out.println("Завдання 1: " + Arrays.toString(filter(arr, isPrime)));

        // ===== Завдання 2 =====
        Student[] students = {
                new Student("Іванов", "ІП-11", new int[]{80,70,90}),
                new Student("Петров", "ІП-12", new int[]{50,40,60}),
                new Student("Сидоров", "ІП-11", new int[]{100,90,95})
        };

        Predicate<Student> noDebts = s -> Arrays.stream(s.getMarks()).allMatch(m -> m >= 60);
        Student[] filtered = filterStudents(students, noDebts);
        System.out.print("Завдання 2: ");
        for (Student s : filtered) System.out.print(s.getName() + " ");
        System.out.println();

        // ===== Завдання 3 =====
        Predicate<Integer> even = n -> n % 2 == 0;
        Predicate<Integer> moreThanFive = n -> n > 5;
        System.out.println("Завдання 3: " + Arrays.toString(filterTwo(arr, even, moreThanFive)));

        // ===== Завдання 4 =====
        Consumer<Student> printName = s -> System.out.println(s.getName());
        System.out.println("Завдання 4:");
        forEachStudent(students, printName);

        // ===== Завдання 5 =====
        Predicate<Integer> greaterThanFive = n -> n > 5;
        Consumer<Integer> printNum = n -> System.out.print(n + " ");
        System.out.print("Завдання 5: ");
        doIf(arr, greaterThanFive, printNum);
        System.out.println();

        // ===== Завдання 6 =====
        Function<Integer,Integer> pow2 = n -> (int)Math.pow(2,n);
        System.out.println("Завдання 6: " + Arrays.toString(processArray(arr, pow2)));

        // ===== Завдання 7 =====
        Function<Integer,String> toWord = n -> {
            String[] words = {"нуль","один","два","три","чотири","п'ять","шість","сім","вісім","дев'ять"};
            return (n >= 0 && n <= 9) ? words[n] : "?";
        };
        System.out.println("Завдання 7: " + Arrays.toString(stringify(arr, toWord)));
    }

    static int[] filter(int[] input, Predicate<Integer> p) {
        return Arrays.stream(input).filter(p::test).toArray();
    }

    static Student[] filterStudents(Student[] input, Predicate<Student> p) {
        return Arrays.stream(input).filter(p::test).toArray(Student[]::new);
    }

    static int[] filterTwo(int[] input, Predicate<Integer> p1, Predicate<Integer> p2) {
        return Arrays.stream(input).filter(i -> p1.test(i) && p2.test(i)).toArray();
    }

    static void forEachStudent(Student[] arr, Consumer<Student> c) {
        for (Student s : arr) c.accept(s);
    }

    static void doIf(int[] arr, Predicate<Integer> p, Consumer<Integer> c) {
        for (int i : arr) if (p.test(i)) c.accept(i);
    }

    static int[] processArray(int[] input, Function<Integer,Integer> f) {
        int[] result = new int[input.length];
        for (int i = 0; i < input.length; i++)
            result[i] = f.apply(input[i]);
        return result;
    }

    static String[] stringify(int[] input, Function<Integer,String> f) {
        String[] result = new String[input.length];
        for (int i = 0; i < input.length; i++)
            result[i] = f.apply(input[i]);
        return result;
    }
}