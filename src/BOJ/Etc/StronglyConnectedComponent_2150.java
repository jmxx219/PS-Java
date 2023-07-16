package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class StronglyConnectedComponent_2150 {
    public static BufferedReader br;
    public static StringTokenizer st;
    public static int N;
    public static int M;
    public static List<List<Integer>> graph;
    public static List<List<Integer>> revGraph;
    public static int[] scc;
    public static int sccCounter;
    public static List<Integer> orders;

    public static void revDfsVisit(int here){
        scc[here] = sccCounter;
        for(int there: revGraph.get(here)){
            if(scc[there] == -1) revDfsVisit(there);
        }
    }

    public static void revDfs(){
        for(int here: orders){
            if(scc[here] == -1){
                revDfsVisit(here);
                sccCounter++;
            }
        }
    }

    public static void dfsVisit(int here, boolean[] visited){
        visited[here] = true;
        for(int next: graph.get(here)) {
            if (!visited[next]) dfsVisit(next, visited);
        }
        orders.add(here);
    }

    public static void dfs() {
        boolean[] visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            if(!visited[i]) dfsVisit(i, visited);
        }
        Collections.reverse(orders);
    }


    public static List<List<Integer>> solve() {
        orders = new ArrayList<>();
        sccCounter = 0;
        scc = new int[N];
        Arrays.fill(scc, -1);

        dfs();
        revDfs();

        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < sccCounter; i++) res.add(new ArrayList<>());
        for(int i = 0; i < N; i++) res.get(scc[i]).add(i);
        Collections.sort(res, (o1, o2) -> Integer.compare(o1.get(0), o2.get(0)));
        return res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        revGraph = new ArrayList<>();
        for(int i = 0; i < N; i++){
            graph.add(new ArrayList<>());
            revGraph.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            graph.get(u).add(v);
            revGraph.get(v).add(u);
        }

        List<List<Integer>> res = solve();

        StringBuilder sb = new StringBuilder();
        sb.append(sccCounter).append("\n");
        for(List<Integer> list : res){
            for(int num: list) sb.append(num + 1).append(" ");
            sb.append(-1).append("\n");
        }
        System.out.println(sb);
    }
}
