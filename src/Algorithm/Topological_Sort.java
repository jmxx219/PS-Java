package Algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 위상 정렬: 비순환 방향 그래프(DAG)에서 정점을 선형으로 정렬
// 순서가 정해져 있는 작업을 수행할 때 이용
public class Topological_Sort {
    private static int N;
    private static List<List<Integer>> graph;
    private static int[] indegree;

    public static List<Integer> topological_sort_bfs() {
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

    public static void topological_sort_dfs(int u, List<Integer> res, boolean[] check) {
        check[u] = true;
        for(int v : graph.get(u)) {
            if(!check[v]) topological_sort_dfs(v, res, check);
        }
        res.add(u);
    }

    public static void main(String[] args) {
        List<Integer> res = new ArrayList<>();
        boolean[] check = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            if(!check[i]) topological_sort_dfs(i, res, check);
        }
        res.stream().forEach(n -> System.out.print(n + " "));
    }

}
