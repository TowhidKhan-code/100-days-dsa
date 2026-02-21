package javabasics;

import java.util.Scanner;

public class Day3 {
   public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       //Conditionals + Loops
       //if statement
       int num = 4;
       //If condition is true only than code block executes
       if(num<10){
           System.out.println("Prints only if the condition is true");
       }
       //if-else
       //If condition is true then if code block executes, if condition false then else code block executes
       if(num>10){
           System.out.println("Prints if condition is true");
       }else{
           System.out.println("Prints if condition is false");
       }

       //if/else if/else
       //checks conditions top to bottom and stops at the first true one.
       int age = 75;

       if (age >= 50) {
           System.out.println("Senior");
       } else if (age >= 18) {
           System.out.println("Adult");
       } else if (age >= 13) {
           System.out.println("Teenagers");
       } else {
           System.out.println("Child");
       }

       //switch
       // checks a value and returns the matching case
       //Always use break inside switch. Without it Java falls through to the next case.
       int day = 3;

       switch (day) {
           case 1:
               System.out.println("Monday");
               break;
           case 2:
               System.out.println("Tuesday");
               break;
           case 3:
               System.out.println("Wednesday");
               break;
           default:
               System.out.println("Invalid day");
       }

       //Loops
       //for loop - when you know exactly how many times
       // 3 parts: starting point ; condition to keep going ; what changes each time
       // loops continue till the condition is true
       for (int i = 1; i <= 5; i++) {
           System.out.println("Day " + i);// prints Day 1, Day 2, Day 3, Day 4, Day 5
       }

       //while loop - when you don't know how many times
       //Checks condition FIRST. If false from start, never runs.
       int n = 1;
       while (n <= 5) {
           System.out.println(n);
           n++;
       }

       //do-while loop - runs at least once no matter what
       //Runs FIRST, checks condition AFTER. Even though n=10 fails the condition, it still runs once
       int a = 10;
       do {
           System.out.println("Runs at least once");
           a++;
       } while (a < 5);

       //break and continue
       // break — exits the loop entirely
       for (int i = 1; i <= 10; i++) {
           if (i == 5) break;
           System.out.println(i); // prints 1 2 3 4
       }

       // continue — skips current iteration, continues loop
       for (int i = 1; i <= 10; i++) {
           if (i == 5) continue;
           System.out.println(i); // prints 1 2 3 4 6 7 8 9 10
       }

       //Practice Programs

       //Grade Checker
       System.out.println("Enter your Marks: ");
       int marks = sc.nextInt();

       if (marks >= 90) {
           System.out.println("A Grade");
       } else if (marks >= 75) {
           System.out.println("B Grade");
       } else if (marks >= 60) {
           System.out.println("C Grade");
       } else {
           System.out.println("Fail");
       }

       //Print 1 to 100
       for(int i=1;i<101;i++){
           System.out.println(i);
       }

       //Sum of 1 to N
       System.out.println("Enter the number: ");
       int last = sc.nextInt();
       int sum = 0;
       for(int i=1;i<=last;i++){
           sum += i;
       }
       System.out.println("Sum: " + sum);

       //Multiplication Table
       System.out.println("Enter number for table: ");
       int number = sc.nextInt();
       for(int i=1;i<11;i++){
           System.out.println(number + "*" + i + "=" + number*i);
       }

       //Reverse a number
       int originalNumber = 1234;
       int reverseNumber = 0;
       while(originalNumber > 0){
           int lastDigit = originalNumber % 10;
           reverseNumber = reverseNumber * 10 + lastDigit;
           originalNumber /= 10;
       }

       //Calculator
       System.out.println("Enter first number: ");
       int num1 = sc.nextInt();
       System.out.println("Enter second number: ");
       int num2 = sc.nextInt();
       sc.nextLine();
       System.out.println("Enter the Operator(+,-,*,/,%): ");
       String text = sc.nextLine();
       char operator = text.charAt(0);
       double result = 0;
       switch(operator){
           case '+':
               result = num1 + num2;
           break;
           case '-':
               result = num1 - num2;
           break;
           case '*':
               result = num1 * num2;
           break;
           case '/':
               if(num2 != 0) {
                   result = num1 / num2;
               }
           break;
           case '%':
               result = num1 % num2;
           break;
           default:
               System.out.println("Enter a valid operator!");
       }
       System.out.println(result);

       //FizzBuzz
       for(int i=1;i<51;i++){
           if(i%3==0 && i%5==0){
               System.out.println("FizzBuzz");
           }else if(i%3==0){
               System.out.println("Fizz");
           }else if(i%5==0){
               System.out.println("Buzz");
           }else{
               System.out.println(i);
           }
       }
    }
}
