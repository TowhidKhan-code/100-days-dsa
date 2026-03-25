package oop.Day32.abstractDemo;

/*
============================================================
DAY 32 - Abstract Classes & Multiple Inheritance Problem
============================================================
*/

public abstract class Parent {
    /*
    ============================================================
    MULTIPLE INHERITANCE PROBLEM (Diamond Problem)
    Java does not support multiple inheritance with classes because
    if two parent classes have a method with the same name, the child
    class will not know which method to inherit → ambiguity (Diamond Problem).

    Solution in Java:
    1. Use Abstract Classes (for partial abstraction)
    2. Use Interfaces (for full abstraction & multiple inheritance)
    ============================================================

    ============================================================
    ABSTRACT CLASS
    - A class that is declared with the "abstract" keyword.
    - Cannot be instantiated (you cannot create object of abstract class).
    - Can have both abstract methods and concrete (normal) methods.
    - Used to provide a common base for subclasses with some shared code.
    - Achieves 0% to 100% abstraction.
    ============================================================
    */

    int age;

    // final variable must be initialized in constructor
    final int VALUE;

    public Parent(int age) {
        this.age = age;
        this.VALUE = 32456789;
    }

    // ============================================================
    // CONCRETE (Normal) Methods in Abstract Class
    // These methods have implementation and can be inherited directly.
    // ============================================================

    static void hello() {
        System.out.println("hey");
    }

    void normal() {
        System.out.println("this is a normal method");
    }

    // ============================================================
    // ABSTRACT METHODS
    // Declared without body (no implementation).
    // Must be overridden by all concrete (non-abstract) subclasses.
    // If a class has even one abstract method, the class must be abstract.
    // ============================================================

    abstract void career();
    abstract void partner();
}