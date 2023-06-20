package BOJ.CodePlus.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class MusicProgram_2623 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static List<List<Integer>> graph;
    private static int[] indegree;

    private static List<Integer> solve() {
        List<Integer> res = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] check = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            if(indegree[i] == 0) {
                queue.add(i);
                check[i] = true;
            }
        }

        while (!queue.isEmpty()) {
            int u = queue.poll();
            res.add(u);
            for(int v : graph.get(u)) {
                indegree[v] -= 1;
                if(indegree[v] == 0) queue.add(v);
            }
        }

        boolean isOk = true;
        for (int i = 1; i <= N; i++) {
            if(indegree[i] > 0) isOk = false;
        }

        if(!isOk) return new ArrayList<>(Arrays.asList(0));

        return res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        indegree = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int prevNode = Integer.parseInt(st.nextToken());
            for (int j = 1; j < K; j++) {
                int nextNode = Integer.parseInt(st.nextToken());
                graph.get(prevNode).add(nextNode);
                indegree[nextNode] += 1;
                prevNode = nextNode;
            }
        }

        for(int x: solve()) {
            System.out.println(x);
        }

    }
}
