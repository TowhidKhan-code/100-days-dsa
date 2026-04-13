// ============================================================
// DAY 50 - RABIN-KARP ALGORITHM (ROLLING HASH)
// ============================================================

package hashmaps;

public class Day50KarpRabin {

    /*
    ============================================================
    RABIN-KARP ALGORITHM
    ============================================================
    Definition:
    Rabin-Karp is a string pattern matching algorithm that uses
    a rolling hash to find all occurrences of a pattern in a text.
    Instead of comparing characters directly at each position,
    it compares hash values first and only does character comparison
    when hashes match.

    Why Rabin-Karp?
    - Naive search: O(n × m) — checks every position character by character
    - Rabin-Karp average: O(n + m) — hash comparison is O(1)
    - Especially powerful for MULTIPLE PATTERN SEARCH

    Core Idea:
    1. Compute hash of pattern once
    2. Compute hash of first window of text
    3. Slide window across text
    4. At each position: compare hashes
    5. If hashes match: verify with actual string comparison
    6. Update hash using ROLLING HASH (O(1) per slide)

    Hash Collisions:
    - Two different strings can produce the same hash (false positive)
    - This is handled by the .equals() verification step
    - Does NOT affect correctness, only performance in worst case

    Time Complexity:
    - Average: O(n + m)   → hash comparison O(1) per window
    - Worst:   O(n × m)   → if every window causes a hash collision
    - Space:   O(1)

    Comparison with Other String Algorithms:
    - Naive:        O(n × m)        simple but slow
    - Rabin-Karp:   O(n + m) avg    hashing, good for multi-pattern
    - KMP:          O(n + m) worst  failure function, no collisions
    - Boyer-Moore:  O(n/m) avg      sublinear, best for long patterns
    ============================================================
    */

    /*
    ============================================================
    POLYNOMIAL HASH FUNCTION
    ============================================================
    Formula:
    hash(s) = s[0]×P^0 + s[1]×P^1 + s[2]×P^2 + ... + s[m-1]×P^(m-1)

    Where:
    - P = prime number (we use 101)
    - s[i] = ASCII value of character at index i
    - Each character weighted by its position power

    Why Prime?
    - Prime base reduces collision probability
    - Better distribution of hash values
    - Common choices: 31, 53, 101, 131

    Example — hash("Ahmed"):
    A=65, h=104, m=109, e=101, d=100
    hash = 65×101^0 + 104×101^1 + 109×101^2 + 101×101^3 + 100×101^4
    ============================================================
    */
    private final int PRIME = 101;

    private double calculateHash(String str) {
        double hash = 0;
        for (int i = 0; i < str.length(); i++) {
            // Each char weighted by PRIME^position
            hash = hash + str.charAt(i) * Math.pow(PRIME, i);
        }
        return hash;
    }

    /*
    ============================================================
    ROLLING HASH — THE KEY INSIGHT
    ============================================================
    Problem: Recalculating hash from scratch = O(m) per slide
    Solution: UPDATE hash mathematically = O(1) per slide

    Old window: text[i ... i+m-1]
    New window: text[i+1 ... i+m]

    Step 1: Remove old character (text[i])
    → (hash - oldChar) / PRIME
    → oldChar was at position 0 with weight P^0 = 1
    → After dividing by P, all remaining weights shift down by 1
    → text[i+1] was P^1, becomes P^0 (now first in window)
    → text[i+2] was P^2, becomes P^1 (now second in window)

    Step 2: Add new character (text[i+m])
    → newHash + newChar × P^(m-1)
    → New char gets the highest position weight

    Formula:
    newHash = (oldHash - oldChar) / PRIME + newChar × PRIME^(m-1)
    ============================================================
    */
    private double updateHash(double prevHash, char oldChar, char newChar, int patternLength) {
        // Step 1: Remove old character from hash
        double newHash = (prevHash - oldChar) / PRIME;

        // Step 2: Add new character at the end
        newHash = newHash + newChar * Math.pow(PRIME, patternLength - 1);

        return newHash;
    }

    /*
    ============================================================
    SEARCH — MAIN ALGORITHM
    ============================================================
    Algorithm:
    1. Calculate hash of entire pattern (once)
    2. Calculate hash of first window (text[0...m-1])
    3. For each position i from 0 to n-m:
       a. If textHash == patternHash:
          → Possible match — verify with .equals()
          → This handles false positives (hash collisions)
       b. If not last window:
          → Roll the hash (O(1) update)

    Edge Cases:
    - Pattern longer than text: loop never runs
    - Pattern at very end: i <= n-m handles this (≤ not <)
    - Multiple occurrences: loop continues after finding match
    - Hash collision: .equals() verification handles it
    ============================================================
    */
    public void search(String text, String pattern) {
        int patternLength = pattern.length();

        // Compute hash once for entire pattern
        double patternHash = calculateHash(pattern);

        // Compute hash for first window of text
        double textHash = calculateHash(text.substring(0, patternLength));

        // Slide window from 0 to n-m (inclusive — pattern can be at last position)
        for (int i = 0; i <= text.length() - patternLength; i++) {

            if (textHash == patternHash) {
                // Hashes match — verify to handle collisions (false positives)
                if (text.substring(i, i + patternLength).equals(pattern)) {
                    System.out.println("Pattern found at: " + i);
                }
            }

            // Update hash for next window (don't update after last window)
            if (i < text.length() - patternLength) {
                textHash = updateHash(
                        textHash,
                        text.charAt(i),           // Outgoing character
                        text.charAt(i + patternLength), // Incoming character
                        patternLength
                );
            }
        }
    }

    /*
    ============================================================
    STEP BY STEP TRACE
    ============================================================
    text    = "TowhidAhmedKhan"
    pattern = "Ahmed"
    m = 5

    Initial: patternHash = hash("Ahmed")
             textHash    = hash("Towhi")

    i=0: hash("Towhi") ≠ patternHash → roll → window="owhid"
    i=1: hash("owhid") ≠ patternHash → roll → window="whidA"
    ...
    i=7: window="Ahmed"
         hash("Ahmed") == patternHash → verify → "Ahmed".equals("Ahmed") → TRUE
         PRINT: "Pattern found at: 7" ✓

    i=8: window="hmedK" → hash ≠ patternHash → roll
    ... loop ends

    IMPORTANT NOTE ON FLOATING POINT:
    Using double for hash values can cause precision issues
    for very long strings. Production code uses:
    - long instead of double
    - Modular arithmetic: hash % MOD (where MOD = large prime)
    - This prevents integer overflow too
    ============================================================
    */

    public static void main(String[] args) {
        Day50KarpRabin algo = new Day50KarpRabin();
        algo.search("TowhidAhmedKhan", "Ahmed");
        // Output: Pattern found at: 7

        // Test multiple occurrences
        algo.search("ababab", "ab");
        // Output: Pattern found at: 0
        //         Pattern found at: 2
        //         Pattern found at: 4

        // Test no match
        algo.search("hello", "world");
        // Output: (nothing)
    }
}