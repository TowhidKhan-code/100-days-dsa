package oop.Day31.access;

/*
============================================================
DAY 31 - Object Class and Overriding its Methods
============================================================
*/

public class ObjectDemo {

    int num;
    float gpa;

    public ObjectDemo(int num, float gpa) {
        this.num = num;
        this.gpa = gpa;
    }

    /*
    ============================================================
    IMPORTANT METHODS OF OBJECT CLASS (java.lang.Object)

    1. toString()     → returns string representation of object
    2. equals(Object) → checks if two objects are equal
    3. hashCode()     → returns hash code value (used in HashMap, HashSet)
    4. getClass()     → returns runtime class of the object
    5. clone()        → creates and returns copy of object
    6. finalize()     → called by Garbage Collector before destroying object
                       (deprecated in modern Java)

    We usually override toString(), equals(), and hashCode()
    ============================================================
    */

    @Override
    public String toString() {
        return "ObjectDemo{" +
                "num=" + num +
                ", gpa=" + gpa +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ObjectDemo that = (ObjectDemo) obj;
        return this.num == that.num;
    }

    @Override
    public int hashCode() {
        return num;   // simple version for demo
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Finalize method called - object is being garbage collected");
    }

    public static void main(String[] args) {
        ObjectDemo obj1 = new ObjectDemo(12, 56.8f);
        ObjectDemo obj2 = new ObjectDemo(12, 78.9f);

        System.out.println(obj1);                    // calls overridden toString()
        System.out.println("obj1 equals obj2: " + obj1.equals(obj2));  // true

        System.out.println("Class name: " + obj1.getClass().getName());
    }
}