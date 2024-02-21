import java.io.*;
import java.util.*;

public class T9PredictiveText {

    private static final String[] KEYS = {
        "abc", "def", "ghi", "jkl", "mno", "prs", "tuv", "xyz", "åäö"
    };

    private Map<String, List<String>> dictionary = new HashMap<>();

    // Populate the dictionary from a .txt file
    public void populateDictionary(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String encoded = encode(line.trim());
                dictionary.putIfAbsent(encoded, new ArrayList<>());
                dictionary.get(encoded).add(line.trim());
            }
        }
    }

    // Encode a word into its T9 representation
    public String encode(String word) {
        StringBuilder encoded = new StringBuilder();
        for (char c : word.toCharArray()) {
            for (int i = 0; i < KEYS.length; i++) {
                if (KEYS[i].indexOf(c) != -1) {
                    encoded.append(i + 1);
                    break;
                }
            }
        }
        return encoded.toString();
    }

    // Decode a T9 representation into a list of possible words
    public List<String> decode(String encoded) {
        return dictionary.getOrDefault(encoded, Collections.emptyList());
    }

    public static void main(String[] args) throws IOException {
        T9PredictiveText t9 = new T9PredictiveText();

        // Populate the dictionary from a .txt file
        t9.populateDictionary("/Users/ammaralzeno/Downloads/kelly.txt");

        // Test encoding
        String word = "vanlig";
        System.out.println("Encoded: " + t9.encode(word));
        String word2 = "i";
        System.out.println("Encoded: " + t9.encode(word2));
        String word3 = "ord";
        System.out.println("Encoded: " + t9.encode(word3));

        // Test decoding
        String encoded = "324";
        System.out.println("Decoded: " + t9.decode(encoded));
    }
}
