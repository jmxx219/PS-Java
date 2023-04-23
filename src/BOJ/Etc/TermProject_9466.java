package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TermProject_9466 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int T;
    private static int N;
    private static int[] students;
    private static boolean[] visited;
    private static boolean[] done; // 팀 소속 여부
    private static int cnt = 0;

    private static void dfs(int curr) {
        visited[curr] = true;

        int next = students[curr];

        if(!visited[next]) dfs(next);
        else if (!done[next]) {
            while (next != curr){
                cnt += 1;
                next = students[next];
            }
            cnt += 1;
        }

        done[curr] = true;
    }

    private static int solve() {
        for (int i = 1; i <= N; i++) {
            if(!done[i]) dfs(i);
        }
        return N - cnt;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());


        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            students = new int[N + 1];
            done = new boolean[N + 1];
            visited = new boolean[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                students[i] = Integer.parseInt(st.nextToken());
            }
            cnt = 0;
            System.out.println(solve());
        }
    }
}
