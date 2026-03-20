package oop.Day29.staticExample;

public class Main {

    public static void main(String[] args) {
        // Non-static method fun2() needs an object to be called
        Main funn = new Main();
        funn.fun2();
    }

    // ── STATIC METHOD ─────────────────────────────────────────────────────
    // static = does not belong to any object, belongs to the class itself
    // Because of this, it has NO access to instance (non-static) members directly
    // To use a non-static method inside a static method, you must create an object first
    static void fun() {
        // greeting(); // COMPILE ERROR — cannot call instance method directly in static context
        //              greeting() belongs to an object, but fun() has no object

        // you cant use this because it requires an instance
        // but the function you are using it in does not depend on instances

        // you cannot access non static stuff without referencing their instances in
        // a static context

        // hence, here I am referencing it
        // FIX: create an instance, then call through it
        Main obj = new Main();
        obj.greeting();     // now it works — obj is the instance context
    }

    // ── NON-STATIC METHOD ─────────────────────────────────────────────────
    // Non-static = belongs to an object (has a this reference)
    // Can freely access both static and non-static members
    void fun2() {
        greeting();         // fine — fun2() belongs to an object so greeting() is accessible
    }

    // ── INSTANCE METHOD ───────────────────────────────────────────────────
    // Belongs to an object — requires an instance to call
    void greeting() {
        // fun(); // this would work — static can always be called from non-static
        System.out.println("Hello world");
    }
}