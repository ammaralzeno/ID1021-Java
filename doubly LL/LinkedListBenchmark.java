
import java.util.Arrays;
import java.util.Random;

public class LinkedListBenchmark {



    public static void benchmark(DoublyLinkedList doublyList, SinglyLinkedList singlyList, int n, int k, long[] resultsDoubly, long[] resultsSingly) {
        Random rand = new Random();
        int[] randomIndices = new int[k];

        for (int run = 0; run < 100; run++) {
            // Reset for every run
            doublyList = new DoublyLinkedList();
            singlyList = new SinglyLinkedList();

            // Random indices
            for (int i = 0; i < k; i++) {
                randomIndices[i] = rand.nextInt(n);
            }

           DoublyLinkedList.Node[] doublyCellArray = new DoublyLinkedList.Node[n];
        for (int i = 0; i < n; i++) {
            doublyCellArray[i] = doublyList.new Node(i);
            doublyList.insertFirst(doublyCellArray[i]);
        }
        long startTimeDoubly = System.nanoTime();
        for (int i = 0; i < k; i++) {
            DoublyLinkedList.Node node = doublyCellArray[randomIndices[i]];
            doublyList.unlink(node);
            doublyList.insertFirst(node);
        }
        long endTimeDoubly = System.nanoTime();
            resultsDoubly[run] = endTimeDoubly - startTimeDoubly;

            SinglyLinkedList.Node[] singlyCellArray = new SinglyLinkedList.Node[n];
        for (int i = 0; i < n; i++) {
            singlyCellArray[i] = singlyList.new Node(i);
            singlyList.insertFirst(singlyCellArray[i]);
        }
        long startTimeSingly = System.nanoTime();
        for (int i = 0; i < k; i++) {
            SinglyLinkedList.Node node = singlyCellArray[randomIndices[i]];
            singlyList.unlink(node);
            singlyList.insertFirst(node);
        }
        long endTimeSingly = System.nanoTime();
            resultsSingly[run] = endTimeSingly - startTimeSingly;
        }
    }

    public static long median(long[] arr) {
        Arrays.sort(arr);
        int length = arr.length;
        if (length % 2 == 0) {
            return (arr[length / 2 - 1] + arr[length / 2]) / 2;
        } else {
            return arr[length / 2];
        }
    }

    public static void main(String[] args) {
        int n = 100000; // Sample size for the list
        int k = 10000;   // Number of random cells to unlink and insert

        DoublyLinkedList doublyList = new DoublyLinkedList();
        SinglyLinkedList singlyList = new SinglyLinkedList();

        long[] resultsDoubly = new long[100];
        long[] resultsSingly = new long[100];

        benchmark(doublyList, singlyList, n, k , resultsDoubly, resultsSingly);

        System.out.println("Median time taken for " + k + " unlink and insert operations in DoublyLinkedList: " + median(resultsDoubly) + " ns");
        System.out.println("Median time taken for " + k + " unlink and insert operations in SinglyLinkedList: " + median(resultsSingly) + " ns");
    }
}
