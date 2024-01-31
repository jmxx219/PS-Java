package BOJ.Retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 트리의_높이와_너비_2250 {
    static class Node {
        int left;
        int right;
        int order;
        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static Node[] nodes;
    private static int[] parentCnt;
    private static int root;
    private static List<List<Integer>> ordersOfLevel;

    private static void dfs(int root, int depth, List<Integer> orders) {
        if(ordersOfLevel.size() <= depth) ordersOfLevel.add(new ArrayList<>());
        ordersOfLevel.get(depth).add(root);
        if(nodes[root].left != -1) dfs(nodes[root].left, depth + 1, orders);
        orders.add(root);
        if(nodes[root].right != -1) dfs(nodes[root].right, depth + 1, orders);
    }

    private static void solve() {
        ordersOfLevel = new ArrayList<>();
        List<Integer> orders = new ArrayList<>();
        dfs(root, 0, orders);

        for(int i = 0; i < N; i++) {
            nodes[orders.get(i)].order = i + 1;
        }

        int resLevel = 1;
        int resDiff = 1;
        for (int i = 0; i < ordersOfLevel.size(); i++) {
            int minOrder = N;
            int maxOrder = 1;
            for(int n : ordersOfLevel.get(i)) {
                minOrder = Math.min(minOrder, nodes[n].order);
                maxOrder = Math.max(maxOrder, nodes[n].order);
            }
            int levelDiff = maxOrder - minOrder + 1;
            if(resDiff < levelDiff) {
                resDiff = levelDiff;
                resLevel = i + 1;
            }
        }

        System.out.println(resLevel + " " + resDiff);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        nodes = new Node[N + 1];
        parentCnt = new int[N + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int here = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            nodes[here] = new Node(left, right);
            if(left != -1) parentCnt[left] += 1;
            if(right != -1) parentCnt[right] += 1;
        }

        for(int i = 1; i <= N; i++) {
            if(parentCnt[i] == 0) root = i;
        }

        solve();
    }
}
