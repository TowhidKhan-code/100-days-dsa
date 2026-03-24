package oop.Day31.access;

public class A {
    protected int num;
    public String name;
    int[] arr;
    private int income;



    public A(int num, String name,int income) {
        this.num = num;
        this.name = name;
        this.arr = new int[num];
        this.income = income;
    }
}
