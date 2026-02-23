package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day5 {

    /*
    ============================================================
    DAY 5 - ARRAYS
    Why Arrays, Syntax, Internal Working, Dynamic Memory,
    String Arrays, null, Array Input, for-each, toString(),
    Array of Objects, Heap Storage, Array in Functions,
    2D Arrays, Dynamic Arrays (ArrayList), Array Functions
    ============================================================
    */


    // ============================================================
    // WHY DO WE NEED ARRAYS?
    // Without arrays — storing 100 student marks needs 100 variables
    // int m1, m2, m3... m100 — impossible to manage
    // With arrays — one variable holds all 100 values
    // int[] marks = new int[100]
    // Arrays let us store multiple values of same type under one name
    // and access each one using an index
    // ============================================================


    // ============================================================
    // WHAT IS AN ARRAY?
    // A fixed-size collection of elements of the SAME type
    // Stored in CONTIGUOUS (side by side) memory locations
    // Each element accessed using an INDEX starting from 0
    // Size is fixed once created — cannot grow or shrink
    //
    // Think of it like a row of lockers in school
    // Each locker has a number (index) starting from 0
    // Each locker holds exactly one thing (element)
    // All lockers are in a row next to each other (contiguous)
    // ============================================================


    // ============================================================
    // SYNTAX — 3 ways to create an array
    // ============================================================

    static void arraySyntax() {

        // Way 1 — declare size, Java fills with default values
        // int default = 0, boolean default = false, String default = null
        int[] arr1 = new int[5];
        arr1[0] = 10;
        arr1[1] = 20;
        arr1[2] = 30;

        // Way 2 — declare and initialize together (most common)
        int[] arr2 = {10, 20, 30, 40, 50};

        // Way 3 — declare first, initialize later
        int[] arr3;
        arr3 = new int[]{10, 20, 30, 40, 50};

        // both [] positions are valid in Java
        int[] arr4 = new int[5]; // preferred style
        int arr5[] = new int[5]; // also valid but less common
    }


    // ============================================================
    // HOW ARRAYS WORK + INTERNAL WORKING
    //
    // When you write: int[] arr = {10, 20, 30, 40, 50};
    //
    // STACK holds: arr ──────────────→ reference (memory address)
    // HEAP holds:  [10][20][30][40][50] (actual data, contiguous)
    //
    // arr itself is just a REFERENCE VARIABLE stored on Stack
    // It holds the memory address of the first element in Heap
    // Java uses this address + index to find any element
    //
    // arr[0] → address + (0 x 4 bytes) = first element
    // arr[1] → address + (1 x 4 bytes) = second element
    // arr[2] → address + (2 x 4 bytes) = third element
    //
    // This is why array access is O(1) — instant regardless of size
    // Just a simple math calculation to find the address
    // ============================================================


    // ============================================================
    // DYNAMIC MEMORY ALLOCATION
    // Arrays in Java are allocated on the HEAP at runtime
    // using the 'new' keyword
    // The size can be determined at runtime — not just hardcoded
    // ============================================================

    static void dynamicAllocation() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size of array: ");
        int size = sc.nextInt(); // size decided at RUNTIME

        int[] arr = new int[size]; // memory allocated dynamically
        System.out.println("Array of size " + size + " created");
    }


    // ============================================================
    // CONTINUITY OF AN ARRAY + INDEX
    // All elements are stored next to each other in memory
    // This is called contiguous memory allocation
    // Index starts at 0 — because index represents OFFSET from start
    // arr[0] = start + 0 steps = first element
    // arr[1] = start + 1 step  = second element
    //
    // ARRAY INDEX OUT OF BOUNDS
    // Accessing index that doesn't exist throws:
    // ArrayIndexOutOfBoundsException
    // Most common bug with arrays — always remember: 0 to length-1
    // ============================================================

    static void indexDemo() {
        int[] arr = {10, 20, 30, 40, 50};

        System.out.println(arr.length);        // 5 — total elements
        System.out.println(arr[0]);            // 10 — first element
        System.out.println(arr[arr.length-1]); // 50 — last element

        // arr[5] would throw ArrayIndexOutOfBoundsException
        // valid indices are 0, 1, 2, 3, 4 only
    }


    // ============================================================
    // STRING ARRAY
    // Arrays can hold any type — including Strings
    // Default value for String array is null (not "" empty string)
    // ============================================================

    static void stringArrayDemo() {
        // String array with values
        String[] names = {"Towhid", "Rahul", "Priya", "Arjun"};

        // String array with just size — all elements are null by default
        String[] empty = new String[3];
        System.out.println(empty[0]); // prints null

        // traverse String array
        for (String name : names) {
            System.out.println(name);
        }
    }


    // ============================================================
    // NULL IN JAVA
    // null means a reference variable points to NOTHING
    // It is the default value for all reference types (objects)
    // Primitives (int, double, boolean) CANNOT be null
    // Trying to use a null reference throws NullPointerException
    // ============================================================

    static void nullDemo() {
        String name = null;           // reference points to nothing
        System.out.println(name);     // prints null — no crash

        // name.length() would throw NullPointerException
        // because name points to nothing — no object to call method on

        // null check before using
        if (name != null) {
            System.out.println(name.length());
        } else {
            System.out.println("name is null — cannot use it");
        }
    }


    // ============================================================
    // ARRAY INPUT FROM USER
    // Take size first, then take each element using a loop
    // ============================================================

    static int[] takeArrayInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size: ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt(); // fill each position
        }
        return arr;
    }


    // ============================================================
    // FOR-EACH LOOP (Enhanced for loop)
    // Cleaner way to traverse when you don't need the index
    // Cannot modify array elements using for-each
    // Cannot traverse backwards using for-each
    // Use normal for loop when you need index or want to modify
    // ============================================================

    static void forEachDemo() {
        int[] arr = {10, 20, 30, 40, 50};

        // normal for loop — use when you need index
        for (int i = 0; i < arr.length; i++) {
            System.out.println("Index " + i + ": " + arr[i]);
        }

        // for-each loop — cleaner when you just need values
        for (int num : arr) {
            System.out.println(num);
        }

        // for-each with String array
        String[] names = {"Towhid", "Rahul", "Priya"};
        for (String name : names) {
            System.out.println(name);
        }
    }


    // ============================================================
    // toString() METHOD
    // Directly printing an array gives garbage like [I@1b6d3586
    // This is the memory address — not useful
    // Use Arrays.toString() to get readable output
    // Use Arrays.deepToString() for 2D arrays
    // ============================================================

    static void toStringDemo() {
        int[] arr = {10, 20, 30, 40, 50};

        System.out.println(arr);                  // [I@1b6d3586 — useless
        System.out.println(Arrays.toString(arr)); // [10, 20, 30, 40, 50] — readable

        int[][] matrix = {{1, 2}, {3, 4}};
        System.out.println(Arrays.deepToString(matrix)); // [[1, 2], [3, 4]]
    }


    // ============================================================
    // ARRAY OF OBJECTS
    // Arrays can store objects — not just primitives
    // Each element in the array is a reference to an object in Heap
    // ============================================================

    // simple class to demonstrate
    static class Student {
        String name;
        int marks;

        Student(String name, int marks) {
            this.name = name;
            this.marks = marks;
        }
    }

    static void arrayOfObjects() {
        // array of Student objects
        Student[] students = new Student[3];

        // each element starts as null — must create objects
        students[0] = new Student("Towhid", 85);
        students[1] = new Student("Rahul", 90);
        students[2] = new Student("Priya", 78);

        // traverse and print
        for (Student s : students) {
            System.out.println(s.name + " — " + s.marks);
        }
    }


    // ============================================================
    // STORAGE OF OBJECTS IN HEAP
    //
    // Student[] students = new Student[3];
    //
    // STACK:
    //   students ──→ [ref0][ref1][ref2]  (array of references in Heap)
    //
    // HEAP:
    //   [ref0] ──→ Student{name="Towhid", marks=85}
    //   [ref1] ──→ Student{name="Rahul",  marks=90}
    //   [ref2] ──→ Student{name="Priya",  marks=78}
    //
    // The array stores REFERENCES to objects, not the objects directly
    // Each object lives separately in Heap
    // This is different from primitive arrays where values are stored directly
    // ============================================================


    // ============================================================
    // ARRAY PASSING IN FUNCTIONS
    // Arrays are OBJECTS in Java
    // When passed to a method — the REFERENCE is passed (not a copy)
    // So changes made inside the method AFFECT the original array
    // This is different from primitives and Strings which pass copies
    // ============================================================

    static void doubleElements(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] * 2; // modifies the ORIGINAL array
        }
    }

    static void arrayPassingDemo() {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("Before: " + Arrays.toString(arr));
        doubleElements(arr); // original array is modified
        System.out.println("After:  " + Arrays.toString(arr)); // [2, 4, 6, 8, 10]
    }


    // ============================================================
    // MULTIDIMENSIONAL ARRAYS — 2D ARRAYS
    //
    // SYNTAX of 2D array:
    // int[][] matrix = new int[rows][cols];
    //
    // Think of it as a TABLE with rows and columns
    //
    // INTERNAL WORKING OF 2D ARRAY:
    // Java implements 2D arrays as "array of arrays"
    // matrix[0] → reference to first row array
    // matrix[1] → reference to second row array
    // Each row is a separate array object in Heap
    // Rows can even have different lengths (jagged arrays)
    //
    //        col0  col1  col2
    // row0: [ 1 ][ 2 ][ 3 ]
    // row1: [ 4 ][ 5 ][ 6 ]
    // row2: [ 7 ][ 8 ][ 9 ]
    //
    // matrix[1][2] = row 1, col 2 = 6
    // ============================================================

    static void twoDArrayDemo() {

        // hardcoded 2D array
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        // access specific element
        System.out.println(matrix[1][2]); // 6 — row 1, col 2

        // matrix.length       = number of rows
        // matrix[i].length    = number of columns in row i

        // print 2D array — nested loops
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(); // new line after each row
        }

        // print using deepToString
        System.out.println(Arrays.deepToString(matrix));
    }


    // ============================================================
    // 2D ARRAY INPUT FROM USER
    // Take rows and cols first, then fill using nested loops
    // ============================================================

    static int[][] take2DInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter rows: ");
        int rows = sc.nextInt();
        System.out.print("Enter cols: ");
        int cols = sc.nextInt();

        int[][] matrix = new int[rows][cols];

        System.out.println("Enter elements:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        return matrix;
    }

    // 2D array output — print in table format
    static void print2DArray(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + "\t"); // tab for clean spacing
            }
            System.out.println();
        }
    }


    // ============================================================
    // DYNAMIC ARRAYS — ArrayList
    // Regular arrays have FIXED size — cannot grow or shrink
    // ArrayList is a DYNAMIC array — grows and shrinks automatically
    //
    // INTERNAL WORKING OF ARRAYLIST:
    // ArrayList internally uses a regular array
    // Default initial capacity = 10
    // When full — creates new array of 1.5x size, copies all elements
    // This is called RESIZING or GROWING
    // This is why adding elements is usually O(1) but occasionally O(n)
    // (when resizing happens — called amortized O(1))
    // ============================================================

    static void arrayListDemo() {

        // creating ArrayList — must use wrapper types not primitives
        // int → Integer, double → Double, char → Character
        ArrayList<Integer> list = new ArrayList<>();

        // add elements — grows automatically
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        System.out.println(list); // [10, 20, 30, 40]

        // add at specific index
        list.add(1, 99); // insert 99 at index 1, shifts others right
        System.out.println(list); // [10, 99, 20, 30, 40]

        // get element by index
        System.out.println(list.get(0)); // 10

        // update element
        list.set(0, 100);
        System.out.println(list); // [100, 99, 20, 30, 40]

        // remove by index
        list.remove(1); // removes element at index 1
        System.out.println(list); // [100, 20, 30, 40]

        // remove by value — must use Integer object not int
        list.remove(Integer.valueOf(20));
        System.out.println(list); // [100, 30, 40]

        // size of ArrayList
        System.out.println(list.size()); // 3

        // check if contains
        System.out.println(list.contains(30)); // true

        // traverse ArrayList
        for (int num : list) {
            System.out.println(num);
        }

        // clear all elements
        list.clear();
        System.out.println(list.size()); // 0
        System.out.println(list.isEmpty()); // true

        // 2D ArrayList — ArrayList of ArrayLists
        ArrayList<ArrayList<Integer>> list2D = new ArrayList<>();
        ArrayList<Integer> row1 = new ArrayList<>();
        row1.add(1); row1.add(2); row1.add(3);
        ArrayList<Integer> row2 = new ArrayList<>();
        row2.add(4); row2.add(5); row2.add(6);
        list2D.add(row1);
        list2D.add(row2);
        System.out.println(list2D); // [[1, 2, 3], [4, 5, 6]]
    }


    // ============================================================
    // BUILT-IN ARRAY FUNCTIONS — java.util.Arrays
    // Must import: import java.util.Arrays;
    // ============================================================

    static void arrayFunctions() {
        int[] arr = {5, 3, 1, 4, 2};

        // sort — ascending order
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr)); // [1, 2, 3, 4, 5]

        // binary search — array MUST be sorted first
        // returns index of element, -ve number if not found
        int index = Arrays.binarySearch(arr, 3);
        System.out.println(index); // 2

        // fill — fill all elements with same value
        Arrays.fill(arr, 0);
        System.out.println(Arrays.toString(arr)); // [0, 0, 0, 0, 0]

        // copyOf — copies first n elements into new array
        int[] original = {1, 2, 3, 4, 5};
        int[] copy = Arrays.copyOf(original, 3);
        System.out.println(Arrays.toString(copy)); // [1, 2, 3]

        // copyOfRange — copies from index start to end (end not included)
        int[] rangeCopy = Arrays.copyOfRange(original, 1, 4);
        System.out.println(Arrays.toString(rangeCopy)); // [2, 3, 4]

        // equals — checks if two arrays have same elements in same order
        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};
        int[] c = {3, 2, 1};
        System.out.println(Arrays.equals(a, b)); // true
        System.out.println(Arrays.equals(a, c)); // false
        // a == b would just compare references — always false for different objects
    }


    // ============================================================
    // IMPORTANT CONCEPTS NOT IN KUNAL'S PLAYLIST
    // ============================================================

    // JAGGED ARRAYS — rows with different column lengths
    static void jaggedArray() {
        int[][] jagged = new int[3][];
        jagged[0] = new int[]{1};           // row 0 has 1 element
        jagged[1] = new int[]{2, 3};        // row 1 has 2 elements
        jagged[2] = new int[]{4, 5, 6};     // row 2 has 3 elements

        for (int[] row : jagged) {
            System.out.println(Arrays.toString(row));
        }
        // [1]
        // [2, 3]
        // [4, 5, 6]
    }

    // ARRAY vs ARRAYLIST — when to use which
    // Use Array when:
    //   size is fixed and known beforehand
    //   performance is critical
    //   storing primitives (int, double etc.)
    // Use ArrayList when:
    //   size changes dynamically
    //   need built-in methods like add, remove, contains
    //   working with objects

    // TIME COMPLEXITY OF ARRAY OPERATIONS
    // Access by index      → O(1) — instant, direct address calculation
    // Search (unsorted)    → O(n) — must check each element
    // Search (sorted)      → O(log n) — binary search
    // Insert at end        → O(1) — just put at next index
    // Insert at beginning  → O(n) — must shift all elements right
    // Delete at end        → O(1) — just reduce size
    // Delete at beginning  → O(n) — must shift all elements left


    public static void main(String[] args) {

        System.out.println("=== Syntax Demo ===");
        arraySyntax();

        System.out.println("\n=== Index Demo ===");
        indexDemo();

        System.out.println("\n=== String Array + null Demo ===");
        stringArrayDemo();
        nullDemo();

        System.out.println("\n=== for-each Demo ===");
        forEachDemo();

        System.out.println("\n=== toString Demo ===");
        toStringDemo();

        System.out.println("\n=== Array of Objects Demo ===");
        arrayOfObjects();

        System.out.println("\n=== Array Passing Demo ===");
        arrayPassingDemo();

        System.out.println("\n=== 2D Array Demo ===");
        twoDArrayDemo();

        System.out.println("\n=== ArrayList Demo ===");
        arrayListDemo();

        System.out.println("\n=== Array Functions Demo ===");
        arrayFunctions();

        System.out.println("\n=== Jagged Array Demo ===");
        jaggedArray();
    }
}
