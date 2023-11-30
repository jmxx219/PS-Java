package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Pass
public class 세친구_17089 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int[] friends;
    private static boolean[][] map;
    private static int[] picked;
    private static int res;

    private static void solve(int start, int index, boolean[] check) {
        if(index == 3) {
            if(map[picked[0]][picked[2]]) {
                int cnt = Arrays.stream(picked).map(f -> friends[f] - 2).sum();
                if(res == -1 || res > cnt) res = cnt;
            }
            return;
        }
        for (int i = start; i < N + 1; i++) {
            if(check[i]) continue;
            if(index > 0 && !map[picked[index - 1]][i]) continue;
            picked[index] = i;
            check[i] = true;
            solve(i + 1, index + 1, check);
            check[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        friends = new int[N + 1];
        map = new boolean[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = map[b][a] = true;
            friends[a] += 1;
            friends[b] += 1;
        }

        res = -1;
        picked = new int[3];
        solve(1, 0, new boolean[N + 1]);
        System.out.println(res);
    }
}
