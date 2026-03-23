package oop.Day30_Principles.inheritance;


public class BoxWeight extends Box {
    double weight;

    public BoxWeight() {
        this.weight = -1;
    }

    //    static cannot be overridden
    //    @Override
    static void greeting() {
        System.out.println("Hey, I am in BoxWeight class. Greetings!");
    }

    BoxWeight(BoxWeight other) {
        super(other);
        weight = other.weight;
    }

    BoxWeight(double side, double weight) {
        super(side);
        this.weight = weight;
    }

    public BoxWeight(double l, double h, double w, double weight) {
        // used to initialize values present in parent class
        super(l, h, w); // calls the parent class constructor
//        System.out.println(super.weight); // super allows to access parent class members
        this.weight = weight;
    }
}
