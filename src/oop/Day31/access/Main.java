package oop.Day31.access;

/*
============================================================
DAY 31 - Main Class to demonstrate Access Modifiers
============================================================
*/

public class Main {
    public static void main(String[] args) {
        /*
        ============================================================
        ACCESSING AND MODIFYING MEMBERS
        - We can access/modify members based on their access level
        - private members can only be accessed via getters/setters
        ============================================================
        */

        A obj = new A(10, "Towhid", 20000);

        // Accessing members
        int n = obj.num;           // protected → allowed (same package)
        String s = obj.name;       // public → allowed
        // int i = obj.income;     // private → compile error

        // Modifying members
        obj.num = 50;
        obj.name = "Rahim";
        // obj.income = 50000;    // private → not allowed

        // Using getter and setter (best practice for private fields)
        System.out.println("Current Income: " + obj.getIncome());
        obj.setIncome(25000);
        System.out.println("Updated Income: " + obj.getIncome());
    }
}