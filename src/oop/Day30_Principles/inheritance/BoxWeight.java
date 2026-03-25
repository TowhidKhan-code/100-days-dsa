package oop.Day30_Principles.inheritance;

/*
============================================================
DAY 30 - Multilevel Inheritance - BoxWeight extends Box
============================================================
*/

    /*
    Difference between Overriding and Hiding:

    | Feature              | Method Overriding (Instance)     | Static Method Hiding          |
    |----------------------|----------------------------------|-------------------------------|
    | Applies to           | Non-static methods               | Static methods                |
    | Decided at           | Runtime (based on object)        | Compile time (based on ref)   |
    | @Override annotation | Allowed & Recommended            | Not allowed (compile error)   |
    | Polymorphism type    | Runtime Polymorphism             | Compile-time behavior         |
    | Purpose              | Dynamic behavior                 | Class-specific behavior       |
    */

public class BoxWeight extends Box {
    /*
    ============================================================
    MULTI-LEVEL INHERITANCE
    When a class inherits from a derived class.
    Example: Box → BoxWeight → BoxPrice

    ============================================================
    STATIC METHOD HIDING (Very Important Concept)

    - Static methods belong to the CLASS, not to the object.
    - You cannot "override" static methods (because overriding is a runtime concept
      based on object type).
    - When a child class defines a static method with the same name and signature
      as a static method in the parent class, it is called STATIC METHOD HIDING.

    Key Rules:
    1. It is NOT overriding → It is hiding.
    2. The method that gets called is decided at COMPILE TIME based on the
       REFERENCE TYPE (not the actual object type).
    3. Static methods cannot be overridden with @Override annotation
       (it will give compile error if you try).
    4. This is also known as "Compile-time polymorphism" for static methods.

    Real-life Analogy:
    Parent class has a static method → "Family Greeting"
    Child class defines same static method → "My own Family Greeting"
    When you call through Parent reference → Parent's greeting is shown.
    When you call through Child reference  → Child's greeting is shown.
    ============================================================
    */

    double weight;

    public BoxWeight() {
        this.weight = -1;
    }

    // Copy Constructor
    BoxWeight(BoxWeight other) {
        super(other);
        this.weight = other.weight;
    }

    BoxWeight(double side, double weight) {
        super(side);
        this.weight = weight;
    }

    public BoxWeight(double l, double h, double w, double weight) {
        super(l, h, w);          // calling parent constructor
        this.weight = weight;
    }

    // This is STATIC METHOD HIDING (not overriding)
    // If you uncomment @Override, you will get compile error
    // @Override
    static void greeting() {
        System.out.println("Hey, I am in BoxWeight class. Greetings!");
    }
}