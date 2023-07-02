package BOJ.CodePlus.Random;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Mutalisk_12869 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[] SCV;

    private static final int[][] attack = {
            {9, 3, 1}, {9, 1, 3},
            {3, 9, 1}, {3, 1, 9},
            {1, 3, 9}, {1, 9, 3}};

    public static class Point {
        int a, b, c;
        int cnt;

        public Point(int a, int b, int c, int cnt) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.cnt = cnt;
        }
    }
    private static int solve() {
        boolean[][][] visited = new boolean[61][61][61];
        Queue<Point> queue = new LinkedList<>();

        int x1 = 0, x2 = 0, x3 = 0;
        if(N >= 1) x1 = SCV[0];
        if(N >= 2) x2 = SCV[1];
        if(N >= 3) x3 = SCV[2];

        queue.add(new Point(x1, x2, x3, 0));
        visited[x1][x2][x3] = true;

        while (!queue.isEmpty()) {
            Point here = queue.poll();
            if(here.a <= 0 && here.b <= 0 && here.c <= 0) {
                return here.cnt;
            }

            for(int i = 0 ; i < 6; i++) {
                int a = (here.a - attack[i][0] <= 0) ? 0 :  here.a - attack[i][0];
                int b = (here.b - attack[i][1] <= 0) ? 0 :  here.b - attack[i][1];
                int c = (here.c - attack[i][2] <= 0) ? 0 :  here.c - attack[i][2];
                if(!visited[a][b][c]) {
                    visited[a][b][c] = true;
                    queue.add(new Point(a, b, c, here.cnt + 1));
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        SCV = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            SCV[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve());
    }
}
