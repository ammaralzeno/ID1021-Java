import java.util.Iterator;
import java.util.Stack;

// The main class representing a binary tree.
public class BinaryTree implements Iterable<Integer> {

    // Inner class for the binary tree node.
    public class Node {
        public Integer key;   // The key by which we order our binary tree
        public Integer value; // The associated value with each key
        public Node left, right; // Pointers to the left and right children

        // Constructor to initialize the node with a key and value
        public Node(Integer key, Integer value) {
            this.key = key;
            this.value = value;
            this.left = this.right = null; // Start with no children
        }

        // Depth-first print (In-order traversal)
        public void print() {
            if(left != null)
                left.print(); // Recursively print left subtree
            System.out.println(" key: " + key + "\tvalue: " + value); // Print the current node
            if(right != null)
                right.print(); // Recursively print right subtree
        }
    }

    private Node root; // The root node of the tree

    // Constructor for the binary tree. Initializes with no nodes.
    public BinaryTree() {
        root = null;
    }

    // Print the binary tree in in-order traversal
    public void printTree() {
        if (root != null) {
            root.print();
        } else {
            System.out.println("The tree is empty."); // Notify if the tree has no nodes
        }
    }

    // Public method to add a key-value pair to the tree
    public void add(Integer key, Integer value) {
        root = addRec(root, key, value);
    }

    // Recursive helper method for add. It returns the new root of the tree/subtree.
    private Node addRec(Node root, Integer key, Integer value) {
        if (root == null) {
            root = new Node(key, value); // Create a new node if we're at a null spot
            return root;
        }
        
        // Decide where to add the new node
        if (key.equals(root.key)) {
            root.value = value; // Update the value if key is found
        } else if (key < root.key) {
            root.left = addRec(root.left, key, value); // Recurse to the left
        } else {
            root.right = addRec(root.right, key, value); // Recurse to the right
        }

        return root; // Return the root of the tree/subtree
    }

    // Public method to look up a value by its key in the tree
    public Integer lookup(Integer key) {
        return lookupRec(root, key);
    }

    // Recursive helper method for lookup
    private Integer lookupRec(Node root, Integer key) {
        if (root == null) {
            return null; // Key was not found
        }
        if (key.equals(root.key)) {
            return root.value; // Key was found, return its associated value
        } else if (key < root.key) {
            return lookupRec(root.left, key); // Recurse to the left
        } else {
            return lookupRec(root.right, key); // Recurse to the right
        }
    }

    // Implement the Iterable interface. This allows for foreach style iteration over the tree nodes.
    @Override
    public Iterator<Integer> iterator() {
        return new TreeIterator();
    }

    // Private inner class for tree iteration using in-order traversal.
    private class TreeIterator implements Iterator<Integer> {
        private Node next; // The next node to be returned by the iterator
        private Stack<Node> stack = new Stack<>(); // Stack to keep track of nodes

        public TreeIterator() {
            next = root;
            while (next != null) {
                stack.push(next);
                next = next.left; // Go as left as possible
            }
            if (!stack.isEmpty()) {
                next = stack.pop();
            }
        }

        // Check if there are more nodes to iterate over
        @Override
        public boolean hasNext() {
            return next != null || !stack.isEmpty();
        }

        // Get the next node's value
        @Override
        public Integer next() {
            Node curr = next;
            
            // Move to the right child, if available
            if (curr.right != null) {
                next = curr.right;
                while (next != null) {
                    stack.push(next);
                    next = next.left; // Again, go as left as possible
                }
            }

            if (!stack.isEmpty()) {
                next = stack.pop(); // Pop from the stack if not empty
            } else {
                next = null;
            }

            return curr.value; // Return the current node's value
        }

        // We don't support removal through the iterator
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // Utility method to print the tree in in-order
    public void inorder() {
        inorderRec(root);
    }

    // Recursive helper for the in-order print
    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left); // Print left subtree
            System.out.println(root.key + " => " + root.value); // Print current node
            inorderRec(root.right); // Print right subtree
        }
    }

    // ... (Other utility methods can be added as required)

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.add(50, 1);
        tree.add(30, 2);
        tree.add(20, 3);
        tree.add(40, 4);
        tree.add(70, 5);
        tree.add(60, 6);
        tree.add(80, 7);
        
        // Print the tree
        tree.inorder();

        BinaryTree treeBenchmark = new BinaryTree();
        long startTime = System.nanoTime();

        // Adding random data
        for (int i = 0; i < 10000; i++) {
            int randomKey = (int)(Math.random() * 10000);
            int randomValue = (int)(Math.random() * 10000);
            treeBenchmark.add(randomKey, randomValue);
        }

        // Lookup
        for (int i = 0; i < 5000; i++) {
            int randomKey = (int)(Math.random() * 10000);
            treeBenchmark.lookup(randomKey);
        }

        long endTime = System.nanoTime();
        System.out.println("Execution time in us : " + (endTime - startTime) / 1000);


          
                BinaryTree treeDepth = new BinaryTree();
                treeDepth.add(50, 5);
                treeDepth.add(30, 3);
                treeDepth.add(70, 7);
                treeDepth.add(20, 2);
                treeDepth.add(40, 4);
                treeDepth.add(60, 6);
                treeDepth.add(80, 8);
                
                // Print the tree in depth-first manner
                treeDepth.printTree();
            

                BinaryTree treeIterate = new BinaryTree();

                treeIterate.add(5,105);
                treeIterate.add(2,102);
                treeIterate.add(7,107);
                treeIterate.add(1,101);
                treeIterate.add(8,108);
                treeIterate.add(6,106);
                treeIterate.add(3,103);

        for (int i : treeIterate){

            System.out.println("next value " + i);
        }
           

        // ... (Other tests can be added as required)
    }
}
