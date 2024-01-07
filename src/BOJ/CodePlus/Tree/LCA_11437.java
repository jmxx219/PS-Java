package BOJ.CodePlus.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class LCA_11437 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static List<List<Integer>> graph;
    private static int[] depth;
    private static int[] parent;

    private static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] check = new boolean[N + 1];

        queue.add(start);
        check[start] = true;
        depth[start] = 0;
        parent[start] = 0;

        while (!queue.isEmpty()) {
            int here = queue.poll();
            for(int next: graph.get(here)) {
                if(!check[next]) {
                    check[next] = true;
                    depth[next] = depth[here] + 1;
                    parent[next] = here;
                    queue.add(next);
                }
            }
        }
    }

    private static int LCA(int a, int b) {
        if(depth[a] < depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        while (depth[a] != depth[b]) {
            a = parent[a];
        }

        while (a != b) {
            a = parent[a];
            b = parent[b];
        }

        return a;
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        depth = new int[N + 1];
        parent = new int[N + 1];
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        bfs(1);

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(LCA(a, b) + "\n");
        }
        System.out.println(sb);
    }
}
