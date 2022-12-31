package BOJ.CodePlus.DataStructure;

import java.util.Scanner;
import java.util.Stack;

public class PostfixExpression2_1935 {
    public static double calculate(char op, double b, double a) {
        if(op == '+') {
            return a + b;
        }
        else if(op == '-') {
            return a - b;
        }
        else if(op == '*') {
            return a * b;
        }
        return a / b;
    }
    public static double computerPostfix(String postfix, double[] operand) {
        Stack<Double> stack = new Stack<>();

        for (char ch : postfix.toCharArray()) {
            if(Character.isUpperCase(ch)) {
                stack.push(operand[(int)(ch - 'A')]);
            }
            else {
                stack.push(calculate(ch, stack.pop(), stack.pop()));
            }
        }

        return stack.pop();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        String postfix = in.next();
        double[] operand = new double[N];
        for(int i = 0; i < N; i++) {
            operand[i] = in.nextInt();
        }
        System.out.printf("%.2f%n", computerPostfix(postfix, operand));
    }
}
