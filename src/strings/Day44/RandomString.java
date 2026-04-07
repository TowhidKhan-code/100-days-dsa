package strings.Day44;

import java.util.Random;

public class RandomString {

    /*
    ============================================================
    RANDOM STRING GENERATOR

    Generates random lowercase string of length n
    Uses ASCII values: 'a'=97 to 'z'=122

    ALGORITHM:
    1. Generate random number 0-25
    2. Add 97 to get ASCII value for a-z
    3. Convert to char
    4. Append to StringBuffer

    WHY STRINGBUFFER:
    - More efficient than String concatenation in loop
    - Mutable — modifies same object
    ============================================================
    */

    static String generate(int n) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            // Generate random number from 0 to 25
            int randomChar = 97 + (int)(random.nextFloat() * 26);
            // random.nextFloat() gives 0.0 to 0.999...
            // * 26 gives 0.0 to 25.999...
            // (int) truncates to 0 to 25
            // + 97 gives 97 to 122 (ASCII for 'a' to 'z')

            char ch = (char) randomChar;
            sb.append(ch);
        }

        return sb.toString();
    }

    // Improved version with more options
//    static String generateAlphanumeric(int n) {
//        StringBuffer sb = new StringBuffer();
//        Random random = new Random();
//        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//
//        for (int i = 0; i < n; i++) {
//            int index = random.nextInt(characters.length());
//            sb.append(characters.charAt(index));
//        }
//
//        return sb.toString();
//    }

    public static void main(String[] args) {
        // Test random generators
        System.out.println("Lowercase (10 chars): " + generate(10));
        System.out.println("Lowercase (20 chars): " + generate(20));

//        System.out.println("Alphanumeric (15 chars): " + generateAlphanumeric(15));
//
//        // Generate password
//        String password = generateAlphanumeric(12);
//        System.out.println("Generated password: " + password);
    }
}

