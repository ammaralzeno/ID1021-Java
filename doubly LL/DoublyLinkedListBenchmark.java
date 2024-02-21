import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DoublyLinkedListBenchmark {

    // The previously defined DoublyLinkedList class...

    public static void main(String[] args) {
        int n = 100000;  // Sample size for the list
        int k = 1000;    // Number of random cells to unlink and insert
        
        DoublyLinkedList list = new DoublyLinkedList();
        DoublyLinkedList.Node[] cellArray = new DoublyLinkedList.Node[n];
        
        // Populate the doubly linked list and the cell array
        for (int i = 0; i < n; i++) {
            cellArray[i] = list.new Node(i);
            list.insertFirst(cellArray[i]);
        }
        
        Random rand = new Random();
        int[] randomIndices = new int[k];
        for (int i = 0; i < k; i++) {
            randomIndices[i] = rand.nextInt(n);
        }
        
        // Benchmarking the unlink and insert operations for k random cells
        long startTime = System.nanoTime();
        for (int i = 0; i < k; i++) {
            DoublyLinkedList.Node node = cellArray[randomIndices[i]];
            list.unlink(node);
            list.insertFirst(node);
        }
        long endTime = System.nanoTime();
        
        long duration = TimeUnit.MICROSECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS);
        System.out.println("Time taken for " + k + " unlink and insert operations: " + duration + " microseconds.");
    }
}
