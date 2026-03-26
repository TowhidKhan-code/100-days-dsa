package oop.Day33.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://docs.oracle.com/javase/tutorial/java/generics/restrictions.html#createObjects

// here T should either be Number or its subclasses
public class WildcardExample<T extends Number> {
    /*
    ============================================================
    BOUNDED GENERICS & WILDCARDS

    1. Bounded Type Parameter:
       <T extends Number> → T can only be Number or its subclasses
       (Integer, Double, Float, Long, etc.)

    2. Wildcard (?):
       List<? extends Number> → Accepts any List whose type is Number
       or its subclass (covariant)

    Purpose:
    - Increases flexibility
    - Allows reading from collection safely
    ============================================================
    */

    private Object[] data;
    private static int DEFAULT_SIZE = 10;
    private int size = 0;

    public WildcardExample() {
        data = new Object[DEFAULT_SIZE];
    }

    // Wildcard example - can accept List<Integer>, List<Double>, etc.
    public void getList(List<? extends Number> list) {
        // We can safely read as Number
        for (Number num : list) {
            System.out.println(num);
        }
    }

    public void add(T num) {
        if (isFull()) resize();
        data[size++] = num;
    }

    private void resize() {
        Object[] temp = new Object[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }

    private boolean isFull() {
        return size == data.length;
    }

    public T remove() {
        return (T) data[--size];
    }

    public T get(int index) {
        return (T) data[index];
    }

    @Override
    public String toString() {
        return "WildcardExample{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                '}';
    }


    public static void main(String[] args) {
//        ArrayList list = new ArrayList();
        WildcardExample list = new WildcardExample();
//        list.add(3);
//        list.add(5);
//        list.add(9);

//        for (int i = 0; i < 14; i++) {
//            list.add(2 * i);
//        }

//        System.out.println(list);

        ArrayList<Integer> list2 = new ArrayList<>();
//        list2.add("dfghj");


        WildcardExample<Integer> list3 = new WildcardExample<>();
        for (int i = 0; i < 14; i++) {
            list3.add(2 * i);
        }

        System.out.println(list3);

    }
}