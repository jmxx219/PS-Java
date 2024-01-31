package BOJ.Retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

public class 후위표기식_1918 {
    private static BufferedReader br;
    private static char[] infix;

    private static int priority(char c) {
        if(c == '*' || c == '/') return 2;
        else if(c == '+' || c == '-') return 1;
        return 0;
    }
    private static String solve() {
        Queue<Character> queue = new LinkedList<>();
        Stack<Character> stack = new Stack<>();

        for(char curr : infix) {
            if(Character.isUpperCase(curr)) queue.add(curr);
            else if(curr == '(') stack.add(curr);
            else if(curr == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    queue.add(stack.pop());
                }
                stack.pop();
            }
            else { // 연산자
                while (!stack.isEmpty() && priority(stack.peek()) >= priority(curr)) {
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
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        infix = br.readLine().toCharArray();
        System.out.println(solve());
    }
}
