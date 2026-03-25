package oop.Day32.interfaces.nested;

/*
============================================================
DAY 32 - Nested Interfaces
============================================================
*/

public class A {
    /*
    ============================================================
    NESTED INTERFACE
    - An interface declared inside another class or interface.
    - It is implicitly static.
    - Used when the interface is only useful within the outer class.
    ============================================================
    */

    // Nested Interface
    public interface NestedInterface {
        boolean isOdd(int num);
    }
}

// Implementing nested interface
class B implements A.NestedInterface {

    @Override
    public boolean isOdd(int num) {
        return (num & 1) == 1;   // bitwise check for odd number
    }
}