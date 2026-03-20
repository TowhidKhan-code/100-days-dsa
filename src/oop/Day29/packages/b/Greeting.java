package oop.Day29.packages.b;

// ── PACKAGES ──────────────────────────────────────────────────────────────
// A package is a folder that groups related classes together.
// Two main purposes:
//   1. Organisation — keep related classes in one place
//   2. Name collision — two classes can have the same name if in different packages
//      e.g. both package a and package b have a class called Greeting
//      Java knows which one to use based on the package declaration

// This is package b's greeting — completely separate from package a's greeting
// Both can exist without any conflict because they are in different packages
public class Greeting {

    public static void main(String[] args) {
        System.out.println("Hello There!");
    }
}