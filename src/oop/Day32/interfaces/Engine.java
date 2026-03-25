package oop.Day32.interfaces;

/*
============================================================
DAY 32 - INTERFACES in Java (Full Abstraction + Multiple Inheritance)
============================================================
*/

public interface Engine {
    /*
    ============================================================
    INTERFACE
    - An interface is a completely abstract class.
    - It contains only abstract methods (before Java 8) and constants.
    - From Java 8: default methods and static methods are allowed.
    - From Java 9: private methods are allowed.
    - Used to achieve 100% abstraction and multiple inheritance.
    - A class can implement multiple interfaces (solves diamond problem).
    ============================================================

    Rules for Interfaces:
    - All variables are by default: public + static + final (constants)
    - All methods are by default: public + abstract (until Java 8)
    - You cannot create object of an interface.
    - A class uses "implements" keyword to use an interface.
    - A class must implement ALL abstract methods of the interface.
    ============================================================
    */

    // Variable in interface is always public static final
    static final int PRICE = 78000;

    // Abstract methods (public by default)
    void start();
    void stop();
    void acc();
}