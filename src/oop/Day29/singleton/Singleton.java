package oop.Day29.singleton;

public class Singleton {

    // ── PRIVATE CONSTRUCTOR ────────────────────────────────────────────────
    // private → no code OUTSIDE this class can call new Singleton()
    // This is the KEY rule of Singleton — only THIS class controls object creation
    // Anyone trying: Singleton s = new Singleton(); → COMPILE ERROR
    private Singleton() {

    }

    // ── STATIC INSTANCE VARIABLE ───────────────────────────────────────────
    // static → belongs to the class, not to any object
    // private → cannot be accessed directly from outside
    // Starts as null — no object created yet
    private static Singleton instance;

    // ── STATIC FACTORY METHOD ─────────────────────────────────────────────
    // static → called on the class itself: Singleton.getInstance()
    //          no object needed to call this (and you can't create one anyway)
    // public → accessible from anywhere
    // This is the ONLY way to get a Singleton object
    public static Singleton getInstance() {
        // First call:  instance is null → create one object → store it
        // Every call after: instance is not null → return the SAME object
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

}