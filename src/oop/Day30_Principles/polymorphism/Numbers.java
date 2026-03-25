package oop.Day30_Principles.polymorphism;

/*
    ============================================================
    METHOD OVERLOADING
    Same method name, different parameters (number, type, or order).
    Resolved at compile time → Compile-time Polymorphism
    ============================================================
    */

public class Numbers {

    //Method overloading
    //method with same name but with different number of parameter,different types of parameter,different return type
    double sum(double a, int b) {
        return a + b;
    }

    double sum(int a, int b) {
        return a + b;
    }

    int sum(int a, int b, int c) {
        return a + b + c;
    }

    public static void main(String[] args) {
        Numbers obj = new Numbers();

        //Type promotion
        obj.sum(2, 3); // this uses the method with two int parameter, if that weren't there it would have used the double one
        obj.sum(1, 3, 7);

        //This will give error as no method has 4 parameters
//        obj.sum(4, 5, 6, 8);
    }
}
