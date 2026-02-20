package javabasics;
//importing java scanner class
import java.util.Scanner;

//Creating the class
//everything in java is written in class
public class Day1 {
    //main()method is the starting point where execution starts for java program
    public static void main(String[] args) {
        // First program
        // System.out.println(); used to print output
        System.out.println("Day 1 of 100 let's go.");

        //Second program
        // Taking user input using scanner
        // Creating scanner object
        Scanner scanner = new Scanner(System.in);

        //scanner.nextLine() used to read user Input in string format
        System.out.println("Enter your name: ");
        //Variable is a named container to store data
        // name is variable which stores the value of user input which can be used in the program
        String name = scanner.nextLine();

        // printing the user input
        System.out.println(name);

        //Third program
        // Taking numbers from user
        // performing basic mathematics operations

        //nextInt() used to read integer value from user
        System.out.print("Enter first number: ");
        int num1 = scanner.nextInt();
        System.out.print("Enter second number: ");
        int num2 = scanner.nextInt();

        int result;

        //Addition
        result = num1 + num2;
        System.out.println(result);

        //Subtraction
        result = num1 - num2;
        System.out.println(result);

        //Multiplication
        result = num1 * num2;
        System.out.println(result);

        //Division
        result = num1 / num2;
        System.out.println(result);

        //Modulus
        result = num1 % num2;
        System.out.println(result);


    }
}