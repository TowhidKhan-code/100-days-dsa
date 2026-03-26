package oop.Day33.generics.cloning;

/*
============================================================
DAY 33 - OBJECT CLONING IN JAVA
============================================================
*/

public class Human implements Cloneable {
    /*
    ============================================================
    CLONING
    Definition:
    Cloning is the process of creating an exact copy of an existing object.

    Why do we need cloning?
    - To create a duplicate object without affecting the original.
    - Useful when you want to modify a copy without changing the source.

    Two Types of Cloning:
    1. Shallow Copy → Copies references (default behavior of Object.clone())
    2. Deep Copy   → Copies actual values (independent copy of mutable objects)

    Important:
    - To use cloning, class must implement Cloneable marker interface
    - Must override clone() method from Object class
    - clone() method throws CloneNotSupportedException
    ============================================================
    */

    int age;
    String name;
    int[] arr;   // mutable object (array) - important for deep vs shallow

    public Human(int age, String name) {
        this.age = age;
        this.name = name;
        this.arr = new int[]{3, 4, 5, 6, 9, 1};
    }

    // Shallow Copy Version (Commented)
    /*
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();   // Shallow copy - arr reference is shared
    }
    */

    // Deep Copy Version (Recommended for mutable objects)
    @Override
    public Object clone() throws CloneNotSupportedException {
        // Step 1: First do shallow copy using super.clone()
        Human twin = (Human) super.clone();

        // Step 2: Manually create deep copy for mutable fields (like array)
        twin.arr = new int[this.arr.length];
        for (int i = 0; i < this.arr.length; i++) {
            twin.arr[i] = this.arr[i];
        }

        return twin;
    }
}