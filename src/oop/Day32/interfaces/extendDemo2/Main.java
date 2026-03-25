package oop.Day32.interfaces.extendDemo2;

/*
============================================================
DAY 32 - Main demonstrating Static & Default Methods
============================================================
*/

public class Main implements A, B {

    @Override
    public void greet() {
        System.out.println("greet method implemented");
    }

    // You can override default method if needed
    @Override
    public void fun() {
        System.out.println("I am overridden in Main class");
    }

    public static void main(String[] args) {
        Main obj = new Main();
        A.greeting();           // calling static method via interface name

        obj.fun();              // calls overridden default method
        obj.greet();
    }
}