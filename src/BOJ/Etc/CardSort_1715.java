package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CardSort_1715 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[] card;

    private static int solve() {
        if(N == 1) return 0;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            minHeap.add(card[i]);
        }

        int res = 0;
        while (!minHeap.isEmpty()) {
            int a = minHeap.poll();
            int b = minHeap.poll();
            int sum = a + b;

            res += sum;
            if(minHeap.isEmpty()) break;
            minHeap.add(sum);
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        card = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            card[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve());
    }
}
