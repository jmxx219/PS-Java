package algostudy.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_2841_외계인의_기타_연주 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N; //멜로디에 포함되어 있는 음의 수
    private static int P; //한 줄에 있는 프렛의 수

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        Stack<Integer>[] stack = new Stack[7];
        for(int i = 1; i <= 6; i++) {
            stack[i] = new Stack<>();
        }

        int cnt = 0;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());

            while (!stack[l].isEmpty() && stack[l].peek() > f) {
                stack[l].pop();
                cnt += 1;
            }

            if(!stack[l].isEmpty() && stack[l].peek() == f) continue;

            stack[l].add(f);
            cnt += 1;
        }
        System.out.println(cnt);
    }
}
