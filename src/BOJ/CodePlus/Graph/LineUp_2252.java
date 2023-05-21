package BOJ.CodePlus.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class LineUp_2252 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N; // 학생 수
    private static int M; // 비교 횟수
    private static List<List<Integer>> graph;
    private static int[] indegree;

    public static List<Integer> topological_sort() {
        List<Integer> res = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if(indegree[i] == 0) queue.add(i);
        }

        while (!queue.isEmpty()) {
            int u = queue.poll();
            res.add(u);
            for(int v : graph.get(u)) {
                indegree[v] -= 1;
                if(indegree[v] == 0) queue.add(v);
            }
        }

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
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            indegree[v] += 1;
        }

        topological_sort().stream().forEach(n -> System.out.print(n + " "));
    }
}
