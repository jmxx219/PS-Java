package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class RemoteControl_1107 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static Set<Integer> brokenBtn;
    private static int INF = 1_000_000;

    public static boolean possible(int num) {
        if(num == 0) {
            return brokenBtn.contains(num) ? false : true;
        }
        while (num > 0) {
            if(brokenBtn.contains(num % 10)) {
                return false;
            }
            num /= 10;
        }
        return true;

    }

    public static int move() {
        int n_minus = N;
        int minus_cnt = INF;
        while(n_minus >= 0) {
            if(possible(n_minus)) {
                minus_cnt = Math.min(minus_cnt, String.valueOf(n_minus).length() + N - n_minus);
                break;
            }
            n_minus -= 1;
        }

        int n_plus = N;
        int plus_cnt = INF;
        while(n_plus <= INF) {
            if(possible(n_plus)) {
                plus_cnt = Math.min(plus_cnt, String.valueOf(n_plus).length() + n_plus - N);
                break;
            }
            n_plus += 1;
        }

        return Math.min(plus_cnt, minus_cnt);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        brokenBtn = new HashSet<>();
        if(M > 0) {
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < M; i++) {
                brokenBtn.add(Integer.parseInt(st.nextToken()));
            }
        }
        System.out.println(N == 100 ? 0 : move());
    }
}
