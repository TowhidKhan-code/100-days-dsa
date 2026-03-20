package oop.Day29.staticExample;

public class InnerClasses {

    // ── INNER CLASS ───────────────────────────────────────────────────────
    // A class defined inside another class
    // static inner class = does not need an instance of the outer class to be created
    // Without static: InnerClasses outer = new InnerClasses(); outer.new Test("Towhid");
    // With static:    InnerClasses.Test a = new InnerClasses.Test("Towhid"); (or just Test a = new Test("Towhid") from inside)
    static class Test {
        String name;

        public Test(String name) {
            this.name = name;
        }

        // ── toString() OVERRIDE ───────────────────────────────────────────
        // Every class inherits toString() from Object
        // Default toString() returns: ClassName@hashcode  e.g. Test@7ef88735
        // When you pass an object to System.out.println(), it calls toString() internally
        // By overriding it, we control what gets printed
        @Override
        public String toString() {
            return name;    // now println(a) prints "Towhid" instead of "Test@hashcode"
        }
    }

    public static void main(String[] args) {
        Test a = new Test("Towhid");   // changed from Kunal to Towhid
        Test b = new Test("Rahul");

        // System.out.println(a) internally calls a.toString()
        // Without override: prints Test@<hashcode>
        // With override:    prints "Towhid"
        System.out.println(a);         // Towhid
    }
}