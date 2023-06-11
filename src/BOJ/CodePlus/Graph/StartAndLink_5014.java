package BOJ.CodePlus.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class StartAndLink_5014 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int F;
    private static int S;
    private static int G;
    private static int U;
    private static int D;

    private static int solve() {
        Queue<Integer> queue = new LinkedList<>();
        int[] dist = new int[F + 1];
        Arrays.fill(dist, -1);

        queue.add(S);
        dist[S] = 0;

        while (!queue.isEmpty()) {
            int here = queue.poll();

            if(here == G) return dist[G];

            if(here + U <= F && dist[here + U] == -1) {
                dist[here + U] = dist[here] + 1;
                queue.add(here + U);
            }
            if(here - D >= 1 && dist[here - D] == -1) {
                dist[here - D] = dist[here] + 1;
                queue.add(here - D);
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        int res = solve();
        if (res == -1) System.out.println("use the stairs");
        else System.out.println(res);
    }
}
