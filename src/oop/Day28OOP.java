package oop;

public class Day28OOP {

    public static void main(String[] args) {

        System.out.println("════════════════════════════════");
        System.out.println("         STUDENT CLASS          ");
        System.out.println("════════════════════════════════");

        // Old way: 3 separate arrays, messy and hard to manage
        int[] rno = new int[5];
        String[] name = new String[5];
        float[] marks = new float[5];

        // New way: one Student array, clean and organised
        Student[] students = new Student[5];

        // Creating objects
        Student towhid = new Student(15, "Towhid Khan", 85.4f);
        Student rahul = new Student(18, "Rahul Rana", 90.3f);

        // Accessing instance variables
        System.out.println(towhid.rno);    // 15
        System.out.println(towhid.name);   // Towhid Khan
        System.out.println(towhid.marks);  // 85.4

        // Copy constructor — creates a NEW object with same values as towhid
        Student random = new Student(towhid);
        System.out.println(random.name);   // Towhid Khan — copied from towhid

        // No-arg constructor → chains to full constructor
        Student random2 = new Student();
        System.out.println(random2.name);  // default person

        // ── Reference Trap ── IMPORTANT ───────────────────────────────────
        // Student two = one is NOT a copy — both point to the SAME heap object
        Student one = new Student();
        Student two = one;
        one.name = "Something something";
        System.out.println(two.name);      // "Something something" — same object!
        // To get an actual copy: Student two = new Student(one);


        System.out.println("\n════════════════════════════════");
        System.out.println("        WRAPPER EXAMPLE         ");
        System.out.println("════════════════════════════════");

        // Autoboxing: int → Integer (automatic)
        Integer a = 10;
        Integer b = 20;

        // Swap demo — does NOT work
        System.out.println("Before swap: a=" + a + ", b=" + b);  // 10 20
        swap(a, b);
        System.out.println("After swap:  a=" + a + ", b=" + b);  // 10 20 — unchanged!
        // WHY: Java passes reference by value
        //   a and b inside swap() are LOCAL COPIES of the references
        //   Reassigning local copies does not affect caller's variables

        // final on primitives
        // final int bonus = 2;
        // bonus = 3; // COMPILE ERROR

        // final on objects — cannot reassign reference, but CAN modify fields
        final A towhidObj = new A("Towhid Khan");
        towhidObj.name = "other name";       // OK: modifying field
        // towhidObj = new A("new object");  // COMPILE ERROR: cannot reassign final reference
        System.out.println("\ntowhidObj.name after modification: " + towhidObj.name);

        // Default toString() prints ClassName@hashcode
        A obj = new A("Rnadvsjhv");
        System.out.println(obj);

        // Garbage Collection demo — uncomment to see finalize() called
        // for (int i = 0; i < 1000000000; i++) {
        //     obj = new A("Random name"); // old object loses reference → eligible for GC
        // }

        // Useful Integer methods
        System.out.println("\n── Integer Methods ──");
        System.out.println("parseInt:       " + Integer.parseInt("123"));       // converts String "123" to int 123
        System.out.println("valueOf:        " + Integer.valueOf(456));           // converts int 456 to Integer object 456
        System.out.println("toBinaryString: " + Integer.toBinaryString(10));    // converts int 10 to binary String "1010"
        System.out.println("toHexString:    " + Integer.toHexString(255));      // converts int 255 to hex String "ff"
        System.out.println("max:            " + Integer.max(10, 20));           // 20
        System.out.println("min:            " + Integer.min(10, 20));           // 10
        System.out.println("MAX_VALUE:      " + Integer.MAX_VALUE);             // 2147483647
        System.out.println("MIN_VALUE:      " + Integer.MIN_VALUE);             // -2147483648


        // Swap — does NOT work for Integer
        // Java passes reference by value — local reassignment has no effect on caller
    }
        static void swap (Integer a, Integer b){
            Integer temp = a;
            a = b;
            b = temp;
            System.out.println("Inside swap: a=" + a + ", b=" + b); // 20 10 (swapped locally only)
        }
}

    // ══════════════════════════════════════════════════════════════════════
// Student Class
// ══════════════════════════════════════════════════════════════════════
    class Student {
        int rno;
        String name;
        float marks = 90;   // default value

        void greeting() {
            System.out.println("Hello! My name is " + this.name);
        }

        void changeName(String name) {
            this.name = name;   // this.name = field, name = parameter
        }

        // Constructor 1: Copy constructor
        Student(Student other) {
            this.name = other.name;
            this.rno = other.rno;
            this.marks = other.marks;
        }

        // Constructor 2: No-arg → chains to full constructor
        Student() {
            this(13, "default person", 100.0f); // must be FIRST line
        }

        // Constructor 3: Full parameterised constructor
        Student(int rno, String name, float marks) {
            this.rno = rno;
            this.name = name;
            this.marks = marks;
        }
    }

    // ══════════════════════════════════════════════════════════════════════
// A Class — demonstrates final field and finalize()
// ══════════════════════════════════════════════════════════════════════
    class A {
        final int num = 10;   // final field: cannot change after initialisation
        String name;

        public A(String name) {
            this.name = name;
        }

        // Called by GC before collecting — deprecated in Java 9, unreliable
        @Override
        protected void finalize() throws Throwable {
            System.out.println("Object is destroyed");
        }
    }
