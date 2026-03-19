package oop.Day28.Introduction;

public class Main {

    public static void main(String[] args) {

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



