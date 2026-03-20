package oop.Day29.singleton;

public class Main {
    public static void main(String[] args) {

        // ── SINGLETON IN ACTION ────────────────────────────────────────────
        // Each call to getInstance() goes through the null check:
        //   obj1: instance is null → creates ONE object → returns it
        //   obj2: instance is not null → returns THE SAME object
        //   obj3: instance is not null → returns THE SAME object
        Singleton obj1 = Singleton.getInstance();
        Singleton obj2 = Singleton.getInstance();
        Singleton obj3 = Singleton.getInstance();

        // All 3 reference variables point to just ONE object on the heap
        // Proof:
        System.out.println(obj1 == obj2); // true — same object
        System.out.println(obj2 == obj3); // true — same object
        System.out.println(obj1 == obj3); // true — same object

        // ── WHY SINGLETON IS USEFUL ────────────────────────────────────────
        // Database connection — you only want one connection object, not hundreds
        // Logger — one logging object shared across the whole application
        // Configuration — one settings object loaded once and reused everywhere
    }
}