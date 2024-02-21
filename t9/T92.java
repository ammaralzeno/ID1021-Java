import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class T92 {

    private Node root;

    public T92() {
        root = new Node();
    }

    private class Node {
        public Node[] next;
        public boolean valid;

        public Node() {
            next = new Node[27];
            valid = false;
        }
    }

    private static int code(char w) {
        switch (w) {
            case 'a': return 0;
            case 'b': return 1;
            case 'c': return 2;
            case 'd': return 3;
            case 'e': return 4;
            case 'f': return 5;
            case 'g': return 6;
            case 'h': return 7;
            case 'i': return 8;
            case 'j': return 9;
            case 'k': return 10;
            case 'l': return 11;
            case 'm': return 12;
            case 'n': return 13;
            case 'o': return 14;
            case 'p': return 15;
            case 'r': return 16;
            case 's': return 17;
            case 't': return 18;
            case 'u': return 19;
            case 'v': return 20;
            case 'x': return 21;
            case 'y': return 22;
            case 'z': return 23;
            case 'å': return 24;
            case 'ä': return 25;
            case 'ö': return 26;
            // ... add cases for other characters
            default: return -1;
        }
    }

    private static char character(int code) {
        switch (code) {
            case 0: return 'a';
            case 1: return 'b';
            case 2: return 'c';
            case 3: return 'd';
            case 4: return 'e';
            case 5: return 'f';
            case 6: return 'g';
            case 7: return 'h';
            case 8: return 'i';
            case 9: return 'j';
            case 10: return 'k';
            case 11: return 'l';
            case 12: return 'm';
            case 13: return 'n';
            case 14: return 'o';
            case 15: return 'p';
            case 16: return 'r';
            case 17: return 's';
            case 18: return 't';
            case 19: return 'u';
            case 20: return 'v';
            case 21: return 'x';
            case 22: return 'y';
            case 23: return 'z';
            case 24: return 'å';
            case 25: return 'ä';
            case 26: return 'ö';
            default: return '?';
        }
    }

    private static int keyToIndex(char key) {
        return key - '1';
    }

    private static char characterToKey(char character) {
        int code = code(character);
        return (char) ('1' + (code / 3));
    }

    public void add(String word) {
        Node current = root;
        for (char c : word.toCharArray()) {
            int index = code(c);
            if (current.next[index] == null) {
                current.next[index] = new Node();
            }
            current = current.next[index];
        }
        current.valid = true;
    }

    public List<String> decode(String sequence) {
        List<String> results = new ArrayList<>();
        collect(root, sequence, 0, "", results);
        return results;
    }

    private void collect(Node node, String sequence, int pos, String currentWord, List<String> results) {
        if (node == null) return;

        if (pos == sequence.length()) {
            if (node.valid) results.add(currentWord);
            return;
        }

        int index = keyToIndex(sequence.charAt(pos));
        for (int i = 0; i < 3; i++) {
            int branchIndex = index * 3 + i;
            collect(node.next[branchIndex], sequence, pos + 1, currentWord + character(branchIndex), results);
        }
    }

    
    public void populateTrie(String filename) {
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = br.readLine()) != null) {
            add(line.trim());
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public static void main(String[] args) {
       
            T9 t9 = new T9();
            String sampleFilePath = "/Users/ammaralzeno/Downloads/kelly.txt"; // Replace with the path to your file.
            t9.populateTrie(sampleFilePath);

            String[] sampleWords = {"svenska", "i", "vanlig", "ord", "hej", "toffel"};
        
        
            // Test encoding and decoding for each word
            for (String word : sampleWords) {
                StringBuilder encoded = new StringBuilder();
                for (char c : word.toCharArray()) {
                    encoded.append(characterToKey(c));
                }
                List<String> decodedWords = t9.decode(encoded.toString());
        
                System.out.println("Word: " + word);
                System.out.println("Encoded: " + encoded);
                System.out.println("Decoded possibilities: " + decodedWords);
                System.out.println("-------------------------------");
            }
        
        
    }
}
