package BOJ.CodePlus.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class TwoCoin_16197 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int[][] board;
    private static int[] dy = {0, 0, 1, -1};
    private static int[] dx = {1, -1, 0, 0};

    public static class Point {
        public int y1, x1;
        public int y2, x2;
        public int dist;
        public Point(int y1, int x1, int y2, int x2, int dist) {
            this.y1 = y1;
            this.x1 = x1;
            this.y2 = y2;
            this.x2 = x2;
            this.dist = dist;
        }
    }

    private static boolean isRange(int y, int x) {
        return  0 <= y && y < N && 0 <= x && x < M;
    }


    private static int bfs(Point coin) {
        Queue<Point> queue = new LinkedList<>();

        queue.add(coin);

        while (!queue.isEmpty()) {
            Point here = queue.poll();

            if(here.dist == 11) return -1;

            for(int i = 0; i < 4; i++) {
                int ny1 = here.y1 + dy[i];
                int nx1 = here.x1 + dx[i];
                int ny2 = here.y2 + dy[i];
                int nx2 = here.x2 + dx[i];

                if(!(isRange(ny1, nx1) || isRange(ny2, nx2))) continue;

                if(isRange(ny1, nx1) != isRange(ny2, nx2)) return here.dist + 1;

                if(board[ny1][nx1] == 2 && board[ny2][nx2] == 2) continue;

                 if (board[ny1][nx1] == 2) {
                     ny1 = here.y1;
                     nx1 = here.x1;
                 }
                 if (board[ny2][nx2] == 2) {
                     ny2 = here.y2;
                     nx2 = here.x2;
                 }
                 queue.add(new Point(ny1, nx1, ny2, nx2, here.dist + 1));
            }

        }

        return -1;
    }

    private static int solve(Point coin) {
        int minCnt = bfs(coin);

        if(minCnt <= 10) return minCnt;

        return -1;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M]; // 0: 동전, 1: 빈칸, 2: 벽
        int y1 = -1, x1 = -1;
        int y2 = -1, x2 = -1;
        for(int i = 0; i < N; i++) {
            String[] st = br.readLine().strip().split("");
            for (int j = 0; j < M; j++) {
                if(st[j].equals("o")) {
                    board[i][j] = 0;
                    if(y1 == -1) {
                        y1 = i;
                        x1 = j;
                    }
                    else if(y2 == -1) {
                        y2 = i;
                        x2 = j;
                    }
                }
                else if(st[j].equals(".")) board[i][j] = 1;
                else if(st[j].equals("#")) board[i][j] = 2;
            }
        }

        System.out.println(solve(new Point(y1, x1, y2, x2, 0)));
    }
}
