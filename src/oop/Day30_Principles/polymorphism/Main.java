package oop.Day30_Principles.polymorphism;

public class Main {
    public static void main(String[] args) {
        Shapes shape = new Shapes();
        Shapes circle = new Circle();
        Shapes square = new Square();

        /*
        ============================================================
        RUNTIME POLYMORPHISM (Dynamic Method Dispatch)
        - Method to be called is decided at runtime based on the actual object type
        - Achieved through method overriding
        ============================================================
        */

        //Runs the method in the Circle class(child)
        //Even though the reference variable is of Shapes(Parent) class
        //Because the method to run is determine by the object rather than the reference variable
        circle.area();
    }
}
