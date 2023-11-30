package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 결혼식_5567 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static boolean[][] friends;

    private static int solve(int curr, boolean[] check, int depth) {
        if(depth == 2) {
            return 0;
        }
        int cnt = 0;
        for (int i = 2; i <= N; i++) {
            if(friends[curr][i]) {
                if(!check[i]) cnt += 1;
                check[i] = true;
                cnt += solve(i, check, depth + 1);
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        friends = new boolean[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            friends[a][b] = friends[b][a] = true;
        }

        System.out.println(solve(1, new boolean[N + 1], 0));
    }
}
