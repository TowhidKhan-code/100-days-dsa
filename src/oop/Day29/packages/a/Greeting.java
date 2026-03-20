package oop.Day29.packages.a;

// ── IMPORT STATEMENT ──────────────────────────────────────────────────────
// import tells Java where to find a class or method from another package
// Without import, you would have to write the full path every time:
//   oop.Day29.packages.b.Message.message()
// With import, you can just write: message()

// ── STATIC IMPORT ─────────────────────────────────────────────────────────
// Normal import:  import oop.Day29.packages.b.Message;  → lets you use Message.message()
// Static import:  import static oop.Day29.packages.b.message.message;
//                 → lets you call message() directly without the class name
// Use static import for utility methods you call frequently (Math.sqrt, etc.)
import static oop.Day29.packages.b.Message.message;


public class Greeting {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        // Calling message() directly because of static import above
        // Without static import this would be: Message.message()
        message();
    }
}