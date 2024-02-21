public class SinglyLinkedList {

    class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;

    public SinglyLinkedList() {
        head = null;
    }

    // Insert a node as the first cell in the list.
    public void insertFirst(Node node) {
        node.next = head;
        head = node;
    }

    // Unlink a given node from the list.
    public void unlink(Node node) {
        if (head == null || node == null) {
            return;
        }

        if (head == node) {
            head = head.next;
            return;
        }

        // Find the previous node
        Node prev = head;
        while (prev.next != null && prev.next != node) {
            prev = prev.next;
        }

        // If node was not present in the list
        if (prev.next == null) {
            return;
        }

        // Unlink the node from the list
        prev.next = prev.next.next;
    }

    // Utility function to print list
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        Node firstNode = list.new Node(10);
        list.insertFirst(firstNode);
        Node secondNode = list.new Node(20);
        list.insertFirst(secondNode);
        Node thirdNode = list.new Node(30);
        list.insertFirst(thirdNode);
        list.printList(); // 30 20 10

        list.unlink(secondNode);
        list.printList(); // 30 10

        Node fourthNode = list.new Node(40);
        list.insertFirst(fourthNode);
        list.printList(); // 40 30 10
    }
}
