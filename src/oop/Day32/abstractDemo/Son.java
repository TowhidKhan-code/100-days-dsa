package oop.Day32.abstractDemo;

/*
============================================================
DAY 32 - Concrete Child Class extending Abstract Class
============================================================
*/

public class Son extends Parent {

    public Son(int age) {
        super(age);   // must call parent constructor
    }

    // Overriding abstract methods is mandatory
    @Override
    void career() {
        System.out.println("I am going to be a doctor");
    }

    @Override
    void partner() {
        System.out.println("I love Pepper Potts");
    }

    // We can also override normal methods if needed
    @Override
    void normal() {
        super.normal();   // calling parent's version
        System.out.println("This is overridden normal method in Son");
    }
}