package strings.Day44;

import java.text.DecimalFormat;
import java.util.Arrays;

public class StringBufferExample {

    /*
    ============================================================
    STRINGBUFFER PRACTICAL EXAMPLES

    Demonstrates:
    - Three types of constructors
    - Common methods (append, insert, replace, delete, reverse)
    - Capacity management
    - String manipulation techniques
    - DecimalFormat for number formatting
    ============================================================
    */

    public static void main(String[] args) {

        // ========================================
        // STRINGBUFFER CONSTRUCTORS
        // ========================================

        // Constructor 1: Default (capacity 16)
        StringBuffer sb = new StringBuffer();

        // Constructor 2: Initialize with String
        StringBuffer sb2 = new StringBuffer("Towhid Khan");

        // Constructor 3: Custom initial capacity
        StringBuffer sb3 = new StringBuffer(30);


        // ========================================
        // APPEND — Add to end
        // ========================================

        sb.append("Java");
        sb.append(" is nice!");
        System.out.println("After append: " + sb);  // Java is nice!


        // ========================================
        // INSERT — Add at specific position
        // ========================================

        StringBuffer sb4 = new StringBuffer("Java is nice!");
        sb4.insert(4, " Language");
        System.out.println("After insert: " + sb4);  // Java Language is nice!


        // ========================================
        // REPLACE — Replace substring
        // ========================================

        StringBuffer sb5 = new StringBuffer("Java is nice!");
        sb5.replace(0, 4, "Python");
        System.out.println("After replace: " + sb5);  // Python is nice!


        // ========================================
        // DELETE — Remove characters
        // ========================================

        StringBuffer sb6 = new StringBuffer("Java is nice!");
        sb6.delete(0, 5);
        System.out.println("After delete: " + sb6);  // is nice!


        // ========================================
        // REVERSE — Reverse entire string
        // ========================================

        StringBuffer sb7 = new StringBuffer("Hello");
        sb7.reverse();
        System.out.println("After reverse: " + sb7);  // olleH


        // ========================================
        // CAPACITY — Check internal buffer size
        // ========================================

        System.out.println("sb capacity: " + sb.capacity());    // 16
        System.out.println("sb3 capacity: " + sb3.capacity());  // 30


        // ========================================
        // CONVERT TO STRING
        // ========================================

        String str = sb.toString();
        System.out.println("Converted to String: " + str);


        // ========================================
        // RANDOM STRING GENERATION
        // ========================================

        int n = 20;
        String name = RandomString.generate(n);
        System.out.println("Random string: " + name);


        // ========================================
        // REMOVING WHITESPACES
        // ========================================

        String sentence = "Hi h    hjh hjkso   slowl   w";
        System.out.println("Original: " + sentence);

        // Using regex to remove ALL whitespace
        System.out.println("No spaces: " + sentence.replaceAll("\\s", ""));
        // \\s matches any whitespace character (space, tab, newline)


        // ========================================
        // SPLIT STRING
        // ========================================

        // Split by space
        String fullName = "Towhid Khan Ahmed";
        String[] names = fullName.split(" ");
        System.out.println("Split by space: " + Arrays.toString(names));
        // [Towhid, Khan, Ahmed]

        // Split by comma
        String csvData = "Towhid,Khan,Ahmed";
        String[] csvNames = csvData.split(",");
        System.out.println("Split by comma: " + Arrays.toString(csvNames));
        // [Towhid, Khan, Ahmed]


        // ========================================
        // DECIMAL FORMATTING
        // ========================================

        DecimalFormat df = new DecimalFormat("00.0000");
        System.out.println("Formatted: " + df.format(7.29));  // 07.2900

        // Format pattern:
        // 0 = mandatory digit (shows 0 if not present)
        // # = optional digit (doesn't show if not present)

        DecimalFormat df2 = new DecimalFormat("#,###.00");
        System.out.println("Money: $" + df2.format(1234567.8));  // $1,234,567.80
    }
}