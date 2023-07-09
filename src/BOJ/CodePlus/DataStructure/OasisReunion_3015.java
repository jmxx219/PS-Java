package BOJ.CodePlus.DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class OasisReunion_3015 {
    private static BufferedReader br;
    private static int N;
    private static int[] H;

    static class Person {
        int height;
        int cnt;

        public Person(int height, int cnt) {
            this.height = height;
            this.cnt = cnt;
        }
    }

    private static long solve() {
        Stack<Person> stack = new Stack<>();
        long ans = 0;

        for (int i = 0; i < N; i++) {
            int cnt = 1;
            while (!stack.isEmpty()) {
                if(stack.peek().height <= H[i]) {
                    ans += (long) stack.peek().cnt;
                    if(stack.peek().height == H[i]) {
                        cnt += stack.peek().cnt;
                    }
                    stack.pop();
                }
                else break;
            }

            if(!stack.isEmpty()) ans += 1L;
            stack.add(new Person(H[i], cnt));
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        H = new int[N];
        for (int i = 0; i < N; i++) {
            H[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(solve());
    }
}
