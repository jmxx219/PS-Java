package BOJ.CodePlus.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bucket_2251 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int[] cap;
    private static boolean[] ans;
    private static final int MAX = 200;
    private static int sum;
    public static final int[] fromList = {0, 0, 1, 1, 2, 2};
    public static final int[] toList = {1, 2, 0, 2, 0, 1};

    static class Pair {
        int first;
        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    private static void solve() {
        boolean[][] visited = new boolean[MAX + 1][MAX + 1];
        Queue<Pair> queue = new LinkedList<>();

        queue.add(new Pair(0, 0));
        visited[0][0] = true;
        ans[cap[2]] = true;

        int[] curr;
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            curr = new int[] {p.first, p.second, sum - p.first - p.second};

            for (int k = 0; k < 6; k++) {
                int[] next = new int[] {curr[0], curr[1], curr[2]};
                int to = toList[k];
                int from = fromList[k];

                next[to] += next[from];
                next[from] = 0;
                if(next[to] >= cap[to]) {
                    next[from] = next[to] - cap[to];
                    next[to] = cap[to];
                }

                if(!visited[next[0]][next[1]]) {
                    visited[next[0]][next[1]] = true;
                    queue.add(new Pair(next[0], next[1]));
                    if(next[0] == 0) {
                        ans[next[2]] = true;
                    }
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        cap = new int[3];
        for (int i = 0; i < 3; i++) {
            cap[i] = Integer.parseInt(st.nextToken());
        }

        sum = cap[2];
        ans = new boolean[MAX + 1];

        solve();
        for (int i = 0; i <= cap[2]; i++) {
            if(ans[i]) System.out.print(i + " ");
        }
        System.out.println();
    }
}
