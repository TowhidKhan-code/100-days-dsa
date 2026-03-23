package oop.Day30_Principles.polymorphism;

public class Shapes {
    void area() {
        System.out.println("I am in shapes");
    }

//      Early binding
//      Methods that are final can't be overridden
//      So at compile time it determines that this method is going to run
//      Skips the steps which determine the method to run via overriding in run time
//    final void area() {
//        System.out.println("I am in shapes");
//    }
}
