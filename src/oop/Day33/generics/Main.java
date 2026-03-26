package oop.Day33.generics;

/*
============================================================
DAY 33 - Implementing Generic Interface
============================================================
*/

public class Main implements GenericInterface<Integer> {

    @Override
    public void display(Integer value) {
        System.out.println("Value: " + value);
    }
}