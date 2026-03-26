package oop.Day33.generics.cloning;

import java.util.Arrays;

/*
============================================================
DAY 33 - Main Class demonstrating Shallow vs Deep Copy
============================================================
*/

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {

        Human kunal = new Human(34, "Kunal Kushwaha");

        // Creating clone
        Human twin = (Human) kunal.clone();

        System.out.println("Original : " + kunal.age + " " + kunal.name);
        System.out.println("Clone    : " + twin.age + " " + twin.name);

        System.out.println("Original Array: " + Arrays.toString(kunal.arr));
        System.out.println("Clone Array   : " + Arrays.toString(twin.arr));

        // Modifying clone's array
        twin.arr[0] = 100;

        System.out.println("\nAfter modifying clone's array:");
        System.out.println("Original Array: " + Arrays.toString(kunal.arr));
        System.out.println("Clone Array   : " + Arrays.toString(twin.arr));

        /*
        Observation:
        - In Deep Copy: Changes in clone do not affect original
        - In Shallow Copy: Changes would reflect in both (shared reference)
        */
    }
}