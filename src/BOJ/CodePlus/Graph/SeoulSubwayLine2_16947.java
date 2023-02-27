package BOJ.CodePlus.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class SeoulSubwayLine2_16947 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static List<List<Integer>> graph;
    private static boolean[] visited;
    private static boolean[] isCycle;
    private static int[] dist;
    private static Set<Integer> circularLine;

    private static int dfs2(int here, int prev) {
        if(visited[here]) {
            return here;
        }

        visited[here] = true;

        for(int next : graph.get(here)) {
            if(next == prev) continue;

            int startCycle = dfs2(next, here);

            if(startCycle == -2) return -2; // 사이클을 찾았지만 포함되어있지 않음

            if(startCycle >= 0) { // 사이클에 해당
                isCycle[here] = true;
                if(here == startCycle) return -2;
                else return startCycle;
            }
        }

        return -1; // 사이클 못찾음
    }

    private static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        dist = new int[N];

        for (int i = 0; i < N; i++) {
            if (isCycle[i]) {
                dist[i] = 0;
                queue.add(i);
            } else {
                dist[i] = -1;
            }
        }

        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (int w : graph.get(v)) {
                if (dist[w] == -1) {
                    queue.add(w);
                    dist[w] = dist[v] + 1;
                }
            }
        }
    }

    private static void solve2() {
        visited = new boolean[N];
        isCycle = new boolean[N];
        dfs2(0, -1); // 사이클 찾기

        dist = new int[N];
        bfs(); // 순환선까지 거리 구하기
        for(int i = 0; i < N; i++) {
            System.out.print(dist[i] + " ");
        }
        System.out.println();
    }

    private static void dfs(int start, int depth) {
        if(visited[start]) {
            if(depth - dist[start] >= 3) {
                circularLine.add(start);
            }
            return;
        }

        visited[start] = true;
        dist[start] = depth;

        for(int w : graph.get(start)) {
            dfs(w, depth + 1);
        }
    }

    private static void solve1() {
        circularLine = new HashSet<>();

        for(int i = 0; i < N; i++) {
            visited = new boolean[N];
            dist = new int[N];
            dfs(i, 1);
        }

        dist = new int[N];
        visited = new boolean[N];
        for(int v : circularLine) {
            visited[v] = true;
            for(int w : graph.get(v)) {
                if(!circularLine.contains(w)) {
                    dfs(w, 1);
                }
            }
        }
        for(int i = 0; i < N; i++) {
            System.out.print(dist[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        solve1();
        solve2();
    }
}
