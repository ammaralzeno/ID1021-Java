

// A concrete subclass of Stack representing a stack with a fixed size.
class FixedSizeStack extends Stack {

    // Constructor that initializes the FixedSizeStack with a given size.
    public FixedSizeStack(int size) {
        super(size); // Call the constructor of the parent class (Stack) with the specified size.
    }

    // Implementation of the push method to add an element to the stack.
    @Override
    public void push(int value) {
        if (stackPointer < maxSize - 1) {    // Check if there's space in the array.
            stackPointer++;                  // Move the stack pointer up.
            array[stackPointer] = value;      // Add the value to the array at the stack pointer.
        } else {
            System.out.println("Stack is full. Cannot push " + value);
        }
    }

    // Implementation of the pop method to remove and return an element from the stack.
    @Override
    public int pop() {
        if (stackPointer >= 0) {             // Check if the stack is not empty.
            int poppedValue = array[stackPointer]; // Get the value at the current stack pointer.
            stackPointer--;                  // Move the stack pointer down.
            return poppedValue;               // Return the popped value.
        } else {
            System.out.println("Stack is empty. Cannot pop.");
            return -1; // Return a default value indicating failure (stack empty).
        }
    }
}
