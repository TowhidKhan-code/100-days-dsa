package oop.Day32.interfaces;

public class Main {
    public static void main(String[] args) {
//        Engine car = new Car();
//        car.a;
//        car.acc();
//        car.start();
//        car.stop();
//        Media carMedia = new Car();
//        carMedia.stop();

        /*
        ============================================================
        IMPORTANT POINTS

        1. Interface reference can hold any implementing class object.
        2. You can only call methods declared in the interface through
           the interface reference.
        3. A class can implement multiple interfaces → Multiple Inheritance.
        ============================================================
        */

        NiceCar car = new NiceCar();

        car.start();
        car.startMusic();
        car.upgradeEngine();
        car.start();
    }
}