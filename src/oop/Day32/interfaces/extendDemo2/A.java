package oop.Day32.interfaces.extendDemo2;

/*
============================================================
DAY 32 - Static and Default Methods in Interfaces (Java 8+)
============================================================
*/

public interface A {
    /*
    ============================================================
    STATIC METHODS IN INTERFACE
    - Introduced in Java 8.
    - Must have a body.
    - Cannot be overridden by implementing class.
    - Called using InterfaceName.methodName()
    - Useful for utility methods.

    DEFAULT METHODS
    - Also introduced in Java 8.
    - Have a body and can be overridden by implementing class.
    - Solves backward compatibility problem.
    ============================================================
    */

    // Static method in interface
    static void greeting() {
        System.out.println("Hey I am static method");
    }

    // Default method
    default void fun() {
        System.out.println("I am in A");
    }
}