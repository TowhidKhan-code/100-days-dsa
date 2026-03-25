package oop.Day30_Principles.inheritance;

public class Main {
    public static void main(String[] args) {
        Box box1 = new Box(4.6, 7.9, 9.9);
        box1.getL();
        Box box2 = new Box(box1);
//        System.out.println(box1.w + " " + box1.h);

//        BoxWeight box3 = new BoxWeight();
//        BoxWeight box4 = new BoxWeight(2, 3, 4, 8);
//        System.out.println(box3.h + " " + box3.weight);


//        Box box5 = new BoxWeight(2, 3, 4, 8);
//        System.out.println(box5.w);

        /*
        Important Rule:
        Reference type decides what members you can access.
        Actual object type decides which overridden method runs.
        */

        // there are many variables in both parent and child classes
        // you are given access to variables that are in the ref type i.e. BoxWeight
        // hence, you should have access to weight variable
        // this also means, that the ones you are trying to access should be initialised
        // but here, when the obj itself is of type parent class, how will you call the constructor of child class
        // this is why error
//        BoxWeight box6 = new Box(2, 3, 4);
//        System.out.println(box6);

//        Box.greeting();

        BoxWeight box6 = new BoxWeight();
        BoxWeight.greeting(); // you can inherit but you cannot override

        /*
        ============================================================
        DEMONSTRATING STATIC METHOD HIDING
        ============================================================
        */

        // Case 1: Reference and Object both of child type
        BoxWeight boxWeight = new BoxWeight();
        boxWeight.greeting();                    // Output: BoxWeight class greeting

        // Case 2: Reference of parent type, Object of child type
        Box box = new BoxWeight();               // Upcasting
        box.greeting();                          // Output: Box class greeting
        // → Even though actual object is BoxWeight, parent's static method is called
        // because static methods are resolved based on REFERENCE TYPE at compile time.

        // Case 3: Direct class call
        Box.greeting();                          // Box class
        BoxWeight.greeting();                    // BoxWeight class

        /*
        Important Summary:
        - For Instance methods → Runtime Polymorphism (Overriding) → decided by object type
        - For Static methods   → Static Method Hiding          → decided by reference type
        */
    }
}
