package oop.Day32.interfaces.extendDemo2;

/*
============================================================
DAY 32 - Interface B
============================================================
*/

public interface B {
    void greet();

    // If you uncomment below, it will cause conflict with A's default fun()
//    default void fun() {
//        System.out.println("I am in B");
//    }
}