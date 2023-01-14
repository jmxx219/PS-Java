package BOJ.CodePlus.DataStructure;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

public class PostfixExpression_1918 {
    public static int priority(char op) {
        if(op == '*' || op == '/') return 2;
        else if(op == '+' || op == '-') return 1;
        return 0;
    }
    public static String infixToPostfix(String infix) {
        Queue<Character> queue = new LinkedList<>();
        Stack<Character> stack = new Stack<>();

        for(char curr : infix.toCharArray()){
            if(Character.isUpperCase(curr)) {
                queue.add(curr);
            }
            else if(curr == '(') {
                stack.push(curr);
            }
            else if(curr == ')') {
                while (!stack.empty() && stack.peek() != '(') {
                    queue.add(stack.pop());
                }
                stack.pop();
            }
            else if(curr == '+' || curr == '-' || curr == '*' || curr == '/') {
                while (!stack.empty() && priority(stack.peek()) >= priority(curr)) {
                    queue.add(stack.pop());
                }
                stack.push(curr);
            }
        }
        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }

        return queue.stream().map(Objects::toString).collect(Collectors.joining());
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String infix = in.next();
        System.out.println(infixToPostfix(infix));
    }
}
