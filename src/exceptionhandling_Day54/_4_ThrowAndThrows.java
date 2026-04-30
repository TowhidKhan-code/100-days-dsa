package exceptionhandling_Day54;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class _4_ThrowAndThrows {

        // Throw when business rule violated
        public static double divide(int a, int b) {
            if (b == 0) {
                throw new ArithmeticException("Division by zero is not allowed");
            }
            return (double) a / b;
        }

        // Throw checked exception
        public static void validateAge(int age) throws IllegalArgumentException {
            if (age < 0) {
                throw new IllegalArgumentException("Age cannot be negative: " + age);
            }
            if (age > 150) {
                throw new IllegalArgumentException("Age seems invalid: " + age);
            }
            System.out.println("Valid age: " + age);
        }

        // Throw and rethrow
        public static void rethrowDemo() throws Exception {
            try {
                int[] arr = new int[5];
                arr[10] = 1; // Throws ArrayIndexOutOfBoundsException
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Caught and rethrowing as RuntimeException");
                throw new RuntimeException("Array access failed", e); // Wrap and rethrow
                // e is stored as the CAUSE of the new exception
            }
        }

        public static void main(String[] args) {
            // Test divide
            try {
                System.out.println(divide(10, 2));  // 5.0
                System.out.println(divide(10, 0));  // Throws!
            } catch (ArithmeticException e) {
                System.out.println("Caught: " + e.getMessage());
            }

            // Test validateAge
            try {
                validateAge(25);   // Valid
                validateAge(-5);   // Throws!
            } catch (IllegalArgumentException e) {
                System.out.println("Caught: " + e.getMessage());
            }

            // Test rethrow
            try {
                rethrowDemo();
            } catch (Exception e) {
                System.out.println("Outer: " + e.getMessage());
                System.out.println("Cause: " + e.getCause().getMessage());
            }

            // Must handle because readFile throws checked exception
            try {
                String content = readFile("test.txt");
                System.out.println(content);
            } catch (IOException e) {
                System.out.println("File not found or read error");
            }
        }


        // Declares it might throw IOException (checked)
        // Caller MUST handle or also declare throws
        public static String readFile(String path) throws IOException {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String content = br.readLine();
            br.close();
            return content;
        }

        // Declares multiple possible exceptions
        public static void connectDB(String url) throws SQLException, ClassNotFoundException {
            Class.forName("com.mysql.Driver");       // ClassNotFoundException
            Connection conn = DriverManager.getConnection(url); // SQLException
        }

        // Chain: method calls throws-declared method, must also declare OR catch
        public static void processFile(String path) throws IOException {
            String content = readFile(path);  // readFile throws IOException
            // Since we don't catch it, we must declare throws IOException
            System.out.println(content);
        }

        // OR: catch it instead of declaring throws
        public static void processFileSafe(String path) {
            try {
                String content = readFile(path);
                System.out.println(content);
            } catch (IOException e) {
                System.out.println("File error: " + e.getMessage());
            }
        }

    }

/*
THROWS RULES:
→ Only for CHECKED exceptions (IOException, SQLException etc.)
→ Unchecked (RuntimeException) don't need throws declaration
→ throws propagates responsibility to the caller
→ Method can declare throws without actually throwing
→ Overriding method: cannot throw BROADER checked exceptions
   Child can throw same or NARROWER exceptions than parent

OVERRIDING RULES:
Parent:  void method() throws IOException
Child:   void method() throws FileNotFoundException  ✓ (narrower)
Child:   void method() throws Exception              ✗ (broader — compile error)
Child:   void method()                               ✓ (no throws is fine)
*/

