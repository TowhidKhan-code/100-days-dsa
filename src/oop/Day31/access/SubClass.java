package oop.Day31.access;

public class SubClass extends A{


    public SubClass(int num, String name, int income) {
        super(num, name, income);
    }

    public static void main(String[] args) {
        SubClass obj = new SubClass(45, "Towhid", 20000);
        int n = obj.num;

        System.out.println(obj instanceof Object);
    }
}
