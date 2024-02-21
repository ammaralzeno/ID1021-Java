

// A concrete subclass of Stack representing a dynamic stack that can grow and shrink.
class DynamicStack extends Stack {
    private static final int RESIZE_FACTOR = 2; // Factor by which the array is resized
    private static final double SHRINK_THRESHOLD = 0.25; // Shrink when stack is less than 25% full
    private static final int MIN_CAPACITY = 4; // Minimum initial and resized capacity

    // Constructor for the DynamicStack class.
    public DynamicStack() {
        super(MIN_CAPACITY); // Call the constructor of the parent class (Stack) with the minimum capacity.
    }

    // Implementation of the push method to add an element to the stack.
    @Override
    public void push(int value) {
        if (stackPointer == maxSize - 1) {
            // Resize the array if it's full
            resizeArray(maxSize * RESIZE_FACTOR);
        }
        
        stackPointer++;
        array[stackPointer] = value;
    }

    // Implementation of the pop method to remove and return an element from the stack.
    @Override
    public int pop() {
        if (stackPointer >= 0) {
            int poppedValue = array[stackPointer];
            stackPointer--;

            // Shrink the array if it's less than the shrink threshold
            if (stackPointer < (int) (maxSize * SHRINK_THRESHOLD) && maxSize > MIN_CAPACITY) {
                resizeArray(Math.max(MIN_CAPACITY, maxSize / RESIZE_FACTOR));
            }

            return poppedValue;
        } else {
            System.out.println("Stack is empty. Cannot pop.");
            return -1; // Return a default value indicating failure
        }
    }

    // Resize the array to a new specified size.
    private void resizeArray(int newSize) {
        
        int[] newArray = new int[newSize];
        
        // Determine the number of elements to copy
        int elementsToCopy = Math.min(array.length, newSize);
        
        // Copy elements from the old array to the new array
        for (int i = 0; i < elementsToCopy; i++) {
            newArray[i] = array[i];
        }
        
        // Update the array reference and maxSize
        array = newArray;
        maxSize = newSize;
    }
    
}
