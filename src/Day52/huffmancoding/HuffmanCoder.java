package Day52.huffmancoding;

import java.util.*;

public class HuffmanCoder {
    HashMap<Character,String> encoder;
    HashMap<String,Character> decoder;

    private class Node implements Comparable<Node> {
        /*
        Node represents one entry in the Huffman tree.

        data: the character ('\0' for internal nodes)
        cost: frequency of character (or sum for internal nodes)
        left/right: children in Huffman tree

        Implements Comparable<Node> so Heap can compare nodes.
        compareTo by cost → min heap orders by frequency.
        Internal nodes (non-leaf) have data='\0' (null char).
        */
        Character data;
        int cost;
        Node left;
        Node right;

        public Node(Character data, int cost) {
            this.data = data;
            this.cost = cost;
            this.left = null;
            this.right = null;
        }

        @Override
        public int compareTo(Node other) {
            return this.cost - other.cost;
            // Negative: this < other → this comes first in min heap
            // Positive: this > other → other comes first
            // Zero: equal frequency
        }
    }

    public HuffmanCoder(String feeder) throws Exception{
        // PHASE 1: Count character frequencies
        HashMap<Character, Integer> fmap = new HashMap<>();
        for (int i = 0; i < feeder.length(); i++) {
            char cc = feeder.charAt(i);
            if (fmap.containsKey(cc)) {
                int ov = fmap.get(cc);
                ov += 1;
                fmap.put(cc, ov);
            } else {
                fmap.put(cc, 1);
            }
        }
        // Cleaner version: fmap.merge(cc, 1, Integer::sum)

        // PHASE 2: Build min heap with one node per character
        Heap<Node> minHeap = new Heap<>();
        Set<Map.Entry<Character, Integer>> entrySet = fmap.entrySet();
        for (Map.Entry<Character, Integer> entry : entrySet) {
            Node node = new Node(entry.getKey(), entry.getValue());
            minHeap.insert(node);
        }

        // PHASE 3: Build Huffman Tree
        // Repeatedly combine two lowest frequency nodes
        while (minHeap.size() != 1) {
            Node first = minHeap.remove();   // Smallest frequency
            Node second = minHeap.remove();  // Second smallest

            // Create internal node combining both
            Node newNode = new Node('\0', first.cost + second.cost);
            newNode.left = first;
            newNode.right = second;

            minHeap.insert(newNode);
            // Continue until only root remains
        }

        // PHASE 4: Extract root and build encoder/decoder maps
        Node ft = minHeap.remove();
        this.encoder = new HashMap<>();
        this.decoder = new HashMap<>();
        this.initEncoderDecoder(ft, "");
    }

    private void initEncoderDecoder(Node node, String osf) {
    /*
    osf = "output so far" — the binary code built so far

    DFS through Huffman tree:
    → Go left: append "0" to code
    → Go right: append "1" to code
    → At leaf: record the complete code

    Base case: node is null → return
    Leaf condition: left == null AND right == null
    → This is a character node → record its code

    osf builds the binary string as we traverse:
    Root → left: osf = "0"
    Root → left → left: osf = "00"
    Root → left → right: osf = "01"

    encoder: Character → binary string ("a" → "01")
    decoder: binary string → Character ("01" → "a")
    */
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            // Leaf node: record the code for this character
            this.encoder.put(node.data, osf);
            this.decoder.put(osf, node.data);
            return;
        }

        initEncoderDecoder(node.left, osf + "0");   // Left = 0
        initEncoderDecoder(node.right, osf + "1");  // Right = 1
    }

    public String encode(String source) {
    /*
    For each character in source:
    → Look up its binary code in encoder map
    → Append to result

    Time: O(n × average code length)
    Note: StringBuilder would be more efficient than String concatenation
    BitSet is mentioned in comment — represents actual bits not string
    */
        String ans = "";
        for (int i = 0; i < source.length(); i++) {
            ans = ans + encoder.get(source.charAt(i));
        }
        return ans;
    }

    public String decode(String codedString) {
    /*
    Scan encoded string bit by bit:
    → Build key one bit at a time
    → When key exists in decoder: found a character!
    → Append character to answer, reset key

    This works because codes are prefix-free:
    → No valid code is a prefix of another
    → First time key matches = correct character
    → No ambiguity possible

    Example: decoder = {"01":"a", "10":"b", "00":"d", "11":"c"}
    coded = "011010110001"
    key=""
    k='0': key="0"  → not in decoder
    k='1': key="01" → IN decoder! ans="a", key=""
    k='1': key="1"  → not in decoder
    k='0': key="10" → IN decoder! ans="ab", key=""
    ... and so on
    */
        String key = "";
        String ans = "";
        for (int i = 0; i < codedString.length(); i++) {
            key = key + codedString.charAt(i);
            if (decoder.containsKey(key)) {
                ans = ans + decoder.get(key);
                key = "";  // Reset for next character
            }
        }
        return ans;
    }
}

