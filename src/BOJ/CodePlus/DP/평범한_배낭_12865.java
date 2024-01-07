package BOJ.CodePlus.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 평범한_배낭_12865 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int K;
    private static Item[] items;

    public static class Item {
        public int weight;
        public int profit;

        public Item(int weight, int profit) {
            this.profit = profit;
            this.weight = weight;
        }
    }

    private static int solve() {
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 1; i < N + 1; i++) {
            for (int w = 1; w < K + 1; w++) {
                if(items[i].weight > w) dp[i][w] = dp[i - 1][w];
                else dp[i][w] = Math.max(dp[i - 1][w],
                        dp[i - 1][w - items[i].weight] + items[i].profit);
            }
        }
        return dp[N][K];
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        items = new Item[N + 1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            items[i] = new Item(w, p);
        }

        System.out.println(solve());
    }
}
