package oop.Day29.packages.b;

// ── ACCESS MODIFIERS PREVIEW ──────────────────────────────────────────────
// public class → accessible from ANY package
// public static void message() → can be called without creating a Message object
//   and accessible from other packages (used by Greeting in package a)

public class Message {


    // ── PUBLIC STATIC METHOD ───────────────────────────────────────────────
    // public  → accessible from other packages (Greeting in package a imports this)
    // static  → called on the class itself, no object needed: Message.message()
    //           or with static import: message()
    // This method is what gets imported via: import static oop.Day29.packages.b.message.message
    public static void message() {
        System.out.println("Hi I am awesome");
    }
}