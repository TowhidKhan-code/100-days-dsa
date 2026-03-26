package oop.Day33.generics;

import java.util.ArrayList;
import java.util.Arrays;


/*
============================================================
DAY 33 - Non-Generic Custom ArrayList (For Comparison)
============================================================
*/

public class CustomArrayList {
    /*
    ============================================================
    PROBLEM WITHOUT GENERICS
    - Can only store one specific type (here only int)
    - If you want to store String, Float, or Student, you need to create
      separate classes → Code duplication
    - No type safety at compile time
    ============================================================
    */

    private int[] data;
    private static int DEFAULT_SIZE = 10;
    private int size = 0;

    public CustomArrayList() {
        this.data = new int[DEFAULT_SIZE];
    }

    public void add(int num) {
        if (isFull()) {
            resize();
        }
        data[size++] = num;
    }

    private void resize() {
        int[] temp = new int[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }

    private boolean isFull() {
        return size == data.length;
    }

    public int remove() {
        return data[--size];
    }

    public int get(int index) {
        return data[index];
    }

    public int size() {
        return size;
    }

    public void set(int index, int value) {
        data[index] = value;
    }

    @Override
    public String toString() {
        return "CustomArrayList{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                '}';
    }
}
