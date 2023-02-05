package BOJ.CodePlus.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class TreeHeightAndWidth_2250 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N; // 노드 개수
    private static Node[] nodes;
    private static int[] parentCnt;
    private static int root;
    private static List<List<Integer>> ordersOfLevel;
    private static int width = 0;

    public static class Node {
        public int left;
        public int right;
        public int order;
        private int level;
        Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    public static void inorder(int node, int depth) {
        if(node == -1) return;

        if(ordersOfLevel.size() <= depth) {
            ordersOfLevel.add(new ArrayList<>());
        }

        inorder(nodes[node].left, depth + 1);

        nodes[node].level = depth;
        nodes[node].order = ++width;
        ordersOfLevel.get(depth).add(nodes[node].order);

        inorder(nodes[node].right, depth + 1);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        nodes = new Node[N + 1];
        parentCnt = new int[N + 1];
        ordersOfLevel = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int here = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            nodes[here] = new Node(left, right);
            if(left != -1) {
                parentCnt[left] += 1;
            }
            if(right != -1) {
                parentCnt[right] += 1;
            }
        }

        for(int i = 1; i <= N; i++) {
            if(parentCnt[i] == 0) {
                root = i;
            }
        }

        ordersOfLevel.add(new ArrayList<>()); // 레벨 0
        inorder(root, 1);

        int resWidth = 1;
        int resLevel = 1;
        for(int level = 1; level < ordersOfLevel.size(); level++) {
            List<Integer> orders = ordersOfLevel.get(level);
            int minWidth = Collections.min(orders);
            int maxWidth = Collections.max(orders);
            if(resWidth < maxWidth - minWidth + 1) {
                resWidth = maxWidth - minWidth + 1;
                resLevel = level;
            }
        }

        System.out.println(resLevel + " " + resWidth);
    }

}
