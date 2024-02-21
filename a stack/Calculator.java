
public class Calculator {
    Item[] expr;
    int ip;

    // FixedSizeStack stack = new FixedSizeStack(10);
    DynamicStack stack = new DynamicStack();

    public Calculator(Item[] expr) {
      this.expr = expr;
      this.ip = 0;
      }


  public int run() {
  
    while ( ip < expr.length ) {
    step(); }
    return stack.pop();
  }

  public void step() {
    Item nxt = expr[ip++];

    switch(nxt.Type()) {
      
        case ADD: 
      
        {
        int y = stack.pop();
        int x = stack.pop();
        stack.push(x + y);
        break;
        }

        case SUB: 
      
        {
        int y = stack.pop();
        int x = stack.pop();
        stack.push(x - y);
        break;
        }

        case DIV: 
      
        {
        int y = stack.pop();
        int x = stack.pop();
        stack.push(x / y);
        break;
        }

        case MUL: 
      
        {
        int y = stack.pop();
        int x = stack.pop();
        stack.push(x * y);
        break;
        }

        case VALUE: 

        {
        int value = nxt.Value(); // Get the value from the "VALUE" item
        stack.push(value); // Push the value onto the stack
        break;
        }


      }
    }

    public static void main(String[] args) { 
      
      // 10 + 2 * 5
      // 10 2 5 * +   in reversed Polish notation

      Item[] expr = {

          Item.Value(3),
          Item.Value(4),
          Item.Add(),
          Item.Value(2),
          Item.Value(5),
          Item.Add(),
          Item.Mul(),
      
      

          };
          
      
      Calculator calc = new Calculator(expr);
      int res = calc.run();
      
      System.out.println(" Calculator: res = " + res);

    

      } 


 
  


}

