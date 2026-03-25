package oop.Day30_Principles.inheritance;

/*
============================================================
DAY 30 - Inheritance in Java
============================================================
*/

public class Box {
    /*
    ============================================================
    INHERITANCE
    Inheritance is an OOP principle that allows a class (child/subclass)
    to inherit properties and methods from another class (parent/superclass).
    It promotes code reusability and establishes "IS-A" relationship.

    Types of Inheritance in Java:
    - Single
    - Multilevel
    - Hierarchical
    (Multiple and Hybrid not supported directly due to diamond problem)
    ============================================================
    */

    private double l;   // private - only accessible in this class
    double h;           // default access
    double w;           // default access

    // Getter for private field
    public double getL() {
        return l;
    }

    // Default Constructor
    Box() {
        this.h = -1;
        this.l = -1;
        this.w = -1;
    }

    // Cube Constructor
    Box(double side) {
        this.w = side;
        this.l = side;
        this.h = side;
    }

    // Parameterized Constructor
    Box(double l, double h, double w) {
        System.out.println("Box class constructor called");
        this.l = l;
        this.h = h;
        this.w = w;
    }

    // Copy Constructor
    Box(Box old) {
        this.h = old.h;
        this.l = old.l;
        this.w = old.w;
    }

    // Static method (cannot be overridden)
    static void greeting() {
        System.out.println("Hey, I am in Box class. Greetings!");
    }
}