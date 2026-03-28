package oop.Day34.enumExamples;

/*
============================================================
DAY 34 - ENUMS IN JAVA
============================================================
*/

public class Basic {
    /*
    ============================================================
    ENUM (Enumeration)

    Definition:
    Enum is a special data type that represents a group of constants
    (unchangeable variables, like final variables).

    Why use Enums?
    - To define a fixed set of constants (days of week, months, directions, etc.)
    - Improves type safety
    - Makes code more readable and maintainable
    - Prevents invalid values

    Internal Working:
    - Every enum constant is an object of the enum type
    - Enums are implicitly public, static, and final
    - You cannot create new instances of enum using 'new' keyword
    - Enums can have constructors, methods, and fields
    ============================================================
    */

    // Enum inside class
    enum Week implements A {
        // These are enum constants
        // Internally: public static final Week Monday = new Week();
        Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday;

        // Enum can have constructor
        Week() {
            System.out.println("Constructor called for " + this.name());
        }

        // Enum can have methods
        void display() {
            System.out.println("Today is " + this);
        }

        // Implementing interface method
        @Override
        public void hello() {
            System.out.println("Hey, how are you?");
        }
    }

    public static void main(String[] args) {

        Week week;

        week = Week.Monday;

        week.hello();                    // calling interface method

        System.out.println(Week.valueOf("Monday"));   // returns enum constant

        // Printing all enum values
        for (Week day : Week.values()) {
            System.out.println(day + " at position " + day.ordinal());
        }

        // ordinal() returns the position (index) of enum constant
        System.out.println("Ordinal of Monday: " + Week.Monday.ordinal());
    }
}