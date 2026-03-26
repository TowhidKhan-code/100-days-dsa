package oop.Day33.generics;

/*
============================================================
DAY 33 - Generic Interface
============================================================
*/

public interface GenericInterface<T> {
    /*
    ============================================================
    GENERIC INTERFACE
    - Interfaces can also be generic
    - The implementing class must specify the type
    ============================================================
    */
    void display(T value);
}
