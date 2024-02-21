
// An abstract class representing a stack that can hold integers.
abstract class Stack {
    
    protected int[] array;        // Array to hold the stack elements
    protected int stackPointer;   // Index of the top element in the stack
    protected int maxSize;        // Maximum capacity of the stack

    // Constructor to initialize the stack with a given maximum size.
    public Stack(int size) {
        maxSize = size;           // Set the maximum capacity of the stack
        array = new int[maxSize]; // Create an array to hold the elements
        stackPointer = 0;        // Initialize the stack pointer to 0
    }

    // Abstract method to push an integer value onto the stack.
    public abstract void push(int value);

    // Abstract method to pop and return an integer value from the stack.
    public abstract int pop();
}
