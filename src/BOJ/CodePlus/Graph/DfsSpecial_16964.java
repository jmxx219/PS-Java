package BOJ.CodePlus.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class DfsSpecial_16964 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static List<List<Integer>> graph;
    private static int[] arrDfs;
    private static int[] order;
    private static boolean[] isVisited;
    private static List<Integer> selectDfs;

    public static void dfs(int u) {
        isVisited[u] = true;
        selectDfs.add(u);
        for(int v : graph.get(u)) {
            if(!isVisited[v]) {
                dfs(v);
            }
        }
    }

    public static boolean solve() {
        for(int i = 0; i < N; i++) {
            Collections.sort(graph.get(i), new Comparator<Integer>() {
                @Override
                public int compare(Integer u, Integer v) {
                    if(order[u] > order[v]) {
                        return 1;
                    } else if(order[u] < order[v]) {
                        return -1;
                    }
                    return 0;
                }
            });
        }

        isVisited = new boolean[N];
        selectDfs = new ArrayList<>();
        dfs(0);

        for (int i = 0; i < N; i++) {
            if (selectDfs.get(i) != arrDfs[i]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        arrDfs = new int[N];
        order = new int[N];

        for(int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arrDfs[i] = Integer.parseInt(st.nextToken()) - 1;
            order[arrDfs[i]] = i;
        }

        if(solve()) {
            System.out.println(1);
        }
        else {
            System.out.println(0);
        }
    }
}
