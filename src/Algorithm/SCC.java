package Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class SCC {
    public static int N;
    public static List<List<Integer>> graph = new ArrayList<>();
    public static List<List<Integer>> revGraph = new ArrayList<>();
    public static List<Integer> orders = new ArrayList<>();
    public static int[] scc;
    public static int sccCounter = 0;

    public static void revDfsVisit(int here){
        scc[here] = sccCounter;
        for(int next: revGraph.get(here)){
            if(scc[next] == -1) revDfsVisit(next);
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


    public static void solve() {
        scc = new int[N];
        Arrays.fill(scc, -1);

        dfs();
        revDfs();
    }
}

