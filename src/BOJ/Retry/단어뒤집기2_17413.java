package BOJ.Retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// Pass(15m)
public class 단어뒤집기2_17413 {
    private static BufferedReader br;
    private static String S;
    private static Stack<Character> stack;

    public static StringBuilder solve() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < S.length(); i++) {
            if(S.charAt(i) == '<' || S.charAt(i) == ' ') {
                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
            }

            if(S.charAt(i) == '<') {
                int j = i;
                while (S.charAt(j) != '>') {
                    sb.append(S.charAt(j));
                    j += 1;
                }
                sb.append(S.charAt(j));
                i = j;
            }
            else if(S.charAt(i) == ' ') sb.append(' ');
            else stack.add(S.charAt(i));
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        stack = new Stack<>();
        System.out.println(solve());
    }
}
