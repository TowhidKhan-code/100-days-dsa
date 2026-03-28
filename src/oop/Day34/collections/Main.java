package oop.Day34.collections;

import java.util.*;
/*
============================================================
DAY 34 - COLLECTIONS FRAMEWORK IN JAVA
============================================================
*/

public class Main {
    /*
    ============================================================
    COLLECTIONS FRAMEWORK

    Definition:
    The Collections Framework is a unified architecture in Java
    that provides a set of interfaces and classes to store and
    manipulate groups of objects (collections).

    Why do we need Collections Framework?
    - Arrays have fixed size → cannot grow or shrink dynamically
    - Arrays can only store one type of data (homogeneous)
    - No built-in methods for common operations (search, sort, etc.)
    - Collections Framework solves these limitations with flexible,
      reusable, and high-performance data structures.

    Hierarchy (Important):

    Collection (Interface)
        ├── List (Interface)      → Ordered, allows duplicates
        │     ├── ArrayList
        │     ├── LinkedList
        │     └── Vector (Legacy)
        ├── Set (Interface)       → No duplicates
        │     ├── HashSet
        │     ├── LinkedHashSet
        │     └── TreeSet
        └── Queue (Interface)     → FIFO behavior

    Map (Separate Interface)
        ├── HashMap
        ├── LinkedHashMap
        ├── TreeMap
        └── Hashtable (Legacy)
    ============================================================
    */

    public static void main(String[] args) {

        // List - Ordered collection that allows duplicates
        List<Integer> list = new ArrayList<>();

        // LinkedList - Good for frequent insertions/deletions
        List<Integer> list2 = new LinkedList<>();

        // Vector - Legacy class, Synchronized (Thread-safe)
        List<Integer> vector = new Vector<>();

        vector.add(45);
        vector.add(5);
        vector.add(15);
        vector.add(56);

        System.out.println("Vector: " + vector);

        /*
        Key Difference:
        - ArrayList → Fast, not synchronized (not thread-safe)
        - Vector    → Slower, synchronized (thread-safe)
        */
    }
}