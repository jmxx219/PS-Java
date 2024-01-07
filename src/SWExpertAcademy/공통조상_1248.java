package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 공통조상_1248 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int T;
    private static int V;
    private static int E;
    private static List<List<Integer>> graph;
    private static int[] depth;
    private static int[] parent;
    private static int[] subtree;

    private static int  dfs(int parentNode, int currNode, boolean[] check) {
        check[currNode] = true;
        depth[currNode] = depth[parentNode] + 1;
        parent[currNode] = parentNode;

        for(int next: graph.get(currNode)) {
            if(!check[next]) {
                subtree[currNode] += dfs(currNode, next, check);
            }
        }

        return subtree[currNode];
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

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());

            depth = new int[V + 1];
            parent = new int[V + 1];
            subtree = new int[V + 1];
            Arrays.fill(subtree, 1);
            graph = new ArrayList<>();
            for (int i = 0; i <= V; i++) {
                graph.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph.get(a).add(b);
            }

            dfs(0, 1, new boolean[V + 1]);
            int commonP = LCA(nodeA, nodeB);

            System.out.println("#" + (t + 1) + " " + commonP + " " + subtree[commonP]);
        }
    }
}
