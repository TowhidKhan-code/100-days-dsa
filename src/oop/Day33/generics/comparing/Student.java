package oop.Day33.generics.comparing;

/*
============================================================
DAY 33 - Comparable Interface (Natural Ordering)
============================================================
*/

public class Student implements Comparable<Student> {
    /*
    ============================================================
    COMPARABLE INTERFACE
    - Used to define natural ordering of objects
    - Contains only one method: compareTo(Object o)
    - Used with Arrays.sort() and Collections.sort()
    ============================================================
    */

    int rollno;
    float marks;

    public Student(int rollno, float marks) {
        this.rollno = rollno;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "rollno=" + rollno +
                ", marks=" + marks +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        System.out.println("Comparing " + this.marks + " with " + o.marks);
        return (int)(this.marks - o.marks);   // ascending order by marks
    }
}