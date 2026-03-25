package oop.Day30_Principles.polymorphism;

/*
    ============================================================
    POLYMORPHISM
    Polymorphism means "many forms".
    It allows the same method name to behave differently based on the object.

    Two Types:
    1. Compile-time Polymorphism (Method Overloading)
    2. Runtime Polymorphism (Method Overriding)
    ============================================================
    */

public class Shapes {
    void area() {
        System.out.println("I am in shapes");
    }

//      Early binding
//      Methods that are final can't be overridden
//      So at compile time it determines that this method is going to run
//      Skips the steps which determine the method to run via overriding in run time
//    final void area() {
//        System.out.println("I am in shapes");
//    }
}
