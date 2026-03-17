package recursion;

import java.util.ArrayList;

public class Day25Permutations {

    // ══════════════════════════════════════════════════════════════════════
    // KEY IDEA — Insertion Approach for Permutations
    // ══════════════════════════════════════════════════════════════════════
    // Take first char of up, insert it at every possible position in p.
    // p.substring(0,i) = left of insertion point   (f)
    // p.substring(i)   = right of insertion point  (s)
    // f + ch + s       = ch inserted at position i

    // ══════════════════════════════════════════════════════════════════════
    // Q1 : Print all permutations
    // ══════════════════════════════════════════════════════════════════════
    static void permutation(String p, String up) {
        if (up.isEmpty()) {
            System.out.println(p);
            return;
        }
        char ch = up.charAt(0);
        for (int i = 0; i <= p.length(); i++) {
            String f = p.substring(0, i);
            String s = p.substring(i, p.length());
            permutation(f + ch + s, up.substring(1));
        }
    }

    // ══════════════════════════════════════════════════════════════════════
    // Q2 : Return ArrayList of permutations
    // ══════════════════════════════════════════════════════════════════════
    static ArrayList<String> permutationList(String p, String up) {
        if (up.isEmpty()) {
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }
        ArrayList<String> ans = new ArrayList<>();
        char ch = up.charAt(0);
        for (int i = 0; i <= p.length(); i++) {
            String f = p.substring(0, i);
            String s = p.substring(i, p.length());
            ans.addAll(permutationList(f + ch + s, up.substring(1)));
        }
        return ans;
    }

    // ══════════════════════════════════════════════════════════════════════
    // Q3 : Count number of permutations
    // ══════════════════════════════════════════════════════════════════════
    static int permutationCount(String p, String up) {
        if (up.isEmpty()) return 1;
        int count = 0;
        char ch = up.charAt(0);
        for (int i = 0; i <= p.length(); i++) {
            String f = p.substring(0, i);
            String s = p.substring(i, p.length());
            count += permutationCount(f + ch + s, up.substring(1));
        }
        return count;
    }

    // ══════════════════════════════════════════════════════════════════════
    // LC 17 : Letter Combinations of a Phone Number
    // ══════════════════════════════════════════════════════════════════════
    // Math.min(digit*3, 26) caps the loop at 26
    // WHY: digit 9 → end = 9*3 = 27 → tries 'a'+26 = '{' (not a letter)
    //      Math.min(27, 26) = 26 → stops at 'z' correctly
    static void letterCom(String p, String up) {
        if (up.isEmpty()) {
            System.out.println(p);
            return;
        }
        int digit = up.charAt(0) - '0';
        int start = (digit - 1) * 3;
        int end   = Math.min(digit * 3, 26);
        for (int i = start; i < end; i++) {
            char ch = (char)('a' + i);
            letterCom(p + ch, up.substring(1));
        }
    }

    // Standard phone mapping (LC 17 original — 2=abc, 7=pqrs, 9=wxyz)
    static String[] phoneMap = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

    static void letterComStandard(String p, String up) {
        if (up.isEmpty()) { System.out.println(p); return; }
        String letters = phoneMap[up.charAt(0) - '0'];
        for (char ch : letters.toCharArray()) {
            letterComStandard(p + ch, up.substring(1));
        }
    }

    static ArrayList<String> letterComList(String p, String up) {
        ArrayList<String> ans = new ArrayList<>();
        if (up.isEmpty()) { ans.add(p); return ans; }
        String letters = phoneMap[up.charAt(0) - '0'];
        for (char ch : letters.toCharArray()) {
            ans.addAll(letterComList(p + ch, up.substring(1)));
        }
        return ans;
    }

    static int letterComCount(String p, String up) {
        if (up.isEmpty()) return 1;
        int count = 0;
        String letters = phoneMap[up.charAt(0) - '0'];
        for (char ch : letters.toCharArray()) {
            count += letterComCount(p + ch, up.substring(1));
        }
        return count;
    }

    // ══════════════════════════════════════════════════════════════════════
    // LC 1155 : Dice Rolls — Standard 6-faced die
    // ══════════════════════════════════════════════════════════════════════
    static void dice(String p, int target) {
        if (target == 0) { System.out.println(p); return; }
        for (int i = 1; i <= 6 && i <= target; i++) {   // FIXED: cap at 6
            dice(p + i, target - i);
        }
    }

    static ArrayList<String> diceList(String p, int target) {
        ArrayList<String> ans = new ArrayList<>();
        if (target == 0) { ans.add(p); return ans; }
        for (int i = 1; i <= 6 && i <= target; i++) {
            ans.addAll(diceList(p + i, target - i));
        }
        return ans;
    }

    // ══════════════════════════════════════════════════════════════════════
    // Dice with custom faces
    // ══════════════════════════════════════════════════════════════════════
    static void diceCustomFace(String p, int target, int face) {
        if (target == 0) { System.out.println(p); return; }
        for (int i = 1; i <= face && i <= target; i++) {
            diceCustomFace(p + i, target - i, face);
        }
    }

    static ArrayList<String> diceCustomFaceList(String p, int target, int face) {
        ArrayList<String> ans = new ArrayList<>();
        if (target == 0) { ans.add(p); return ans; }
        for (int i = 1; i <= face && i <= target; i++) {
            ans.addAll(diceCustomFaceList(p + i, target - i, face));
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println("=== Permutations of 'abc' ===");
        permutation("", "abc");

        System.out.println("\n=== Permutation List ===");
        System.out.println(permutationList("", "abc"));

        System.out.println("\n=== Permutation Count ===");
        System.out.println(permutationCount("", "abc"));   // 6

        System.out.println("\n=== Letter Combinations '23' (standard) ===");
        letterComStandard("", "23");

        System.out.println("\n=== Letter Combinations List ===");
        System.out.println(letterComList("", "23"));

        System.out.println("\n=== Letter Combinations Count ===");
        System.out.println(letterComCount("", "23"));      // 9

        System.out.println("\n=== Dice (6-faced) target=4 ===");
        dice("", 4);

        System.out.println("\n=== Dice List ===");
        System.out.println(diceList("", 4));

        System.out.println("\n=== Dice Custom Face (10-faced) target=7 ===");
        diceCustomFace("", 7, 10);

        System.out.println("\n=== Dice Custom Face List ===");
        System.out.println(diceCustomFaceList("", 7, 10));
    }
}