package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 계란으로_계란치기_16987 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N; // 계란 수
    private static int[] S;
    private static int[] W;
    private static int res;

    // 계란으로 계란을 치게 되면, 각 계란의 내구도는 상대 계란의 무게만큼 깎이게 된다.
    // 내구도 <= 0 되면, 계란은 깨지게 된다.
    // 계란 1의 내구도가 7, 무게가 5
    // 계란 2의 내구도가 3, 무게가 4
    // 치게 되면, 계란 1의 내구도는 4만큼 감소해 3이 되고 계란 2의 내구도는 5만큼 감소해 -2

    private static void solve(int curr, int cnt) {
        if(curr == N) {
            res = Math.max(res, cnt);
            return;
        }

        if(S[curr] <= 0 || cnt == N - 1) {
            solve(curr + 1, cnt);
            return;
        }

        for (int next = 0; next < N; next++) {
            if(curr == next || S[next] <= 0) continue;
            S[curr] -= W[next];
            S[next] -= W[curr];

            if(S[curr] <= 0) cnt += 1;
            if(S[next] <= 0) cnt += 1;

            solve(curr + 1, cnt);

            if(S[curr] <= 0) cnt -= 1;
            if(S[next] <= 0) cnt -= 1;

            S[curr] += W[next];
            S[next] += W[curr];
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        S = new int[N];
        W = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            S[i] = Integer.parseInt(st.nextToken()); // 내구도
            W[i] = Integer.parseInt(st.nextToken()); // 무게
        }

        res = 0;
        solve(0, 0);
        System.out.println(res);
    }
}
