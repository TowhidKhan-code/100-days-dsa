package oop.Day29.staticExample;

public class Human {
    // ── INSTANCE VARIABLES ────────────────────────────────────────────────
    // Each Human object has its own copy of these
    int age;
    String name;
    int salary;
    boolean married;

    // ── STATIC VARIABLE ───────────────────────────────────────────────────
    // Shared across ALL Human objects — only one copy exists for the entire class
    // Represents data that belongs to the class, not any individual object
    // Use case here: count total humans (population) — not per-person data
    static long population;

    // ── STATIC METHOD ─────────────────────────────────────────────────────
    // Belongs to the class, not any object
    // Has no 'this' reference — no current object context
    static void message() {
        System.out.println("Hello world");
        // System.out.println(this.age); // COMPILE ERROR: cannot use this in static
        //   WHY: 'this' refers to the current object
        //        but static methods have no object — they belong to the class itself
    }

    public Human(int age, String name, int salary, boolean married) {
        this.age     = age;
        this.name    = name;
        this.salary  = salary;
        this.married = married;

        // ── STATIC VARIABLE IN CONSTRUCTOR ────────────────────────────────
        // Every time a Human object is created, population increases by 1
        // All objects share the SAME population variable
        // Human.population or just population — both work inside the class
        Human.population += 1;
    }
}