package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 창용_마을_무리의_개수_7465 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int T;
    private static int N;
    private static int M;
    private static List<List<Integer>> graph;

    private static void bfs(int start, boolean[] check) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        check[start] = true;

        while(!q.isEmpty()) {
            int here = q.poll();
            for(int next: graph.get(here)) {
                if(!check[next]) {
                    check[next] = true;
                    q.add(next);
                }
            }
        }
    }

    private static int solve() {
        int cnt = 0;
        boolean[] check = new boolean[N];

        for(int i = 0; i < N; i++) {
            if(!check[i]) {
                bfs(i, check);
                cnt += 1;
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            for(int i = 0; i < N; i++) {
                graph.add(new ArrayList<>());
            }
            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                graph.get(a).add(b);
                graph.get(b).add(a);
            }
            System.out.println("#" + (t + 1) + " " + solve());
        }
    }
}
