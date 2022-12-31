package BOJ.CodePlus.DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

public class FlipTheWord2_17413 {

    public static String flipTheWord(String str) {
        Queue<Character> queue = new LinkedList<>();
        Stack<Character> stack = new Stack<>();
        int i = 0;

        while(i < str.length()) {
            if(str.charAt(i) == '<' || str.charAt(i) == ' ') {
                while (!stack.empty()) {
                    queue.add(stack.pop());
                }
            }
            if(str.charAt(i) == '<') {
                while (str.charAt(i) != '>') {
                    queue.add(str.charAt(i));
                    i += 1;
                }
                queue.add(str.charAt(i));
            }
            else if(str.charAt(i) == ' ') {
                queue.add(str.charAt(i));
            }
            else {
                stack.push(str.charAt(i));
            }
            i += 1;
        }

        while (!stack.empty()) {
            queue.add(stack.pop());
        }

        return queue.stream().map(Objects::toString).collect(Collectors.joining());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(flipTheWord(br.readLine()));
    }
}
