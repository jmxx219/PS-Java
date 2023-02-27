package BOJ.CodePlus.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BfsSpecial_16940 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static List<List<Integer>> graph;
    private static int[] order;
    private static int[] parent;

    private static boolean bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] isVisited = new boolean[N];

        queue.add(start);
        isVisited[start] = true;

        int orderIndex = 1;
        for(int i = 0; i < N; i++) {
            if(queue.isEmpty()) return false;

            int v = queue.poll();
            if(v != order[i]) return false;

            int cnt = 0; // 큐에 넣어야 할 정점 개수
            for(int w : graph.get(v)) {
                if(!isVisited[w]) {
                    parent[w] = v;
                    cnt += 1;
                }
            }

            for(int j = 0; j < cnt; j++) {
                if(orderIndex + j >= N || parent[order[orderIndex + j]] != v) return false;
                queue.add(order[orderIndex + j]);
                isVisited[order[orderIndex + j]] = true;
            }
            orderIndex += cnt;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        order = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            order[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        parent = new int[N];
        if(bfs(0)) {
            System.out.println(1);
        }
        else {
            System.out.println(0);
        }
    }
}
