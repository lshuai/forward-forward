package snapchat;
import java.util.*;
import java.util.stream.*;

public class EvaluateExpression {
    public static void main(String[] args) {
        //System.out.println(new EvaluateExpression().toReversePolish("((2 + 1) * 3)"));
        EvaluateExpression eval = new EvaluateExpression();
        System.out.println(String.join(" ", eval.toReversePolish("- (-1 + 1) + 1")));
        System.out.println(eval.evalRPN(new String[] {"0", "3", "/"}));
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (isStrOperator(token)) 
                stack.push(compute(token, stack.pop(), stack.pop()));
            else
                stack.push(Integer.valueOf(token));
        }
        return stack.pop();
    }

    public String[] toReversePolish(String s) {
        if (s == null || s.length() == 0)
            return null;

        Queue<String> queue = new LinkedList<String>();
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                int start = i;
                while (i < s.length() && Character.isDigit(s.charAt(i)))
                    i++;
                queue.offer(s.substring(start, i));
            }
            if (i >= s.length())
                break;
            char token = s.charAt(i);
            if (isOperator(token)) {
                while (!stack.isEmpty() && isOperator(stack.peek()) && isHigherOrEqual(stack.peek(), token)) 
                    queue.offer(stack.pop().toString());
                stack.push(token);
            }
            else if (token == '(')
                stack.push(token);
            else if (token == ')') {
                while (stack.peek() != '(') 
                    queue.offer(stack.pop().toString());
                stack.pop();
            }
        }
        while (!stack.isEmpty())
            queue.offer(stack.pop().toString());
        return queue.toArray(new String[queue.size()]);
    }

    private static boolean isHigherOrEqual(char o1, char o2) {
        if (o1 == '*' || o1 == '/')
            return true;
        return o2 == '+' || o2 == '-';
    }

    private static boolean isOperator(char token) {
        return token == '+' || token == '-' || token == '*' || token == '/';
    }

    private static boolean isStrOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }
    
    private static int compute(String op, int num2, int num1) {
        switch(op) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            default:
                return num1 / num2;
        }
    }
}
