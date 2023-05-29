package BOJ.CodePlus.DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;

public class StringExplosion_9935 {
    private static BufferedReader br;
    private static char[] str;
    private static char[] explosion;

    private static String solve() {
        Stack<Character> stack = new Stack<>();
        Stack<Character> tmp = new Stack<>();
        for (int i = 0; i < str.length; i++) {
            stack.add(str[i]);
            int index = explosion.length - 1;
            while (!stack.isEmpty() && index >= 0 && stack.peek() == explosion[index]) {
                index -= 1;
                tmp.add(stack.pop());
            }
            if(index == -1) tmp.clear();
            else {
                while (!tmp.isEmpty()) {
                    stack.add(tmp.pop());
                }
            }
        }
        String res = stack.stream().map(Objects::toString).collect(Collectors.joining());
        return res.length() == 0 ? "FRULA" : res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine().toCharArray();
        explosion = br.readLine().toCharArray();
        System.out.println(solve());
    }
}
