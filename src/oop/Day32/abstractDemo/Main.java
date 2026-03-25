package oop.Day32.abstractDemo;

/*
============================================================
DAY 32 - Main Class demonstrating Abstract Classes
============================================================
*/

public class Main {
    public static void main(String[] args) {
        /*
        ============================================================
        KEY POINTS ABOUT ABSTRACT CLASSES

        1. Abstract Constructors
           - Abstract classes CAN have constructors.
           - They are called when a child class object is created (via super()).

        2. Object of Abstract Class
           - NOT allowed. You cannot do: new Parent(45);
           - Reason: Abstract class is incomplete (has abstract methods).

        3. Abstract Static Methods
           - NOT allowed. Static methods cannot be abstract
             because static methods belong to class and cannot be overridden.

        4. Static Methods in Abstract Class
           - Allowed and can be called using class name.

        5. final Keyword in Abstract Class
           - You can have final variables (must be initialized in constructor).
           - You can have final methods (cannot be overridden by child).
           - You cannot make abstract class final (because then no one can extend it).
        ============================================================
        */

        Son son = new Son(30);
        son.career();           // calls Son's implementation
        son.normal();           // calls overridden normal method

        // Upcasting: Parent reference pointing to Daughter object
        Parent daughter = new Daughter(28);
        daughter.career();      // Runtime polymorphism - calls Daughter's method

        // Static method can be called using abstract class name
        Parent.hello();

        // Not allowed - compile error
        // Parent mom = new Parent(45);
    }
}