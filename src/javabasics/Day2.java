package javabasics;

import java.util.Scanner;

public class Day2 {
  public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);

      // Primitive Data Type
          // Whole numbers
          int age = 21;
          long population = 1400000000L;  // notice the L at end
          short verySmallNumber = 124;
          byte smallNumber = 10;

          // Decimal numbers
          float percentage = 85.5f;       // notice the f at end
          double pi = 3.14159265358979;

          // Single character
          char letter = 'A';               // notice single quotes

          // True or False
          boolean isPlaced = false;

          // Text (not primitive — this is a class)
            //String is a sequence of characters
          String name = "Towhid";         // notice double quotes

//          System.out.println("Name: " + name);
//          System.out.println("Age: " + age);
//          System.out.println("PI: " + pi);
//          System.out.println("Placed: " + isPlaced);


       // Type Casting
        //widening - automatic(Implicit)
        int first = 10;
        double second = first;
//        System.out.println(second); // prints 10.0

        // Narrowing - manual(Explicit)
        double x = 9.99;
        int y = (int) x;
//        System.out.println(y); // prints 9 - decimal lost!

        // Most important - integer division fix
        int num1 = 10;
        int num2 = 3;
//        System.out.println(num1 / num2);           // prints 3
//        System.out.println((double) num1 / num2);  // prints 3.333

      // Automatic Type Promotion in expressions

        int f = 257;
        // max value that can be stored in byte is 256
        // if we try to store more than that it stores the remainder i.e, value % maxvalue = remainder
        byte g = (byte) f; // 257 % 256 = 1

        byte h =  40;
        byte i = 50;
        byte j = 100;
        int k = h * i / j; //the bytes are automatically converted to integer in the expression

        byte l = 50;
//        l = l * 2; // this will show error as byte gets converted to int in expression so assigning int to byte shows error
//        l = (byte) ((byte) l * 2); // can be done manually like this


      //Operators

      // Relational - always returns boolean i.e, true or false
        //Allows to compare two operands
        int a = 10;
        int b = 5;
//        System.out.println(a > b);   // true
//        System.out.println(a < b);   // false
//        System.out.println(a == b);  // false
//        System.out.println(a != b);  // true
//        System.out.println(a >= 10); // true

      // Logical - Allows to check multiple conditions
        //System.out.println(a > 5 && b > 3);  // true - both true
        //System.out.println(a > 5 && b > 10); // false - one is false
        //System.out.println(a > 5 || b > 10); // true - at least one true
        //System.out.println(!(a > 5));         // false - flips true to false

      //Compound Operator(Shorthand)
        int n = 10;
        n += 5;
        //System.out.println(n); // 15
        n -= 3;
        //System.out.println(n); // 12
        n *= 2;
        //System.out.println(n); // 24
        n /= 4;
        //System.out.println(n); // 6
        n %= 4;
        //System.out.println(n); // 2

      //Increment/Decrement
        // Tricky part - understand this properly
        int c = 5;
        //System.out.println(c++); // prints 5, then a becomes 6
        //System.out.println(c);   // prints 6
        //System.out.println(++c); // a becomes 7, then prints 7
        //System.out.println(c);   // prints 7

      //Ternary Operator
        //Ternary is just a one line if/else. Pattern is always:
            //`condition ? valueIfTrue : valueIfFalse`
        int grade = 90;
        String result = (grade >= 50) ? "Pass" : "Fail";
        //System.out.println(result); // Pass

        int d = 10;
        int e = 20;
        int max = (d > e) ? d : e;
        //System.out.println("Max is: " + max); // 20


      // Practice Programs

      // Even or Odd
      /*
      System.out.print("Enter a number: ");
      int number1 = sc.nextInt();

      String evenOrOdd = number1 % 2 == 0 ? "Even" : "Odd";
      System.out.println(evenOrOdd);
      */

      // Swap two numbers
      int one = 10;
      int two = 20;

      one = one + two;
      two = one - two;
      one = one - two;

//      System.out.println("After: one = " + one + " two = " + two);


      // Biggest Number
      int three = 45;
      int four = 78;
      int biggest = three>four ? three : four;
//      System.out.println(biggest);

      
  }
}
