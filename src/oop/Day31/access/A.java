package oop.Day31.access;

/*
============================================================
DAY 31 - Access Modifiers in Java
============================================================
*/

public class A {
    /*
    ============================================================
    ACCESS MODIFIERS
    Access modifiers control the visibility and accessibility of
    classes, methods, and variables.

    4 Types of Access Modifiers in Java:
    1. public     → Accessible from everywhere
    2. protected  → Accessible within same package + all subclasses
                    (even in different packages)
    3. default    → (no modifier) Accessible only within same package
    4. private    → Accessible only within the same class
    ============================================================
    */

    protected int num;      // protected: same package + subclasses
    public String name;     // public: accessible from anywhere
    int[] arr;              // default (package-private): only same package
    private int income;     // private: only inside this class

    public A(int num, String name, int income) {
        this.num = num;
        this.name = name;
        this.arr = new int[num];
        this.income = income;
    }

    // Getter for private member
    public int getIncome() {
        return income;
    }

    // Setter for private member
    public void setIncome(int income) {
        this.income = income;
    }
}