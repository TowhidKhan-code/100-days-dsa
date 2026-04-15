package Day52.huffmancoding;

public class Main {
    static void main(String[] args) throws Exception {
        String str = "abbccda";
        HuffmanCoder hf = new HuffmanCoder(str);
        String cs = hf.encode(str);
        System.out.println(cs);
        String dcs = hf.decode(cs);
        System.out.println(dcs);
    }
}
