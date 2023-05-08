package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PushbackInputStream;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class FuelFill_1826 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static GasStation[] gasStation;
    private static int L; // 마을까지 거리
    private static int P; // 기존 연료의 양

    public static class GasStation implements Comparable<GasStation> {
        int dist, fuel;

        public GasStation(int dist, int fuel) {
            this.dist = dist;
            this.fuel = fuel;
        }

        @Override
        public int compareTo(GasStation o) {
            return this.dist - o.dist;
        }
    }

    private static int solve() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int res = 0;
        boolean[] visited = new boolean[N];

        while (P < L) {
            for (int i = 0; i < N; i++) {
                if(!visited[i] && gasStation[i].dist <= P) {
                    visited[i] = true;
                    pq.add(gasStation[i].fuel);
                }
            }

            if(pq.isEmpty()) return -1;

            res += 1;
            P += pq.poll();
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        gasStation = new GasStation[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            gasStation[i] = new GasStation(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        System.out.println(solve());
    }
}
