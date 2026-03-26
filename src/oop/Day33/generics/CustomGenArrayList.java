// https://docs.oracle.com/javase/tutorial/java/generics/restrictions.html#createObjects
package oop.Day33.generics;

import java.util.Arrays;

/*
============================================================
DAY 33 - GENERICS IN JAVA
============================================================
*/

public class CustomGenArrayList<T> {
    /*
    ============================================================
    GENERICS
    Definition:
    Generics allow you to create classes, interfaces, and methods
    that operate on different data types while providing compile-time
    type safety.

    Why Generics?
    - Type Safety: Catches type errors at compile time instead of runtime
    - No need for type casting
    - Reusability: Write code once and use it with any type
    - Backward compatibility with raw types

    Internal Working:
    - At compile time, Java performs type checking and type erasure
    - After compilation, all generic information is removed (Type Erasure)
    - Everything becomes Object in bytecode
    ============================================================
    */

    private Object[] data;
    private static int DEFAULT_SIZE = 10;
    private int size = 0;   // also acts as index

    public CustomGenArrayList() {
        data = new Object[DEFAULT_SIZE];
    }

    // T represents any type passed by user (Integer, String, Student, etc.)
    public void add(T num) {
        if (isFull()) {
            resize();
        }
        data[size++] = num;
    }

    private void resize() {
        Object[] temp = new Object[data.length * 2];

        // Copy existing elements to new larger array
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }

    private boolean isFull() {
        return size == data.length;
    }

    // Returns element of type T (type safety)
    public T remove() {
        T removed = (T) data[--size];   // explicit cast due to type erasure
        return removed;
    }

    public T get(int index) {
        return (T) data[index];
    }

    public int size() {
        return size;
    }

    public void set(int index, T value) {
        data[index] = value;
    }

    @Override
    public String toString() {
        return "CustomGenArrayList{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                '}';
    }

    public static void main(String[] args) {

        // Without Generics (Raw Type) - Not Recommended
        // CustomGenArrayList list = new CustomGenArrayList();

        // With Generics - Type Safe
        CustomGenArrayList<Integer> list3 = new CustomGenArrayList<>();

        for (int i = 0; i < 14; i++) {
            list3.add(2 * i);
        }

        System.out.println(list3);
    }
}