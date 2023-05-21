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

public class Work_2056 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[] work;
    private static List<List<Integer>> graph;
    private static int[] indegree;

    private static int solve() {
        int[] res = new int[N + 1];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if(indegree[i] == 0) {
                queue.add(i);
                res[i] = work[i];
            }
        }

        while (!queue.isEmpty()) {
            int u = queue.poll();
            
            for(int v : graph.get(u)) {
                indegree[v] -= 1;
                if(res[v] < res[u] + work[v]) res[v] =  res[u] + work[v];
                if(indegree[v] == 0) queue.add(v);
            }
        }

        return Arrays.stream(res).max().getAsInt();
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        indegree = new int[N + 1];
        work = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            work[i] = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            indegree[i] = M;
            for (int j = 0; j < M; j++) {
                int u = Integer.parseInt(st.nextToken());
                graph.get(u).add(i);
            }
        }

        System.out.println(solve());
    }
}
