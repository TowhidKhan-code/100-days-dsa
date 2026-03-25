package oop.Day31.access;

/*
============================================================
DAY 31 - Subclass demonstrating protected access
============================================================
*/

public class SubClass extends A {

    public SubClass(int num, String name, int income) {
        super(num, name, income);
    }

    public static void main(String[] args) {
        SubClass obj = new SubClass(45, "Towhid", 20000);

        // protected member is accessible in subclass
        int n = obj.num;

        System.out.println("Is instance of Object? " + (obj instanceof Object));
    }
}