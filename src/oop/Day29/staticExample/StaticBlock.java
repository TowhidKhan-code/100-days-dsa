
    package oop.Day29.staticExample;

    public class StaticBlock {
        static int a = 4;
        static int b;

        // ── STATIC BLOCK ──────────────────────────────────────────────────────
        // Runs ONCE when the class is first loaded into memory (not per object)
        // Used to initialise static variables that need logic (not just a direct value)
        // Order: static variables initialised first, then static block runs
        // Even if you create 100 objects, this block runs exactly once
        // will only run once, when  the first obj is created i.e. when the class is loaded for the first time
        static {
            System.out.println("I am in static block");
            b = a * 5;     // b = 4 * 5 = 20
        }

        public static void main(String[] args) {
            StaticBlock obj = new StaticBlock();                          // static block runs here (first load)
            System.out.println(StaticBlock.a + " " + StaticBlock.b);    // 4 20

            StaticBlock.b += 3;                                           // b = 23

            System.out.println(StaticBlock.a + " " + StaticBlock.b);    // 4 23

            StaticBlock obj2 = new StaticBlock();                         // static block does NOT run again
            System.out.println(StaticBlock.a + " " + StaticBlock.b);    // 4 23 — unchanged
        }
    }
