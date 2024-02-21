import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class T9 {
    private Node root;

    public T9() {
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
        if (w >= 'a' && w <= 'p') {
            return w - 'a';
        } else if (w >= 'r' && w <= 'z') {
            return w - 'a' - 1; // Subtracting 1 to account for 'q'
        } else if (w == 'å') {
            return 24;
        } else if (w == 'ä') {
            return 25;
        } else if (w == 'ö') {
            return 26;
        }
        return -1;
    }
    
    

    private static char charFromCode(int code) {
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

    private static int indexFromKey(char key) {
        return key - '1';
    }

    private static char keyFromChar(char w) {
        int code = code(w);
        if (code == -1) return '?';
        return (char) ('1' + (code / 3));
    }

    // Inside the T9 class:

    public void add(String word) {
        Node current = root;
        for (char w : word.toCharArray()) {
            int index = code(w);
            if (index == -1) {
                System.out.println("Error with word: " + word + ", character: " + w);
                return; // Skip this word.
            }
            if (current.next[index] == null) {
                current.next[index] = new Node();
            }
            current = current.next[index];
        }
        current.valid = true;
    }
    


// Inside the T9 class:

public boolean wordExists(String word) {
    Node current = root;
    for (char w : word.toCharArray()) {
        int index = code(w);
        if (current.next[index] == null) {
            return false;
        }
        current = current.next[index];
    }
    return current.valid;
}


public List<String> decode(String sequence) {
    List<String> results = new ArrayList<>();
    collect(root, "", sequence, 0, results);
    return results;
}

private void collect(Node node, String currentWord, String sequence, int seqIndex, List<String> results) {
    if (node == null) return;

    if (seqIndex == sequence.length()) {
        if (node.valid) {
            results.add(currentWord);
            System.out.println("Found word: " + currentWord);
        }
        return;
    }

    int index = indexFromKey(sequence.charAt(seqIndex));
    for (int i = 0; i < 3; i++) {
        int branchIndex = index * 3 + i;
        if (branchIndex < 27) {
            collect(node.next[branchIndex], currentWord + charFromCode(branchIndex), sequence, seqIndex + 1, results);
        }
    }
}




// Inside the T9 class:

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

    // 1. Populate the trie with words from a sample file.
    String sampleFilePath = "/Users/ammaralzeno/Downloads/kelly.txt"; // Replace with the path to your file.
    t9.populateTrie(sampleFilePath);

    // 2. Sample words for testing.
    String[] sampleWords = {"svenska", "i", "vanlig", "ord"};

    for (String word : sampleWords) {
        System.out.println("Testing word: " + word);

        // Encode the word into a key sequence.
        StringBuilder encoded = new StringBuilder();
        for (char c : word.toCharArray()) {
            encoded.append(keyFromChar(c));
        }
        System.out.println("Encoded sequence: " + encoded);

        // Decode the key sequence to get possible words.
        List<String> decodedWords = t9.decode(encoded.toString());
        System.out.println("Decoded possibilities: " + decodedWords);

        // Check if the original word is in the decoded possibilities.
        if (decodedWords.contains(word)) {
            System.out.println("Original word found in decoded possibilities!");
        } else {
            System.out.println("Original word NOT found in decoded possibilities.");
        }

        System.out.println("-------------------------------------------------");
    }

}

}
